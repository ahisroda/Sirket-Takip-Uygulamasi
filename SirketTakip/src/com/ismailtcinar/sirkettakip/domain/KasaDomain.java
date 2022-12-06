package com.ismailtcinar.sirkettakip.domain;

import com.ismailtcinar.sirkettakip.dao.IsciDao;
import com.ismailtcinar.sirkettakip.dao.MaasDao;

public class KasaDomain {

	private int id;
	private int miktar;
	private String tarih;
	private String aciklama;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMiktar() {
		return miktar;
	}

	public void setMiktar(int miktar) {
		this.miktar = miktar;
	}

	

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	@Override
	public String toString() {

		return tarih + " tarihinde " + miktar + " TL" + " (" + aciklama + ")";
	}

}
