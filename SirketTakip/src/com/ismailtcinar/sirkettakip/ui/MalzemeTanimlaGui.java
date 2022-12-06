package com.ismailtcinar.sirkettakip.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ismailtcinar.sirkettakip.dao.MalzemeDao;
import com.ismailtcinar.sirkettakip.domain.MalzemeDomain;

import Utils.DigerUtils;

public class MalzemeTanimlaGui extends JDialog {

	public MalzemeTanimlaGui() {
		initPencere();
	}

	public void initPencere() {
		add(initPanel());
		setTitle("Malzeme Tanýmla");
		setSize(300, 500);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE); // bu pencere en önde olacak ve alta asla alýnamayacak
		setResizable(false);
		setVisible(true);
	}

	public JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel ustPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		JPanel ortaPanel = new JPanel(new BorderLayout());
		JPanel altPanel = new JPanel(new GridLayout(2, 2, 5, 5));

		JLabel malzemeAdiLabel = new JLabel("Malzeme Adý");
		JTextField malzemeAdiText = new JTextField(20);

		JLabel malzemeNoLabel = new JLabel("Malzeme No");
		JTextField malzemeNoText = new JTextField(20);

		JTextField araText = new JTextField(20);
		araText.setBorder(BorderFactory.createTitledBorder("Ara"));

		ustPanel.add(malzemeAdiLabel);
		ustPanel.add(malzemeAdiText);
		ustPanel.add(malzemeNoLabel);
		ustPanel.add(malzemeNoText);

		anaPanel.add(ustPanel, BorderLayout.NORTH);

		JList malzemelerList = new JList();
		listeyiGuncelle(malzemelerList);
		JScrollPane urunlerPane = new JScrollPane(malzemelerList);
		urunlerPane.setBorder(BorderFactory.createTitledBorder("Malzemeler"));
		urunlerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		ImageIcon ekleIkon = new ImageIcon("icons/ekle_24_Ikon.png");
		JButton ekleButon = new JButton("Ekle", ekleIkon);
		getRootPane().setDefaultButton(ekleButon);

		ImageIcon silIkon = new ImageIcon("icons/sil_24_Ikon.png");
		JButton silButon = new JButton("Sil", silIkon);

		ImageIcon duzenleIkon = new ImageIcon("icons/edit_24_Ikon.png");
		JButton duzenleButon = new JButton("Düzenle", duzenleIkon);

		ImageIcon geriIkon = new ImageIcon("icons/geri_24_Ikon.png");
		JButton geriButon = new JButton("Geri", geriIkon);

		altPanel.add(ekleButon);
		altPanel.add(silButon);
		altPanel.add(duzenleButon);
		altPanel.add(geriButon);

		anaPanel.add(altPanel, BorderLayout.SOUTH);

		ortaPanel.add(araText, BorderLayout.NORTH);
		ortaPanel.add(urunlerPane);

		anaPanel.add(ortaPanel);

		malzemelerList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				MalzemeDomain secilenMalzeme = (MalzemeDomain) malzemelerList.getSelectedValue();

				if (secilenMalzeme != null) {
					malzemeAdiText.setText(secilenMalzeme.getAdi());
					malzemeNoText.setText(secilenMalzeme.getMalzemeNo());
				}

			}
		});

		araText.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				malzemelerList.setListData(MalzemeDao.bul(araText.getText()).toArray());

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		ekleButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				MalzemeDomain eklenecekMalzeme = new MalzemeDomain();

				if (malzemeAdiText.getText().equals("") || malzemeAdiText.getText().equals("")) {
					JOptionPane.showMessageDialog(MalzemeTanimlaGui.this, "Boþ alan býrakmayýnýz");
				} else {
					eklenecekMalzeme.setAdi(malzemeAdiText.getText());
					eklenecekMalzeme.setMalzemeNo(malzemeNoText.getText());
					MalzemeDao.ekle(eklenecekMalzeme);
					DigerUtils.alanTemizle(new JTextField[] { malzemeAdiText, malzemeNoText });
					listeyiGuncelle(malzemelerList);
				}

			}
		});

		silButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				MalzemeDomain silinecekMalzeme = (MalzemeDomain) malzemelerList.getSelectedValue();

				if (silinecekMalzeme != null) {
					int sorgu = JOptionPane.showConfirmDialog(MalzemeTanimlaGui.this,
							"Bu malzemeyi silerseniz, malzemeye dair stok kayýtlarý da silinecektir\n"
									+ "Silmek istiyor musunuz?");

					if (sorgu == 0) {
						MalzemeDao.sil(silinecekMalzeme);
						DigerUtils.alanTemizle(new JTextField[] { malzemeAdiText, malzemeNoText });
						JOptionPane.showMessageDialog(MalzemeTanimlaGui.this, "Malzeme silindi");
						listeyiGuncelle(malzemelerList);
					}

				}

			}
		});

		duzenleButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MalzemeDomain duzenlenecekMalzeme = (MalzemeDomain) malzemelerList.getSelectedValue();

				if (duzenlenecekMalzeme != null) {
					duzenlenecekMalzeme.setAdi(malzemeAdiText.getText());
					duzenlenecekMalzeme.setMalzemeNo(malzemeNoText.getText());

					MalzemeDao.duzenle(duzenlenecekMalzeme);
					DigerUtils.alanTemizle(new JTextField[] { malzemeAdiText, malzemeNoText });
					listeyiGuncelle(malzemelerList);

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

	private void listeyiGuncelle(JList l) {
		l.setListData(MalzemeDao.listele().toArray());
	}

}
