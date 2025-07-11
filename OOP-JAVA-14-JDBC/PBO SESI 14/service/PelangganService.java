package service;

import model.Pelanggan;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganService {

    // Menambahkan pelanggan ke database
    public void tambahPelanggan(Pelanggan p) {
        String sql = "INSERT INTO pelanggan (nama, no_hp) VALUES (?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getNoHp());
            stmt.executeUpdate();
            System.out.println("Pelanggan berhasil ditambahkan!");

        } catch (SQLException e) {
            System.out.println("Gagal menambah pelanggan: " + e.getMessage());
        }
    }

    // Menampilkan semua pelanggan
    public List<Pelanggan> getSemuaPelanggan() {
        List<Pelanggan> daftar = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pelanggan p = new Pelanggan(
                        rs.getInt("id_pelanggan"),
                        rs.getString("nama"),
                        rs.getString("no_hp")
                );
                daftar.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pelanggan: " + e.getMessage());
        }

        return daftar;
    }

    // Menghapus pelanggan berdasarkan ID
    public void hapusPelanggan(int id) {
        String sql = "DELETE FROM pelanggan WHERE id_pelanggan = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                System.out.println("Pelanggan berhasil dihapus.");
            } else {
                System.out.println("ID pelanggan tidak ditemukan.");
            }

        } catch (SQLException e) {
            System.out.println("Gagal menghapus pelanggan: " + e.getMessage());
        }
    }
}
