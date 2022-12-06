package com.ismailtcinar.sirkettakip.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.FontUIResource;

import com.ismailtcinar.sirkettakip.dao.AktifKullaniciDao;
import com.ismailtcinar.sirkettakip.dao.BorcOdeDao;
import com.ismailtcinar.sirkettakip.dao.BorcTanimlaDao;
import com.ismailtcinar.sirkettakip.dao.HareketlerDao;
import com.ismailtcinar.sirkettakip.dao.IsciDao;
import com.ismailtcinar.sirkettakip.dao.KasaDao;
import com.ismailtcinar.sirkettakip.dao.KullaniciDao;
import com.ismailtcinar.sirkettakip.dao.MaasDao;
import com.ismailtcinar.sirkettakip.dao.MalzemeDao;
import com.ismailtcinar.sirkettakip.dao.OdenmisBorclarDao;
import com.ismailtcinar.sirkettakip.dao.StokDao;
import com.ismailtcinar.sirkettakip.domain.AktifKullaniciDomain;
import com.ismailtcinar.sirkettakip.domain.KullaniciDomain;

import Utils.SiniflaraErisim;

public class AnaEkranGui extends JFrame {

	public AnaEkranGui() {
		initPencere();
	}

	private void initPencere() {

		ImageIcon anaIcon = new ImageIcon("icons/isletme48.png");
		setIconImage(anaIcon.getImage());
		add(initPanel());
		setJMenuBar(initMenu());
		setSize(850, 450);
		setTitle("Þirket Yönetimi");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	private JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel ustPanel = new JPanel(new BorderLayout());
		JPanel ustModulPanel = new JPanel();
		JPanel sagPanel = new JPanel(new BorderLayout());
		JPanel sagAltPanel = new JPanel(new GridLayout(2, 1, 5, 5));

		BorcTanimlaDao.durumaGoreSil();

		ImageIcon hareketIcon = new ImageIcon("modulikon/hareket48.png");
		JButton hareketlerPaneli = new JButton("Hareketler Paneli", hareketIcon);

		ImageIcon stokIcon = new ImageIcon("modulikon/stok48.png");
		JButton stokPaneli = new JButton("Stok Paneli", stokIcon);

		ImageIcon maasIcon = new ImageIcon("modulikon/maas48.png");
		JButton maasPaneli = new JButton("Maaþ Paneli", maasIcon);

		ImageIcon kasaIcon = new ImageIcon("modulikon/kasa48.png");
		JButton kasaPaneli = new JButton("Kasa Paneli", kasaIcon);

		ImageIcon borcIcon = new ImageIcon("modulikon/borc48.png");
		JButton borcPaneli = new JButton("Borç Öde", borcIcon);

		JList hareketlerList = new JList();
		listele(hareketlerList);
		JScrollPane hareketlerPane = new JScrollPane(hareketlerList);
		hareketlerPane.setBorder(BorderFactory.createTitledBorder("Bugünün Hareketleri"));
		hareketlerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		anaPanel.add(hareketlerPane);

		ustModulPanel.add(hareketlerPaneli);
		ustModulPanel.add(stokPaneli);
		ustModulPanel.add(maasPaneli);
		ustModulPanel.add(kasaPaneli);
		ustModulPanel.add(borcPaneli);
		ustPanel.add(ustModulPanel);

		AktifKullaniciDomain aktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();
		KullaniciDomain gelenAd = KullaniciDao.kullaniciAdiGetir(aktifKullanici.getAktifKullaniciId());
		JLabel kullaniciAdiLabel = new JLabel("Hoþgeldiniz " + gelenAd.getAdSoyad(), JLabel.CENTER);
		ustPanel.add(kullaniciAdiLabel, BorderLayout.NORTH);

		anaPanel.add(ustPanel, BorderLayout.NORTH);

		JLabel itc = new JLabel("Made by Ýsmail Turan Çýnar - 2022®");
		itc.setFont(new FontUIResource(getName(), ABORT, 10));
		anaPanel.add(itc, BorderLayout.SOUTH);

		ImageIcon cikisIcon = new ImageIcon("modulikon/exitIkon.png");
		JButton cikisButton = new JButton("Çýkýþ", cikisIcon);

		ImageIcon yenileIcon = new ImageIcon("modulikon/yenile32.png");
		JButton yenileButon = new JButton("Yenile", yenileIcon);

		sagAltPanel.add(yenileButon);
		sagAltPanel.add(cikisButton);

		sagPanel.add(sagAltPanel, BorderLayout.SOUTH);
		anaPanel.add(sagPanel, BorderLayout.EAST);

		maasPaneli.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.maasEkrani();

			}
		});

		kasaPaneli.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.kasaEkrani();

			}
		});

		stokPaneli.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.stokEkrani();

			}
		});

		hareketlerPaneli.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.hareketlerEkrani();

			}
		});

		borcPaneli.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.borcOdemeEkrani();
			}
		});

		yenileButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listele(hareketlerList);

			}
		});

		cikisButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AktifKullaniciDao.sil();
				System.exit(0);

			}
		});

		return anaPanel;
	}

	private JMenuBar initMenu() {
		JMenuBar bar = new JMenuBar();

		JMenu tanimlamalarMenu = new JMenu("Tanýmlamalar");
		JMenu profilAyarlariMenu = new JMenu("Profil Ayarlarý");
		JMenu programAyariMenu = new JMenu("Program Ayarý");

		ImageIcon isciTanimlamaIcon = new ImageIcon("modulikon/isciTanimla16.png");
		JMenuItem isciTanimlaItem = new JMenuItem("Ýþçi Tanýmla", isciTanimlamaIcon);

		ImageIcon malzemeTanimlamaIcon = new ImageIcon("modulikon/malzemeTanimla16.png");
		JMenuItem malzemeTanimlaItem = new JMenuItem("Malzeme Tanýmla", malzemeTanimlamaIcon);

		ImageIcon borcTanimlamaIcon = new ImageIcon("modulikon/borc16.png");
		JMenuItem borcTanimlaItem = new JMenuItem("Borç Tanýmla", borcTanimlamaIcon);

		tanimlamalarMenu.add(isciTanimlaItem);
		tanimlamalarMenu.add(malzemeTanimlaItem);
		tanimlamalarMenu.add(borcTanimlaItem);

		ImageIcon hesapSilIcon = new ImageIcon("modulikon/hesapSil16.png");
		JMenuItem hesapSilItem = new JMenuItem("Hesap Sil", hesapSilIcon);

		ImageIcon sifreDegistirIcon = new ImageIcon("modulikon/sifreDegistir16.png");
		JMenuItem sifreDegistirItem = new JMenuItem("Þifre Deðiþtir", sifreDegistirIcon);

		profilAyarlariMenu.add(hesapSilItem);
		profilAyarlariMenu.add(sifreDegistirItem);

		ImageIcon programiSifirlaIcon = new ImageIcon("modulikon/sifirla16.png");
		JMenuItem programiSifirlaItem = new JMenuItem("Programý Sýfýrla", programiSifirlaIcon);

		programAyariMenu.add(programiSifirlaItem);

		isciTanimlaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.isciTanimlamaEkrani();

			}
		});

		malzemeTanimlaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.malzemeTanimlamaEkrani();

			}
		});

		borcTanimlaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.borcTanimlaEkrani();

			}
		});

		programiSifirlaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int sorgu1 = JOptionPane.showConfirmDialog(AnaEkranGui.this,
						"Programdaki tüm verileri silmek istiyor musunuz?");

				if (sorgu1 == 0) {
					int sorgu2 = JOptionPane.showConfirmDialog(AnaEkranGui.this, "Buna emin misiniz?");
					if (sorgu2 == 0) {
						HareketlerDao.hareketlerTablosunuSil();
						IsciDao.isciTablosunuSil();
						KasaDao.kasaTablosunuSil();
						StokDao.stokTablosunuSil();
						MalzemeDao.malzemeTablosunuSil();
						MaasDao.maasTablosunuSil();
						BorcTanimlaDao.borcTanimlaTablosunuSil();
						BorcOdeDao.borcOdeTablosunuSil();
						OdenmisBorclarDao.odenmisBorclarTablosunuSil();
						JOptionPane.showMessageDialog(AnaEkranGui.this, "Tüm veriler silinmiþtir");

					}
				}

			}
		});

		hesapSilItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.hesapSilEkrani();

			}
		});

		sifreDegistirItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.sifreDegistirEkrani();

			}
		});

		bar.add(tanimlamalarMenu);
		bar.add(profilAyarlariMenu);
		bar.add(programAyariMenu);

		return bar;
	}

	private void listele(JList l) {
		Date tarih = new Date();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String simdikiTarih = format.format(tarih);
		l.setListData(HareketlerDao.buGununHareketleriListele(simdikiTarih).toArray());
	}
}
