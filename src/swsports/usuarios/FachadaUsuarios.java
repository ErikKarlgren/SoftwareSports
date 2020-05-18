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

	@Override
	public List<Usuario> busquedaUsuarios(TransferUsuario tUsu) {
		return sa.busquedaUsuarios(null);
	}

	@Override
	public boolean cerrarSesion() {
		return sa.cerrarSesion();
	}

	@Override
	public Usuario consultaUsuario(String id) {
		return sa.consultaUsuario(id);
	}

	@Override
	public boolean darDeBajaUsuario(String id) {
		return sa.darDeBajaUsuario(id);
	}

	@Override
	public boolean editarUsuario(TransferUsuario usu) {
		return sa.editarUsuario(usu);
	}

	@Override
	public boolean login(String id, String contrasenya) {
		return sa.login(id, contrasenya);
	}

	@Override
	public boolean registroUsuario(Usuario usu) {
		return sa.registroUsuario(usu);
	}

}
