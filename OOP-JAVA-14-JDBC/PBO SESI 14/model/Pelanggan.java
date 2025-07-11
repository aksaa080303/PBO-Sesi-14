package model;

public class Pelanggan {
    private int id;
    private String nama;
    private String noHp;

    public Pelanggan(int id, String nama, String noHp) {
        this.id = id;
        this.nama = nama;
        this.noHp = noHp;
    }

    public Pelanggan(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getNoHp() { return noHp; }

    public void setId(int id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setNoHp(String noHp) { this.noHp = noHp; }
}
