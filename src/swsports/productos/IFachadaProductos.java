package swsports.productos;

import java.util.List;

import swsports.modelo.TransferProducto;
import swsports.modelo.Producto;

public interface IFachadaProductos {
      /**
      * Da de alta un producto nuevo.
      *
      * @param prod Producto nuevo
      * @return  <code>true</code> si se ha podido registrar el producto.
      *          <code>false</code> en caso contrario.
      */
      public boolean altaProducto(Producto prod);
  
      /**
      * Da de baja un producto. Su identificador (id) se usará para buscar el
      * usuario que se quiere eliminar.
      *
      *@param prod Objeto con los atributos del producto a eliminar.
      *@return <code>true</code> si se ha podido dar de baja el producto.
      *        <code>false</code> en caso contrario.
      */
      public boolean bajaProducto(Producto prod);
  
      /**
      * Edita los datos de un producto. Su identificador (id) se usará para buscar el
      * usuario que se quiere editar y se sobrescribirán el resto de datos. 
      *
      * @param prod Objeto con los nuevos stributos del producto.
      * @return <code>true</code> si se ha podido editar el producto.
      *         <code>false</code> en caso contrario.
      */
      public boolean editarProducto(TransferProducto prod);
  
      /**
      * Devuelve los datos de un producto dado su identidicador (id).
      *
      * @param id Identificador del producto.
      * @return Producto con el identificador buscado (puede ser nulo).
      */
      public Producto consultaProducto(String id);
  
      /**
      * Devuelve una lista de los productos que se adhieren a los parámetros
      * de búsqueda proporcionados. Los parámetros que sean nulos en el objeto
      * {@link TransferProducto} no se usarán para la búsqueda.
      *
      * @param tProd Objeto {@link TransferProducto} usado como parametro de búsqueda
      * @return Lista con los productos que buscamos
      */
      public List<Producto> busquedaProducto(TransferProducto tProd);
  
      /**
      * Anyade un producto al carrito
      *
      * @param prod Producto que se anyade
      * @return <code>true</code> si se ha podido anyadir el producto correctamente.
      *         <code>false</code> en caso contrario.
      */
      public boolean anyadirProducto(Producto prod);
  
      /**
      * Elimina un producto del carrito
      *
      * @param prod Producto que se quiere eliminar
      * @return <code>true</code> si se ha podido eliminar el producto correctamente.
      *         <code>false</code> en caso contrario.
      */ 
      public boolean quitarProducto(Producto prod);
  
      /**
      * 
      *
      * @param prod Producto que se quiere eliminar
      * @return <code>true</code> si se ha podido realizar la compra correctamente.
      *         <code>false</code> en caso contrario.
      */  
      public boolean comprar(Tarjeta t, List<Producto> lp);
}
