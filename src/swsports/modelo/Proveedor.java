package swsports.modelo;

import org.json.JSONObject;

public class Proveedor implements Reportable {
	
	private final String id;
	private String nombre;
	private String descripcion;
	private String idProducto;
	private int stock;
	private double precio;
	
	public Proveedor(JSONObject prov) {
		this.id = prov.getString("id");
		this.nombre = prov.getString("nombre");
		this.descripcion = prov.getString("descripcion");
		this.idProducto = prov.getString("idProducto");
		this.stock = prov.getInt("stock");
		this.precio = prov.getDouble("precio");
	}
	
	public Proveedor(String id, String nombre, String desc, String idP, int stck, double p) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = desc;
		this.idProducto = idP;
		this.stock = stck;
		this.precio = p;
	}
	
	public Proveedor(TransferProveedor p) {
		this.id = p.getId();
		this.nombre = p.getNombre();
		this.descripcion = p.getDesc();
		this.idProducto = p.getIdProd();
		this.stock = p.getStock();
		this.precio = p.getPrecio();
	}

	public String getId() {
		return id;
	}
	
	public String getIdProducto() {
		return idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDesc() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("nombre", nombre);
		obj.put("descripcion", descripcion);
		obj.put("producto", idProducto);
		obj.put("stock", stock);
		obj.put("precio", precio);
		return obj;
	}
	
}
