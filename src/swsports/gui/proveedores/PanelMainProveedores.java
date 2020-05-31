package swsports.gui.proveedores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import swsports.modelo.Producto;
import swsports.modelo.Proveedor;
import swsports.modelo.TransferProveedor;
import swsports.productos.ControladorProductos;

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
	private JTextField nameTextField;
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
			String nombre = nameTextField.getText().equals("") ? null : nameTextField.getText();
			String desc =  descTextField.getText().equals("") ? null : descTextField.getText();
			String idProd = idProdTextField.getText().equals("") ? null : idProdTextField.getText();
			prod = controlProd.consultaProducto(idProd);
			Integer stock = stockTextField.getText().equals("") ? null : Integer.valueOf(stockTextField.getText());
			
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
		nameTextField = createTextField();
		map.put("Nombre", nameTextField);
		idProdTextField = createTextField();
		map.put("ID producto", idProdTextField);
		descTextField = createTextField();
		map.put("Descripcion", descTextField);
		stockTextField = createTextField();
		map.put("Cantidad", stockTextField);
		anyadirProveedorButton = new JButton("Anyadir");
		anyadirProveedorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AnyadirProveedorDialog();
			}
		});
		map.put("Anyadir nuevo proveedor", anyadirProveedorButton);

		return map;
	}
	
	private class AnyadirProveedorDialog extends JDialog{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		AnyadirProveedorDialog(){
			super();
			this.setTitle("Anyadir proveedor");
			initGUI();
			this.pack();
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
			private void initGUI() {
				JPanel panelText = new JPanel();
				
				JLabel lblNewLabel_1 = new JLabel("ID");
				lblNewLabel_1.setForeground(Color.BLACK);
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 1;
				panelText.add(lblNewLabel_1);

				JTextField idPTextField = new JTextField();
				idPTextField.setEditable(false);
				GridBagConstraints gbc_idTextField = new GridBagConstraints();
				gbc_idTextField.fill = GridBagConstraints.BOTH;
				gbc_idTextField.anchor = GridBagConstraints.NORTHWEST;
				gbc_idTextField.insets = new Insets(0, 0, 5, 0);
				gbc_idTextField.gridx = 2;
				gbc_idTextField.gridy = 1;
				idPTextField.setColumns(10);
				panelText.add(idPTextField);
				
				JLabel lblNewLabel_2 = new JLabel("Nombre");
				lblNewLabel_2.setForeground(Color.BLACK);
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 0;
				gbc_lblNewLabel_2.gridy = 1;
				panelText.add(lblNewLabel_2);

				JTextField namePTextField = new JTextField();
				namePTextField.setEditable(false);
				GridBagConstraints gbc_nameTextField = new GridBagConstraints();
				gbc_nameTextField.fill = GridBagConstraints.BOTH;
				gbc_nameTextField.anchor = GridBagConstraints.NORTHWEST;
				gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
				gbc_nameTextField.gridx = 2;
				gbc_nameTextField.gridy = 1;
				namePTextField.setColumns(10);
				panelText.add(namePTextField);
				
				JLabel lblNewLabel_3 = new JLabel("Descripcion");
				lblNewLabel_3.setForeground(Color.BLACK);
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 0;
				gbc_lblNewLabel_3.gridy = 1;
				panelText.add(lblNewLabel_3);

				JTextField descPTextField = new JTextField();
				descPTextField.setEditable(false);
				GridBagConstraints gbc_descTextField = new GridBagConstraints();
				gbc_descTextField.fill = GridBagConstraints.BOTH;
				gbc_descTextField.anchor = GridBagConstraints.NORTHWEST;
				gbc_descTextField.insets = new Insets(0, 0, 5, 0);
				gbc_descTextField.gridx = 2;
				gbc_descTextField.gridy = 1;
				descPTextField.setColumns(10);
				panelText.add(descPTextField);
				
				this.add(panelText, BorderLayout.CENTER);
				
				
				JButton anyadir = new JButton("Anyadir");
				anyadir.addActionListener(new ActionListener() {
					Proveedor prov = new Proveedor(idTextField.getText(), nameTextField.getText(), descTextField.getText(), "", 0, 0);
					@Override
					public void actionPerformed(ActionEvent arg0) {
						controlProv.anyadirProveedor(prov);
					}
				});
				
				this.add(anyadir, BorderLayout.SOUTH);
			}
		}

	@Override
	protected AbstractPanelMain<Proveedor>.BuscarSwingWorker getNewSwingWorker() {
		return new BuscarProveedorWorker();
	}

}
