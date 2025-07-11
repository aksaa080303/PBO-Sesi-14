package model;

import java.sql.Date;

public class Transaksi {
    private int id;
    private int idPelanggan;
    private int idHewan;
    private int jumlahBeli;
    private double totalHarga;
    private Date tanggal;

    public Transaksi(int idPelanggan, int idHewan, int jumlahBeli, double totalHarga, Date tanggal) {
        this.idPelanggan = idPelanggan;
        this.idHewan = idHewan;
        this.jumlahBeli = jumlahBeli;
        this.totalHarga = totalHarga;
        this.tanggal = tanggal;
    }

    public Transaksi(int id, int idPelanggan, int idHewan, int jumlahBeli, double totalHarga, Date tanggal) {
        this.id = id;
        this.idPelanggan = idPelanggan;
        this.idHewan = idHewan;
        this.jumlahBeli = jumlahBeli;
        this.totalHarga = totalHarga;
        this.tanggal = tanggal;
    }

    public int getId() { return id; }
    public int getIdPelanggan() { return idPelanggan; }
    public int getIdHewan() { return idHewan; }
    public int getJumlahBeli() { return jumlahBeli; }
    public double getTotalHarga() { return totalHarga; }
    public Date getTanggal() { return tanggal; }

    public void setId(int id) { this.id = id; }
    public void setIdPelanggan(int idPelanggan) { this.idPelanggan = idPelanggan; }
    public void setIdHewan(int idHewan) { this.idHewan = idHewan; }
    public void setJumlahBeli(int jumlahBeli) { this.jumlahBeli = jumlahBeli; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }
}
