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
import forme_za_dodavanje_i_izmenu.FormaDodajIzmeniMusteriju;
import moduli.Administrator;
import moduli.Automobil;
import moduli.Musterija;

public class TabelaMusterija extends JFrame {

	private String slika_add_putanja = new String(PutanjeZaSlike.slika_addKORISNIK_putanja);
	private String slika_edit_putanja = new String(PutanjeZaSlike.slika_editKORISNIK_putanja);
	private String slika_delete_putanja = new String(PutanjeZaSlike.slika_deleteKORISNIK_putanja);
	
	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel table_model;
	private JTable musterije_tabela;
	
	private UcitajIzFajla uf;
	
	public TabelaMusterija(UcitajIzFajla ufs) {
		this.uf = ufs;
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
		Object[][] sadrzaj = new Object[uf.getMusterije().size()][zaglavlje.length];
		
		for(int i = 0; i < uf.getMusterije().size(); i ++) {
			Musterija mus = uf.getMusterije().get(i);
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
					Musterija mus = uf.NadjiMusteriju(korisnicko_ime);
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi korisnika: " + korisnicko_ime + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						uf.getMusterije().remove(mus);
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Korisnik " + korisnicko_ime + " obrisan!");
						uf.upisiMusterije();
					}			
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FormaDodajIzmeniMusteriju fda = new FormaDodajIzmeniMusteriju(uf, null);
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
					Musterija muster = uf.NadjiMusteriju(korisnicko_ime);
					FormaDodajIzmeniMusteriju fda = new FormaDodajIzmeniMusteriju(uf, muster);
					fda.setVisible(true);
				}
				
			}
		});
	}
}