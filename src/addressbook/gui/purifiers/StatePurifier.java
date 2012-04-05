package addressbook.gui.purifiers;

/**
 * An input purifier for a state's 2-letter abbreviation. The abbreviation is
 * converted to upper casing and all non-alpha characters are removed.
 * Additionally, all whitespace is removed.
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class StatePurifier extends Purifier
{
	/**
	 * Allow only letters in input. Automatic capitalization of both letters.
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
			if (Character.isLetter(input.charAt(i)) && purified.length() < 2)
				purified += Character.toUpperCase(input.charAt(i));
		}
		
		return purified;
	}
}