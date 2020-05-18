package swsports.modelo;

import org.json.JSONObject;

/**
 * Interfaz para los objetos que tienen un identificador y una representación en
 * formato JSON que se usará en la base de datos.
 */
public interface Reportable {
	/**
	 * Devuelve el identificador del objeto
	 * 
	 * @return Identificador del objeto
	 */
	public String getId();

	/**
	 * Devuelve una representación del objeto en formato JSON. Este formato es el
	 * que se debe usar en la base de datos.
	 * 
	 * @return {@link JSONObject} con la representación del objeto.
	 */
	public JSONObject report();
}
