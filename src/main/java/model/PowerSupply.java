/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class PowerSupply extends Komponen {

    private int daya;    

    public PowerSupply(String nama, int daya) {
        super(nama, "PowerSupply");
        this.daya = daya;
    }

    public int getDaya() {
        return daya;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + daya + "W]";
    }
}

