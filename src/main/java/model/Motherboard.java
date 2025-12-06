/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Motherboard extends Komponen {

    private String socket;
    private String chipset;
    private int maxRAM;
    private String tipeRAM;

    public Motherboard(String nama, String socket, String chipset, int maxRAM) {
        this(nama, socket, chipset, maxRAM, "DDR4");
    }

    public Motherboard(String nama, String socket, String chipset, int maxRAM, String tipeRAM) {
        super(nama, "Motherboard");
        this.socket = socket;
        this.chipset = chipset;
        this.maxRAM = maxRAM;
        this.tipeRAM = tipeRAM;
    }

    public String getSocket() {
        return socket;
    }

    public String getChipset() {
        return chipset;
    }

    public int getMaxRAM() {
        return maxRAM;
    }

    public String getTipeRAM() {
        return tipeRAM;
    }

    @Override
    public String toString() {
        return super.toString() + " [Socket=" + socket +
                ", Chipset=" + chipset +
                ", MaxRAM=" + maxRAM + "GB" +
                ", TipeRAM=" + tipeRAM + "]";
    }
}
