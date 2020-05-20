package swsports.modelo;

import java.util.Map;
import java.util.HashMap;

public class Carrito {

  private Map<Producto, Integer> listaProductos;
  
  public Carrito(){
    this.listaProductos = new HashMap<Producto, Integer>();
  }
  
  /*
  * Añade un producto p al carrito. Si el producto ya está, simplemente se actualiza el número de unidades
  */
  public void anyadirProducto(Producto p){
    if(this.listaProductos.containsValue(p)){
      this.listaProductos.put(p, this.listaProductos.get(p) + 1);
    }
    
    else{
      this.listaProductos.put(p, 1);
    }
  }
  
  public boolean eliminarProducto(Producto p){
    boolean eliminado = false;
    
    if(this.listaProductos.containsValue(p)){
      if(this.listaProductos.get(p) == 1){
        this.listaProductos.remove(p);
      }
      else{
        this.listaProductos.put(p, this.listaProductos.get(p) - 1);
      }
      
      eliminado = true;
    }
    
    return eliminado;
}
