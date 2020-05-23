package swsports.gui;

import javax.swing.JFrame;

import swsports.productos.ControladorProductos;
import swsports.proveedores.ControladorProveedores;
import swsports.usuarios.ControladorUsuario;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControladorUsuario controladorUsuario;
	private ControladorProductos controladorProductos;
	private ControladorProveedores controladorProveedores;

	public MainWindow(ControladorUsuario ctrlUsu, ControladorProductos ctrlProd, ControladorProveedores ctrlProv) {
		controladorUsuario = ctrlUsu;
		controladorProductos = ctrlProd;
		controladorProveedores = ctrlProv;
	}
}
