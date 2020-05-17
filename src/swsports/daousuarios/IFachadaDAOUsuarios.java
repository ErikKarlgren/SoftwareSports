package swsports.daousuarios;

import java.util.List;

import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;

public interface IFachadaDAOUsuarios {
	/**
	 * Devuelve una lista de los usuarios que se adhieren a los par�metros de
	 * b�squeda proporcionados. Los par�metros que sean nulos no se usar�n para la
	 * b�squeda.
	 * 
	 * @param tUsu TODO
	 */
	public List<Usuario> busquedaUsuarios(TransferUsuario tUsu);

	/**
	 * Cierra la sesi�n actual.
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
	 * Edita los datos de un usuario. Su identificador (id) se usar� para buscar el
	 * usuario que se quiere editar y se sobrescribir�n el resto de datos.
	 */
	public boolean editarUsuario(TransferUsuario usu);

	/**
	 * Inicia la sesi�n de un usuario dado su identificador (id) y su contrase�a.
	 */
	public boolean login(String id, String contrasenya);

	/**
	 * Da de alta un usuario y lo registra en la apliaci�n.
	 */
	public boolean registroUsuario(Usuario usu);
}
