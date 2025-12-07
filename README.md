# 🖥️ Simulasi Toko Komputer

## 📌 Deskripsi Project
**Simulasi Toko Komputer** adalah aplikasi berbasis Java GUI yang mensimulasikan proses kerja sebuah toko komputer, mulai dari konfigurasi PC rakitan, pengelolaan inventaris komponen, hingga manajemen pesanan pelanggan.  
Aplikasi ini dibangun menggunakan konsep Object-Oriented Programming (OOP) dan didukung multithreading untuk mensimulasikan proses toko yang berjalan secara paralel dan dinamis.

Project ini dibuat untuk memenuhi tugas pemrograman dengan ketentuan:
- Menggunakan **GUI** sebagai antarmuka utama
- Mengimplementasikan **2 materi sebelum UTS**
- Mengimplementasikan **1 materi setelah UTS (non-GUI)**

## 📂 Struktur Program
<pre>src/
└── simulasi/tokokomputer/
    ├── model/
    │   ├── Komponen.java
    │   ├── CPU.java
    │   ├── GPU.java
    │   ├── RAM.java
    │   ├── Storage.java
    │   ├── PowerSupply.java
    │   ├── Motherboard.java
    │   ├── PC.java
    │   └── Pesanan.java
    │
    ├── builder/
    │   ├── PCBuilderController.java
    │   ├── KompatibilitasChecker.java
    │   └── IncompatibleComponentException.java
    │
    ├── inventory/
    │   ├── Inventaris.java
    │   └── StokTidakCukupException.java
    │
    ├── order/
    │   ├── OrderManager.java
    │   └── StatusPesanan.java
    │
    ├── simulation/
    │   ├── SimulatorEngine.java
    │   ├── SupplierSimulator.java
    │   └── MarketDemandSimulator.java
    │
    └── ui/
        ├── MainDashboard.java
        ├── PCConfiguratorPanel.java
        ├── TabelInventarisPanel.java
        ├── TabelPesananMenungguPanel.java
        └── TabelPesananSiapRakitPanel.java
</pre>

## 📁 Penjelasan Singkat Package

- **package `model`**  
  Berisi class–class data utama, seperti komponen PC (`CPU`, `GPU`, `RAM`, dll.), `PC`, dan `Pesanan`. Package ini fokus pada representasi objek di dalam sistem.

- **package `builder`**  
  Mengatur logika perakitan PC dan pengecekan kompatibilitas komponen. Di sini terdapat controller perakitan dan exception jika konfigurasi tidak cocok.

- **package `inventory`**  
  Mengelola inventaris komponen toko: data stok, penambahan/pengurangan stok, serta pengecekan ketersediaan komponen.

- **package `order`**  
  Mengurus manajemen pesanan, mulai dari pembuatan pesanan, daftar pesanan, hingga perubahan status pesanan (menunggu komponen, siap dirakit, selesai).

- **package `simulation`**  
  Menangani simulasi proses toko yang berjalan otomatis menggunakan multithreading, seperti simulasi pemasok dan permintaan pasar.

- **package `ui`**  
  Berisi seluruh tampilan **GUI** aplikasi, mulai dari `MainDashboard` hingga panel-panel tabel dan konfigurasi PC. Package ini menjadi antarmuka utama yang digunakan pengguna.


## ▶️ Cara Menjalankan Project
1. Pastikan **Java JDK** sudah terinstal.
2. Clone atau download repository ini.
3. Buka project menggunakan **NetBeans / IntelliJ IDEA / VS Code**.
4. Jalankan file utama:
5. Aplikasi akan berjalan dan menampilkan GUI Simulasi Toko Komputer.

<img width="1919" height="1014" alt="Screenshot 2025-12-06 223523" src="https://github.com/user-attachments/assets/83e7d418-adfe-4beb-b643-0c51164db472" />

## ⚙️ Fitur Utama Program

1. 🧩 **Konfigurasi PC Rakitan**
   - Memilih komponen PC (CPU, Motherboard, RAM, GPU, Storage, PSU).
   - Menampilkan ringkasan konfigurasi rakitan.
   - Mengecek kompatibilitas komponen sebelum dibuat pesanan.

2. 📦 **Manajemen Inventaris Komponen**
   - Menyimpan dan menampilkan stok komponen yang tersedia di toko.
   - Stok otomatis berkurang ketika pesanan diproses.
   - Stok dapat bertambah melalui simulasi pemasok (supplier).

3. 📑 **Manajemen Pesanan Pelanggan**
   - Membuat pesanan berdasarkan konfigurasi PC yang dipilih.
   - Mengelola status pesanan:
     - `MENUNGGU_KOMPONEN`
     - `SIAP_DIRAKIT`
     - `SELESAI` (opsional, tergantung implementasi)
   - Menampilkan daftar pesanan menunggu dan siap dirakit dalam bentuk tabel.

