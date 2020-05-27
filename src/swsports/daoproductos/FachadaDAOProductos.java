package swsports.daoproductos;

import java.util.List;

import swsports.modelo.TransferProducto;
import swsports.modelo.Producto;
import swsports.modelo.Tarjeta;

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
  public boolean anyadirProducto(Producto prod){
  	return dao.anyadirProducto(prod);
  }
  
  @Override 
  public boolean quitarProducto(Producto prod){
	return dao.quitarProducto(prod);
  }
  
  @Override 
  public boolean comprar(Tarjeta t, List<Producto> lp){
	return dao.comprar(t, lp);
  }

}
