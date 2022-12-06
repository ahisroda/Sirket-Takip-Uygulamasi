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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ismailtcinar.sirkettakip.dao.KasaDao;
import com.ismailtcinar.sirkettakip.domain.KasaDomain;

import Utils.DigerUtils;

public class KasaGui extends JDialog {

	public KasaGui() {
		initPencere();
	}

	public void initPencere() {
		add(initPanel());
		setTitle("Kasa Yönetimi");
		setSize(800, 280);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setResizable(false);
		setVisible(true);
	}

	public JPanel initPanel() {

		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel solPanel = new JPanel(new GridLayout(5, 2, 5, 5));
		JPanel sagPanel = new JPanel(new BorderLayout());
		JPanel sagUstJPanel = new JPanel();
		JPanel radioPanel = new JPanel();

		JLabel aciklamaLabel = new JLabel("Açýklama");
		JTextField aciklamaText = new JTextField(12);

		JLabel miktarLabel = new JLabel("Ücret");
		JTextField miktarText = new JTextField(12);

		JLabel tarihLabel = new JLabel("Tarih");
		JTextField tarihText = new JTextField(12);

		JLabel hareketLabel = new JLabel("Hareket:");

		ButtonGroup radioGrup = new ButtonGroup();
		JRadioButton girisRadio = new JRadioButton("Giriþ");
		JRadioButton cikisRadio = new JRadioButton("Çýkýþ");
		radioGrup.add(girisRadio);
		radioGrup.add(cikisRadio);

		radioPanel.add(girisRadio);
		radioPanel.add(cikisRadio);

		ImageIcon kaydetIkon = new ImageIcon("icons/save_24_Ikon.png");
		JButton kaydetButon = new JButton("Ekle", kaydetIkon);
		getRootPane().setDefaultButton(kaydetButon);

		ImageIcon geriIkon = new ImageIcon("icons/geri_24_Ikon.png");
		JButton geriButon = new JButton("Geri", geriIkon);

		solPanel.add(aciklamaLabel);
		solPanel.add(aciklamaText);
		solPanel.add(miktarLabel);
		solPanel.add(miktarText);
		solPanel.add(tarihLabel);
		solPanel.add(tarihText);
		solPanel.add(hareketLabel);
		solPanel.add(radioPanel);
		solPanel.add(kaydetButon);
		solPanel.add(geriButon);

		anaPanel.add(solPanel, BorderLayout.WEST);

		JList paraGirisiList = new JList();
		listele(paraGirisiList);
		JScrollPane paraGirisiPane = new JScrollPane(paraGirisiList);
		paraGirisiPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		paraGirisiPane.setBorder(BorderFactory.createTitledBorder("Kaydedilmiþ Giriþler"));

		JLabel kasaToplamLabel = new JLabel();
		kasaToplamiGuncelle(kasaToplamLabel);

		sagUstJPanel.add(kasaToplamLabel);

		sagPanel.add(sagUstJPanel, BorderLayout.NORTH);
		sagPanel.add(paraGirisiPane);
		anaPanel.add(sagPanel);

		paraGirisiList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

			}
		});

		kaydetButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (aciklamaText.getText().equals("") || miktarText.getText().equals("")
						|| tarihText.getText().equals("") || radioGrup.isSelected(null)) {
					JOptionPane.showMessageDialog(KasaGui.this, "Boþ alan býrakmayýnýz");
				} else {
					KasaDomain eklenecekPara = new KasaDomain();
					int kasaToplami = KasaDao.kasadakiToplamPara();

					int miktar = Integer.parseInt(miktarText.getText());
					String aciklama = aciklamaText.getText();
					String tarih = tarihText.getText();
					if (girisRadio.isSelected()) {
						eklenecekPara.setAciklama(aciklama);
						eklenecekPara.setMiktar(miktar);
						eklenecekPara.setTarih(tarih);
						KasaDao.ekle(eklenecekPara);
						listele(paraGirisiList);
						kasaToplamiGuncelle(kasaToplamLabel);

						JOptionPane.showMessageDialog(KasaGui.this, "Para kasaya eklendi");
						DigerUtils.alanTemizle(new JTextField[] { aciklamaText, miktarText, tarihText });
					} else if (cikisRadio.isSelected()) {
						if (miktar > kasaToplami) {
							JOptionPane.showMessageDialog(KasaGui.this, "Kasada çekeceðiniz miktarda para yoktur");
						} else {

							eklenecekPara.setAciklama(aciklama);
							eklenecekPara.setMiktar(-miktar);
							eklenecekPara.setTarih(tarih);
							KasaDao.ekle(eklenecekPara);
							listele(paraGirisiList);
							kasaToplamiGuncelle(kasaToplamLabel);

							JOptionPane.showMessageDialog(KasaGui.this, "Para kasadan çýkarýldý");
							DigerUtils.alanTemizle(new JTextField[] { aciklamaText, miktarText, tarihText });
						}
					}

					/*
					 * if (aciklamaText.getText().equals("") || miktarText.getText().equals("") ||
					 * tarihText.getText().equals("") || radioGrup.isSelected(null)) {
					 * JOptionPane.showMessageDialog(KasaGui.this, "Boþ alan býrakmayýnýz"); } else
					 * {
					 * 
					 * 
					 * }
					 */
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
		l.setListData(KasaDao.listele().toArray());
	}

	private void kasaToplamiGuncelle(JLabel b) {

		int toplamKasa = KasaDao.kasadakiToplamPara();
		b.setText("Toplam Para: " + String.valueOf(toplamKasa) + " TL");
	}

}
