package Utils;

import javax.swing.SwingUtilities;

import com.ismailtcinar.sirkettakip.ui.AnaEkranGui;
import com.ismailtcinar.sirkettakip.ui.BorcOdeGui;
import com.ismailtcinar.sirkettakip.ui.BorcTanimlaGui;
import com.ismailtcinar.sirkettakip.ui.GirisGui;
import com.ismailtcinar.sirkettakip.ui.HareketlerGui;
import com.ismailtcinar.sirkettakip.ui.HesapSilGui;
import com.ismailtcinar.sirkettakip.ui.IsciTanimlaGui;
import com.ismailtcinar.sirkettakip.ui.KasaGui;
import com.ismailtcinar.sirkettakip.ui.MaasGui;
import com.ismailtcinar.sirkettakip.ui.MalzemeTanimlaGui;
import com.ismailtcinar.sirkettakip.ui.OdenmisBorclarGui;
import com.ismailtcinar.sirkettakip.ui.SifreDegistirGui;
import com.ismailtcinar.sirkettakip.ui.SifremiUnuttumGui;
import com.ismailtcinar.sirkettakip.ui.StokGui;
import com.ismailtcinar.sirkettakip.ui.YeniHesapOlusturGui;

public class SiniflaraErisim {

	public static void girisEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GirisGui();

			}
		});
	}

	public static void sifremiUnuttumEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SifremiUnuttumGui();

			}
		});
	}

	public static void yeniHesapOlusturEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new YeniHesapOlusturGui();

			}
		});
	}

	public static void anaEkran() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new AnaEkranGui();

			}
		});
	}

	public static void isciTanimlamaEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new IsciTanimlaGui();

			}
		});
	}

	public static void maasEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MaasGui();

			}
		});
	}

	public static void kasaEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new KasaGui();

			}
		});
	}

	public static void malzemeTanimlamaEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MalzemeTanimlaGui();

			}
		});
	}

	public static void stokEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new StokGui();

			}
		});
	}

	public static void hareketlerEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new HareketlerGui();

			}
		});
	}

	public static void hesapSilEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new HesapSilGui();

			}
		});
	}

	public static void sifreDegistirEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SifreDegistirGui();

			}
		});
	}

	public static void borcTanimlaEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new BorcTanimlaGui();

			}
		});
	}

	public static void borcOdemeEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new BorcOdeGui();

			}
		});
	}

	public static void odenmisBorclarEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new OdenmisBorclarGui();

			}
		});
	}
}
