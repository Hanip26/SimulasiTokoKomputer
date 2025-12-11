# 🖥️ Simulasi Toko Komputer

## 📌 Deskripsi Project
**Simulasi Toko Komputer** adalah aplikasi berbasis Java GUI yang mensimulasikan proses kerja sebuah toko komputer, mulai dari konfigurasi PC rakitan, pengelolaan inventaris komponen, hingga manajemen pesanan pelanggan.  
Aplikasi ini dibangun menggunakan konsep Object-Oriented Programming (OOP) dan didukung multithreading untuk mensimulasikan proses toko yang berjalan secara paralel dan dinamis.

Project ini dibuat untuk memenuhi tugas pemrograman dengan ketentuan:
- Menggunakan GUI sebagai antarmuka utama
- Mengimplementasikan 2 materi sebelum UTS
- Mengimplementasikan 1 materi setelah UTS (non-GUI)

## 📂 Struktur Program
<pre>src/
└── main/java/
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

Bagian ini menjelaskan langkah langkah untuk menjalankan program **Simulasi Toko Komputer** mulai dari proses download hingga aplikasi berhasil dijalankan.

### 1. Persyaratan Sistem

Sebelum menjalankan project, pastikan hal berikut sudah terpenuhi:

- Java Development Kit (JDK) sudah terinstal  
  - Disarankan minimal **JDK 8** atau versi yang lebih baru.
- Sudah terpasang salah satu IDE berikut:
  - **NetBeans**
  - **IntelliJ IDEA**
  - **Visual Studio Code** dengan ekstensi Java
- Git (opsional, jika ingin clone langsung dari repository)

---

### 2. Mengunduh Source Code

Terdapat dua cara untuk mendapatkan source code project:

#### **a. Clone dari GitHub menggunakan Git**

```bash
git clone https://github.com/username/simulasi-toko-komputer.git
cd simulasi-toko-komputer
```
#### **b. Download sebagai ZIP**

1. Buka repository project pada GitHub.
2. Klik tombol **Code**.
3. Pilih **Download ZIP**.
4. Ekstrak file ZIP ke folder yang diinginkan.

---

### 3. Membuka Project di IDE

Project dapat dibuka menggunakan NetBeans, IntelliJ IDEA, atau Visual Studio Code.

### **a. NetBeans**

1. Buka **NetBeans**.
2. Pilih menu **File → Open Project**.
3. Arahkan ke folder project hasil clone atau ekstrak ZIP.
4. Pilih folder project, lalu klik **Open Project**.
5. Tunggu hingga NetBeans menyelesaikan proses scanning dan konfigurasi project.

### **b. IntelliJ IDEA**

1. Buka **IntelliJ IDEA**.
2. Pada tampilan awal, pilih **Open**.
3. Arahkan ke folder project.
4. Klik **OK**.
5. Jika muncul pilihan tipe project, pilih **Java Project** (atau **Maven/Gradle** jika project menggunakan build system tersebut).
6. Tunggu hingga proses indexing selesai dan IDE siap digunakan.

### **c. Visual Studio Code**

1. Buka **Visual Studio Code**.
2. Pilih menu **File → Open Folder**.
3. Pilih folder project, kemudian klik **Select Folder**.
4. Pastikan ekstensi **Extension Pack for Java** sudah terinstal.
5. VS Code akan mendeteksi struktur project Java dan menampilkan file yang dapat dijalankan.

---

### 4. Menjalankan Program Utama

Entry aplikasi berada pada file berikut:
<pre>
SimulasiTokoKomputer/src/main/java/ui/MainDashboard.java
</pre>

---

<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/bba783af-095a-4785-bf27-3c08d7d1331c" />

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

# 🗺️**Flowchart**

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

Tampilan GUI:

<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/88ef02d5-eec4-46d6-ba82-4c5dd003fea5" />

## 1️⃣ Bagian Header & Kontrol Simulasi (Bagian Atas)

