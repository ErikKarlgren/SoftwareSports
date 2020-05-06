package swsports.modelo;

public class Usuario {
	private final String id;
	private String nombre;
	private String mail;
	private String contrasenya;
	private int telefono;
	private String direccion;
	private boolean esAdmin;

	public Usuario(String id, String nombre, String mail, String contrasenya, int tlfn, String dir) {
		this(id, nombre, mail, contrasenya, tlfn, dir, false);
	}

	public Usuario(String id, String nombre, String mail, String contrasenya, int tlfn, String dir, boolean esAdmin) {
		this.id = id;
		this.nombre = nombre;
		this.mail = mail;
		this.contrasenya = contrasenya;
		this.telefono = tlfn;
		this.direccion = dir;
		this.esAdmin = esAdmin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean esAdmin() {
		return esAdmin;
	}

	public void setAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	public String getId() {
		return id;
	}

}
