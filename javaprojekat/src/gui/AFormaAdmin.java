package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import enumi.Pol;
import moduli.Administrator;
import net.miginfocom.swing.MigLayout;
import rad_sa_listama.SveListe;

public class AFormaAdmin extends JFrame {

	private JLabel label_ime = new JLabel("Ime: ");
	private JLabel label_prezime = new JLabel("Prezime: ");
	private JLabel label_jmbg = new JLabel("JMBG: ");
	private JLabel label_pol = new JLabel("Pol: ");
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
	private JComboBox<Pol> pol = new JComboBox<Pol>(Pol.values());
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");

	private SveListe sv;
	private Administrator admin;
	
	
	public AFormaAdmin(SveListe sv, Administrator ad) {
		this.sv = sv;
		this.admin = ad;
		if(admin == null) {
			setTitle("Novi Administrator");
		}else {
			setTitle("Izmenjivanje administratora: " + admin.getPrezime());
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
		MigLayout layout = new MigLayout("wrap 2", "[][]","[][][][][][][][][]25[]");
		setLayout(layout);
		
		if(admin != null) {
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
		add(btnOK, "split 2");
		add(btnCancel);

	}
	
	private void InitActions() {
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				AFormaAdmin.this.dispose();
				AFormaAdmin.this.setVisible(false);
				
			}
		});


		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					int idd = sv.getAdmini().get(sv.getAdmini().size() - 1).getId() + 1;
					String imee = ime.getText().trim();
					String prezimee = prezime.getText().trim();
					String jmbgg = jmbg.getText().trim();
					Pol poll = Pol.valueOf(pol.getSelectedItem().toString());
					String adresaa = adresa.getText().trim();
					String brojj = broj_telefona.getText().trim();
					String korisnicko_i = korisnicko_ime.getText().trim();
					String sifrica = sifra.getText().trim();
					String plataa = plata.getText().trim();
					if(admin == null) {
						if(sv.NadjiAdministratora(idd) == null && sv.nadjiAdministratora(korisnicko_i) == null) {
						Administrator adminc = new Administrator(idd, imee, prezimee,
								jmbgg, poll, adresaa, brojj, korisnicko_i, sifrica, plataa);
						sv.getAdmini().add(adminc);
						sv.upisiAdministratore();
						JOptionPane.showMessageDialog(null, "Korisnik " + korisnicko_i + " dodat!");

						}else {
							JOptionPane.showMessageDialog(null, "Korisnik sa upisanim ID-jem ili korisnickim imenom vec postoji.");
						}
					}else {
						admin.setId(idd);
						admin.setIme(imee);
						admin.setAdresa(adresaa);
						admin.setBroj_telefona(brojj);
						admin.setJmbg(jmbgg);
						admin.setKorisnicko_ime(korisnicko_i);
						admin.setSifra(sifrica);
						admin.setPlata(plataa);
						admin.setPol(poll);
						admin.setPrezime(prezimee);
					}
				
					sv.upisiAdministratore();
					AFormaAdmin.this.dispose();
					AFormaAdmin.this.setVisible(false);
					
				}
				
			}
		});
	}
	
	private void popuniPolja() {
		ime.setText(admin.getIme());
		prezime.setText(admin.getPrezime());
		jmbg.setText(admin.getJmbg());
		pol.setSelectedItem(admin.getPol());
		adresa.setText(admin.getAdresa());
		broj_telefona.setText(admin.getBroj_telefona());
		korisnicko_ime.setText(admin.getKorisnicko_ime());
		sifra.setText(admin.getSifra());
		plata.setText(admin.getPlata());

	
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

