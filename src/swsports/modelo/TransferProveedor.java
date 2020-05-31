package swsports.modelo;

/**
 * Clase con datos del proveedor.
 */
public class TransferProveedor {
	private final String id;
	private String nombre;
	private String descripcion;
	private String idProd;
	private Integer stock;
	private Double precioTotal;

	/**
	 * Crea un {@link TransferProveedor} a partir de los argumentos dados.
	 * 
	 * @param id             Identificador del proveedor.
	 * @param nombre         Nombre del proveedor.
	 * @param desc           Descripcion del proveedor.
	 * @param idProd         Producto a comprar.
	 * @param stock          Stock del producto a comprar.
	 * @param precioProducto Precio de una unidad del producto.
	 * @param haveNulls      true para que pueda tener nulls este transfer
	 */
	public TransferProveedor(String id, String nombre, String desc, String idProd, Integer stock, Double precioProducto,
			boolean haveNulls) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = desc;
		this.idProd = idProd;
		this.stock = stock == null && haveNulls ? null : stock;
		if (precioProducto == null || stock == null)
			precioTotal = haveNulls ? null : 0.0;
		else
			this.precioTotal = precioProducto * this.stock;
	}

	/**
	 * @return El identificador del proveedor
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return El nombre del proveedor
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return La descripcion del proveedor
	 */
	public String getDesc() {
		return descripcion;
	}

	/**
	 * @return El stock (cantidad) del producto a comprar
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @return El precio de la compra, stock * producto.precio;
	 */
	public Double getPrecio() {
		return precioTotal;
	}

	/**
	 * @return El id del producto a comprar.
	 */
	public String getIdProd() {
		return idProd;
	}

}
