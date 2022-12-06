package com.ismailtcinar.sirkettakip.domain;

import java.sql.Timestamp;

public class BorcOdeDomain {

	private int id;
	private int borcid;
	private int odenen;
	private int kalan;
	private String aciklama;
	private Timestamp zaman;
	private String tarih;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBorcid() {
		return borcid;
	}

	public void setBorcid(int borcid) {
		this.borcid = borcid;
	}

	public int getOdenen() {
		return odenen;
	}

	public void setOdenen(int odenen) {
		this.odenen = odenen;
	}

	public int getKalan() {
		return kalan;
	}

	public void setKalan(int kalan) {
		this.kalan = kalan;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public Timestamp getZaman() {
		return zaman;
	}

	public void setZaman(Timestamp zaman) {
		this.zaman = zaman;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	@Override
	public String toString() {
		return tarih + " tarihinde " + aciklama;
	}

}