### 🔘 Tombol **Start Simulation**
**Fungsi:**
- Menjalankan mesin simulasi toko komputer.
- Mengaktifkan proses multithreading, meliputi:
  - **Supplier Simulator** sebagai penambah stok komponen.
  - **Market Demand Simulator** sebagai pengurang stok (simulasi permintaan pasar).
  - **Order Checker / Simulator Engine** untuk mengecek status pesanan.
- Perubahan stok inventaris dan status pesanan terjadi secara otomatis.

📌 Saat tombol ini ditekan, sistem berjalan secara **real-time** tanpa intervensi pengguna.

---

### 🔴 Tombol **Stop Simulation**
**Fungsi:**
- Menghentikan seluruh proses simulasi yang sedang berjalan.
- Semua thread multithreading dihentikan secara aman.
- Digunakan untuk mengontrol jalannya simulasi agar tidak berjalan terus-menerus.

---

### 🔄 Tombol **Refresh**
**Fungsi:**
- Memperbarui tampilan data pada GUI secara manual.
- Digunakan untuk memastikan data inventaris dan pesanan selalu sinkron dengan kondisi sistem terbaru.

---

## 2️⃣ Panel Kiri – Konfigurasi PC (*PC Builder Pro*)

Panel ini berfungsi sebagai **modul perakitan PC virtual**, tempat pengguna menentukan spesifikasi PC yang akan dirakit.

### 📌 Dropdown Pilihan Komponen
Setiap dropdown berfungsi untuk memilih komponen PC sebagai berikut:

| Komponen       | Fungsi |
|---------------|--------|
| CPU           | Memilih prosesor |
| Motherboard   | Memilih motherboard sesuai socket dan chipset |
| RAM           | Menentukan kapasitas serta kecepatan RAM |
| GPU           | Memilih kartu grafis |
| Storage       | Memilih media penyimpanan |
| Power Supply  | Menentukan PSU beserta kapasitas daya |

✅ Seluruh pilihan komponen terhubung langsung dengan sistem **pengecekan kompatibilitas**.

---

### 🔘 Tombol **Preview**
**Fungsi:**
- Menampilkan ringkasan konfigurasi PC yang telah dipilih.
- Tidak memengaruhi data sistem (hanya bersifat pratinjau).

---

### 🔘 Tombol **Check**
**Fungsi:**
- Memeriksa kesesuaian konfigurasi PC, meliputi:
  - Kesesuaian socket antara CPU dan motherboard.
  - Batas maksimum RAM yang didukung motherboard.
  - Kecukupan daya PSU terhadap total kebutuhan sistem.
- Jika konfigurasi tidak valid, sistem akan menampilkan error atau exception.

---

### 🔘 Tombol **Order**
**Fungsi:**
- Membuat objek **Pesanan** baru berdasarkan konfigurasi PC.
- Status awal pesanan adalah **MENUNGGU_KOMPONEN**.
- Pesanan akan masuk ke sistem order dan menunggu stok komponen terpenuhi.

---

## 3️⃣ Panel Tengah – Ringkasan Konfigurasi PC

<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/d0b094ee-2f15-449c-9234-11bd79559e02" />

### 📄 Ringkasan PC Saat Ini
Panel ini menampilkan detail konfigurasi PC yang sedang dipilih, meliputi:
- CPU beserta spesifikasinya
- Motherboard dan socket
- RAM (kapasitas dan kecepatan)
- GPU
- Storage
- Power Supply
- Perkiraan kebutuhan daya minimum PSU

📌 Panel ini membantu pengguna melakukan validasi akhir sebelum membuat pesanan.

---

## 4️⃣ Panel Kanan Atas – Inventaris Komponen

### 📦 Tabel Inventaris
Tabel inventaris menampilkan data berikut:
- **Component Name**
- **Stock**
- **Status**

