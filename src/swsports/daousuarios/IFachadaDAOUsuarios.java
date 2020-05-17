package swsports.daousuarios;

import java.util.List;

import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;

public interface IFachadaDAOUsuarios {
	/**
	 * Devuelve una lista de los usuarios que se adhieren a los parámetros de
	 * búsqueda proporcionados. Los parámetros que sean nulos no se usarán para la
	 * búsqueda.
	 * 
	 * @param tUsu TODO
	 */
	public List<Usuario> busquedaUsuarios(TransferUsuario tUsu);

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
	public boolean editarUsuario(TransferUsuario usu);

	/**
	 * Inicia la sesión de un usuario dado su identificador (id) y su contraseña.
	 */
	public boolean login(String id, String contrasenya);

	/**
	 * Da de alta un usuario y lo registra en la apliación.
	 */
	public boolean registroUsuario(Usuario usu);
}
