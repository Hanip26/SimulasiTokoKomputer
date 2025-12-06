/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

public class PC {

    private final ArrayList<Komponen> komponenTerpilih = new ArrayList<>();

    private CPU cpu;
    private Motherboard motherboard;
    private RAM ram;
    private GPU gpu;
    private Storage storage;
    private PowerSupply powerSupply;

    public PC() {
    }

    public void tambahKomponen(Komponen k) {
        if (k == null) return;

        komponenTerpilih.removeIf(existing ->
                existing.getKategori().equalsIgnoreCase(k.getKategori()));

        komponenTerpilih.add(k);

        if (k instanceof CPU) {
            cpu = (CPU) k;
        } else if (k instanceof Motherboard) {
            motherboard = (Motherboard) k;
        } else if (k instanceof RAM) {
            ram = (RAM) k;
        } else if (k instanceof GPU) {
            gpu = (GPU) k;
        } else if (k instanceof Storage) {
            storage = (Storage) k;
        } else if (k instanceof PowerSupply) {
            powerSupply = (PowerSupply) k;
        }
    }

    public ArrayList<Komponen> getKomponen() {
        return new ArrayList<>(komponenTerpilih);
    }

    public Komponen getByKategori(String kategori) {
        if (kategori == null) return null;
        for (Komponen k : komponenTerpilih) {
            if (kategori.equalsIgnoreCase(k.getKategori())) {
                return k;
            }
        }
        return null;
    }

    public CPU getCpu() {
        return cpu;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public RAM getRam() {
        return ram;
    }

    public GPU getGpu() {
        return gpu;
    }

    public Storage getStorage() {
        return storage;
    }

    public PowerSupply getPowerSupply() {
        return powerSupply;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
        if (cpu != null) {
            tambahKomponen(cpu);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Konfigurasi PC:\n");
        for (Komponen k : komponenTerpilih) {
            sb.append("- ").append(k).append("\n");
        }
        return sb.toString();
    }
}
