package com.ismailtcinar.sirkettakip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ismailtcinar.sirkettakip.db.VeriTabani;
import com.ismailtcinar.sirkettakip.domain.IsciDomain;

public class IsciDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE isci("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "adi VARCHAR(50), " + "telno VARCHAR(50), " + "tckimlik VARCHAR(50))");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ýsçi tablosu zaten var");
		}
	}

	public static void ekle(IsciDomain eklenecekIsci) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("INSERT INTO isci(adi, telno, tckimlik) VALUES (?,?,?)");

			sorgu.setString(1, eklenecekIsci.getAdSoyad());
			sorgu.setString(2, eklenecekIsci.getTelno());
			sorgu.setString(3, eklenecekIsci.getTcKimlik());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void duzenle(IsciDomain duzenlenecekIsci) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("UPDATE isci SET adi = ?, telno = ?, tckimlik =? WHERE id =?");

			sorgu.setString(1, duzenlenecekIsci.getAdSoyad());
			sorgu.setString(2, duzenlenecekIsci.getTelno());
			sorgu.setString(3, duzenlenecekIsci.getTcKimlik());
			sorgu.setInt(4, duzenlenecekIsci.getId());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void sil(IsciDomain silinecekIsci) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM isci WHERE id = ?");

			sorgu.setInt(1, silinecekIsci.getId());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static ArrayList<IsciDomain> listele() {

		ArrayList<IsciDomain> liste = new ArrayList<IsciDomain>();
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM isci ORDER BY adi ASC");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				IsciDomain siradakiIscý = new IsciDomain();

				siradakiIscý.setId(rs.getInt("id"));
				siradakiIscý.setAdSoyad(rs.getString("adi"));
				siradakiIscý.setTelno(rs.getString("telno"));
				siradakiIscý.setTcKimlik(rs.getString("tckimlik"));

				liste.add(siradakiIscý);

			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return liste;
	}

	public static boolean mevcutIscýVarMi(String tckimlik) {

		boolean kontrol = false;
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM isci WHERE tckimlik = ?");

			sorgu.setString(1, tckimlik);

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

	public static ArrayList<IsciDomain> bul(String isciAdi) {

		ArrayList<IsciDomain> isciListesi = new ArrayList<IsciDomain>();

		Connection baglantiConnection = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglantiConnection
					.prepareStatement("SELECT * FROM isci WHERE adi LIKE ? ORDER BY adi ASC");
			sorgu.setString(1, "%" + isciAdi + "%");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				IsciDomain siradakiDomain = new IsciDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setAdSoyad(rs.getString("adi"));
				siradakiDomain.setTelno(rs.getString("telno"));
				siradakiDomain.setTcKimlik(rs.getString("tckimlik"));

				isciListesi.add(siradakiDomain);
			}

			baglantiConnection.close();
			sorgu.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isciListesi;
	}

	public static IsciDomain isciIdGetir(int id) {

		IsciDomain gelecekIsciId = new IsciDomain();
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM isci WHERE id = ?");

			sorgu.setInt(1, id);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				gelecekIsciId.setId(rs.getInt("id"));
				gelecekIsciId.setAdSoyad(rs.getString("adi"));
				gelecekIsciId.setTcKimlik(rs.getString("tckimlik"));
				gelecekIsciId.setTelno(rs.getString("telno"));
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return gelecekIsciId;
	}
	
	public static void isciTablosunuSil() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM isci");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
