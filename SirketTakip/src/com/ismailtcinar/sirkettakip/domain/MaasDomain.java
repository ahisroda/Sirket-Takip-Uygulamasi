package com.ismailtcinar.sirkettakip.domain;

import java.util.Objects;

import com.ismailtcinar.sirkettakip.dao.IsciDao;

public class MaasDomain {

	private int id;
	private int isciId;
	private int miktar;
	private String aciklama;
	private String tarih;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsciId() {
		return isciId;
	}

	public void setIsciId(int isciId) {
		this.isciId = isciId;
	}

	public int getMiktar() {
		return miktar;
	}

	public void setMiktar(int miktar) {
		this.miktar = miktar;
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
	public int hashCode() {
		return Objects.hash(aciklama, id, isciId, miktar, tarih);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaasDomain other = (MaasDomain) obj;
		return Objects.equals(aciklama, other.aciklama) && id == other.id && isciId == other.isciId
				&& miktar == other.miktar && Objects.equals(tarih, other.tarih);
	}

	@Override
	public String toString() {

		IsciDomain gelenIsciId = IsciDao.isciIdGetir(isciId);

		return gelenIsciId.getAdSoyad() + "  " + tarih + " tarihinde " + miktar + " TL (" + aciklama + ")";
	}

}
