package administratorove_tabele;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import crud.UcitajIzFajla;
import forme_za_dodavanje_i_izmenu.FormaDodajIzmeniAdmina;
import forme_za_dodavanje_i_izmenu.FormaDodajIzmeniServisera;
import moduli.Administrator;
import moduli.Musterija;
import moduli.Serviser;

public class TabelaServisera extends JFrame {

	private String slika_add_putanja = new String(PutanjeZaSlike.slika_addKORISNIK_putanja);
	private String slika_edit_putanja = new String(PutanjeZaSlike.slika_editKORISNIK_putanja);
	private String slika_delete_putanja = new String(PutanjeZaSlike.slika_deleteKORISNIK_putanja);
	
	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel table_model;
	private JTable serviseri_tabela;
	
	private UcitajIzFajla uf;
	
	public TabelaServisera(UcitajIzFajla ufs) {
		this.uf = ufs;
		setTitle("Pregled servisera");
		setSize(850, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		
	}
	private void initGUI() {

		ImageIcon add_icon = new ImageIcon(slika_add_putanja);
		btnAdd.setIcon(add_icon);
		ImageIcon edit_icon = new ImageIcon(slika_edit_putanja);
		btnEdit.setIcon(edit_icon);
		ImageIcon delete_icon = new ImageIcon(slika_delete_putanja);
		btnDelete.setIcon(delete_icon);
		
		main_tool_bar.add(btnAdd);
		main_tool_bar.add(btnEdit);
		main_tool_bar.add(btnDelete);

		add(main_tool_bar, BorderLayout.SOUTH);
		String[] zaglavlje = new String[] {"Ime", "Prezime", "Korisnicko ime" ,"Adresa", "Broj Telefona", "Plata", "Specijalizacija"};
		Object[][] sadrzaj = new Object[uf.getServiseri().size()][zaglavlje.length];
		
		for(int i = 0; i < uf.getServiseri().size(); i ++) {
			Serviser ser = uf.getServiseri().get(i);
			sadrzaj[i][0] = ser.getIme();
			sadrzaj[i][1] = ser.getPrezime();
			sadrzaj[i][2] = ser.getKorisnicko_ime();
			sadrzaj[i][3] = ser.getAdresa();
			sadrzaj[i][4] = ser.getBroj_telefona();
			sadrzaj[i][5] = ser.getPlata();
			sadrzaj[i][6] = ser.getSpecijalizacija();

		
		
		}
		
		table_model = new DefaultTableModel(sadrzaj, zaglavlje);
		serviseri_tabela = new JTable(table_model);
		
		serviseri_tabela.setRowSelectionAllowed(true);
		serviseri_tabela.setColumnSelectionAllowed(false);
		serviseri_tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		serviseri_tabela.setDefaultEditor(Object.class, null);
		serviseri_tabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane jsp = new JScrollPane(serviseri_tabela);
		add(jsp, BorderLayout.CENTER);
	
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = serviseri_tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite servisera kog zelite da obrisete.", "Greska!", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnicko_ime = table_model.getValueAt(red,  2).toString();
					Serviser ser = uf.NadjiServisera(korisnicko_ime);
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi korisnika: " + ser.getIme() + ser.getPrezime() + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						uf.getServiseri().remove(ser);
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Korisnik " + korisnicko_ime + " obrisan!");
						uf.upisiServisere();
					}
					
					
							
				}
				
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				FormaDodajIzmeniServisera fds = new FormaDodajIzmeniServisera(uf, null);
				fds.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = serviseri_tabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite red za izmenu.", "jGreska!", JOptionPane.WARNING_MESSAGE);
					
				}else {
					String korisnicko_ime = table_model.getValueAt(red, 2).toString();
					Serviser serv = uf.NadjiServisera(korisnicko_ime);
					FormaDodajIzmeniServisera fds = new FormaDodajIzmeniServisera(uf, serv);
					fds.setVisible(true);		
					}
			}
		});
		
	
	
}
}
