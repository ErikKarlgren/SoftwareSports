package swsports.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import swsports.modelo.TransferProducto;
import swsports.modelo.TransferProveedor;
import swsports.modelo.Producto;
import swsports.modelo.Proveedor;
import swsports.productos.ControladorProductos;
import swsports.proveedores.ControladorProveedores;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ItemEvent;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JSeparator;

/**
 * Panel usado para editar un proveedor o a馻dir uno nuevo.
 */
public class EditarProveedorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField descTextField;
//	private JTextField idProductoTextField;
//	private JTextField stockTextField;
//	private JTextField precioTextField;

	private JLabel productIDLabel;
	private JToggleButton editProductoButton;
	private Proveedor proveedor;
	private ControladorProductos controlProd;
	private ControladorProveedores controlProv;
	private boolean nuevoProveedor;

	/**
	 * Crea un panel para poder editar o a馻dir un proveedor.
	 * 
	 * @param ctrlProv Controlador del m骴ulo Proveedores.
	 * @param ctrlProd Controlador del m骴ulo Productos.
	 * @param prod     {@link Producto} que queremos editar (null si es nuevo).
	 * @param nuevo    Booleano: true indica que estoy creando un proveedor nuevo,
	 *                 false que estoy editando.
	 */
	public EditarProveedorPanel(ControladorProveedores ctrlProv, ControladorProductos ctrlProd, Proveedor prod,
			boolean nuevo) {
		setBackground(SystemColor.textHighlight);
		this.controlProd = ctrlProd;
		this.controlProv = ctrlProv;
		this.nuevoProveedor = nuevo;
		this.proveedor = prod;
		initGUI();
	}

	/**
	 * Comprueba si se han rellenado todos los datos y son correctos: - Si es un
	 * nuevo proveedor, compruebo que se ha rellenado el campo del id y que no
	 * exista otro proveedor con el mismo.
	 * 
	 * - Tanto si es editar como a帽adir, compruebo si los campos est醤 rellenados y
	 * que tanto stock como precio no tengan valores negativos.
	 * 
	 * @return <code>true</code> si se han rellenado todos los datos y son
	 *         correctos, <code>false</code> en caso contrario.
	 */
	private boolean datosCorrectos() {
		boolean correcto = false;
		try {
			if (!nameTextField.getText().trim().equals("") && !descTextField.getText().trim().equals("")
			// && !idProductoTextField.getText().trim().equals("")
			// && !stockTextField.getText().trim().equals("")
			// && (Integer.parseInt(stockTextField.getText()) >= 0)
			// && !precioTextField.getText().trim().equals("")
			// && (Double.parseDouble(precioTextField.getText()) >= 0.0))
			) {

				if (nuevoProveedor) {
					if (!idTextField.getText().trim().equals("")
							&& controlProd.consultaProducto(idTextField.getText()) == null)
						correcto = true;
				} else {
					correcto = true;
				}
			}
		} catch (NumberFormatException e) {
			// no hacer nada, 'correcto' es false
		}
		return correcto;
	}

	/**
	 * M茅todo que sirve para editar o crear un producto. En un nuevo producto, los
	 * campos aparecen disponibles para escribir. Si estamos editando un producto,
	 * habr谩 que pulsar el bot贸n.
	 * 
	 * @param i {@link ItemEvent}
	 */

	private void editarCrear(ItemEvent i) {
		if (i.getStateChange() == ItemEvent.SELECTED) {
			setTextFieldsEditable(true);
		} else if (datosCorrectos()) {
			editarCrearAux();
		} else {
			String mensaje;
			if (nuevoProveedor) {
				mensaje = "Todos los campos deben estar completos."
						+ "\nEl id debe ser diferente a los proveedores ya existentes."
						+ "\nEl stock y el precio deben ser n鷐eros no negativos.";
			} else {
				mensaje = "Todos los campos deben estar completos."
						+ "\nEl stock y el precio deben ser n鷐eros no negativos.";
			}
			JOptionPane.showMessageDialog(this, mensaje, "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * M閠odo auxiliar para editar o crear un {@link Producto}.
	 */
	private void editarCrearAux() {
		if (nuevoProveedor) {
			controlProv.anyadirProveedor(new Proveedor(leerDatos()));
			setTextFieldsEditable(false);
			SwingUtilities.invokeLater(() -> JOptionPane.showConfirmDialog(this, "Se ha anyadido el nuevo producto",
					"Anyadir producto", JOptionPane.PLAIN_MESSAGE));
		} else {
			controlProv.editarProveedor(leerDatos());
			setTextFieldsEditable(false);
			SwingUtilities.invokeLater(() -> JOptionPane.showConfirmDialog(this, "Los nuevos datos se han guardado",
					"Editar producto", JOptionPane.PLAIN_MESSAGE));
		}
	}

	/**
	 * Crea la interfaz gr谩fica del panel.
	 */
	private void initGUI() {
		setLayout(new BorderLayout(0, 0));

		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, BorderLayout.NORTH);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, BorderLayout.EAST);

		JPanel dataPanel = new JPanel();
		dataPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		dataPanel.setBackground(Color.WHITE);
		dataPanel.setLayout(new BorderLayout());
		add(dataPanel, BorderLayout.CENTER);

		JPanel dataAuxPanel = new JPanel();
		dataPanel.add(dataAuxPanel, BorderLayout.CENTER);
		GridBagLayout gbl_dataPanel = new GridBagLayout();
		gbl_dataPanel.columnWidths = new int[] { 41, 68, 0, 0 };
		gbl_dataPanel.rowHeights = new int[] { 20, 20, 26, 0, 0, 0, 0, 0 };
		gbl_dataPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_dataPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		dataAuxPanel.setLayout(gbl_dataPanel);
		dataAuxPanel.setBackground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		dataAuxPanel.add(lblNewLabel, gbc_lblNewLabel);

		/**
		 * Si es un nuevo producto, a帽ado un campo de texto para introducir el id. Si
		 * estoy editando un producto, a帽ado una etiqueta con su identificador, que no
		 * puede cambiar.
		 */
		if (nuevoProveedor) {
			idTextField = new JTextField();
			idTextField.setEditable(true);
			GridBagConstraints gbc_idTextField = new GridBagConstraints();
			gbc_idTextField.fill = GridBagConstraints.BOTH;
			gbc_idTextField.anchor = GridBagConstraints.NORTHWEST;
			gbc_idTextField.insets = new Insets(0, 0, 5, 0);
			gbc_idTextField.gridx = 2;
			gbc_idTextField.gridy = 0;
			dataAuxPanel.add(idTextField, gbc_idTextField);
			idTextField.setColumns(10);

		} else {
			productIDLabel = new JLabel(
					proveedor != null ? proveedor.getId() : "(deberia aparecer el ID del producto aqui)");
			productIDLabel.setForeground(Color.BLACK);
			productIDLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			GridBagConstraints gbc_productIDLabel = new GridBagConstraints();
			gbc_productIDLabel.anchor = GridBagConstraints.WEST;
			gbc_productIDLabel.fill = GridBagConstraints.VERTICAL;
			gbc_productIDLabel.insets = new Insets(0, 0, 5, 0);
			gbc_productIDLabel.gridx = 2;
			gbc_productIDLabel.gridy = 0;
			dataAuxPanel.add(productIDLabel, gbc_productIDLabel);
		}

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		dataAuxPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		nameTextField = new JTextField(proveedor != null ? proveedor.getNombre() : "");
		nameTextField.setEditable(false);
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.fill = GridBagConstraints.BOTH;
		gbc_nameTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_nameTextField.gridx = 2;
		gbc_nameTextField.gridy = 1;
		dataAuxPanel.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Descripcion");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		dataAuxPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		descTextField = new JTextField(proveedor != null ? proveedor.getDesc() : "");
		descTextField.setEditable(false);
		GridBagConstraints gbc_descTextField = new GridBagConstraints();
		gbc_descTextField.fill = GridBagConstraints.BOTH;
		gbc_descTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_descTextField.insets = new Insets(0, 0, 5, 0);
		gbc_descTextField.gridx = 2;
		gbc_descTextField.gridy = 2;
		dataAuxPanel.add(descTextField, gbc_descTextField);
		descTextField.setColumns(10);

//		stockTextField = new JTextField(proveedor != null ? String.valueOf(proveedor.getStock()) : "");
//		stockTextField.setEditable(false);
		GridBagConstraints gbc_stockTextField = new GridBagConstraints();
		gbc_stockTextField.fill = GridBagConstraints.BOTH;
		gbc_stockTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_stockTextField.insets = new Insets(0, 0, 5, 0);
		gbc_stockTextField.gridx = 2;
		gbc_stockTextField.gridy = 3;

//		precioTextField = new JTextField(proveedor != null ? String.valueOf(proveedor.getPrecio()) : "");
//		precioTextField.setEditable(false);
		GridBagConstraints gbc_precioTextField = new GridBagConstraints();
		gbc_precioTextField.fill = GridBagConstraints.BOTH;
		gbc_precioTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_precioTextField.insets = new Insets(0, 0, 5, 0);
		gbc_precioTextField.gridx = 2;
		gbc_precioTextField.gridy = 4;

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		dataPanel.add(horizontalStrut_2, BorderLayout.WEST);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		dataPanel.add(horizontalStrut_3, BorderLayout.EAST);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		dataPanel.add(verticalStrut_1, BorderLayout.NORTH);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		dataPanel.add(verticalStrut_2, BorderLayout.SOUTH);

		JPanel bottomPanel = new JPanel();
		add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		bottomPanel.setBackground(Color.ORANGE);

		JPanel bottomAuxPanel = new JPanel();
		bottomPanel.add(bottomAuxPanel, BorderLayout.CENTER);
		bottomAuxPanel.setBackground(bottomPanel.getBackground());

		/**
		 * Cambia el bot贸n en funci贸n de si edito/creo
		 */
		if (nuevoProveedor) {
			editProductoButton = new JToggleButton("Anyadir producto");
		} else {
			editProductoButton = new JToggleButton("Editar producto");
		}

		editProductoButton.addItemListener(arg0 -> editarCrear(arg0));

		editProductoButton.setBackground(Color.WHITE);
		bottomAuxPanel.add(editProductoButton);

		Component horizontalGlue = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue, BorderLayout.EAST);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue_1, BorderLayout.WEST);

		JSeparator separator = new JSeparator();
		bottomPanel.add(separator, BorderLayout.NORTH);

		setTextFieldsEditable(false);
	}

	/**
	 * Si estoy editando un producto, cojo todos los campos y mantengo su id.
	 * 
	 * @return {@link TransferProducto} Nuevos datos del producto.
	 */
	private TransferProveedor leerDatos() {
		String id = this.nuevoProveedor ? idTextField.getText() : this.proveedor.getId();
		return new TransferProveedor(id, nameTextField.getText(), descTextField.getText(), null, null, null, false);
	}

	/**
	 * Establece si los campos son editables o no en funci贸n de un booleano.
	 * 
	 * @param b Boolean que indica si se ponen o no editables.
	 */
	private void setTextFieldsEditable(boolean b) {
		nameTextField.setEditable(b);
		descTextField.setEditable(b);
//		stockTextField.setEditable(b);
//		precioTextField.setEditable(b);
//		idProductoTextField.setEditable(b);

		if (nuevoProveedor) {
			idTextField.setEditable(b);
		}

	}
}