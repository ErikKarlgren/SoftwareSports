package swsports.gui;

import javax.swing.JPanel;
import swsports.modelo.Reportable;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.SystemColor;

/**
 * Panel que permite mostrar la información de un objeto que implemente
 * {@link Reportable} y realizar acciones sobre el mismo.
 * 
 * @param <T> Tipo del objeto del cual queremos mostrar su información. Debe
 *            implementar {@link Reportable}.
 */
public abstract class DataPanel<T extends Reportable> extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int MARGIN_SIZE = 5;
	/**
	 * Objeto del que queremos representar su información. Debe heredar de
	 * {@link Reportable}.
	 */
	protected final T object;
	/**
	 * Subpanel inferior. Contiene los botones para realizar acciones sobre el
	 * objeto {@link Reportable} guardado.
	 */
	private JPanel actionsPanel;

	/**
	 * Subpanel superior o principal. Contiene los datos que queremos mostrar del
	 * objeto {@link Reportable} guardado.
	 */
	private JPanel mainPanel;
	/**
	 * Número de datos que se han añadido al subpanel principal. Se usa para poder
	 * colocar los nuevos datos en sus filas correspondientes.
	 */
	private int dataCount;
	private Component bottomMargin;
	/**
	 * Ventana principal de la aplicación. Se puede usar para interactuar con otros
	 * componentes gráficos de la aplicación.
	 */
	protected MainWindow owner;

	/**
	 * Crea el panel dado un objeto {@link Reportable} y la ventana principal de la
	 * aplicación.
	 * 
	 * @param owner Ventana principal.
	 * @param obj   Objeto del que queremos mostrar sus datos.
	 */
	public DataPanel(MainWindow owner, T obj) {
		this.owner = Objects.requireNonNull(owner);
		this.object = Objects.requireNonNull(obj);
		dataCount = 0;
		initGUI();
		addDataField("ID", object.getId());
	}

	/**
	 * Añade un botón al subpanel inferior de acciones.
	 * 
	 * @param name   Nombre del botón.
	 * @param action {@link ActionListener} del botón.
	 */
	protected final void addAction(String name, ActionListener action) {
		JButton button = new JButton(name);
		button.addActionListener(action);

		double factor = 0.8;
		double preferredWidth = button.getPreferredSize().getWidth();
		double preferredHeight = button.getPreferredSize().getHeight();
		button.setMaximumSize(new Dimension((int) (preferredWidth * 1.0), (int) (preferredHeight * factor)));
		button.setPreferredSize(button.getMaximumSize());

		button.setBackground(Color.WHITE);
		actionsPanel.add(button);
	}

	private void addBottomMargin() {
		bottomMargin = Box.createVerticalStrut(MARGIN_SIZE / 2);
		GridBagConstraints gbcBottomMargin = new GridBagConstraints();
		gbcBottomMargin.gridx = 1;
		gbcBottomMargin.gridy = dataCount + 1;
		gbcBottomMargin.weighty = 1;
		mainPanel.add(bottomMargin, gbcBottomMargin);
	}

	/**
	 * Añade dos {@link JLabel} al subpanel principal para representar un dato del
	 * objeto del que queremos mostrar su información. Ambos se colocarán a la misma
	 * altura en el subpanel.
	 * 
	 * @param dataName  Nombre del dato que queremos mostrar.
	 * @param dataValue Valor del dato que queremos mostrar.
	 */
	protected final void addDataField(String dataName, String dataValue) {
		mainPanel.remove(bottomMargin);

		JLabel dataNameLabel = new JLabel(dataName);
		GridBagConstraints gbcNameLabel = new GridBagConstraints();
		gbcNameLabel.fill = GridBagConstraints.VERTICAL;
		gbcNameLabel.anchor = GridBagConstraints.WEST;
		gbcNameLabel.insets = new Insets(0, 5, 5, 50);
		gbcNameLabel.gridx = 0;
		gbcNameLabel.gridy = dataCount;
		gbcNameLabel.weighty = 0;
		mainPanel.add(dataNameLabel, gbcNameLabel);

		JLabel dataValueLabel = new JLabel(dataValue);
		dataValueLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbcValueLabel = new GridBagConstraints();
		gbcValueLabel.insets = new Insets(0, 0, 5, 0);
		gbcValueLabel.anchor = GridBagConstraints.EAST;
		gbcValueLabel.gridx = 1;
		gbcValueLabel.gridy = dataCount;
		gbcValueLabel.weighty = 0;
		mainPanel.add(dataValueLabel, gbcValueLabel);

		addBottomMargin();
		dataCount++;
	}

	/**
	 * Crea la interfaz gráfica del panel.
	 */
	private void initGUI() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setLayout(new BorderLayout(0, 0));

		Component verticalStrut = Box.createVerticalStrut(MARGIN_SIZE);
		add(verticalStrut, BorderLayout.NORTH);

		Component horizontalStrut = Box.createHorizontalStrut(MARGIN_SIZE);
		add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(MARGIN_SIZE);
		add(horizontalStrut_1, BorderLayout.EAST);

		mainPanel = new JPanel();
		mainPanel.setBackground(this.getBackground());
		add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_mainPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_mainPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		mainPanel.setLayout(gbl_mainPanel);
		addBottomMargin();

		actionsPanel = new JPanel();
		actionsPanel.setBackground(SystemColor.activeCaption);
		actionsPanel.setBorder(new LineBorder(Color.WHITE, 1, true));
		add(actionsPanel, BorderLayout.SOUTH);
	}
}
