package swsports.basedatos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import swsports.modelo.Reportable;
import swsports.modelo.Usuario;

/**
 * Base de datos que usa ficheros en formato JSON para guardar los datos.
 * <p>
 * Las subclases deben implementar una forma de comprobar que un
 * {@link JSONObject} tiene el formato correcto para la clase que sustituya el
 * genérico. Por ejemplo, para {@link Usuario}, el {@link JSONObject} debe tener
 * un campo de dirección (etiqueta "direccion") que sea un string.
 * 
 * @param <T> Debe implementar {@link Reportable} y, por tanto, sobrescribir los
 *            métodos "getId()" y "report()".
 */
abstract class BaseDatosFicheroJSON<T extends Reportable> implements BaseDatos<T> {

	/**
	 * Como todos los {@link JSONObject} tienen al menos una etiqueta, especificamos
	 * cuál esperamos en nuestra base de datos con este atributo.
	 */
	private String etiqueta;
	/**
	 * Fichero de donde cargamos los datos y donde los guardamos. Se actualiza cada
	 * vez que se realiza una operación que modifique la base de datos.
	 */
	private File fichero;
	/**
	 * Lista con los objetos de la base de datos. Se usa para recorridos o búsquedas
	 * no directas (es decir, que no se busca un elemento por su identificador).
	 */
	private List<T> list;
	/**
	 * Mapa que guarda los objetos de la base de datos usando el identificador de
	 * cada uno de ellos como su clave respectiva.
	 */
	private Map<String, T> map;

	/**
	 * Construye un {@link BaseDatosFicheroJSON} a partir de la dirección del
	 * fichero que contiene los datos y la clave/etiqueta del {@link JSONObject} que
	 * representa el fichero.
	 * <p>
	 * Si el fichero proporcionado no existe o la etiqueta es incorrecta, crea un
	 * nuevo fichero que tendrá la representación de un {@link JSONObject} con la
	 * etiqueta sirviendo de clave y su valor siendo un {@link JSONArray} vacío.
	 * 
	 * @param path     Dirección del fichero de datos.
	 * @param etiqueta Etiqueta/clave del {@link JSONObject} representado en el
	 *                 fichero.
	 */
	protected BaseDatosFicheroJSON(String path, String etiqueta) {
		this.fichero = new File(path);
		this.etiqueta = etiqueta;
		this.list = new LinkedList<>();
		this.map = new HashMap<>();

		try (FileInputStream in = new FileInputStream(fichero)) {
			JSONObject datos = new JSONObject(new JSONTokener(in));
			leerDatos(datos);
		} catch (IOException | JSONException e) {
			crearFichero(etiqueta);
		}
	}

	/**
	 * Dado un objeto {@link JSONObject}, comprueba si tiene la clave
	 * <code>key</code> y si su valor es de la clase dada.
	 * 
	 * @param obj   Objeto a testear
	 * @param key   Clave que queremos comprobar si está o no en <code>obj</code>.
	 * @param clase Clase que queremos comprobar si es la misma que la del valor
	 *              guardado en la clave <code>key</code>.
	 * @return <code>true</code> si el formato es correcto. Devuelve
	 *         <code>false</code> en caso contrario.
	 */
	protected static boolean comprobarClaveYClase(JSONObject obj, String key, Class<?> clase) {
		return obj.has(key) && clase.isInstance(obj.get(key));
	}

	/**
	 * @throws IllegalArgumentException Si ya existía un elemento con el mismo
	 *                                  identificador en la base de datos.
	 */
	@Override
	public final void anyadir(T obj) {
		if (consulta(obj.getId()) != null) {
			throw new IllegalArgumentException("Ya existe este elemento");
		} else {
			list.add(obj);
			map.put(obj.getId(), obj);
			guardarDatos();
		}
	}

	@Override
	public final List<T> busqueda(Predicate<T> predicado) {
		return list.stream().filter(predicado::test).collect(Collectors.toList());
	}

	/**
	 * Comprueba si el formato de los datos es el adecuado para la base de datos. La
	 * implementación será responsabilidad de cualquier subclase que herede de
	 * {@link BaseDatosFicheroJSON}.
	 * 
	 * @param obj Objeto cuyo formato queremos evaluar.
	 * @return Devuelve <code>true</code> si el formato es correcto
	 *         (<code>false</code> en caso contrario).
	 */
	protected abstract boolean comprobarFormatoDatos(JSONObject obj);

	@Override
	public final T consulta(String id) {
		return map.get(id);
	}

	private void crearFichero(String etiqueta) {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(fichero))) {
			JSONObject datos = new JSONObject();
			datos.put(etiqueta, new LinkedList<JSONObject>());
			out.write(datos.toString());
		} catch (IOException e) {
			// no debería llegar a lanzarse una excepción
			e.printStackTrace();
		}
	}

	/**
	 * @throws IllegalArgumentException Si no existe un elemento con el mismo
	 *                                  identificador en la base de datos.
	 */
	@Override
	public final void editar(T nuevo) {
		T viejo = consulta(nuevo.getId());
		if (viejo == null) {
			throw new IllegalArgumentException("No existe un elemento con el mismo identificador que el argumento");
		} else {
			// Sabemos que "viejo" y "nuevo" tienen el mismo identificador
			list.remove(viejo);
			list.add(nuevo);
			map.put(nuevo.getId(), nuevo);
			guardarDatos();
		}
	}

	/**
	 * @throws IllegalArgumentException Si no existía un elemento con el mismo
	 *                                  identificador en la base de datos.
	 */
	@Override
	public final void eliminar(String id) {
		if (consulta(id) == null) {
			throw new IllegalArgumentException("No existe un elemento con el mismo id que el argumento");
		} else {
			list.removeIf(o -> o.getId().equals(id));
			map.remove(id);
			guardarDatos();
		}
	}

	/**
	 * Guarda los datos cargados en memoria en el fichero que corresponde a la base
	 * de datos.
	 */
	private void guardarDatos() {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(fichero))) {
			JSONObject datos = new JSONObject();
			datos.put(etiqueta, list.stream().map(Reportable::report).collect(Collectors.toList()));
			out.write(datos.toString(4));
		} catch (IOException e) {
			// no debería llegar a lanzarse una excepción
			e.printStackTrace();
		}
	}

	/**
	 * Lee un {@link JSONObject}, crea su objeto correspondiente y lo añade a la
	 * base de datos llamando al método {@link #anyadir(Reportable)}.
	 * 
	 * @param obj Objeto en formato {@link JSONObject} a leer.
	 */
	protected abstract void leerDato(JSONObject obj);

	/**
	 * Asegura que el formato del parámetro es el apropiado para la base de datos.
	 * Requiere definir el método "comprobarFormatoDatos(JSONObject obj)".
	 * 
	 * @param datos Objeto con todos los datos que vamos a cargar en la memoria.
	 * @throws IllegalArgumentException Si el formato de los datos es ilegal.
	 */
	private void leerDatos(JSONObject datos) {
		JSONArray jsonArray = datos.getJSONArray(this.etiqueta);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject datoJson = jsonArray.getJSONObject(i);
			if (comprobarFormatoDatos(datoJson)) {
				leerDato(datoJson);
			} else {
				throw new IllegalArgumentException("Formato de los datos incorrecto");
			}
		}
	}
	
	@Override
	public void limpiar() {
		list = new LinkedList<>();
		map = new HashMap<>();
		guardarDatos();
	}
}
