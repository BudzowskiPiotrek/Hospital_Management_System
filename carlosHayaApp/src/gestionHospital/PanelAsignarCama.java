package gestionHospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

class PanelAsignarCama extends JPanel {
     private JTextField txtBuscar;
    private JButton btnBuscar;
    private JComboBox<String> camasCombo;

    public PanelAsignarCama() {
        setLayout(new BorderLayout(0, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Asignar cama a paciente", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Panel búsqueda y formulario
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        txtBuscar = new JTextField();
        btnBuscar = new JButton("Buscar paciente");
        topPanel.add(txtBuscar, BorderLayout.CENTER);
        topPanel.add(btnBuscar, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(1, 2, 10, 10));
        camasCombo = new JComboBox<>(new String[] {"Cama 1","Cama 2","Cama 3"});
        form.add(new JLabel("Cama disponible:"));
        form.add(camasCombo);
        add(form, BorderLayout.CENTER);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = txtBuscar.getText().trim();
                if (query.isEmpty()) {
                    JOptionPane.showMessageDialog(PanelAsignarCama.this, "Introduce nombre o ID.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String[] resultados = buscarPacientes(query);
                if (resultados.length == 0) {
                    JOptionPane.showMessageDialog(PanelAsignarCama.this, "No se encontró paciente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String paciente = (String) JOptionPane.showInputDialog(
                        PanelAsignarCama.this,
                        "Selecciona paciente:",
                        "Resultados de búsqueda",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        resultados,
                        resultados[0]
                    );
                    if (paciente != null) {
                        asignarCama(paciente, (String) camasCombo.getSelectedItem());
                    }
                }
            }
        });
    }

    private String[] buscarPacientes(String query) {
        // TODO: reemplazar con lógica real de búsqueda
        return new String[] { query + " (ID123)" };
    }

    private void asignarCama(String paciente, String cama) {
        // TODO: lógica real de asignación
        JOptionPane.showMessageDialog(
            this,
            "Asignada " + cama + " a " + paciente,
            "Asignación completada",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}