package swsports.productos;

import java.util.List;

import swsports.modelo.TransferProducto;
import swsports.modelo.Carrito;
import swsports.modelo.Producto;

/**
* Fachada del modulo Productos. Todas las operaciones que tengan
* que ver con dicho modulo se realizan mediante llamadas a los métodos
* que se encuentran en esta clase.
*/

public class FachadaProductos implements IFachadaProductos {
        private ISAProductos sa;

        public FachadaProductos(){
          sa = new SAProductos();
        }

        @Override
        public boolean altaProducto(Producto prod){
              return sa.altaProducto(prod);
        }

        @Override
        public boolean bajaProducto(Producto prod){
              return sa.bajaProducto(prod);
        }

        @Override
        public boolean editarProducto(TransferProducto prod){
          return sa.editarProducto(prod);
        }

        @Override
        public Producto consultaProducto(String id){
          return sa.consultaProducto(id);
        }

        @Override
        public List<Producto> busquedaProducto(TransferProducto tProd){
          return sa.busquedaProducto(tProd);
        }

        @Override
        public boolean anyadirProducto(Producto prod, Carrito carrito){
          return sa.anyadirProducto(prod, carrito);
        }

        @Override
        public boolean quitarProducto(Producto prod, Carrito carrito){
          return sa.quitarProducto(prod, carrito);
        }

        @Override
        public boolean comprar(Carrito carrito){
          return sa.comprar(carrito);
        }	

}
