package swsports.gui.proveedores;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import swsports.gui.DataPanel;
import swsports.gui.MainWindow;
import swsports.gui.ProveedorMainPanel;
import swsports.modelo.Proveedor;
import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;
import swsports.productos.ControladorProductos;
import swsports.proveedores.ControladorProveedores;
import swsports.usuarios.ControladorUsuario;

public class ProveedorDataPanel extends DataPanel<Proveedor> {

	private boolean hayPedido = false;

	/**
	 * DiÃ¡logo que muestra el panel para editar el perfil de un usuario, incluyendo
	 * si concederle o revocarle privilegios de administrador.
	 */
	private class EditarProveedorDialog extends JDialog {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		EditarProveedorDialog() {
			super();
			this.setTitle("Editar proveedor");
			this.add(new ProveedorMainPanel(object, controlProv, controlProd));
			this.pack();
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}

	private class CrearPedidoDialog extends JDialog {
		private static final long serialVersionUID = 1L;

		private boolean hayPedido = false;

		CrearPedidoDialog() {
			super();
			this.setTitle("Crear pedido");
			this.add(new ProveedorMainPanel(object, controlProv, controlProd));
			this.pack();
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> {
//			ControladorProveedores ctrl = new ControladorProveedores();
//			Proveedor prov = ctrl.consultaProveedor("z9");
//
//			JFrame frame = new JFrame();
//			frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//			frame.add(new ProveedorDataPanel(owner, ctrl, wwwww, prov));
//			frame.pack();
//			frame.setVisible(true);
//		});
//	}

	private ControladorProveedores controlProv;
	private ControladorProductos controlProd;

	/**
	 * Crea un panel con los datos de un {@link Usuario} y siguiendo las
	 * restricciones guardadas en un {@link TransferUsuario} (los atributos nulos
	 * implican que sus datos correspondientes no se mostrarÃ¡n). El
	 * {@link ControladorUsuario} es necesario para poder realizar las acciones de
	 * editar el usuario o darlo de baja.
	 * 
	 * @param owner       Ventana principal del programa.
	 * @param ctrlProv    Controlador del módulo Proveedores.
	 * @param ctrlProd    Controlador del módulo Productos.
	 * @param usu         {@link Usuario} del que queremos mostrar sus datos.
	 * @param constraints Restricciones a la hora de mostrar o no los atributos de
	 *                    <code>usu</code>. Los atributos nulos de
	 *                    <code>constraints</code> implican que los atributos
	 *                    correspondientes de <code>usu</code> no se mostrarÃ¡n. Si
	 *                    <code>constraints</code> es nulo se mostrarÃ¡n todos los
	 *                    datos.
	 */
	public ProveedorDataPanel(MainWindow owner, ControladorProveedores ctrlProv, ControladorProductos ctrlProd,
			Proveedor prov) {
		super(owner, prov);
		this.controlProv = ctrlProv;
		addData();
		addActions();
	}

	/**
	 * AÃ±ade las acciones del panel: editar un usuario y darlo de baja.
	 */
	private void addActions() {
		addAction("Editar proveedor", a -> new EditarProveedorDialog());
		addAction("Hacer Pedido", a -> new CrearPedidoDialog());
		addAction("Recibir Pedido", a -> eliminarPedido());
		addAction("Quitar Pedido", a -> eliminarPedido());
		addAction("Eliminar Proveedor", a -> darDeBaja());
	}

	private void eliminarPedido() {
		// TODO Auto-generated method stub
		controlProv.quitarPedido(object.getIdProducto());
	}

	/**
	 * AÃ±ade los campos del usuario al panel siguiendo los criterios marcados por
	 * un {@link TransferUsuario}.
	 */
	private void addData() {
		addDataField("Nombre", object.getNombre());
		addDataField("Descripcion", object.getDesc());
		if (hayPedido == true) {
			addDataField("Producto", object.getIdProducto());
			addDataField("Cantidad", Integer.toString(object.getStock()));
			addDataField("Precio Total", Double.toString(object.getPrecio()));
		}
	}

	/**
	 * Da de baja a un {@link Usuario} si el usuario de la aplicaciÃ³n confirma su
	 * decisiÃ³n.
	 */
	private void darDeBaja() {
		String[] options = { "SÃ­", "No" };
		int option = JOptionPane.showOptionDialog(this, "Â¿Seguro que quieres eliminar a este proveedor?",
				"Eliminar proveedor", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlProv.quitarProveedor(object);
		}
	}
}
