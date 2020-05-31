package swsports.proveedores;

import swsports.modelo.TransferProveedor;

import java.util.List;

import swsports.modelo.Proveedor;

public class FachadaProveedores implements IFachadaProveedores {

	private ISAProveedores sa;

	public FachadaProveedores(){
		sa = new SAProveedores();
	}

	@Override
	public List<Proveedor> busquedaProveedor(TransferProveedor tProv){
		return sa.busquedaProveedores(tProv);
	}

	@Override
	public Proveedor consultaProveedor(String id){
		return sa.consultaProveedor(id);
	}

	@Override
	public boolean quitarProveedor(Proveedor prov){
		return sa.quitarProveedor(prov);
	}

	@Override
	public boolean editarProveedor(TransferProveedor tProv){
		return sa.editarProveedor(tProv);
	}

	@Override
	public boolean anyadirProveedor(Proveedor prov){
		return sa.anyadirProveedor(prov);
	}

	@Override
	public boolean pedidoProveedor(TransferProveedor prov){
		return sa.pedidoProveedor(prov);
	}

	@Override
	public boolean cancelarPedido(Proveedor prov){
		return sa.cancelarPedido(prov);
	}

	@Override
	public boolean recibirPedido(Proveedor prov){
		return sa.recibirPedido(prov);
	}
}