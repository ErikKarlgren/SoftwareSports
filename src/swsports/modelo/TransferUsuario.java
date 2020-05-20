package swsports.modelo;

import org.json.JSONObject;

/**
 * Esta clase sirve para transferir los datos de un usuario. *
 * Clase para transferir los datos sin más de un usuario.
 */
public class TransferUsuario {
	private final String id;
	private String nombre;
	private String mail;
	private String contrasenya;
	private int telefono;
	private Integer telefono;
	private String direccion;
	private boolean esAdmin;
	private Boolean esAdmin;

	public TransferUsuario(String id, String nombre, String mail, String contrasenya, int tlfn, String dir) {
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

	public TransferUsuario(String id, String nombre, String mail, String contrasenya, int tlfn, String dir,
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
			boolean esAdmin) {
		this.id = id;
		this.nombre = nombre;
@@ -26,7 +52,12 @@ public TransferUsuario(String id, String nombre, String mail, String contrasenya
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
@@ -68,7 +99,7 @@ public String getContrasenya() {
	/**
	 * @return El telefono del usuario
	 */
	public int getTelefono() {
	public Integer getTelefono() {
		return telefono;
	}

@@ -82,7 +113,7 @@ public String getDireccion() {
	/**
	 * @return Si es admin o no el usuario
	 */
	public boolean esAdmin() {
	public Boolean esAdmin() {
		return esAdmin;
	}

	
