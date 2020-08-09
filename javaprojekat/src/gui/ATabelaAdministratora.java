package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import moduli.Administrator;
import rad_sa_listama.SveListe;

public class ATabelaAdministratora extends JFrame {

	
	private String slika_add_putanja = new String(SlikePutanje.slika_add);
	private String slika_edit_putanja = new String(SlikePutanje.slika_edit);
	private String slika_delete_putanja = new String(SlikePutanje.slika_delete);

	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel table_model;
	private JTable administratori_tabela;
	
	private SveListe sv;
	
	public ATabelaAdministratora(SveListe sv) {
		this.sv = sv;
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
		Object[][] sadrzaj = new Object[sv.getAdmini().size()][zaglavlje.length];
		
		for(int i = 0; i < sv.getAdmini().size(); i ++) {
			Administrator adm = sv.getAdmini().get(i);
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
					Administrator admin = sv.nadjiAdministratora(korisnicko_ime);
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi korisnika: " + korisnicko_ime + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						sv.getAdmini().remove(admin);
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Korisnik " + korisnicko_ime + " obrisan!");
						sv.upisiSve();
					}
					
					
							
				}
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				AFormaAdmin fda = new AFormaAdmin(sv, null, ATabelaAdministratora.this);
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
					Administrator adminis = sv.nadjiAdministratora(korisnicko_ime);
					AFormaAdmin fda = new AFormaAdmin(sv, adminis, null);
					fda.setVisible(true);
				}
				
			}
		});

	
		}

}