package com.ismailtcinar.sirkettakip.ui;

import java.awt.BorderLayout;
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
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ismailtcinar.sirkettakip.dao.BorcOdeDao;
import com.ismailtcinar.sirkettakip.dao.BorcTanimlaDao;
import com.ismailtcinar.sirkettakip.domain.BorcTanimlaDomain;

import Utils.DigerUtils;

public class BorcTanimlaGui extends JDialog {

	public BorcTanimlaGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		setSize(600, 300);
		setTitle("Borç Tanýmla");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}

	private JPanel initPanel() {

		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel solPanel = new JPanel(new GridLayout(6, 2, 5, 5));
		JPanel sagPanel = new JPanel(new BorderLayout());

		BorcTanimlaDao.durumaGoreSil();

		JLabel kimeLabel = new JLabel("Kime ");
		JTextField kimeText = new JTextField(10);

		JLabel borcLabel = new JLabel("Borç Miktarý");
		JTextField borcText = new JTextField(10);

		JLabel tarihLabel = new JLabel("Tarih");
		JTextField tarihText = new JTextField(10);

		ImageIcon ekleIcon = new ImageIcon("icons/ekle_24_Ikon.png");
		JButton ekleButon = new JButton("Ekle", ekleIcon);
		getRootPane().setDefaultButton(ekleButon);

		ImageIcon silIcon = new ImageIcon("icons/sil_24_Ikon.png");
		JButton silButon = new JButton("Sil", silIcon);

		ImageIcon geriIcon = new ImageIcon("icons/geri_24_Ikon.png");
		JButton geriButon = new JButton("Geri", geriIcon);

		solPanel.add(kimeLabel);
		solPanel.add(kimeText);
		solPanel.add(borcLabel);
		solPanel.add(borcText);
		solPanel.add(tarihLabel);
		solPanel.add(tarihText);
		solPanel.add(ekleButon);
		solPanel.add(silButon);
		solPanel.add(geriButon);

		JTextField araText = new JTextField();
		araText.setBorder(BorderFactory.createTitledBorder("Borç sahibi ara"));

		JList borclarList = new JList();
		listele(borclarList);
		JScrollPane borclarPane = new JScrollPane(borclarList);

		sagPanel.add(borclarPane);
		sagPanel.add(araText, BorderLayout.NORTH);

		anaPanel.add(solPanel, BorderLayout.WEST);
		anaPanel.add(sagPanel);

		ekleButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				BorcTanimlaDomain eklenecekBorc = new BorcTanimlaDomain();

				if (kimeText.getText().equals("") || borcText.getText().equals("") || tarihText.getText().equals("")) {
					JOptionPane.showMessageDialog(BorcTanimlaGui.this, "Boþ alan býrakmayýnýz");
				} else {
					eklenecekBorc.setBorc(Integer.parseInt(borcText.getText()));
					eklenecekBorc.setKime(kimeText.getText());
					eklenecekBorc.setTarih(tarihText.getText());
					eklenecekBorc.setDurum(1);

					BorcTanimlaDao.ekle(eklenecekBorc);
					JOptionPane.showMessageDialog(BorcTanimlaGui.this, "Borç eklenmiþtir, Allah kolaylýk versin...");
					DigerUtils.alanTemizle(new JTextField[] { kimeText, borcText, tarihText });
					listele(borclarList);
				}

			}
		});

		silButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				BorcTanimlaDomain silinecekBorc = (BorcTanimlaDomain) borclarList.getSelectedValue();

				if (silinecekBorc != null) {
					BorcTanimlaDao.sil(silinecekBorc);
					JOptionPane.showMessageDialog(BorcTanimlaGui.this, "Borç, kayýttan silinmiþtir");
					DigerUtils.alanTemizle(new JTextField[] { kimeText, borcText, tarihText });
					listele(borclarList);
				} else {
					JOptionPane.showMessageDialog(BorcTanimlaGui.this, "Lütfen seçim yapýn");
				}

			}
		});

		borclarList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				BorcTanimlaDomain seciliBorc = (BorcTanimlaDomain) borclarList.getSelectedValue();

				if (seciliBorc != null) {
					kimeText.setText(seciliBorc.getKime());
					borcText.setText(String.valueOf(seciliBorc.getBorc()));
					tarihText.setText(seciliBorc.getTarih());
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
				borclarList.setListData(BorcTanimlaDao.bul(araText.getText()).toArray());

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

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
		l.setListData(BorcTanimlaDao.listele().toArray());

	}

}
