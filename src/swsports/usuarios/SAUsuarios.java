package swsports.usuarios;

import java.util.List;

import swsports.daousuarios.FachadaDAOUsuarios;
import swsports.daousuarios.IFachadaDAOUsuarios;
import swsports.modelo.Usuario;

class SAUsuarios implements ISAUsuarios {

	private IFachadaDAOUsuarios dao;

	public SAUsuarios() {
		dao = new FachadaDAOUsuarios();
	}

	/**
	 * Devuelve una lista de los usuarios que se adhieren a los par�metros de
	 * b�squeda proporcionados. Los par�metros que sean nulos no se usar�n para la
	 * b�squeda.
	 */
	@Override
	public List<Usuario> busquedaUsuarios(String nombre, String mail, String id, int telefono, String direccion) {
		return dao.busquedaUsuarios(nombre, mail, id, telefono, direccion);
	}

	/**
	 * Cierra la sesi�n actual.
	 */
	@Override
	public boolean cerrarSesion() {
		return dao.cerrarSesion();
	}

	/**
	 * Devuelve los datos de un usuario dado su identificador (id).
	 */
	@Override
	public Usuario consultaUsuario(String id) {
		return dao.consultaUsuario(id);
	}

	/**
	 * Da de baja a un usuario dado su identificador (id).
	 */
	@Override
	public boolean darDeBajaUsuario(String id) {
		return (dao.consultaUsuario(id) == null) && dao.darDeBajaUsuario(id);
	}

	/**
	 * Edita los datos de un usuario. Su identificador (id) se usar� para buscar el
	 * usuario que se quiere editar y se sobrescribir�n el resto de datos.
	 */
	@Override
	public boolean editarUsuario(Usuario usu) {
		return dao.editarUsuario(usu);
	}

	/**
	 * Concede privilegios de administrador a un usuario dado su identificador (id).
	 * Falla si el usuario no existe o ya ten�a privilegios de administrador.
	 */
	@Override
	public boolean hacerAdmin(String id) {
		Usuario u = dao.consultaUsuario(id);
		return (u != null) && !u.esAdmin() && dao.hacerAdmin(id);
	}

	/**
	 * Inicia la sesi�n de un usuario dado su identificador (id) y su contrase�a.
	 */
	@Override
	public boolean login(String id, String contrasenya) {
		return dao.login(id, contrasenya);
	}

	/**
	 * Revoca los privilegios de administrador de un usuario dado su identificador
	 * (id). Falla si el usuario no existe o ya carec�a de dichos privilegios.
	 */
	@Override
	public boolean quitarAdmin(String id) {
		Usuario u = dao.consultaUsuario(id);
		return (u != null) && u.esAdmin() && dao.quitarAdmin(id);
	}

	/**
	 * Da de alta un usuario y lo registra en la apliaci�n.
	 */
	@Override
	public boolean registroUsuario(Usuario usu) {
		return (dao.consultaUsuario(usu.getId()) == null) && dao.registroUsuario(usu);
	}

}
