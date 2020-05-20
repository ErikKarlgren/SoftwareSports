package swsports.modelo;

import java.util.Map;
import java.util.HashMap;

public class Carrito {

  private Map<Producto, Integer> listaProductos;
  
  public Carrito(){
    this.listaProductos = new HashMap<Producto, Integer>();
  }
  
  public anyadirProducto(Producto p){
    if(this.listaProductos.containsValue(p)){
      this.listaProductos.put(p, this.listaProductos.get(p) + 1);
    }
    
    else{
      this.listaProductos.put(p, 1);
    }
  }
  
}
