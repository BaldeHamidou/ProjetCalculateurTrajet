import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import Tools.Dijkstra;
import calculateur.abstracts.Ligne;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;
import calculateur.implementations.metro.ModaliteMetro;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;


public class IHM extends JFrame {
	
	private static ModaliteMetro ratpMetro = new ModaliteMetro();
	private static Dijkstra dijkstra;
	private static JComboBox boxDepart;
	private static JComboBox boxArrivee;
	private static String depart;
	private static String arrivee;
	public static JPanel panelTrajet = new JPanel();
	public static JPanel panelLigne = new JPanel();
	public static JLabel trajet = new JLabel("");
	public static JLabel[] labelLigne = new JLabel[10];
	public static String Newligne=System.getProperty("line.separator"); 
	public IHM() {
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("RATP - Calculateur de trajet");
		setSize(500, 500);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		int i = 1;
		String[] dictionary = new String[ratpMetro.getReseau().getMapStations().size()+1];
		dictionary[0] = "";
		for (Station value : ratpMetro.getReseau().getMapStations()
				.values()) {
			dictionary[i] = value.getName();
			i++;
		}
		
		trajet.setForeground(new Color(153, 102, 204));
		trajet.setBackground(Color.WHITE);
		trajet.setHorizontalAlignment(SwingConstants.LEFT);
		trajet.setVerticalAlignment(SwingConstants.TOP);
		trajet.setLocation(0, 0);
		trajet.setSize(361, 270);
		
		JPanel panelRecherche = new JPanel();
		panelRecherche.setBackground(Color.WHITE);
		panelRecherche.setBounds(0, 0, 481, 174);
		getContentPane().add(panelRecherche);
		panelRecherche.setLayout(null);
		
		JLabel lblTitle = new JLabel("Rechercher votre trajet");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setForeground(new Color(153, 102, 204));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 5, 475, 30);
		panelRecherche.add(lblTitle);
		
		JLabel lblDpart = new JLabel("D\u00E9part : ");
		lblDpart.setForeground(new Color(153, 102, 204));
		lblDpart.setBounds(10, 46, 60, 33);
		lblDpart.setHorizontalAlignment(SwingConstants.CENTER);
		panelRecherche.add(lblDpart);
		
		JLabel labelArrivee = new JLabel("Arriv\u00E9e : ");
		labelArrivee.setForeground(new Color(153, 102, 204));
		labelArrivee.setHorizontalAlignment(SwingConstants.CENTER);
		labelArrivee.setBounds(268, 46, 60, 33);
		panelRecherche.add(labelArrivee);
		
		final JComboBox boxDepart  = new JComboBox(dictionary);
		boxDepart.setBackground(Color.WHITE);
		AutoCompleteDecorator.decorate(boxDepart);
		boxDepart.setBounds(80, 52, 86, 20);
		panelRecherche.add(boxDepart);
		
		final JComboBox boxArrivee  = new JComboBox(dictionary);
		boxArrivee.setBackground(Color.WHITE);
		AutoCompleteDecorator.decorate(boxArrivee);
		boxArrivee.setBounds(338, 52, 86, 20);
		panelRecherche.add(boxArrivee);
				
		JLabel lblCritere = new JLabel("Crit\u00E8re : ");
		lblCritere.setForeground(new Color(153, 102, 204));
		lblCritere.setHorizontalAlignment(SwingConstants.CENTER);
		lblCritere.setBounds(10, 90, 60, 33);
		panelRecherche.add(lblCritere);
		
		final JRadioButton rdbtnRapide = new JRadioButton("Le plus rapide");
		rdbtnRapide.setBackground(Color.WHITE);
		rdbtnRapide.setForeground(new Color(153, 102, 255));
		rdbtnRapide.setBounds(80, 95, 109, 23);
		
		panelRecherche.add(rdbtnRapide);
		
		final JRadioButton rdbtnChgmt = new JRadioButton("Le moins de changement");
		rdbtnChgmt.setBackground(Color.WHITE);
		rdbtnChgmt.setForeground(new Color(153, 102, 204));
		rdbtnChgmt.setBounds(259, 95, 216, 23);
		panelRecherche.add(rdbtnChgmt);
		
