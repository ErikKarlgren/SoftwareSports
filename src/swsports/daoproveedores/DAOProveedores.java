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
		Predicate<Proveedor> pred = new Predicate<Proveedor>() {
			@Override
			public boolean test(Proveedor prov) {
				boolean ok = true;

				ok &= (tProv.getId() == null || prov.getId().equals(tProv.getId()));
				ok &= (tProv.getNombre() == null || prov.getNombre().equals(tProv.getNombre()));
				ok &= (tProv.getDesc() == null || prov.getDesc().contains(tProv.getDesc()));
				ok &= (tProv.getIdProd() == null || prov.getIdProducto().contains(tProv.getIdProd()));
				ok &= (prov.getStock() == tProv.getStock());
				ok &= (prov.getPrecio() == tProv.getPrecio());

				return ok;
			}
		};
		return bd.busqueda(pred);
	}

	@Override
	public boolean pedidoProveedor(TransferProveedor tProv) {
		try {
			bd.editar(new Proveedor(tProv));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public Proveedor consultaProveedor(String id) {
		return bd.consulta(id);
	}

	@Override
	public boolean quitarProveedor(Proveedor prov) {
		try {
			bd.eliminar(prov.getId());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean recibirPedido(TransferProveedor tProv) {
		try {
			bd.editar(new Proveedor(tProv));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean editarProveedor(TransferProveedor tProv) {
		try {
			bd.editar(new Proveedor(tProv));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean cancelarPedido(TransferProveedor tProv) {
		try {
			bd.editar(new Proveedor(tProv));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean anyadirProveedor(Proveedor prov) {
		try {
			bd.anyadir(prov);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
