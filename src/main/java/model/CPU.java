/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class CPU extends Komponen {
    private String socket;  
    private int coreCount;
    private double clockSpeed;  

    public CPU(String nama, String socket, int coreCount, double clockSpeed) {
        super(nama, "CPU");
        this.socket = socket;
        this.coreCount = coreCount;
        this.clockSpeed = clockSpeed;
    }

    public String getSocket() {
        return socket;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public double getClockSpeed() {
        return clockSpeed;
    }

    @Override
    public String toString() {
        return super.toString() + " [Socket=" + socket + ", Core=" + coreCount +
                ", Clock=" + clockSpeed + "GHz]";
    }
}
