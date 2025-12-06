/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class RAM extends Komponen {

    private int kapasitas;  // dalam GB
    private int speed;      // dalam MHz
    private String tipe;    // contoh: DDR4, DDR5

    public RAM(String nama, int kapasitas, int speed) {
        this(nama, kapasitas, speed, "DDR4");
    }

    public RAM(String nama, int kapasitas, int speed, String tipe) {
        super(nama, "RAM");
        this.kapasitas = kapasitas;
        this.speed = speed;
        this.tipe = tipe;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public int getSpeed() {
        return speed;
    }

    public String getTipe() {
        return tipe;
    }

    @Override
    public String toString() {
        return super.toString() +
                " [" + kapasitas + "GB, " + speed + "MHz, " + tipe + "]";
    }
}
