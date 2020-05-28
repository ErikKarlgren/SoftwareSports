package swsports.proveedores;

import swsports.modelo.TransferProveedor;
import swsports.modelo.Proveedor;

public class FachadaProveedores implements IFachadaProveedores {

	private ISAProveedores sa;

	public FachadaProveedores(){
		sa = new SAProveedores();
	}

	public List<Proveedor> busquedaProveedor(TransferProveedor tProv){
		return sa.busquedaProvedor(tProv);
	}

	public Proveedor consultaProveedor(String id){
		return sa.consultaProveedor(id);
	}

	public boolean quitarProveedor(Proveedor prov){
		return sa.quitarProveedor(prov);
	}

	public boolean editarProveedor(TransferProveedor tProv){
		return sa.editarProveedor(tProv);
	}

	public boolean anyadirProveedor(Proveedor prov){
		return sa.anyadirProveedor(prov);
	}

	public boolean pedidoProveedor(TransferProveedor prov){
		return sa.pedidoProveedor(tProv);
	}

	public boolean cancelarPedido(TransferProveedor tProv){
		return sa.cancelarPedido(tProv);
	}

	public boolean recibirPedido(TransferProveedor tProv){
		return sa.recibirPedido(tProv);
	}
}
