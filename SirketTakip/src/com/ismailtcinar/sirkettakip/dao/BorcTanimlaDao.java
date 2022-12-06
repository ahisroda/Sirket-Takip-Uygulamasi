package com.ismailtcinar.sirkettakip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ismailtcinar.sirkettakip.db.VeriTabani;
import com.ismailtcinar.sirkettakip.domain.BorcTanimlaDomain;

public class BorcTanimlaDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE borctanimla("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "borc INTEGER, " + "kime VARCHAR(50), " + "tarih VARCHAR(50), durum INTEGER)");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Borç tanýmla tablosu zaten var");
		}
	}

	public static void ekle(BorcTanimlaDomain eklenecekBorc) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("INSERT INTO borctanimla(borc, kime, tarih, durum) VALUES (?,?,?,?)");

			sorgu.setInt(1, eklenecekBorc.getBorc());
			sorgu.setString(2, eklenecekBorc.getKime());
			sorgu.setString(3, eklenecekBorc.getTarih());
			sorgu.setInt(4, eklenecekBorc.getDurum());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sil(BorcTanimlaDomain silinecekBorc) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM borctanimla WHERE id = ?");

			sorgu.setInt(1, silinecekBorc.getId());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<BorcTanimlaDomain> listele() {

		ArrayList<BorcTanimlaDomain> liste = new ArrayList<BorcTanimlaDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM borctanimla ORDER BY tarih DESC");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				BorcTanimlaDomain siradakiDomain = new BorcTanimlaDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setBorc(rs.getInt("borc"));
				siradakiDomain.setKime(rs.getString("kime"));
				siradakiDomain.setTarih(rs.getString("tarih"));
				siradakiDomain.setDurum(rs.getInt("durum"));

				liste.add(siradakiDomain);
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;
	}

	public static int borcGetir(int id) {

		int borc = 0;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT SUM(borc) FROM borctanimla WHERE id = ?");

			sorgu.setInt(1, id);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				borc = rs.getInt(1);
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return borc;
	}

	public static void borcuGuncelle(int guncelBorc, int id) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("UPDATE borctanimla SET borc = ? WHERE id = ? ");

			sorgu.setInt(1, guncelBorc);
			sorgu.setInt(2, id);

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BorcTanimlaDomain tanimlanmisBorcIdGetir(int id) {

		BorcTanimlaDomain idGetir = new BorcTanimlaDomain();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM borctanimla WHERE id = ?");

			sorgu.setInt(1, id);

			ResultSet rs = sorgu.executeQuery();

			idGetir.setId(rs.getInt("id"));
			idGetir.setKime(rs.getString("kime"));

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return idGetir;
	}

	public static void durumuGuncelle(int guncelDurum, int id) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("UPDATE borctanimla SET durum = ? WHERE id = ? ");

			sorgu.setInt(1, guncelDurum);
			sorgu.setInt(2, id);

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BorcTanimlaDomain durumGetir(int id) {

		BorcTanimlaDomain gelenDurum = new BorcTanimlaDomain();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM borctanimla WHERE id = ?");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				BorcTanimlaDomain siradakiDomain = new BorcTanimlaDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setBorc(rs.getInt("borc"));
				siradakiDomain.setKime(rs.getString("kime"));
				siradakiDomain.setTarih(rs.getString("tarih"));
				siradakiDomain.setDurum(rs.getInt("durum"));

			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gelenDurum;
	}

	public static void durumaGoreSil() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM borctanimla WHERE durum = 2");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<BorcTanimlaDomain> bul(String kime) {

		ArrayList<BorcTanimlaDomain> borcListesi = new ArrayList<BorcTanimlaDomain>();

		Connection baglantiConnection = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglantiConnection
					.prepareStatement("SELECT * FROM borctanimla WHERE kime LIKE ? ORDER BY tarih DESC");
			sorgu.setString(1, "%" + kime + "%");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				BorcTanimlaDomain siradakiDomain = new BorcTanimlaDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setKime(rs.getString("kime"));
				siradakiDomain.setBorc(rs.getInt("borc"));
				siradakiDomain.setTarih(rs.getString("tarih"));
				siradakiDomain.setDurum(rs.getInt("durum"));

				borcListesi.add(siradakiDomain);
			}

			baglantiConnection.close();
			sorgu.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borcListesi;
	}

	public static void borcTanimlaTablosunuSil() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM borctanimla");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
