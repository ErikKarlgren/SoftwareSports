package swsports.modelo;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

/**
 * Clase que representa un carrito.
 */
public class Carrito {

	/**
	 * Mapa cuyo par clave-valor representa un producto y el número de unidades que ha añadido el usuario respectivamente.
	 * Es una forma de evitar añadir el mismo producto varias veces.
	 */
	private Map<Producto, Integer> mapaProductos;
	private Integer numProductos;
	private Double precioTotal;

	/**
	 * Crea un carrito nuevo. Para ello inicializa el mapa y pone a 0 el número de productos y el precio.
	 */
	public Carrito() {
		this.mapaProductos = new HashMap<Producto, Integer>();
		this.numProductos = 0;
		this.precioTotal = 0.0;
	}

	/**
	 * Método para saber si hay algún producto en el carrito.
	 * 
	 * @return 	<code>true</code> si no hay ningún producto.
	 * 			<code>false</code> en caso contrario.
	 */
	public boolean carritoVacio() {
		return this.mapaProductos.size() == 0;
	}

	/**
	 * Método que devuelve el precio total de la compra.
	 */
	public Double getPrecioTotal() {
		return this.precioTotal;
	}

	/**
	 * Método que devuelve el número total de productos.
	 */
	public Integer getNumProductos() {
		return this.numProductos;
	}

	/**
	 * Método que vacía un carrito. Además si el usuario ha finalizado la compra, es decir, ha encargado los productos,
	 * estos se restan de la base de datos.
	 * @param compraFinalizada	<code>true</code> si ha finalizado la compra.
	 * 							<code>false</code> en caso contrario.
	 */
	public void vaciarCarrito(boolean compraFinalizada) {
		
		if(!compraFinalizada) {
			for(Producto p : mapaProductos.keySet()) {
				p.setStock(p.getStock() + mapaProductos.get(p));
			}
		}
	
		this.mapaProductos.clear();
		this.numProductos = 0;
		this.precioTotal = 0.0;
	}

	/**
	 * Método que devuelve el número de unidades de un producto que ha adquirido el usuario.
	 * 
	 * @param p {@link Producto} Producto del que queremos saber cuantas unidades ha adquirido el usuario.
	 * @return int con el número de unidades, 0 si no se encuentra en el mapa.
	 */
	public int getNumUnidadesProducto(Producto p) {
		if (this.mapaProductos.containsKey(p)) {
			return this.mapaProductos.get(p);
		} else {
			return 0;
		}
	}
	
	/**
	 * Devuelve una copia del mapa de productos que nunca se podrá modificar. Esto se utiliza para editar la base de datos cuando el usuario finaliza una compra.
	 */
	public Map<Producto, Integer> getMapaProductos(){
		return Collections.unmodifiableMap(this.mapaProductos);
	}

	/**
	 * Añade un producto p al carrito. Si el producto ya está, simplemente se actualiza el número de unidades.
	 */
	public void anyadirProducto(Producto p) {
		if (this.mapaProductos.containsKey(p)) {
			this.mapaProductos.put(p, this.mapaProductos.get(p) + 1);
		}

		else {
			this.mapaProductos.put(p, 1);
		}

		this.numProductos++;
		this.precioTotal += p.getPrecio();
	}

	/**
	 * Elimina un producto p al carrito. Si el número de unidades es 1, lo elimina del mapa, si no, le resta una unidad. También devuelve true si el producto
	 * se ha eliminado, es decir, existe en el mapa.
	 */
	public boolean eliminarProducto(Producto p) {

		boolean eliminado = false;

		if (this.mapaProductos.containsKey(p)) {
			if (this.mapaProductos.get(p) == 1) {
				this.mapaProductos.remove(p);
			}

			else {
				this.mapaProductos.put(p, this.mapaProductos.get(p) - 1);
			}

			eliminado = true;
			this.numProductos--;
			this.precioTotal -= p.getPrecio();

		}

		return eliminado;
	}
}
