/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulation;

import inventory.Inventaris;
import inventory.StokTidakCukupException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MarketDemandSimulator implements Runnable {

    private final Inventaris inventaris;
    private final List<String> komponenFavorit;
    private final Random random = new Random();
    private volatile boolean running = true;

    private long intervalMs = 7000;

    public MarketDemandSimulator(Inventaris inventaris) {
        this.inventaris = inventaris;
        this.komponenFavorit = Arrays.asList(
                "Intel Core i5-12400F",
                "AMD Ryzen 5 5600",
                "NVIDIA RTX 4060 8GB",
                "Corsair Vengeance 16GB 3200",
                "SSD NVMe 512GB",
                "PSU 550W Bronze",
                "ASUS Prime B660M"
        );
    }

    public void setIntervalMs(long intervalMs) {
        this.intervalMs = intervalMs;
    }

    public void stop() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            String nama = komponenFavorit.get(random.nextInt(komponenFavorit.size()));

            try {
                inventaris.ambilKomponen(nama, 1);
                System.out.println("[MarketDemand] Membeli 1 unit: " + nama);
            } catch (StokTidakCukupException e) {
                System.out.println("[MarketDemand] Gagal membeli " + nama +
                        " (stok tidak cukup)");
            }

            try {
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                running = false;
                Thread.currentThread().interrupt();
            }
        }
    }
}
