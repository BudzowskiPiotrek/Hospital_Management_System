package gestionHospital;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder; // Needed for consistent padding

@SuppressWarnings("serial")
public class PanelMantenimiento extends JPanel {
	private final PanelImagen panelImagen;

	// Colores consistentes con la aplicación
	Color colorbg = Color.decode("#212f3d"); // Fondo general del panel principal y menú izquierdo
	Color colorButton = Color.decode("#006D77"); // Color de los botones del menú izquierdo
	Color panelAccentColor = Color.decode("#F4D35E"); // Color de acento para el título del panel (amarillo)
	Color displayPanelBg = Color.decode("#ECF0F1"); // Fondo del panel derecho donde se muestran los contenidos

	// Color rojo para el fondo de los títulos de los subpaneles "Salas" y "Habitaciones"
	private final Color subPanelTitleBgColor = Color.RED; // NEW: Defined a new color for the sub-panel titles

	private CardLayout cardLayout = new CardLayout();
	private JPanel infoPanel; // El panel derecho que contendrá los diferentes subpaneles

	// Declaración de los subpaneles específicos de Mantenimiento
	private JPanel panelSalas;
	private JPanel panelHabitaciones;

	public PanelMantenimiento(PanelImagen panelImagen) {
		this.panelImagen = panelImagen;
		setLayout(new BorderLayout()); // Layout principal del PanelMantenimiento
		setBackground(colorbg); // Fondo del PanelMantenimiento
		setPreferredSize(new Dimension(800, 600)); // Adjusted to a more standard size

		initComponents(); // Centralized construction into a single method
	}

	private void initComponents() {
		// --- Main wrapper panel for the content (similar to wrapperPanel in other panels) ---
		JPanel mainWrapperPanel = new JPanel(new BorderLayout());
		mainWrapperPanel.setBackground(colorbg); // Background of the main panel

		// --- Title Panel (Top - BorderLayout.NORTH) ---
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(colorbg); // Dark background #212f3d for the main title panel
		titlePanel.setBorder(new EmptyBorder(20, 20, 10, 20)); // Inner padding
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel titleLabel = new JLabel("PERSONAL DE MANTENIMIENTO", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 35)); // Larger font for the title
		titleLabel.setForeground(panelAccentColor); // Yellow accent color #F4D35E
		titleLabel.setPreferredSize(new Dimension(800, 100)); // Preferred height for the title
		titlePanel.add(titleLabel);

		// --- Central Content Panel (Left Menu + Right Display - BorderLayout.CENTER) ---
		JPanel contentPanel = new JPanel(new BorderLayout(10, 10)); // Spacing between menu and display
		contentPanel.setBackground(colorbg); // Dark background #212f3d
		contentPanel.setBorder(new EmptyBorder(0, 10, 10, 10)); // Padding for the content

		// Option panel (left sidebar menu - BorderLayout.WEST)
		JPanel optionPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 3 rows (for 3 buttons), 1 column, 10px spacing
		optionPanel.setBackground(colorbg); // Dark background #212f3d
		optionPanel.setPreferredSize(new Dimension(230, 550)); // Preferred width for buttons
		optionPanel.setBorder(new EmptyBorder(0, 20, 0, 0)); // 20px left padding

		// Content display panel (right - BorderLayout.CENTER of contentPanel)
		infoPanel = new JPanel(cardLayout);
		infoPanel.setBackground(displayPanelBg); // Light gray background #ECF0F1

		// Initialize the specific Maintenance sub-panels
		panelSalas = createPanelSalas(); // Call the method that builds the rooms panel
		panelHabitaciones = createPanelHabitaciones(); // Call the method that builds the rooms panel

		// Add the panels to the CardLayout
		infoPanel.add(panelSalas, "Salas");
		infoPanel.add(panelHabitaciones, "Habitaciones");

		String[] buttonLabels = { "Salas", "Habitaciones", "Cerrar Sesión" }; // Simplified "Cerrar Sesión" button text

		for (String buttonLabel : buttonLabels) {
			JButton button = new JButton(buttonLabel);
			// Apply styles, passing true if it's the "Cerrar Sesión" button
			stylePanelButton(button, buttonLabel.equals("Cerrar Sesión"));

			if (buttonLabel.equals("Cerrar Sesión")) {
				button.addActionListener(e -> {
					button.setBackground(Color.decode("#FF6347")); // Color when pressed
					panelImagen.cambiarPanel(new PanelLogin(panelImagen));
				});
			} else {
				button.addActionListener(e -> switchPanel(buttonLabel)); // Switch the panel
			}
			optionPanel.add(button);
		}

		// Assemble the contentPanel
		contentPanel.add(optionPanel, BorderLayout.WEST);
		contentPanel.add(infoPanel, BorderLayout.CENTER);

		// Assemble the mainWrapperPanel
		mainWrapperPanel.add(titlePanel, BorderLayout.NORTH);
		mainWrapperPanel.add(contentPanel, BorderLayout.CENTER);

		// Add the mainWrapperPanel to PanelMantenimiento
		this.add(mainWrapperPanel, BorderLayout.CENTER);

		// Show the first panel by default
		cardLayout.show(infoPanel, "Salas");

