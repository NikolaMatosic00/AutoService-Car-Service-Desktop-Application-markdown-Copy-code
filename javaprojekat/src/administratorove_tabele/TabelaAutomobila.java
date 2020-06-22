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
import forme_za_dodavanje_i_izmenu.FormaDodajIzmeniAutomobil;
import forme_za_dodavanje_i_izmenu.FormaDodajIzmeniServis;
import moduli.Administrator;
import moduli.Automobil;
import moduli.Servis;

public class TabelaAutomobila extends JFrame {

	private String slika_add_putanja = new String(PutanjeZaSlike.slika_plus_putanja);
	private String slika_edit_putanja = new String(PutanjeZaSlike.slika_edit_putanja);
	private String slika_delete_putanja = new String(PutanjeZaSlike.slika_minus_putanja);
	
	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel table_model;
	private JTable automobili_tabela;
	
	private UcitajIzFajla uf;
	
	public TabelaAutomobila(UcitajIzFajla ufs) {
		this.uf = ufs;
		setTitle("Pregled automobila");
		setSize(800, 300);
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
		String[] zaglavlje = new String[] {"ID", "Vlasnik", "Marka", "Model" ,"Godiste", "Zapremina u ccm", "Snaga u ks", "Vrsta goriva"};
		Object[][] sadrzaj = new Object[uf.getAutomobili().size()][zaglavlje.length];
		
		for(int i = 0; i < uf.getAutomobili().size(); i ++) {
			Automobil aut = uf.getAutomobili().get(i);
			sadrzaj[i][0] = aut.getId();
			sadrzaj[i][1] = aut.getVlasnik().getKorisnicko_ime();
			sadrzaj[i][2] = aut.getMarka();
			sadrzaj[i][3] = aut.getModel();
			sadrzaj[i][4] = aut.getGodina_proizvodnje();
			sadrzaj[i][5] = aut.getZapremina_motora();
			sadrzaj[i][6] = aut.getSnaga_motora();
			sadrzaj[i][7] = aut.getVrsta_goriva();

		
		
		}
		
		table_model = new DefaultTableModel(sadrzaj, zaglavlje);
		automobili_tabela = new JTable(table_model);
		
		automobili_tabela.setRowSelectionAllowed(true);
		automobili_tabela.setColumnSelectionAllowed(false);
		automobili_tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		automobili_tabela.setDefaultEditor(Object.class, null);
		automobili_tabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane jsp = new JScrollPane(automobili_tabela);
		add(jsp, BorderLayout.CENTER);
	
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = automobili_tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite automobil koji zelite da obrisete.", "Greska!", JOptionPane.WARNING_MESSAGE);
				}else {
					int id = Integer.parseInt(table_model.getValueAt(red,  0).toString());
					Automobil auto = uf.NadjiAutomobil(id);
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi automobil: " + id + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						uf.getAutomobili().remove(auto);
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Automobil sa ID: " + id + " obrisan!");
						uf.upisiAutomobile();
					}
					
					
							
				}
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				FormaDodajIzmeniAutomobil fda = new FormaDodajIzmeniAutomobil(uf, null);
				fda.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = automobili_tabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite red za izmenu.", "Greska!", JOptionPane.WARNING_MESSAGE);
					
				}else {
					String idd = table_model.getValueAt(red, 0).toString();
					Automobil s = uf.NadjiAutomobil(Integer.parseInt(idd));
					FormaDodajIzmeniAutomobil fda = new FormaDodajIzmeniAutomobil(uf, s);
					fda.setVisible(true);
				}
				
			}
		});

	}
}