4. 🔄 **Simulasi Dinamis dengan Multithreading**
   - **SupplierSimulator**: menambah stok komponen secara berkala.
   - **MarketDemandSimulator**: mengurangi stok komponen sebagai simulasi permintaan pasar.
   - **OrderChecker / SimulatorEngine**: mengecek pesanan secara periodik dan mengubah status jika stok mencukupi.

5. 🖥️ **Dashboard GUI Interaktif**
   - Tampilan terpusat untuk konfigurasi PC, inventaris, dan pesanan.
   - Tombol **Start Simulation** dan **Stop Simulation** untuk mengontrol jalannya simulasi.
   - Fitur **refresh / auto-refresh** agar tampilan tabel selalu update.

6. 🔐 **Pengecekan Error & Validasi**
   - Pengecekan kompatibilitas komponen sebelum pesanan dibuat.
   - Penanganan kasus stok tidak mencukupi saat pesanan diproses (menggunakan exception / logika validasi).

**Flowchart TD**
<img width="3695" height="4723" alt="flowchart PBO" src="https://github.com/user-attachments/assets/91dfa8bd-df37-4760-bba0-84193965e083" />
    
## 🔄 Alur Singkat Program

1. Program dijalankan dan melakukan inisialisasi data, meliputi inventaris komponen, manajemen pesanan, serta tampilan GUI utama.
2. User mengontrol jalannya simulasi melalui tombol **Start Simulation** dan **Stop Simulation**.
3. Saat simulasi berjalan, sistem menjalankan beberapa proses paralel:
   - Pemasok menambah stok komponen secara berkala.
   - Permintaan pasar mengurangi stok komponen tertentu.
   - Sistem mengecek ketersediaan stok untuk setiap pesanan.
4. User melakukan konfigurasi PC dengan memilih komponen yang diinginkan.
5. Sistem melakukan pengecekan kompatibilitas komponen sebelum pesanan dibuat.
6. Jika konfigurasi valid, user dapat membuat pesanan dengan status **MENUNGGU_KOMPONEN**.
7. Ketika stok komponen mencukupi, sistem otomatis mengubah status pesanan menjadi **SIAP_DIRAKIT** dan memperbarui tampilan tabel di GUI.
8. Seluruh perubahan data ditampilkan secara real-time pada dashboard aplikasi.

## 🎨 Penjelasan GUI yang Digunakan
GUI dibuat menggunakan **Java Swing**, dengan komponen utama:
- `JFrame` sebagai window utama
- `JPanel` sebagai pembagi layout
- `JTable` untuk menampilkan data inventaris dan pesanan
- `JButton` untuk kontrol simulasi (Start, Stop, Refresh)
- `JLabel`, `JList`, dan `JScrollPane` sebagai elemen pendukung

Tampilan GUI terdiri dari:
- **Panel Konfigurasi PC**
- **Panel Inventaris Komponen**
- **Panel Pesanan Menunggu & Pesanan Siap Dirakit**
- **Panel Kontrol Simulasi**

GUI diperbarui otomatis menggunakan **Timer** sehingga perubahan data ditampilkan secara real-time.

---

## 📘 Materi Sebelum UTS yang Diimplementasikan
### 1️⃣ Object-Oriented Programming (OOP)
Penerapan OOP meliputi:
- **Class & Object** → `PC`, `Komponen`, `CPU`, `GPU`, `Pesanan`
- **Inheritance** → komponen PC merupakan turunan dari class `Komponen`
- **Polymorphism** → berbagai jenis komponen diproses dalam satu koleksi
- **Constructor & Method** → inisialisasi dan operasi objek

### 2️⃣ Array dan Collection
- `ArrayList` digunakan untuk menyimpan daftar komponen dan pesanan
- `Map / HashMap` digunakan untuk pengelolaan inventaris dan stok
- Collection mempermudah pengolahan data secara dinamis

---

## 📕 Materi Setelah UTS (Non-GUI)
### ✅ Multithreading
Multithreading digunakan untuk simulasi proses toko secara paralel, meliputi:
- **SupplierSimulator** → menambah stok komponen secara berkala
- **MarketDemandSimulator** → mensimulasikan permintaan pasar
- **SimulatorEngine** → menjalankan dan mengatur beberapa thread sekaligus

Materi ini dijalankan sebagai proses logika program tanpa bergantung langsung pada GUI.

---

## 📸 Screenshots Hasil Program
Screenshot hasil program meliputi:
- Tampilan Dashboard Utama
- Tampilan Konfigurasi PC
- Inventaris Komponen
- Pesanan Menunggu & Pesanan Siap Dirakit

📌 
<img width="1919" height="1016" alt="Screenshot 2025-12-06 224108" src="https://github.com/user-attachments/assets/e27feb26-265b-408d-8f96-8b70194dd413" />
