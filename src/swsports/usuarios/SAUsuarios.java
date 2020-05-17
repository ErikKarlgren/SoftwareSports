package swsports.usuarios;

import java.util.List;

import swsports.daousuarios.FachadaDAOUsuarios;
import swsports.daousuarios.IFachadaDAOUsuarios;
import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;

class SAUsuarios implements ISAUsuarios {

	private IFachadaDAOUsuarios dao;

	public SAUsuarios() {
		dao = new FachadaDAOUsuarios();
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
		// FIXME no hace falta consultar la base de datos a menos que queramos guardar el cierre de sesión
		return dao.cerrarSesion();
	}

	/**
	 * Devuelve los datos de un usuario dado su identificador (id).
	 */
	@Override
	public Usuario consultaUsuario(String id) {
		return id == null ? null : dao.consultaUsuario(id);
	}

	/**
	 * Da de baja a un usuario dado su identificador (id).
	 */
	@Override
	public boolean darDeBajaUsuario(String id) {
		return (dao.consultaUsuario(id) == null) && dao.darDeBajaUsuario(id);
	}

	/**
	 * Edita los datos de un usuario. Su identificador (id) se usará para buscar el
	 * usuario que se quiere editar y se sobrescribirán el resto de datos.
	 */
	@Override
	public boolean editarUsuario(TransferUsuario usu) {
		return usu != null && dao.editarUsuario(usu);
	}

	/**
	 * Inicia la sesión de un usuario dado su identificador (id) y su contraseña.
	 */
	@Override
	public boolean login(String id, String contrasenya) {
		return id != null && contrasenya != null && dao.login(id, contrasenya);
	}

	/**
	 * Da de alta un usuario y lo registra en la apliación.
	 */
	@Override
	public boolean registroUsuario(Usuario usu) {
		return usu != null && (dao.consultaUsuario(usu.getId()) == null) && dao.registroUsuario(usu);
	}

}
