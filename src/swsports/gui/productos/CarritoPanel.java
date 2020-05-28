package swsports.gui.productos;

import javax.swing.JPanel;
import swsports.modelo.Carrito;
import swsports.modelo.Producto;
import swsports.productos.ControladorProductos;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class CarritoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ControladorProductos controlador;
	private Carrito carrito;
	
	private JPanel lista;
	private JPanel comprar;
	
	private JButton comprarButton;
	
	public CarritoPanel(ControladorProductos ctrl, Carrito c){
		this.controlador = ctrl;
		this.carrito = c;
		
		initGUI();
	}

	private void initGUI() {
			
		setLayout(new BorderLayout(0, 0));
		
		lista = new JPanel();
		add(lista, BorderLayout.CENTER);
		lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
		
		comprar = new JPanel();
		add(comprar, BorderLayout.SOUTH);
		
		comprarButton = new JButton("Comprar");
		comprar.add(comprarButton);
		
		Producto prod = new Producto("12", "deportivas", "f", 1, 57.0);
		carrito.anyadirProducto(prod);
		carrito.anyadirProducto(prod);
		carrito.anyadirProducto(new Producto("11", "raqueta", "f", 1, 57.0));
		carrito.anyadirProducto(new Producto("13", "deportivas", "f", 1, 57.0));
		carrito.anyadirProducto(new Producto("14", "deportivas", "f", 1, 57.0));
		carrito.anyadirProducto(new Producto("15", "deportivas", "f", 1, 57.0));
		for (Producto p : carrito.getListaProductos()) {
			lista.add(new ProductoDataPanel(p, controlador, EnumModoPanelProductos.CARRITO, carrito));
		}
		
	}
	
	private void update() {
		initGUI();
	}

}