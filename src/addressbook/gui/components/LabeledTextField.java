package addressbook.gui.components;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import addressbook.gui.StyleConstants;

/**
 * UML Diagram 
 *
 * --------------------------------------------------
 *                LabelledTextField
 * --------------------------------------------------
 *  #label: JLabel
 *  #field: JTextField
 * --------------------------------------------------
 *  +ContactInformationPanel()
 *  +componentResized(ComponentEvent): void
 *  +componentMoved(ComponentEvent): void
 *  +componentShown(ComponentEvent): void
 *  +componentHidden(ComponentEvent): void
 * --------------------------------------------------
 */

/**
 * Creates a new labeled text field panel. Despite it's name, this class is
 * not a descendant of JTextField, but rather a JPanel that overlays a
 * JTextField onto a JLabel. Some JTextField methods have been aliased to
 * allow you to edit the text fields contents.
 * <p>
 * Upon creating a new labeled text field you must pass a string with the
 * label text in it.
 *
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class LabeledTextField extends JPanel implements ComponentListener
{
	/**
	 * Explicitly set class version unique id to prevent serialization errors.
	 */
	private static final long serialVersionUID = -2779713275361859484L;

	/**
	 * Stores the label component.
	 * 
	 * @since 1.1
	 */
	protected JLabel label;

	/**
	 * Stores the text field component.
	 * 
	 * @since 1.1
	 */
	protected JTextField field;

	/**
	 * Creates a new instance of this class by setting up a JLabel containing
	 * the specified description behind a transparent JTextField for the user
	 * to enter input.
	 * 
	 * @param description the label description label text of this input field
	 * @since 1.1
	 */
	public LabeledTextField(String description)
	{
		// create and style label with default settings
		label = new JLabel(" " + description);
		label.setFont(StyleConstants.CI_LABEL_FONT);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setOpaque(false);

		// create and style field with default settings
		field = new JTextField();
		field.setBorder(null);
		//field.setEditable(false);
		field.setFont(StyleConstants.CI_INPUT_FONT);
		field.setOpaque(false);

		// add label and field to panel
		this.setBackground(StyleConstants.CI_BACKGROUND_COLOR);
		this.setLayout(null);
		this.add(label);
		this.add(field);
		this.addComponentListener(this);
	}

	/**
	 * Resizes all sub-components to fit this panel whenever it is resized.
	 * 
	 * @param e low-level event which indicates component has changed size
	 * @since 1.1
	 */
	@Override public void componentResized(ComponentEvent e)
	{
		label.setBounds(0, 0, this.getWidth(), this.getHeight());
		field.setBounds(2, 10, this.getWidth() - 4, this.getHeight() - 10);
	}

	//---
	// Unused Listener Methods
	@Override public void componentMoved(ComponentEvent e) {}
	@Override public void componentShown(ComponentEvent e) {}
	@Override public void componentHidden(ComponentEvent e) {}
	//---
}