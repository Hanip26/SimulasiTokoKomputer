/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class GPU extends Komponen {

    private int vram;   

    public GPU(String nama, int vram) {
        super(nama, "GPU");
        this.vram = vram;
    }

    public int getVram() {
        return vram;
    }

    @Override
    public String toString() {
        return super.toString() + " [VRAM=" + vram + "GB]";
    }
}
