package swsports.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import swsports.modelo.Usuario;
import swsports.productos.ControladorProductos;
import swsports.proveedores.ControladorProveedores;
import swsports.usuarios.ControladorUsuario;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.util.Arrays;
import java.awt.Color;
import java.awt.SystemColor;

/**
 * Ventana para iniciar sesión o registrarse como usuario en el programa. Debe
 * ser la primera ventana en abrirse. Después de que un usuario haya iniciado
 * sesión, se cerrará esta ventana y se abrirá la ventana principal.
 */
public class LoginWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControladorUsuario controladorUsuario;
	private ControladorProductos controladorProductos;
	private ControladorProveedores controladorProveedores;
	private JPanel contentPane;
	private JTextField loginIDTextField;
	private JPasswordField loginPasswordField;
	private JTextField registerIDTextfield;
	private JTextField registerNameTextfield;
	private JTextField registerTlfnTextfield;
	private JTextField registerDirectionTextfield;
	private JTextField registerEmailTextfield;
	private JPasswordField registerPasswordField;
	private JPasswordField registerConfirmPasswordField;
	private ButtonGroup adminSiONoButtonGroup;

	/**
	 * Create the frame.
	 */
	public LoginWindow(ControladorUsuario ctrlUsu, ControladorProductos ctrlProd, ControladorProveedores ctrlProv) {
		setBackground(Color.ORANGE);
		controladorUsuario = ctrlUsu;
		controladorProductos = ctrlProd;
		controladorProveedores = ctrlProv;

		setTitle("Software Sports");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 575);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		contentPane.add(tabbedPane);

		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(Color.WHITE);
		tabbedPane.addTab("Iniciar sesi\u00F3n", null, new JScrollPane(panelLogin), null);
		panelLogin.setLayout(new BorderLayout(0, 0));

		Component horizontalStrutLeft = Box.createHorizontalStrut(10);
		panelLogin.add(horizontalStrutLeft, BorderLayout.WEST);

		Component horizontalStrutRight = Box.createHorizontalStrut(10);
		panelLogin.add(horizontalStrutRight, BorderLayout.EAST);

		Component verticalStrutUp = Box.createVerticalStrut(10);
		panelLogin.add(verticalStrutUp, BorderLayout.NORTH);

		Component verticalStrutBottom = Box.createVerticalStrut(10);
		panelLogin.add(verticalStrutBottom, BorderLayout.SOUTH);

		JPanel panelAuxLogin = new JPanel();
		panelAuxLogin.setBackground(Color.WHITE);
		panelLogin.add(panelAuxLogin, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelAuxLogin.setLayout(gbl_panel);

		JLabel lblDescription = new JLabel("Introduzca sus datos para iniciar sesi\u00F3n.");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.gridheight = 2;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 0);
		gbc_lblDescription.fill = GridBagConstraints.BOTH;
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 0;
		panelAuxLogin.add(lblDescription, gbc_lblDescription);

		JLabel loginIDLabel = new JLabel("ID");
		GridBagConstraints gbc_loginIDLabel = new GridBagConstraints();
		gbc_loginIDLabel.anchor = GridBagConstraints.WEST;
		gbc_loginIDLabel.insets = new Insets(0, 0, 5, 0);
		gbc_loginIDLabel.gridx = 0;
		gbc_loginIDLabel.gridy = 2;
		panelAuxLogin.add(loginIDLabel, gbc_loginIDLabel);

		loginIDTextField = new JTextField();
		GridBagConstraints gbc_LoginIDTextField = new GridBagConstraints();
		gbc_LoginIDTextField.insets = new Insets(0, 0, 5, 0);
		gbc_LoginIDTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_LoginIDTextField.gridx = 0;
		gbc_LoginIDTextField.gridy = 3;
		panelAuxLogin.add(loginIDTextField, gbc_LoginIDTextField);
		loginIDTextField.setColumns(10);

		JLabel loginPasswordLabel = new JLabel("Contrase\u00F1a");
		GridBagConstraints gbc_loginPasswordLabel = new GridBagConstraints();
		gbc_loginPasswordLabel.anchor = GridBagConstraints.WEST;
		gbc_loginPasswordLabel.insets = new Insets(0, 0, 5, 0);
		gbc_loginPasswordLabel.gridx = 0;
		gbc_loginPasswordLabel.gridy = 4;
		panelAuxLogin.add(loginPasswordLabel, gbc_loginPasswordLabel);

		loginPasswordField = new JPasswordField();
		GridBagConstraints gbc_LoginPasswordField = new GridBagConstraints();
		gbc_LoginPasswordField.insets = new Insets(0, 0, 5, 0);
		gbc_LoginPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_LoginPasswordField.gridx = 0;
		gbc_LoginPasswordField.gridy = 5;
		panelAuxLogin.add(loginPasswordField, gbc_LoginPasswordField);

		JButton loginButton = new JButton("Iniciar sesi\u00F3n");
		loginButton.addActionListener(a -> login());

		Component verticalStrut = Box.createVerticalStrut(5);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 6;
		panelAuxLogin.add(verticalStrut, gbc_verticalStrut);
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.gridx = 0;
		gbc_loginButton.gridy = 7;
		panelAuxLogin.add(loginButton, gbc_loginButton);

		JPanel panelRegistro = new JPanel();
		panelRegistro.setBackground(Color.WHITE);
		tabbedPane.addTab("Registrarse", null, new JScrollPane(panelRegistro), null);
		panelRegistro.setLayout(new BorderLayout(0, 0));

		Component horizontalStrutLeft2 = Box.createHorizontalStrut(10);
		panelRegistro.add(horizontalStrutLeft2, BorderLayout.WEST);

		Component horizontalStrutRight2 = Box.createHorizontalStrut(10);
		panelRegistro.add(horizontalStrutRight2, BorderLayout.EAST);

		Component verticalStrutUp2 = Box.createVerticalStrut(10);
		panelRegistro.add(verticalStrutUp2, BorderLayout.NORTH);

		Component verticalStrutBottom2 = Box.createVerticalStrut(12);
		panelRegistro.add(verticalStrutBottom2, BorderLayout.SOUTH);

		JPanel panelAuxRegistro = new JPanel();
		panelAuxRegistro.setBackground(Color.WHITE);
		panelRegistro.add(panelAuxRegistro, BorderLayout.CENTER);
		GridBagLayout gbl_panelAuxRegistro = new GridBagLayout();
		gbl_panelAuxRegistro.columnWidths = new int[] { 0, 0 };
		gbl_panelAuxRegistro.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelAuxRegistro.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelAuxRegistro.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelAuxRegistro.setLayout(gbl_panelAuxRegistro);

		JLabel lblNewLabel_2 = new JLabel("Cree un nuevo usuario rellenando los siguientes datos.");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridheight = 2;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panelAuxRegistro.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel RegisterIDLabel = new JLabel("ID*");
		GridBagConstraints gbc_RegisterIDLabel = new GridBagConstraints();
		gbc_RegisterIDLabel.insets = new Insets(0, 0, 5, 0);
		gbc_RegisterIDLabel.fill = GridBagConstraints.BOTH;
		gbc_RegisterIDLabel.gridx = 0;
		gbc_RegisterIDLabel.gridy = 2;
		panelAuxRegistro.add(RegisterIDLabel, gbc_RegisterIDLabel);

		registerIDTextfield = new JTextField();
		GridBagConstraints gbc_RegisterIDTextfield = new GridBagConstraints();
		gbc_RegisterIDTextfield.insets = new Insets(0, 0, 5, 0);
		gbc_RegisterIDTextfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_RegisterIDTextfield.gridx = 0;
		gbc_RegisterIDTextfield.gridy = 3;
		panelAuxRegistro.add(registerIDTextfield, gbc_RegisterIDTextfield);
		registerIDTextfield.setColumns(10);

		JLabel RegisterNameLabel = new JLabel("Nombre*");
		GridBagConstraints gbc_RegisterNameLabel = new GridBagConstraints();
		gbc_RegisterNameLabel.fill = GridBagConstraints.BOTH;
		gbc_RegisterNameLabel.insets = new Insets(0, 0, 5, 0);
		gbc_RegisterNameLabel.gridx = 0;
		gbc_RegisterNameLabel.gridy = 4;
		panelAuxRegistro.add(RegisterNameLabel, gbc_RegisterNameLabel);

		registerNameTextfield = new JTextField();
		GridBagConstraints gbc_RegisterNameTextfield = new GridBagConstraints();
		gbc_RegisterNameTextfield.insets = new Insets(0, 0, 5, 0);
		gbc_RegisterNameTextfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_RegisterNameTextfield.gridx = 0;
		gbc_RegisterNameTextfield.gridy = 5;
		panelAuxRegistro.add(registerNameTextfield, gbc_RegisterNameTextfield);
		registerNameTextfield.setColumns(10);

		JLabel RegisterTlfnLabel = new JLabel("Tel\u00E9fono*");
		GridBagConstraints gbc_RegisterTlfnLabel = new GridBagConstraints();
		gbc_RegisterTlfnLabel.fill = GridBagConstraints.BOTH;
		gbc_RegisterTlfnLabel.insets = new Insets(0, 0, 5, 0);
		gbc_RegisterTlfnLabel.gridx = 0;
		gbc_RegisterTlfnLabel.gridy = 6;
		panelAuxRegistro.add(RegisterTlfnLabel, gbc_RegisterTlfnLabel);

		registerTlfnTextfield = new JTextField();
		GridBagConstraints gbc_RegisterTlfnTextfield = new GridBagConstraints();
		gbc_RegisterTlfnTextfield.insets = new Insets(0, 0, 5, 0);
		gbc_RegisterTlfnTextfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_RegisterTlfnTextfield.gridx = 0;
		gbc_RegisterTlfnTextfield.gridy = 7;
		panelAuxRegistro.add(registerTlfnTextfield, gbc_RegisterTlfnTextfield);
		registerTlfnTextfield.setColumns(10);

		JLabel RegisterDirectionLabel = new JLabel("Direcci\u00F3n");
		GridBagConstraints gbc_RegisterDirectionLabel = new GridBagConstraints();
		gbc_RegisterDirectionLabel.fill = GridBagConstraints.BOTH;
		gbc_RegisterDirectionLabel.insets = new Insets(0, 0, 5, 0);
		gbc_RegisterDirectionLabel.gridx = 0;
		gbc_RegisterDirectionLabel.gridy = 8;
		panelAuxRegistro.add(RegisterDirectionLabel, gbc_RegisterDirectionLabel);

		registerDirectionTextfield = new JTextField();
		GridBagConstraints gbc_RegisterDirectionTextfield = new GridBagConstraints();
		gbc_RegisterDirectionTextfield.insets = new Insets(0, 0, 5, 0);
		gbc_RegisterDirectionTextfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_RegisterDirectionTextfield.gridx = 0;
		gbc_RegisterDirectionTextfield.gridy = 9;
		panelAuxRegistro.add(registerDirectionTextfield, gbc_RegisterDirectionTextfield);
		registerDirectionTextfield.setColumns(10);

		JLabel RegisterEmailLabel = new JLabel("Correo electr\u00F3nico");
		GridBagConstraints gbc_RegisterEmailLabel = new GridBagConstraints();
		gbc_RegisterEmailLabel.fill = GridBagConstraints.BOTH;
		gbc_RegisterEmailLabel.insets = new Insets(0, 0, 5, 0);
		gbc_RegisterEmailLabel.gridx = 0;
		gbc_RegisterEmailLabel.gridy = 10;
		panelAuxRegistro.add(RegisterEmailLabel, gbc_RegisterEmailLabel);

		registerEmailTextfield = new JTextField();
		GridBagConstraints gbc_RegisterEmailTextfield = new GridBagConstraints();
		gbc_RegisterEmailTextfield.insets = new Insets(0, 0, 5, 0);
		gbc_RegisterEmailTextfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_RegisterEmailTextfield.gridx = 0;
		gbc_RegisterEmailTextfield.gridy = 11;
		panelAuxRegistro.add(registerEmailTextfield, gbc_RegisterEmailTextfield);
		registerEmailTextfield.setColumns(10);

		JLabel registerPasswordLabel = new JLabel("Contrase\u00F1a*");
		GridBagConstraints gbc_RegisterPasswordLabel = new GridBagConstraints();
		gbc_RegisterPasswordLabel.fill = GridBagConstraints.BOTH;
		gbc_RegisterPasswordLabel.insets = new Insets(0, 0, 5, 0);
		gbc_RegisterPasswordLabel.gridx = 0;
		gbc_RegisterPasswordLabel.gridy = 12;
		panelAuxRegistro.add(registerPasswordLabel, gbc_RegisterPasswordLabel);

		registerPasswordField = new JPasswordField();
		GridBagConstraints gbc_registerPasswordField = new GridBagConstraints();
		gbc_registerPasswordField.insets = new Insets(0, 0, 5, 0);
		gbc_registerPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerPasswordField.gridx = 0;
		gbc_registerPasswordField.gridy = 13;
		panelAuxRegistro.add(registerPasswordField, gbc_registerPasswordField);

		JLabel registerConfirmPassowordLabel = new JLabel("Confirmar contrase\u00F1a*");
		GridBagConstraints gbc_registerConfirmPassowordLabel = new GridBagConstraints();
		gbc_registerConfirmPassowordLabel.fill = GridBagConstraints.BOTH;
		gbc_registerConfirmPassowordLabel.insets = new Insets(0, 0, 5, 0);
		gbc_registerConfirmPassowordLabel.gridx = 0;
		gbc_registerConfirmPassowordLabel.gridy = 14;
		panelAuxRegistro.add(registerConfirmPassowordLabel, gbc_registerConfirmPassowordLabel);

		registerConfirmPasswordField = new JPasswordField();
		GridBagConstraints gbc_registerConfirmPasswordField = new GridBagConstraints();
		gbc_registerConfirmPasswordField.insets = new Insets(0, 0, 5, 0);
		gbc_registerConfirmPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerConfirmPasswordField.gridx = 0;
		gbc_registerConfirmPasswordField.gridy = 15;
		panelAuxRegistro.add(registerConfirmPasswordField, gbc_registerConfirmPasswordField);

		JLabel adminLabel = new JLabel("\u00BFConceder privilegios de administrador?");
		GridBagConstraints gbc_adminLabel = new GridBagConstraints();
		gbc_adminLabel.fill = GridBagConstraints.BOTH;
		gbc_adminLabel.insets = new Insets(0, 0, 5, 0);
		gbc_adminLabel.gridx = 0;
		gbc_adminLabel.gridy = 16;
		panelAuxRegistro.add(adminLabel, gbc_adminLabel);

		JPanel adminPanel = new JPanel();
		GridBagConstraints gbc_adminPanel = new GridBagConstraints();
		gbc_adminPanel.insets = new Insets(0, 0, 5, 0);
		gbc_adminPanel.fill = GridBagConstraints.BOTH;
		gbc_adminPanel.gridx = 0;
		gbc_adminPanel.gridy = 17;
		panelAuxRegistro.add(adminPanel, gbc_adminPanel);

		JRadioButton yesAdminButton = new JRadioButton("S\u00ED");
		yesAdminButton.setActionCommand("Si");
		adminPanel.add(yesAdminButton);

		JRadioButton noAdminButton = new JRadioButton("No");
		noAdminButton.setSelected(true);
		adminPanel.add(noAdminButton);

		adminSiONoButtonGroup = new ButtonGroup();
		adminSiONoButtonGroup.add(yesAdminButton);
		adminSiONoButtonGroup.add(noAdminButton);

		JButton registerButton = new JButton("Registrar usuario");
		registerButton.addActionListener(a -> registrar());
		GridBagConstraints gbc_registerButton = new GridBagConstraints();
		gbc_registerButton.gridx = 0;
		gbc_registerButton.gridy = 18;
		panelAuxRegistro.add(registerButton, gbc_registerButton);

		this.pack();
		this.setResizable(false);
	}

	/**
	 * Intenta iniciar sesión con los datos introducidos por el usuario.
	 */
	private void login() {
		String id = loginIDTextField.getText();
		String contrasenya = new String(loginPasswordField.getPassword());

		if (controladorUsuario.login(id, contrasenya)) {
			new MainWindow(controladorUsuario, controladorProductos, controladorProveedores);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Usuario y/o contraseÃ±a incorrectos", "Login Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Intenta registrar a un usuario en la base de datos. Para acceder al programa
	 * tendrá que iniciar sesión usando los datos de la cuenta que acaba de crear en
	 * caso de éxito.
	 */
	private void registrar() {
		if (!datosEstanCompletos()) {
			JOptionPane.showMessageDialog(this, "Datos incompletos (unicos optativos: mail y direccion)",
					"Error al registrarse", JOptionPane.ERROR_MESSAGE);
		} else if (!Arrays.equals(registerConfirmPasswordField.getPassword(), registerPasswordField.getPassword())) {
			JOptionPane.showMessageDialog(this, "La contrase\u00f1a debe coincidir en ambos campos de texto.",
					"Error al registrarse", JOptionPane.WARNING_MESSAGE);
		} else if (controladorUsuario.registroUsuario(crearUsuario())) {
			JOptionPane.showMessageDialog(this,
					"Ha podido registrarse con \u00e9xito. Inicie sesi\u00f3n para comenzar a usar Software Sports.",
					"Registro completado", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Usuario ya existente", "Error al registrarse",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Crea un {@link Usuario} a partir de los datos introducidos en los campos de
	 * texto de la pestaña para registrar un nuevo usuario. Debe comprobarse antes
	 * que el formato de los datos de dichos campos de texto sea el correcto así
	 * como que se hayan introducido los datos obligatorios.
	 * 
	 * @return Un nuevo {@link Usuario} para registrarse.
	 */
	private Usuario crearUsuario() {
		String id = registerIDTextfield.getText();
		String nombre = registerNameTextfield.getText();
		String mail = registerEmailTextfield.getText();
		Integer telefono = Integer.parseInt(registerTlfnTextfield.getText());
		String direccion = registerDirectionTextfield.getText();
		boolean admin = adminSiONoButtonGroup.getSelection().getActionCommand().equals("Si");
		String contrasenya = new String(registerPasswordField.getPassword());
		return new Usuario(id, nombre, mail, contrasenya, telefono, direccion, admin);
	}

	/**
	 * Comprueba que se hayan introducido todos los datos obligatorios.
	 * 
	 * @return <code>true</code> si están todos los datos necesarios,
	 *         <code>false</code> en caso contrario.
	 */
	private boolean datosEstanCompletos() {
		return !registerIDTextfield.getText().trim().equals("") && !registerNameTextfield.getText().trim().equals("")
				&& !registerTlfnTextfield.getText().trim().equals("")
				&& (registerPasswordField.getPassword().length != 0)
				&& (registerConfirmPasswordField.getPassword().length != 0)
				&& (adminSiONoButtonGroup.getSelection() != null);
	}
}
