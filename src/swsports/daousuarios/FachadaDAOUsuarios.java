package swsports.daousuarios;

import java.util.List;

import swsports.modelo.Usuario;

public class FachadaDAOUsuarios implements IFachadaDAOUsuarios {

	@Override
	public boolean registroUsuario(Usuario usu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login(String id, String contrasenya) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Usuario> busquedaUsuarios(String nombre, String mail, String id, int telefono, String direccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario consultaUsuario(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean darDeBajaUsuario(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editarUsuario(Usuario usu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hacerAdmin(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean quitarAdmin(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cerrarSesion() {
		// TODO Auto-generated method stub
		return false;
	}

}
