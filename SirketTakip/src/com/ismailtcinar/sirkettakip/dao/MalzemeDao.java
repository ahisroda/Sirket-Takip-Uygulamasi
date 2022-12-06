package com.ismailtcinar.sirkettakip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ismailtcinar.sirkettakip.db.VeriTabani;
import com.ismailtcinar.sirkettakip.domain.IsciDomain;
import com.ismailtcinar.sirkettakip.domain.MalzemeDomain;

public class MalzemeDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE malzeme("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "adi VARCHAR(50), " + "malzemeno VARCHAR(50))");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Malzeme tablosu zaten var");
		}

	}

	public static void ekle(MalzemeDomain eklenecekMalzeme) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("INSERT INTO malzeme(adi, malzemeno) VALUES (?,?)");

			sorgu.setString(1, eklenecekMalzeme.getAdi());
			sorgu.setString(2, eklenecekMalzeme.getMalzemeNo());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public static void sil(MalzemeDomain silinecekMalzeme) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM malzeme WHERE id = ?");

			sorgu.setInt(1, silinecekMalzeme.getId());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public static void duzenle(MalzemeDomain duzenlenecekMalzeme) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("UPDATE malzeme SET adi =?, malzemeno = ? WHERE id = ?");

			sorgu.setString(1, duzenlenecekMalzeme.getAdi());
			sorgu.setString(2, duzenlenecekMalzeme.getMalzemeNo());
			sorgu.setInt(3, duzenlenecekMalzeme.getId());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public static ArrayList<MalzemeDomain> listele() {
		ArrayList<MalzemeDomain> liste = new ArrayList<MalzemeDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM malzeme ORDER BY adi ASC");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				MalzemeDomain eklenecekMalzeme = new MalzemeDomain();

				eklenecekMalzeme.setId(rs.getInt("id"));
				eklenecekMalzeme.setAdi(rs.getString("adi"));
				eklenecekMalzeme.setMalzemeNo(rs.getString("malzemeno"));

				liste.add(eklenecekMalzeme);

			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return liste;
	}

	public static ArrayList<MalzemeDomain> bul(String malzemeAdi) {

		ArrayList<MalzemeDomain> malzemeListesi = new ArrayList<MalzemeDomain>();

		Connection baglantiConnection = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglantiConnection
					.prepareStatement("SELECT * FROM malzeme WHERE adi LIKE ? ORDER BY adi ASC");
			sorgu.setString(1, "%" + malzemeAdi + "%");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				MalzemeDomain siradakiDomain = new MalzemeDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setAdi(rs.getString("adi"));
				siradakiDomain.setMalzemeNo(rs.getString("malzemeno"));

				malzemeListesi.add(siradakiDomain);
			}

			baglantiConnection.close();
			sorgu.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return malzemeListesi;
	}

	public static MalzemeDomain malzemeIdGetir(int id) {
		MalzemeDomain gelenMalzeme = new MalzemeDomain();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM malzeme WHERE id = ?");

			sorgu.setInt(1, id);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {

				gelenMalzeme.setId(rs.getInt("id"));
				gelenMalzeme.setAdi(rs.getString("adi"));

			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return gelenMalzeme;
	}

	public static boolean malzemeNoMevcutMu(String malzemeNo) {
		boolean kontrol = false;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM malzeme WHERE malzemeno = ?");

			sorgu.setString(1, malzemeNo);

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

	public static MalzemeDomain malzemeAdiGetir(String malzemeNo) {
		MalzemeDomain gelenMalzeme = new MalzemeDomain();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM malzeme WHERE malzemeno = ?");

			sorgu.setString(1, malzemeNo);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {

				gelenMalzeme.setId(rs.getInt("id"));
				gelenMalzeme.setAdi(rs.getString("adi"));

			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return gelenMalzeme;
	}

	public static void malzemeTablosunuSil() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM malzeme");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
