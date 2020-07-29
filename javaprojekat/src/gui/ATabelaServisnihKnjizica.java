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

import moduli.Servis;
import moduli.ServisnaKnjizica;
import rad_sa_listama.SveListe;

public class ATabelaServisnihKnjizica extends JFrame {

	private String slika_add_putanja = new String(SlikePutanje.slika_plus);
	private String slika_delete_putanja = new String(SlikePutanje.slika_minus);
	
	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel table_model;
	private JTable servisne_knjizice_tabela;
	
	private SveListe sv;
	
	public ATabelaServisnihKnjizica(SveListe svs) {
		this.sv = svs;
		setTitle("Pregled Servisnih Knjizica");
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
		ImageIcon delete_icon = new ImageIcon(slika_delete_putanja);
		btnDelete.setIcon(delete_icon);
		
		main_tool_bar.add(btnAdd);
		main_tool_bar.add(btnDelete);

		add(main_tool_bar, BorderLayout.SOUTH);
		String[] zaglavlje = new String[] {"ID", "Automobil", "Obavljeni servisi"};
		Object[][] sadrzaj = new Object[sv.getServisne_knjizice().size()][zaglavlje.length];
		
		for(int i = 0; i < sv.getServisne_knjizice().size(); i ++) {
			ServisnaKnjizica sk = sv.getServisne_knjizice().get(i);
			sadrzaj[i][0] = sk.getId();
			sadrzaj[i][1] = sk.getAutomobil().getMarka() + "-" + sk.getAutomobil().getModel();
			String obavljeni = " ";
			for(Servis s : sk.getObavljeni_servisi()) {
				obavljeni += s.getOpis() + ",";
			}
			sadrzaj[i][2] = obavljeni.substring(0, obavljeni.length() - 1);	
		
		}
		
		table_model = new DefaultTableModel(sadrzaj, zaglavlje);
		servisne_knjizice_tabela = new JTable(table_model);
		
		servisne_knjizice_tabela.setRowSelectionAllowed(true);
		servisne_knjizice_tabela.setColumnSelectionAllowed(false);
		servisne_knjizice_tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		servisne_knjizice_tabela.setDefaultEditor(Object.class, null);
		servisne_knjizice_tabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane jsp = new JScrollPane(servisne_knjizice_tabela);
		add(jsp, BorderLayout.CENTER);
	
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = servisne_knjizice_tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite servisnu knjizicu koju zelite da obrisete.", "Greska!", JOptionPane.WARNING_MESSAGE);
				}else {
					int id = Integer.parseInt(table_model.getValueAt(red,  0).toString());
					ServisnaKnjizica ser_knj = sv.NadjiServisnuKnjizicu(id);
					int idauta = 0;
					int idmusterije = 0;
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi servisnu knjizicu?: " + id + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						idauta = ser_knj.getAutomobil().getId();
						idmusterije = ser_knj.getAutomobil().getVlasnik().getId();
						sv.getServisne_knjizice().remove(ser_knj);
						sv.getMusterije().remove(sv.NadjiMusteriju(idmusterije));
						sv.getAutomobili().remove(sv.NadjiAutomobil(idauta));
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Servisna knjizica " + id + " obrisana!");
						sv.upisiSve();
					}
					
					
							
				}
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(sv.getServisne_knjizice().size() == sv.getAutomobili().size()) {
					JOptionPane.showMessageDialog(null, "Svi automobili vec imaju servisne knjizice.");
				} else {
				
					AFormaServisnaKnjizica fda = new AFormaServisnaKnjizica(sv, null);
					fda.setVisible(true);
			
				}
			}	
		});
		

		
	}
}

