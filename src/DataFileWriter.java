/*
 * @(#)DataFileWriter.java	1.1 10/10/13
 *
 * Source code written by Kyle Campbell.
 * HexSupport provided by a third party associated with the Apache
 * Software Foundation.
 *
 * UML Diagram
 * --------------------------------------------------
 *                  DataFileWriter
 * --------------------------------------------------
 *  -PRIMARY_KEY: byte[]
 *  -fileWriter: BufferedWriter
 *  -dataKey: byte[]
 *  -cipher: Cipher
 * --------------------------------------------------
 *  +DataFileWriter(File)
 *  +close(): void
 *  +write(String): void
 * --------------------------------------------------
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * The class DataFileWriter encrypts and writes Strings to the specified
 * dataFile. The encryption algorithm used is AES 128-bit. DataFileReader
 * should be used to obtain the data contained within the specified dataFile.
 * 
 * @author k.j.campbell
 * @see DataFileReader
 * @see HexSupport
 */
public final class DataFileWriter
{
	/** Stores the primary key used for encryption/decryption of dataKey. */
	private static final byte[] PRIMARY_KEY = {-82, -64, -109, -67, -45, 30,
		-67, -94, -113, 31, 70, 72, 77, 7, 54, -122};

	/** A BufferedFileWriter tethered to the specified dataFile for writing data. */
	private BufferedWriter fileWriter;

	/** The AES encryption key used to encrypt/decrypt data. */
	private byte[] dataKey;

	/** The AES 128-bit encryption cipher used to encrypt/decrypt data. */
	private Cipher cipher;

	/**
	 * Constructs a new DataFileWriter to write encrypted data into the
	 * specified data file.
	 * 
	 * @param dataFile the file to write encrypted data into
	 */
	public DataFileWriter(File dataFile)
	{
		try
		{
			// make sure the path to the dataFile exists
			if (!dataFile.exists())
				dataFile.getParentFile().mkdirs();

			// create buffered file writer for dataFile
			fileWriter = new BufferedWriter(new FileWriter(dataFile, false));

			// create data encryption key for AES
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			dataKey = keygen.generateKey().getEncoded();

			// create cipher using AES algorithm
			cipher = Cipher.getInstance("AES");

			// encrypt dataKey using primaryKey and write it to dataFile
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(PRIMARY_KEY, "AES"));
			fileWriter.write(HexSupport.toHexFromBytes(cipher.doFinal(dataKey))+"\r\n");

			// re-initialize cipher to use dataKey for data encryption
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(dataKey, "AES"));
		}
		catch (Exception e)
		{
			System.err.println("Error with file encryption.");
		}
	}

	/**
	 * Encrypts the specified String of data and writes it to a new line in the
	 * specified data file.
	 * 
	 * @param data the String to encrypt and write
	 */
	public synchronized void write(String data)
	{
		try
		{
			fileWriter.write(HexSupport.toHexFromBytes(cipher.doFinal(data.getBytes()))+"\r\n");
		}
		catch (Exception e)
		{
			System.err.println("Could not write data!");
		}
	}

	/**
	 * Closes the dataFile that this DataFileWriter is tethered to. Before the
	 * file is closed, any data remaining in the buffer is written to the file.
	 */
	public synchronized void close()
	{
		try
		{
			fileWriter.close();
		}
		catch (Exception e)
		{
			System.err.println("Could not close data file!");
		}
	}
}