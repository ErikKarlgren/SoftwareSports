package swsports.daoproductos;

import java.util.List;

import swsports.modelo.TransferProducto;
import swsports.modelo.Carrito;
import swsports.modelo.Producto;

public class FachadaDAOProductos implements IFachadaDAOProductos  {
  
  private IDAOProductos dao;
  
  public FachadaDAOProductos(){
  	dao= new DAOProductos();
  }
  
  @Override
  public boolean altaProducto(Producto prod){
  	return dao.altaProducto(prod);
  }
  
  @Override
  public boolean bajaProducto(Producto prod){
  	return dao.bajaProducto(prod);
  }
  
  @Override 
  public boolean editarProducto(TransferProducto prod){
  	return dao.editarProducto(prod);
  }
  
  @Override 
  public Producto consultaProducto(String id){
  	return dao.consultaProducto(id);
  }
  
  @Override
  public List<Producto> busquedaProducto(TransferProducto tProd){
  	return dao.busquedaProducto(tProd);
  }
  
  @Override
  public boolean anyadirProducto(Producto prod, Carrito carrito){
  	return dao.anyadirProducto(prod, carrito);
  }
  
  @Override 
  public boolean quitarProducto(Producto prod, Carrito carrito){
	return dao.quitarProducto(prod, carrito);
  }
  
  @Override 
  public boolean comprar(Carrito carrito){
	return dao.comprar(carrito);
  }

}
