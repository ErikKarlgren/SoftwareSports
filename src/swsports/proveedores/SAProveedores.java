package swsports.proveedores;

class SAProveedores implements ISAProveedores {
	private IFachadaDAOProveedores dao;

	SAProveedores(){
		dao = new FachadaDAOProveedores();
	}
}
