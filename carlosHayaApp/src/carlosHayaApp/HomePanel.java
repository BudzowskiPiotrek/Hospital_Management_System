package carlosHayaApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HomePanel extends JPanel {
	private App app;

	public HomePanel(App app) {
		this.app = app;
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

        // Menú superior
        JMenuBar barraMenu = new JMenuBar();

        JMenu menuPersonal = new JMenu("Personal");
        JMenuItem itemEmpleados = new JMenuItem("Empleados");
        itemEmpleados.addActionListener(e -> app.abrirMarcoInterno(new MarcoGestionEmpleados()));
        menuPersonal.add(itemEmpleados);

        JMenuItem itemRolesTurnos = new JMenuItem("Roles y Turnos");
        /*itemRolesTurnos.addActionListener(e -> app.abrirMarcoInterno(new MarcoAsignacionRolesTurnos()));*/
        menuPersonal.add(itemRolesTurnos);
        barraMenu.add(menuPersonal);

        JMenu menuPacientes = new JMenu("Pacientes");
        JMenuItem itemRegistroPaciente = new JMenuItem("Registro");
        /*itemRegistroPaciente.addActionListener(e -> app.abrirMarcoInterno(new MarcoRegistroPacientes()));*/
        menuPacientes.add(itemRegistroPaciente);

        JMenuItem itemHistorialMedico = new JMenuItem("Historial Médico");
        /*itemHistorialMedico.addActionListener(e -> app.abrirMarcoInterno(new MarcoHistorialMedico()));*/
        menuPacientes.add(itemHistorialMedico);
        barraMenu.add(menuPacientes);

        JMenu menuInfraestructura = new JMenu("Infraestructura");
        JMenuItem itemSalas = new JMenuItem("Salas");
        /*itemSalas.addActionListener(e -> app.abrirMarcoInterno(new MarcoGestionSalas()));*/
        menuInfraestructura.add(itemSalas);
        barraMenu.add(menuInfraestructura);

        JMenu menuTurnos = new JMenu("Turnos");
        JMenuItem itemSolicitudTurno = new JMenuItem("Solicitar Turno");
        /*itemSolicitudTurno.addActionListener(e -> app.abrirMarcoInterno(new MarcoSolicitudTurno()));*/
        menuTurnos.add(itemSolicitudTurno);

        JMenuItem itemAgenda = new JMenuItem("Agenda");
        /*itemAgenda.addActionListener(e -> app.abrirMarcoInterno(new MarcoAgenda()));*/
        menuTurnos.add(itemAgenda);
        barraMenu.add(menuTurnos);

        JMenu menuReportes = new JMenu("Reportes");
        JMenuItem itemPacientesInternos = new JMenuItem("Pacientes Internados");
        /*itemPacientesInternos.addActionListener(e -> app.abrirMarcoInterno(new MarcoReportePacientesInternos()));*/
        menuReportes.add(itemPacientesInternos);

        JMenuItem itemDisponibilidadSalas = new JMenuItem("Disponibilidad Salas");
        /*itemDisponibilidadSalas.addActionListener(e -> app.abrirMarcoInterno(new MarcoReporteDisponibilidadSalas()));*/
        menuReportes.add(itemDisponibilidadSalas);
        barraMenu.add(menuReportes);

        
        add(barraMenu, BorderLayout.NORTH);

       
        add(app.getEscritorio(), BorderLayout.CENTER);
	}

}
