package gui;

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

import moduli.Administrator;
import moduli.Automobil;
import moduli.Servis;
import rad_sa_listama.SveListe;

public class ATabelaAutomobili extends JFrame {

	private String slika_add_putanja = new String(SlikePutanje.slika_plus);
	private String slika_edit_putanja = new String(SlikePutanje.slika_editt);
	private String slika_delete_putanja = new String(SlikePutanje.slika_minus);
	
	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel table_model;
	private JTable automobili_tabela;
	
	private SveListe sv;
	
	public ATabelaAutomobili(SveListe sv) {
		this.sv = sv;
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
		Object[][] sadrzaj = new Object[sv.getAutomobili().size()][zaglavlje.length];
		
		for(int i = 0; i < sv.getAutomobili().size(); i ++) {
			Automobil aut = sv.getAutomobili().get(i);
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
					Automobil auto = sv.NadjiAutomobil(id);
					int idvlasnika = auto.getVlasnik().getId();
					int idsk = auto.getServisna_knjizica().getId();
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi automobil: " + id + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						sv.getMusterije().remove(sv.NadjiMusteriju(idvlasnika));
						sv.getServisne_knjizice().remove(sv.NadjiServisnuKnjizicu(idsk));
						sv.getAutomobili().remove(auto);
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Automobil sa ID: " + id + " obrisan!");
						sv.upisiSve();
					}
					
					
							
				}
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				AFormaAutomobil fda = new AFormaAutomobil(sv, null);
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
					Automobil s = sv.NadjiAutomobil(Integer.parseInt(idd));
					AFormaAutomobil fda = new AFormaAutomobil(sv, s);
					fda.setVisible(true);
				}
				
			}
		});

		}
}
