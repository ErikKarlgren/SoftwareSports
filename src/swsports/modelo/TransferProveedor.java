package swsports.modelo;

public class TransferProveedor {
	private final String id;
	private String nombre;
	private String descripcion;
	private String idProducto;
	private int stock;
	private double precio;
	

	/**
	 * Crea un {@link TransferProveedor} a partir de los argumentos dados.
	 * 
	 * @param id          Identificador del proveedor.
	 * @param nombre      Nombre del proveedor.
	 * @param desc        Descripcion del proveedor.
	 * @param idProd	  id del producto a comprar.
	 * @param stck        Stock del producto a comprar.
	 * @param p	          Precio de la compra del producto.
	 */
	public TransferProveedor(String id, String nombre, String desc, String idProd, int stck, double p) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = desc;
		this.idProducto = idProd;
		this.stock = stck;
		this.precio = p;
	}

	/**
	 * Crea un {@link TransferProveedor} a partir de los datos de un {@link Proveedor}.
	 * 
	 * @param prov Proveedor del que se extraen los datos.
	 */
	public TransferProveedor(Proveedor prov) {
		this.id = prov.getId();
		this.nombre = prov.getNombre();
		this.descripcion = prov.getDesc();
		this.idProducto = prov.getIdProducto();
		this.stock = prov.getStock();
		this.precio = prov.getPrecio();
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
	 * @return El id del producto a comprar
	 */
	public String getIdProducto() {
		return idProducto;
	}

	/**
	 * @return El stock (cantidad) del producto a comprar
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @return El precio de la compra, stock * producto.precio;
	 */
	public double getPrecio() {
		return precio;
	}

}

