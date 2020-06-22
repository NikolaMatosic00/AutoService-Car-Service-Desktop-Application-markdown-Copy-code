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
import enumi.Marka;
import enumi.ModelAutomobila;
import moduli.Automobil;
import moduli.Deo;
import moduli.Servis;
import moduli.ServisnaKnjizica;
import net.miginfocom.swing.MigLayout;

public class FormaDodajIzmeniDeo extends JFrame {

private JLabel label_id = new JLabel("ID: ");
private JLabel label_marka = new JLabel("Marka: ");
private JLabel label_model = new JLabel("Model: ");
private JLabel label_naziv = new JLabel("Naziv: ");
private JLabel label_cena = new JLabel("Cena: ");
private JTextField id = new JTextField(20);
private JComboBox<Marka> marka = new JComboBox<Marka>(Marka.values());
private JComboBox<ModelAutomobila> model = new JComboBox<ModelAutomobila>(ModelAutomobila.values());
private JTextField naziv = new JTextField(20);
private JTextField cena = new JTextField(20);
private JButton btnOK = new JButton("OK");
private JButton btnCancel = new JButton("Cancel");

private UcitajIzFajla uf;
private Deo deo;

public FormaDodajIzmeniDeo(UcitajIzFajla uf, Deo d) {
	this.uf = uf;
	this.deo = d;
	if(deo == null) {
		setTitle("Novi Deo");
	}else {
		setTitle("Izmenjivanje dela: " + deo.getId());
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
	MigLayout layout = new MigLayout("wrap 2", "[][]","[][][][][]25[]");
	setLayout(layout);
	
	
	if(deo != null) {
		popuniPolja();
	}
	
	add(label_id);
	add(id);
	add(label_marka);
	add(marka);
	add(label_model);
	add(model);
	add(label_naziv);
	add(naziv);
	add(label_cena);
	add(cena);
	add(btnOK, "split 2");
	add(btnCancel);

}

private void InitActions() {
	
	btnCancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			FormaDodajIzmeniDeo.this.dispose();
			FormaDodajIzmeniDeo.this.setVisible(false);
			
		}
	});
	
	btnOK.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(validacija()) {
				int idd = Integer.parseInt(id.getText().trim());
				Marka markaa = Marka.valueOf(marka.getSelectedItem().toString());
				ModelAutomobila modell = ModelAutomobila.valueOf(model.getSelectedItem().toString());
				String nazivv = naziv.getText().toString().trim();
				int cenaa = Integer.parseInt(cena.getText().toString().trim());
				if(deo == null) {
					if(uf.NadjiDeo(idd) == null) {
						
						Deo deooo = new Deo(idd, markaa, modell, nazivv, cenaa);
						uf.getDelovi().add(deooo);
						uf.upisiSve();
						
					}else {
						JOptionPane.showMessageDialog(null, "Servis sa upisanim ID-jem vec postoji.");
					}
				}else {
							
					deo.SetId(idd);
					deo.setMarka(Marka.valueOf(marka.getSelectedItem().toString()));
					deo.setModel(ModelAutomobila.valueOf(model.getSelectedItem().toString()));
					deo.setNaziv(naziv.getText().toString());
					deo.setCena(Integer.parseInt(cena.getText().toString()));
					
					
				}
			
				uf.upisiSve();
				FormaDodajIzmeniDeo.this.dispose();
				FormaDodajIzmeniDeo.this.setVisible(false);
			
			
		}
		}});
}

private void popuniPolja() {
	id.setText(String.valueOf(deo.getId()));
	marka.setSelectedItem(deo.getMarka());
	model.setSelectedItem(deo.getMarka());
	naziv.setText(deo.getNaziv());
	cena.setText(String.valueOf(deo.getCena()));
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
	if(naziv.getText().trim().equals("")) {
		poruka += "-Naziv\n";
		ok = false;
	}
	if(cena.getText().toString().trim().equals("")) {
		poruka += "-Cena\n";
		ok = false;
	}
		

		if(ok == false) {
		JOptionPane.showMessageDialog(null, poruka, "Prazna polja", JOptionPane.WARNING_MESSAGE);
	}
	return ok;
}
}

