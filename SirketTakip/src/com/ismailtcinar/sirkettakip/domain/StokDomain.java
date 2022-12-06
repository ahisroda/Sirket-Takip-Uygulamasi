package com.ismailtcinar.sirkettakip.domain;

import com.ismailtcinar.sirkettakip.dao.MalzemeDao;

public class StokDomain {

	private int id;
	private int malzemeId;
	private int adet;
	private String tarih;
	private String aciklama;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMalzemeId() {
		return malzemeId;
	}

	public void setMalzemeId(int malzemeId) {
		this.malzemeId = malzemeId;
	}

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
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

		MalzemeDomain gelenMalzeme = MalzemeDao.malzemeIdGetir(malzemeId);

		return gelenMalzeme.getAdi() + "  " + adet + " adet | " + tarih + " (" + aciklama + ")";

	}

}
