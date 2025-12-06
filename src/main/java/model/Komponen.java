/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public abstract class Komponen {

    protected String nama;
    protected String kategori;

    public Komponen(String nama, String kategori) {
        this.nama = nama;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    @Override
    public String toString() {
        return nama;
    }
}
