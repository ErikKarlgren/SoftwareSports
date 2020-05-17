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
	 * Devuelve una lista de los usuarios que se adhieren a los par�metros de
	 * b�squeda proporcionados. Los par�metros que sean nulos no se usar�n para la
	 * b�squeda.
	 */
	@Override
	public List<Usuario> busquedaUsuarios(TransferUsuario tUsu) {
		return dao.busquedaUsuarios(null);
	}

	/**
	 * Cierra la sesi�n actual.
	 */
	@Override
	public boolean cerrarSesion() {
		// FIXME no hace falta consultar la base de datos a menos que queramos guardar el cierre de sesi�n
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
	 * Edita los datos de un usuario. Su identificador (id) se usar� para buscar el
	 * usuario que se quiere editar y se sobrescribir�n el resto de datos.
	 */
	@Override
	public boolean editarUsuario(TransferUsuario usu) {
		return usu != null && dao.editarUsuario(usu);
	}

	/**
	 * Inicia la sesi�n de un usuario dado su identificador (id) y su contrase�a.
	 */
	@Override
	public boolean login(String id, String contrasenya) {
		return id != null && contrasenya != null && dao.login(id, contrasenya);
	}

	/**
	 * Da de alta un usuario y lo registra en la apliaci�n.
	 */
	@Override
	public boolean registroUsuario(Usuario usu) {
		return usu != null && (dao.consultaUsuario(usu.getId()) == null) && dao.registroUsuario(usu);
	}

}
