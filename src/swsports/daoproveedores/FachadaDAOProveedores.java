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
		// TODO Auto-generated method stub
		return dao.busquedaProveedores(tProv);
	}

	@Override
	public boolean pedidoProveedor(TransferProveedor prov) {
		// TODO Auto-generated method stub
		return dao.pedidoProveedor(prov);
	}

	@Override
	public Proveedor consultaProveedor(String id) {
		// TODO Auto-generated method stub
		return dao.consultaProveedor(id);
	}

	@Override
	public boolean quitarProveedor(String id) {
		// TODO Auto-generated method stub
		return dao.quitarProveedor(id);
	}

	@Override
	public boolean recibirPedido(TransferProveedor tProv) {
		// TODO Auto-generated method stub
		return dao.recibirPedido(tProv);
	}

	@Override
	public boolean editarProveedor(TransferProveedor tProv) {
		// TODO Auto-generated method stub
		return dao.editarProveedor(tProv);
	}

	@Override
	public boolean cancelarPedido(TransferProveedor tProv) {
		// TODO Auto-generated method stub
		return dao.cancelarPedido(tProv);
	}

	@Override
	public boolean anyadirProveedor(Proveedor prov) {
		// TODO Auto-generated method stub
		return dao.anyadirProveedor(prov);
	}
}
