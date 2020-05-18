package swsports.daousuarios;

import java.util.List;

import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;

/**
 * Implementación de la fachada del módulo DAOUsuarios.
 */
public class FachadaDAOUsuarios implements IFachadaDAOUsuarios {

	private IDAOUsuarios dao;

	public FachadaDAOUsuarios() {
		dao = new DAOUsuarios();
	}

	@Override
	public List<Usuario> busquedaUsuarios(TransferUsuario tUsu) {
		return dao.busquedaUsuarios(null);
	}

	@Override
	public boolean cerrarSesion() {
		return dao.cerrarSesion();
	}

	@Override
	public Usuario consultaUsuario(String id) {
		return dao.consultaUsuario(id);
	}

	@Override
	public boolean darDeBajaUsuario(String id) {
		return dao.darDeBajaUsuario(id);
	}

	@Override
	public boolean editarUsuario(TransferUsuario usu) {
		return dao.editarUsuario(usu);
	}

	@Override
	public boolean login(String id, String contrasenya) {
		return dao.login(id, contrasenya);
	}

	@Override
	public boolean registroUsuario(Usuario usu) {
		return dao.registroUsuario(usu);
	}

}
