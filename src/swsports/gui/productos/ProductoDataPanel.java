package swsports.gui.productos;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import swsports.gui.DataPanel;
import swsports.gui.EditarProductoPanel;
import swsports.modelo.Producto;
import swsports.modelo.TransferProducto;

import swsports.productos.ControladorProductos;

public class ProductoDataPanel extends DataPanel<Producto> {

	private ControladorProductos controlador;
	
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
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ControladorProductos ctrl = new ControladorProductos();
			Producto prod = ctrl.consultaProducto("z9");

			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			frame.add(new ProductoDataPanel(prod, null, ctrl));
			frame.pack();
			frame.setVisible(true);
		});
	}

	
	public ProductoDataPanel(Producto prod, TransferProducto constraints, ControladorProductos ctrl) {
		super(prod);
		this.controlador = ctrl;
		addData();
		addActions();
	}

	private void addActions() {
		addAction("Editar producto", a -> new EditarProductoDialog());
		addAction("Dar de baja", a -> darDeBaja());
	}

	/**
	 * Añade los campos del usuario al panel siguiendo los criterios marcados por un
	 * {@link TransferUsuario}.
	 */
	private void addData() {
		addDataField("Id", object.getId());
		addDataField("Nombre", String.valueOf(object.getNombre()));
		addDataField("Descripcion", object.getDesc());
		addDataField("Stock", String.valueOf(object.getStock()));
		addDataField("Precio", String.valueOf(object.getPrecio()));
	}

	/**
	 * Da de baja a un {@link Producto} si el usuario de la aplicación confirma su
	 * decisión.
	 */
	private void darDeBaja() {
		String[] options = { "S�", "No" };
		int option = JOptionPane.showOptionDialog(this, "�Seguro que quieres dar de baja este producto?",
				"Dar producto de baja", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlador.bajaProducto(object);
		}
	}
}