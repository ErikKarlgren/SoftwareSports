package swsports.daousuarios;

import java.util.List;

import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;

public class FachadaDAOUsuarios implements IFachadaDAOUsuarios {

	private IDAOUsuarios dao;

	public FachadaDAOUsuarios() {
		dao = new DAOUsuarios();
	}

	/**
	 * Devuelve una lista de los usuarios que se adhieren a los parámetros de
	 * búsqueda proporcionados. Los parámetros que sean nulos no se usarán para la
	 * búsqueda.
	 */
	@Override
	public List<Usuario> busquedaUsuarios(TransferUsuario tUsu) {
		return dao.busquedaUsuarios(null);
	}

	/**
	 * Cierra la sesión actual.
	 */
	@Override
	public boolean cerrarSesion() {
		return dao.cerrarSesion();
	}

	/**
	 * Devuelve los datos de un usuario dado su identificador (id).
	 */
	@Override
	public Usuario consultaUsuario(String id) {
		return dao.consultaUsuario(id);
	}

	/**
	 * Da de baja a un usuario dado su identificador (id).
	 */
	@Override
	public boolean darDeBajaUsuario(String id) {
		return dao.darDeBajaUsuario(id);
	}

	/**
	 * Edita los datos de un usuario. Su identificador (id) se usará para buscar el
	 * usuario que se quiere editar y se sobrescribirán el resto de datos.
	 */
	@Override
	public boolean editarUsuario(TransferUsuario usu) {
		return dao.editarUsuario(usu);
	}

	/**
	 * Inicia la sesión de un usuario dado su identificador (id) y su contraseña.
	 */
	@Override
	public boolean login(String id, String contrasenya) {
		return dao.login(id, contrasenya);
	}

	/**
	 * Da de alta un usuario y lo registra en la apliación.
	 */
	@Override
	public boolean registroUsuario(Usuario usu) {
		return dao.registroUsuario(usu);
	}

}
