package com.ismailtcinar.sirkettakip.domain;

public class BorcTanimlaDomain {

	private int id;
	private String kime;
	private int borc;
	private String tarih;
	private int durum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKime() {
		return kime;
	}

	public void setKime(String kime) {
		this.kime = kime;
	}

	public int getBorc() {
		return borc;
	}

	public void setBorc(int borc) {
		this.borc = borc;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public int getDurum() {
		return durum;
	}

	public void setDurum(int durum) {
		this.durum = durum;
	}

	@Override
	public String toString() {
		return kime + "'ye Borç: " + borc + " TL";
	}

}
