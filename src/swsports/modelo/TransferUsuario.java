package swsports.modelo;

/**
 * Clase para transferir los datos sin más de un usuario.
 */
public class TransferUsuario {
	private final String id;
	private String nombre;
	private String mail;
	private String contrasenya;
	private Integer telefono;
	private String direccion;
	private Boolean esAdmin;

	/**
	 * Crea un {@link TransferUsuario} equivalente a un {@link Usuario} sin
	 * privilegios de administrador.
	 * 
	 * @param id          Identificador del usuario.
	 * @param nombre      Nombre del usuario.
	 * @param mail        Correo electrónico del usuario.
	 * @param contrasenya Contraseña del usuario.
	 * @param tlfn        Teléfono del usuario.
	 * @param dir         Dirección del usuario.
	 */
	public TransferUsuario(String id, String nombre, String mail, String contrasenya, Integer tlfn, String dir) {
		this(id, nombre, mail, contrasenya, tlfn, dir, false);
	}

	/**
	 * Crea un {@link TransferUsuario} a partir de los argumentos dados.
	 * 
	 * @param id          Identificador del usuario.
	 * @param nombre      Nombre del usuario.
	 * @param mail        Correo electrónico del usuario.
	 * @param contrasenya Contraseña del usuario.
	 * @param tlfn        Teléfono del usuario.
	 * @param dir         Dirección del usuario.
	 * @param esAdmin     Especifica si el usuario tendrá privilegios de
	 *                    administrador (<code>true</code>) o no
	 *                    (<code>false</code>).
	 */
	public TransferUsuario(String id, String nombre, String mail, String contrasenya, Integer tlfn, String dir,
			Boolean esAdmin) {
		this.id = id;
		this.nombre = nombre;
		this.mail = mail;
		this.contrasenya = contrasenya;
		this.telefono = tlfn;
		this.direccion = dir;
		this.esAdmin = esAdmin;
	}

	/**
	 * Crea un {@link TransferUsuario} a partir de los datos de un {@link Usuario}.
	 * 
	 * @param usu Usuario del que se extraen los datos.
	 */
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
	 * @return Si es admin o no el usuario
	 */
	public Boolean esAdmin() {
		return esAdmin;
	}

	/**
	 * @return La contrasenya del usuario
	 */
	public String getContrasenya() {
		return contrasenya;
	}

	/**
	 * @return La direccion del usuario
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @return El identificador del usuario
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return El mail del usuario
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @return El nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return El telefono del usuario
	 */
	public Integer getTelefono() {
		return telefono;
	}

}
