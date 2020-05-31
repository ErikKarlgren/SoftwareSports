package swsports.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ItemEvent;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import swsports.modelo.Proveedor;
import swsports.modelo.TransferProveedor;
import swsports.productos.ControladorProductos;
import swsports.proveedores.ControladorProveedores;

public class ProveedorMainPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField nameTextField;
	private JTextField descTextField;
	private JLabel provIDLabel;
	private JToggleButton editProvButton;
	private JToggleButton anyadirProveedor;
	private JToggleButton hacerPedido;
	private JToggleButton pedidoRecibido;
	private JToggleButton quitarPedido;
	private Proveedor prov;
	private ControladorProveedores controlProv;
	private ControladorProductos controlProd;

	private boolean adminMode;

// Descomentar este constructor cuando no se vaya a editar más la interfaz gráfica.
// Dejarlo descomentado para luego intentar usar el plugin para editarlo provoca problemas.

	/**
	 * Crea un panel para poder editar el perfil de un usuario teniendo que
	 * especificar
	 * 
	 * @param prov     {@link Proveedor} del que se quiere consultar el perfil.
	 * @param ctrlProv Controlador del módulo Proveedores.
	 * @param ctrlProd Controlador del módulo Productos.
	 */
	public ProveedorMainPanel(Proveedor prov, ControladorProveedores ctrlProv, ControladorProductos ctrlProd) {
		setBackground(SystemColor.textHighlight);
		this.prov = prov;
		this.controlProv = ctrlProv;
		initGUI();
	}

	/**
	 * Comprueba si se han rellenado todos los datos obligatorios.
	 * 
	 * @return <code>true</code> si se han rellenado todos los datos necesarios,
	 *         <code>false</code> en caso contrario.
	 */
	private boolean datosCompletos() {
		return !nameTextField.getText().trim().equals("");
	}

	private void editar(ItemEvent i) {
		if (i.getStateChange() == ItemEvent.SELECTED) {
			setTextFieldsEditable(true);
		} else if (datosCompletos()) {
			controlProv.editarProveedor(leerDatos());
			setTextFieldsEditable(false);
			SwingUtilities.invokeLater(() -> JOptionPane.showConfirmDialog(this, "Los nuevos datos se han guardado",
					"Editar proveedor", JOptionPane.PLAIN_MESSAGE));
		} else {
			JOptionPane.showMessageDialog(this, "Datos incompletos", "Edit Error", JOptionPane.ERROR_MESSAGE);
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

		provIDLabel = new JLabel(prov != null ? prov.getId() : "(deber\u00EDa aparecer el ID del proveedor aqu\u00ED)");
		provIDLabel.setForeground(Color.BLACK);
		provIDLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_userIDLabel = new GridBagConstraints();
		gbc_userIDLabel.anchor = GridBagConstraints.WEST;
		gbc_userIDLabel.fill = GridBagConstraints.VERTICAL;
		gbc_userIDLabel.insets = new Insets(0, 0, 5, 0);
		gbc_userIDLabel.gridx = 2;
		gbc_userIDLabel.gridy = 0;
		dataAuxPanel.add(provIDLabel, gbc_userIDLabel);

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

		nameTextField = new JTextField(prov != null ? prov.getNombre() : "");
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

		descTextField = new JTextField(prov != null ? String.valueOf(prov.getDesc()) : "");
		descTextField.setEditable(false);
		GridBagConstraints gbc_telephoneTextField = new GridBagConstraints();
		gbc_telephoneTextField.fill = GridBagConstraints.BOTH;
		gbc_telephoneTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_telephoneTextField.insets = new Insets(0, 0, 5, 0);
		gbc_telephoneTextField.gridx = 2;
		gbc_telephoneTextField.gridy = 2;
		dataAuxPanel.add(descTextField, gbc_telephoneTextField);
		descTextField.setColumns(10);

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

		Component horizontalGlue = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue, BorderLayout.EAST);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue_1, BorderLayout.WEST);

		JSeparator separator = new JSeparator();
		bottomPanel.add(separator, BorderLayout.NORTH);

		setTextFieldsEditable(false);
	}

	private TransferProveedor leerDatos() {
		return new TransferProveedor(prov.getId(), prov.getNombre(), prov.getDesc(),
				controlProd.consultaProducto(prov.getIdProducto()), prov.getStock());
	}

	private void setTextFieldsEditable(boolean b) {
		nameTextField.setEditable(b);
		descTextField.setEditable(b);
	}
}
