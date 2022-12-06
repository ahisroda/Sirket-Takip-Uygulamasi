package com.ismailtcinar.sirkettakip.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.ismailtcinar.sirkettakip.dao.BorcOdeDao;
import com.ismailtcinar.sirkettakip.dao.BorcTanimlaDao;
import com.ismailtcinar.sirkettakip.dao.OdenmisBorclarDao;
import com.ismailtcinar.sirkettakip.domain.BorcOdeDomain;
import com.ismailtcinar.sirkettakip.domain.BorcTanimlaDomain;
import com.ismailtcinar.sirkettakip.domain.OdenmisBorclarDomain;

import Utils.SiniflaraErisim;

public class BorcOdeGui extends JDialog {

	private BorcOdeDomain borcOdeDomain = null;

	public BorcOdeGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		setSize(800, 250);
		setJMenuBar(initMenu());
		setTitle("Borç Öde");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}

	private JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());

		JPanel sagPanel = new JPanel(new GridLayout(4, 2, 5, 5));

		JLabel borclarLabel = new JLabel("Borçlar: ");
		JComboBox borclarBox = new JComboBox(BorcTanimlaDao.listele().toArray());

		JLabel odenecekTutarLabel = new JLabel("Ödenecek Tutar");
		JTextField odenecekTutarText = new JTextField(12);

		JLabel tarihLabel = new JLabel("Tarih");
		JTextField tarihText = new JTextField(12);

		ImageIcon odeIcon = new ImageIcon("icons/ode24.png");
		JButton odeButon = new JButton("Öde", odeIcon);
		getRootPane().setDefaultButton(odeButon);

		ImageIcon geriIcon = new ImageIcon("icons/geri_24_Ikon.png");
		JButton geriButon = new JButton("Geri", geriIcon);

		sagPanel.add(borclarLabel);
		sagPanel.add(borclarBox);
		sagPanel.add(odenecekTutarLabel);
		sagPanel.add(odenecekTutarText);
		sagPanel.add(tarihLabel);
		sagPanel.add(tarihText);
		sagPanel.add(odeButon);
		sagPanel.add(geriButon);

		JList borcList = new JList();
		JScrollPane borcPane = new JScrollPane(borcList);
		borcPane.setBorder(BorderFactory.createTitledBorder("Ödenmiþ borç verisi"));
		borcPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		anaPanel.add(borcPane);

		odeButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BorcTanimlaDomain seciliBorc = (BorcTanimlaDomain) borclarBox.getSelectedItem();

				BorcOdeDomain borcOde = new BorcOdeDomain();

				int odenecekTutar = Integer.parseInt(odenecekTutarText.getText());
				int borc = BorcTanimlaDao.borcGetir(seciliBorc.getId());
				int kalan = borc - odenecekTutar;
				int odenen = borc - kalan;

				if (seciliBorc != null && !odenecekTutarText.getText().equals("") && !tarihText.getText().equals("")) {

					if (borc < odenecekTutar) {
						JOptionPane.showMessageDialog(BorcOdeGui.this, "Bu kadar borcunuz yok sakin olun :)");
					} else {
						borcOde.setBorcid(seciliBorc.getId());
						borcOde.setKalan(kalan);
						borcOde.setOdenen(odenen);
						borcOde.setTarih(tarihText.getText());
						borcOde.setAciklama("(" + seciliBorc.getKime() + ") " + String.valueOf(borc) + " TL'den "
								+ String.valueOf(odenecekTutar) + " TL ödendi | Kalan: " + String.valueOf(kalan)
								+ " TL");

						BorcOdeDao.ekle(borcOde);

						BorcTanimlaDao.borcuGuncelle(kalan, seciliBorc.getId());

						odenecekTutarText.setText("");
						borclarBox.setSelectedItem(null);

						borcList.setListData(BorcOdeDao.listele(seciliBorc.getId()).toArray());

						OdenmisBorclarDomain odenmisBorcEkle = new OdenmisBorclarDomain();
						int guncelBorc = BorcTanimlaDao.borcGetir(seciliBorc.getId());

						if (guncelBorc <= 0) {

							int odenenBorcD = BorcOdeDao.odenenGetir(seciliBorc.getId());

							BorcTanimlaDao.durumuGuncelle(2, seciliBorc.getId());
							odenmisBorcEkle.setBorcid(seciliBorc.getId());
							odenmisBorcEkle.setAciklama(
									seciliBorc.getKime() + "'ye olan " + odenenBorcD + " TL'lik borç ödenmiþtir...");
							odenmisBorcEkle.setTarih(tarihText.getText());
							JOptionPane.showMessageDialog(BorcOdeGui.this,
									seciliBorc.getKime() + "'ye olan borç ödenmiþtir, Hayýrlý olsun :)");
							OdenmisBorclarDao.ekle(odenmisBorcEkle);
							BorcTanimlaDao.durumaGoreSil();
						}

					}

				} else {
					JOptionPane.showMessageDialog(BorcOdeGui.this, "Boþ yer býrakmayýnýz");
				}

			}
		});

		geriButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		anaPanel.add(sagPanel, BorderLayout.EAST);

		return anaPanel;

	}

	private JMenuBar initMenu() {
		JMenuBar bar = new JMenuBar();

		JMenu borcExtraMenu = new JMenu("Borç Extra");

		ImageIcon mutluIcon = new ImageIcon("icons/mutlu16.png");
		JMenuItem odenmisBorclarItem = new JMenuItem("Ödenmiþ Borçlar", mutluIcon);

		borcExtraMenu.add(odenmisBorclarItem);

		odenmisBorclarItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.odenmisBorclarEkrani();

			}
		});

		bar.add(borcExtraMenu);

		return bar;
	}

}
