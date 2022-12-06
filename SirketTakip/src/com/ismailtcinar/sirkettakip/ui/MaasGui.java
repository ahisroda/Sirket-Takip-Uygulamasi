package com.ismailtcinar.sirkettakip.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import com.ismailtcinar.sirkettakip.dao.IsciDao;
import com.ismailtcinar.sirkettakip.dao.KasaDao;
import com.ismailtcinar.sirkettakip.dao.MaasDao;
import com.ismailtcinar.sirkettakip.domain.IsciDomain;
import com.ismailtcinar.sirkettakip.domain.KasaDomain;
import com.ismailtcinar.sirkettakip.domain.MaasDomain;

import Utils.DigerUtils;

public class MaasGui extends JDialog {

	public MaasGui() {
		initPencere();
	}

	public void initPencere() {
		add(initPanel());
		setTitle("Maaþ Ekraný");
		setSize(750, 280);
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

		JLabel iscilerLabel = new JLabel("Ýþçiler");
		JComboBox iscilerBox = new JComboBox(IsciDao.listele().toArray());
		iscilerBox.setSelectedItem(null);

		JLabel aciklamaLabel = new JLabel("Açýklama");
		JTextField aciklamaText = new JTextField(13);

		JLabel tarihLabel = new JLabel("Tarih");
		JTextField tarihText = new JTextField(13);

		JLabel miktarLabel = new JLabel("Ücret");
		JTextField miktarText = new JTextField(13);

		ImageIcon kaydetIkon = new ImageIcon("icons/save_24_Ikon.png");
		JButton kaydetButon = new JButton("Kaydet", kaydetIkon);
		getRootPane().setDefaultButton(kaydetButon);

		ImageIcon geriIkon = new ImageIcon("icons/geri_24_Ikon.png");
		JButton geriButon = new JButton("Geri", geriIkon);

		solPanel.add(iscilerLabel);
		solPanel.add(iscilerBox);
		solPanel.add(tarihLabel);
		solPanel.add(tarihText);
		solPanel.add(aciklamaLabel);
		solPanel.add(aciklamaText);
		solPanel.add(miktarLabel);
		solPanel.add(miktarText);
		solPanel.add(kaydetButon);
		solPanel.add(geriButon);

		anaPanel.add(solPanel, BorderLayout.WEST);

		JList paraGirisiList = new JList();
		listele(paraGirisiList);
		JScrollPane paraGirisiPane = new JScrollPane(paraGirisiList);
		paraGirisiPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		paraGirisiPane.setBorder(BorderFactory.createTitledBorder("Kaydedilmiþ Maaþlar"));

		JLabel kasaToplamLabel = new JLabel();

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
						|| tarihText.getText().equals("") || iscilerBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(MaasGui.this, "Boþ alan býrakmayýnýz");
				} else {
					IsciDomain gelecekIsciId = (IsciDomain) iscilerBox.getSelectedItem();

					MaasDomain eklenecekMaas = new MaasDomain();
					KasaDomain eklenecekKasa = new KasaDomain();
					int kasaToplami = KasaDao.kasadakiToplamPara();

					int miktar = Integer.parseInt(miktarText.getText());
					String aciklama = aciklamaText.getText();
					String tarih = tarihText.getText();

					eklenecekKasa.setAciklama(aciklama + " " + String.valueOf(gelecekIsciId.getAdSoyad()));
					eklenecekKasa.setMiktar(-miktar);
					eklenecekKasa.setTarih(tarih);
					KasaDao.ekle(eklenecekKasa);

					eklenecekMaas.setIsciId(gelecekIsciId.getId());
					eklenecekMaas.setAciklama(aciklama);
					eklenecekMaas.setMiktar(miktar);
					eklenecekMaas.setTarih(tarih);
					MaasDao.ekle(eklenecekMaas);
					listele(paraGirisiList);
					JOptionPane.showMessageDialog(MaasGui.this, "Maaþ kaydý gerçekleþti");
					DigerUtils.alanTemizle(new JTextField[] { aciklamaText, miktarText, tarihText });
					iscilerBox.setSelectedItem(null);

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
		l.setListData(MaasDao.listele().toArray());
	}

}
