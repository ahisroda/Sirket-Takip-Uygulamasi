package com.ismailtcinar.sirkettakip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ismailtcinar.sirkettakip.db.VeriTabani;
import com.ismailtcinar.sirkettakip.domain.IsciDomain;
import com.ismailtcinar.sirkettakip.domain.MaasDomain;

public class MaasDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE maas("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "isciid INTEGER, " + "miktar INTEGER, " + "tarih VARCHAR(50), " + "aciklama VARCHAR(50))");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Maaþ tablosu zaten var");
		}
	}

	public static void ekle(MaasDomain eklenecekMaas) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("INSERT INTO maas(isciid, miktar, tarih, aciklama) VALUES (?,?,?,?)");

			sorgu.setInt(1, eklenecekMaas.getIsciId());
			sorgu.setInt(2, eklenecekMaas.getMiktar());
			sorgu.setString(3, eklenecekMaas.getTarih());
			sorgu.setString(4, eklenecekMaas.getAciklama());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static ArrayList<MaasDomain> listele() {

		ArrayList<MaasDomain> liste = new ArrayList<MaasDomain>();
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM maas ORDER BY tarih DESC");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				MaasDomain siradakiMaas = new MaasDomain();

				siradakiMaas.setId(rs.getInt("id"));
				siradakiMaas.setIsciId(rs.getInt("isciid"));
				siradakiMaas.setTarih(rs.getString("tarih"));
				siradakiMaas.setMiktar(rs.getInt("miktar"));
				siradakiMaas.setAciklama(rs.getString("aciklama"));

				liste.add(siradakiMaas);

			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return liste;
	}

	public static MaasDomain isciIdGetir(int id) {

		MaasDomain gelecekIsciId = new MaasDomain();
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM maas WHERE isciid = ?");

			sorgu.setInt(1, id);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				gelecekIsciId.setId(rs.getInt("id"));
				gelecekIsciId.setIsciId(rs.getInt("isciid"));
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return gelecekIsciId;
	}

	public static void maasTablosunuSil() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM maas");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
