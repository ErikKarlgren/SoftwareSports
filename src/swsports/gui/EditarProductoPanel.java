package swsports.gui;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import swsports.modelo.TransferProducto;
import swsports.modelo.Producto;
import swsports.productos.ControladorProductos;

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
 * Panel usado para editar un producto o añadir uno nuevo.
 */
public class EditarProductoPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField nameTextField;
	private JTextField descTextField;
	private JTextField stockTextField;
	private JTextField precioTextField;
	private JTextField idTextField;
	private JLabel productIDLabel;
	private JToggleButton editProductoButton;
	private Producto producto;
	private ControladorProductos controlador;
	private boolean nuevoProducto;
	
	/**
	 * Crea un panel para poder editar o añadir un producto.
	 * 
	 * @param prod  {@link Producto} que queremos editar (null si es nuevo).
	 * @param ctrl Controlador del módulo Productos.
	 * @param nuevo Booleano: true indica que estoy creando un producto nuevo, false que estoy editando.
	 */
	public EditarProductoPanel(Producto prod, ControladorProductos ctrl, boolean nuevo) {
		setBackground(SystemColor.textHighlight);
		this.controlador = ctrl;
		this.nuevoProducto = nuevo;	
		this.producto = prod;
		initGUI();
	}

	/**
	 * Comprueba si se han rellenado todos los datos.
	 * 
	 * @return <code>true</code> si se han rellenado todos los datos,
	 *         <code>false</code> en caso contrario.
	 */
	private boolean datosCompletos() {
		
		boolean completos = false;
		
		if(!nameTextField.getText().trim().equals("")
				&& !descTextField.getText().trim().equals("")
				&& !stockTextField.getText().trim().equals("")
				&& !precioTextField.getText().trim().equals("")) {
			completos = true;
		}
		
		if(nuevoProducto && idTextField.getText().trim().equals("")) {
			completos = false;
		}
		
		return completos; 
	}

	/**
	 * Método que sirve para editar o crear un producto. En un nuevo producto, los campos aparecen disponibles para escribir. Si estamos editando
	 * un producto, habrá que pulsar el botón.
	 */
	private void editarCrear(ItemEvent i) {
		
		if (!nuevoProducto && i.getStateChange() == ItemEvent.SELECTED) {
			setTextFieldsEditable(true);
		} 
		
		//Si están todos los datos completos
		else if (datosCompletos()) {
			
			if(nuevoProducto){
				controlador.altaProducto(leerDatosAnyadir());
				setTextFieldsEditable(false);
				SwingUtilities.invokeLater(() -> JOptionPane.showConfirmDialog(this, "Se ha anyadido el nuevo producto",
						"Anyadir producto", JOptionPane.PLAIN_MESSAGE));
			}
			
			else {
				controlador.editarProducto(leerDatosEditar());
				setTextFieldsEditable(false);
				SwingUtilities.invokeLater(() -> JOptionPane.showConfirmDialog(this, "Los nuevos datos se han guardado",
						"Editar producto", JOptionPane.PLAIN_MESSAGE));
			}
				
		} else {
			JOptionPane.showMessageDialog(this, "Datos incompletos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Crea la interfaz gráfica del panel.
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
		 * Si es un nuevo producto, añado un campo de texto para introducir el id.
		 * Si estoy editando un producto, añado una etiqueta con su identificador, que no puede cambiar.
		 */
		if(nuevoProducto) {
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
			
		}
		else {
			productIDLabel = new JLabel(producto != null ? producto.getId() : "(deberia aparecer el ID del producto aqui)");
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

		nameTextField = new JTextField(producto != null ? producto.getNombre() : "");
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

		descTextField = new JTextField(producto != null ? producto.getDesc() : "");
		descTextField.setEditable(false);
		GridBagConstraints gbc_descTextField = new GridBagConstraints();
		gbc_descTextField.fill = GridBagConstraints.BOTH;
		gbc_descTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_descTextField.insets = new Insets(0, 0, 5, 0);
		gbc_descTextField.gridx = 2;
		gbc_descTextField.gridy = 2;
		dataAuxPanel.add(descTextField, gbc_descTextField);
		descTextField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Stock");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		dataAuxPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		stockTextField = new JTextField(producto != null ? String.valueOf(producto.getStock()) : "");
		stockTextField.setEditable(false);
		GridBagConstraints gbc_stockTextField = new GridBagConstraints();
		gbc_stockTextField.fill = GridBagConstraints.BOTH;
		gbc_stockTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_stockTextField.insets = new Insets(0, 0, 5, 0);
		gbc_stockTextField.gridx = 2;
		gbc_stockTextField.gridy = 3;
		dataAuxPanel.add(stockTextField, gbc_stockTextField);
		stockTextField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Precio");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		dataAuxPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		precioTextField = new JTextField(producto != null ? String.valueOf(producto.getPrecio()) : "");
		precioTextField.setEditable(false);
		GridBagConstraints gbc_precioTextField = new GridBagConstraints();
		gbc_precioTextField.fill = GridBagConstraints.BOTH;
		gbc_precioTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_precioTextField.insets = new Insets(0, 0, 5, 0);
		gbc_precioTextField.gridx = 2;
		gbc_precioTextField.gridy = 4;
		dataAuxPanel.add(precioTextField, gbc_precioTextField);
		precioTextField.setColumns(10);

		JPanel adminButtonsPanel = new JPanel();
		adminButtonsPanel.setBackground(dataAuxPanel.getBackground());
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 6;
		dataAuxPanel.add(adminButtonsPanel, gbc_panel_1);

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
		 * Cambia el botón en función de si edito/creo
		 */
		if(nuevoProducto) {
			editProductoButton = new JToggleButton("Anyadir producto");
		}
		else {
			editProductoButton = new JToggleButton("Editar producto");
		}
		
		editProductoButton.addItemListener(this::editarCrear);
		editProductoButton.setBackground(Color.WHITE);
		bottomAuxPanel.add(editProductoButton);

		Component horizontalGlue = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue, BorderLayout.EAST);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue_1, BorderLayout.WEST);

		JSeparator separator = new JSeparator();
		bottomPanel.add(separator, BorderLayout.NORTH);

		setTextFieldsEditable(nuevoProducto);
	}

	/**
	 * Si estoy editando un producto, cojo todos los campos y mantengo su id.
	 * 
	 * @return {@link TransferProducto} Nuevos datos del producto.
	 */
	private TransferProducto leerDatosEditar() {
		return new TransferProducto(producto.getId(), nameTextField.getText(), descTextField.getText(),
				Integer.valueOf(stockTextField.getText()),
				Double.valueOf(precioTextField.getText()));
	}
	
	/**
	 * Si estoy creando un producto, cojo todos los campos.
	 * 
	 * @return {@link TransferProducto} Datos del nuevo producto.
	 */
	private Producto leerDatosAnyadir() {
		return new Producto(idTextField.getText(), nameTextField.getText(), descTextField.getText(),
				Integer.valueOf(stockTextField.getText()),
				Double.valueOf(precioTextField.getText()));
	}

	/**
	 * Establece si los campos son editables o no en función de un booleano.
	 * 
	 * @param b Boolean que indica si se ponen o no editables.
	 */
	private void setTextFieldsEditable(boolean b) {
		nameTextField.setEditable(b);
		descTextField.setEditable(b);
		stockTextField.setEditable(b);
		precioTextField.setEditable(b);

	}
}