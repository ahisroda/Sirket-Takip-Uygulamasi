package com.ismailtcinar.sirkettakip.domain;

public class KullaniciDomain {

	private int id;
	private String adSoyad;
	private String kullaniciAdi;
	private String parola;
	private String hesapKurtarmaNo;
	private int yetki;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdSoyad() {
		return adSoyad;
	}

	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public String getHesapKurtarmaNo() {
		return hesapKurtarmaNo;
	}

	public void setHesapKurtarmaNo(String hesapKurtarmaNo) {
		this.hesapKurtarmaNo = hesapKurtarmaNo;
	}

	public int getYetki() {
		return yetki;
	}

	public void setYetki(int yetki) {
		this.yetki = yetki;
	}

}
