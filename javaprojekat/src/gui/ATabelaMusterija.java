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

import moduli.Automobil;
import moduli.Musterija;
import rad_sa_listama.SveListe;

public class ATabelaMusterija extends JFrame {
	
	private String slika_add_putanja = new String(SlikePutanje.slika_add);
	private String slika_edit_putanja = new String(SlikePutanje.slika_edit);
	private String slika_delete_putanja = new String(SlikePutanje.slika_delete);
	
	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel table_model;
	private JTable musterije_tabela;
	
	private SveListe sv;
	
	public ATabelaMusterija(SveListe sv) {
		this.sv = sv;
		setTitle("Pregled musterija");
		setSize(1000, 300);
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
		String[] zaglavlje = new String[] {"Ime", "Prezime", "Korisnicko ime" ,"Adresa", "Broj Telefona", "Nagradni bodovi", "Njegovi automobili"};
		Object[][] sadrzaj = new Object[sv.getMusterije().size()][zaglavlje.length];
		
		for(int i = 0; i < sv.getMusterije().size(); i ++) {
			Musterija mus = sv.getMusterije().get(i);
			sadrzaj[i][0] = mus.getIme();
			sadrzaj[i][1] = mus.getPrezime();
			sadrzaj[i][2] = mus.getKorisnicko_ime();
			sadrzaj[i][3] = mus.getAdresa();
			sadrzaj[i][4] = mus.getBroj_telefona();
			sadrzaj[i][5] = mus.getBroj_nagradnoh_bodova();
			String za_upis = " ";
			for(Automobil a : mus.getAutomobili()) {
				za_upis += a.getMarka() + "-" + a.getModel() + ",";
			}
			sadrzaj[i][6] = za_upis.substring(0, za_upis.length() - 1);

		
		
		}
		
		table_model = new DefaultTableModel(sadrzaj, zaglavlje);
		musterije_tabela = new JTable(table_model);
		
		musterije_tabela.setRowSelectionAllowed(true);
		musterije_tabela.setColumnSelectionAllowed(false);
		musterije_tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		musterije_tabela.setDefaultEditor(Object.class, null);
		musterije_tabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane jsp = new JScrollPane(musterije_tabela);
		add(jsp, BorderLayout.CENTER);
	
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = musterije_tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite musteriju kog zelite da obrisete.", "Greska!", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnicko_ime = table_model.getValueAt(red,  2).toString();
					Musterija mus = sv.NadjiMusteriju(korisnicko_ime);
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi korisnika: " + korisnicko_ime + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					int iddd = 0;
					int idsk = 0;
					if(izbor == JOptionPane.YES_OPTION) {
						for(Automobil a : mus.getAutomobili()) {
							iddd = a.getId();
							idsk = a.getServisna_knjizica().getId(); 
						}
						sv.getAutomobili().remove(sv.NadjiAutomobil(iddd));
						sv.getMusterije().remove(mus);
						sv.getServisne_knjizice().remove(sv.NadjiServisnuKnjizicu(idsk));
						sv.upisiSve();
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Korisnik " + korisnicko_ime + " obrisan!");
					}			
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AFormaMusterija fda = new AFormaMusterija(sv, null);
				fda.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = musterije_tabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite red za izmenu.", "Greska!", JOptionPane.WARNING_MESSAGE);
					
				}else {
					String korisnicko_ime = table_model.getValueAt(red, 2).toString();
					Musterija muster = sv.NadjiMusteriju(korisnicko_ime);
					AFormaMusterija fda = new AFormaMusterija(sv, muster);
					fda.setVisible(true);
				}
				
			}
		});
		
		}
}