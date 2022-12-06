package com.ismailtcinar.sirkettakip.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.ismailtcinar.sirkettakip.dao.MalzemeDao;
import com.ismailtcinar.sirkettakip.dao.StokDao;
import com.ismailtcinar.sirkettakip.domain.MalzemeDomain;
import com.ismailtcinar.sirkettakip.domain.StokDomain;

import Utils.DigerUtils;

public class StokGui extends JDialog {

	MalzemeDomain malzemeDomain = null;

	public StokGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		setSize(600, 307);
		setTitle("Stok Ekraný");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}

	private JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel solPanel = new JPanel(new GridLayout(8, 2, 5, 5));
		JPanel radioPanel = new JPanel();

		JLabel malzemeNoLabel = new JLabel("Malzeme No");
		JTextField malzemeNoField = new JTextField();

		JLabel malzemeAdiLabel = new JLabel("Malzeme Adý");
		JTextField malzemeAdiField = new JTextField();
		malzemeAdiField.setEditable(false);

		JLabel adetLabel = new JLabel("Adet");
		JTextField adetField = new JTextField();

		JLabel tarihLabel = new JLabel("Tarih");
		JTextField tarihField = new JTextField();

		JLabel aciklamaLabel = new JLabel("Açýklama");
		JTextField aciklamaField = new JTextField();

		JLabel hareketLabel = new JLabel("Hareket:");
		ButtonGroup grup = new ButtonGroup();
		JRadioButton girisButon = new JRadioButton("Giriþ");
		JRadioButton cikisButon = new JRadioButton("Çýkýþ");
		grup.add(girisButon);
		grup.add(cikisButon);

		radioPanel.add(girisButon);
		radioPanel.add(cikisButon);

		JLabel saLabel = new JLabel("Stok adedi: ");
		JLabel stokMiktariLabel = new JLabel();

		ImageIcon kaydetIkon = new ImageIcon("icons/save_24_Ikon.png");
		JButton kaydetButon = new JButton("Kaydet", kaydetIkon);

		ImageIcon geriIkon = new ImageIcon("icons/geri_24_Ikon.png");
		JButton geriButon = new JButton("Geri", geriIkon);

		solPanel.add(malzemeNoLabel);
		solPanel.add(malzemeNoField);
		solPanel.add(malzemeAdiLabel);
		solPanel.add(malzemeAdiField);
		solPanel.add(adetLabel);
		solPanel.add(adetField);
		solPanel.add(tarihLabel);
		solPanel.add(tarihField);
		solPanel.add(aciklamaLabel);
		solPanel.add(aciklamaField);
		solPanel.add(hareketLabel);
		solPanel.add(radioPanel);
		solPanel.add(saLabel);
		solPanel.add(stokMiktariLabel);
		solPanel.add(kaydetButon);
		solPanel.add(geriButon);

		JList stokList = new JList();
		listele(stokList);
		JScrollPane stokPane = new JScrollPane(stokList);
		stokPane.setBorder(BorderFactory.createTitledBorder("Stok Hareketleri"));
		stokPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		anaPanel.add(stokPane);

		malzemeNoField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean malzemeVarMý = MalzemeDao.malzemeNoMevcutMu(malzemeNoField.getText());

				if (malzemeVarMý == false) {
					JOptionPane.showMessageDialog(StokGui.this, "Bu numarada malzeme bulunmamaktadýr!");
				} else {
					malzemeDomain = MalzemeDao.malzemeAdiGetir(malzemeNoField.getText());
					malzemeAdiField.setText(malzemeDomain.toString());
					stokMiktariLabel.setText(String.valueOf(StokDao.stokAdediGetir(malzemeDomain.getId())) + " adet");
				}

			}
		});

		kaydetButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int malzemeId = malzemeDomain.getId();
				int guncelStokAdedi = StokDao.stokAdediGetir(malzemeId);
				MalzemeDomain gelenMalzemeAdi = MalzemeDao.malzemeAdiGetir(malzemeNoField.getText());

				if (malzemeDomain != null) {

					if (adetField.getText().equals("") || tarihField.getText().equals("")
							|| aciklamaField.getText().equals("") || grup.isSelected(null)) {
						JOptionPane.showMessageDialog(StokGui.this, "Lütfen boþ alan býrakmayýnýz");
					} else {

						int adet = Integer.parseInt(adetField.getText());
						String tarih = tarihField.getText();
						String aciklama = aciklamaField.getText();

						int hareket;

						if (girisButon.isSelected()) {
							hareket = 0;
						} else {
							hareket = 1;
							adet = -adet;
						}

						StokDomain eklenecekStok = new StokDomain();

						eklenecekStok.setMalzemeId(malzemeId);
						eklenecekStok.setAdet(adet);
						eklenecekStok.setAciklama(aciklama);
						eklenecekStok.setTarih(tarih);

						StokDao.ekle(eklenecekStok);
						listele(stokList);
						JOptionPane.showMessageDialog(StokGui.this, gelenMalzemeAdi.getAdi()+" stoða tanýmlanmýþtýr");
						DigerUtils.alanTemizle(new JTextField[] { malzemeNoField, malzemeAdiField, adetField,
								tarihField, aciklamaField });

						stokMiktariLabel.setText(String.valueOf(StokDao.stokAdediGetir(guncelStokAdedi)) + " adet");

					}

				} else {
					JOptionPane.showMessageDialog(StokGui.this, "Lütfen malzeme tanýmlayýn");
				}

			}
		});

		geriButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		anaPanel.add(solPanel, BorderLayout.WEST);
		return anaPanel;
	}

	private void listele(JList l) {
		l.setListData(StokDao.listele().toArray());
	}

}
