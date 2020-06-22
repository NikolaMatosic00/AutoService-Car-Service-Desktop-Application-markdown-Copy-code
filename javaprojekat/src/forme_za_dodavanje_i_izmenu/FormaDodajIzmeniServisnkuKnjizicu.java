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
import moduli.Automobil;
import moduli.Servis;
import moduli.ServisnaKnjizica;
import net.miginfocom.swing.MigLayout;

public class FormaDodajIzmeniServisnkuKnjizicu extends JFrame {

private JLabel label_id = new JLabel("ID: ");
private JLabel label_automobil = new JLabel("Automobil: ");
private JTextField id = new JTextField(20);
private JComboBox<String> automobil = new JComboBox<String>();
private JButton btnOK = new JButton("OK");
private JButton btnCancel = new JButton("Cancel");

private UcitajIzFajla uf;
private ServisnaKnjizica servisna_knjizica;

public FormaDodajIzmeniServisnkuKnjizicu(UcitajIzFajla uf, ServisnaKnjizica sk) {
	this.uf = uf;
	this.servisna_knjizica = sk;
	if(servisna_knjizica == null) {
		setTitle("Nova servisna knjizica");
	}else {
		setTitle("Izmenjivanje servisne knjizice: " + servisna_knjizica.getId());
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
	MigLayout layout = new MigLayout("wrap 2", "[][]","[][]25[]");
	setLayout(layout);
	
	for(Automobil a : uf.getAutomobili()) {
		if(a.getServisna_knjizica() != null) {
			automobil.addItem(String.valueOf(a.getId()) + a.getModel());
		}
	}
	
	if(servisna_knjizica != null) {
		popuniPolja();
	}
	
	add(label_id);
	add(id);
	add(label_automobil);
	add(automobil);
	add(btnOK, "split 2");
	add(btnCancel);

}

private void InitActions() {
	
	btnCancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			FormaDodajIzmeniServisnkuKnjizicu.this.dispose();
			FormaDodajIzmeniServisnkuKnjizicu.this.setVisible(false);
			
		}
	});
	
	btnOK.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(validacija()) {
				int idd = Integer.parseInt(id.getText().trim());
				Automobil auto = uf.NadjiAutomobil(Integer.parseInt(automobil.getSelectedItem().toString().split("-")[0]));
				
				if(servisna_knjizica == null) {
					if(uf.NadjiServisnuKnjizicu(idd) == null) {
						
						ServisnaKnjizica serv_knj = new ServisnaKnjizica(idd, auto, new ArrayList<Servis>());
						uf.getServisne_knjizice().add(serv_knj);
						for(Automobil au : uf.getAutomobili()) {
							if(au.getId() == auto.getId()) {
								au.setServisna_knjizica(serv_knj);
							}
						}
						uf.upisiSve();
						
					}else {
						JOptionPane.showMessageDialog(null, "Servis sa upisanim ID-jem vec postoji.");
					}
				}else {
							
					servisna_knjizica.SetId(idd);
					servisna_knjizica.setAutomobil(auto);
					
					
					for(Automobil au : uf.getAutomobili()) {
						if(au.getId() == auto.getId()) {
							au.setServisna_knjizica(servisna_knjizica);
						}
					}
				}
			
				uf.upisiSve();
				FormaDodajIzmeniServisnkuKnjizicu.this.dispose();
				FormaDodajIzmeniServisnkuKnjizicu.this.setVisible(false);
			
			
		}
		}});
}

private void popuniPolja() {
	id.setText(String.valueOf(servisna_knjizica.getId()));
	automobil.setSelectedItem(String.valueOf(servisna_knjizica.getAutomobil().getId()));
	
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
		

		if(ok == false) {
		JOptionPane.showMessageDialog(null, poruka, "Prazna polja", JOptionPane.WARNING_MESSAGE);
	}
	return ok;
}
}

