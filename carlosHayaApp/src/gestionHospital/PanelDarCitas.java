package gestionHospital;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
class PanelDarCitas extends JPanel {

  // Campos del formulario declarados aquí para poder acceder a ellos
  private JTextField dniField;
  private JTextField dayField;
  private JTextField timeField;

  public PanelDarCitas() {
    setLayout(new BorderLayout()); // Eliminado el espaciado horizontal y vertical para el panel principal
    setBackground(Color.decode("#212f3d")); // Color de fondo general del panel
    setBorder(new EmptyBorder(15, 15, 15, 15)); // Espaciado interno del panel principal

    // 1. Panel para el título (panelLabel)
    JPanel panelLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panelLabel.setBackground(Color.decode("#E3242B")); // Color de fondo rojo para el título
    JLabel formTitle = new JLabel("Introducir Datos de la Cita", SwingConstants.CENTER);
    formTitle.setFont(new Font("Roboto", Font.BOLD, 25));
    formTitle.setForeground(Color.white); // Color del texto del título
    panelLabel.add(formTitle);
    panelLabel.setPreferredSize(new Dimension(panelLabel.getPreferredSize().width, 60)); // Altura de 60px
    add(panelLabel, BorderLayout.NORTH); // Añadir el panel del título en la parte superior

    // Panel para los campos de entrada del formulario (fieldsPanel)
    JPanel fieldsPanel = new JPanel(new GridBagLayout());
    fieldsPanel.setBackground(Color.white); // CAMBIO AQUÍ: Color de fondo blanco para el panel de campos
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(8, 8, 8, 8); // Espaciado entre componentes
    gbc.fill = GridBagConstraints.HORIZONTAL; // Los componentes se expanden horizontalmente
    gbc.weightx = 1.0; // Permitir que los campos de texto se expandan

    // Campo para el DNI
    gbc.gridx = 0;
    gbc.gridy = 0;
    fieldsPanel.add(new JLabel("DNI de la Persona:"), gbc);
    dniField = new JTextField(20);
    gbc.gridx = 1;
    fieldsPanel.add(dniField, gbc);

    // Campo para el Día
    gbc.gridx = 0;
    gbc.gridy = 1;
    fieldsPanel.add(new JLabel("Día (YYYY-MM-DD):"), gbc);
    dayField = new JTextField(20);
    gbc.gridx = 1;
    fieldsPanel.add(dayField, gbc);

    // Campo para la Hora
    gbc.gridx = 0;
    gbc.gridy = 2;
    fieldsPanel.add(new JLabel("Hora (HH:MM):"), gbc);
    timeField = new JTextField(20);
    gbc.gridx = 1;
    fieldsPanel.add(timeField, gbc);

    add(fieldsPanel, BorderLayout.CENTER); // Añadir el panel de campos al centro

    // Panel para los botones Guardar y Cancelar (buttonPanel)
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    buttonPanel.setBackground(Color.decode("#728C69")); // Color de fondo verde para el panel de botones

    // Botón Guardar Cita
    JButton saveButton = new JButton("Guardar Cita");
    styleButton(saveButton, Color.decode("#006D77"));
    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String dni = dniField.getText();
        String day = dayField.getText();
        String time = timeField.getText();

        if (dni.isEmpty() || day.isEmpty() || time.isEmpty()) {
          JOptionPane.showMessageDialog(PanelDarCitas.this, "Por favor, complete todos los campos.", "Campos Vacíos",
              JOptionPane.WARNING_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(PanelDarCitas.this,
              "Cita guardada para DNI: " + dni + ", Día: " + day + ", Hora: " + time, "Cita Guardada",
              JOptionPane.INFORMATION_MESSAGE);
          clearFields(); // Limpiar campos después de guardar
        }
      }
    });
    buttonPanel.add(saveButton);

    // Botón Cancelar
    JButton cancelButton = new JButton("Borar");
    styleButton(cancelButton, Color.decode("#C08080"));
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(PanelDarCitas.this, "Creación de cita borrado.", "Cita Cancelada",
            JOptionPane.INFORMATION_MESSAGE);
        clearFields(); // Limpiar campos al cancelar
      }
    });
    buttonPanel.add(cancelButton);

    buttonPanel.setPreferredSize(new Dimension(buttonPanel.getPreferredSize().width, 80)); // Altura de 80px
    add(buttonPanel, BorderLayout.SOUTH); // Añadir el panel de botones a la parte inferior
  }

  /**
   * Limpia los campos del formulario.
   */
  private void clearFields() {
    dniField.setText("");
    dayField.setText("");
    timeField.setText("");
  }

  /**
   * Aplica un estilo consistente a los botones dentro de este panel.
   *
   * @param button  El JButton al que se aplicará el estilo.
   * @param bgColor El color de fondo del botón.
   */
  private void styleButton(JButton button, Color bgColor) {
    button.setBackground(bgColor);
    button.setForeground(Color.white);
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    button.setPreferredSize(new Dimension(150, 35));
  }
}
