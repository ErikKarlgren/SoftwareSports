package swsports.gui;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;
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


public class EditProductoPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextField nameTextField;
	private JTextField descTextField;
	private JTextField stockTextField;
	private JTextField precioTextField;
	private JLabel productIDLabel;
	private JToggleButton editProductoButton;
	private Producto producto;
	private ControladorProductos controlador;
	
	public EditProductoPanel(Producto prod, ControladorProductos ctrl) {
		setBackground(SystemColor.textHighlight);
		this.producto = prod;
		this.controlador = ctrl;
		initGUI();
	}


	private void setBackground(SystemColor texthighlight) {
		// TODO Auto-generated method stub
		
	}


	private boolean datosCompletos() {
		return !nameTextField.getText().trim().equals("")
				&& !descTextField.getText().trim().equals("")
				&& !stockTextField.getText().trim().equals("")
				&& !precioTextField.getText().trim().equals("");
	}

	private void editar(ItemEvent i) {
		if (i.getStateChange() == ItemEvent.SELECTED) {
			setTextFieldsEditable(true);
		} else if (datosCompletos()) {
			controlador.editarProducto(leerDatos());
			setTextFieldsEditable(false);
			SwingUtilities.invokeLater(() -> JOptionPane.showConfirmDialog(this, "Los nuevos datos se han guardado",
					"Editar producto", JOptionPane.PLAIN_MESSAGE));
		} else {
			JOptionPane.showMessageDialog(this, "Datos incompletos (unicos optativos: mail y direccion)", "Edit Error",
					JOptionPane.ERROR_MESSAGE);
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

		productIDLabel = new JLabel(
				producto != null ? producto.getId() : "(deber\u00EDa aparecer el ID del producto aqu\u00ED)");
		productIDLabel.setForeground(Color.BLACK);
		productIDLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_productIDLabel = new GridBagConstraints();
		gbc_productIDLabel.anchor = GridBagConstraints.WEST;
		gbc_productIDLabel.fill = GridBagConstraints.VERTICAL;
		gbc_productIDLabel.insets = new Insets(0, 0, 5, 0);
		gbc_productIDLabel.gridx = 2;
		gbc_productIDLabel.gridy = 0;
		dataAuxPanel.add(productIDLabel, gbc_productIDLabel);

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

		JLabel lblNewLabel_2 = new JLabel("Descripci�n");
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

		editProductoButton = new JToggleButton("Editar producto");
		editProductoButton.addItemListener(this::editar);
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

	private void setLayout(BorderLayout borderLayout) {
		// TODO Auto-generated method stub
		
	}


	private void add(Component horizontalStrut, String west) {
		// TODO Auto-generated method stub
		
	}


	private TransferProducto leerDatos() {
		return new TransferProducto(producto.getId(), nameTextField.getText(), descTextField.getText(),
				Integer.valueOf(stockTextField.getText()),
				Double.valueOf(precioTextField.getText()));
	}

	private void setTextFieldsEditable(boolean b) {
		//idTextField.setEditable(b);
		nameTextField.setEditable(b);
		descTextField.setEditable(b);
		stockTextField.setEditable(b);
		precioTextField.setEditable(b);

	}
}