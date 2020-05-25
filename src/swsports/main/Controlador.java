package swsports.main;

import javax.swing.JFrame;

import swsports.gui.LoginWindow;
import swsports.gui.MainWindow;
import swsports.modelo.Usuario;
import swsports.productos.ControladorProductos;
import swsports.proveedores.ControladorProveedores;
import swsports.usuarios.ControladorUsuario;

/**
 * Clase que maneja la creaci�n y cierre de ventanas. Tambi�n se encarga de
 * crear los controladores de cada m�dulo de la aplicaci�n y tiene m�todos get()
 * para obtenerlos.
 */
public class Controlador {

	private ControladorUsuario controladorUsuario;
	private ControladorProductos controladorProductos;
	private ControladorProveedores controladorProveedores;

	/**
	 * Crea un {@link Controlador}.
	 */
	public Controlador() {
		controladorUsuario = new ControladorUsuario();
		controladorProductos = new ControladorProductos();
		controladorProveedores = new ControladorProveedores();
	}

	/**
	 * Cierra la ventana de {@link MainWindow} y vuelve a crear una ventana
	 * {@link LoginWindow}.
	 * 
	 * @param mWindow Ventana {@link MainWindow} que se cierra llamando a
	 *                {@link JFrame#dispose()}.
	 */
	public void cerrarSesion(MainWindow mWindow) {
		mWindow.dispose();
		start();
	}

	/**
	 * Devuelve el controlador del m�dulo Productos.
	 * 
	 * @return Controlador del m�dulo Productos.
	 */
	public ControladorProductos getControladorProductos() {
		return controladorProductos;
	}

	/**
	 * Devuelve el controlador del m�dulo Proveedores.
	 * 
	 * @return Controlador del m�dulo Proveedores.
	 */
	public ControladorProveedores getControladorProveedores() {
		return controladorProveedores;
	}

	/**
	 * Devuelve el controlador del m�dulo Usuarios.
	 * 
	 * @return Controlador del m�dulo Usuarios.
	 */
	public ControladorUsuario getControladorUsuario() {
		return controladorUsuario;
	}

	/**
	 * Cierra la ventana de {@link LoginWindow} y crea la ventana principal
	 * {@link MainWindow}.
	 * 
	 * @param lWindow Ventana de login que tenemos que cerrar (llama a
	 *                {@link JFrame#dispose()}.
	 * @param usu     El {@link Usuario} que ha iniciado sesi�n es necesario para
	 *                crear {@link MainWindow}.
	 */
	public void launchMainWindow(LoginWindow lWindow, Usuario usu) {
		lWindow.dispose();
		new MainWindow(usu, this);
	}

	/**
	 * Aqu� empieza el programa. Crea una ventana {@link LoginWindow}.
	 */
	public void start() {
		new LoginWindow(this);
	}
}
