package com.ismailtcinar.sirkettakip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ismailtcinar.sirkettakip.db.VeriTabani;
import com.ismailtcinar.sirkettakip.domain.BorcOdeDomain;
import com.ismailtcinar.sirkettakip.domain.BorcTanimlaDomain;
import com.ismailtcinar.sirkettakip.domain.OdenmisBorclarDomain;

public class OdenmisBorclarDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE odenmisborclar("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "borcid INTEGER, aciklama VARCHAR(50), zaman TIMESTAMP, tarih VARCHAR(50))");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ödenmiþ borçlar tablosu zaten var");
		}
	}

	public static void ekle(OdenmisBorclarDomain eklenecekOdenmisBorc) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement(
					"INSERT INTO odenmisborclar(borcid, aciklama, zaman, tarih) VALUES (?,?,CURRENT_TIMESTAMP, ?)");

			sorgu.setInt(1, eklenecekOdenmisBorc.getBorcid());
			sorgu.setString(2, eklenecekOdenmisBorc.getAciklama());
			sorgu.setString(3, eklenecekOdenmisBorc.getTarih());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<OdenmisBorclarDomain> odenmisBorclariListele() {

		ArrayList<OdenmisBorclarDomain> liste = new ArrayList<OdenmisBorclarDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM odenmisborclar ORDER BY zaman DESC");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				OdenmisBorclarDomain siradakiDomain = new OdenmisBorclarDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setBorcid(rs.getInt("borcid"));
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

	public static void odenmisBorclarTablosunuSil() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM odenmisborclar");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
