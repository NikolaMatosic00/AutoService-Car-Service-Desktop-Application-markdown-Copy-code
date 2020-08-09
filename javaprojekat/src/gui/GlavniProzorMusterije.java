package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import moduli.Musterija;
import net.miginfocom.swing.MigLayout;
import rad_sa_listama.SveListe;

public class GlavniProzorMusterije extends JFrame {

	private String slika_aut_putanja = new String(SlikePutanje.slika_auto);
	private String slika_knj_putanja = new String(SlikePutanje.slika_knjizica);
	private String slika_sei_putanja = new String(SlikePutanje.slika_servis);
	
	private JButton btn_automobili = new JButton("Automobili        ");
	private JButton btn_servisne_knjizice = new JButton("Servisne knjizice");
	private JButton btn_servisi = new JButton("Servisi               ");
	private JButton logout = new JButton("Odjavi me");

	
	private SveListe sv;
	private Musterija musterija;
	
	
	public GlavniProzorMusterije(SveListe sv, Musterija musterija) {
		this.sv = sv;
		this.musterija = musterija;
		setSize(350, 350);
		setResizable(true);
		setTitle("Prijavljeni ste kao: " + musterija.getIme() + " " + musterija.getPrezime());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(350, 350));
		GUIMenu();
		initMenu();
		
	}
	
	private void GUIMenu() {
		MigLayout ml = new MigLayout();
		setLayout(ml);
		
		ImageIcon slika4 = new ImageIcon(slika_aut_putanja);
		btn_automobili.setIcon(slika4);
		ImageIcon slika5 = new ImageIcon(slika_knj_putanja);
		btn_servisne_knjizice.setIcon(slika5);
		ImageIcon slika6 = new ImageIcon(slika_sei_putanja);
		btn_servisi.setIcon(slika6);
		
		btn_servisi.setBorderPainted(false);
		btn_servisne_knjizice.setBorderPainted(false);
		btn_automobili.setBorderPainted(false);

		btn_servisi.setFocusPainted(false);
		btn_servisne_knjizice.setFocusPainted(false);
		btn_automobili.setFocusPainted(false);
		
		add(btn_automobili, "grow, push, wrap");
		add(btn_servisne_knjizice, "grow, push, wrap");
		add(btn_servisi, "grow, push, wrap");
		add(logout, "grow, push");

	}
	
	private void initMenu(){
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				GlavniProzorMusterije.this.setVisible(false);
				GlavniProzorMusterije.this.dispose();
				LoginProzor lpr = new LoginProzor(sv);
				lpr.setVisible(true);
				
			}
		});
		
		btn_automobili.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				MTabelaAutomobila tabAut = new MTabelaAutomobila(sv, musterija);
				tabAut.setVisible(true);
				
			}
		});
		
		btn_servisne_knjizice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				MTabelaServisnihKnjizica tabSK = new MTabelaServisnihKnjizica(sv, musterija);
				tabSK.setVisible(true);
			}
		});
		
		btn_servisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				MTabelaServisa tabSR = new MTabelaServisa(sv, musterija);
				tabSR.setVisible(true);
				
			}
		});
	}

}
