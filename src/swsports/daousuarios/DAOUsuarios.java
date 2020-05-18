package swsports.daousuarios;

import java.util.List;
import java.util.function.Predicate;

import org.json.JSONObject;

import swsports.basedatos.BaseDatos;
import swsports.basedatos.BaseDatosUsuarioJSON;
import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;

/**
 * Implementación de la lógica de acceso a datos.
 */
class DAOUsuarios implements IDAOUsuarios {

	private BaseDatos<Usuario> bd;

	public DAOUsuarios() {
		bd = BaseDatosUsuarioJSON.getInstance();
	}

	@Override
	public boolean registroUsuario(Usuario usu) {
		try {
			bd.anyadir(usu);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean login(String id, String contrasenya) {
		Usuario usu = consultaUsuario(id);
		return usu != null && usu.getContrasenya().equals(contrasenya);
	}

	@Override
	public List<Usuario> busquedaUsuarios(TransferUsuario tUsu) {
		Predicate<Usuario> pred = new Predicate<Usuario>() {
			@Override
			public boolean test(Usuario u) {
				boolean ok = true;

				ok &= (tUsu.getId() == null || u.getId().equals(tUsu.getId()));
				ok &= (tUsu.getContrasenya() == null || u.getContrasenya().equals(tUsu.getContrasenya()));
				ok &= (tUsu.getDireccion() == null || u.getDireccion().contains(tUsu.getDireccion()));
				ok &= (tUsu.esAdmin() == null || u.esAdmin() == tUsu.esAdmin());
				ok &= (tUsu.getMail() == null || u.getMail().contains(tUsu.getMail()));
				ok &= (tUsu.getNombre() == null || u.getNombre().contains(tUsu.getNombre()));
				ok &= (tUsu.getTelefono() == null
						|| String.valueOf(u.getTelefono()).contains(String.valueOf(tUsu.getTelefono())));

				return ok;
			}
		};
		return bd.busqueda(pred);
	}

	@Override
	public Usuario consultaUsuario(String id) {
		return bd.consulta(id);
	}

	@Override
	public boolean darDeBajaUsuario(String id) {
		try {
			bd.eliminar(id);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean editarUsuario(TransferUsuario usu) {
		try {
			bd.editar(new Usuario(usu));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean cerrarSesion() {
		// TODO este método igual habría que quitarlo si no guardamos en
		// la base de datos los cierres de sesión
		return false;
	}

}
