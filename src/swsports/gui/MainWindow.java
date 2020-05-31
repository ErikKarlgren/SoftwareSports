package swsports.gui;

import javax.swing.JFrame;

import swsports.gui.productos.EnumModoPanelProductos;
import swsports.gui.productos.PanelMainCarrito;
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

/**
 * Ventana principal de la aplicaci�n. Se accede a esta ventana despu�s de que
 * el usuario haya iniciado sesi�n.
 */
public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador ctrl;
	private Usuario usuario;
	private PanelMainUsuarios pmUsuarios;
	private PanelMainProductos pmProductos;
	private PanelMainProductos pTienda;
	private PanelMainCarrito pmCarrito;

	/**
	 * Crea un {@link MainWindow} a partir del usuario que ha iniciado sesi�n y el
	 * controlador principal del programa.
	 * 
	 * @param usu  {@link Usuario} que ha iniciado sesi�n.
	 * @param ctrl {@link Controlador} de la aplicaci�n.
	 */
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
		panel.setForeground(Color.BLACK);
		panel.setBackground(panelBG);
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

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

		anyadirPaneles(tabbedPane);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setMinimumSize(getSize());
		this.setVisible(true);
	}

	/**
	 * A�ade los paneles principales a la ventana.
	 * 
	 * @param tabbedPane {@link JTabbedPane} en el que van los paneles.
	 */
	private void anyadirPaneles(JTabbedPane tabbedPane) {
		if (usuario.esAdmin()) {
			pmUsuarios = new PanelMainUsuarios(this, ctrl.getControladorUsuario());
			pmProductos = new PanelMainProductos(this, ctrl.getControladorProductos(), EnumModoPanelProductos.PRODUCTOS,
					null);

			tabbedPane.addTab("Usuarios", null, pmUsuarios,
					"Consultar y manejar los usuarios guardados en la base de datos");
			tabbedPane.addTab("Productos", null, pmProductos,
					"Consultar y manejar los productos guardados en la base de datos");
		}

		pTienda = new PanelMainProductos(this, ctrl.getControladorProductos(), EnumModoPanelProductos.TIENDA,
				this.usuario.getCarrito());
		pmCarrito = new PanelMainCarrito(this, ctrl.getControladorProductos());

		tabbedPane.addTab("Tienda", null, pTienda, "Consultar y comprar los productos disponibles en la tienda");
		tabbedPane.addTab("Carrito", null, pmCarrito, "Consultar productos del carrito y tramitar pedido");
	}

	/**
	 * Pide confirmaci�n al usuario para cerrar la sesi�n actual.
	 */
	private void cerrarSesion() {
		int n = JOptionPane.showOptionDialog(this, "\u00bfSeguro que quiere cerrar sesi\u00f3n?", "Cerrar sesi\u00f3n",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
		if (n == JOptionPane.OK_OPTION) {
			ctrl.cerrarSesion(this);
			this.usuario.getCarrito().vaciarCarrito(false);
		}
	}

	/**
	 * @return {@link Usuario} que ha iniciado sesi�n.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Actualiza el panel para el carrito.
	 */
	public void actualizarPanelCarrito() {
		pmCarrito.actualizar();
	}
}
