package swsports.daoproductos;

import java.util.List;
import java.util.function.Predicate;

import org.json.JSONObject;

import swsports.basedatos.BaseDatos;
import swsports.basedatos.BaseDatosProductoJSON;
import swsports.modelo.TransferProducto;
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
      bd.eliminar(prod);
      return true;
    } catch (IllegalArgumentException e){
      return false;
    }
  }
  
  @Override 
  public boolean editarProducto(Transfer prod){
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
          boolean ok=true;          
          
          ok &= (tProd.getId() == null || p.getId().equals(tProd.getId()));
          ok &= (tProd.getNombre() == null || p.getNombre().equals(tProd.getNombre()));
          ok &= (tProd.getDesc() == null || p.getDesc().contains(tProd.getDesc()));
          ok &= (tProd.getStock() == null
              || String.valueOf(p.getTelefono()).contains(String.valueOf(tProd.getTelefono())));

          ok &= (tProd.getPrecio() == null
              || String.valueOf(p.getPrecio()).contains(String.valueOf(tProd.getPrecio())));

          return ok;
			}
		};
		return bd.busqueda(pred);
	}
  
  @Override
  public boolean anyadirProducto(Producto prod){
  	return consultaProducto(prod.getId()) != null;
  }
  
  @Override 
  public boolean quitarProducto(Producto prod){
	return consultaProducto(prod.getId()) != null;
  }
  
  @Override 
  public boolean comprar(Tarjeta t, List<Producto> lp){
  } 

}
