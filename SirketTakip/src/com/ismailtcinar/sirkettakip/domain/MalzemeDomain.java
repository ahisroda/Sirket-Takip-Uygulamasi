package com.ismailtcinar.sirkettakip.domain;

public class MalzemeDomain {

	private int id;
	private String adi;
	private String malzemeNo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getMalzemeNo() {
		return malzemeNo;
	}

	public void setMalzemeNo(String malzemeNo) {
		this.malzemeNo = malzemeNo;
	}

	@Override
	public String toString() {
		return adi;
	}

}
