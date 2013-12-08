import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Tools.Dijkstra;
import calculateur.abstracts.Ligne;
import calculateur.abstracts.Relation;
import calculateur.abstracts.Station;
import calculateur.implementations.metro.ModaliteMetro;


public class IHM extends JFrame {
	private static ModaliteMetro ratpMetro = new ModaliteMetro();
	private static Dijkstra dijkstra;
	private static JTextField textFieldDepart;
	private static JTextField textFieldArrivee;
	public static JPanel panelTrajet = new JPanel();
	public static JPanel panelLigne = new JPanel();
	public static JLabel trajet = new JLabel("");
	public static JLabel ligne = new JLabel("");
	public static String Newligne=System.getProperty("line.separator"); 
	public IHM() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("RATP - Calculateur de trajet");
		setSize(500, 500);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		trajet.setHorizontalAlignment(SwingConstants.LEFT);
		trajet.setVerticalAlignment(SwingConstants.TOP);
		trajet.setLocation(0, 0);
		
		trajet.setSize(361, 270);
		ligne.setBounds(0, 0, 481, 174);
		JPanel panelRecherche = new JPanel();
		panelRecherche.setBounds(0, 0, 481, 174);
		getContentPane().add(panelRecherche);
		panelRecherche.setLayout(null);
		
		JLabel lblTitle = new JLabel("Rechercher votre trajet");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 5, 434, 14);
		panelRecherche.add(lblTitle);
		
		JLabel lblDpart = new JLabel("D\u00E9part : ");
		lblDpart.setBounds(10, 46, 60, 33);
		lblDpart.setHorizontalAlignment(SwingConstants.CENTER);
		panelRecherche.add(lblDpart);
		
		JLabel labelArrivee = new JLabel("Arriv\u00E9e : ");
		labelArrivee.setHorizontalAlignment(SwingConstants.CENTER);
		labelArrivee.setBounds(268, 46, 60, 33);
		panelRecherche.add(labelArrivee);
		
		textFieldDepart = new JTextField();
		textFieldDepart.setBounds(80, 52, 86, 20);
		panelRecherche.add(textFieldDepart);
		textFieldDepart.setColumns(10);
		
		textFieldArrivee = new JTextField();
		textFieldArrivee.setColumns(10);
		textFieldArrivee.setBounds(338, 52, 86, 20);
		panelRecherche.add(textFieldArrivee);
		
		JLabel lblCritere = new JLabel("Crit\u00E8re : ");
		lblCritere.setHorizontalAlignment(SwingConstants.CENTER);
		lblCritere.setBounds(10, 90, 60, 33);
		panelRecherche.add(lblCritere);
		
		final JRadioButton rdbtnRapide = new JRadioButton("Le plus rapide");
		rdbtnRapide.setBounds(80, 95, 109, 23);
		
		panelRecherche.add(rdbtnRapide);
		
		final JRadioButton rdbtnChgmt = new JRadioButton("Le moins de changement");
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
		btnRechercheMonTrajet.setBounds(140, 140, 208, 23);
		panelRecherche.add(btnRechercheMonTrajet);
		
		JPanel panelLigne = new JPanel();
		panelLigne.setBounds(10, 212, 94, 239);
		getContentPane().add(panelLigne);
		panelLigne.setLayout(new BoxLayout(panelLigne, BoxLayout.Y_AXIS));
		
		
		panelLigne.add(ligne);
		
		JPanel panel = new JPanel();
		panel.setBounds(113, 181, 361, 270);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		panel.add(trajet);
		btnRechercheMonTrajet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (rdbtnRapide.isSelected()){
					metroModalite("Rapide");
					}else if (rdbtnChgmt.isSelected()){
						metroModalite("Chgmt");
					}
					
				}
			});
		

	}
	private static void metroModalite(String mode) {
		dijkstra = new Dijkstra(ratpMetro);
		String depart = textFieldDepart.getText();
		String arrivee = textFieldArrivee.getText();
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
					trajet.setText("<html>Trajet avec le moins de changement:<br></html>");
					System.out.println("Trajet avec le moins de changement:");
					ArrayList<Relation> relationsTrajet = dijkstra.moinsDeChangement(stationDepart, stationArrivee);
					afficheTrajet(relationsTrajet, stationDepart, stationArrivee);
				} catch (StackOverflowError e) {
					System.out
							.println("Désolé, le trajet avec le moins de changement pour aller de "
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
			
	

	private static void  afficheTrajet(ArrayList<Relation> relations,
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
						IHM.ligne.setText("<html> "
								+ relations.get(indicesCorrespondance.get(j))
										.getLigne() + ":");
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
									.getStationArrivee().getName()+"<br><br><br> ");
							System.out.println("   jusqu'à "
								+ relations.get(indicesCorrespondance.get(j))
										.getStationArrivee().getName() + "\n");
					}
					// affichage du dernier tronçon
					IHM.ligne.setText(IHM.ligne.getText()+"<br><br><br>" + ligne +"</html>");
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
					System.out.println(" " + relations.get(0).getLigne() + ":");
					System.out.println("   depuis " + depart.getName());
					System.out.println("   direction "
							+ relations.get(relations.size() - 1)
									.getDirection());
					System.out
							.println("   jusqu'à " + arrivee.getName() + "\n");
				}
			} else {
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
