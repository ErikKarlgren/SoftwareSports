package swsports.basedatos;

import org.json.JSONObject;

import swsports.modelo.Proveedor;

public class BaseDatosProveedorJSON extends BaseDatosFicheroJSON<Proveedor>{

	static BaseDatosProveedorJSON bdProducto;
	
	protected BaseDatosProveedorJSON() {
		super("proveedores.json", "proveedores");
	}
	
	/**
	 * Devuelve una instancia única en el programa de {@link BaseDatosProductoJSON}.
	 * 
	 * @return Instancia de {@link BaseDatosProductoJSON}.
	 */
	public static BaseDatos<Proveedor> getInstance() {
		if (bdProducto == null)
			bdProducto = new BaseDatosProveedorJSON();
		return bdProducto;
	}

	/**
	 * Comprueba que el formato de <code>obj</code> contenga las siguientes claves y
	 * sus valores correspondientes tengan la clase correcta:
	 * <ul>
	 * <li>"id": {@link Integer}</li>
	 * <li>"nombre": {@link String}</li>
	 * <li>"desc": {@link String}</li>
	 * <li>"stock": {@link Integer}</li>
	 * <li>"precio": {@link Double}</li>
	 * </ul>
	 */
	@Override
	protected boolean comprobarFormatoDatos(JSONObject obj) {
		boolean correcto = true;

		correcto &= comprobarClaveYClase(obj, "id", Integer.class);
		correcto &= comprobarClaveYClase(obj, "nombre", String.class);
		correcto &= comprobarClaveYClase(obj, "descipcion", String.class);
		correcto &= comprobarClaveYClase(obj, "stock", Integer.class);
		correcto &= comprobarClaveYClase(obj, "precio", Double.class);

		return correcto;
	}
	
	@Override
	protected void leerDato(JSONObject obj) {
		anyadir(new Proveedor(obj));
	}
}
© 2020 GitHub, Inc.