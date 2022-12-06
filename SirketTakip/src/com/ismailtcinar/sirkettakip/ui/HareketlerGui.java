package com.ismailtcinar.sirkettakip.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.ismailtcinar.sirkettakip.dao.HareketlerDao;
import com.ismailtcinar.sirkettakip.dao.KasaDao;
import com.ismailtcinar.sirkettakip.domain.HareketlerDomain;
import com.ismailtcinar.sirkettakip.domain.KasaDomain;

import Utils.DigerUtils;

public class HareketlerGui extends JDialog {

	public HareketlerGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		setSize(700, 300);
		setTitle("Hareketler Penceresi");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setResizable(false);
		setVisible(true);
	}

	private JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel solPanel = new JPanel(new BorderLayout()); // Üste solPanel, alta Buton Panel
		JPanel solUstPanel = new JPanel(new GridLayout(2, 1, 5, 5)); // Burada açýklama ve tarih olacak
		JPanel istegeBagliPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		JPanel radioPanel = new JPanel();
		JPanel solAltButonPanel = new JPanel(); // Butonlarýn Geleceði Panel

		solUstPanel.setBackground(Color.white);
		solAltButonPanel.setBackground(Color.white);

		JTextField aciklamaText = new JTextField();
		aciklamaText.setBorder(BorderFactory.createTitledBorder("Açýklama"));

		JTextField tarihText = new JTextField();
		tarihText.setBorder(BorderFactory.createTitledBorder("Tarih"));

		solUstPanel.add(aciklamaText);
		solUstPanel.add(tarihText);

		istegeBagliPanel.setBorder(BorderFactory.createEtchedBorder());

		JLabel kasayaDahilEdilsinMiLabel = new JLabel("Kasaya dahil edilsin");
		JCheckBox kasayaDahilEdilsinCheck = new JCheckBox();
		kasayaDahilEdilsinCheck.setSelected(false);

		JLabel ucretLabel = new JLabel("Ücret");
		JTextField ucretText = new JTextField();

		JLabel hareketLabel = new JLabel("Hareket");
		ButtonGroup grup = new ButtonGroup();
		JRadioButton girisRadio = new JRadioButton("Giriþ");
		JRadioButton cikisRadio = new JRadioButton("Çýkýþ");
		grup.add(girisRadio);
		grup.add(cikisRadio);

		radioPanel.add(girisRadio);
		radioPanel.add(cikisRadio);

		istegeBagliPanel.add(kasayaDahilEdilsinMiLabel);
		istegeBagliPanel.add(kasayaDahilEdilsinCheck);
		istegeBagliPanel.add(ucretLabel);
		istegeBagliPanel.add(ucretText);
		istegeBagliPanel.add(hareketLabel);
		istegeBagliPanel.add(radioPanel);

		ImageIcon ekleIkon = new ImageIcon("icons/ekle_24_Ikon.png");
		JButton kaydetButon = new JButton("Kaydet", ekleIkon);

		ImageIcon geriIkon = new ImageIcon("icons/geri_24_Ikon.png");
		JButton geriButon = new JButton("Geri", geriIkon);

		solAltButonPanel.add(kaydetButon);
		solAltButonPanel.add(geriButon);

		JList hareketlerList = new JList();
		listele(hareketlerList);
		JScrollPane hareketlerPane = new JScrollPane(hareketlerList);
		hareketlerPane.setBorder(BorderFactory.createTitledBorder("Kaydedilmiþ Hareketler"));
		hareketlerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		anaPanel.add(hareketlerPane);

		solPanel.add(solUstPanel, BorderLayout.NORTH);
		solPanel.add(istegeBagliPanel, BorderLayout.CENTER);
		solPanel.add(solAltButonPanel, BorderLayout.SOUTH);

		anaPanel.add(solPanel, BorderLayout.WEST);

		kaydetButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HareketlerDomain eklenecekHareket = new HareketlerDomain();
				KasaDomain eklenecekKasa = new KasaDomain();

				if (kasayaDahilEdilsinCheck.isSelected()) {
					int ucret = Integer.parseInt(ucretText.getText());
					if (tarihText.getText().equals("") || aciklamaText.getText().equals("")
							|| ucretText.getText().equals("") || grup.isSelected(null)) {
						JOptionPane.showMessageDialog(HareketlerGui.this, "Boþ alan býrakmayýn");
					} else {

						if (cikisRadio.isSelected()) {
							eklenecekKasa.setMiktar(-ucret);
							eklenecekHareket.setAciklama(
									aciklamaText.getText() + " -" + String.valueOf(ucretText.getText() + " TL"));
						} else if (girisRadio.isSelected()) {
							eklenecekKasa.setMiktar(ucret);
							eklenecekHareket.setAciklama(
									aciklamaText.getText() + " " + String.valueOf(ucretText.getText() + " TL"));
						}

						eklenecekKasa.setAciklama(aciklamaText.getText());
						eklenecekKasa.setTarih(tarihText.getText());
						KasaDao.ekle(eklenecekKasa);

						eklenecekHareket.setTarih(tarihText.getText());
						HareketlerDao.ekle(eklenecekHareket);
						DigerUtils.alanTemizle(new JTextField[] { tarihText, aciklamaText, ucretText });
						kasayaDahilEdilsinCheck.setSelected(false);
						listele(hareketlerList);
						JOptionPane.showMessageDialog(HareketlerGui.this, "Hareket eklendi");

					}

				} else {
					if (tarihText.getText().equals("") || aciklamaText.getText().equals("")) {
						JOptionPane.showMessageDialog(HareketlerGui.this, "Boþ alan býrakmayýn");

					} else {

						eklenecekHareket.setAciklama(aciklamaText.getText());
						eklenecekHareket.setTarih(tarihText.getText());
						HareketlerDao.ekle(eklenecekHareket);
						DigerUtils.alanTemizle(new JTextField[] { tarihText, aciklamaText });
						listele(hareketlerList);
						JOptionPane.showMessageDialog(HareketlerGui.this, "Hareket eklendi");

					}
				}

			}
		});

		geriButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		return anaPanel;
	}

	private void listele(JList l) {
		l.setListData(HareketlerDao.Listele().toArray());
	}

}
