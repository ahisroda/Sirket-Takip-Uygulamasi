package com.ismailtcinar.sirkettakip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ismailtcinar.sirkettakip.db.VeriTabani;
import com.ismailtcinar.sirkettakip.domain.StokDomain;

public class StokDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE stok("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "malzemeid INTEGER, adet INTEGER, " + "tarih VARCHAR(50), " + "aciklama VARCHAR(50))");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Stok tablosu zaten var");
		}
	}

	public static void ekle(StokDomain eklenecekStok) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("INSERT INTO stok(malzemeid, adet, tarih, aciklama) VALUES (?,?,?,?)");

			sorgu.setInt(1, eklenecekStok.getMalzemeId());
			sorgu.setInt(2, eklenecekStok.getAdet());
			sorgu.setString(3, eklenecekStok.getTarih());
			sorgu.setString(4, eklenecekStok.getAciklama());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static ArrayList<StokDomain> listele() {
		ArrayList<StokDomain> liste = new ArrayList<StokDomain>();
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM stok ORDER BY tarih DESC");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				StokDomain siradakiDomain = new StokDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setMalzemeId(rs.getInt("malzemeid"));
				siradakiDomain.setAdet(rs.getInt("adet"));
				siradakiDomain.setTarih(rs.getString("tarih"));
				siradakiDomain.setAciklama(rs.getString("aciklama"));

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

	public static int stokAdediGetir(int malzemeId) {
		int stok = 0;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT SUM(adet) FROM stok WHERE malzemeid=?");

			sorgu.setInt(1, malzemeId);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				stok = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stok;
	}
	
	public static void stokTablosunuSil() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM stok");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
