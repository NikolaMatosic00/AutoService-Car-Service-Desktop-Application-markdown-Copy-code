package forme_za_dodavanje_i_izmenu;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JTextField;

	import crud.UcitajIzFajla;
	import enumi.Pol;
	import moduli.Administrator;
import moduli.Automobil;
import moduli.Musterija;
import net.miginfocom.swing.MigLayout;

	public class FormaDodajIzmeniMusteriju extends JFrame {

		private JLabel label_id = new JLabel("ID: ");
	private JLabel label_ime = new JLabel("Ime: ");
	private JLabel label_prezime = new JLabel("Prezime: ");
	private JLabel label_jmbg = new JLabel("JMBG: ");
	private JLabel label_pol = new JLabel("Pol: ");
	private JLabel label_adresa = new JLabel("Adresa: ");
	private JLabel label_broj_telefona = new JLabel("Broj telefona: ");
	private JLabel label_korisnicko_ime = new JLabel("Korisnicko ime: ");
	private JLabel label_sifra = new JLabel("Sifra: ");
	private JLabel label_bodovi = new JLabel("Nagradni bodovi: ");
	private JTextField id = new JTextField(20);
	private JTextField ime = new JTextField(20);
	private JTextField prezime = new JTextField(20);
	private JTextField jmbg = new JTextField(20);
	private JTextField adresa = new JTextField(20);
	private JTextField broj_telefona = new JTextField(20);
	private JTextField korisnicko_ime = new JTextField(20);
	private JTextField sifra = new JTextField(20);
	private JComboBox<Pol> pol = new JComboBox<Pol>(Pol.values());
	private JTextField bodovi = new JTextField(20);
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");

	private UcitajIzFajla uf;
	private Musterija musterija;
	
	
	public FormaDodajIzmeniMusteriju(UcitajIzFajla uf, Musterija mus) {
		this.uf = uf;
		this.musterija = mus;
		if(musterija == null) {
			setTitle("Nova Musterija");
		}else {
			setTitle("Izmenjivanje Musterije: " + musterija.getPrezime());
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
		MigLayout layout = new MigLayout("wrap 2", "[][]","[][][][][][][][][][]25[]");
		setLayout(layout);
		
		if(musterija != null) {
			popuniPolja();
		}
		
		add(label_id);
		add(id);
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
		add(label_bodovi);
		add(bodovi);
		add(btnOK, "split 2");
		add(btnCancel);

	}
	
	private void InitActions() {
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				FormaDodajIzmeniMusteriju.this.dispose();
				FormaDodajIzmeniMusteriju.this.setVisible(false);
				
			}
		});
		
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					int idd = Integer.parseInt(id.getText().trim());
					String imee = ime.getText().trim();
					String prezimee = prezime.getText().trim();
					String jmbgg = jmbg.getText().trim();
					Pol poll = Pol.valueOf(pol.getSelectedItem().toString());
					String adresaa = adresa.getText().trim();
					String brojj = broj_telefona.getText().trim();
					String korisnicko_i = korisnicko_ime.getText().trim();
					String sifrica = sifra.getText().trim();
					int bodovii = Integer.parseInt(bodovi.getText().trim());
					if(musterija == null) {
						if(uf.NadjiMusteriju(idd) == null && uf.NadjiMusteriju(korisnicko_i) == null) {
						Musterija musterrr = new Musterija(idd, imee, prezimee,
								jmbgg, poll, adresaa, brojj, korisnicko_i, sifrica, bodovii, new ArrayList<Automobil>());
						uf.getMusterije().add(musterrr);
						}else {
							JOptionPane.showMessageDialog(null, "Korisnik sa upisanim ID-jem ili korisnickim imenom vec postoji.");
						}
					}else {
						musterija.setId(idd);
						musterija.setIme(imee);
						musterija.setAdresa(adresaa);
						musterija.setBroj_telefona(brojj);
						musterija.setJmbg(jmbgg);
						musterija.setKorisnicko_ime(korisnicko_i);
						musterija.setSifra(sifrica);
						musterija.setBroj_nagradnoh_bodova(bodovii);
						musterija.setPol(poll);
						musterija.setPrezime(prezimee);
					}
				
					uf.upisiMusterije();
					FormaDodajIzmeniMusteriju.this.dispose();
					FormaDodajIzmeniMusteriju.this.setVisible(false);
				}
				
			}
		});
	}
	
	private void popuniPolja() {
		id.setText(String.valueOf(musterija.getId()));
		ime.setText(musterija.getIme());
		prezime.setText(musterija.getPrezime());
		jmbg.setText(musterija.getJmbg());
		pol.setSelectedItem(musterija.getPol());
		adresa.setText(musterija.getAdresa());
		broj_telefona.setText(musterija.getBroj_telefona());
		korisnicko_ime.setText(musterija.getKorisnicko_ime());
		sifra.setText(musterija.getSifra());
		bodovi.setText(String.valueOf(musterija.getBroj_nagradnoh_bodova()));

	
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo ispravite sledece greske u unosu: \n";
		try {
			Integer.parseInt(id.getText().trim());
		}catch(NumberFormatException e) {
			poruka += "ID mora biti broj\n";
			ok = false;
		}
			
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

		try {
			Integer.parseInt(bodovi.getText().trim());
		}catch(NumberFormatException e) {
			poruka += "Broj bodova mora biti broj\n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Prazna polja", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}
