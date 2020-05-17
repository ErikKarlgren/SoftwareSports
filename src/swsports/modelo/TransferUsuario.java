package swsports.modelo;

/**
 * Esta clase sirve para transferir los datos de un usuario. *
 */
public class TransferUsuario {
	private final String id;
	private String nombre;
	private String mail;
	private String contrasenya;
	private int telefono;
	private String direccion;
	private boolean esAdmin;

	public TransferUsuario(String id, String nombre, String mail, String contrasenya, int tlfn, String dir) {
		this(id, nombre, mail, contrasenya, tlfn, dir, false);
	}

	public TransferUsuario(String id, String nombre, String mail, String contrasenya, int tlfn, String dir,
			boolean esAdmin) {
		this.id = id;
		this.nombre = nombre;
		this.mail = mail;
		this.contrasenya = contrasenya;
		this.telefono = tlfn;
		this.direccion = dir;
		this.esAdmin = esAdmin;
	}
	
	public TransferUsuario(Usuario usu) {
		this.id = usu.getId();
		this.nombre = usu.getNombre();
		this.mail = usu.getMail();
		this.contrasenya = usu.getContrasenya();
		this.telefono = usu.getTelefono();
		this.direccion = usu.getDireccion();
		this.esAdmin = usu.esAdmin();
	}

	/**
	 * @return El identificador del usuario
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return El nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return El mail del usuario
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @return La contrasenya del usuario
	 */
	public String getContrasenya() {
		return contrasenya;
	}

	/**
	 * @return El telefono del usuario
	 */
	public int getTelefono() {
		return telefono;
	}

	/**
	 * @return La direccion del usuario
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @return Si es admin o no el usuario
	 */
	public boolean esAdmin() {
		return esAdmin;
	}

}
