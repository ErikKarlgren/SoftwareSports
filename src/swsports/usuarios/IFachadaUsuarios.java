package swsports.usuarios;

import java.util.List;

import swsports.modelo.Usuario;

public interface IFachadaUsuarios {
	/**
	 * Devuelve una lista de los usuarios que se adhieren a los parámetros de
	 * búsqueda proporcionados. Los parámetros que sean nulos no se usarán para la
	 * búsqueda.
	 */
	public List<Usuario> busquedaUsuarios(String nombre, String mail, String id, int telefono, String direccion);

	/**
	 * Cierra la sesión actual.
	 */
	public boolean cerrarSesion();

	/**
	 * Devuelve los datos de un usuario dado su identificador (id).
	 */
	public Usuario consultaUsuario(String id);

	/**
	 * Da de baja a un usuario dado su identificador (id).
	 */
	public boolean darDeBajaUsuario(String id);

	/**
	 * Edita los datos de un usuario. Su identificador (id) se usará para buscar el
	 * usuario que se quiere editar y se sobrescribirán el resto de datos.
	 */
	public boolean editarUsuario(Usuario usu);

	/**
	 * Concede privilegios de administrador a un usuario dado su identificador (id).
	 * Falla si el usuario no existe o ya tenía privilegios de administrador.
	 */
	public boolean hacerAdmin(String id);

	/**
	 * Inicia la sesión de un usuario dado su identificador (id) y su contraseña.
	 */
	public boolean login(String id, String contrasenya);

	/**
	 * Revoca los privilegios de administrador de un usuario dado su identificador
	 * (id). Falla si el usuario no existe o ya carecía de dichos privilegios.
	 */
	public boolean quitarAdmin(String id);

	/**
	 * Da de alta un usuario y lo registra en la apliación.
	 */
	public boolean registroUsuario(Usuario usu);
}
