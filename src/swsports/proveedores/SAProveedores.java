package swsports.proveedores;

import java.util.List;

import swsports.daoproveedores.FachadaDAOProveedores;
import swsports.daoproveedores.IFachadaDAOProveedores;
import swsports.modelo.TransferProveedores;
import swsports.modelo.Proveedores;

class SAProveedores implements ISAProveedores {

	private IFachadaDAOProveedores dao;

	SAProveedores(){
		dao = new FachadaDAOProveedores();
	}

	@Override
	public List<Proveedor> busquedaProveedores(TransferProveedor tProv){
		dao.busquedaProveedores(tProv);
	}

	@Override
	public boolean pedidoProveedor(TransferProveedor prov){
		return dao.consultaProveedor(id) != null && dao.pedidoProveedor(prov);
	}

	@Override
	public Proveedor consultaProveedor(String id){
		return id == null ? nul : dao.consultaProveedor(id);
	}

	@Override
	public boolean quitarProveedor(Proveedor prov){
		return (dao.consultaProveedor(id) != null) && dao.quitarProveedor(prov);
	}

	@Override
	public boolean recibirPedido(TransferProveedor tProv){
		return dao.recibirPedido(tProv);
	}

	@Override
	public boolean editarProveedor(TransferProveedor tProv){
		return tProv != null && dao.editarProveedor(tProv);
	}

	@Override
	public boolean cancelarPedido(TransferProveedor tProv){
		return dao.cancelarPedido(tProv);
	}

	@Override
	public boolean anyadirProveedor(Proveedor prov){
		return prov != null && (dao.consultaProveedor(prov.getId()) == null) && dao.anyadirProveedor(prov);
	}
}
