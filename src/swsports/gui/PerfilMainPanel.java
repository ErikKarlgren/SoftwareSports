package swsports.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JFrame;

import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;
import swsports.usuarios.ControladorUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ItemEvent;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;
import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
	private JLabel isAdminLabel;
	private JToggleButton editUserButton;
	private Usuario usuario;
	private ControladorUsuario controlador;

	/**
	 * Create the panel.
	 */
	public PerfilMainPanel(Usuario usu, ControladorUsuario ctrl) {

		this.usuario = usu;
		this.controlador = ctrl;
		setLayout(new BorderLayout(0, 0));

		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, BorderLayout.NORTH);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		add(verticalStrut_1, BorderLayout.SOUTH);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, BorderLayout.EAST);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 41, 68, 0, 0 };
		gbl_panel.rowHeights = new int[] { 20, 20, 26, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		userIDLabel = new JLabel(
				usuario != null ? usuario.getId() : "(deber\u00EDa aparecer el ID del usuario aqu\u00ED)");
		userIDLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_userIDLabel = new GridBagConstraints();
		gbc_userIDLabel.anchor = GridBagConstraints.WEST;
		gbc_userIDLabel.fill = GridBagConstraints.VERTICAL;
		gbc_userIDLabel.insets = new Insets(0, 0, 5, 0);
		gbc_userIDLabel.gridx = 2;
		gbc_userIDLabel.gridy = 0;
		panel.add(userIDLabel, gbc_userIDLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		nameTextField = new JTextField(usuario != null ? usuario.getNombre() : "");
		nameTextField.setEditable(false);
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.fill = GridBagConstraints.BOTH;
		gbc_nameTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_nameTextField.gridx = 2;
		gbc_nameTextField.gridy = 1;
		panel.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tel\u00E9fono");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		telephoneTextField = new JTextField(usuario != null ? String.valueOf(usuario.getTelefono()) : "");
		telephoneTextField.setEditable(false);
		GridBagConstraints gbc_telephoneTextField = new GridBagConstraints();
		gbc_telephoneTextField.fill = GridBagConstraints.BOTH;
		gbc_telephoneTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_telephoneTextField.insets = new Insets(0, 0, 5, 0);
		gbc_telephoneTextField.gridx = 2;
		gbc_telephoneTextField.gridy = 2;
		panel.add(telephoneTextField, gbc_telephoneTextField);
		telephoneTextField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Direcci\u00F3n");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		directionTextField = new JTextField(usuario != null ? usuario.getDireccion() : "");
		directionTextField.setEditable(false);
		GridBagConstraints gbc_directionTextField = new GridBagConstraints();
		gbc_directionTextField.fill = GridBagConstraints.BOTH;
		gbc_directionTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_directionTextField.insets = new Insets(0, 0, 5, 0);
		gbc_directionTextField.gridx = 2;
		gbc_directionTextField.gridy = 3;
		panel.add(directionTextField, gbc_directionTextField);
		directionTextField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Correo electr\u00F3nico");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		emailTextField = new JTextField(usuario != null ? usuario.getMail() : "");
		emailTextField.setEditable(false);
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.fill = GridBagConstraints.BOTH;
		gbc_emailTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_emailTextField.insets = new Insets(0, 0, 5, 0);
		gbc_emailTextField.gridx = 2;
		gbc_emailTextField.gridy = 4;
		panel.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Contrase\u00F1a");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 5;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		passwordField = new JPasswordField(usuario != null ? usuario.getContrasenya() : "");
		passwordField.setEditable(false);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 5;
		panel.add(passwordField, gbc_passwordField);

		JLabel lblNewLabel_5 = new JLabel("Administrador");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridwidth = 2;
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 6;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		editUserButton = new JToggleButton("Editar perfil");
		editUserButton.addItemListener(this::editar);

		isAdminLabel = new JLabel("Usuario nulo");
		isAdminLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_isAdminLabel = new GridBagConstraints();
		gbc_isAdminLabel.anchor = GridBagConstraints.WEST;
		gbc_isAdminLabel.insets = new Insets(0, 0, 5, 0);
		gbc_isAdminLabel.gridx = 2;
		gbc_isAdminLabel.gridy = 6;
		panel.add(isAdminLabel, gbc_isAdminLabel);
		GridBagConstraints gbc_editUserButton = new GridBagConstraints();
		gbc_editUserButton.gridwidth = 3;
		gbc_editUserButton.insets = new Insets(0, 0, 0, 5);
		gbc_editUserButton.gridx = 0;
		gbc_editUserButton.gridy = 8;
		panel.add(editUserButton, gbc_editUserButton);
		if (usuario != null) {
			if (usuario.esAdmin())
				isAdminLabel.setText("Sí");
			else
				isAdminLabel.setText("No");
		}

	}

	private void editar(ItemEvent i) {
		// System.out.println("Editar");
		// if (usuario != null) {
		if (i.getStateChange() == ItemEvent.SELECTED) {
			setTextFieldsEditable(true);
		} else {
			setTextFieldsEditable(false);
			if(!nameTextField.getText().trim().equals("") && !String.copyValueOf(passwordField.getPassword()).trim().equals("") &&
			   !telephoneTextField.getText().trim().equals("")) {
				controlador.editarUsuario(leerDatos());
			}
			else {
				editUserButton.doClick(); 
				JOptionPane.showMessageDialog(this,"Datos incompletos (unicos optativos: mail y direccion)", "Edit Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		// }
	}

	private void setTextFieldsEditable(boolean b) {
		nameTextField.setEditable(b);
		telephoneTextField.setEditable(b);
		directionTextField.setEditable(b);
		emailTextField.setEditable(b);
		passwordField.setEditable(b);
	}

	private TransferUsuario leerDatos() {
		return new TransferUsuario(usuario.getId(), nameTextField.getText(), emailTextField.getText(),
				String.copyValueOf(passwordField.getPassword()), Integer.valueOf(telephoneTextField.getText()),
				directionTextField.getText(), usuario.esAdmin());
	}
}
