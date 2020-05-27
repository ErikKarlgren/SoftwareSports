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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import swsports.gui.AbstractPanelMain;
import swsports.gui.EditarProductoPanel;
import swsports.modelo.TransferProducto;
import swsports.modelo.Producto;
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
	private boolean esModoTienda;

	private class BuscarProductoWorker extends BuscarSwingWorker {

		@Override
		protected Void doInBackground() throws Exception {
			setSearchButtonEnabled(false);

			String id = idTextField.getText().equals("") ? null : idTextField.getText();
			String nombre = nombreTextField.getText().equals("") ? null : nombreTextField.getText();
			String desc =  descTextField.getText().equals("") ? null : descTextField.getText();
			Integer stock = stockTextField.getText().equals("") ? null : Integer.valueOf(stockTextField.getText());
			Double precio = precioTextField.getText().equals("") ? null : Double.valueOf(precioTextField.getText());

			TransferProducto tProd = new TransferProducto(id, nombre, desc, stock, precio);
			objetos = controlador.busquedaProducto(tProd);
			
			removeReportablePanels();
			
			for (Producto p : objetos) {
				publish(new ProductoDataPanel(p, controlador, esModoTienda));
			}
						
			return null;
		}
	}
	
	public PanelMainProductos(ControladorProductos ctrl, boolean tienda) {
		super("Productos");
		this.controlador = ctrl;
		this.esModoTienda = tienda;
		
		if(esModoTienda) this.initGUITienda();
		else this.initGUIProductos();
		
	}
	
	private void initGUIProductos() {
		anyadirProductoButton = new JButton("Anyadir Producto");
		add(anyadirProductoButton, BorderLayout.SOUTH);
		anyadirProductoButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new AnyadirProductoDialog();
			}
		});
	}
	
	private void initGUITienda() {
		carritoButton = new JButton("Carrito");
		add(carritoButton, BorderLayout.SOUTH);
		carritoButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new CarritoDialog();
			}
		});
	}
	
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
	
	private class CarritoDialog extends JDialog{
		private static final long serialVersionUID = 1L;

		CarritoDialog() {
			super();
			this.setTitle("Carrito");
			
			this.setVisible(true);
		}
	}

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

	protected AbstractPanelMain<Producto>.BuscarSwingWorker getNewSwingWorker() {
		return new BuscarProductoWorker();
	}

}