/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package order;

import java.util.ArrayList;
import java.util.List;

import inventory.Inventaris;
import inventory.StokTidakCukupException;
import model.Komponen;
import model.PC;
import model.Pesanan;

public class OrderManager {

    private List<Pesanan> daftarPesanan = new ArrayList<>();
    private Inventaris inventaris;

    public OrderManager(Inventaris inventaris) {
        this.inventaris = inventaris;
    }

    // Tambah pesanan baru
    public void tambahPesanan(Pesanan p) {
        p.setStatus(StatusPesanan.MENUNGGU_KOMPONEN);
        daftarPesanan.add(p);
    }

    // Cek apakah pesanan bisa berpindah status → SIAP DIRAKIT
    public void prosesCekPesanan() {
        for (Pesanan pesanan : daftarPesanan) {

            if (pesanan.getStatus() != StatusPesanan.MENUNGGU_KOMPONEN)
                continue;

            PC rakitan = pesanan.getPC();
            List<Komponen> komponenList = rakitan.getKomponen();

            boolean stokLengkap = true;

            for (Komponen k : komponenList) {
                if (inventaris.getStok(k.getNama()) < 1) {
                    stokLengkap = false;
                    break;
                }
            }

            if (stokLengkap) {
                pesanan.setStatus(StatusPesanan.SIAP_DIRAKIT);
            }
        }
    }

    // Ambil komponen dan tandai pesanan selesai
    public boolean ambilKomponenUntukPesanan(Pesanan pesanan) {

        if (pesanan.getStatus() != StatusPesanan.SIAP_DIRAKIT) {
            return false;
        }

        try {
            for (Komponen k : pesanan.getPC().getKomponen()) {
                inventaris.ambilKomponen(k.getNama(), 1);
            }
            pesanan.setStatus(StatusPesanan.SELESAI);
            return true;

        } catch (StokTidakCukupException e) {
            System.out.println("[GAGAL] " + e.getMessage());
            return false;
        }
    }

    // Getter daftar pesanan berdasarkan status
    public List<Pesanan> getPesananMenunggu() {
        List<Pesanan> list = new ArrayList<>();
        for (Pesanan p : daftarPesanan) {
            if (p.getStatus() == StatusPesanan.MENUNGGU_KOMPONEN)
                list.add(p);
        }
        return list;
    }

    public List<Pesanan> getPesananSiapDirakit() {
        List<Pesanan> list = new ArrayList<>();
        for (Pesanan p : daftarPesanan) {
            if (p.getStatus() == StatusPesanan.SIAP_DIRAKIT)
                list.add(p);
        }
        return list;
    }

    public List<Pesanan> getPesananSelesai() {
        List<Pesanan> list = new ArrayList<>();
        for (Pesanan p : daftarPesanan) {
            if (p.getStatus() == StatusPesanan.SELESAI)
                list.add(p);
        }
        return list;
    }
}

