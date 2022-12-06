package com.ismailtcinar.sirkettakip.domain;

import java.util.Objects;

public class IsciDomain {

	private int id;
	private String adSoyad;
	private String telno;
	private String tcKimlik;

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

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getTcKimlik() {
		return tcKimlik;
	}

	public void setTcKimlik(String tcKimlik) {
		this.tcKimlik = tcKimlik;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adSoyad, id, tcKimlik, telno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IsciDomain other = (IsciDomain) obj;
		return Objects.equals(adSoyad, other.adSoyad) && id == other.id && Objects.equals(tcKimlik, other.tcKimlik)
				&& Objects.equals(telno, other.telno);
	}

	@Override
	public String toString() {
		return adSoyad;
	}

}
