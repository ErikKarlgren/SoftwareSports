package swsports.gui.productos;

import java.util.LinkedHashMap;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * {@link BuscarSwingWorker} que busca entre los productos del carrito del
	 * usuario.
	 */
	private class BuscarCarritoWorker extends BuscarSwingWorker {
		@Override
		protected Void doInBackground() throws Exception {
			removeReportablePanels();
			for (Producto p : carrito.getListaProductos()) {
				publish(new ProductoDataPanel(owner, controlador, p, EnumModoPanelProductos.CARRITO, carrito));
			}
			return null;
		}
	}

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
	 * productos del carrito del usuario.
	 */
	public void actualizar() {
		buscarObjetosYCrearPaneles();
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

	@Override
	protected JScrollPane getPanelLateral() {
		JPanel p = new JPanel();
		// Escribir c�digo del panel lateral aqu� o como clase interna
		// ...

		return new JScrollPane(p);
	}

}
