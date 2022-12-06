package com.ismailtcinar.sirkettakip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VeriTabani {

	public static final String YOL = "jdbc:derby:sirkettakip;create=true";
	public static final String KULLANICI = "";
	public static final String PAROLA = "";

	public static Connection baglantiAl() {
		Connection baglanti = null;

		try {
			baglanti = DriverManager.getConnection(YOL, KULLANICI, PAROLA);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baglanti;
	}

}
