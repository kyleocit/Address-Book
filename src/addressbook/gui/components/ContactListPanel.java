package addressbook.gui.components;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import addressbook.ABPerson;
import addressbook.gui.StyleConstants;

/**
 * UML Diagram
 *
 * --------------------------------------------------
 *                 ContactListPanel
 * --------------------------------------------------
 *  -serialVersionUID: long
 *  #list: JList
 *  #scrollpane: JScrollPane
 * --------------------------------------------------
 *  +ContactListPanel()
 *  +componentResized(ComponentEvent): void
 *  +componentMoved(ComponentEvent): void
 *  +componentShown(ComponentEvent): void
 *  +componentHidden(ComponentEvent): void
 * --------------------------------------------------
 */

/**
 * Creates a new contact list panel for displaying a list of contacts
 * from an AddressBook. The sub-components of this panel are dynamic and
 * will automatically expand or contract to fit this panels dimensions.
 * <p>
 * To clear all entries from this list, simply call the clear() method.
 *
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class ContactListPanel extends JPanel implements ComponentListener
{
	/**
	 * Explicitly set class version unique id to prevent serialization errors.
	 * 
	 * @since 1.1
	 */
	private static final long serialVersionUID = 2586701740681006853L;

	/**
	 * Stores the current list of contacts to be displayed.
	 * 
	 * @since 1.1
	 */
	protected JList<ABPerson> list;

	/**
	 * Stores the scrollpane for the list component.
	 * 
	 * @since 1.1
	 */
	protected JScrollPane scrollpane;

	/**
	 * Creates a new contact list panel to display a list of contacts and
	 * allow the user to select one to be shown in a contact information
	 * panel. All sub-components are created and setup in this constructor.
	 * 
	 * @since 1.1
	 */
	public ContactListPanel()
	{
		// setup list component
		list = new JList<ABPerson>();
		list.setBackground(StyleConstants.BACKGROUND_COLOR);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// setup scroll pane for list component
		scrollpane = new JScrollPane();
		scrollpane.getViewport().setView(list);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// setup panel properties
		this.add(scrollpane);
		this.addComponentListener(this);
		this.setLayout(null);
	}

	/**
	 * Resizes all sub-components to fit this panel whenever it is resized.
	 * 
	 * @param e low-level event which indicates component has changed size
	 * @since 1.1
	 */
	@Override public void componentResized(ComponentEvent e)
	{
		Dimension d = this.getSize();
		scrollpane.setBounds(0, 0, d.width, d.height);
	}

	//---
	// Unused Listener Methods
	@Override public void componentMoved(ComponentEvent e) {}
	@Override public void componentShown(ComponentEvent e) {}
	@Override public void componentHidden(ComponentEvent e) {}
	//---
}