package rad_sa_listama;

import gui.LoginProzor;
import moduli.Automobil;
import moduli.Musterija;
import moduli.ServisnaKnjizica;

public class Main {

	public static void main(String[] args) {

		SveListe sv = new SveListe();
		sv.ucitajMusterije("C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\musterije.txt");
		sv.UcitajAdministratore("C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\administratori.txt");
		sv.ucitajServisnaKnjizice("C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\servisneknjizice.txt");
		sv.ucitajAutomobile("C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\automobili.txt");
		sv.ucitajServisere("C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\serviseri.txt");
		sv.ucitajServise("C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\servisi.txt");
		sv.ucitajDelove("C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\delovi.txt");
		
		for(ServisnaKnjizica sk : sv.getServisne_knjizice()) {
			for(Automobil a : sv.getAutomobili()) {
				if(a.getServisna_knjizica().getId() == sk.getId()) {
					a.setServisna_knjizica(sk);
				}
			}
		}
		
		
		LoginProzor lp = new LoginProzor(sv);

		lp.setVisible(true);
		sv.upisiSve();
		
	}

}
