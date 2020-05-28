package swsports.productos;

import java.util.List;

import swsports.daoproductos.FachadaDAOProductos;
import swsports.daoproductos.IFachadaDAOProductos;
import swsports.modelo.TransferProducto;
import swsports.modelo.Carrito;
import swsports.modelo.Producto;

/**
* Clase encargada de la lógica del módulo de Productos
*/
class SAProductos implements ISAProductos {
	
	private IFachadaDAOProductos dao;
	
	SAProductos(){
		dao = new FachadaDAOProductos();
	}

        @Override
        public boolean altaProducto(Producto prod){
              return prod != null && (dao.consultaProducto(prod.getId()) == null) && dao.altaProducto(prod);
        }

        @Override
        public boolean bajaProducto(Producto prod){
              return (dao.consultaProducto(prod.getId()) !=null) && dao.bajaProducto(prod);
        }

        @Override
        public boolean editarProducto(TransferProducto tProd){
          return tProd != null && dao.editarProducto(tProd);
        }

        @Override
        public Producto consultaProducto(String id){
          return id == null? null : dao.consultaProducto(id);
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
