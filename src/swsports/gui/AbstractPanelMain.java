package swsports.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import swsports.modelo.Reportable;

/**
 * Clase abstracta creada para mantener una interfaz uniforme para los paneles
 * principales de cada módulo.
 * 
 * Las subclases deben de asegurarse de que los componentes que se quieran
 * añadir a la GUI se inicien en el método {@link #getComponentesBusqueda()} y
 * que {@link #getNewSwingWorker()} devuelva un nuevo objeto que sea subclase de
 * {@link BuscarSwingWorker} (se recomienda que la implementación sea una clase
 * interna por claridad del código).
 * 
 * @param <T> Clase que implemente {@link Reportable}.
 */
public abstract class AbstractPanelMain<T extends Reportable> extends JPanel {

	/**
	 * Implementación de {@link SwingWorker} que busca usuarios según unos criterios
	 * de búsqueda y crea paneles con sus datos. Durante la ejecución de
	 * {@link #doInBackground()} se deshabilita el botón de búsqueda del panel
	 * izquierdo.
	 */
	protected abstract class BuscarSwingWorker extends SwingWorker<Void, DataPanel<T>> {

		/**
		 * Se debe deshabilitar {@link AbstractPanelMain#searchButton} al comenzar el
		 * método, leer los datos de los componentes guardados en
		 * {@link AbstractPanelMain#mapaComponentes}, llamar al controlador para buscar
		 * los objetos {@link Reportable}, crear paneles del tipo {@link DataPanel}, y
		 * llamar a {@link #publish(Object...)} con ellos.
		 */
		@Override
		protected abstract Void doInBackground() throws Exception;

		@Override
		protected void done() {
			worker = null;
			/*
			 * Comprobamos si son 'null' porque las subclases de AbstractPanelMain pueden
			 * sobrescribir los paneles
			 */
			if (searchButton != null)
				searchButton.setEnabled(true);
			if (mainObjectsAuxPanel != null)
				mainObjectsAuxPanel.revalidate();
		}

		@Override
		protected void process(List<DataPanel<T>> chunks) {
			for (DataPanel<T> panel : chunks) {
				mainObjectsAuxPanel.add(Box.createVerticalStrut(5));
				mainObjectsAuxPanel.add(panel);
				mainObjectsAuxPanel.add(Box.createVerticalStrut(5));
			}
		}
	}

	/**
	 * Panel donde aparecerán los datos de los objetos de tipo {@link Reportable}
	 * que se hayan buscado.
	 */
	private class DefaultPanelPrincipal extends JScrollPane {
		private static final long serialVersionUID = 1L;
		private final Color bgColor1 = SystemColor.textHighlight;
		private final Color bgColor2 = Color.WHITE;
		private final Color fgColor = Color.BLACK;
		private final String cabecera;

		/**
		 * Crea un PanelPrincipal a partir de un {@link String} que sirve como cabecera
		 * o título del panel.
		 * 
		 * @param cabecera Texto para usar como cabecera del panel.
		 */
		DefaultPanelPrincipal(String cabecera) {
			this.cabecera = cabecera;
			initGUI();
		}

		/**
		 * Crea la interfaz gráfica del panel.
		 */
		private void initGUI() {
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout(0, 0));
			this.setViewportView(mainPanel);
			this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

			JPanel mainUpPanel = new JPanel();
			mainUpPanel.setBackground(bgColor2);
			mainPanel.add(mainUpPanel, BorderLayout.NORTH);
			mainUpPanel.setLayout(new BorderLayout(0, 0));

			Component verticalStrut_2 = Box.createVerticalStrut(5);
			mainUpPanel.add(verticalStrut_2, BorderLayout.NORTH);

			JLabel lblNewLabel_6 = new JLabel(cabecera);
			lblNewLabel_6.setForeground(fgColor);
			lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
			mainUpPanel.add(lblNewLabel_6, BorderLayout.CENTER);

			JPanel mainUpAuxPanel = new JPanel();
			mainUpAuxPanel.setBackground(mainUpPanel.getBackground());
			mainUpPanel.add(mainUpAuxPanel, BorderLayout.SOUTH);
			mainUpAuxPanel.setLayout(new BorderLayout(0, 0));

			Component verticalStrut_3 = Box.createVerticalStrut(5);
			mainUpAuxPanel.add(verticalStrut_3, BorderLayout.NORTH);

