package service;

import model.Hewan;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HewanService {

    // Menambahkan data hewan ke database
    public void tambahHewan(Hewan h) {
        String sql = "INSERT INTO hewan (jenis, ras, umur_bulan, harga, stok) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, h.getJenis());
            stmt.setString(2, h.getRas());
            stmt.setInt(3, h.getUmurBulan());
            stmt.setDouble(4, h.getHarga());
            stmt.setInt(5, h.getStok());

            stmt.executeUpdate();
            System.out.println("Hewan berhasil ditambahkan!");

        } catch (SQLException e) {
            System.out.println("Gagal menambah hewan: " + e.getMessage());
        }
    }

    // Menampilkan semua hewan
    public List<Hewan> getSemuaHewan() {
        List<Hewan> daftar = new ArrayList<>();
        String sql = "SELECT * FROM hewan";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Hewan h = new Hewan(
                        rs.getInt("id_hewan"),
                        rs.getString("jenis"),
                        rs.getString("ras"),
                        rs.getInt("umur_bulan"),
                        rs.getDouble("harga"),
                        rs.getInt("stok")
                );
                daftar.add(h);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data hewan: " + e.getMessage());
        }

        return daftar;
    }

    // Mengupdate stok hewan setelah transaksi
    public void kurangiStok(int idHewan, int jumlah) {
        String sql = "UPDATE hewan SET stok = stok - ? WHERE id_hewan = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jumlah);
            stmt.setInt(2, idHewan);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Gagal mengurangi stok: " + e.getMessage());
        }
    }

    // Mendapatkan satu data hewan berdasarkan ID
    public Hewan getById(int id) {
        String sql = "SELECT * FROM hewan WHERE id_hewan = ?";
        Hewan h = null;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                h = new Hewan(
                        rs.getInt("id_hewan"),
                        rs.getString("jenis"),
                        rs.getString("ras"),
                        rs.getInt("umur_bulan"),
                        rs.getDouble("harga"),
                        rs.getInt("stok")
                );
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data hewan berdasarkan ID: " + e.getMessage());
        }

        return h;
    }
}
