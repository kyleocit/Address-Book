package addressbook.gui.components;

import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import addressbook.filters.ImageFilter;
import addressbook.gui.StyleConstants;

/**
 * UML Diagram
 *
 * --------------------------------------------------
 *               ContactPicturePanel
 * --------------------------------------------------
 *  -serialVersionUID: long
 *  #NO_PICTURE: ImageIcon
 *  #picture: JLabel
 *  #choosePicture: JButton
 *  #chooser: JFileChooser
 * --------------------------------------------------
 *  +ContactPicturePanel()
 *  #resizeImage(ImageIcon): ImageIcon
 *  +componentResized(ComponentEvent): void
 *  +componentMoved(ComponentEvent): void
 *  +componentShown(ComponentEvent): void
 *  +componentHidden(ComponentEvent): void
 * --------------------------------------------------
 */

/**
 * Creates a new contact picture panel for displaying a contacts
 * picture. The sub-components of this panel are dynamic and will
 * automatically expand or contract to fit this panels dimensions.
 * <p>
 * Any image file selected will be automatically resized to fit the
 * panels dimensions.
 * <p>
 * To remove the selected picture call the clear() method.
 *
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class ContactPicturePanel extends JPanel implements ComponentListener
{
	/**
	 * Explicitly set class version unique id to prevent serialization errors.
	 * 
	 * @since 1.1
	 */
	private static final long serialVersionUID = 7612070192162548663L;

	/**
	 * The ImageIcon of the picture to use when no picture has been set.
	 * 
	 * @since 1.1
	 */
	protected static final ImageIcon NO_PICTURE = new ImageIcon(ContactPicturePanel.class.getResource(".").getPath().replaceAll("%20", " ") + "cpp_media/nopicture.png");
	
	/**
	 * The label used to display the selected picture.
	 * 
	 * @since 1.1
	 */
	protected JLabel picture;

	/**
	 * Opens the chooser dialog menu allowing the user to select an
	 * image file to be used as the contact's picture.
	 * 
	 * @since 1.1
	 */
	protected JButton choosePicture;

	/**
	 * The selection dialog that allows the user to select a new
	 * picture for the current contact. Only image files will be
	 * shown in the dialog as options.
	 * 
	 * @since 1.1
	 */
	protected JFileChooser chooser;

	/**
	 * Creates a new contact picture panel for displaying a contact's
	 * picture and allowing the user to choose a new one. Sets up all
	 * necessary components, including the dialog selection box.
	 * 
	 * @since 1.1
	 */
	public ContactPicturePanel()
	{
		// setup picture chooser dialog box
		chooser = new JFileChooser();
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setApproveButtonText("Select");
		chooser.setBackground(StyleConstants.BACKGROUND_COLOR);
		chooser.setDialogTitle("Select a new contact picture");
		chooser.setFileFilter(new ImageFilter());
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);

		// setup picture label
		picture = new JLabel(NO_PICTURE);
		picture.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, StyleConstants.CI_BORDER_COLOR));
		this.add(picture);

		// setup choose picture button
		choosePicture = new JButton("Change Picture");
		choosePicture.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, StyleConstants.CI_BORDER_COLOR));
		this.add(choosePicture);

		// setup panel properties
		this.addComponentListener(this);
		this.setLayout(null);
	}

	/**
	 * Resizes the specified ImageIcon so that it will fit, exactly, into the
	 * picture label.
	 * 
	 * @param i the ImageIcon to resize
	 * @return a resized version of the specified ImageIcon
	 * @since 1.1
	 */
	protected ImageIcon resizeImage(ImageIcon i)
	{
		if (picture == null || picture.getWidth() <= 0 || picture.getHeight() <= 0)
			return i;
		return new ImageIcon(i.getImage().getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_SMOOTH));
	}

	/**
	 * Resizes all sub-components to fit this panel whenever it is resized.
	 * 
	 * @param e low-level event which indicates component has changed size
	 * @since 1.1
	 */
	@Override public void componentResized(ComponentEvent e)
	{
		picture.setBounds(0, 0, this.getWidth(), this.getHeight() - 20);
		choosePicture.setBounds(0, this.getHeight() - 20, this.getWidth(), 20);

		// TODO i need to find a better place to do this, there is no reason to do it on each resize
		picture.setIcon(resizeImage((ImageIcon)picture.getIcon()));
	}

	//---
	// Unused Listener Methods
	@Override public void componentMoved(ComponentEvent e) {}
	@Override public void componentHidden(ComponentEvent e) {}
	@Override public void componentShown(ComponentEvent e) {}
	//---
}