/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

import model.CPU;
import model.GPU;
import model.Motherboard;
import model.PC;
import model.PowerSupply;
import model.RAM;
import model.Storage;

public class KompatibilitasChecker {

    public static void cekKompatibilitas(PC pc) throws IncompatibleComponentException {
        CPU cpu = (CPU) pc.getByKategori("CPU");
        Motherboard mb = (Motherboard) pc.getByKategori("Motherboard");
        RAM ram = (RAM) pc.getByKategori("RAM");
        GPU gpu = (GPU) pc.getByKategori("GPU");
        Storage storage = (Storage) pc.getByKategori("Storage");
        PowerSupply psu = (PowerSupply) pc.getByKategori("PowerSupply");

        if (cpu != null && mb != null) {
            String cpuSocket = cpu.getSocket();
            String mbSocket = mb.getSocket();
            if (cpuSocket == null || mbSocket == null || !cpuSocket.equalsIgnoreCase(mbSocket)) {
                throw new IncompatibleComponentException(
                    "Incompatible: CPU socket '" + cpuSocket + "' tidak cocok dengan Motherboard socket '" + mbSocket + "'."
                );
            }
        }
        if (ram != null && mb != null) {
            int ramKapasitas = ram.getKapasitas();    
            int mbMaxRam = mb.getMaxRAM();              
            if (ramKapasitas > mbMaxRam) {
                throw new IncompatibleComponentException(
                    "Incompatible: Kapasitas RAM (" + ramKapasitas + "GB) melebihi kapasitas maksimum Motherboard (" + mbMaxRam + "GB)."
                );
            }
        }
        if (storage != null) {
            String tipe = storage.getTipe();
            if (tipe != null && tipe.equalsIgnoreCase("NVME") && mb != null) {
            }
        }
        if (psu != null) {
            int estimatedWatt = estimateWatt(cpu, gpu, ram, storage, mb);
            if (psu.getDaya() < estimatedWatt) {
                throw new IncompatibleComponentException(
                    "Incompatible: Daya PSU (" + psu.getDaya() + "W) diperkirakan tidak cukup untuk komponen (~" + estimatedWatt + "W)."
                );
            }
        }
    }

    private static int estimateWatt(CPU cpu, GPU gpu, RAM ram, Storage storage, Motherboard mb) {
        int total = 0;

        if (cpu != null) {
            int cores = Math.max(1, cpu.getCoreCount());
            total += cores * 10; 
        }

        if (gpu != null) {
            int assumed = 150; 
            try {
                assumed += Math.max(0, gpu.getVram() - 4) * 10;
            } catch (Exception e) {}
            total += assumed;
        }

        if (ram != null) {
            total += Math.max(1, ram.getKapasitas()) * 3; 
        }

        if (storage != null) {
            String tipe = storage.getTipe();
            if (tipe != null && tipe.equalsIgnoreCase("HDD")) total += 8;
            else total += 5; 
        }

        if (mb != null) {
            total += 30; 
        }
        total = (int) Math.ceil(total * 1.2);

        return Math.max(50, total);
    }
}
