package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


import moduli.Administrator;
import moduli.Servis;
import net.miginfocom.swing.MigLayout;
import rad_sa_listama.SveListe;

public class GlavniProzorAdmini extends JFrame {

	private String slika_adm_putanja = new String(SlikePutanje.slika_admin);
	private String slika_mus_putanja = new String(SlikePutanje.slika_musterija);
	private String slika_ser_putanja = new String(SlikePutanje.slika_serviser);
	private String slika_aut_putanja = new String(SlikePutanje.slika_auto);
	private String slika_knj_putanja = new String(SlikePutanje.slika_knjizica);
	private String slika_sei_putanja = new String(SlikePutanje.slika_servis);
	private String slika_deo_putanja = new String(SlikePutanje.slika_deo);

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	Date datum =Calendar.getInstance().getTime();
	String danas_string = df.format(datum);
	private JButton btn_vreme = new JButton(danas_string);
	private JButton logout = new JButton("Odjavi me");

	private JButton btn_admini = new JButton("Admini                   ");
	private JButton btn_musterije = new JButton("Musterije          ");
	private JButton btn_serviseri = new JButton("Serviseri               ");
	private JButton btn_automobili = new JButton("Automobili        ");
	private JButton btn_servisne_knjizice = new JButton("Servisne knjizice");
	private JButton btn_servisi = new JButton("Servisi               ");
	private JButton btn_delovi = new JButton("Delovi               ");

	
	private SveListe sv;
	private Administrator admin;
	
	
	public GlavniProzorAdmini(SveListe sv, Administrator admin) {
		this.sv = sv;
		this.admin = admin;
		setResizable(true);
		setSize(650, 400);
		setLocationRelativeTo(null);
		setTitle("Prijavljeni ste kao: " + admin.getIme() + " " + admin.getPrezime());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		Dimension d = new Dimension(650, 400);
		setMinimumSize(d);
		initGUI();
		initActions();
		
	}
	
	public void initGUI() {

		MigLayout ml = new MigLayout();
		setLayout(ml);
		
		ImageIcon slika = new ImageIcon(slika_adm_putanja);
		btn_admini.setIcon(slika);
		ImageIcon slika2 = new ImageIcon(slika_mus_putanja);
		btn_musterije.setIcon(slika2);
		ImageIcon slika3 = new ImageIcon(slika_ser_putanja);
		btn_serviseri.setIcon(slika3);
		ImageIcon slika4 = new ImageIcon(slika_aut_putanja);
		btn_automobili.setIcon(slika4);
		ImageIcon slika5 = new ImageIcon(slika_knj_putanja);
		btn_servisne_knjizice.setIcon(slika5);
		ImageIcon slika6 = new ImageIcon(slika_sei_putanja);
		btn_servisi.setIcon(slika6);
		ImageIcon slika7 = new ImageIcon(slika_deo_putanja);
		btn_delovi.setIcon(slika7);
		
		
		btn_admini.setBorderPainted(false);
		btn_musterije.setBorderPainted(false);
		btn_automobili.setBorderPainted(false);
		btn_serviseri.setBorderPainted(false);
		btn_servisi.setBorderPainted(false);
		btn_servisne_knjizice.setBorderPainted(false);
		btn_delovi.setBorderPainted(false);
		btn_vreme.setBorderPainted(false);
		logout.setBorderPainted(false);
		logout.setFocusPainted(false);
		btn_admini.setFocusPainted(false);
		btn_automobili.setFocusPainted(false);
		btn_serviseri.setFocusPainted(false);
		btn_servisi.setFocusPainted(false);
		btn_servisne_knjizice.setFocusPainted(false);
		btn_musterije.setFocusPainted(false);
		btn_delovi.setFocusPainted(false);
		btn_vreme.setBackground(Color.lightGray);
		btn_vreme.setFocusable(false);

		
		logout.setBackground(Color.lightGray);
		
		add(btn_admini, "grow, push");
		add(btn_musterije, "grow, push");
		add(btn_serviseri, "grow, push, wrap");
		add(btn_vreme, "grow, push");
		add(btn_servisi, "grow, push");
		add(logout, "grow, push, wrap");
		add(btn_automobili, "grow, push");
		add(btn_servisne_knjizice, "grow, push");
		add(btn_delovi, "grow, push");

	}
	
	private void initActions() {
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				GlavniProzorAdmini.this.setVisible(false);
				GlavniProzorAdmini.this.dispose();
				LoginProzor lpr = new LoginProzor(sv);
				lpr.setVisible(true);
				
			}
		});
		
		
		btn_admini.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ATabelaAdministratora ata = new ATabelaAdministratora(sv);
				ata.setVisible(true);
				
			}
		});
		
		btn_serviseri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ATabelaServisera ats = new ATabelaServisera(sv);
				ats.setVisible(true);
				
			}
		});
		
		btn_musterije.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ATabelaMusterija atm = new ATabelaMusterija(sv);
				atm.setVisible(true);
				
			}
		});
		
		btn_servisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ATabelaServisa ats = new ATabelaServisa(sv);
				ats.setVisible(true);
				
			}
		});
		
		btn_automobili.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ATabelaAutomobili ata = new ATabelaAutomobili(sv);
				ata.setVisible(true);
			}
		});
		
		btn_delovi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ATabelaDelova atd = new ATabelaDelova(sv);
				atd.setVisible(true);
				
			}
		});
		
		btn_servisne_knjizice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ATabelaServisnihKnjizica ats = new ATabelaServisnihKnjizica(sv);
				ats.setVisible(true);
				
			}
		});
	}

	}

