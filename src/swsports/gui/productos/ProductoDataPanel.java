package swsports.gui.productos;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import swsports.gui.DataPanel;
import swsports.gui.EditarProductoPanel;
import swsports.modelo.Producto;
import swsports.productos.ControladorProductos;

public class ProductoDataPanel extends DataPanel<Producto> {

	private static final long serialVersionUID = 1L;
	
	private ControladorProductos controlador;
	private boolean esModoTienda;
	
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
	
	public ProductoDataPanel(Producto prod, ControladorProductos ctrl, boolean tienda) {
		super(prod);
		this.controlador = ctrl;
		this.esModoTienda = tienda;
		addData();
		addActions();
	}

	private void addActions() {
		
		if(esModoTienda) {
			addAction("Anyadir al carrito", a -> anyadirCarrito());
		}
		
		else {
			addAction("Editar producto", a -> new EditarProductoDialog());
			addAction("Eliminar producto", a -> eliminarProducto());
		}	
	}

	private void addData() {
		addDataField("Nombre", String.valueOf(object.getNombre()));
		addDataField("Descripcion", object.getDesc());
		addDataField("Stock", String.valueOf(object.getStock()));
		addDataField("Precio", String.valueOf(object.getPrecio()));
	}

	private void eliminarProducto() {
		String[] options = { "Si", "No" };
		int option = JOptionPane.showOptionDialog(this, "Seguro que quieres dar de baja este producto?",
				"Dar producto de baja", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlador.bajaProducto(object);
		}
	}
	
	private void anyadirCarrito() {
		String[] options = { "Si", "No" };
		int option = JOptionPane.showOptionDialog(this, "Quieres anyadir este producto a tu carrito?",
				"Anyadir carrito", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlador.anyadirProducto(object);
		}
	}
}