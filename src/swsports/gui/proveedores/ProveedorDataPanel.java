package swsports.gui.proveedores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import swsports.gui.DataPanel;
import swsports.gui.MainWindow;
import swsports.gui.ProveedorMainPanel;
import swsports.modelo.Proveedor;
import swsports.modelo.TransferProveedor;
import swsports.modelo.TransferUsuario;
import swsports.modelo.Usuario;
import swsports.productos.ControladorProductos;
import swsports.proveedores.ControladorProveedores;
import swsports.usuarios.ControladorUsuario;

public class ProveedorDataPanel extends DataPanel<Proveedor> {

	private boolean hayPedido = false;
	private ControladorProveedores controlProv;
	private ControladorProductos controlProd;
	/**
	 * Diálogo que muestra el panel para editar el perfil de un usuario, incluyendo
	 * si concederle o revocarle privilegios de administrador.
	 */
	private class EditarProveedorDialog extends JDialog {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		EditarProveedorDialog(){
			super();
			this.setTitle("Editar proveedor");
			initGUI();
			this.pack();
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
			private void initGUI() {
				JPanel panelText = new JPanel();
				
				JLabel lblNewLabel_1 = new JLabel("ID");
				lblNewLabel_1.setForeground(Color.BLACK);
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 1;
				panelText.add(lblNewLabel_1);

				JTextField idPTextField = new JTextField();
				idPTextField.setEditable(false);
				GridBagConstraints gbc_idTextField = new GridBagConstraints();
				gbc_idTextField.fill = GridBagConstraints.BOTH;
				gbc_idTextField.anchor = GridBagConstraints.NORTHWEST;
				gbc_idTextField.insets = new Insets(0, 0, 5, 0);
				gbc_idTextField.gridx = 2;
				gbc_idTextField.gridy = 1;
				idPTextField.setColumns(10);
				panelText.add(idPTextField);
				
				JLabel lblNewLabel_2 = new JLabel("Nombre");
				lblNewLabel_2.setForeground(Color.BLACK);
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 0;
				gbc_lblNewLabel_2.gridy = 1;
				panelText.add(lblNewLabel_2);

				JTextField namePTextField = new JTextField();
				namePTextField.setEditable(false);
				GridBagConstraints gbc_nameTextField = new GridBagConstraints();
				gbc_nameTextField.fill = GridBagConstraints.BOTH;
				gbc_nameTextField.anchor = GridBagConstraints.NORTHWEST;
				gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
				gbc_nameTextField.gridx = 2;
				gbc_nameTextField.gridy = 1;
				namePTextField.setColumns(10);
				panelText.add(namePTextField);
				
				JLabel lblNewLabel_3 = new JLabel("Descripcion");
				lblNewLabel_3.setForeground(Color.BLACK);
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 0;
				gbc_lblNewLabel_3.gridy = 1;
				panelText.add(lblNewLabel_3);

				JTextField descPTextField = new JTextField();
				descPTextField.setEditable(false);
				GridBagConstraints gbc_descTextField = new GridBagConstraints();
				gbc_descTextField.fill = GridBagConstraints.BOTH;
				gbc_descTextField.anchor = GridBagConstraints.NORTHWEST;
				gbc_descTextField.insets = new Insets(0, 0, 5, 0);
				gbc_descTextField.gridx = 2;
				gbc_descTextField.gridy = 1;
				descPTextField.setColumns(10);
				panelText.add(descPTextField);
				
				this.add(panelText, BorderLayout.CENTER);
				
				
				JButton editar = new JButton("Editar");
				editar.addActionListener(new ActionListener() {
					TransferProveedor tProv = new TransferProveedor(idPTextField.getText(), namePTextField.getText(), descPTextField.getText(),
													controlProd.consultaProducto(object.getIdProducto()), object.getStock());
					@Override
					public void actionPerformed(ActionEvent arg0) {
						controlProv.editarProveedor(tProv);
					}
				});
				
				this.add(editar, BorderLayout.SOUTH);
			}
		}
	private class CrearPedidoDialog extends JDialog {
		private static final long serialVersionUID = 1L;
		private ProveedorDataPanel pdp;

