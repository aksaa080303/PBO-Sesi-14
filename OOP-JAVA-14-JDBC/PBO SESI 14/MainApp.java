import model.Hewan;
import model.Pelanggan;
import model.Transaksi;
import service.HewanService;
import service.PelangganService;
import service.TransaksiService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PelangganService pelangganService = new PelangganService();
    private static final HewanService hewanService = new HewanService();
    private static final TransaksiService transaksiService = new TransaksiService();

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("=== MENU UTAMA ===");
            System.out.println("1. Kelola Data Pelanggan");
            System.out.println("2. Kelola Data Hewan");
            System.out.println("3. Transaksi Penjualan");
            System.out.println("4. Riwayat Transaksi");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = Integer.parseInt(scanner.nextLine());

            switch (pilihan) {
                case 1 -> menuPelanggan();
                case 2 -> menuHewan();
                case 3 -> menuTransaksi();
                case 4 -> tampilkanRiwayatTransaksi();
                case 0 -> System.out.println("Keluar dari program...");
                default -> System.out.println("Pilihan tidak valid.");
            }

            System.out.println();
        } while (pilihan != 0);
    }

    private static void menuPelanggan() {
        System.out.println("=== MENU PELANGGAN ===");
        System.out.println("1. Tambah Pelanggan");
        System.out.println("2. Lihat Semua Pelanggan");
        System.out.println("3. Hapus Pelanggan");
        System.out.print("Pilih menu: ");
        int pilih = Integer.parseInt(scanner.nextLine());

        switch (pilih) {
            case 1 -> {
                System.out.print("Nama: ");
                String nama = scanner.nextLine();
                System.out.print("No HP: ");
                String noHp = scanner.nextLine();
                Pelanggan p = new Pelanggan(nama, noHp);
                pelangganService.tambahPelanggan(p);
            }
            case 2 -> {
                List<Pelanggan> daftar = pelangganService.getSemuaPelanggan();
                for (Pelanggan p : daftar) {
                    System.out.println(p.getId() + " - " + p.getNama() + " (" + p.getNoHp() + ")");
                }
            }
            case 3 -> {
                System.out.print("Masukkan ID pelanggan yang akan dihapus: ");
                int id = Integer.parseInt(scanner.nextLine());
                pelangganService.hapusPelanggan(id);
            }
            default -> System.out.println("Pilihan tidak valid.");
        }
    }

    private static void menuHewan() {
        System.out.println("=== MENU HEWAN ===");
        System.out.println("1. Tambah Hewan");
        System.out.println("2. Lihat Semua Hewan");
        System.out.print("Pilih menu: ");
        int pilih = Integer.parseInt(scanner.nextLine());

        switch (pilih) {
            case 1 -> {
                System.out.print("Jenis: ");
                String jenis = scanner.nextLine();
                System.out.print("Ras: ");
                String ras = scanner.nextLine();
                System.out.print("Umur (bulan): ");
                int umur = Integer.parseInt(scanner.nextLine());
                System.out.print("Harga: ");
                double harga = Double.parseDouble(scanner.nextLine());
                System.out.print("Stok: ");
                int stok = Integer.parseInt(scanner.nextLine());

                Hewan h = new Hewan(jenis, ras, umur, harga, stok);
                hewanService.tambahHewan(h);
            }
            case 2 -> {
                List<Hewan> daftar = hewanService.getSemuaHewan();
                for (Hewan h : daftar) {
                    System.out.println(h.getId() + " - " + h.getJenis() + " " + h.getRas() +
                            ", umur " + h.getUmurBulan() + " bln, harga: " + h.getHarga() + ", stok: " + h.getStok());
                }
            }
            default -> System.out.println("Pilihan tidak valid.");
        }
    }

    private static void menuTransaksi() {
        System.out.println("=== TRANSAKSI PENJUALAN ===");

        // Pilih pelanggan
        System.out.println("Daftar Pelanggan:");
        List<Pelanggan> pelangganList = pelangganService.getSemuaPelanggan();
        for (Pelanggan p : pelangganList) {
            System.out.println(p.getId() + " - " + p.getNama());
        }
        System.out.print("Masukkan ID Pelanggan: ");
        int idPelanggan = Integer.parseInt(scanner.nextLine());

        // Pilih hewan
        System.out.println("Daftar Hewan:");
        List<Hewan> hewanList = hewanService.getSemuaHewan();
        for (Hewan h : hewanList) {
            System.out.println(h.getId() + " - " + h.getJenis() + " " + h.getRas() +
                    ", harga: " + h.getHarga() + ", stok: " + h.getStok());
        }
        System.out.print("Masukkan ID Hewan yang dibeli: ");
        int idHewan = Integer.parseInt(scanner.nextLine());

        Hewan hewan = hewanService.getById(idHewan);
        if (hewan == null) {
            System.out.println("Hewan tidak ditemukan.");
            return;
        }

        System.out.print("Jumlah yang dibeli: ");
        int jumlah = Integer.parseInt(scanner.nextLine());

        if (jumlah > hewan.getStok()) {
            System.out.println("Stok tidak mencukupi!");
            return;
        }

        double totalHarga = hewan.getHarga() * jumlah;
        Date tanggal = new Date(System.currentTimeMillis());

        Transaksi transaksi = new Transaksi(idPelanggan, idHewan, jumlah, totalHarga, tanggal);
        transaksiService.tambahTransaksi(transaksi);
        hewanService.kurangiStok(idHewan, jumlah);
    }

    private static void tampilkanRiwayatTransaksi() {
        System.out.println("=== RIWAYAT TRANSAKSI ===");
        List<Transaksi> transaksiList = transaksiService.getSemuaTransaksi();

        for (Transaksi t : transaksiList) {
            System.out.println("ID: " + t.getId() +
                    " | Pelanggan ID: " + t.getIdPelanggan() +
                    " | Hewan ID: " + t.getIdHewan() +
                    " | Jumlah: " + t.getJumlahBeli() +
                    " | Total: " + t.getTotalHarga() +
                    " | Tanggal: " + t.getTanggal());
        }
    }
}
