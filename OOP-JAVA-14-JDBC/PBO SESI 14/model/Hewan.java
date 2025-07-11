package model;

public class Hewan {
    private int id;
    private String jenis;
    private String ras;
    private int umurBulan;
    private double harga;
    private int stok;

    public Hewan(int id, String jenis, String ras, int umurBulan, double harga, int stok) {
        this.id = id;
        this.jenis = jenis;
        this.ras = ras;
        this.umurBulan = umurBulan;
        this.harga = harga;
        this.stok = stok;
    }

    public Hewan(String jenis, String ras, int umurBulan, double harga, int stok) {
        this.jenis = jenis;
        this.ras = ras;
        this.umurBulan = umurBulan;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId() { return id; }
    public String getJenis() { return jenis; }
    public String getRas() { return ras; }
    public int getUmurBulan() { return umurBulan; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }

    public void setId(int id) { this.id = id; }
    public void setJenis(String jenis) { this.jenis = jenis; }
    public void setRas(String ras) { this.ras = ras; }
    public void setUmurBulan(int umurBulan) { this.umurBulan = umurBulan; }
    public void setHarga(double harga) { this.harga = harga; }
    public void setStok(int stok) { this.stok = stok; }
}
