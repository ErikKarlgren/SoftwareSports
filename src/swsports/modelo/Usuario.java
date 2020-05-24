package swsports.modelo;

import org.json.JSONObject;

/**
 * Clase que representa un usuario.
 */
public class Usuario implements Reportable {
	private String id;
	private String nombre;
	private String mail;
	private String contrasenya;
	private int telefono;
	private String direccion;
	private boolean esAdmin;
	private Carrito carrito;

	/**
	 * Crea un usuario a partir de unos datos en formato JSON. Hay que asegurar
	 * primero que el formato del argumento es correcto.
	 * 
	 * @param usu Objeto {@link JSONObject} con los datos del usuario.
	 */
	public Usuario(JSONObject usu) {
		this.id = usu.getString("id");
		this.nombre = usu.getString("nombre");
		this.mail = usu.getString("mail");
		this.contrasenya = usu.getString("contrasenya");
		this.telefono = usu.getInt("telefono");
		this.direccion = usu.getString("direccion");
		this.esAdmin = usu.getBoolean("admin");
		this.carrito = new Carrito();
	}

	/**
	 * Crea un usuario sin privilegios de administrador a partir de los siguientes
	 * argumentos:
	 * 
	 * @param id          Identificador del usuario.
	 * @param nombre      Nombre del usuario.
	 * @param mail        Correo electrónico del usuario.
	 * @param contrasenya Contraseña del usuario.
	 * @param tlfn        Teléfono del usuario.
	 * @param dir         Dirección del usuario.
	 */
	public Usuario(String id, String nombre, String mail, String contrasenya, int tlfn, String dir) {
		this(id, nombre, mail, contrasenya, tlfn, dir, false);
	}

	/**
	 * Crea un usuario especificando si tiene privilegios de administrador o no.
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
	public Usuario(String id, String nombre, String mail, String contrasenya, int tlfn, String dir, boolean esAdmin) {
		this.id = id;
		this.nombre = nombre;
		this.mail = mail;
		this.contrasenya = contrasenya;
		this.telefono = tlfn;
		this.direccion = dir;
		this.esAdmin = esAdmin;
	}

	/**
	 * Crea un {@link Usuario} a partir de un objeto {@link TransferUsuario} que
	 * contenga todos los datos.
	 * 
	 * @param usu Objeto con los datos.
	 */
	public Usuario(TransferUsuario usu) {
		this.id = usu.getId();
		this.nombre = usu.getNombre();
		this.mail = usu.getMail();
		this.contrasenya = usu.getContrasenya();
		this.telefono = usu.getTelefono();
		this.direccion = usu.getDireccion();
		this.esAdmin = usu.esAdmin();
	}

	/**
	 * Devuelve si el usuario tiene privilegios de administrador o no.
	 * 
	 * @return <code>true</code> si es administrador (<code>false</code> en caso
	 *         contrario).
	 */
	public boolean esAdmin() {
		return esAdmin;
	}

	/**
	 * Devuelve la contraseña del usuario. Este método no es seguro y en una
	 * aplicación real las contraseñas estarían encriptadas.
	 * 
	 * @return Contraseña del usuario.
	 */
	public String getContrasenya() {
		return contrasenya;
	}

	/**
	 * Devuelve la dirección donde reside el usuario.
	 * 
	 * @return Dirección del usuario.
	 */
	public String getDireccion() {
		return direccion;
	}

	@Override
	public String getId() {
		return id;
	}

	/**
	 * Devuelve el correo electrónico del usuario.
	 * 
	 * @return Correo electrónico del usuario.
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Devuelve el nombre del usuario.
	 * 
	 * @return Nombre del usuario.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Devuelve el teléfono del usuario.
	 * 
	 * @return Teléfono del usuario.
	 */
	public int getTelefono() {
		return telefono;
	}

	@Override
	public JSONObject report() {
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("direccion", direccion);
		obj.put("admin", esAdmin);
		obj.put("contrasenya", contrasenya);
		obj.put("mail", mail);
		obj.put("nombre", nombre);
		obj.put("telefono", telefono);
		return obj;
	}

	/**
	 * Determina si el usuario debe tener o no privilegios de administrador.
	 * 
	 * @param esAdmin <code>true</code> para conceder privilegios de administrador.
	 *                <code>false</code> para quitarlos.
	 */
	public void setAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	/**
	 * Establece la nueva contraseña del usuario.
	 * 
	 * @param contrasenya Nueva contraseña.
	 */
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	/**
	 * Cambia la dirección del usuario.
	 * 
	 * @param direccion Nueva dirección.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Cambia el correo electrónico del usuario.
	 * 
	 * @param mail Nuevo correo electrónico.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Cambia el nombre del usuario.
	 * 
	 * @param nombre Nuevo nombre.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Cambia el teléfono del usuario.
	 * 
	 * @param telefono Nuevo teléfono.
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}
