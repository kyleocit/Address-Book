package addressbook.gui.purifiers;

/**
 * An input purifier for a locations zip code. The zip code can contain
 * up to 5 numbers plus an additional 4 for the extension.
 * Additionally, all whitespace is removed.
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class ZipCodePurifier extends Purifier
{
	/**
	 * Allow only up to 5 numbers for zip code and an additional 4 numbers for
	 * zip code extension.
	 * 
	 * @param input the input string to purify
	 * @return the purified input string
	 * @since 1.1
	 */
	@Override public String purify(String input)
	{
		String purified = "";

		for (int i = 0; i < input.length(); i++)
		{
			if (Character.isDigit(input.charAt(i)) && purified.length() < 9)
				purified += input.charAt(i);
		}

		if (purified.length() > 5)
			purified = purified.substring(0, 5) + " - " + purified.substring(5, purified.length());
		
		return purified;
	}
}