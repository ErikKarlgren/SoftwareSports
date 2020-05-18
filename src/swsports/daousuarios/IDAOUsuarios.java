package swsports.daousuarios;

import java.util.List;

import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;

/**
 * Interfaz para el acceso a los datos del m�dulo Usuarios.
 */
interface IDAOUsuarios {
	/**
	 * Devuelve una lista de los usuarios que se adhieren a los par�metros de
	 * b�squeda proporcionados. Los par�metros que sean nulos en el objeto
	 * {@link TransferUsuario} no se usar�n para la b�squeda.
	 * 
	 * @param tUsu Objeto {@link TransferUsuario} usado como par�metro de busca
	 * @return Lista con los usuarios que buscamos
	 */
	public List<Usuario> busquedaUsuarios(TransferUsuario tUsu);

	/**
	 * Cierra la sesi�n actual.
	 * 
	 * @return <code>true</code> si se ha podido cerrar la sesi�n correctamente.
	 */
	public boolean cerrarSesion();

	/**
	 * Devuelve los datos de un usuario dado su identificador (id).
	 * 
	 * @param id Identificador del usuario.
	 * @return Usuario con el identificador buscado (puede ser nulo).
	 */
	public Usuario consultaUsuario(String id);

	/**
	 * Da de baja a un usuario dado su identificador (id).
	 * 
	 * @param id Identificador del usuario
	 * @return <code>true</code> si se ha podido dar de baja al usuario.
	 *         <code>false</code> en caso contrario.
	 */
	public boolean darDeBajaUsuario(String id);

	/**
	 * Edita los datos de un usuario. Su identificador (id) se usar� para buscar el
	 * usuario que se quiere editar y se sobrescribir�n el resto de datos.
	 * 
	 * @param usu Objeto con los nuevos atributos del usuario.
	 * @return <code>true</code> si se ha podido editar el usuario.
	 */
	public boolean editarUsuario(TransferUsuario usu);

	/**
	 * Inicia la sesi�n de un usuario dado su identificador (id) y su contrase�a.
	 * 
	 * @param id          Identificador del usuario
	 * @param contrasenya Contrase�a del usuario
	 * @return <code>true</code> si se ha podido iniciar sesi�n.
	 */
	public boolean login(String id, String contrasenya);

	/**
	 * Da de alta un usuario y lo registra en la apliaci�n.
	 * 
	 * @param usu Usuario a registrar
	 * @return <code>true</code> si se ha podido registrar al usuario.
	 *         <code>false</code> en caso contrario.
	 */
	public boolean registroUsuario(Usuario usu);
}
