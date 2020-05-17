package swsports.usuarios;

import java.util.List;

import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;

/**
 * Clase que sirve como fachada del módulo Usuarios. Todas las operaciones que
 * tengan que ver con dicho módulo se realizan mediante llamadas a los métodos
 * de esta clase.
 */
public class FachadaUsuarios implements IFachadaUsuarios {

	private ISAUsuarios sa;

	public FachadaUsuarios() {
		sa = new SAUsuarios();
	}

	/**
	 * Devuelve una lista de los usuarios que se adhieren a los parámetros de
	 * búsqueda proporcionados. Los parámetros que sean nulos no se usarán para la
	 * búsqueda.
	 */
	@Override
	public List<Usuario> busquedaUsuarios(TransferUsuario tUsu) {
		return sa.busquedaUsuarios(null);
	}

	/**
	 * Cierra la sesión actual.
	 */
	@Override
	public boolean cerrarSesion() {
		return sa.cerrarSesion();
	}

	/**
	 * Devuelve los datos de un usuario dado su identificador (id).
	 */
	@Override
	public Usuario consultaUsuario(String id) {
		return sa.consultaUsuario(id);
	}

	/**
	 * Da de baja a un usuario dado su identificador (id).
	 */
	@Override
	public boolean darDeBajaUsuario(String id) {
		return sa.darDeBajaUsuario(id);
	}

	/**
	 * Edita los datos de un usuario. Su identificador (id) se usará para buscar el
	 * usuario que se quiere editar y se sobrescribirán el resto de datos.
	 */
	@Override
	public boolean editarUsuario(TransferUsuario usu) {
		return sa.editarUsuario(usu);
	}

	/**
	 * Inicia la sesión de un usuario dado su identificador (id) y su contraseña.
	 */
	@Override
	public boolean login(String id, String contrasenya) {
		return sa.login(id, contrasenya);
	}

	/**
	 * Da de alta un usuario y lo registra en la apliación.
	 */
	@Override
	public boolean registroUsuario(Usuario usu) {
		return sa.registroUsuario(usu);
	}

}
