package gestionHospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

class PanelAsignarCama extends JPanel {
    private JComboBox<String> pacientesCombo;
    private JComboBox<String> camasCombo;

    public PanelAsignarCama() {
        setLayout(new BorderLayout(0, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Asignar cama a paciente", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.add(new JLabel("Paciente:"));
        pacientesCombo = new JComboBox<>(new String[]{"Paciente A","Paciente B","Paciente C"});
        form.add(pacientesCombo);

        form.add(new JLabel("Cama disponible:"));
        camasCombo = new JComboBox<>(new String[]{"Cama 1","Cama 2","Cama 3"});
        form.add(camasCombo);
        add(form, BorderLayout.CENTER);

        JButton btnAsignar = new JButton("Asignar");
        btnAsignar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAsignar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String paciente = (String) pacientesCombo.getSelectedItem();
                String cama = (String) camasCombo.getSelectedItem();
                JOptionPane.showMessageDialog(
                    PanelAsignarCama.this,
                    "Asignada la " + cama + " al paciente " + paciente,
                    "Asignación completada",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        JPanel btnPanel = new JPanel(); btnPanel.add(btnAsignar);
        add(btnPanel, BorderLayout.SOUTH);
}
}