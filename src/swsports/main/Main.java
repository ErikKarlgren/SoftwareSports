package swsports.main;

import swsports.gui.LoginWindow;
import swsports.productos.ControladorProductos;
import swsports.proveedores.ControladorProveedores;
import swsports.usuarios.ControladorUsuario;

public class Main {

	public static void main(String[] args) {
		ControladorUsuario cUsu = new ControladorUsuario();
		ControladorProductos cProd = new ControladorProductos();
		ControladorProveedores cProv = new ControladorProveedores();
		new LoginWindow(cUsu, cProd, cProv);
		System.out.println("Main acabado");
	}

}