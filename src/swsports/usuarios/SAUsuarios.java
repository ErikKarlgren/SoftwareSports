package swsports.usuarios;

import java.util.List;

import swsports.daousuarios.FachadaDAOUsuarios;
import swsports.daousuarios.IFachadaDAOUsuarios;
import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;

/**
 * Esta clase es la encargada de la lógica de negocio del módulo Usuarios.
 */
class SAUsuarios implements ISAUsuarios {

	private IFachadaDAOUsuarios dao;

	SAUsuarios() {
		dao = new FachadaDAOUsuarios();
	}

	@Override
	public List<Usuario> busquedaUsuarios(TransferUsuario tUsu) {
		return dao.busquedaUsuarios(tUsu);
	}

	@Override
	public boolean cerrarSesion() {
		// FIXME no hace falta consultar la base de datos a menos que queramos guardar
		// el cierre de sesión
		throw new UnsupportedOperationException("Hay que empezar a diseñar la GUI primero.");
		// return dao.cerrarSesion();
	}

	@Override
	public Usuario consultaUsuario(String id) {
		return id == null ? null : dao.consultaUsuario(id);
	}

	@Override
	public boolean darDeBajaUsuario(String id) {
		return (dao.consultaUsuario(id) != null) && dao.darDeBajaUsuario(id);
	}

	@Override
	public boolean editarUsuario(TransferUsuario tUsu) {
		return tUsu != null && dao.editarUsuario(tUsu);
	}

	@Override
	public boolean login(String id, String contrasenya) {
		return id != null && contrasenya != null && dao.login(id, contrasenya);
	}

	@Override
	public boolean registroUsuario(Usuario usu) {
		return usu != null && (dao.consultaUsuario(usu.getId()) == null) && dao.registroUsuario(usu);
	}

}
