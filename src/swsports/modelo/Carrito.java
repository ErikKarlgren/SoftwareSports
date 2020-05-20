package swsports.modelo;

import java.util.Map;
import java.util.HashMap;

public class Carrito {

  private Map<Producto, Integer> listaProductos;
  private Integer numProductos;
  private Double precioTotal;
  
  public Carrito(){
    this.listaProductos = new HashMap<Producto, Integer>();
    this.numProductos = 0;
    this.precioTotal = 0.0;
  }
  
  
  public boolean carritoVacio(){
	  return this.listaProductos.size() == 0;
  }
  
  public Double getPrecioTotal(){
	  return this.precioTotal;
  }
  
  public Integer getNumProductos(){
	  return this.numProductos;
  }
  
  /**
  * Añade un producto p al carrito. Si el producto ya está, simplemente se actualiza el número de unidades
  */
  public void anyadirProducto(Producto p){
	  if(this.listaProductos.containsKey(p)){
		  this.listaProductos.put(p, this.listaProductos.get(p) + 1);
	  }
    
	  else {
		  this.listaProductos.put(p, 1);
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
	     
	  if(this.listaProductos.containsKey(p)){
		  if(this.listaProductos.get(p) == 1){
			  this.listaProductos.remove(p);
	  }
		  
	  else{
		  this.listaProductos.put(p, this.listaProductos.get(p) - 1);
	  }
	   
	  eliminado = true;
	  this.numProductos--;
	  this.precioTotal -= p.getPrecio();
	  
	  }
	    
	  return eliminado;
	  }
}
