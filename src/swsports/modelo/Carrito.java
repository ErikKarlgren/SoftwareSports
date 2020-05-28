package swsports.modelo;

import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Carrito {

  private Map<Producto, Integer> mapaProductos;
  private List<Producto> listaProductos;
  private Integer numProductos;
  private Double precioTotal;
  
  public Carrito(){
    this.mapaProductos = new HashMap<Producto, Integer>();
    this.listaProductos = new ArrayList<Producto>();
    this.numProductos = 0;
    this.precioTotal = 0.0;
  }
  
  
  public boolean carritoVacio(){
	  return this.mapaProductos.size() == 0;
  }
  
  public Double getPrecioTotal(){
	  return this.precioTotal;
  }
  
  public Integer getNumProductos(){
	  return this.numProductos;
  }
  
  public void vaciarCarrito() {
	  this.mapaProductos.clear();
	  this.numProductos = 0;
	  this.precioTotal = 0.0;
  }
  
  public int getNumUnidadesProducto(Producto p) {
	  if(this.mapaProductos.containsKey(p)) {
		  return this.mapaProductos.get(p);
	  }
	  else {
		  return 0;
	  }
  }
  
  public List<Producto> getListaProductos(){
	  return Collections.unmodifiableList(this.listaProductos);
  }
    
  /**
  * Añade un producto p al carrito. Si el producto ya está, simplemente se actualiza el número de unidades
  */
  public void anyadirProducto(Producto p){
	  if(this.mapaProductos.containsKey(p)){
		  this.mapaProductos.put(p, this.mapaProductos.get(p) + 1);
	  }
    
	  else {
		  this.listaProductos.add(p);
		  this.mapaProductos.put(p, 1);
	  }
    
	  this.numProductos++;
	  this.precioTotal += p.getPrecio();
  }
  
  /**
  * Elimina un producto p al carrito. Si el número de unidades es 1, lo elimina del mapa, si no, le resta una unidad.
  * También devuelve true si el producto se ha eliminado, es decir, existe en el mapa.
  */
  public boolean eliminarProducto(Producto p){
	  
	  boolean eliminado = false;
	     
	  if(this.mapaProductos.containsKey(p)){
		  if(this.mapaProductos.get(p) == 1){
			  this.mapaProductos.remove(p);
			  this.listaProductos.remove(p);
		  }
		  
	  else{
		  this.mapaProductos.put(p, this.mapaProductos.get(p) - 1);
	  }
	   
	  eliminado = true;
	  this.numProductos--;
	  this.precioTotal -= p.getPrecio();
	  
	  }
	    
	  return eliminado;
  }
}
