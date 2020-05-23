package swsports.gui.usuarios;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import swsports.gui.DataPanel;
import swsports.gui.PerfilMainPanel;
import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;
import swsports.usuarios.ControladorUsuario;

/**
 * Panel que muestra los datos de un {@link Usuario}. Permite además editar sus
 * datos (incluso conceder o revocar privilegios de administrador) y darlo de
 * baja.
 */
public class UsuarioDataPanel extends DataPanel<Usuario> {

	/**
	 * Diálogo que muestra el panel para editar el perfil de un usuario, incluyendo
	 * si concederle o revocarle privilegios de administrador.
	 */
	private class EditarUsuarioDialog extends JDialog {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		EditarUsuarioDialog() {
			super();
			this.setTitle("Editar usuario");
			this.add(new PerfilMainPanel(object, controlador, true));
			this.pack();
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ControladorUsuario ctrl = new ControladorUsuario();
			Usuario usu = ctrl.consultaUsuario("z9");

			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			frame.add(new UsuarioDataPanel(usu, null, ctrl));
			frame.pack();
			frame.setVisible(true);
		});
	}

	private ControladorUsuario controlador;

	/**
	 * Crea un panel con los datos de un {@link Usuario} y siguiendo las
	 * restricciones guardadas en un {@link TransferUsuario} (los atributos nulos
	 * implican que sus datos correspondientes no se mostrarán). El
	 * {@link ControladorUsuario} es necesario para poder realizar las acciones de
	 * editar el usuario o darlo de baja.
	 * 
	 * @param usu         {@link Usuario} del que queremos mostrar sus datos.
	 * @param constraints Restricciones a la hora de mostrar o no los atributos de
	 *                    <code>usu</code>. Los atributos nulos de
	 *                    <code>constraints</code> implican que los atributos
	 *                    correspondientes de <code>usu</code> no se mostrarán. Si
	 *                    <code>constraints</code> es nulo se mostrarán todos los
	 *                    datos.
	 * @param ctrl        Controlador para poder interactuar con el resto del
	 *                    programa.
	 */
	public UsuarioDataPanel(Usuario usu, TransferUsuario constraints, ControladorUsuario ctrl) {
		super(usu);
		this.controlador = ctrl;
		addData();
		addActions();
	}

	/**
	 * Añade las acciones del panel: editar un usuario y darlo de baja.
	 */
	private void addActions() {
		addAction("Editar usuario", a -> new EditarUsuarioDialog());
		addAction("Dar de baja", a -> darDeBaja());
	}

	/**
	 * Añade los campos del usuario al panel siguiendo los criterios marcados por un
	 * {@link TransferUsuario}.
	 */
	private void addData() {
		addDataField("Nombre", object.getNombre());
		addDataField("Telefono", String.valueOf(object.getTelefono()));
		addDataField("Dirección", object.getDireccion());
		addDataField("Correo electrónico", object.getMail());
		if (object.esAdmin())
			addDataField("Administrador", "");
	}

	/**
	 * Da de baja a un {@link Usuario} si el usuario de la aplicación confirma su
	 * decisión.
	 */
	private void darDeBaja() {
		String[] options = { "Sí", "No" };
		int option = JOptionPane.showOptionDialog(this, "¿Seguro que quieres dar de baja a este usuario?",
				"Dar usuario de baja", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlador.darDeBajaUsuario(object.getId());
		}
	}
}