		rdbtnRapide.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				rdbtnChgmt.setSelected(false);
			}
		});
		
		rdbtnChgmt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				rdbtnRapide.setSelected(false);
			}
		});
		
		
		panelTrajet.setBounds(114, 181, 367, 280);
		
		panelTrajet.setLayout(new BoxLayout(panelTrajet, BoxLayout.Y_AXIS));
		
		
		JButton btnRechercheMonTrajet = new JButton("Recherche mon trajet");
		btnRechercheMonTrajet.setForeground(new Color(153, 102, 204));
		btnRechercheMonTrajet.setBounds(140, 140, 208, 23);
		panelRecherche.add(btnRechercheMonTrajet);
		
		final JPanel panelLigne = new JPanel();
		panelLigne.setBackground(Color.WHITE);
		panelLigne.setBounds(10, 181, 94, 270);
		
		panelLigne.setLayout(new BoxLayout(panelLigne, BoxLayout.Y_AXIS));
		
		getContentPane().add(panelLigne);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(113, 181, 361, 270);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.add(trajet);
		
		btnRechercheMonTrajet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				depart = boxDepart.getSelectedItem().toString();
				arrivee = boxArrivee.getSelectedItem().toString();
				trajet.setText("");
				panelLigne.removeAll();
				if (rdbtnRapide.isSelected()){
					metroModalite("Rapide");
					for(int i=0;i<10;i++){
						panelLigne.add(labelLigne[i]);
					}
					
					}else if (rdbtnChgmt.isSelected()){
						metroModalite("Chgmt");
						for(int i=0;i<10;i++){
							panelLigne.add(labelLigne[i]);
						}
					}
					
				}
			});
		

	}
	private void metroModalite(String mode) {
		dijkstra = new Dijkstra(ratpMetro);
		
		Station stationDepart = getStationByNameMetro(depart);
		Station stationArrivee = getStationByNameMetro(arrivee);
		

		if (!stationDepart.equals(stationArrivee)) {
			if(mode.contains("Rapide")){
				try {
					trajet.setText("Trajet le plus rapide:");
					System.out.println("Trajet le plus rapide:");
					ArrayList<Relation> relationsTrajet = dijkstra.plusRapideChemin(stationDepart, stationArrivee);
					System.out.println(dijkstra.plusRapideChemin(stationDepart, stationArrivee));
					afficheTrajet(relationsTrajet, stationDepart, stationArrivee);
					
				} catch (StackOverflowError e) {
					System.out
							.println("Désolé, le trajet le plus rapide pour aller de "
									+ stationDepart.getName()
									+ " à "
									+ stationArrivee.getName()
									+ " n'a pas pu être calculé.\n");
				}
			}else if (mode.contains("Chgmt")){
				try {
					trajet.setText("Trajet avec le moin de changement : ");
					System.out.println("Trajet le plus rapide:");
					ArrayList<Relation> relationsTrajet = dijkstra.moinsDeChangement(stationDepart, stationArrivee);
					System.out.println(dijkstra.moinsDeChangement(stationDepart, stationArrivee));
					afficheTrajet(relationsTrajet, stationDepart, stationArrivee);
				} catch (StackOverflowError e) {
					System.out
							.println("Désolé, le trajet le plus rapide pour aller de "
									+ stationDepart.getName()
									+ " à "
									+ stationArrivee.getName()
									+ " n'a pas pu être calculé.\n");
				}
				
			}
		} else
			System.out.println("Vous y êtes déja ! ^^");
		
	}
	private static Station getStationByNameMetro(String nomStation) {
		
		Station station = null;
		
				for (Station value : ratpMetro.getReseau().getMapStations()
						.values()) {
					if (value.getName().trim().toLowerCase()
							.equals(nomStation.trim().toLowerCase())) {
						station = value;
						
					}
				}
				return station;
			}
			
	

	private  void  afficheTrajet(ArrayList<Relation> relations,
			Station depart, Station arrivee) {
		trajet.setText(trajet.getText()+"\tDépart: " + depart.getName() + "\tArrivée: "
				+ arrivee.getName());
		System.out.println("\tDépart: " + depart.getName() + "\tArrivée: "
				+ arrivee.getName());
		if (relations.size() > 0) {
			int i = 0;
			if (relations.size() > 1) {
				ArrayList<Integer> indicesCorrespondance = new ArrayList<Integer>();
				Ligne ligne = relations.get(0).getLigne();
				while (i < relations.size()
						&& relations.get(i).getLigne().equals(ligne)) {
					i++;
					if (i < relations.size()
							&& !relations.get(i).getLigne().equals(ligne)) {
						indicesCorrespondance.add(i - 1);
						ligne = relations.get(i).getLigne();
					}
				}
				if (indicesCorrespondance.size() > 0) {
					
					for (int j = 0; j < indicesCorrespondance.size(); j++) {
						ImageIcon icon = new ImageIcon(getClass().getResource("./images/"+relations.get(indicesCorrespondance.get(j)).getLigne().toString()+".png"));
						int indice = j;
						labelLigne[indice] = new JLabel();
						labelLigne[indice].setIcon(icon);
						System.out.println(" "
								+ relations.get(indicesCorrespondance.get(j))
										.getLigne() + ":");
						if (j == 0){
							trajet.setText("<html>   Depuis "+depart.getName());
							System.out.println("   depuis " + depart.getName());
						}else
							trajet.setText("<html>   Depuis : "+ relations.get(indicesCorrespondance
									.get(j) + 1));
							System.out.println("   depuis "
									+ relations.get(indicesCorrespondance
											.get(j) + 1));

							trajet.setText(trajet.getText()+"<br>Direction : "+ relations.get(indicesCorrespondance.get(j))
									.getDirection());
							System.out.println("   direction "
								+ relations.get(indicesCorrespondance.get(j))
										.getDirection());
						
							trajet.setText(trajet.getText()+"<br>Jusqu'à : "+ relations.get(indicesCorrespondance.get(j))
									.getStationArrivee().getName()+"<br> ");
							System.out.println("   jusqu'à "
								+ relations.get(indicesCorrespondance.get(j))
										.getStationArrivee().getName() + "\n");
					}
					// affichage du dernier tronçon
					int indice2 = 1;
					ImageIcon icon = new ImageIcon(getClass().getResource("./images/"+ligne.getNameLigne().toString()+".png"));
					labelLigne[indice2] = new JLabel();
					labelLigne[indice2].setIcon(icon) ;
					System.out.println(" " + ligne + ":");
					trajet.setText(trajet.getText()+"<br> Depuis : "+ relations
							.get(indicesCorrespondance
									.get(indicesCorrespondance
											.size() - 1) + 1)
							.getStationDepart().getName());
					
					System.out
							.println("   depuis "
									+ relations
											.get(indicesCorrespondance
													.get(indicesCorrespondance
															.size() - 1) + 1)
											.getStationDepart().getName());
					trajet.setText(trajet.getText()+"<br> Direction : "+ relations.get(relations.size() - 1)
							.getDirection());
					System.out.println("   direction "
							+ relations.get(relations.size() - 1)
									.getDirection());
					trajet.setText(trajet.getText()+"<br> Jusqu'à : "+ arrivee.getName() + "</html>");
					System.out
							.println("   jusqu'à " + arrivee.getName() + "\n");
				} else {
					ImageIcon icon = new ImageIcon(getClass().getResource("./images/"+relations.get(0).getLigne().toString()+".png"));
					labelLigne[0] = new JLabel();
					labelLigne[0].setIcon(icon);
					trajet.setText("<html>   Depuis "+depart.getName());
					trajet.setText(trajet.getText()+"<br>Direction : "+ relations.get(relations.size() - 1)
							.getDirection());
					trajet.setText(trajet.getText()+"<br>Jusqu'à : "+ arrivee.getName());
					System.out.println(" " + relations.get(0).getLigne() + ":");
					System.out.println("   depuis " + depart.getName());
					System.out.println("   direction "
							+ relations.get(relations.size() - 1)
									.getDirection());
					System.out
							.println("   jusqu'à " + arrivee.getName() + "\n");
				}
			} else {
				labelLigne[0] = new JLabel();
				labelLigne[0].setText(relations.get(0).getLigne().toString());
				trajet.setText("<html>   Depuis "+depart.getName());
				trajet.setText(trajet.getText()+"<br>Direction : "+ relations.get(relations.size() - 1)
						.getDirection());
				trajet.setText(trajet.getText()+"<br>Jusqu'à : "+ arrivee.getName());
				System.out.println(relations.get(0).getLigne() + ":");
				System.out.println("   depuis " + depart.getName());
				System.out.println("   direction "
						+ relations.get(0).getDirection());
				System.out.println("   jusqu'à " + arrivee.getName() + "\n");
			}
		} else
			System.out
					.println("Désolé le calculateur à retourner un trajet vide.");
		
			
		
		}
	}
