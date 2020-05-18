package swsports.daousuarios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import swsports.basedatos.BaseDatosUsuarioJSON;
import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;

class IDAOUsuariosTest {

	private static IDAOUsuarios dao;

	@BeforeAll
	static void setUp() {
		dao = new DAOUsuarios();
		BaseDatosUsuarioJSON.getInstance().limpiar();
		dao.registroUsuario(new Usuario("z1", "Zero", "lelouch@d.com", "geass2141", 9224, "japon"));
		dao.registroUsuario(new Usuario("z2", "Squiw", "squiwh@d.com", "14sq997", 94459, "japon"));
	}

	@Test
	@Order(1)
	void testBusqueda() {
		TransferUsuario tUsu = new TransferUsuario(null, null, null, null, 9, null);
		assertTrue(dao.busquedaUsuarios(tUsu).size() == 2);
		tUsu = new TransferUsuario(null, null, null, null, 92, null);
		assertTrue(dao.busquedaUsuarios(tUsu).size() == 1);
		tUsu = new TransferUsuario(null, null, null, null, null, null, true);
		assertTrue(dao.busquedaUsuarios(tUsu).isEmpty());
		tUsu = new TransferUsuario("z1", null, null, null, null, null);
		assertTrue(dao.busquedaUsuarios(tUsu).size() == 1);
		tUsu = new TransferUsuario(null, null, ".com", null, null, null);
		assertTrue(dao.busquedaUsuarios(tUsu).size() == 2);
	}

	@Test
	@Order(2)
	void testConsulta() {
		assertTrue(dao.consultaUsuario("z1") != null);
		assertTrue(dao.consultaUsuario("z2") != null);
		assertFalse(dao.consultaUsuario("z3") != null);
	}

	@Test
	@Order(3)
	void testDarDeBaja() {
		assertTrue(dao.darDeBajaUsuario("z1"));
		assertFalse(dao.darDeBajaUsuario("z1"));

	}

	@Test
	@Order(4)
	void testEditar() {
		TransferUsuario tUsu = new TransferUsuario("z2", "hola", "hola", "hola", 92, "hola");
		boolean b = dao.editarUsuario(tUsu);
		assertTrue(b);
		if (b)
			System.out.println("Éxito al editar el usuario");
		tUsu = new TransferUsuario("z3", "hola", "hola", "hola", 92, "hola");
		assertFalse(dao.editarUsuario(tUsu));
	}

	@Test
	@Order(5)
	void testLogin() {
		assertTrue(dao.login("z1", "geass2141"));
		assertFalse(dao.login("z1", "gwgwgw"));
		assertFalse(dao.login("z9", "geass2141"));
	}

	@Test
	@Order(6)
	void testRegistro() {
		Usuario usu = new Usuario("z9", "Zero", "lelouch@d.com", "geass2141", 9224, "japon");
		assertTrue(dao.registroUsuario(usu));
	}

}
