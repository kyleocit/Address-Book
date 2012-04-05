package addressbook.gui.components;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import addressbook.gui.StyleConstants;
import addressbook.gui.purifiers.Purifier;

/**
 * UML Diagram 
 *
 * --------------------------------------------------
 *                  LabeledTextArea
 * --------------------------------------------------
 *  -serialVersionUID: long
 *  #label: JLabel
 *  #field: JTextArea
 *  #scrollpane: JScrollPane
 * --------------------------------------------------
 *  +LabeledTextArea(String)
 *  +addPurifier(Purifier): void
 *  +componentResized(ComponentEvent): void
 *  +componentMoved(ComponentEvent): void
 *  +componentShown(ComponentEvent): void
 *  +componentHidden(ComponentEvent): void
 * --------------------------------------------------
 */

/**
 * Creates a new labeled text area panel. Despite it's name, this class is
 * not a descendant of JTextArea, but rather a JPanel that places a header
 * label above a JTextArea. Some JTextArea methods have been aliased to
 * allow you to edit the text areas contents.
 * <p>
 * Upon creating a new labeled text area you must pass a string with the
 * label text in it.
 *
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class LabeledTextArea extends JPanel implements ComponentListener
{
	/**
	 * Explicitly set class version unique id to prevent serialization errors.
	 * 
	 * @since 1.1
	 */
	private static final long serialVersionUID = -3408612708471845394L;

	/**
	 * Stores the label component.
	 * 
	 * @since 1.1
	 */
	protected JLabel label;

	/**
	 * Stores the text area component.
	 * 
	 * @since 1.1
	 */
	protected JTextArea field;

	/**
	 * Stores the scroll pane for the text area component.
	 * 
	 * @since 1.1
	 */
	protected JScrollPane scrollpane;

	/**
	 * Creates a new instance of this class by setting up a JLabel containing
	 * the specified description above a JTextArea for the user to enter input.
	 * 
	 * @param description the label description label text of this input field
	 * @since 1.1
	 */
	public LabeledTextArea(String description)
	{
		// create and style label with default settings
		label = new JLabel(description);
		label.setBackground(StyleConstants.BACKGROUND_COLOR);
		label.setFont(StyleConstants.CI_LABEL_FONT);
		label.setForeground(Color.BLACK);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setOpaque(true);
		label.setVerticalAlignment(SwingConstants.CENTER);

		// create and style field with default settings
		field = new JTextArea();
		field.setFont(StyleConstants.CI_INPUT_FONT);
		field.setLineWrap(true);
		field.setOpaque(false);
		field.setTabSize(4);
		field.setWrapStyleWord(true);

		// setup scroll pane for field to prevent text overflow
		scrollpane = new JScrollPane();
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setViewportView(field);

		// add label and field to panel
		this.setBackground(StyleConstants.CI_BACKGROUND_COLOR);
		this.setLayout(null);
		this.add(label);
		this.add(scrollpane);
		this.addComponentListener(this);
	}

	/**
	 * Adds a purification filter to the text area. The filter watches the
	 * text area and filters it removing disallowed characters.
	 * 
	 * @param purifier a purifier that implements the key listener interface
	 * @since 1.1
	 */
	public void addPurifier(Purifier purifier)
	{
		field.addKeyListener(purifier);
	}

	/**
	 * Resizes all sub-components to fit this panel whenever it is resized.
	 * 
	 * @param e low-level event which indicates component has changed size
	 * @since 1.1
	 */
	@Override public void componentResized(ComponentEvent e)
	{
		label.setBounds(0, 0, this.getWidth(), 20);
		scrollpane.setBounds(0, 20, this.getWidth(), this.getHeight() - 20);
	}

	//---
	// Unused Listener Methods
	@Override public void componentMoved(ComponentEvent e) {}
	@Override public void componentShown(ComponentEvent e) {}
	@Override public void componentHidden(ComponentEvent e) {}
	//---
}