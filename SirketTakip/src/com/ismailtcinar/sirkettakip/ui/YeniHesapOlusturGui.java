package com.ismailtcinar.sirkettakip.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ismailtcinar.sirkettakip.dao.KullaniciDao;
import com.ismailtcinar.sirkettakip.domain.KullaniciDomain;

import Utils.DigerUtils;
import Utils.SiniflaraErisim;

public class YeniHesapOlusturGui extends JDialog {

	public YeniHesapOlusturGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		setSize(400, 300);
		setTitle("Kaydol");
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel ustPanel = new JPanel(new GridLayout(5, 2, 5, 5));
		JPanel altPanel = new JPanel();

		JLabel adiLabel = new JLabel("Ad-Soyad");
		JTextField adiText = new JTextField(10);

		JLabel kullaniciAdiLabel = new JLabel("Kullan�c� Ad�");
		JTextField kullaniciAdiText = new JTextField(11);

		JLabel parolaLabel = new JLabel("�ifre");
		JTextField parolaText = new JTextField(10);

		JLabel parolaTekrarLabel = new JLabel("�ifre Tekrar");
		JTextField parolaTekrarText = new JTextField(10);

		JLabel hesapKurtarmaNoLabel = new JLabel("Hesap Kurtarma no");
		JTextField hesapKurtarmaNoText = new JTextField(10);

		ustPanel.add(adiLabel);
		ustPanel.add(adiText);
		ustPanel.add(kullaniciAdiLabel);
		ustPanel.add(kullaniciAdiText);
		ustPanel.add(parolaLabel);
		ustPanel.add(parolaText);
		ustPanel.add(parolaTekrarLabel);
		ustPanel.add(parolaTekrarText);
		ustPanel.add(hesapKurtarmaNoLabel);
		ustPanel.add(hesapKurtarmaNoText);

		anaPanel.add(ustPanel);

		ImageIcon ekleIcon = new ImageIcon("icons/ekle_24_Ikon.png");
		JButton kaydetButon = new JButton("Kaydet", ekleIcon);
		getRootPane().setDefaultButton(kaydetButon);

		ImageIcon geriIcon = new ImageIcon("icons/geri_24_Ikon.png");
		JButton geriButon = new JButton("Geri", geriIcon);

		altPanel.add(kaydetButon);
		altPanel.add(geriButon);

		anaPanel.add(altPanel, BorderLayout.SOUTH);

		kaydetButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				KullaniciDomain eklenecekKullanici = new KullaniciDomain();

				eklenecekKullanici.setAdSoyad(adiText.getText());
				eklenecekKullanici.setKullaniciAdi(kullaniciAdiText.getText());
				eklenecekKullanici.setParola(parolaText.getText());
				eklenecekKullanici.setHesapKurtarmaNo(hesapKurtarmaNoText.getText());
				eklenecekKullanici.setYetki(1);

				boolean kullaniciMevcudiyetKontrolu = KullaniciDao.hesapMevcutMu(kullaniciAdiText.getText());

				if (adiText.getText().equals("") || kullaniciAdiText.getText().equals("")
						|| parolaText.getText().equals("") || hesapKurtarmaNoText.getText().equals("")) {
					JOptionPane.showMessageDialog(YeniHesapOlusturGui.this, "L�tfen t�m alanlar� doldurunuz");
				} else {

					if (kullaniciMevcudiyetKontrolu == false) {
						if (parolaText.getText().equals(parolaTekrarText.getText())) {
							KullaniciDao.ekle(eklenecekKullanici);
							DigerUtils.alanTemizle(new JTextField[] { adiText, kullaniciAdiText, parolaText,
									parolaTekrarText, hesapKurtarmaNoText });
							JOptionPane.showMessageDialog(YeniHesapOlusturGui.this, " Hesab�n�z olu�turulmu�tur");
							dispose();
							SiniflaraErisim.girisEkrani();
						} else {
							JOptionPane.showMessageDialog(YeniHesapOlusturGui.this, "Parolalar uyu�muyor");
						}
					} else {
						JOptionPane.showMessageDialog(YeniHesapOlusturGui.this, "Kullan�c� ad�nda mevcut hesap vard�r");
					}
				}

			}
		});

		geriButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SiniflaraErisim.girisEkrani();

			}
		});

		return anaPanel;
	}

}
