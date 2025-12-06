/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Storage extends Komponen {

    private int kapasitas;     
    private String tipe;       

    public Storage(String nama, int kapasitas, String tipe) {
        super(nama, "Storage");
        this.kapasitas = kapasitas;
        this.tipe = tipe;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public String getTipe() {
        return tipe;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + kapasitas + "GB, " + tipe + "]";
    }
}

