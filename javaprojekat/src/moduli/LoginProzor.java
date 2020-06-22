package moduli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import crud.UcitajIzFajla;
import glavni_prozori.GlavniProzorAdmini;
import glavni_prozori.GlavniProzorMusterije;
import glavni_prozori.GlavniProzorServiseri;
import net.miginfocom.swing.MigLayout;

public class LoginProzor extends JFrame {
	
	private JLabel lbl_greetings = new JLabel("Molimo prijavite se.");
	private JLabel lbl_username = new JLabel("Korisnicko ime");
	private JLabel lbl_sifra = new JLabel("Sifra");

	private JTextField tekst_korisnicko_ime = new JTextField(20);
	private JTextField tekst_sifra = new JTextField(20);
	
	private JButton btn_ok = new JButton("OK");
	private JButton btn_cancel = new JButton("Cancel");

	private UcitajIzFajla uf;
	
	public LoginProzor(UcitajIzFajla uf) {
		this.uf = uf;
		setTitle("Prijava");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		ActionsInit();
		pack();
		getRootPane().setDefaultButton(btn_ok);
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		add(lbl_greetings, "span 2");
		add(lbl_username);
		add(tekst_korisnicko_ime);
		add(lbl_sifra);
		add(tekst_sifra);
		add(new JLabel());
		add(btn_ok, "split 2");
		add(btn_cancel);
	}
	
	public void ActionsInit() {
		btn_cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				LoginProzor.this.dispose();
				LoginProzor.this.setVisible(false);
				
			}
		});
		
		btn_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ki = tekst_korisnicko_ime.getText().trim();
				String sif = tekst_sifra.getText().trim();
				int rez = 0;
				for(Administrator a : uf.getAdmini()) {
					if(a.getKorisnicko_ime().equalsIgnoreCase(ki)) {
						rez += 1;
					}
				}for(Musterija a : uf.getMusterije()) {
					if(a.getKorisnicko_ime().equalsIgnoreCase(ki)) {
						rez += 1;
					}
				}for(Serviser a : uf.getServiseri()) {
					if(a.getKorisnicko_ime().equalsIgnoreCase(ki)) {
						rez += 1;
					}
				}

				if(rez == 0) {
					JOptionPane.showMessageDialog(null, "Uneseni podaci se ne podudaraju ni sa jednim korisnikom.", "Greska prilikom unsa!", JOptionPane.WARNING_MESSAGE);
				}
				if(ki.equals("") || sif.equals("")) {
					JOptionPane.showMessageDialog(null, "Ostavili ste prazno polje", "Greska prilikom unsa!", JOptionPane.WARNING_MESSAGE);

					
				}else {
					for(Administrator a : uf.getAdmini()) {
						if(a.getKorisnicko_ime().equalsIgnoreCase(ki) && a.getSifra().equals(sif)) {
							LoginProzor.this.dispose();
							LoginProzor.this.setVisible(false);
							GlavniProzorAdmini gpa = new GlavniProzorAdmini(uf, a);
							gpa.setVisible(true);
						}
					}for(Musterija m : uf.getMusterije()) {
						if(m.getKorisnicko_ime().equalsIgnoreCase(ki) && m.getSifra().equals(sif)) {
							LoginProzor.this.dispose();
							LoginProzor.this.setVisible(false);
							GlavniProzorMusterije gpm = new GlavniProzorMusterije(uf, m);
							gpm.setVisible(true);
							
						}
					}for(Serviser s : uf.getServiseri()) {
						if(s.getKorisnicko_ime().equalsIgnoreCase(ki) && s.getSifra().equals(sif)) {
							LoginProzor.this.dispose();
							LoginProzor.this.setVisible(false);
							GlavniProzorServiseri gps = new GlavniProzorServiseri(uf, s);
							gps.setVisible(true);
							
						}
					}
				}
				
				
			}
		});
	}
	
}
