# 🖥️ Simulasi Toko Komputer

## 📌 Deskripsi Project
**Simulasi Toko Komputer** adalah aplikasi berbasis Java GUI yang mensimulasikan proses kerja sebuah toko komputer, mulai dari konfigurasi PC rakitan, pengelolaan inventaris komponen, hingga manajemen pesanan pelanggan.  
Aplikasi ini dibangun menggunakan konsep Object-Oriented Programming (OOP) dan didukung multithreading untuk mensimulasikan proses toko yang berjalan secara paralel dan dinamis.

Project ini dibuat untuk memenuhi tugas pemrograman dengan ketentuan:
- Menggunakan **GUI** sebagai antarmuka utama
- Mengimplementasikan **2 materi sebelum UTS**
- Mengimplementasikan **1 materi setelah UTS (non-GUI)**

---

## ▶️ Cara Menjalankan Project
1. Pastikan **Java JDK** sudah terinstal.
2. Clone atau download repository ini.
3. Buka project menggunakan **NetBeans / IntelliJ IDEA / VS Code**.
4. Jalankan file utama:
5. Aplikasi akan berjalan dan menampilkan GUI Simulasi Toko Komputer.

<img width="1919" height="1014" alt="Screenshot 2025-12-06 223523" src="https://github.com/user-attachments/assets/83e7d418-adfe-4beb-b643-0c51164db472" />


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
