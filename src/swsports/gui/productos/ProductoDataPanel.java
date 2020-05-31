package swsports.gui.productos;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import swsports.gui.DataPanel;
import swsports.gui.EditarProductoPanel;
import swsports.gui.MainWindow;
import swsports.modelo.Carrito;
import swsports.modelo.Producto;
import swsports.productos.ControladorProductos;

/**
 * Panel que muestra los datos de un {@link Producto}. Permite adem谩s editar sus
 * datos y darlo de baja.
 */
public class ProductoDataPanel extends DataPanel<Producto> {
	
	/**
	 * Di醠ogo que muestra el panel para editar el producto.
	 */
	private class EditarProductoDialog extends JDialog {

		private static final long serialVersionUID = 1L;

		EditarProductoDialog() {
			super();
			this.setTitle("Editar producto");
			this.add(new EditarProductoPanel(object, controlador, false));
			this.pack();
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControladorProductos controlador;
	private EnumModoPanelProductos modo;

	/**
	 * Crea un panel con los datos de un {@link Producto}. El {@link ControladorProducto}
	 * es necesario para poder realizar las acciones de editar el producto o darlo de baja.
	 * 
	 * @param owner Ventana principal.
	 * @param ctrl Controlador para poder interactuar con el resto del programa.
	 * @param prod {@link Producto} del que queremos mostrar sus datos.
	 * @param m {@link EnumModoPanelProductos} enumerado para mostrar unas opciones u otras en funci贸n del modo.
	 * @param c {@link Carrito} Carrito necesario para a帽adir productos.
	 */
	public ProductoDataPanel(MainWindow owner, ControladorProductos ctrl, Producto prod, EnumModoPanelProductos m, Carrito c) {
		super(owner, prod);
		this.controlador = ctrl;
		this.modo = m;
		addData(c, prod);
		addActions(c);
	}

	/**
	 * A帽ade las acciones del panel en funci贸n del modo:
	 * 		- Si se encuentra en modo productos (gesti贸n), podremos editar dicho producto o eliminarlo.
	 * 		- Si estamos en modo tienda, podremos a帽adir el producto a nuestro carrito.
	 * 		- Si estamos en modo carrito, podremos eliminar el producto de nuestro carrito.
	 */
	private void addActions(Carrito c) {

		if (this.modo == EnumModoPanelProductos.PRODUCTOS) {
			addAction("Editar producto", a -> new EditarProductoDialog());
			addAction("Eliminar producto", a -> eliminarProducto());
		}

		else if (this.modo == EnumModoPanelProductos.TIENDA) {
			addAction("A\u00f1adir al carrito", a -> anyadirCarrito(c));
		}

		else {
			addAction("Eliminar del carrito", a -> eliminarCarrito(c));
		}
	}

	/**
	 * A帽ade los campos del producto al panel en funci贸n del modo.
	 * En todos los modos mostraremos: su identificador, su nombre, su descripci贸n y su precio
	 * En modo productos (gesti贸n) y modo tienda mostraremos el n煤mero de unidades restantes.
	 * En modo carrito, mostraremos las unidades que ha adquirido el usuario.
	 * 
	 * @param c {@link Carrito} Carrito para saber las unidades de un producto.
	 * @param p {@link Producto} Producto del que mostraremos sus datos.
	 * 
	 */
	private void addData(Carrito c, Producto p) {
		addDataField("Nombre", String.valueOf(object.getNombre()));
		addDataField("Descripci\u00f3n", object.getDesc());

		if (this.modo == EnumModoPanelProductos.CARRITO) {
			addDataField("Unidades", Integer.toString(c.getNumUnidadesProducto(p)));
		}

		else {
			addDataField("Stock", String.valueOf(object.getStock()));
		}

		addDataField("Precio", String.valueOf(object.getPrecio()));
	}

	/**
	 * Da de baja a un {@link Producto} si el usuario de la aplicaci贸n confirma su
	 * decisi贸n.
	 */
	private void eliminarProducto() {
		String[] options = { "Si", "No" };
		int option = JOptionPane.showOptionDialog(this, "\u00bfSeguro que quieres dar de baja este producto?",
				"Dar producto de baja", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlador.bajaProducto(object);
		}
	}

	/**
	 * A馻de un producto al carrito si el usuario confirma la decisi贸n.
	 * @param c {@link Carrito} Carrito al que a帽adimos el producto.
	 */
	private void anyadirCarrito(Carrito c) {
		String[] options = { "Si", "No" };
		int option = JOptionPane.showOptionDialog(this, "Quieres a\u00f1adir este producto a tu carrito?",
				"Anyadir carrito", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlador.anyadirProducto(object, c);
			owner.actualizarPanelCarrito();
		}
	}

	/**
	 * Elimina un producto del carrito si el usuario confirma la decisi贸n.
	 * @param c {@link Carrito} Carrito del que eliminamos el producto.
	 */
	private void eliminarCarrito(Carrito c) {
		String[] options = { "Si", "No" };
		int option = JOptionPane.showOptionDialog(this, "Quieres eliminar este producto de tu carrito?",
				"Eliminar del carrito", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlador.quitarProducto(object, c);
			owner.actualizarPanelCarrito();
		}
	}
}