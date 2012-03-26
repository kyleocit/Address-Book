/*
 * @(#)ImageFilter.java	1.0	10/10/25
 * 
 * Written by Kyle Campbell.
 *   
 * UML Diagram
 * --------------------------------------------------
 *                  ImageFilter
 * --------------------------------------------------
 *  -validExtensions: String[]
 * --------------------------------------------------
 *  +accept(File): boolean
 *  +getDescription(): String
 * --------------------------------------------------
 * 
 * Change Log
 * --------------------------------------------------
 *  v1.0
 *   - initial release
 * --------------------------------------------------
 */

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * The class ImageFilter is a FileFilter for JFileChooser intended to filter
 * non-image files from appearing in the JFileChooser dialog box. Any files with
 * a file extension not listed in validExtensions will be filtered out using the
 * {@link #accept(File)} method.
 * 
 * @see javax.swing.filechooser.FileFilter
 */
public class ImageFilter extends FileFilter
{	
	/**
	 * Stores a String array of valid image file extensions. Comparison of image
	 * extensions is not case sensitive.
	 */
	private final static String[] validExtensions = {
		"jpg", "jpeg",
		"tif", "tiff",
		"gif",
		"png"
	};

	/**
	 * Determines if the specified File is a valid image file.
	 * 
	 * @param f the file to check
	 * @return true if file is a valid image file; else, false
	 */
	public boolean accept(File f)
	{
		// display directories to allow navigation
		if (f.isDirectory())
			return true;
		
		// set extension to file name
		String extension = f.getName();

		// set extension to file extension
		extension = extension.substring(extension.lastIndexOf('.') + 1, extension.length());

		// check if extension is valid, if it is return true
		for (String valid : validExtensions)
			if (extension.equalsIgnoreCase(valid))
				return true;

		// not a valid extension... return false
		return false;
	}

	/**
	 * Gets the description of this filter.
	 */
	public String getDescription()
	{
		return "Images";
	}
}