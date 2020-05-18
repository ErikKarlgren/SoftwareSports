package swsports.basedatos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import swsports.modelo.Usuario;

class BaseDatosUsuarioJSONTest {

	static BaseDatosUsuarioJSON bd;

	@BeforeAll
	static void setup() {
		bd = BaseDatosUsuarioJSON.getInstance();
		bd.limpiar();
		bd.anyadir(new Usuario("u1", "paco", "paco@gmail.com", "pacopaco", 94962449, "calle mayor"));
		bd.anyadir(new Usuario("u2", "pepe", "pepe@gmail.com", "pepe12345", 979879495, "calle mayor"));
	}

	@Test
	void testBaseDatosUsuario() {
		assertDoesNotThrow(BaseDatosUsuarioJSON::getInstance);
	}

	@Test
	void testBusqueda() {
		try {
			bd.eliminar("u3");
		} catch (Exception e) {
			// nada
		}
		assertTrue(bd.busqueda(o -> o.getId().equals("u3")).isEmpty());
		assertTrue(bd.busqueda(o -> o.getNombre().contains("p")).size() == 2);
	}

	@Test
	void testComprobarFormatoDatos() {
		JSONObject obj = new JSONObject();
		obj.put("id", "jbwoq");
		obj.put("nombre", "hola");
		obj.put("mail", "fe@eg.com");
		obj.put("contrasenya", "password1");
		obj.put("direccion", "calle de alli");
		obj.put("telefono", 191264);
		obj.put("admin", true);
		assertTrue(bd.comprobarFormatoDatos(obj));

		obj.put("id", 61591);
		assertFalse(bd.comprobarFormatoDatos(obj));

		obj.put("id", "bobo");
		obj.remove("admin");
		assertFalse(bd.comprobarFormatoDatos(obj));
	}

	@Test
	void testConsulta() {
		assertFalse(bd.consulta(null) != null);
		assertTrue(bd.consulta("u1") != null);
	}

	@Test
	void testEditar() {
		Usuario usu = new Usuario("u1", "paco", "paco@gmail.com", "pacopaco", new Random().nextInt(), "calle mayor");
		try {
			bd.editar(usu);
			assertTrue(true);
		} catch (Exception e) {
			fail("Se ha lanzado una excepción");
		}
	}

}
