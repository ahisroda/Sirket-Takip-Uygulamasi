package com.ismailtcinar.sirkettakip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ismailtcinar.sirkettakip.db.VeriTabani;
import com.ismailtcinar.sirkettakip.domain.AktifKullaniciDomain;

public class AktifKullaniciDao {

	public static void tabloOlustur() {

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE aktifkullanici("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "aktifkullaniciid INTEGER)");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			System.out.println("Aktif kullanýcý tablosu zaten var");
		}

	}

	public static void ekle(AktifKullaniciDomain eklenecekAktifKullanici) {

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("INSERT INTO aktifkullanici(aktifkullaniciid) VALUES (?)");

			sorgu.setInt(1, eklenecekAktifKullanici.getAktifKullaniciId());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void sil() {

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM aktifkullanici");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static AktifKullaniciDomain aktifKullaniciIdGetir() {
		AktifKullaniciDomain gelenAktifKullanici = new AktifKullaniciDomain();
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM aktifkullanici");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				gelenAktifKullanici.setId(rs.getInt("id"));
				gelenAktifKullanici.setAktifKullaniciId(rs.getInt("aktifkullaniciid"));
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gelenAktifKullanici;
	}
}
