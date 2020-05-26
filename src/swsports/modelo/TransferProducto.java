package swsports.modelo;

/**
 * Clase para transferir los datos sin más de un producto.
 */
public class TransferProducto {
	private final String id;
	private String nombre;
	private String desc;
	private Integer stock;
	private Double precio;

	/**
	 * Crea un {@link TransferProducto} a partir de los argumentos dados.
	 * 
	 * @param id          Identificador del producto.
	 * @param nombre      Nombre del producto.
	 * @param desc        Descripción del producto..
	 * @param stock       Número de Productos disponibles.
	 * @param precio      Precio del producto
	 */
	public TransferProducto(String id, String nombre, String desc, int stock, double precio) {
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
		this.stock = prod.getStock();
		this.precio = prod.getPrecio();
	}

	/**
	 * @return El identificador del producto
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return El nombre del producto
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @return La descripción del producto
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * @return El stock del que se dispone
	 */
	public Integer getStock() {
		return this.stock;
	}

	/**
	 * @return El precio del producto
	 */
	public Double getPrecio() {
		return this.precio;
	}
}
