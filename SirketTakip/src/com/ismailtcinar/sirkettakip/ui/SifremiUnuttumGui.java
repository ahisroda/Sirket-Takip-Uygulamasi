package com.ismailtcinar.sirkettakip.ui;

import java.awt.BorderLayout;
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

import com.ismailtcinar.sirkettakip.dao.KullaniciDao;
import com.ismailtcinar.sirkettakip.domain.KullaniciDomain;

import Utils.SiniflaraErisim;

public class SifremiUnuttumGui extends JDialog {

	public SifremiUnuttumGui() {
		initPencere();
	}

	public void initPencere() {
		add(initPanel());
		setSize(400, 250);
		setTitle("Þifremi Unuttum");
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel ustPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		JPanel altPanel = new JPanel();

		JLabel kullaniciAdiLabel = new JLabel("Kullanýcý Adý");
		JTextField kullaniciAdiText = new JTextField(11);

		JLabel hesapKurtarmaNoLabel = new JLabel("Hesap Kurtarma no");
		JTextField hesapKurtarmaNoText = new JTextField(10);

		JLabel yeniParolaLabel = new JLabel("Yeni Þifre");
		JTextField yeniParolaText = new JTextField(10);

		JLabel yeniParolaTekrarLabel = new JLabel("Yeni Þifre Tekrar");
		JTextField yeniParolaTekrarText = new JTextField(10);

		ustPanel.add(kullaniciAdiLabel);
		ustPanel.add(kullaniciAdiText);
		ustPanel.add(hesapKurtarmaNoLabel);
		ustPanel.add(hesapKurtarmaNoText);
		ustPanel.add(yeniParolaLabel);
		ustPanel.add(yeniParolaText);
		ustPanel.add(yeniParolaTekrarLabel);
		ustPanel.add(yeniParolaTekrarText);

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

				KullaniciDomain duzenlenecekSifre = new KullaniciDomain();

				boolean hesapBilgileriDogruMu = KullaniciDao.sifremiUnuttumKontrolu(kullaniciAdiText.getText(),
						hesapKurtarmaNoText.getText());
				if (hesapBilgileriDogruMu == false) {
					JOptionPane.showMessageDialog(SifremiUnuttumGui.this, "Hesap bilgileri yanlýþ!");
				} else {
					duzenlenecekSifre.setParola(yeniParolaText.getText());
					if (yeniParolaText.getText().equals(yeniParolaTekrarText.getText())) {
						KullaniciDao.sifreDegistir(duzenlenecekSifre, kullaniciAdiText.getText());
						JOptionPane.showMessageDialog(SifremiUnuttumGui.this, "Þifreniz Deðiþmiþtir");
						dispose();
						SiniflaraErisim.girisEkrani();

					} else {
						JOptionPane.showMessageDialog(SifremiUnuttumGui.this, "Girilen þifreler ayný deðil");
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