		revalidate();
		repaint();
	}

	// Method to create the Rooms panel (extracted and renamed for clarity)
	private JPanel createPanelSalas() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(displayPanelBg); // Use the display panel background color

		// --- Title for this sub-panel (red background) ---
		JPanel titleWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titleWrapper.setBackground(subPanelTitleBgColor); // Set background to RED
		titleWrapper.setBorder(new EmptyBorder(15, 0, 15, 0)); // Padding for the title

		JLabel titulo = new JLabel("Estado de Limpieza - Salas", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 22));
		titulo.setForeground(Color.WHITE); // Make title text white for contrast
		titleWrapper.add(titulo);

		panel.add(titleWrapper, BorderLayout.NORTH); // Add the title wrapper to the NORTH

		JPanel listaSalas = new JPanel();
		listaSalas.setLayout(new BoxLayout(listaSalas, BoxLayout.Y_AXIS));
		listaSalas.setBackground(displayPanelBg); // Use the display panel background color

		String[] salas = { "Sala 101", "Sala 102", "Sala 103", "Sala 104", "Sala 105" };

		for (String sala : salas) {
			JPanel salaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			salaPanel.setBackground(displayPanelBg); // Use the display panel background color
			JLabel salaLabel = new JLabel(sala);
			salaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
			salaPanel.add(salaLabel);

			JRadioButton rbSi = new JRadioButton("Limpia");
			JRadioButton rbNo = new JRadioButton("Pendiente");
			rbSi.setBackground(displayPanelBg); // Use the display panel background color
			rbNo.setBackground(displayPanelBg); // Use the display panel background color
			rbSi.setFont(new Font("Arial", Font.PLAIN, 16));
			rbNo.setFont(new Font("Arial", Font.PLAIN, 16));

			ButtonGroup grupo = new ButtonGroup();
			grupo.add(rbSi);
			grupo.add(rbNo);

			rbNo.setSelected(true);

			salaPanel.add(rbSi);
			salaPanel.add(rbNo);

			listaSalas.add(salaPanel);
		}

		JScrollPane scrollPane = new JScrollPane(listaSalas);
		scrollPane.setBorder(new EmptyBorder(10, 20, 20, 20)); // Use EmptyBorder
		panel.add(scrollPane, BorderLayout.CENTER);

		return panel;
	}

	// Method to create the Rooms panel (extracted and renamed for clarity)
	private JPanel createPanelHabitaciones() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(displayPanelBg); // Use the display panel background color

		// --- Title for this sub-panel (red background) ---
		JPanel titleWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titleWrapper.setBackground(subPanelTitleBgColor); // Set background to RED
		titleWrapper.setBorder(new EmptyBorder(15, 0, 15, 0)); // Padding for the title

		JLabel titulo = new JLabel("Estado de Limpieza - Habitaciones", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 22));
		titulo.setForeground(Color.WHITE); // Make title text white for contrast
		titleWrapper.add(titulo);

		panel.add(titleWrapper, BorderLayout.NORTH); // Add the title wrapper to the NORTH

		JPanel listaHabitaciones = new JPanel();
		listaHabitaciones.setLayout(new BoxLayout(listaHabitaciones, BoxLayout.Y_AXIS));
		listaHabitaciones.setBackground(displayPanelBg); // Use the display panel background color

		String[] habitaciones = { "Habitación 201", "Habitación 202", "Habitación 203", "Habitación 204",
				"Habitación 205" };

		for (String habitacion : habitaciones) {
			JPanel habitacionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			habitacionPanel.setBackground(displayPanelBg); // Use the display panel background color
			JLabel habitacionLabel = new JLabel(habitacion);
			habitacionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
			habitacionPanel.add(habitacionLabel);

			JRadioButton rbSi = new JRadioButton("Limpia");
			JRadioButton rbNo = new JRadioButton("Pendiente");
			rbSi.setBackground(displayPanelBg); // Use the display panel background color
			rbNo.setBackground(displayPanelBg); // Use the display panel background color
			rbSi.setFont(new Font("Arial", Font.PLAIN, 16));
			rbNo.setFont(new Font("Arial", Font.PLAIN, 16));

			ButtonGroup grupo = new ButtonGroup();
			grupo.add(rbSi);
			grupo.add(rbNo);

			rbNo.setSelected(true);

			habitacionPanel.add(rbSi);
			habitacionPanel.add(rbNo);

			listaHabitaciones.add(habitacionPanel);
		}

		JScrollPane scrollPane = new JScrollPane(listaHabitaciones);
		scrollPane.setBorder(new EmptyBorder(10, 20, 20, 20)); // Use EmptyBorder
		panel.add(scrollPane, BorderLayout.CENTER);

		return panel;
	}

	private void switchPanel(String buttonLabel) {
		String panelName = "";
		switch (buttonLabel) {
		case "Salas":
			panelName = "Salas";
			break;
		case "Habitaciones":
			panelName = "Habitaciones";
			break;
		default:
			panelName = "Salas"; // Default to Salas if label doesn't match
			break;
		}
		cardLayout.show(infoPanel, panelName);
		infoPanel.revalidate();
		infoPanel.repaint();
	}

	// Method to style buttons, unified and consistent
	private void stylePanelButton(JButton button, boolean isLogoutButton) {
		button.setFont(new Font("Arial", Font.BOLD, 17)); // Slightly smaller font for consistency with other panels
		button.setFocusPainted(false);
		// Use BorderFactory.createCompoundBorder for consistent internal padding
		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), // 1px black border
				new EmptyBorder(10, 20, 10, 20) // 10px top/bottom, 20px left/right padding
		));

		if (isLogoutButton) {
			button.setBackground(Color.decode("#C08080")); // Original color of the Logout button
			button.setForeground(Color.white);
		} else {
			button.setBackground(colorButton); // Original color of normal buttons #006D77
			button.setForeground(Color.white);
		}
	}
}