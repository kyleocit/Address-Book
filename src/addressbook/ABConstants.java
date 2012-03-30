package addressbook;
/*
 * @(#)ABConstants.java	1.0 10/10/21
 *
 * Written by Kyle Campbell.
 *
 * UML Diagram
 * --------------------------------------------------
 *                AddressBookListener
 * --------------------------------------------------
 *  +backgroundColor: Color
 *  +rootPath: String
 *  +imagesPath: String
 *  +userDatabase: String
 *  +defaultPicture: ImageIcon
 *  +infoFont: Font
 * --------------------------------------------------
 *  (no methods)
 * --------------------------------------------------
 * 
 * Change Log
 * v1.0
 *  - initial release
 */

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

public final class ABConstants
{
	public static final Color primaryColor = new Color(200, 221, 242);
	
	public static final Color backgroundColor = new Color(200, 221, 242);
	public static final String rootPath = ABApplication.class.getResource(".").getPath().replaceAll("%20", " ");
	public static final String imagesPath = rootPath + "images/";
	public static final String userDatabase = System.getProperty("user.home") + "/kjccsc/contacts.abd";
	public static final ImageIcon defaultPicture = new ImageIcon(imagesPath + "nopicture.png");
	public static final Font infoFont = new Font("Times New Roman", Font.PLAIN, 14);
}