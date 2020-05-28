package swsports.gui.productos;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import swsports.gui.DataPanel;
import swsports.gui.EditarProductoPanel;
import swsports.modelo.Carrito;
import swsports.modelo.Producto;
import swsports.productos.ControladorProductos;

public class ProductoDataPanel extends DataPanel<Producto> {

	private static final long serialVersionUID = 1L;
	
	private ControladorProductos controlador;
	private EnumModoPanelProductos modo;
	
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
	
	public ProductoDataPanel(Producto prod, ControladorProductos ctrl, EnumModoPanelProductos m, Carrito c) {
		super(prod);
		this.controlador = ctrl;
		this.modo = m;
		addData(c, prod);
		addActions(c);
	}

	private void addActions(Carrito c) {
		
		if(this.modo == EnumModoPanelProductos.PRODUCTOS) {
			addAction("Editar producto", a -> new EditarProductoDialog());
			addAction("Eliminar producto", a -> eliminarProducto());
			
		}
		
		else if(this.modo == EnumModoPanelProductos.TIENDA){
			addAction("Anyadir al carrito", a -> anyadirCarrito(c));
		}
		
		else {
			addAction("Eliminar del carrito", a -> eliminarCarrito());
		}
	}

	private void addData(Carrito c, Producto p) {
		addDataField("Nombre", String.valueOf(object.getNombre()));
		addDataField("Descripcion", object.getDesc());
		
		if(this.modo == EnumModoPanelProductos.CARRITO) {
			addDataField("Unidades", Integer.toString(c.getNumUnidadesProducto(p)));
		}
		
		else {
			addDataField("Stock", String.valueOf(object.getStock()));
		}
		
		addDataField("Precio", String.valueOf(object.getPrecio()));
	}

	/*
	private int ventanaDialogo(String title, String message) {
		String[] options = { "Si", "No" };
		int option = JOptionPane.showOptionDialog(this, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
		return option;
	}*/
	
	private void eliminarProducto() {
		String[] options = { "Si", "No" };
		int option = JOptionPane.showOptionDialog(this, "Seguro que quieres dar de baja este producto?",
				"Dar producto de baja", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlador.bajaProducto(object);
		}
	}
	
	private void anyadirCarrito(Carrito c) {
		String[] options = { "Si", "No" };
		int option = JOptionPane.showOptionDialog(this, "Quieres anyadir este producto a tu carrito?",
				"Anyadir carrito", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlador.anyadirProducto(object, c);
		}
	}
	
	private void eliminarCarrito() {
		String[] options = { "Si", "No" };
		int option = JOptionPane.showOptionDialog(this, "Quieres eliminar este producto de tu carrito?",
				"Eliminar del carrito", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlador.quitarProducto(object);
		}
	}
}