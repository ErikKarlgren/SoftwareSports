package swsports.daousuarios;

import java.util.List;

import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;

class DAOUsuarios implements IDAOUsuarios {

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
	public List<Usuario> busquedaUsuarios(TransferUsuario tUsu) {
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
	public boolean editarUsuario(TransferUsuario usu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cerrarSesion() {
		// TODO Auto-generated method stub
		return false;
	}

}
