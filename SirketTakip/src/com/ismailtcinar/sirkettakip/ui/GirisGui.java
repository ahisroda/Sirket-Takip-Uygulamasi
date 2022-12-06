package com.ismailtcinar.sirkettakip.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import com.ismailtcinar.sirkettakip.dao.AktifKullaniciDao;
import com.ismailtcinar.sirkettakip.dao.KullaniciDao;
import com.ismailtcinar.sirkettakip.domain.AktifKullaniciDomain;
import com.ismailtcinar.sirkettakip.domain.KullaniciDomain;

import Utils.SiniflaraErisim;

public class GirisGui extends JDialog {

	public GirisGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		pack();
		// setSize(600, 600);
		setTitle("Giriþ Ekraný");
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private JPanel initPanel() {

		JPanel anaPanel = new JPanel(new BorderLayout());

		JPanel girisPanel = new JPanel(new BorderLayout());
		JPanel ustPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		JPanel altPanel = new JPanel();

		JLabel kullaniciAdiLabel = new JLabel("Kullanýcý Adý");
		JTextField kullaniciAdiText = new JTextField(11);

		JLabel parolaLabel = new JLabel("Þifre");
		JTextField parolaText = new JTextField(10);

		AktifKullaniciDao.sil();

		ustPanel.add(kullaniciAdiLabel);
		ustPanel.add(kullaniciAdiText);
		ustPanel.add(parolaLabel);
		ustPanel.add(parolaText);

		girisPanel.add(ustPanel);

		ImageIcon girisIcon = new ImageIcon("icons/giris_24_Ikon.png");
		JButton girisButon = new JButton("Giriþ", girisIcon);
		getRootPane().setDefaultButton(girisButon);

		ImageIcon cikisIcon = new ImageIcon("icons/exit_24_Ikon.png");
		JButton cikisButon = new JButton("Çýkýþ", cikisIcon);

		ImageIcon sifremiUnuttumIcon = new ImageIcon("icons/sifre_24_Ikon.png");
		JButton sifremiUnuttumButon = new JButton("Þifremi Unuttum", sifremiUnuttumIcon);

		altPanel.add(girisButon);
		altPanel.add(sifremiUnuttumButon);
		altPanel.add(cikisButon);

		girisPanel.add(altPanel, BorderLayout.SOUTH);

		girisButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean kontrol = KullaniciDao.hesapGirisKontrolu(kullaniciAdiText.getText(), parolaText.getText());
				AktifKullaniciDomain eklenecekAktifKullanici = new AktifKullaniciDomain();
				KullaniciDomain kullaniciId = KullaniciDao.idGetir(kullaniciAdiText.getText());

				// KullaniciDomain gelenUnvanDomain =
				// KullaniciDao.unvanGetir(kullaniciAdiText.getText());
				// int gelenUnvan = gelenUnvanDomain.getUnvan();

				if (kontrol == false) {
					JOptionPane.showMessageDialog(GirisGui.this, "Hesap bilgileri yanlýþtýr!");
				} else {
					eklenecekAktifKullanici.setAktifKullaniciId(kullaniciId.getId());
					AktifKullaniciDao.ekle(eklenecekAktifKullanici);

					JOptionPane.showMessageDialog(GirisGui.this, "Giriþ baþarýlý");
					dispose();
					SiniflaraErisim.anaEkran();
				}
				/*
				 * } if (gelenUnvan == 1) { JOptionPane.showMessageDialog(GirisEkraniGui.this,
				 * "Giriþ baþarýlý"); dispose(); SiniflaraErisimUtil.swingHekimPaneli(); } else
				 * { JOptionPane.showMessageDialog(GirisEkraniGui.this, "Giriþ baþarýlý");
				 * dispose(); SiniflaraErisimUtil.swingSekreterPaneli(); } }
				 */
			}
		});

		cikisButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		sifremiUnuttumButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SiniflaraErisim.sifremiUnuttumEkrani();

			}
		});

		ImageIcon sirketIcon = new ImageIcon("icons/sirket128.png");

		JLabel sirketIconLabel = new JLabel("Þirket Yönetimi");
		sirketIconLabel.setIcon(sirketIcon);
		sirketIconLabel.setHorizontalTextPosition(JLabel.CENTER);
		sirketIconLabel.setVerticalTextPosition(JLabel.TOP);
		sirketIconLabel.setForeground(Color.black);
		sirketIconLabel.setBackground(Color.black);
		sirketIconLabel.setVerticalAlignment(JLabel.CENTER);
		sirketIconLabel.setHorizontalAlignment(JLabel.CENTER);
		sirketIconLabel.setFont(new Font("Serif", Font.PLAIN, 14));

		anaPanel.add(sirketIconLabel, BorderLayout.CENTER);

		anaPanel.add(girisPanel, BorderLayout.SOUTH);

		return anaPanel;
	}

}
