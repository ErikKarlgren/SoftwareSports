package swsports.modelo;

import org.json.JSONObject;

/**
 * Clase para transferir los datos sin más de un producto.
 */
public class TransferProducto {
	private final String id;
	private String nombre;
	private String desc;
	private Integer stock;
	private double precio;

	/**
	 * Crea un {@link TransferProducto} equivalente a un {@link Producto}.
	 * 
	 * @param id              Identificador del producto.
	 * @param nombre          Nombre del producto.
	 * @param desc            Descripción del producto.
	 * @param stock           Número de Productos disponibles.
	 * @param precio          Precio del producto.
	 * .
	 */
	public TransferProducto(String id, String nombre, String desc, Integer stock, double precio) {
		this(id, nombre, desc, stock, precio);
	}

	/**
	 * Crea un {@link TransferProducto} a partir de los argumentos dados.
	 * 
	 * @param id          Identificador del producto.
	 * @param nombre      Nombre del producto.
	 * @param desc        Descripción del producto..
	 * @param stock       Número de Productos disponibles.
	 * @param precio      Precio del producto
	 */
	public TransferProducto(String id, String nombre, String desc, Integer stock, double precio) {
		this.id = id;
		this.nombre = nombre;
		this.desc = desc;
		this.stock = stock;
		this.precio = precio;
	}

	/**
	 * Crea un {@link TransferProducto} a partir de los datos de un {@link Producto}.
	 * 
	 * @param prod Producto del que se extraen los datos.
	 */
	public TransferProducto(Producto prod) {
		this.id = prod.getId();
		this.nombre = prod.getNombre();
		this.desc = prod.getDesc();
		this.stock = usu.getStock();
		this.precio = usu.getPrecio();
	}

	/**
	 * @return El identificador del producto
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return El nombre del producto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return La descripción del producto
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return El stock del que se dispone
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @return El precio del producto
	 */
	public double getPrecio() {
		return telefono;
	}
}
