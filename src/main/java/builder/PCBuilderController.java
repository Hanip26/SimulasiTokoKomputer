/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

import model.CPU;
import model.GPU;
import model.Motherboard;
import model.PC;
import model.PowerSupply;
import model.RAM;
import model.Storage;

public class PCBuilderController {

    private PC pc;

    public PCBuilderController() {
        this.pc = new PC();
    }
    
    public void pasangCPU(CPU cpu) {
        this.pc.tambahKomponen(cpu);
    }

    public void pasangMotherboard(Motherboard mb) {
        this.pc.tambahKomponen(mb);
    }

    public void pasangRAM(RAM ram) {
        this.pc.tambahKomponen(ram);
    }

    public void pasangGPU(GPU gpu) {
        this.pc.tambahKomponen(gpu);
    }

    public void pasangStorage(Storage storage) {
        this.pc.tambahKomponen(storage);
    }

    public void pasangPSU(PowerSupply psu) {
        this.pc.tambahKomponen(psu);
    }

    public void cekKompatibilitas() throws IncompatibleComponentException {
        KompatibilitasChecker.cekKompatibilitas(this.pc);
    }

    public PC getPC() {
        return this.pc;
    }

    public void resetBuild() {
        this.pc = new PC();
    }
}

