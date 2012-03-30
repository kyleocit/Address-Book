package addressbook.gui.components;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;

import addressbook.filters.ImageFilter;
import addressbook.gui.StyleConstants;

public class ContactPicturePanel extends JPanel implements ComponentListener
{
	protected static final ImageIcon NO_PICTURE = new ImageIcon(ContactPicturePanel.class.getResource(".").getPath().replaceAll("%20", " ") + "cpp_media/nopicture.png");
	protected JLabel picture;
	protected JButton choosePicture;
	protected JFileChooser chooser;

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

	protected ImageIcon resizeImage(ImageIcon i)
	{
		if (picture == null || picture.getWidth() <= 0 || picture.getHeight() <= 0)
			return i;
		return new ImageIcon(i.getImage().getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_SMOOTH));
	}

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