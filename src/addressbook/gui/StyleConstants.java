package addressbook.gui;

import java.awt.Color;
import java.awt.Font;

/**
 * A static class that stores styling constants for the GUI such as colors
 * and fonts.
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class StyleConstants
{
	/**
	 * The background color for the main application panel.
	 * 
	 * @since 1.1
	 */
	public static final Color BACKGROUND_COLOR = new Color(200, 221, 242);

	/**
	 * The background color for the contact information panel.
	 * 
	 * @since 1.1
	 */
	public static final Color CI_BACKGROUND_COLOR = new Color(238, 238, 238);

	/**
	 * The border color for the contact information panel.
	 * 
	 * @since 1.1
	 */
	public static final Color CI_BORDER_COLOR = Color.GRAY;

	/**
	 * The font to use for the top tabbed pane.
	 * 
	 * @since 1.1
	 */
	public static final Font MODES_FONT = new Font("Arial", Font.BOLD, 12);

	/**
	 * The font to use for the alphabetical listings tabbed pane.
	 * 
	 * @since 1.1
	 */
	public static final Font LISTINGS_FONT = new Font("Tahoma", Font.BOLD, 12);

	/**
	 * The font to use for the input field labels in the contact information
	 * panel.
	 * 
	 * @since 1.1
	 */
	public static final Font CI_LABEL_FONT = new Font("Tahoma", Font.BOLD, 10);

	/**
	 * The font to use for the input field text in the contact information
	 * panel.
	 * 
	 * @since 1.1
	 */
	public static final Font CI_INPUT_FONT = new Font("Times New Roman", Font.PLAIN, 14);
}