			JSeparator separator = new JSeparator();
			mainUpAuxPanel.add(separator, BorderLayout.SOUTH);

			Component horizontalStrut_2 = Box.createHorizontalStrut(150);
			mainUpPanel.add(horizontalStrut_2, BorderLayout.WEST);

			Component horizontalStrut_3 = Box.createHorizontalStrut(150);
			mainUpPanel.add(horizontalStrut_3, BorderLayout.EAST);

			JPanel mainUsersPanel = new JPanel();
			mainUsersPanel.setLayout(new BorderLayout());
			mainUsersPanel.setBackground(bgColor1);
			mainPanel.add(mainUsersPanel, BorderLayout.CENTER);

			mainObjectsAuxPanel = new JPanel();
			mainUsersPanel.add(mainObjectsAuxPanel, BorderLayout.CENTER);
			mainObjectsAuxPanel.setLayout(new BoxLayout(mainObjectsAuxPanel, BoxLayout.Y_AXIS));
			mainObjectsAuxPanel.setBackground(mainUsersPanel.getBackground());

			Component horizontalStrut_4 = Box.createHorizontalStrut(5);
			mainUsersPanel.add(horizontalStrut_4, BorderLayout.EAST);

			Component horizontalStrut_5 = Box.createHorizontalStrut(5);
			mainUsersPanel.add(horizontalStrut_5, BorderLayout.WEST);
		}
	}

	/**
	 * Subpanel izquierdo en el que se introducen los parámetros de búsqueda. Los
	 * campos que se dejen vacíos deberían ignorarse en las subclases de
	 * {@link AbstractPanelMain}.
	 */
	private class PanelBusqueda extends JScrollPane {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final Color bgColor = Color.ORANGE;
		private final Color fgColor = Color.BLACK;
		private JPanel lateralAuxPanel;

		private PanelBusqueda() {
			initGUI();
		}

		/**
		 * Añade una serie de componentes {@link JComponent} guardados en un mapa
		 * {@link LinkedHashMap} intercalados con objetos {@link JLabel} creados a
		 * partir de las claves de dicho mapa. Añade finalmente el botón de búsqueda.
		 * 
		 * @param comps Mapa con los componentes y su {@link String} correspondiente con
		 *              el que se crearán sus respectivos {@link JLabel}.
		 */
		private void addComponents(LinkedHashMap<String, JComponent> comps) {
			for (Map.Entry<String, JComponent> entry : comps.entrySet()) {
				addVerticalStrut(5);
				JLabel label = createLabel(entry.getKey());
				lateralAuxPanel.add(label);
				addVerticalStrut(2);
				JComponent comp = entry.getValue();
				lateralAuxPanel.add(comp);
			}
			addVerticalStrut(20);
			Component glue = Box.createVerticalGlue();
			lateralAuxPanel.add(glue);

			searchButton = new JButton("Buscar");
			searchButton.setBackground(Color.WHITE);
			searchButton.addActionListener(a -> buscarObjetosYCrearPaneles());
			lateralAuxPanel.add(searchButton);
		}

		/**
		 * Añade una separación vertical de un número determinado de píxeles.
		 * 
		 * @param x Número de píxeles de separación.
		 */
		private void addVerticalStrut(int x) {
			lateralAuxPanel.add(Box.createVerticalStrut(x));
		}

		/**
		 * Crea un {@link JLabel} a partir de un {@link String} de modo que tenga unos
		 * colores acordes al tema del panel (fondo naranja y letra en negro).
		 * 
		 * @param name Parámetro para construir el {@link JLabel}.
		 * @return Un {@link JLabel} de acuerdo al tema del panel;
		 */
		private JLabel createLabel(String name) {
			JLabel label = new JLabel(name);
			label.setForeground(fgColor);
			label.setBackground(bgColor);
			label.setFont(new Font("Dialog", Font.PLAIN, 12));
			return label;
		}

		/**
		 * Crea la interfaz gráfica del panel.
		 */
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

			JLabel lblTitulo = new JLabel("Opciones de b\u00FAsqueda");
			lblTitulo.setForeground(fgColor);
			lblTitulo.setBackground(bgColor);
			lateralAuxPanel.add(lblTitulo);
			addComponents(getComponentesBusqueda());
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TXT_FIELD_WIDTH = 500;
	private static final int TXT_FIELD_HEIGHT = 20;

	/**
	 * Crea un campo de texto de un tamaño máximo determinado
	 * ({@value #TXT_FIELD_HEIGHT} píxeles de alto, y {@value #TXT_FIELD_WIDTH}
	 * píxeles de largo).
	 * 
	 * @return Un {@link JTextField} con cierto tamaño máximo.
	 */
	protected static JTextField createTextField() {
		JTextField textField = new JTextField();
		textField.setMaximumSize(new Dimension(TXT_FIELD_WIDTH, TXT_FIELD_HEIGHT));
		return textField;
	}

	private BuscarSwingWorker worker;
	private JButton searchButton;
	private JPanel mainObjectsAuxPanel;
	protected MainWindow owner;
	protected List<T> objetos;

	/**
	 * Mapa ordenado con la clave siendo el nombre o identificador de un componente
	 * {@link JComponent}.
	 */
	protected LinkedHashMap<String, JComponent> mapaComponentes;

	/**
	 * Crea el panel. Recibe {@link MainWindow} y un nombre para el panel que en
	 * principio se usará para el panel principal.
	 * 
	 * @param owner Ventana principal de la aplicación.
	 */
	public AbstractPanelMain(MainWindow owner, String nombreModulo) {
		this.owner = owner;
		objetos = new LinkedList<>();
		setBorder(null);
		setLayout(new BorderLayout(0, 0));
		add(getPanelLateral(), BorderLayout.WEST);
		add(getPanelPrincipal(nombreModulo), BorderLayout.CENTER);
	}

	/**
	 * Invoca a una subclase de {@link SwingWorker} para que vaya buscando los
	 * usuarios que cumplan los requisitos especificados en el panel lateral y crea
	 * paneles con sus datos. Esto se hace en una hebra distina para evitar que la
	 * hebra de Swing se sobrecargue.
	 */
	protected final void buscarObjetosYCrearPaneles() {
		if (worker == null) {
			worker = getNewSwingWorker();
			worker.execute();
		}
	}

	/**
	 * Método donde se deben iniciar los componentes que se vayan a añadir al
	 * subpanel {@link PanelBusqueda} de {@link AbstractPanelMain}. Devuelve un mapa
	 * {@link LinkedHashMap} con pares de {@link String} que se usarán para crear
	 * {@link JLabel} y {@link JComponent}.
	 * 
	 * @return Mapa con pares de {@link String} y {@link JComponent}.
	 */
	protected abstract LinkedHashMap<String, JComponent> getComponentesBusqueda();

	/**
	 * Método que debe ser implementado y tiene que devolver la implementación de
	 * BuscarSwingWorker correspondiente al tipo T {@link Reportable}.
	 * 
	 * @return Subclase de BuscarSwingWorker
	 */
	protected abstract BuscarSwingWorker getNewSwingWorker();

	/**
	 * Devuelve el subpanel lateral de este panel. Puede sobrescribirse si no se
	 * desea usar el panel por defecto({@link JScrollPane}).
	 * 
	 * @return Devuelve el subpanel lateral
	 */
	protected JScrollPane getPanelLateral() {
		return new PanelBusqueda();
	}

	/**
	 * Devuelve el subpanel principal con scroll ({@link JScrollPane}). Puede
	 * sobrescribirse por otras clases si es necesario usar un panel distinto al de
	 * por defecto ({@link DefaultPanelPrincipal}). Recibe un string que sirve como
	 * título de dicho subpanel.
	 * 
	 * @param nombreModulo En principio el título de del subpanel, aunque se puede
	 *                     omitir si se quiere al sobrescribir este método.
	 * @return Devuelve un {@link JScrollPane} que servirá como panel principal.
	 */
	protected JScrollPane getPanelPrincipal(String nombreModulo) {
		return new DefaultPanelPrincipal(nombreModulo);
	}

	/**
	 * Quita los paneles {@link DataPanel} del PanelPrincipal. Se usa para no añadir
	 * paneles duplicados cada vez que se busquen objetos.
	 */
	protected final void removeReportablePanels() {
		if (mainObjectsAuxPanel != null)
			mainObjectsAuxPanel.removeAll();
	}

	/**
	 * Habilita o deshabilita el botón de búsqueda.
	 * 
	 * @param b <code>true</code> para habilitarlo, <code>false</code> para
	 *          deshabilitarlo.
	 */
	protected final void setSearchButtonEnabled(boolean b) {
		searchButton.setEnabled(b);
	}

}
