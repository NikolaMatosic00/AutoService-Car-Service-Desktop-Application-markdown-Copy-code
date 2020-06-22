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
import javax.swing.table.TableModel;

import crud.UcitajIzFajla;
import forme_za_dodavanje_i_izmenu.FormaDodajIzmeniAdmina;
import moduli.Administrator;

public class TabelaAdministratora extends JFrame {

	
	private String slika_add_putanja = new String(PutanjeZaSlike.slika_addKORISNIK_putanja);
	private String slika_edit_putanja = new String(PutanjeZaSlike.slika_editKORISNIK_putanja);
	private String slika_delete_putanja = new String(PutanjeZaSlike.slika_deleteKORISNIK_putanja);

	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel table_model;
	private JTable administratori_tabela;
	
	private UcitajIzFajla uf;
	
	public TabelaAdministratora(UcitajIzFajla ufs) {
		this.uf = ufs;
		setTitle("Pregled administratora");
		setSize(500, 300);
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
		String[] zaglavlje = new String[] {"Ime", "Prezime", "Korisnicko ime" ,"Adresa", "Broj Telefona", "Plata"};
		Object[][] sadrzaj = new Object[uf.getAdmini().size()][zaglavlje.length];
		
		for(int i = 0; i < uf.getAdmini().size(); i ++) {
			Administrator adm = uf.getAdmini().get(i);
			sadrzaj[i][0] = adm.getIme();
			sadrzaj[i][1] = adm.getPrezime();
			sadrzaj[i][2] = adm.getKorisnicko_ime();
			sadrzaj[i][3] = adm.getAdresa();
			sadrzaj[i][4] = adm.getBroj_telefona();
			sadrzaj[i][5] = adm.getPlata();
		}
		
		table_model = new DefaultTableModel(sadrzaj, zaglavlje);
		administratori_tabela = new JTable(table_model);
		
		administratori_tabela.setRowSelectionAllowed(true);
		administratori_tabela.setColumnSelectionAllowed(false);
		administratori_tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		administratori_tabela.setDefaultEditor(Object.class, null);
		administratori_tabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane jsp = new JScrollPane(administratori_tabela);
		add(jsp, BorderLayout.CENTER);
	
	}
	
	private void initActions() {
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = administratori_tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite administratora kog zelite da obrisete.", "Greska!", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnicko_ime = table_model.getValueAt(red,  2).toString();
					Administrator admin = uf.nadjiAdministratora(korisnicko_ime);
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi korisnika: " + korisnicko_ime + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						uf.getAdmini().remove(admin);
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Korisnik " + korisnicko_ime + " obrisan!");
						uf.upisiAdministratore();
					}
					
					
							
				}
				
			}
		});
	
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				FormaDodajIzmeniAdmina fda = new FormaDodajIzmeniAdmina(uf, null);
				fda.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = administratori_tabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite red za izmenu.", "Greska!", JOptionPane.WARNING_MESSAGE);
					
				}else {
					String korisnicko_ime = table_model.getValueAt(red, 2).toString();
					Administrator adminis = uf.nadjiAdministratora(korisnicko_ime);
					FormaDodajIzmeniAdmina fda = new FormaDodajIzmeniAdmina(uf, adminis);
					fda.setVisible(true);
				}
				
			}
		});
	}
}
