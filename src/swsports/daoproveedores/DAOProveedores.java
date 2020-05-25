package swsports.daoproveedores;

import java.util.List;
import java.util.function.Predicate;

import swsports.basedatos.BaseDatos;
import swsports.basedatos.BaseDatosProveedorJSON;
import swsports.modelo.Proveedor;
import swsports.modelo.TransferProveedor;

class DAOProveedores implements IDAOProveedores {

	private BaseDatos<Proveedor> bd;

	public DAOProveedores() {
		bd = BaseDatosProveedorJSON.getInstance();
	}

	@Override
	public List<Proveedor> busquedaProveedores(TransferProveedor tProv) {
		// TODO Auto-generated method stub
		Predicate<Proveedor> pred = new Predicate<Proveedor>() {
			@Override
			public boolean test(Proveedor prov) {
				boolean ok = true;

				ok &= (tProv.getId() == null || prov.getId().equals(tProv.getId()));
				ok &= (tProv.getNombre() == null || prov.getNombre().equals(tProv.getNombre()));
				ok &= (tProv.getDesc() == null || prov.getDesc() == tProv.getDesc());
				ok &= (tProv.getIdProducto() == null || prov.getIdProducto().contains(tProv.getIdProducto()));
				ok &= (prov.getStock()==tProv.getStock());
				ok &= (prov.getPrecio()==tProv.getPrecio());

				return ok;
			}
		};
		return bd.busqueda(pred);
	}

	@Override
	public boolean pedidoProveedor(TransferProveedor tProv) {
		// TODO Auto-generated method stub
		try {
			bd.editar(new Proveedor(tProv));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public Proveedor consultaProveedor(String id) {
		// TODO Auto-generated method stub
		return bd.consulta(id);
	}

	@Override
	public boolean quitarProveedor(Proveedor prov) {
		// TODO Auto-generated method stub
		try {
			bd.eliminar(prov.getId());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean recibirPedido(TransferProveedor tProv) {
		// TODO Auto-generated method stub
		try {
			bd.editar(new Proveedor(tProv));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean editarProveedor(TransferProveedor tProv) {
		// TODO Auto-generated method stub
		try {
			bd.editar(new Proveedor(tProv));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean cancelarPedido(TransferProveedor tProv) {
		// TODO Auto-generated method stub
		try {
			bd.editar(new Proveedor(tProv));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean anyadirProveedor(Proveedor prov) {
		// TODO Auto-generated method stub
		try {
			bd.anyadir(prov);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
