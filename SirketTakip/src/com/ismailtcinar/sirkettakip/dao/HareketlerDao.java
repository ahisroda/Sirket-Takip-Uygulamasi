package com.ismailtcinar.sirkettakip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ismailtcinar.sirkettakip.db.VeriTabani;
import com.ismailtcinar.sirkettakip.domain.HareketlerDomain;

public class HareketlerDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE hareketler("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "aciklama VARCHAR(100), " + "tarih VARCHAR(50))");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Hareketler tablosu zaten var");
		}

	}

	public static void ekle(HareketlerDomain eklenecekHareket) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("INSERT INTO hareketler(aciklama, tarih) VALUES (?,?)");

			sorgu.setString(1, eklenecekHareket.getAciklama());
			sorgu.setString(2, eklenecekHareket.getTarih());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public static ArrayList<HareketlerDomain> Listele() {

		ArrayList<HareketlerDomain> liste = new ArrayList<HareketlerDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM hareketler ORDER BY tarih DESC");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				HareketlerDomain siradakiDomain = new HareketlerDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setAciklama(rs.getString("aciklama"));
				siradakiDomain.setTarih(rs.getString("tarih"));

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

	public static ArrayList<HareketlerDomain> buGununHareketleriListele(String tarih) {

		ArrayList<HareketlerDomain> liste = new ArrayList<HareketlerDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM hareketler WHERE tarih = ?");

			sorgu.setString(1, tarih);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				HareketlerDomain siradakiDomain = new HareketlerDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setAciklama(rs.getString("aciklama"));
				siradakiDomain.setTarih(rs.getString("tarih"));

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

	public static void hareketlerTablosunuSil() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM hareketler");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