**Fungsi utama:**
- Menampilkan jumlah stok komponen secara real-time.
- Stok akan:
  - Bertambah melalui proses **Supplier Simulator**.
  - Berkurang akibat **Market Demand Simulator** atau pemrosesan pesanan.

✅ Panel ini terhubung langsung dengan kelas `Inventaris.java`.

---

## 5️⃣ Panel Kanan Bawah – Manajemen Pesanan

### 📑 Tab **Waiting**
Menampilkan:
- ID Pesanan
- Status: **MENUNGGU_KOMPONEN**

**Fungsi:**
- Menyimpan daftar pesanan yang belum dapat dirakit karena stok komponen belum mencukupi.

---

### ✅ Tab **Ready**
Menampilkan:
- ID Pesanan
- Status: **SIAP_DIRAKIT**

**Fungsi:**
- Menampilkan pesanan yang sudah memenuhi seluruh kebutuhan komponen.
- Pesanan berpindah ke tab ini secara otomatis setelah dicek oleh **SimulatorEngine**.

📌 Perpindahan status pesanan terjadi **tanpa input pengguna** (otomatis).

---

## 📘 Materi Sebelum UTS yang Diimplementasikan

### 1️⃣ Object-Oriented Programming (OOP)

Konsep **Object-Oriented Programming (OOP)** diterapkan secara nyata dan menyeluruh dalam perancangan struktur program. Implementasi OOP terlihat pada beberapa aspek berikut:

- **Class & Object**  
  Program menggunakan banyak class sebagai representasi objek di dunia nyata, seperti `PC`, `Komponen`, `CPU`, `GPU`, `RAM`, `Storage`, `PowerSupply`, dan `Pesanan`.  
  Setiap pesanan dan konfigurasi PC direpresentasikan sebagai objek yang memiliki atribut dan perilaku masing-masing.

- **Constructor & Method**  
  Setiap class memiliki constructor untuk inisialisasi data objek serta method untuk menjalankan operasi tertentu, seperti perhitungan daya, pengecekan kompatibilitas, pengurangan stok, dan pengelolaan status pesanan.

Penerapan OOP ini membuat program bersifat modular, mudah dikembangkan, dan sesuai dengan prinsip pemrograman berorientasi objek.

---

### 2️⃣ Array dan Collection

Materi **Array dan Collection** digunakan untuk menyimpan dan mengelola data secara dinamis selama simulasi berlangsung, antara lain:

- **ArrayList**  
  Digunakan untuk menyimpan daftar komponen PC dan daftar pesanan pelanggan.  
  Struktur ini memungkinkan penambahan dan penghapusan data secara fleksibel seiring berjalannya simulasi.

- **Map / HashMap**  
  Digunakan pada sistem inventaris untuk memetakan nama atau jenis komponen dengan jumlah stok yang tersedia.  
  Struktur ini mempermudah proses pencarian, pengurangan, dan penambahan stok komponen secara efisien.

Penggunaan collection memberikan kemudahan dalam pengolahan data dan mendukung perubahan data yang bersifat dinamis selama simulasi berjalan.

---

## 📕 Materi Setelah UTS (Non-GUI)

### 1️⃣ Enkapsulasi dan Inheritance

- **Inheritance (Pewarisan)**  
  Struktur komponen PC pada program ini menggunakan pewarisan dari class induk `Komponen`.  
  Class seperti `CPU`, `GPU`, `RAM`, `Storage`, `Motherboard`, dan `PowerSupply` merupakan turunan langsung dari `Komponen`, sehingga seluruh atribut dasar seperti nama komponen, kategori, dan deskripsi diwariskan secara
  otomatis.  
  Pendekatan ini mengurangi duplikasi kode, menjaga konsistensi struktur data, serta memudahkan penambahan jenis komponen baru tanpa perlu mengubah bagian logika inti program.

