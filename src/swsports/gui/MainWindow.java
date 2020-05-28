package swsports.gui;

import javax.swing.JFrame;

import swsports.gui.productos.CarritoPanel;
import swsports.gui.productos.EnumModoPanelProductos;
import swsports.gui.productos.PanelMainProductos;
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
	private Usuario usuario;

	public MainWindow(Usuario usu, Controlador ctrl) {
		this.ctrl = ctrl;
		this.usuario = usu;
		getContentPane().setBackground(Color.WHITE);

		setTitle("Software Sports");
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addTab("Perfil", null, new PerfilMainPanel(usu, ctrl.getControladorUsuario()),
				"Consultar su perfil");

		Color panelBG = Color.WHITE;
		JPanel panel = new JPanel();
		// JToolBar panel = new JToolBar();
		panel.setForeground(Color.BLACK);
		// panel.setFloatable(false);
		panel.setBackground(panelBG);
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		// panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));

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
			
			tabbedPane.addTab("Productos", null, new PanelMainProductos(ctrl.getControladorProductos(), EnumModoPanelProductos.PRODUCTOS, null), 
					"Consultar y manejar los productos guardados en la base de datos");
		}
		
		tabbedPane.addTab("Tienda", null, new PanelMainProductos(ctrl.getControladorProductos(),  EnumModoPanelProductos.TIENDA, this.usuario.getCarrito()), 
				"Consultar y comprar los productos disponibles en la tienda");
		/*
		tabbedPane.addTab("Carrito", null, new PanelMainProductos(ctrl.getControladorProductos(),  EnumModoPanelProductos.CARRITO, this.usuario.getCarrito()), 
				"Consultar productos del carrito y tramitar pedido");*/
		
		tabbedPane.addTab("Carrito", null, new CarritoPanel(ctrl.getControladorProductos(), this.usuario.getCarrito()), 
				"Consultar productos del carrito y tramitar pedido");
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setMinimumSize(getSize());
		this.setVisible(true);
	}

	private void cerrarSesion() {
		int n = JOptionPane.showOptionDialog(this, "�Seguro que quiere cerrar sesi�n?", "Cerrar sesi�n",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
		if (n == JOptionPane.OK_OPTION)
			ctrl.cerrarSesion(this);
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
