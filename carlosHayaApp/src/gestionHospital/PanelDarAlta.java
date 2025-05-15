package gestionHospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

class PanelDarAlta extends JPanel {
    private JList<String> listaPacientes;

    public PanelDarAlta() {
        setLayout(new BorderLayout(0, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Dar de alta a paciente", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        listaPacientes = new JList<>(new String[]{
            "Paciente A - Hab. 101",
            "Paciente B - Hab. 102",
            "Paciente C - Hab. 103"
        });
        listaPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(listaPacientes), BorderLayout.CENTER);

        JButton btnAlta = new JButton("Dar de alta");
        btnAlta.setFont(new Font("Arial", Font.BOLD, 16));
        btnAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sel = listaPacientes.getSelectedValue();
                if (sel == null) {
                    JOptionPane.showMessageDialog(
                        PanelDarAlta.this,
                        "Debe seleccionar un paciente.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                JOptionPane.showMessageDialog(
                    PanelDarAlta.this,
                    "Paciente dado de alta:\n" + sel,
                    "Alta completada",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        JPanel btnPanel = new JPanel(); btnPanel.add(btnAlta);
        add(btnPanel, BorderLayout.SOUTH);
} 
}