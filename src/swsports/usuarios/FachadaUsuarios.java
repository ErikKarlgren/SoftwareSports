package swsports.usuarios;

import java.util.List;

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
	public List<Usuario> busquedaUsuarios(String nombre, String mail, String id, int telefono, String direccion) {
		return sa.busquedaUsuarios(nombre, mail, id, telefono, direccion);
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
	public boolean editarUsuario(Usuario usu) {
		return sa.editarUsuario(usu);
	}

	/**
	 * Concede privilegios de administrador a un usuario dado su identificador (id).
	 * Falla si el usuario no existe o ya tenía privilegios de administrador.
	 */
	@Override
	public boolean hacerAdmin(String id) {
		return sa.hacerAdmin(id);
	}

	/**
	 * Inicia la sesión de un usuario dado su identificador (id) y su contraseña.
	 */
	@Override
	public boolean login(String id, String contrasenya) {
		return sa.login(id, contrasenya);
	}

	/**
	 * Revoca los privilegios de administrador de un usuario dado su identificador
	 * (id). Falla si el usuario no existe o ya carecía de dichos privilegios.
	 */
	@Override
	public boolean quitarAdmin(String id) {
		return sa.quitarAdmin(id);
	}

	/**
	 * Da de alta un usuario y lo registra en la apliación.
	 */
	@Override
	public boolean registroUsuario(Usuario usu) {
		return sa.registroUsuario(usu);
	}

}
