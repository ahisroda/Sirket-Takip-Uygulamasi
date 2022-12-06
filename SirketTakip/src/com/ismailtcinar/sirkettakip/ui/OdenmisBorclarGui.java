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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.ismailtcinar.sirkettakip.dao.BorcOdeDao;
import com.ismailtcinar.sirkettakip.dao.BorcTanimlaDao;
import com.ismailtcinar.sirkettakip.dao.OdenmisBorclarDao;
import com.ismailtcinar.sirkettakip.domain.BorcOdeDomain;
import com.ismailtcinar.sirkettakip.domain.BorcTanimlaDomain;

public class OdenmisBorclarGui extends JDialog {

	public OdenmisBorclarGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		setSize(500, 400);
		// pack();
		setTitle("Ödenmiþ Borçlar");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}

	private JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());

		//borç, ödenmiþ borçlara girdikten sonra borçtanýmlamadaki durumu 2 olanlar silinsin
		
		JList odenmisBorclarList = new JList();
		odenmisBorclarList.setListData(OdenmisBorclarDao.odenmisBorclariListele().toArray());
		JScrollPane odenmiBorclarPane = new JScrollPane(odenmisBorclarList);
		odenmiBorclarPane.setBorder(BorderFactory.createTitledBorder("Ödenmiþ Borçlar"));
		odenmiBorclarPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		anaPanel.add(odenmiBorclarPane);

		return anaPanel;

	}

}
