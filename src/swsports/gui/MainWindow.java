package swsports.gui;

import javax.swing.JFrame;

import swsports.gui.usuarios.PanelMainUsuarios;
import swsports.main.Controlador;
import swsports.modelo.Usuario;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Dimension;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador ctrl;

	public MainWindow(Usuario usu, Controlador ctrl) {
		getContentPane().setBackground(Color.WHITE);
		this.ctrl = ctrl;

		setTitle("Software Sports");
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addTab("Perfil", null, new PerfilMainPanel(usu, ctrl.getControladorUsuario()),
				"Consultar su perfil");

		Color panelBG = Color.WHITE;
		JPanel panel = new JPanel();
		//JToolBar panel = new JToolBar();
		panel.setForeground(Color.BLACK);
		//panel.setFloatable(false);
		panel.setBackground(panelBG);
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		//panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

		JButton btnCerrarSesion = new JButton(new ImageIcon("icons/cerrar_sesion.png"));
		btnCerrarSesion.setSize(new Dimension(0, 15));
		btnCerrarSesion.setBorderPainted(false);
		btnCerrarSesion.setForeground(Color.BLACK);
		btnCerrarSesion.setHorizontalAlignment(SwingConstants.RIGHT);
		btnCerrarSesion.setBackground(panelBG);
		btnCerrarSesion.setBorder(BorderFactory.createEmptyBorder());
		btnCerrarSesion.addActionListener(a -> cerrarSesion());

		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		panel.add(btnCerrarSesion);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);

		if (usu.esAdmin()) {
			tabbedPane.addTab("Usuarios", null, new PanelMainUsuarios(ctrl.getControladorUsuario()),
					"Consultar y manejar los usuarios guardados en la base de datos");
		}
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setMinimumSize(getSize());
		this.setVisible(true);
	}

	private void cerrarSesion() {
		int n = JOptionPane.showOptionDialog(this, "¿Seguro que quiere cerrar sesión?", "Cerrar sesión",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
		if(n == JOptionPane.OK_OPTION)
			ctrl.cerrarSesion(this);
	}
}
