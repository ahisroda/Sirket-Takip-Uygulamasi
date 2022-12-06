package com.ismailtcinar.sirkettakip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ismailtcinar.sirkettakip.db.VeriTabani;
import com.ismailtcinar.sirkettakip.domain.KasaDomain;

public class KasaDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE kasa("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "miktar INTEGER, " + "tarih VARCHAR(50), " + "aciklama VARCHAR(50))");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Kasa tablosu zaten var");
		}
	}

	public static void ekle(KasaDomain eklenecekKasa) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("INSERT INTO kasa(miktar, aciklama, tarih) VALUES (?,?,?)");

			sorgu.setInt(1, eklenecekKasa.getMiktar());
			sorgu.setString(2, eklenecekKasa.getAciklama());
			sorgu.setString(3, eklenecekKasa.getTarih());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static int kasadakiToplamPara() {
		int kasa = 0;
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT SUM(miktar) FROM kasa");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				kasa = rs.getInt(1);
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return kasa;
	}

	public static ArrayList<KasaDomain> listele() {
		ArrayList<KasaDomain> liste = new ArrayList<KasaDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM kasa ORDER BY tarih DESC");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				KasaDomain siradakiDomain = new KasaDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setAciklama(rs.getString("aciklama"));
				siradakiDomain.setMiktar(rs.getInt("miktar"));
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
	
	
	public static void kasaTablosunuSil() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM kasa");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
