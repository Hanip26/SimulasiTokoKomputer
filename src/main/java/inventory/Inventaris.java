/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory;

import java.util.HashMap;
import java.util.Map;

import model.*;   

public class Inventaris {
    private Map<String, Integer> stok = new HashMap<>();
    private Map<String, Komponen> daftarKomponen = new HashMap<>();

    public void tambahStok(Komponen komponen, int jumlah) {
        String nama = komponen.getNama();
        daftarKomponen.putIfAbsent(nama, komponen);
        stok.put(nama, stok.getOrDefault(nama, 0) + jumlah);
    }

    public int getStok(String namaKomponen) {
        return stok.getOrDefault(namaKomponen, 0);    
    }
    
    public Map<String, Integer> getStokMap() {
    return new HashMap<>(stok);
    }
    public Komponen ambilKomponen(String namaKomponen, int jumlah)
            throws StokTidakCukupException {
        int stokSekarang = stok.getOrDefault(namaKomponen, 0);
        if (stokSekarang < jumlah) {
            throw new StokTidakCukupException(
                "Stok komponen '" + namaKomponen + "' tidak cukup. " +
                "Tersedia: " + stokSekarang + ", Dibutuhkan: " + jumlah
            );
        }
        stok.put(namaKomponen, stokSekarang - jumlah);
        return daftarKomponen.get(namaKomponen);
    }

    public void tampilkanStok() {
        System.out.println("===== STOK INVENTARIS =====");
        for (String nama : stok.keySet()) {
            System.out.println("- " + nama + " : " + stok.get(nama));
        }
    }

}
