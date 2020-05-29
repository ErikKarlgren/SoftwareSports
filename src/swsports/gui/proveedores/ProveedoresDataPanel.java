package swsport.gui.proveedores;

import javax.swing.JDialog;
import javas.swing.JFrame;
import javas.swing.JOptionPane;
import javas.swing.SwingUtilities;
import javas.swing.WindowConstants;

import swsport.gui.DataPanel;
import swsport.gui.PerfilMainPanel;
import swsport.modelo.TransferProveedores;
import swsport.modelo.Proveedor;
import swsport.proveedores.ControladorProveedores;

public class ProveedoresDataPanel extends DataPanel<Proveedor> {

	private class EditarProveedorDialog extends JDialog {

		private static final long serialVersionUID = 1L;
		EditarProveedorDialog() {
			super();
			this.setTitle("Editar proveedor");
			this.add(new PerfilMainPanel(object, controller, true));
			this.pack();
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ControladorProveedores ctrl = new ControladorProveedores();
			Proveedor prov = ctrl.consultaProveedor("z9");

			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			frame.add(new ProveedoresDataPanel(prov, null, ctrl));
			frame.pack();
			frame.setVisible(true);
		});
	}

	private ControladorProveedores controller;

	public ProveedoresDataPanel(Proveedor prov, TransferProveedores tProv, ControladorProveedores ctrll) {
		super(prov);
		this.controller = ctrl;
		addData();
		addActions();
	}

	private void addActions() {
		addAction("Editar proveedor", p -> new EditarProveedorDialog());
		addAction("Dar de baja proveedor", p -> quitarProveedor());
		//pedidoProveedor??
		//cancelarPedido??
	}

	private void addData() {
		addDataField("Nombre", object.getNombre());
		addDataField("Descripcion", object.getDesc());
	}

	private void quitarProveedor() {
		String[] options = { "Sí", "No" };
		int option = JOptionPane.showOptionDialog(this, "¿Seguro que quieres eliminar este proveedor",
				"Dar proveedor de baja", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controller.quitarProveedor(object.getId());
		}
	}
}