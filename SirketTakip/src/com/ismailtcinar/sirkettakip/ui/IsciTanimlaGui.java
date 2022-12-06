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

import com.ismailtcinar.sirkettakip.dao.IsciDao;
import com.ismailtcinar.sirkettakip.domain.IsciDomain;

import Utils.DigerUtils;

public class IsciTanimlaGui extends JDialog {

	public IsciTanimlaGui() {
		initPencere();
	}

	public void initPencere() {
		add(initPanel());
		setTitle("Ýþçi Tanýmla");
		setSize(300, 500);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE); // bu pencere en önde olacak ve alta asla alýnamayacak
		setResizable(false);
		setVisible(true);
	}

	public JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel ustPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		JPanel ortaPanel = new JPanel(new BorderLayout());
		JPanel altPanel = new JPanel(new GridLayout(2, 2, 5, 5));

		JLabel adiSoyadiLabel = new JLabel("Adý Soyadý");
		JTextField adiSoyadiText = new JTextField(20);

		JLabel telNoLabel = new JLabel("Telefon No");
		JTextField telNoText = new JTextField(20);

		JLabel tcKimlikNoLabel = new JLabel("Tc Kimlik No");
		JTextField tcKimlikNoText = new JTextField(20);

		JTextField araText = new JTextField(20);
		araText.setBorder(BorderFactory.createTitledBorder("Ara"));

		ustPanel.add(adiSoyadiLabel);
		ustPanel.add(adiSoyadiText);
		ustPanel.add(telNoLabel);
		ustPanel.add(telNoText);
		ustPanel.add(tcKimlikNoLabel);
		ustPanel.add(tcKimlikNoText);

		anaPanel.add(ustPanel, BorderLayout.NORTH);

		JList iscilerList = new JList();
		listeyiGuncelle(iscilerList);
		JScrollPane iscilerPane = new JScrollPane(iscilerList);
		iscilerPane.setBorder(BorderFactory.createTitledBorder("Ýþçiler"));
		iscilerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

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
		ortaPanel.add(iscilerPane);

		anaPanel.add(ortaPanel);

		iscilerList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				IsciDomain secilenIsci = (IsciDomain) iscilerList.getSelectedValue();

				if (secilenIsci != null) {
					adiSoyadiText.setText(secilenIsci.getAdSoyad());
					telNoText.setText(secilenIsci.getTelno());
					tcKimlikNoText.setText(secilenIsci.getTcKimlik());
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
				iscilerList.setListData(IsciDao.bul(araText.getText()).toArray());

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		ekleButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				IsciDomain eklenecekIsci = new IsciDomain();
				boolean isciMevcudiyetKontrol = IsciDao.mevcutIscýVarMi(tcKimlikNoText.getText());

				if (adiSoyadiText.getText().equals("") || adiSoyadiText.getText().equals("")) {
					JOptionPane.showMessageDialog(IsciTanimlaGui.this, "Boþ alan býrakmayýnýz");
				} else {

					if (isciMevcudiyetKontrol == false) {
						eklenecekIsci.setAdSoyad(adiSoyadiText.getText());
						eklenecekIsci.setTelno(telNoText.getText());
						eklenecekIsci.setTcKimlik(tcKimlikNoText.getText());
						IsciDao.ekle(eklenecekIsci);
						DigerUtils.alanTemizle(new JTextField[] { adiSoyadiText, tcKimlikNoText, telNoText });
						listeyiGuncelle(iscilerList);
					} else {
						JOptionPane.showMessageDialog(IsciTanimlaGui.this, "Bu kimlikle iþçi kayýtlýdýr");
					}

				}

			}
		});

		silButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				IsciDomain silinecekIsci = (IsciDomain) iscilerList.getSelectedValue();

				if (silinecekIsci != null) {
					int sorgu = JOptionPane.showConfirmDialog(IsciTanimlaGui.this,
							"Bu iþçiyi silerseniz, iþçiye dair tüm kayýtlar da silinecektir\n"
									+ "Silmek istiyor musunuz?");

					if (sorgu == 0) {
						IsciDao.sil(silinecekIsci);
						DigerUtils.alanTemizle(new JTextField[] { adiSoyadiText, tcKimlikNoText, telNoText });
						JOptionPane.showMessageDialog(IsciTanimlaGui.this, "Ýþçi kaydý silindi");
						listeyiGuncelle(iscilerList);
					}
				}

			}
		});

		duzenleButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				IsciDomain duzenlenecekIsci = (IsciDomain) iscilerList.getSelectedValue();

				if (duzenlenecekIsci != null) {
					duzenlenecekIsci.setAdSoyad(adiSoyadiText.getText());
					duzenlenecekIsci.setTcKimlik(tcKimlikNoText.getText());
					duzenlenecekIsci.setTelno(telNoText.getText());

					IsciDao.duzenle(duzenlenecekIsci);
					DigerUtils.alanTemizle(new JTextField[] { adiSoyadiText, tcKimlikNoText, telNoText });
					listeyiGuncelle(iscilerList);

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
		l.setListData(IsciDao.listele().toArray());
	}

}
