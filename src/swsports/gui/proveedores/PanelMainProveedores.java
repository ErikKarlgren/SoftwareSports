package swsports.gui.proveedores;

import java.util.LinkedHashMap;

import swsports.modelo.Producto;
import swsports.modelo.Proveedor;
import swsports.modelo.TransferProveedor;
import swsports.productos.ControladorProductos;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

import swsports.gui.AbstractPanelMain;
import swsports.gui.MainWindow;
import swsports.proveedores.ControladorProveedores;

public class PanelMainProveedores extends AbstractPanelMain<Proveedor> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField idTextField;
	private JTextField nombreTextField;
	private JTextField descTextField;
	private JTextField idProdTextField;
	private JTextField stockTextField;
	private JTextField precioTextField;
	private ControladorProveedores controlProv;
	private ControladorProductos controlProd;
	private JButton anyadirProveedorButton;
	private Producto prod;
	//private EnumModoPanelProductos modo;

	
	private class BuscarProveedorWorker extends BuscarSwingWorker {

		@Override
		protected Void doInBackground() throws Exception {
			setSearchButtonEnabled(false);

			String id = idTextField.getText().equals("") ? null : idTextField.getText();
			String nombre = nombreTextField.getText().equals("") ? null : nombreTextField.getText();
			String desc =  descTextField.getText().equals("") ? null : descTextField.getText();
			String idProd = idProdTextField.getText().equals("") ? null : idProdTextField.getText();
			prod = controlProd.consultaProducto(idProd);
			Integer stock = stockTextField.getText().equals("") ? null : Integer.valueOf(stockTextField.getText());
			//Double precio = precioTextField.getText().equals("") ? null : Double.valueOf(precioTextField.getText());
			
			TransferProveedor tProv = new TransferProveedor(id, nombre, desc, prod, stock);
			objetos = controlProv.busquedaProveedores(tProv);
			
			removeReportablePanels();
			
			for (Proveedor p : objetos) {
				publish(new ProveedorDataPanel(owner, controlProv, controlProd, p));
			}
						
			return null;
		}
	}

	public PanelMainProveedores(MainWindow owner, ControladorProveedores ctrlProv, ControladorProductos ctrlProd) {
		super(owner, "Proveedores");
		controlProv = ctrlProv;
		controlProd = ctrlProd;
	}

	@Override
	protected LinkedHashMap<String, JComponent> getComponentesBusqueda() {
		LinkedHashMap<String, JComponent> map = new LinkedHashMap<>();

		idTextField = createTextField();
		map.put("ID", idTextField);
		nombreTextField = createTextField();
		map.put("Nombre", nombreTextField);
		descTextField = createTextField();
		map.put("Telefono", descTextField);
		idProdTextField = createTextField();
		map.put("ID Producto", idProdTextField);
		stockTextField = createTextField();
		map.put("Cantidad", stockTextField);
		stockTextField = createTextField();
		map.put("Cantidad", stockTextField);

		return map;
	}

	@Override
	protected AbstractPanelMain<Proveedor>.BuscarSwingWorker getNewSwingWorker() {
		return new BuscarProveedorWorker();
	}

}
