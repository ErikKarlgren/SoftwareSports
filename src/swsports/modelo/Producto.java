package swsports.modelo;

import org.json.JSONObject;

/**
* Clase que representa un producto
*/

public class Producto implements Reportable {
	private final String id;
	private String nombre;
	private String desc;
	private Integer stock;
	private Double precio;

	/**
	 * Crea un producto a partir de unos datos en formato JSON. Hay que asegurar
	 * primero que el formato del argumento es correcto.
	 * 
	 * @param prod Objeto {@link JSONObject} con los datos del producto.
	 */
	public Producto(JSONObject prod) {
      this.id = prod.getString("id");
      this.nombre = prod.getString("nombre");
      this.desc = prod.getString("desc");
      this.stock = prod.getInt("stock");
      this.precio = prod.getDouble("precio");
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
	public Producto(String id, String nombre, String desc, Integer stock, Double precio) {
		this.id = id;
		this.nombre = nombre;
		this.desc = desc;
		this.stock = stock;
		this.precio = precio;
	}
	/**
	 * Crea un {@link Producto} a partir de un objeto {@link TransferProducto}
	 * que contenga todos los datos.
	 * 
	 * @param prod Objeto con los datos.
	 */
	public Producto(TransferProducto prod) {
		this.id = prod.getId();
		this.nombre = prod.getNombre();
		this.desc = prod.getDesc();
		this.stock = prod.getStock();
		this.precio = prod.getPrecio();
	}

	/**
	 * @return El identificador del producto.
	 */
  @Override
	public String getId() {
		return id;
	}

	/**
	 * @return El nombre del producto.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return La descripción del producto.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return El stock del que se dispone.
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @return El precio del producto.
	 */
	public Double getPrecio() {
		return this.precio;
	}
  
	@Override
	public JSONObject report() {
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("nombre", nombre);
		obj.put("desc", desc);
		obj.put("stock", stock);
		obj.put("precio", precio);
		return obj;
	}
  
	/**
	 * Cambia el número de productos de los que se dispone en tienda.
	 * @param stock Nuevo stock.
	 */
	public void setStock(int stock){
		this.stock=stock;
	}
  
	/**
	 * Cambia el precio del producto.
	 * @param precio Nuevo precio.
	 */
	public void setPrecio(double precio){
		this.precio=precio;
	}
  
	/**
	 * Cambia la descripción del producto.
	 * @param desc Nueva descripción.
	 */
	public void setDesc(String desc){
		this.desc=desc;
	}
  
	/**
	 * Cambia el nombre del producto.
	 * @param nombre Nuevo nombre.
	 */
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
}
