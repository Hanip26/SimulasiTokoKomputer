/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulation;

import inventory.Inventaris;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class SupplierSimulator implements Runnable {

    private final Inventaris inventaris;
    private final List<Komponen> katalogSupplier = new ArrayList<>();
    private volatile boolean running = true;

    // Interval suplai dalam milidetik
    private long intervalMs = 5000;

    public SupplierSimulator(Inventaris inventaris) {
        this.inventaris = inventaris;
        initKatalog();
    }

    private void initKatalog() {
        // CPU
        CPU cpu1 = new CPU("Intel Core i3-12100F", "LGA1700", 4, 3.3);
        CPU cpu2 = new CPU("Intel Core i5-12400F", "LGA1700", 6, 3.5);
        CPU cpu3 = new CPU("AMD Ryzen 5 5600", "AM4", 6, 3.6);
        CPU cpu4 = new CPU("AMD Ryzen 7 5800X", "AM4", 8, 3.8);

        // GPU
        GPU gpu1 = new GPU("NVIDIA GTX 1650 4GB", 4);
        GPU gpu2 = new GPU("NVIDIA RTX 3050 8GB", 8);
        GPU gpu3 = new GPU("NVIDIA RTX 4060 8GB", 8);
        GPU gpu4 = new GPU("AMD RX 6600 8GB", 8);

        // RAM
        RAM ram1 = new RAM("Team Elite 8GB 3200", 8, 3200, "DDR4");
        RAM ram2 = new RAM("Corsair Vengeance 16GB 3200", 16, 3200, "DDR4");
        RAM ram3 = new RAM("G.Skill Ripjaws 32GB 3600", 32, 3600, "DDR4");

        // Storage
        Storage s1 = new Storage("SSD SATA 240GB", 240, "SSD");
        Storage s2 = new Storage("SSD NVMe 512GB", 512, "SSD");
        Storage s3 = new Storage("HDD 1TB 7200rpm", 1000, "HDD");

        // Power Supply
        PowerSupply psu1 = new PowerSupply("PSU 450W Bronze", 450);
        PowerSupply psu2 = new PowerSupply("PSU 550W Bronze", 550);
        PowerSupply psu3 = new PowerSupply("PSU 650W Gold", 650);

        // Motherboard
        Motherboard mb1 = new Motherboard("ASUS Prime B660M", "LGA1700", "B660", 64, "DDR4");
        Motherboard mb2 = new Motherboard("MSI PRO B760M", "LGA1700", "B760", 128, "DDR4");
        Motherboard mb3 = new Motherboard("ASUS TUF B550M", "AM4", "B550", 128, "DDR4");

        // Masukkan semua ke katalog
        katalogSupplier.add(cpu1);
        katalogSupplier.add(cpu2);
        katalogSupplier.add(cpu3);
        katalogSupplier.add(cpu4);

        katalogSupplier.add(gpu1);
        katalogSupplier.add(gpu2);
        katalogSupplier.add(gpu3);
        katalogSupplier.add(gpu4);

        katalogSupplier.add(ram1);
        katalogSupplier.add(ram2);
        katalogSupplier.add(ram3);

        katalogSupplier.add(s1);
        katalogSupplier.add(s2);
        katalogSupplier.add(s3);

        katalogSupplier.add(psu1);
        katalogSupplier.add(psu2);
        katalogSupplier.add(psu3);

        katalogSupplier.add(mb1);
        katalogSupplier.add(mb2);
        katalogSupplier.add(mb3);
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
            // Untuk setiap komponen di katalog, tambah stok 1 unit
            for (Komponen k : katalogSupplier) {
                inventaris.tambahStok(k, 1);
            }

            System.out.println("[Supplier] Menambah stok komponen…");
            inventaris.tampilkanStok();

            try {
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                running = false;
                Thread.currentThread().interrupt();
            }
        }
    }
}
