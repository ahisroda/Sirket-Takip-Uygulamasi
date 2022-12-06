package com.ismailtcinar.sirkettakip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ismailtcinar.sirkettakip.db.VeriTabani;
import com.ismailtcinar.sirkettakip.domain.KullaniciDomain;

public class KullaniciDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("" + "CREATE TABLE kullanici("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "adi VARCHAR(50), kullaniciadi VARCHAR(50), "
					+ "parola VARCHAR(50), hesapkurtarmano VARCHAR(50), yetki INTEGER)");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Kullanýcý tablosu zaten var");
		}
	}

	public static void ekle(KullaniciDomain eklenecekKullanici) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement(
					"INSERT INTO kullanici(adi, kullaniciadi, parola, hesapkurtarmano, yetki) VALUES (?,?,?,?, ?)");

			sorgu.setString(1, eklenecekKullanici.getAdSoyad());
			sorgu.setString(2, eklenecekKullanici.getKullaniciAdi());
			sorgu.setString(3, eklenecekKullanici.getParola());
			sorgu.setString(4, eklenecekKullanici.getHesapKurtarmaNo());
			sorgu.setInt(5, eklenecekKullanici.getYetki());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void sifreDegistir(KullaniciDomain degisecekSifre, String kullaniciAdi) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("UPDATE kullanici SET parola = ? WHERE kullaniciadi = ?");

			sorgu.setString(1, degisecekSifre.getParola());
			sorgu.setString(2, kullaniciAdi);

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean hesapGirisKontrolu(String kullaniciAdi, String parola) {
		boolean kontrol = false;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT * FROM kullanici WHERE kullaniciadi = ? AND parola =?");

			sorgu.setString(1, kullaniciAdi);
			sorgu.setString(2, parola);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				kontrol = true;
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kontrol;
	}

	public static boolean hesapMevcutMu(String kullaniciAdi) {
		boolean kontrol = false;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM kullanici WHERE kullaniciadi = ?");

			sorgu.setString(1, kullaniciAdi);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				kontrol = true;
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kontrol;
	}

	public static boolean sifreDegistirKontrol(String kullaniciAdi, String parola) {
		boolean kontrol = false;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT * FROM kullanici WHERE kullaniciadi = ? AND parola =?");

			sorgu.setString(1, kullaniciAdi);
			sorgu.setString(2, parola);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				kontrol = true;
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kontrol;
	}

	public static boolean sifremiUnuttumKontrolu(String kullaniciAdi, String hesapKurtarmaNo) {
		boolean kontrol = false;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT * FROM kullanici WHERE kullaniciadi = ? AND hesapkurtarmano =?");

			sorgu.setString(1, kullaniciAdi);
			sorgu.setString(2, hesapKurtarmaNo);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				kontrol = true;
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kontrol;
	}

	public static boolean hesapSilmeBilgiKontrol(String kullaniciAdi, String hesapkurtarmano, String parola) {

		boolean kontrol = false;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement(
					"SELECT * FROM kullanici WHERE kullaniciadi = ? AND hesapkurtarmano = ? AND parola = ?");

			sorgu.setString(1, kullaniciAdi);
			sorgu.setString(2, hesapkurtarmano);
			sorgu.setString(3, parola);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				kontrol = true;
			}
			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kontrol;

	}

	public static void hesapSil(String kullaniciAdi, String hesapkurtarmano) {

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("DELETE FROM kullanici WHERE kullaniciadi = ? AND hesapkurtarmano = ?");

			sorgu.setString(1, kullaniciAdi);
			sorgu.setString(2, hesapkurtarmano);

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean zatenHesapVarMi() {
		boolean kontrol = false;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM kullanici WHERE yetki = 1");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				kontrol = true;
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kontrol;
	}

	public static KullaniciDomain idGetir(String kullaniciAdi) {
		KullaniciDomain gelecekId = new KullaniciDomain();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM kullanici WHERE kullaniciadi = ?");

			sorgu.setString(1, kullaniciAdi);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				gelecekId.setId(rs.getInt("id"));

			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gelecekId;
	}

	public static KullaniciDomain kullaniciAdiGetir(int id) {
		KullaniciDomain gelecekAd = new KullaniciDomain();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM kullanici WHERE id = ?");

			sorgu.setInt(1, id);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				gelecekAd.setId(rs.getInt("id"));
				gelecekAd.setAdSoyad(rs.getString("adi"));

			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gelecekAd;
	}

}
