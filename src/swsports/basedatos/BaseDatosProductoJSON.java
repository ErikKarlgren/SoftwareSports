package swsports.basedatos;

import org.json.JSONObject;

import swsports.modelo.Producto;

public class BaseDatosProductoJSON extends BaseDatosFicheroJSON<Producto>{

	static BaseDatosProductoJSON bdProducto;
	
	protected BaseDatosProductoJSON() {
		super("productos.json", "productos");
	}
	
	/**
	 * Devuelve una instancia única en el programa de {@link BaseDatosProductoJSON}.
	 * 
	 * @return Instancia de {@link BaseDatosProductoJSON}.
	 */
	public static BaseDatos<Producto> getInstance() {
		if (bdProducto == null)
			bdProducto = new BaseDatosProductoJSON();
		return bdProducto;
	}

	/**
	 * Comprueba que el formato de <code>obj</code> contenga las siguientes claves y
	 * sus valores correspondientes tengan la clase correcta:
	 * <ul>
	 * <li>"id": {@link String}</li>
	 * <li>"nombre": {@link String}</li>
	 * <li>"desc": {@link String}</li>
	 * <li>"stock": {@link Integer}</li>
	 * <li>"precio": {@link Double}</li>
	 * </ul>
	 */
	@Override
	protected boolean comprobarFormatoDatos(JSONObject obj) {
		boolean correcto = true;

		correcto &= comprobarClaveYClase(obj, "id", String.class);
		correcto &= comprobarClaveYClase(obj, "nombre", String.class);
		correcto &= comprobarClaveYClase(obj, "desc", String.class);
		correcto &= comprobarClaveYClase(obj, "stock", Integer.class);
		correcto &= comprobarClaveYClase(obj, "precio", Double.class);

		return correcto;
	}
	
	@Override
	protected void leerDato(JSONObject obj) {
		anyadir(new Producto(obj));
	}
	
}
