package swsports.basedatos;

import org.json.JSONObject;

import swsports.modelo.Usuario;

/**
 * Base de datos que usa ficheros en formato JSON para guardar los datos de los
 * usuarios. Tiene una instancia única en el programa a la que se puede acceder
 * con el método {@link #getInstance()}.
 */
public class BaseDatosUsuarioJSON extends BaseDatosFicheroJSON<Usuario> {

	static BaseDatosUsuarioJSON bdUsuario;

	private BaseDatosUsuarioJSON() {
		super("usuarios.json", "usuarios");
	}

	/**
	 * Devuelve una instancia única en el programa de {@link BaseDatosUsuarioJSON}.
	 * 
	 * @return Instancia de {@link BaseDatosUsuarioJSON}.
	 */
	public static BaseDatosUsuarioJSON getInstance() {
		if (bdUsuario == null)
			bdUsuario = new BaseDatosUsuarioJSON();
		return bdUsuario;
	}

	/**
	 * Comprueba que el formato de <code>obj</code> contenga las siguientes claves y
	 * sus valores correspondientes tengan la clase correcta:
	 * <ul>
	 * <li>"id": {@link String}</li>
	 * <li>"nombre": {@link String}</li>
	 * <li>"mail": {@link String}</li>
	 * <li>"contrasenya": {@link String}</li>
	 * <li>"direccion": {@link String}</li>
	 * <li>"telefono": {@link Integer}</li>
	 * <li>"admin": {@link Boolean}</li>
	 * </ul>
	 */
	@Override
	protected final boolean comprobarFormatoDatos(JSONObject obj) {
		boolean correcto = true;

		correcto &= comprobarClaveYClase(obj, "id", String.class);
		correcto &= comprobarClaveYClase(obj, "nombre", String.class);
		correcto &= comprobarClaveYClase(obj, "mail", String.class);
		correcto &= comprobarClaveYClase(obj, "contrasenya", String.class);
		correcto &= comprobarClaveYClase(obj, "direccion", String.class);
		correcto &= comprobarClaveYClase(obj, "telefono", Integer.class);
		correcto &= comprobarClaveYClase(obj, "admin", Boolean.class);

		return correcto;
	}

	@Override
	protected void leerDato(JSONObject obj) {
		anyadir(new Usuario(obj));
	}
}
