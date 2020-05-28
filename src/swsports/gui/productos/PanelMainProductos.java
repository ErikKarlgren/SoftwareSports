package swsports.gui.productos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import swsports.gui.AbstractPanelMain;
import swsports.gui.EditarProductoPanel;
import swsports.gui.MainWindow;
import swsports.modelo.TransferProducto;
import swsports.modelo.Producto;
import swsports.modelo.Carrito;
import swsports.productos.ControladorProductos;

public class PanelMainProductos extends AbstractPanelMain<Producto> {

	private static final long serialVersionUID = 1L;

	private JTextField idTextField;
	private JTextField nombreTextField;
	private JTextField descTextField;
	private JTextField stockTextField;
	private JTextField precioTextField;
	private ControladorProductos controlador;
	private JButton anyadirProductoButton;
	private JButton carritoButton;
	private EnumModoPanelProductos modo;
	private Carrito carrito;

	private class BuscarProductoWorker extends BuscarSwingWorker {

		@Override
		protected Void doInBackground() throws Exception {
			setSearchButtonEnabled(false);

			String id = idTextField.getText().equals("") ? null : idTextField.getText();
			String nombre = nombreTextField.getText().equals("") ? null : nombreTextField.getText();
			String desc = descTextField.getText().equals("") ? null : descTextField.getText();
			Integer stock = stockTextField.getText().equals("") ? null : Integer.valueOf(stockTextField.getText());
			Double precio = precioTextField.getText().equals("") ? null : Double.valueOf(precioTextField.getText());

			TransferProducto tProd = new TransferProducto(id, nombre, desc, stock, precio);
			objetos = controlador.busquedaProducto(tProd);

			removeReportablePanels();

			for (Producto p : objetos) {
				publish(new ProductoDataPanel(owner, controlador, p, modo, carrito));
			}

			return null;
		}
	}

	public PanelMainProductos(MainWindow owner, ControladorProductos ctrl, EnumModoPanelProductos m, Carrito c) {
		super(owner, "Productos");
		this.controlador = ctrl;
		this.modo = m;
		this.carrito = c;

		if (this.modo == EnumModoPanelProductos.PRODUCTOS)
			this.initGUIProductos();
		else if (this.modo == EnumModoPanelProductos.CARRITO)
			this.initGUICarrito();

	}

	private void initGUIProductos() {
		anyadirProductoButton = new JButton("Anyadir Producto");
		add(anyadirProductoButton, BorderLayout.SOUTH);
		anyadirProductoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AnyadirProductoDialog();
			}
		});
	}

	private void initGUICarrito() {
		carritoButton = new JButton("Tramitar Pedido");
		add(carritoButton, BorderLayout.SOUTH);
		carritoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tramitarCompra();
			}
		});
	}

	/*
	 * FALTA COMPROBAR/PROHIBIR QUE EL USUARIO COMPRE UN PRODUCTO CON STOCK = 0
	 */
	private class AnyadirProductoDialog extends JDialog {
		private static final long serialVersionUID = 1L;

		AnyadirProductoDialog() {
			super();
			this.setTitle("Anyadir producto");
			this.add(new EditarProductoPanel(null, controlador, true));
			this.pack();
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}

	private void tramitarCompra() {
		String[] options = { "Si", "No" };
		int option = JOptionPane.showOptionDialog(this, "Seguro que quieres finalizar tu compra?", "Tramitar pedido",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlador.comprar(carrito);
		}
	}

	@Override
	protected LinkedHashMap<String, JComponent> getComponentesBusqueda() {
		LinkedHashMap<String, JComponent> map = new LinkedHashMap<>();

		idTextField = createTextField();
		map.put("ID", idTextField);
		nombreTextField = createTextField();
		map.put("Nombre", nombreTextField);
		descTextField = createTextField();
		map.put("Descripcion", descTextField);
		stockTextField = createTextField();
		map.put("Stock", stockTextField);
		precioTextField = createTextField();
		map.put("Precio", precioTextField);

		JPanel lateralAdminPanel = new JPanel();
		lateralAdminPanel.setBackground(Color.ORANGE);
		lateralAdminPanel.setLayout(new BoxLayout(lateralAdminPanel, BoxLayout.Y_AXIS));

		return map;
	}

	@Override
	protected AbstractPanelMain<Producto>.BuscarSwingWorker getNewSwingWorker() {
		return new BuscarProductoWorker();
	}

}