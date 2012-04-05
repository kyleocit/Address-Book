package addressbook.gui.purifiers;

/**
 * An input purifier for a person's, business's, entity's, or place's name.
 * The name is converted to proper casing and all non-characters are removed.
 * Additionally, unnecessary whitespace is removed.
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class NamePurifier extends Purifier
{
	/**
	 * Allow only letters and spaces in input. Automatic capitalization of
	 * first letter of each word.
	 * 
	 * @param input the input string to purify
	 * @return the purified input string
	 * @since 1.1
	 */
	@Override public String purify(String input)
	{
		String purified = "";
		boolean capNext = true;

		for (int i = 0; i < input.length(); i++)
		{
			if (input.charAt(i) == ' ' && !capNext)
			{
				purified += " ";
				capNext = true;
			}
			else if (capNext && Character.isLetter(input.charAt(i)))
			{
				purified += Character.toUpperCase(input.charAt(i));
				capNext = false;
			}
			else if (Character.isLetter(input.charAt(i)))
				purified += input.charAt(i);
		}
		
		return purified;
	}
}