package com.ismailtcinar.sirkettakip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ismailtcinar.sirkettakip.db.VeriTabani;
import com.ismailtcinar.sirkettakip.domain.BorcOdeDomain;
import com.ismailtcinar.sirkettakip.domain.BorcTanimlaDomain;

public class BorcOdeDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE borcode("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "borcid INTEGER, odenen INTEGER, kalan INTEGER, aciklama VARCHAR(50), zaman TIMESTAMP, tarih VARCHAR(50))");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Borç Öde tablosu zaten var");
		}
	}

	public static void ekle(BorcOdeDomain eklenecekBorcOdemesi) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement(
					"INSERT INTO borcode(borcid, kalan, odenen, aciklama,zaman,tarih) VALUES (?,?,?,?, CURRENT_TIMESTAMP,?)");

			sorgu.setInt(1, eklenecekBorcOdemesi.getBorcid());
			sorgu.setInt(2, eklenecekBorcOdemesi.getKalan());
			sorgu.setInt(3, eklenecekBorcOdemesi.getOdenen());
			sorgu.setString(4, eklenecekBorcOdemesi.getAciklama());
			sorgu.setString(5, eklenecekBorcOdemesi.getTarih());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int odenenGetir(int borcId) {

		int odenen = 0;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT SUM(odenen) FROM borcode WHERE borcid = ?");

			sorgu.setInt(1, borcId);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				odenen = rs.getInt(1);
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return odenen;
	}

	public static ArrayList<BorcOdeDomain> listele(int borcId) {

		ArrayList<BorcOdeDomain> liste = new ArrayList<BorcOdeDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT * FROM borcode WHERE borcid = ? ORDER BY zaman DESC");

			sorgu.setInt(1, borcId);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				BorcOdeDomain siradakiDomain = new BorcOdeDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setBorcid(rs.getInt("borcid"));
				siradakiDomain.setKalan(rs.getInt("kalan"));
				siradakiDomain.setOdenen(rs.getInt("odenen"));
				siradakiDomain.setAciklama(rs.getString("aciklama"));
				siradakiDomain.setZaman(rs.getTimestamp("zaman"));
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

	public static void odenenKalanGuncelle(int odenen, int kalan, int borcid) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("UPDATE borcode SET odenen = ?, kalan = ? WHERE borcid = ? ");

			sorgu.setInt(1, odenen);
			sorgu.setInt(2, kalan);
			sorgu.setInt(3, borcid);

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void borcOdeTablosunuSil() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM borcode");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
