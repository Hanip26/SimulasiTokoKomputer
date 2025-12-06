/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulation;

import inventory.Inventaris;
import order.OrderManager;

public class SimulatorEngine {

    private final Inventaris inventaris;
    private final OrderManager orderManager;

    private SupplierSimulator supplierSimulator;
    private MarketDemandSimulator marketDemandSimulator;

    private Thread supplierThread;
    private Thread demandThread;
    private Thread orderCheckerThread;

    private volatile boolean running = false;

    // interval pengecekan pesanan (ms)
    private long orderCheckIntervalMs = 3000;

    public SimulatorEngine(Inventaris inventaris, OrderManager orderManager) {
        this.inventaris = inventaris;
        this.orderManager = orderManager;
    }

    public void setOrderCheckIntervalMs(long interval) {
        this.orderCheckIntervalMs = interval;
    }

    public void start() {
        if (running) return;
        running = true;

        // Inisialisasi simulator
        supplierSimulator = new SupplierSimulator(inventaris);
        marketDemandSimulator = new MarketDemandSimulator(inventaris);

        supplierThread = new Thread(supplierSimulator, "SupplierSimulator-Thread");
        demandThread = new Thread(marketDemandSimulator, "MarketDemandSimulator-Thread");

        // Thread untuk cek semua pesanan secara periodik
        orderCheckerThread = new Thread(() -> {
            while (running) {
                orderManager.prosesCekPesanan();
                try {
                    Thread.sleep(orderCheckIntervalMs);
                } catch (InterruptedException e) {
                    running = false;
                    Thread.currentThread().interrupt();
                }
            }
        }, "OrderChecker-Thread");

        supplierThread.start();
        demandThread.start();
        orderCheckerThread.start();

        System.out.println("[SimulationEngine] Simulasi dimulai.");
    }

    public void stop() {
        if (!running) return;
        running = false;

        if (supplierSimulator != null) supplierSimulator.stop();
        if (marketDemandSimulator != null) marketDemandSimulator.stop();

        if (supplierThread != null) supplierThread.interrupt();
        if (demandThread != null) demandThread.interrupt();
        if (orderCheckerThread != null) orderCheckerThread.interrupt();

        System.out.println("[SimulationEngine] Simulasi dihentikan.");
    }
}