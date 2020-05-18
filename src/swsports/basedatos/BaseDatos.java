package swsports.basedatos;

import java.util.List;
import java.util.function.Predicate;

/**
 * Interfaz con las operaciones que deben implementar las bases de datos.
 * 
 * @param <T> Tipo de objeto que vamos a guardar en la base de datos.
 */
public interface BaseDatos<T> {
	/**
	 * Devuelve una lista de con todos los objetos que cumplan el predicado (es
	 * decir, los objetos para los que {@link Predicate#test(Object)} devuelve
	 * <code>true</code>).
	 * 
	 * @param predicado El objeto que usamos para filtrar los objetos que queremos.
	 * @return Lista con los elementos buscados
	 */
	public List<T> busqueda(Predicate<T> predicado);

	/**
	 * Busca un objeto del modelo por su identificador. Si no se encuentra se
	 * devuelve un objeto nulo.
	 * 
	 * @param id El identificador del objeto que queremos buscar
	 * @return El objeto buscado si se encuentra. Si no, devuelve <code>null</code>.
	 */
	public T consulta(String id);

	/**
	 * Añade un objeto a la base de datos. Si ya había un elemento con el mismo
	 * identificador, lanza una excepción.
	 * 
	 * @param obj El objeto que queremos añadir
	 * @throws IllegalArgumentException - Si ya existía un elemento con el mismo
	 *                                  identificador
	 */
	public void anyadir(T obj);

	/**
	 * Busca un objeto con el mismo identificador y lo reemplaza con los nuevos
	 * datos.
	 * 
	 * @param nuevo El objeto que va a reemplazar al antiguo.
	 * @throws IllegalArgumentException Si no existe un elemento con el mismo
	 *                                  identificador
	 */
	public void editar(T nuevo);

	/**
	 * Elimina un objeto buscando por su identificador. Si no existía, lanza una
	 * excepción.
	 * 
	 * @param id El identificador del objeto que queremos eliminar.
	 * @throws IllegalArgumentException Si no existe un elemento con el mismo
	 *                                  identificador
	 */
	public void eliminar(String id);

	/**
	 * Borra todos los datos de la base de datos de manera irreversible. No se
	 * debería usar a la ligera.
	 */
	public void limpiar();
}
