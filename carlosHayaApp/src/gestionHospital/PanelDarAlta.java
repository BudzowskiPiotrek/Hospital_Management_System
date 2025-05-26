package gestionHospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

class PanelDarAlta extends JPanel {
   private JTextField txtBuscar;
    private JButton btnBuscar;
    private JList<String> listaPacientes;
    private DefaultListModel<String> listModel;

    public PanelDarAlta() {
        setLayout(new BorderLayout(0, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Dar de alta a paciente", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        txtBuscar = new JTextField();
        btnBuscar = new JButton("Buscar paciente");
        searchPanel.add(txtBuscar, BorderLayout.CENTER);
        searchPanel.add(btnBuscar, BorderLayout.EAST);
        add(searchPanel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        listaPacientes = new JList<>(listModel);
        listaPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(listaPacientes), BorderLayout.CENTER);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = txtBuscar.getText().trim();
                if (query.isEmpty()) {
                    JOptionPane.showMessageDialog(PanelDarAlta.this, "Introduce nombre o ID.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String[] resultados = buscarPacientes(query);
                listModel.clear();
                for (String p : resultados) listModel.addElement(p);
            }
        });

        JButton btnAlta = new JButton("Dar de alta");
        btnAlta.setFont(new Font("Arial", Font.BOLD, 16));
        btnAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sel = listaPacientes.getSelectedValue();
                if (sel == null) {
                    JOptionPane.showMessageDialog(PanelDarAlta.this, "Selecciona un paciente.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                darAlta(sel);
            }
        });
        JPanel btnPanel = new JPanel(); btnPanel.add(btnAlta);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private String[] buscarPacientes(String query) {
        // TODO: reemplazar con lógica real de búsqueda
        return new String[] { query + " (ID123) - Hab. 101" };
    }

    private void darAlta(String paciente) {
        // TODO: lógica real de alta
        JOptionPane.showMessageDialog(
            this,
            "Paciente dado de alta:\n" + paciente,
            "Alta completada",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
