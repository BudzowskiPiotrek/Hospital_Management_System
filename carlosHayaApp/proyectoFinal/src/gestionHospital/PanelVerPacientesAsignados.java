package gestionHospital;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clases.DBConnection;

public class PanelVerPacientesAsignados extends JPanel {  // Cambiado de Component a JPanel

    private Color colorbg = Color.decode("#212f3d");
    private JTable tablaPacientes;
    private DBConnection db;
    private  DefaultTableModel modelo;
    
    public PanelVerPacientesAsignados() {
        setBackground(colorbg);
        setPreferredSize(new Dimension(800, 600));  // Asegúrate de darle un tamaño adecuado
        initComponents();
        
    }

    private void initComponents() {
        // Crear el título
        JLabel titulo = new JLabel("Pacientes Asignados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);  // Usa add() directamente de JPanel

        // Columnas de la tabla
        String[] columnas = { "ID", "Nombre", "Apellido", "Contacto","Obra social", "Sala" };
        
        //Conexión con base de datos
        db=new DBConnection();

        modelo = new DefaultTableModel( columnas, 0) {
            // Evitar que las celdas sean editables
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //Se carga la lista pacientes asignados
        cargaDatos();

        tablaPacientes = new JTable(modelo);
        tablaPacientes.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaPacientes.setRowHeight(25);
        tablaPacientes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Crear un JScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaPacientes);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Añadir el JScrollPane al centro del panel
        add(scrollPane, BorderLayout.CENTER);  // Usa add() directamente de JPanel
    }

	private void cargaDatos() {
		  // Limpiar tabla antes de cargar
        modelo.setRowCount(0);

        ArrayList<Object[][]> resultados = db.pacientesAsignados(Sesion.getUsuarioLogueado());

        // Cada Object[][] es una fila, en el método pacienteAsignados se define así:
        // Object[][] datos = {{dni,nombre,apellido,...}};
        for (Object[][] filaArray : resultados) {
            // filaArray[0] es el array con los valores
            modelo.addRow(filaArray[0]);
        }
		
	}
}
