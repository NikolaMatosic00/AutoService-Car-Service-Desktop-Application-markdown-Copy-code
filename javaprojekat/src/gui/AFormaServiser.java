package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import enumi.Pol;
import enumi.Specijalizacija;
import moduli.Administrator;
import moduli.Servis;
import moduli.Serviser;
import net.miginfocom.swing.MigLayout;
import rad_sa_listama.SveListe;

public class AFormaServiser extends JFrame {

	private JLabel label_ime = new JLabel("Ime: ");
	private JLabel label_prezime = new JLabel("Prezime: ");
	private JLabel label_jmbg = new JLabel("JMBG: ");
	private JLabel label_pol = new JLabel("Pol: ");
	private JLabel label_specijalizacija = new JLabel("Specijalizacija: ");
	private JLabel label_adresa = new JLabel("Adresa: ");
	private JLabel label_broj_telefona = new JLabel("Broj telefona: ");
	private JLabel label_korisnicko_ime = new JLabel("Korisnicko ime: ");
	private JLabel label_sifra = new JLabel("Sifra: ");
	private JLabel label_plata = new JLabel("Plata: ");
	private JTextField ime = new JTextField(20);
	private JTextField prezime = new JTextField(20);
	private JTextField jmbg = new JTextField(20);
	private JTextField adresa = new JTextField(20);
	private JTextField broj_telefona = new JTextField(20);
	private JTextField korisnicko_ime = new JTextField(20);
	private JTextField sifra = new JTextField(20);
	private JTextField plata = new JTextField(20);
	private JComboBox<Specijalizacija> specijalizacija = new JComboBox<Specijalizacija>(Specijalizacija.values());
	private JComboBox<Pol> pol = new JComboBox<Pol>(Pol.values());
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");

	private SveListe sv;
	private Serviser serviser;
	
	
	public AFormaServiser(SveListe sv, Serviser ser) {
		this.sv = sv;
		this.serviser = ser;
		if(serviser == null) {
			setTitle("Dodavanje Servisera");
		}else {
			setTitle("Izmenjivanje servisera: " + serviser.getPrezime());
		}
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(400, 600);
		InitGUI();
		InitActions();
		pack();
	}
	
	private void InitGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]","[][][][][][][][][][][]25[]");
		setLayout(layout);
		
		if(serviser != null) {
			popuniPolja();
		}
		
		add(label_ime);
		add(ime);
		add(label_prezime);
		add(prezime);
		add(label_jmbg);
		add(jmbg);
		add(label_pol);
		add(pol);
		add(label_adresa);
		add(adresa);
		add(label_broj_telefona);
		add(broj_telefona);
		add(label_korisnicko_ime);
		add(korisnicko_ime);
		add(label_sifra);
		add(sifra);
		add(label_plata);
		add(plata);
		add(label_specijalizacija);
		add(specijalizacija);
		add(btnOK, "split 2");
		add(btnCancel);

	}
	
	private void InitActions() {
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				AFormaServiser.this.dispose();
				AFormaServiser.this.setVisible(false);
				
			}
		});
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					int idd = sv.getServiseri().get(sv.getServiseri().size() - 1).getId() + 1;
					String imee = ime.getText().trim();
					String prezimee = prezime.getText().trim();
					String jmbgg = jmbg.getText().trim();
					Pol poll = Pol.valueOf(pol.getSelectedItem().toString());
					String adresaa = adresa.getText().trim();
					String brojj = broj_telefona.getText().trim();
					String korisnicko_i = korisnicko_ime.getText().trim();
					String sifrica = sifra.getText().trim();
					String plataa = plata.getText().trim();
					Specijalizacija spec = Specijalizacija.valueOf(specijalizacija.getSelectedItem().toString());
					if(serviser == null) {
						if(sv.NadjiServisera(idd) == null && sv.NadjiServisera(korisnicko_i) == null) {
						Serviser serviserr = new Serviser(idd, imee, prezimee,
								jmbgg, poll, adresaa, brojj, korisnicko_i, sifrica, plataa, spec, new ArrayList<Servis>());
						sv.getServiseri().add(serviserr);
						}else {
							JOptionPane.showMessageDialog(null, "Korisnik sa upisanim ID-jem ili korisnickim imenom vec postoji.");
						}
					}else {
						serviser.setId(idd);
						serviser.setIme(imee);
						serviser.setAdresa(adresaa);
						serviser.setBroj_telefona(brojj);
						serviser.setJmbg(jmbgg);
						serviser.setKorisnicko_ime(korisnicko_i);
						serviser.setSifra(sifrica);
						serviser.setPlata(plataa);
						serviser.setPol(poll);
						serviser.setPrezime(prezimee);
					}
				
					sv.upisiServisere();;
					AFormaServiser.this.dispose();
					AFormaServiser.this.setVisible(false);
					
				}
				
			}
		});
	}
	
	private void popuniPolja() {
		ime.setText(serviser.getIme());
		prezime.setText(serviser.getPrezime());
		jmbg.setText(serviser.getJmbg());
		pol.setSelectedItem(serviser.getPol());
		adresa.setText(serviser.getAdresa());
		broj_telefona.setText(serviser.getBroj_telefona());
		korisnicko_ime.setText(serviser.getKorisnicko_ime());
		sifra.setText(serviser.getSifra());
		plata.setText(serviser.getPlata());
		specijalizacija.setSelectedItem(serviser.getSpecijalizacija());
	
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo ispravite sledece greske u unosu: \n";
			
		if(ime.getText().trim().equals("")) {
			poruka += "-Ime\n";
			ok = false;
		}

		if(prezime.getText().trim().equals("")) {
			poruka += "-Prezime\n";
			ok = false;
		}

		if(jmbg.getText().trim().equals("")) {
			poruka += "-JMBG\n";
			ok = false;
		}

		if(pol.getSelectedItem().toString().equals("")) {
			poruka += "-Pol\n";
			ok = false;
		}

		if(adresa.getText().trim().equals("")) {
			poruka += "-Adresa\n";
			ok = false;
		}

		if(broj_telefona.getText().trim().equals("")) {
			poruka += "-Broj Telefona\n";
			ok = false;
		}

		if(korisnicko_ime.getText().trim().equals("")) {
			poruka += "-Korisnicko Ime\n";
			ok = false;
		}

		if(sifra.getText().trim().equals("")) {
			poruka += "-Sifra\n";
			ok = false;
		}

		if(plata.getText().trim().equals("")) {
			poruka += "-Plata\n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Prazna polja", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}

