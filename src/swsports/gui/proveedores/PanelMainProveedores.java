package swsports.gui.proveedores;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import swsports.modelo.Producto;
import swsports.modelo.Proveedor;
import swsports.modelo.TransferProveedor;
import swsports.modelo.TransferUsuario;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

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
	private ControladorProveedores controlador;	
	private Producto prod;
	
	private class BuscarProveedorWorker extends BuscarSwingWorker {

		@Override
		protected Void doInBackground() throws Exception {
			setSearchButtonEnabled(false);

			String id = idTextField.getText().equals("") ? null : idTextField.getText();
			String nombre = nombreTextField.getText().equals("") ? null : nombreTextField.getText();
			String desc =  descTextField.getText().equals("") ? null : descTextField.getText();
			String idProd = idProdTextField.getText().equals("") ? null : idProdTextField.getText();
			prod = controlador.busquedaProducto(idProd);
			
			TransferProveedor tProv = new TransferProveedor(id, nombre, desc, prod, stock);
			objetos = controlador.busquedaProveedores(tProv);
			
			removeReportablePanels();	
			for (Proveedor p : objetos) {
				publish(new ProveedorDataPanel(p, controlador));
			}
						
			return null;
		}
	}
	


	public PanelMainProveedores(ControladorProveedores ctrlProv) {
		super("Proveedores");
		controlador = ctrlProv;
		this.initGUIProveedores();
		// TODO Auto-generated constructor stub
	}

	private void initGUIProveedores() {
		JPanel botones = new JPanel();
		JButton anyadirProveedor = new JButton("Anyadir Proveedor");
		botones.add(anyadirProveedor);
		anyadirProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AnyadirProveedorDialog();
			}
		});
		this.add(botones, BorderLayout.SOUTH);
	}
	
	private class AnyadirProveedorDialog extends JDialog {
		private static final long serialVersionUID = 1L;
		
		private JTextField id;
		private JTextField nombre;
		private JTextField descripcion;
		
		AnyadirProveedorDialog() {
			super();
			this.setTitle("Anyadir proveedor");
			this.setLayout(new FlowLayout());
			
			JLabel idP = new JLabel("ID");
			this.add(idP);
			id = new JTextField();
			id.setPreferredSize(new Dimension(100, 20));
			this.add(id);
			JLabel nombreP = new JLabel("Nombre");
			this.add(nombreP);
			nombre = new JTextField();
			nombre.setPreferredSize(new Dimension(100, 20));
			this.add(nombre);
			JLabel descP = new JLabel("Descripcion");
			this.add(descP);
			descripcion = new JTextField();
			descripcion.setPreferredSize(new Dimension(100, 20));
			this.add(descripcion);
			JButton anyadir = new JButton("Anyadir");
			anyadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new AnyadirProveedorDialog();
				}
			});
			
			this.pack();
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}
	
	@Override
	protected LinkedHashMap<String, JComponent> getComponentesBusqueda() {
		// TODO Auto-generated method stub
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