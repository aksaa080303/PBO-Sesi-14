package service;

import model.Transaksi;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiService {

    // Menambahkan transaksi ke database
    public void tambahTransaksi(Transaksi t) {
        String sql = "INSERT INTO transaksi (id_pelanggan, id_hewan, jumlah_beli, total_harga, tanggal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdPelanggan());
            stmt.setInt(2, t.getIdHewan());
            stmt.setInt(3, t.getJumlahBeli());
            stmt.setDouble(4, t.getTotalHarga());
            stmt.setDate(5, t.getTanggal());

            stmt.executeUpdate();
            System.out.println("Transaksi berhasil dicatat!");

        } catch (SQLException e) {
            System.out.println("Gagal mencatat transaksi: " + e.getMessage());
        }
    }

    // Menampilkan riwayat transaksi
    public List<Transaksi> getSemuaTransaksi() {
        List<Transaksi> daftar = new ArrayList<>();
        String sql = "SELECT * FROM transaksi";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Transaksi t = new Transaksi(
                        rs.getInt("id_transaksi"),
                        rs.getInt("id_pelanggan"),
                        rs.getInt("id_hewan"),
                        rs.getInt("jumlah_beli"),
                        rs.getDouble("total_harga"),
                        rs.getDate("tanggal")
                );
                daftar.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil riwayat transaksi: " + e.getMessage());
        }

        return daftar;
    }
}
