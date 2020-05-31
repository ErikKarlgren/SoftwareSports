package swsports.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;
import swsports.usuarios.ControladorUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ItemEvent;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JSeparator;

/**
 * Panel usado para editar el perfil de un usuario.
 */
public class PerfilMainPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField nameTextField;
	private JTextField telephoneTextField;
	private JTextField directionTextField;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JLabel userIDLabel;
	private JRadioButton siEsAdminButton;
	private JRadioButton noEsAdminButton;
	private JToggleButton editUserButton;
	private Usuario usuario;
	private ControladorUsuario controlador;

	private boolean adminMode;

// Descomentar este constructor cuando no se vaya a editar más la interfaz gráfica.
// Dejarlo descomentado para luego intentar usar el plugin para editarlo provoca problemas.

	/**
	 * Crea un panel para poder editar el perfil de un usuario sin poder dar o
	 * revocar los privilegios de administrador.
	 * 
	 * @param usu  {@link Usuario} del que se quiere consultar el perfil.
	 * @param ctrl Controlador del módulo Usuarios.
	 */
	public PerfilMainPanel(Usuario usu, ControladorUsuario ctrl) {
		this(usu, ctrl, false);
	}

	/**
	 * Crea un panel para poder editar el perfil de un usuario teniendo que
	 * especificar
	 * 
	 * @param usu       {@link Usuario} del que se quiere consultar el perfil.
	 * @param ctrl      Controlador del módulo Usuarios.
	 * @param adminMode Especifica si se pueden dar o quitar los privilegios de
	 *                  administrador (<code>true</code> en caso afirmativo,
	 *                  <code>false</code> en el caso contrario):
	 */
	public PerfilMainPanel(Usuario usu, ControladorUsuario ctrl, boolean adminMode) {
		setBackground(SystemColor.textHighlight);
		this.usuario = usu;
		this.controlador = ctrl;
		this.adminMode = adminMode;
		initGUI();
	}

	/**
	 * Comprueba si se han rellenado todos los datos obligatorios.
	 * 
	 * @return <code>true</code> si se han rellenado todos los datos necesarios,
	 *         <code>false</code> en caso contrario.
	 */
	private boolean datosCompletos() {
		return !nameTextField.getText().trim().equals("")
				&& !String.copyValueOf(passwordField.getPassword()).trim().equals("")
				&& !telephoneTextField.getText().trim().equals("");
	}

	private void editar(ItemEvent i) {
		if (i.getStateChange() == ItemEvent.SELECTED) {
			setTextFieldsEditable(true);
		} else if (datosCompletos()) {
			try {
				controlador.editarUsuario(leerDatos());
				setTextFieldsEditable(false);
				SwingUtilities.invokeLater(() -> JOptionPane.showConfirmDialog(this, "Los nuevos datos se han guardado",
						"Editar usuario", JOptionPane.PLAIN_MESSAGE));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "El formato de alguno de los datos es incorrecto.",
						"Error de formato", JOptionPane.ERROR_MESSAGE);
			}
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

		userIDLabel = new JLabel(
				usuario != null ? usuario.getId() : "(deber\u00EDa aparecer el ID del usuario aqu\u00ED)");
		userIDLabel.setForeground(Color.BLACK);
		userIDLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_userIDLabel = new GridBagConstraints();
		gbc_userIDLabel.anchor = GridBagConstraints.WEST;
		gbc_userIDLabel.fill = GridBagConstraints.VERTICAL;
		gbc_userIDLabel.insets = new Insets(0, 0, 5, 0);
		gbc_userIDLabel.gridx = 2;
		gbc_userIDLabel.gridy = 0;
		dataAuxPanel.add(userIDLabel, gbc_userIDLabel);

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

		nameTextField = new JTextField(usuario != null ? usuario.getNombre() : "");
		nameTextField.setEditable(false);
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.fill = GridBagConstraints.BOTH;
		gbc_nameTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_nameTextField.gridx = 2;
		gbc_nameTextField.gridy = 1;
		dataAuxPanel.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tel\u00E9fono");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		dataAuxPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		telephoneTextField = new JTextField(usuario != null ? String.valueOf(usuario.getTelefono()) : "");
		telephoneTextField.setEditable(false);
		GridBagConstraints gbc_telephoneTextField = new GridBagConstraints();
		gbc_telephoneTextField.fill = GridBagConstraints.BOTH;
		gbc_telephoneTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_telephoneTextField.insets = new Insets(0, 0, 5, 0);
		gbc_telephoneTextField.gridx = 2;
		gbc_telephoneTextField.gridy = 2;
		dataAuxPanel.add(telephoneTextField, gbc_telephoneTextField);
		telephoneTextField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Direcci\u00F3n");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		dataAuxPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		directionTextField = new JTextField(usuario != null ? usuario.getDireccion() : "");
		directionTextField.setEditable(false);
		GridBagConstraints gbc_directionTextField = new GridBagConstraints();
		gbc_directionTextField.fill = GridBagConstraints.BOTH;
		gbc_directionTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_directionTextField.insets = new Insets(0, 0, 5, 0);
		gbc_directionTextField.gridx = 2;
		gbc_directionTextField.gridy = 3;
		dataAuxPanel.add(directionTextField, gbc_directionTextField);
		directionTextField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Correo electr\u00F3nico");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		dataAuxPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		emailTextField = new JTextField(usuario != null ? usuario.getMail() : "");
		emailTextField.setEditable(false);
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.fill = GridBagConstraints.BOTH;
		gbc_emailTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_emailTextField.insets = new Insets(0, 0, 5, 0);
		gbc_emailTextField.gridx = 2;
		gbc_emailTextField.gridy = 4;
		dataAuxPanel.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Contrase\u00F1a");
		lblNewLabel_6.setForeground(Color.BLACK);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 5;
		dataAuxPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		passwordField = new JPasswordField(usuario != null ? usuario.getContrasenya() : "");
		passwordField.setEditable(false);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 5;
		dataAuxPanel.add(passwordField, gbc_passwordField);

		JLabel lblNewLabel_5 = new JLabel("Administrador");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_5.gridwidth = 2;
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 6;
		dataAuxPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		JPanel adminButtonsPanel = new JPanel();
		adminButtonsPanel.setBackground(dataAuxPanel.getBackground());
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 6;
		dataAuxPanel.add(adminButtonsPanel, gbc_panel_1);

		siEsAdminButton = new JRadioButton("S\u00ED");
		siEsAdminButton.setForeground(Color.BLACK);
		siEsAdminButton.setBackground(dataAuxPanel.getBackground());
		adminButtonsPanel.add(siEsAdminButton);

		noEsAdminButton = new JRadioButton("No");
		noEsAdminButton.setForeground(Color.BLACK);
		noEsAdminButton.setBackground(dataAuxPanel.getBackground());
		adminButtonsPanel.add(noEsAdminButton);

		ButtonGroup adminButtonGroup = new ButtonGroup();
		adminButtonGroup.add(noEsAdminButton);
		adminButtonGroup.add(siEsAdminButton);

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

		editUserButton = new JToggleButton("Editar perfil");
		editUserButton.addItemListener(this::editar);
		editUserButton.setBackground(Color.WHITE);
		bottomAuxPanel.add(editUserButton);

		Component horizontalGlue = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue, BorderLayout.EAST);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		bottomPanel.add(horizontalGlue_1, BorderLayout.WEST);

		JSeparator separator = new JSeparator();
		bottomPanel.add(separator, BorderLayout.NORTH);

		if (usuario != null) {
			noEsAdminButton.setSelected(!usuario.esAdmin());
			siEsAdminButton.setSelected(usuario.esAdmin());
		}
		setTextFieldsEditable(false);
	}

	private TransferUsuario leerDatos() {
		boolean hacerAdmin = siEsAdminButton.isSelected();
		return new TransferUsuario(usuario.getId(), nameTextField.getText(), emailTextField.getText(),
				String.copyValueOf(passwordField.getPassword()), Integer.valueOf(telephoneTextField.getText()),
				directionTextField.getText(), hacerAdmin);
	}

	private void setTextFieldsEditable(boolean b) {
		nameTextField.setEditable(b);
		telephoneTextField.setEditable(b);
		directionTextField.setEditable(b);
		emailTextField.setEditable(b);
		passwordField.setEditable(b);

		if (!b || adminMode) {
			siEsAdminButton.setEnabled(b);
			noEsAdminButton.setEnabled(b);
		}
	}
}
