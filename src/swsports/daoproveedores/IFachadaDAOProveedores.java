package swsports.daoproveedores;

public interface IFachadaDAOProveedores {
  		/**
	 * Devuelve una lista de los proveedores que se adhieren a los parámetros de
	 * búsqueda proporcionados. Los parámetros que sean nulos en el objeto
	 * {@link TransferProveedor} no se usarán para la búsqueda.
	 * 
	 * @param tProv Objeto {@link TransferProveedor} usado como parámetro de busca
	 * @return Lista con los proveedores que buscamos
	 */
	public List<Proveedor> busquedaProveedores(TransferProveedor tProv);

	/**
	 * Actualiza el idProducto del proveedor en cuestion.
	 * 
  	 * @param prov Proveedor a actualizar.
	 * @return <code>true</code> si se ha podido actualizar correctamente.
	 */
	public boolean pedidoProveedor(TransferProveedor prov);

	/**
	 * Devuelve los datos de un proveedor dado su identificador (id).
	 * 
	 * @param id Identificador del proveedor.
	 * @return Proveedor con el identificador buscado (puede ser nulo).
	 */
	public Proveedor consultaProveedor(String id);

	/**
	 * Da de baja a un proveedor.
	 * 
	 * @param prov Proveedor a eliminar.
	 * @return <code>true</code> si se ha podido dar de baja al proveedor.
	 *         <code>false</code> en caso contrario.
	 */
	public boolean quitarProveedor(Proveedor prov);
	
	/**
	 * Edita los datos de un proveedor, con el idProducto a nulo.
	 * 
	 * @param rProv          Proveedor actualizado.
	 * @return <code>true</code> si se ha podido editar al proveedor.
	 */
	public boolean recibirPedido(TransferProveedor tProv);

	/**
	 * Edita los datos de un proveedor.
	 * 
	 * @param rProv          Proveedor actualizado.
	 * @return <code>true</code> si se ha podido editar al proveedor.
	 */
	public boolean editarProveedor(TransferProveedor tProv);

	/**
	 * Cancela el pedido con el proveedor.
	 * 
	 * @param rProv          Proveedor actualizado con el idProducto a nulo.
	 * @return <code>true</code> si se ha podido editar los datos.
	 */
	public boolean cancelarPedido(TransferProveedor tProv);

	/**
	 * Da de alta un proveedor y lo registra en la apliación.
	 * 
	 * @param prov Proveedor a registrar
	 * @return <code>true</code> si se ha podido registrar al proveedor.
	 *         <code>false</code> en caso contrario.
	 */
	public boolean anyadirProveedor(Proveedor prov);
}
