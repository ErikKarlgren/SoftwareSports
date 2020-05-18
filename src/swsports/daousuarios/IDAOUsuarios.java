package swsports.daousuarios;

import java.util.List;

import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;

/**
 * Interfaz para el acceso a los datos del módulo Usuarios.
 */
interface IDAOUsuarios {
	/**
	 * Devuelve una lista de los usuarios que se adhieren a los parámetros de
	 * búsqueda proporcionados. Los parámetros que sean nulos en el objeto
	 * {@link TransferUsuario} no se usarán para la búsqueda.
	 * 
	 * @param tUsu Objeto {@link TransferUsuario} usado como parámetro de busca
	 * @return Lista con los usuarios que buscamos
	 */
	public List<Usuario> busquedaUsuarios(TransferUsuario tUsu);

	/**
	 * Cierra la sesión actual.
	 * 
	 * @return <code>true</code> si se ha podido cerrar la sesión correctamente.
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
	 * Edita los datos de un usuario. Su identificador (id) se usará para buscar el
	 * usuario que se quiere editar y se sobrescribirán el resto de datos.
	 * 
	 * @param usu Objeto con los nuevos atributos del usuario.
	 * @return <code>true</code> si se ha podido editar el usuario.
	 */
	public boolean editarUsuario(TransferUsuario usu);

	/**
	 * Inicia la sesión de un usuario dado su identificador (id) y su contraseña.
	 * 
	 * @param id          Identificador del usuario
	 * @param contrasenya Contraseña del usuario
	 * @return <code>true</code> si se ha podido iniciar sesión.
	 */
	public boolean login(String id, String contrasenya);

	/**
	 * Da de alta un usuario y lo registra en la apliación.
	 * 
	 * @param usu Usuario a registrar
	 * @return <code>true</code> si se ha podido registrar al usuario.
	 *         <code>false</code> en caso contrario.
	 */
	public boolean registroUsuario(Usuario usu);
}
