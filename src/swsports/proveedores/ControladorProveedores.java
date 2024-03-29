package swsports.proveedores;

import java.util.List;

import swsports.modelo.TransferProveedor;
import swsports.modelo.Proveedor;

/**
 * Controlador de lo relativo a los proveedores ({@link Proveedor}).
 */

public class ControladorProveedores {

	private IFachadaProveedores fachProv;

	public ControladorProveedores(){
		fachProv = new FachadaProveedores();
	}

	/**
	 * Devuelve una lista de los proveedores que se adhieren a los par�metros de
	 * b�squeda proporcionados. Los par�metros que sean nulos en el objeto
	 * {@link TransferProvedor} no se usar�n para la b�squeda.
	 * 
	 * @param tProv Objeto {@link TransferProveedor} usado como par�metro de busqueda.
	 * @return Lista con los proveedores que buscamos.
	 */
	public List<Proveedor> busquedaProveedores(TransferProveedor tProv){
		return fachProv.busquedaProveedor(tProv);
	}

	/**
	 * Devuelve los datos de un proveedor dado su identificador (id).
	 * 
	 * @param id Identificador del proveedor.
	 * @return Proveedor con el identificador buscado (puede ser nulo).
	 */
	public Proveedor consultaProveedor(String id){
		return fachProv.consultaProveedor(id);
	}

	/**
	 * Da de baja a un proveedor
	 * 
	 * @param prov Proveedor a eliminar
	 * @return <code>true</code> si se ha podido dar de baja al proveedor.
	 *         <code>false</code> en caso contrario.
	 */
	public boolean quitarProveedor(Proveedor prov){
		return fachProv.quitarProveedor(prov);
	}

	/**
	 * Edita los datos de un proveedor. Su identificador (id) se usar� para buscar el
	 * proveedor que se quiere editar y se sobrescribir�n el resto de datos.
	 * 
	 * @param tProv Objeto con los nuevos atributos del proveedor.
	 * @return <code>true</code> si se ha podido editar el proveedor.
	 */
	public boolean editarProveedor(TransferProveedor tProv){
		return fachProv.editarProveedor(tProv);
	}

	/**
	 * Da de alta un proveedor y lo registra en la aplicaci�n.
	 * 
	 * @param prov Proveedor a registrar.
	 * @return <code>true</code> si se ha podido registrar al Proveedor.
	 *         <code>false</code> en caso contrario.
	 */
	public boolean anyadirProveedor(Proveedor prov){
		return fachProv.anyadirProveedor(prov);
	}

	/**
	 * Se realiza un pedido a un proveedor.
	 * 
	 * @param prov Proveedor al que se realiza el pedido.
	 * @return <code>true</code> si se ha podido realizar el pedido al Proveedor.
	 *         <code>false</code> en caso contrario.
	 */
	public boolean pedidoProveedor(TransferProveedor prov){
		return fachProv.pedidoProveedor(prov);
	}

	/**
	 * Cancela el pedido que se realiza al proveedor.
	 * 
	 * @param prov Proveedor al que se realiza el pedido
	 * @return <code>true</code> si se ha podido cancelar el pedido realizado al Proveedor.
	 *         <code>false</code> en caso contrario.
	 */
	public boolean cancelarPedido(Proveedor prov){
		return fachProv.cancelarPedido(prov);
	}

	/**
	 * Informa si se ha recibido el pedido realizado al proveedor.
	 * 
	 * @param prov Proveedor al que se le ha realizado el pedido.
	 * @return <code>true</code> si el pedido se ha recibido correctamente.
	 *         <code>false</code> en caso contrario.
	 */
	public boolean recibirPedido(Proveedor prov){
		return fachProv.recibirPedido(prov);
	}
}