- **Enkapsulasi**  
  Setiap class komponen dan class pendukung lainnya menerapkan enkapsulasi melalui penggunaan atribut privat serta menyediakan getter dan setter untuk mengakses nilai atribut tersebut.  
  Dengan cara ini, integritas data tetap terjaga dan setiap perubahan terhadap objek harus dilakukan melalui method resmi yang sudah disediakan.

### 2️⃣ Polimorfisme

- **Polymorphism (Polimorfisme)**  
  Program memanfaatkan polimorfisme dengan memperlakukan seluruh jenis komponen sebagai objek bertipe `Komponen`.  
  Hal ini memungkinkan berbagai class turunan (`CPU`, `GPU`, `RAM`, dll.) disimpan dalam satu koleksi yang sama seperti `ArrayList<Komponen>`.  
  Ketika sistem melakukan operasi seperti pengecekan kompatibilitas, pengambilan stok, atau menampilkan informasi komponen, method yang dipanggil akan menyesuaikan perilaku sesuai tipe objek sebenarnya melalui mekanisme
  overriding.  
  Polimorfisme ini membuat sistem lebih fleksibel, mudah diperluas, dan tidak memerlukan logika percabangan untuk membedakan jenis komponen secara manual.
  
### 3️⃣ Multithreading

Materi **Multithreading** diimplementasikan sebagai bagian dari logika inti program dan berjalan secara independen dari GUI. Multithreading digunakan untuk mensimulasikan kondisi toko komputer yang aktif dan dinamis.

Implementasi multithreading meliputi:

- **SupplierSimulator**  
  Berjalan sebagai thread terpisah yang bertugas menambah stok komponen secara berkala, mensimulasikan proses suplai dari pemasok.

- **MarketDemandSimulator**  
  Mensimulasikan permintaan pasar dengan mengurangi stok komponen tertentu secara periodik, sehingga stok tidak bersifat statis.

- **SimulatorEngine**  
  Berperan sebagai pengelola utama yang menjalankan dan mengatur beberapa thread sekaligus, termasuk pengecekan pesanan yang menunggu komponen.  
  Engine ini bertugas mengubah status pesanan dari **MENUNGGU_KOMPONEN** menjadi **SIAP_DIRAKIT** ketika seluruh kebutuhan komponen telah terpenuhi.

---

## 📸 Screenshots Hasil Program
Screenshot hasil program meliputi:
- Tampilan Dashboard Utama
- Tampilan Konfigurasi PC
- Inventaris Komponen
- Pesanan Menunggu & Pesanan Siap Dirakit

📌 

<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/1d74b301-5db3-44e0-919c-7d910fc19b0c" />

## 👨‍💻 Pembagian Tugas Anggota Kelompok

Pembagian tugas dalam pengembangan program **Simulasi Toko Komputer Cerdas – PC Builder Pro** dilakukan secara terstruktur agar setiap anggota berkontribusi sesuai dengan peran masing-masing.

| NIM | Nama | Peran dan Kontribusi |
|----|------|----------------------|
| **L0324010** | **Daffa Arkhan Aditama** | Mengembangkan ide dan tema utama program, menyusun flowchart alur sistem, mengimplementasikan fitur pada package **simulation** dan **ui**, menyusun dan menata tampilan antarmuka (UI), melakukan revisi serta perbaikan bug atau error pada kode dan program, serta menambahkan dan merapikan isi dokumentasi pada file README.md. |
| **L0324016** | **Hanief Fahrel Wilianto** | Merancang dan mengimplementasikan algoritma inti program, mengembangkan class pada package **model** dan **order**, melakukan pengelolaan repository termasuk upload source code ke GitHub, serta menyusun struktur awal file README.md. |
| **L0324022** | **Muhammad Affan Nur Zhafariza** | Menyediakan perangkat awal sebagai dasar pengembangan program, merancang struktur kode sejak tahap awal, mengimplementasikan fitur pada package **builder** dan **inventory**, serta menambahkan dan melengkapi dokumentasi pada README.md. |
