package swsports.productos;

import java.util.List;

import swsports.modelo.TransferProducto;
import swsports.modelo.Producto;

/*
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
              return facProd.altaProducto(prod);
        }

        @Override
        public boolean bajaProducto(Producto prod){
              return facProd.bajaProducto(prod);
        }

        @Override
        public boolean editarProducto(TransferProducto prod){
          return facProd.editarProducto(prod)
        }

        @Override
        public Producto consultaProducto(String id){
          return facProd.consultaProducto(id);
        }

        @Override
        public List<Producto> busquedaProducto(TransferProducto tProd){
          return facProd.busquedaProducto(null);
        }

        @Override
        public boolean anyadirProducto(Producto prod){
          return facProd.anyadirProducto(prod)
        }

        @Override
        public boolean quitarProducto(Producto prod){
          return facProd.quitarProducto(prod);

        @Override
        public boolean comprar(Tarjeta t, List<Producto>){
          return facProd.comprar(t, List);
        }	

}
