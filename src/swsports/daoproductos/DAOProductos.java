package swsports.daoproductos;

import java.util.List;
import java.util.function.Predicate;

import swsports.basedatos.BaseDatos;
import swsports.basedatos.BaseDatosProductoJSON;
import swsports.modelo.TransferProducto;
import swsports.modelo.Carrito;
import swsports.modelo.Producto;

class DAOProductos implements IDAOProductos {
  
  private BaseDatos<Producto> bd;
  
  public DAOProductos(){
    bd = BaseDatosProductoJSON.getInstance();
  }
  
  @Override
  public boolean altaProducto(Producto prod){
    try{
      bd.anyadir(prod);
      return true;
    } catch (IllegalArgumentException e){
      return false;
    }      
  }
  
  @Override
  public boolean bajaProducto(Producto prod){
    try{
      bd.eliminar(prod.getId());
      return true;
    } catch (IllegalArgumentException e){
      return false;
    }
  }
  
  @Override 
  public boolean editarProducto(TransferProducto prod){
    try{
      bd.editar(new Producto(prod));
      return true;
    } catch (IllegalArgumentException e){
      return false;
    }
  }
  
  @Override 
  public Producto consultaProducto(String id){
    return bd.consulta(id);
  }
  
  @Override
  public List<Producto> busquedaProducto(TransferProducto tProd){
      Predicate<Producto> pred = new Predicate<Producto>(){
        @Override
        public boolean test(Producto p){
          boolean ok = true;          
          
          ok &= (tProd.getId() == null || p.getId().equals(tProd.getId()));
          ok &= (tProd.getNombre() == null || (p.getNombre().toLowerCase()).contains((tProd.getNombre().toLowerCase())));
          ok &= (tProd.getDesc() == null || (p.getDesc().toLowerCase()).contains(tProd.getDesc().toLowerCase()));
          ok &= (tProd.getStock() == null || p.getStock() == tProd.getStock());
          ok &= (tProd.getPrecio() == null || p.getPrecio() == tProd.getPrecio());
              
          return ok;
		}
      };
      
		return bd.busqueda(pred);
	}
  
  @Override
  public boolean anyadirProducto(Producto prod, Carrito carrito){
	  carrito.anyadirProducto(prod);
	  prod.setStock(prod.getStock() - 1);
	  return true;
  }
  
  @Override 
  public boolean quitarProducto(Producto prod, Carrito carrito){
	  if(carrito.eliminarProducto(prod)) {
		  prod.setStock(prod.getStock() + 1);
		  return true;
	  }
	  else {
		  return false;
	  }
  }
  
  @Override 
  public boolean comprar(Carrito carrito){
	  if(carrito.carritoVacio()) {
		  return false;
	  }
	  
	  else {
		  
		  for(Producto p : carrito.getMapaProductos().keySet()) {
			  bd.editar(new Producto(p.getId(), p.getNombre(), p.getDesc(), p.getStock(), p.getPrecio()));
		  }
		  
		  carrito.vaciarCarrito(true);
		  
		  return true;
	  }
  } 

}
