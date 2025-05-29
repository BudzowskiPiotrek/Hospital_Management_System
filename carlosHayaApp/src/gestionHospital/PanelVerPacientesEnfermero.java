package gestionHospital;

import java.awt.*;
import java.util.ArrayList;
import java.util.List; // Usamos List en lugar de ArrayList en algunos puntos para generalizar
import javax.swing.table.JTableHeader; // Importado explícitamente para mayor claridad
import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

// Eliminamos la importación de clases.DBConnection
// import clases.DBConnection; 

@SuppressWarnings("serial")
public class PanelVerPacientesEnfermero extends JPanel {

	private final Color primaryBackgroundColor = Color.decode("#212f3d");
	private final Color titlePanelColor = Color.RED;
	private final Color titleTextColor = Color.WHITE;
	private final Color headerBackgroundColor = Color.decode("#2C3E50");
	private final Color headerTextColor = Color.WHITE;

	private JTable tablaPacientes;
	// Eliminamos la declaración de DBConnection
	// private DBConnection db;
	private DefaultTableModel modelo;

	public PanelVerPacientesEnfermero() {
		setLayout(new BorderLayout(10, 10)); // Espaciado entre componentes principales
		setBackground(primaryBackgroundColor); // Fondo del panel principal
		setPreferredSize(new Dimension(800, 600)); // Tamaño preferido

		// Eliminamos la inicialización de DBConnection
		// db = new DBConnection();

		initComponents();
	}

	private void initComponents() {
		// --- Panel para el título (NORTE) ---
		JPanel titleContainerPanel = new JPanel();
		titleContainerPanel.setBackground(titlePanelColor); // Fondo rojo
		titleContainerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20)); // Padding
		titleContainerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel titulo = new JLabel("Pacientes Asignados", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 28));
		titulo.setForeground(titleTextColor); // Texto blanco
		titleContainerPanel.add(titulo);
		add(titleContainerPanel, BorderLayout.NORTH);

		// --- Configuración de la tabla (CENTRO) ---
		String[] columnas = { "ID", "Nombre", "Apellido", "Contacto", "Obra Social", "Sala" };

		modelo = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L; // serialVersionUID es buena práctica

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Las celdas de la tabla no son editables
			}
		};

		// Llamada a cargaDatos() para llenar la tabla con ejemplos
		cargaDatos();

		tablaPacientes = new JTable(modelo);
		tablaPacientes.setFont(new Font("Arial", Font.PLAIN, 14));
		tablaPacientes.setRowHeight(25);
		tablaPacientes.setFillsViewportHeight(true); // Para que ocupe todo el espacio en el JScrollPane
		tablaPacientes.setGridColor(Color.LIGHT_GRAY); // Color de las líneas de la cuadrícula
		tablaPacientes.setSelectionBackground(Color.decode("#006D77").brighter()); // Color de selección
		tablaPacientes.setSelectionForeground(Color.WHITE); // Color del texto seleccionado

		// --- Renderer personalizado para la cabecera de la tabla ---
		DefaultTableCellRenderer customHeaderRenderer = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);

				label.setBackground(headerBackgroundColor); // Fondo de la cabecera
				label.setForeground(headerTextColor); // Color del texto de la cabecera
				label.setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto
				label.setOpaque(true); // Necesario para que el color de fondo se muestre

				return label;
			}
		};

		// Asignar el renderer a cada columna de la cabecera
		JTableHeader tableHeader = tablaPacientes.getTableHeader();
		tableHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente negrita para la cabecera
		tableHeader.setReorderingAllowed(false); // Evitar que el usuario reordene columnas
		tableHeader.setResizingAllowed(true); // Permitir redimensionar columnas (opcional)

		for (int i = 0; i < tablaPacientes.getColumnModel().getColumnCount(); i++) {
			tablaPacientes.getColumnModel().getColumn(i).setHeaderRenderer(customHeaderRenderer);
		}

		// --- JScrollPane para la tabla ---
		JScrollPane scrollPane = new JScrollPane(tablaPacientes);
		// Ajustar el borde del scrollPane para que coincida con el padding general del
		// panel
		scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); // Padding alrededor de la tabla
		add(scrollPane, BorderLayout.CENTER);

		// Ajustar anchos de columna al inicio
		adjustColumnWidths(tablaPacientes);
	}

	// Método que carga datos de ejemplo, simulando una consulta a la base de datos
	private void cargaDatos() {
		modelo.setRowCount(0); // Limpiar filas existentes

		// Datos de ejemplo hardcodeados
		List<Object[]> pacientesEjemplo = new ArrayList<>();
		pacientesEjemplo.add(new Object[] { "P001", "Ana", "García", "611223344", "Sanitas", "Sala A1" });
		pacientesEjemplo.add(new Object[] { "P002", "Luis", "Martínez", "655443322", "Mapfre", "Sala B2" });
		pacientesEjemplo.add(new Object[] { "P003", "Clara", "Ruiz", "699887766", "Adeslas", "UCI" });
		pacientesEjemplo.add(new Object[] { "P004", "Sergio", "Fernández", "677889900", "Seg. Social", "Sala C3" });
		pacientesEjemplo.add(new Object[] { "P005", "Marta", "Sánchez", "633445566", "DKV", "Sala A2" });

		// Añadir los datos de ejemplo al modelo de la tabla
		for (Object[] paciente : pacientesEjemplo) {
			modelo.addRow(paciente);
		}
	}

	// Método para ajustar automáticamente el ancho de las columnas (copiado de
	// otros paneles)
	private void adjustColumnWidths(JTable table) {
		for (int column = 0; column < table.getColumnCount(); column++) {
			table.getColumnModel().getColumn(column).setPreferredWidth(120); // Ancho predeterminado
			int maxWidth = 0;
			// Calcular ancho basado en el contenido de las celdas
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
				Component c = table.prepareRenderer(cellRenderer, row, column);
				maxWidth = Math.max(c.getPreferredSize().width + table.getIntercellSpacing().width, maxWidth);
			}
			// Calcular ancho basado en el encabezado de la columna
			TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
			Component headerComp = headerRenderer.getTableCellRendererComponent(table,
					table.getColumnModel().getColumn(column).getHeaderValue(), false, false, 0, column);
			maxWidth = Math.max(maxWidth, headerComp.getPreferredSize().width + table.getIntercellSpacing().width);

			table.getColumnModel().getColumn(column).setPreferredWidth(maxWidth + 10); // Añadir un poco de padding
																						// extra
		}
	}
}