package com.ismailtcinar.sirkettakip.test;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.ismailtcinar.sirkettakip.dao.AktifKullaniciDao;
import com.ismailtcinar.sirkettakip.dao.BorcOdeDao;
import com.ismailtcinar.sirkettakip.dao.BorcTanimlaDao;
import com.ismailtcinar.sirkettakip.dao.HareketlerDao;
import com.ismailtcinar.sirkettakip.dao.IsciDao;
import com.ismailtcinar.sirkettakip.dao.KasaDao;
import com.ismailtcinar.sirkettakip.dao.KullaniciDao;
import com.ismailtcinar.sirkettakip.dao.MaasDao;
import com.ismailtcinar.sirkettakip.dao.MalzemeDao;
import com.ismailtcinar.sirkettakip.dao.OdenmisBorclarDao;
import com.ismailtcinar.sirkettakip.dao.StokDao;
import com.ismailtcinar.sirkettakip.ui.GirisGui;
import com.ismailtcinar.sirkettakip.ui.YeniHesapOlusturGui;

import Utils.SiniflaraErisim;

public class Test {

	public static void main(String[] args) {

		KullaniciDao.tabloOlustur();
		IsciDao.tabloOlustur();
		MaasDao.tabloOlustur();
		KasaDao.tabloOlustur();
		MalzemeDao.tabloOlustur();
		StokDao.tabloOlustur();
		HareketlerDao.tabloOlustur();
		AktifKullaniciDao.tabloOlustur();
		BorcTanimlaDao.tabloOlustur();
		BorcOdeDao.tabloOlustur();
		OdenmisBorclarDao.tabloOlustur();

		boolean hesapMevcutKontrolu = KullaniciDao.zatenHesapVarMi();

		// com.sun.java.swing.plaf.windows.WindowsLookAndFeel
		// javax.swing.plaf.nimbus.NimbusLookAndFeel
		// javax.swing.plaf.metal.MetalLookAndFeel
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (hesapMevcutKontrolu == false) {
					JOptionPane.showMessageDialog(null, "Kayýtlý hesap yoktur, lütfen kayýt yapýnýz");
					SiniflaraErisim.yeniHesapOlusturEkrani();
					// SiniflaraErisim.anaEkran();
				} else {
					SiniflaraErisim.girisEkrani();
					// SiniflaraErisim.anaEkran();
				}

				// SiniflaraErisim.anaEkran();
			}
		});

	}

}
