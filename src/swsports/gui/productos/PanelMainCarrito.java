package swsports.gui.productos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.LinkedHashMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import swsports.gui.AbstractPanelMain;
import swsports.gui.MainWindow;
import swsports.modelo.Carrito;
import swsports.modelo.Producto;
import swsports.productos.ControladorProductos;

/**
 * Panel para consultar los productos a�adidos al carrito por el usuario. Sirve
 * tambi�n para encargarlos para poder recogerlos m�s tarde en la tienda f�sica
 * de Software Sports.
 */
public class PanelMainCarrito extends AbstractPanelMain<Producto> {

	private static final long serialVersionUID = 1L;
	private JLabel numeroProductosLabel;
	private JLabel precioTotalLabel;

	/**
	 * {@link BuscarSwingWorker} que busca entre los productos del carrito del
	 * usuario.
	 */
	private class BuscarCarritoWorker extends BuscarSwingWorker {
		@Override
		protected Void doInBackground() throws Exception {
			removeReportablePanels();
			
			for(Producto p : carrito.getMapaProductos().keySet()) {
				publish(new ProductoDataPanel(owner, controlador, p, EnumModoPanelProductos.CARRITO, carrito));
			}
			
			return null;
		}
	}
	
	/**
	 * Clase panel lateral de compra, que sustituye al panel de búsqueda del modo Productos y Tienda.
	 * En esta clase aparece un resumen de compra y un botón para finalizarla, es decir, encargar el pedido.
	 */
	class PanelLateralCompra extends JScrollPane {

		private static final long serialVersionUID = 1L;
		private final Color bgColor = Color.ORANGE;
		private final Color fgColor = Color.BLACK;
		private JPanel lateralAuxPanel;
		private JButton comprar;
		
		PanelLateralCompra() {
			initGUI();
		}
		
		private void initGUI() {
			JPanel lateralPanel = new JPanel();
			lateralPanel.setBorder(null);
			lateralPanel.setBackground(bgColor);
			lateralPanel.setLayout(new BorderLayout(0, 0));
			lateralPanel.add(Box.createHorizontalStrut(25), BorderLayout.EAST);
			lateralPanel.add(Box.createHorizontalStrut(15), BorderLayout.WEST);
			lateralPanel.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
			lateralPanel.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);

			this.setViewportView(lateralPanel);
			this.setViewportBorder(null);
			this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			
			lateralAuxPanel = new JPanel();
			lateralAuxPanel.setBackground(lateralPanel.getBackground());

			lateralAuxPanel.setLayout(new BoxLayout(lateralAuxPanel, BoxLayout.Y_AXIS));
			lateralPanel.add(lateralAuxPanel, BorderLayout.CENTER);

			JLabel lblTitulo = new JLabel("Resumen de compra");
			lblTitulo.setForeground(fgColor);
			lblTitulo.setBackground(bgColor);	
			lateralAuxPanel.add(lblTitulo);
			
			numeroProductosLabel = new JLabel("Número de productos: 0");
			precioTotalLabel = new JLabel("Total: 0.0€");
			lateralAuxPanel.add(Box.createVerticalStrut(20));
			lateralAuxPanel.add(numeroProductosLabel);
			lateralAuxPanel.add(Box.createVerticalStrut(20));
			lateralAuxPanel.add(precioTotalLabel);
			
			lateralAuxPanel.add(Box.createVerticalStrut(20));
			Component glue = Box.createVerticalGlue();
			lateralAuxPanel.add(glue);

			comprar = new JButton("Comprar");
			comprar.setBackground(Color.WHITE);
			comprar.addActionListener(a -> finalizarCompra());
			lateralAuxPanel.add(comprar);
			
		}
		
	}

	/**
	 * 
	 */
	private Carrito carrito;
	private ControladorProductos controlador;

	/**
	 * Crea el panel para el carrito del usuario.
	 * 
	 * @param owner Ventana principal {@link MainWindow}.
	 * @param ctrl  Controlador del m�dulo productos.
	 */
	public PanelMainCarrito(MainWindow owner, ControladorProductos ctrl) {
		super(owner, "Carrito");
		this.carrito = owner.getUsuario().getCarrito();
		this.controlador = ctrl;
	}

	/**
	 * Actualiza el panel principal con los {@link ProductoDataPanel} de los
	 * productos del carrito del usuario y el resumen de compra.
	 */
	public void actualizar() {
		buscarObjetosYCrearPaneles();
		actualizarResumenCompra();
	}

	/**
	 * No usa PanelBusqueda de AbstractPanelMain, as� que devuelve 'null'.
	 */
	@Override
	protected LinkedHashMap<String, JComponent> getComponentesBusqueda() {
		return null;
	}

	@Override
	protected AbstractPanelMain<Producto>.BuscarSwingWorker getNewSwingWorker() {
		return new BuscarCarritoWorker();
	}

	protected JScrollPane getPanelLateral() {
		return new PanelLateralCompra();
	}
	
	/**
	 * Muestra una ventana cuando el usuario pulsa el botón "comprar". 
	 * 		- Si todo ha salido bien, aparece un mensaje de confirmación.
	 * 		- Si ha ocurrido un error, se informa al usuario.
	 * 
	 */
	private void finalizarCompra() {
		if(controlador.comprar(carrito)) {
			JOptionPane.showMessageDialog(this, "Su pedido ha sido encargado con exito. Podrá recogerlo en tienda en 2h.", "Confirmación de pedido",
					JOptionPane.INFORMATION_MESSAGE);
			actualizar();
		}
		
		else {
			JOptionPane.showMessageDialog(this, "Su carrito está vacío. Anyada algo para realizar el pedido.", "Error de carrito",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Actualiza las etiquetas del resumen de compra.
	 */
	private void actualizarResumenCompra() {
		numeroProductosLabel.setText("Número de productos: " + carrito.getNumProductos());
		precioTotalLabel.setText("Total: " + carrito.getPrecioTotal() + "€");
	}

}