		CrearPedidoDialog(ProveedorDataPanel proveedorDataPanel) {
				super();
				pdp = proveedorDataPanel;
				this.setTitle("Editar proveedor");
				initGUI();
				this.pack();
				this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				this.setVisible(true);
			}
				private void initGUI() {
					JPanel panelText = new JPanel();
					
					JLabel lblNewLabel_1 = new JLabel("ID Producto");
					lblNewLabel_1.setForeground(Color.BLACK);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
					GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
					gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
					gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
					gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_1.gridx = 0;
					gbc_lblNewLabel_1.gridy = 1;
					panelText.add(lblNewLabel_1);

					JTextField idTextField = new JTextField();
					idTextField.setEditable(false);
					GridBagConstraints gbc_idTextField = new GridBagConstraints();
					gbc_idTextField.fill = GridBagConstraints.BOTH;
					gbc_idTextField.anchor = GridBagConstraints.NORTHWEST;
					gbc_idTextField.insets = new Insets(0, 0, 5, 0);
					gbc_idTextField.gridx = 2;
					gbc_idTextField.gridy = 1;
					idTextField.setColumns(10);
					panelText.add(idTextField);
					
					JLabel lblNewLabel_2 = new JLabel("Cantidad");
					lblNewLabel_2.setForeground(Color.BLACK);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
					GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
					gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
					gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
					gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_2.gridx = 0;
					gbc_lblNewLabel_2.gridy = 1;
					panelText.add(lblNewLabel_2);

					JTextField cantidadTextField = new JTextField();
					cantidadTextField.setEditable(false);
					GridBagConstraints gbc_cantidadTextField = new GridBagConstraints();
					gbc_cantidadTextField.fill = GridBagConstraints.BOTH;
					gbc_cantidadTextField.anchor = GridBagConstraints.NORTHWEST;
					gbc_cantidadTextField.insets = new Insets(0, 0, 5, 0);
					gbc_cantidadTextField.gridx = 2;
					gbc_cantidadTextField.gridy = 1;
					cantidadTextField.setColumns(10);
					panelText.add(cantidadTextField);
					
					this.add(panelText, BorderLayout.CENTER);
					
					JButton pedido = new JButton("Hacer pedido");
					pedido.addActionListener(new ActionListener() {
						TransferProveedor tProv = new TransferProveedor(object.getId(), object.getNombre(), object.getDesc(), 
								controlProd.consultaProducto(idTextField.getText()), Integer.parseInt(cantidadTextField.getText()));
						@Override
						public void actionPerformed(ActionEvent arg0) {
							controlProv.pedidoProveedor(tProv);
							JOptionPane.showMessageDialog(pdp, "Has pagado con al proveedor con " + (Double.toString(object.getPrecio())));
						}
					});
					
					this.add(pedido, BorderLayout.SOUTH);
				}
		}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> {
//			ControladorProveedores ctrl = new ControladorProveedores();
//			Proveedor prov = ctrl.consultaProveedor("z9");
//
//			JFrame frame = new JFrame();
//			frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//			frame.add(new ProveedorDataPanel(owner, ctrl, wwwww, prov));
//			frame.pack();
//			frame.setVisible(true);
//		});
//	}



	/**
	 * Crea un panel con los datos de un {@link Usuario} y siguiendo las
	 * restricciones guardadas en un {@link TransferUsuario} (los atributos nulos
	 * implican que sus datos correspondientes no se mostrarán). El
	 * {@link ControladorUsuario} es necesario para poder realizar las acciones de
	 * editar el usuario o darlo de baja.
	 * 
	 * @param owner       Ventana principal del programa.
	 * @param ctrlProv    Controlador del m�dulo Proveedores.
	 * @param ctrlProd    Controlador del m�dulo Productos.
	 * @param usu         {@link Usuario} del que queremos mostrar sus datos.
	 * @param constraints Restricciones a la hora de mostrar o no los atributos de
	 *                    <code>usu</code>. Los atributos nulos de
	 *                    <code>constraints</code> implican que los atributos
	 *                    correspondientes de <code>usu</code> no se mostrarán. Si
	 *                    <code>constraints</code> es nulo se mostrarán todos los
	 *                    datos.
	 */
	public ProveedorDataPanel(MainWindow owner, ControladorProveedores ctrlProv, ControladorProductos ctrlProd,
			Proveedor prov) {
		super(owner, prov);
		this.controlProv = ctrlProv;
		addData();
		addActions();
	}

	/**
	 * Añade las acciones del panel: editar un usuario y darlo de baja.
	 */
	private void addActions() {
		addAction("Editar proveedor", a -> new EditarProveedorDialog());
		addAction("Hacer Pedido", a -> new CrearPedidoDialog(this));
		addAction("Recibir Pedido", a -> recibirPedido());
		addAction("Quitar Pedido", a -> eliminarPedido());
		addAction("Eliminar Proveedor", a -> darDeBaja());
	}
	
	private void recibirPedido() {
		controlProv.recibirPedido(object);	
	}

	private void eliminarPedido() {
		// TODO Auto-generated method stub
		controlProv.cancelarPedido(object);
	}

	/**
	 * Añade los campos del usuario al panel siguiendo los criterios marcados por
	 * un {@link TransferUsuario}.
	 */
	private void addData() {
		addDataField("Nombre", object.getNombre());
		addDataField("Descripcion", object.getDesc());
		if (hayPedido == true) {
			addDataField("Producto", object.getIdProducto());
			addDataField("Cantidad", Integer.toString(object.getStock()));
			addDataField("Precio Total", Double.toString(object.getPrecio()));
		}
	}

	/**
	 * Da de baja a un {@link Usuario} si el usuario de la aplicación confirma su
	 * decisión.
	 */
	private void darDeBaja() {
		String[] options = { "Sí", "No" };
		int option = JOptionPane.showOptionDialog(this, "¿Seguro que quieres eliminar a este proveedor?",
				"Eliminar proveedor", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[1]);
		if (option == JOptionPane.YES_OPTION) {
			controlProv.quitarProveedor(object);
		}
	}
}