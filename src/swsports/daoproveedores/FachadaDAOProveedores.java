package swsports.daoproveedores;

import java.util.List;

import swsports.modelo.Proveedor;
import swsports.modelo.TransferProveedor;

public class FachadaDAOProveedores implements IFachadaDAOProveedores {
  
	private IDAOProveedores dao;

	public FachadaDAOProveedores() {
		dao = new DAOProveedores();
	}

	@Override
	public List<Proveedor> busquedaProveedores(TransferProveedor tProv) {
		return dao.busquedaProveedores(tProv);
	}

	@Override
	public boolean pedidoProveedor(TransferProveedor prov) {
		return dao.pedidoProveedor(prov);
	}

	@Override
	public Proveedor consultaProveedor(String id) {
		return dao.consultaProveedor(id);
	}

	@Override
	public boolean quitarProveedor(Proveedor prov) {
		return dao.quitarProveedor(prov);
	}

	@Override
	public boolean recibirPedido(TransferProveedor tProv) {
		return dao.recibirPedido(tProv);
	}

	@Override
	public boolean editarProveedor(TransferProveedor tProv) {
		return dao.editarProveedor(tProv);
	}

	@Override
	public boolean cancelarPedido(TransferProveedor tProv) {
		return dao.cancelarPedido(tProv);
	}

	@Override
	public boolean anyadirProveedor(Proveedor prov) {
		return dao.anyadirProveedor(prov);
	}
}
