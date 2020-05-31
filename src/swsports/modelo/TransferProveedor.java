package swsports.modelo;

public class TransferProveedor {
	private final String id;
	private String nombre;
	private String descripcion;
	private Producto prod;
	private int stock;
	private double precio;
	

	/**
	 * Crea un {@link TransferProveedor} a partir de los argumentos dados.
	 * 
	 * @param id          Identificador del proveedor.
	 * @param nombre      Nombre del proveedor.
	 * @param desc        Descripcion del proveedor.
	 * @param prod	      Producto a comprar.
	 * @param stck        Stock del producto a comprar.
	 */
	public TransferProveedor(String id, String nombre, String desc, Producto prod, int stck) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = desc;
		this.prod = prod;
		this.stock = stck;
		this.precio = prod.getPrecio() * this.stock;
	}

//	/**
//	 * Crea un {@link TransferProveedor} a partir de los datos de un {@link Proveedor}.
//	 * 
//	 * @param prov Proveedor del que se extraen los datos.
//	 */
//	public TransferProveedor(Proveedor prov) {
//		this.id = prov.getId();
//		this.nombre = prov.getNombre();
//		this.descripcion = prov.getDesc();
//		this.prod = prov.getProducto();
//		this.stock = prov.getStock();
//		this.precio = prov.getPrecio();
//	}

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
	public Producto getProducto() {
		return prod;
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

	public String getIdProd() {
		return prod.getId();
	}

}
