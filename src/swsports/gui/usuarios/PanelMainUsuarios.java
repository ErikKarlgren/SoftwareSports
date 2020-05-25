package swsports.gui.usuarios;

import java.awt.Color;
import java.util.LinkedHashMap;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import swsports.gui.AbstractPanelMain;
import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;
import swsports.usuarios.ControladorUsuario;

public class PanelMainUsuarios extends AbstractPanelMain<Usuario> {

	/**
	 * Implementaci�n de {@link BuscarSwingWorker} que busca usuarios seg�n unos
	 * criterios de b�squeda y crea paneles con sus datos. Durante la ejecuci�n de
	 * {@link #doInBackground()} se deshabilita el bot�n de b�squeda del panel
	 * izquierdo.
	 */
	private class BuscarUsuarioWorker extends BuscarSwingWorker {

		@Override
		protected Void doInBackground() throws Exception {
			setSearchButtonEnabled(false);

			String id = idTextField.getText().equals("") ? null : idTextField.getText();
			String nombre = nombreTextField.getText().equals("") ? null : nombreTextField.getText();
			Integer tlfn = tlfnTextField.getText().equals("") ? null : Integer.valueOf(tlfnTextField.getText());
			String direccion = directionTextField.getText().equals("") ? null : directionTextField.getText();
			String email = emailTextField.getText().equals("") ? null : emailTextField.getText();
			Boolean esAdmin = adminDaIgualRadioButton.isSelected() ? null : adminSiRadioButton.isSelected();

			TransferUsuario tUsu = new TransferUsuario(id, nombre, email, null, tlfn, direccion, esAdmin);
			objetos = controlador.busquedaUsuarios(tUsu);

			removeReportablePanels();
			for (Usuario u : objetos) {
				publish(new UsuarioDataPanel(u, tUsu, controlador));
			}
			return null;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.add(new PanelMainUsuarios(new ControladorUsuario()));
		frame.pack();
		frame.setVisible(true);
	}

	private JTextField idTextField;
	private JTextField nombreTextField;
	private JTextField tlfnTextField;
	private JTextField directionTextField;
	private JTextField emailTextField;
	private JRadioButton adminSiRadioButton;
	private JRadioButton adminNoRadioButton;
	private JRadioButton adminDaIgualRadioButton;
	private ControladorUsuario controlador;

	/**
	 * Crea un {@link PanelMainUsuarios} dado un {@link ControladorUsuario}.
	 * 
	 * @param ctrl
	 */
	public PanelMainUsuarios(ControladorUsuario ctrl) {
		super("Usuarios");
		controlador = ctrl;
	}

	@Override
	protected LinkedHashMap<String, JComponent> getComponentesBusqueda() {
		LinkedHashMap<String, JComponent> map = new LinkedHashMap<>();

		idTextField = createTextField();
		map.put("ID", idTextField);
		nombreTextField = createTextField();
		map.put("Nombre", nombreTextField);
		tlfnTextField = createTextField();
		map.put("Telefono", tlfnTextField);
		directionTextField = createTextField();
		map.put("Direccion", directionTextField);
		emailTextField = createTextField();
		map.put("Correo electr�nico", emailTextField);

		JPanel lateralAdminPanel = new JPanel();
		lateralAdminPanel.setBackground(Color.ORANGE);
		lateralAdminPanel.setLayout(new BoxLayout(lateralAdminPanel, BoxLayout.Y_AXIS));

		adminSiRadioButton = new JRadioButton("S\u00ED");
		adminSiRadioButton.setForeground(Color.BLACK);
		adminSiRadioButton.setBackground(Color.ORANGE);
		lateralAdminPanel.add(adminSiRadioButton);

		adminNoRadioButton = new JRadioButton("No");
		adminNoRadioButton.setForeground(Color.BLACK);
		adminNoRadioButton.setBackground(Color.ORANGE);
		lateralAdminPanel.add(adminNoRadioButton);

		adminDaIgualRadioButton = new JRadioButton("No importa");
		adminDaIgualRadioButton.setForeground(Color.BLACK);
		adminDaIgualRadioButton.setBackground(Color.ORANGE);
		adminDaIgualRadioButton.setSelected(true);
		lateralAdminPanel.add(adminDaIgualRadioButton);

		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(adminDaIgualRadioButton);
		radioButtonGroup.add(adminNoRadioButton);
		radioButtonGroup.add(adminSiRadioButton);

		map.put("Es admin", lateralAdminPanel);

		return map;
	}

	@Override
	protected AbstractPanelMain<Usuario>.BuscarSwingWorker getNewSwingWorker() {
		return new BuscarUsuarioWorker();
	}

}
