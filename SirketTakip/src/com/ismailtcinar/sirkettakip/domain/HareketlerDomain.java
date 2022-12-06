package com.ismailtcinar.sirkettakip.domain;

public class HareketlerDomain {

	private int id;
	private String aciklama;
	private String tarih;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	@Override
	public String toString() {
		return "(" + tarih + ") " + aciklama;
	}

}
