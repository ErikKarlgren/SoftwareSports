package swsports.modelo;

import org.json.JSONObject;

/**
 * Interfaz para los objetos que tienen un identificador y una representaci�n en
 * formato JSON que se usar� en la base de datos.
 */
public interface Reportable {
	/**
	 * Devuelve el identificador del objeto
	 * 
	 * @return Identificador del objeto
	 */
	public String getId();

	/**
	 * Devuelve una representaci�n del objeto en formato JSON. Este formato es el
	 * que se debe usar en la base de datos.
	 * 
	 * @return {@link JSONObject} con la representaci�n del objeto.
	 */
	public JSONObject report();
}
