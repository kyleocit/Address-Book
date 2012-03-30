package addressbook.datafile;
/*
 * @(#)DataFileReader.java	1.1 10/10/13
 *
 * Source code written by Kyle Campbell.
 * HexSupport provided by a third party associated with the Apache
 * Software Foundation.
 *
 * UML Diagram
 * --------------------------------------------------
 *                  DataFileReader
 * --------------------------------------------------
 *  -PRIMARY_KEY: byte[]
 *  -fileReader: Scanner
 *  -dataKey: byte[]
 *  -cipher: Cipher
 * --------------------------------------------------
 *  +DataFileWriter(File)
 *  +hasNext(): boolean
 *  +next(): String
 * --------------------------------------------------
 * 
 * Change Log
 * v1.1
 *  - added check for dataFile existence upon instantiation
 * v1.0
 *  - initial release
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import addressbook.HexSupport;

/**
 * The class DataFileReader reads encrypted data written to a data file by
 * an instance of the DataFileWriter class. The data file being read must
 * have been written to only by the DataFileWriter class.
 * 
 * @author k.j.campbell
 * @see DataFileWriter
 * @see HexSupport
 */
public class DataFileReader
{
	/** Stores the primary key used for encryption/decryption of dataKey. */
	private static final byte[] PRIMARY_KEY = {-82, -64, -109, -67, -45, 30,
		-67, -94, -113, 31, 70, 72, 77, 7, 54, -122};

	/** A Scanner tethered to the specified dataFile for reading data. */
	private Scanner fileReader;

	/** The AES encryption key used to encrypt/decrypt data. */
	private byte[] dataKey;

	/** The AES 128-bit encryption cipher used to encrypt/decrypt data. */
	private Cipher cipher;

	/**
	 * Constructs a new DataFileReader to read encrypted data from the
	 * specified data file. The file must have been written by an instance
	 * of the DataFileWriter class.
	 * 
	 * @param dataFile the file to read encrypted data from
	 * @see DataFileWriter
	 */
	public DataFileReader(File dataFile)
	{
		try
		{
			// make sure the path to the dataFile exists
			if (!dataFile.exists())
				throw new FileNotFoundException(dataFile.getAbsolutePath() + " does not exist.");

			// create scanner to read from dataFile
			fileReader = new Scanner(dataFile);

			// create cipher using AES algorithm
			cipher = Cipher.getInstance("AES");

			// decrypt dataKey in file header using PRIMARY_KEY
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(PRIMARY_KEY, "AES"));
			dataKey = cipher.doFinal(HexSupport.toBytesFromHex(fileReader.nextLine()));

			// re-initialize cipher to use dataKey for data decryption
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(dataKey, "AES"));
		}
		catch (Exception e)
		{
			System.err.println("Data file is corrupt.");
		}
	}

	/**
	 * Checks to see if the data file contains any more data.
	 * 
	 * @return true if more data is available; else, false
	 */
	public synchronized boolean hasNext()
	{
		return fileReader.hasNextLine();
	}

	/**
	 * Gets the next String of data from the file. After the data is decrypted
	 * it is returned.
	 * 
	 * @return the decrypted line of data as a String
	 */
	public synchronized String next()
	{
		String s, hex = fileReader.nextLine();
		try
		{
			s = new String(cipher.doFinal(HexSupport.toBytesFromHex(hex)));
		}
		catch (Exception e)
		{
			System.err.println("Error reading data!");
			s = "";
		}	
		return s;
	}
}
