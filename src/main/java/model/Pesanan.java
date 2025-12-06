/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.concurrent.atomic.AtomicInteger;
import order.StatusPesanan;

public class Pesanan {

    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private int id;
    private PC pc;
    private StatusPesanan status;  

    public Pesanan(PC pc, StatusPesanan status) {
        this.id = COUNTER.getAndIncrement();
        this.pc = pc;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public PC getPC() {
        return pc;
    }

    public StatusPesanan getStatus() {
        return status;
    }

    public void setStatus(StatusPesanan status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pesanan #" + id + " [" + status + "]";
    }
}

