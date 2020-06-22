package glavni_prozori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import administratorove_tabele.TabelaAutomobila;
import administratorove_tabele.TabelaMusterija;
import administratorove_tabele.TabelaServisa;
import administratorove_tabele.TabelaServisnihKnjizica;
import crud.UcitajIzFajla;
import moduli.Automobil;
import moduli.Musterija;
import musterijine_tabele.TabelaAutomobilaMusterijina;
import musterijine_tabele.TabelaServisaMusterijina;
import musterijine_tabele.TabelaServisnihKnjizicaMusterijina;

public class GlavniProzorMusterije extends JFrame {

	private JMenuBar mainMenu = new JMenuBar();
	private JMenuItem automobili = new JMenuItem("Vasi Automobili");
	private JMenu servisiMenu = new JMenu("          Servisi          ");
	private JMenuItem servisi_novi = new JMenuItem("Novi Servis");
	private JMenuItem servisi = new JMenuItem("Pregled vasih servisa");
	private JMenuItem servisne_knjizice = new JMenuItem("Vase Servisne Knjizice");
	
	private UcitajIzFajla uf;
	private Musterija musterija;
	
	
	public GlavniProzorMusterije(UcitajIzFajla uf, Musterija musterija) {
		this.uf = uf;
		this.musterija = musterija;
		setSize(500, 200);
		setResizable(true);
		setTitle("Prijavljeni ste kao: " + musterija.getKorisnicko_ime());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	public void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(automobili);
		mainMenu.add(servisne_knjizice);
		mainMenu.add(servisiMenu);
		servisiMenu.add(servisi_novi);
		servisiMenu.add(servisi);




	}

	public void initActions() {
		
		servisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				TabelaServisaMusterijina tabServis = new TabelaServisaMusterijina(uf);
				tabServis.setVisible(true);
				
			}
		});
		
//		servisi_novi.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				TabelaServisaMusterijinaNovi tabServis = new TabelaServisaMusterijinaNovi(uf, musterija);
//				tabServis.setVisible(true);
//				
//			}
//		});
		
		automobili.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				TabelaAutomobilaMusterijina tabAut = new TabelaAutomobilaMusterijina(uf, musterija);
				tabAut.setVisible(true);
				
			}
		});
		
		
		
		servisne_knjizice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TabelaServisnihKnjizicaMusterijina tabSerKnj = new TabelaServisnihKnjizicaMusterijina(uf, musterija);
				tabSerKnj.setVisible(true);
				
			}
		});
	}
}
