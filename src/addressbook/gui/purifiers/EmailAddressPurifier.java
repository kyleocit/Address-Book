package addressbook.gui.purifiers;

/**
 * An input purifier for an email address. The input is stripped of any
 * invalid characters.
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class EmailAddressPurifier extends Purifier
{
	// TODO This particular method of validation is fundamentally flawed and needs to be reworked.
	/**
	 * Allow only valid email addresses.
	 * 
	 * @param input the input string to purify
	 * @return the purified input string
	 * @since 1.1
	 */
	@Override public String purify(String input)
	{
		String purified = "";

		for (int i = 0; i < input.length(); i++)
			if (Character.isLetterOrDigit(input.charAt(i)) || input.charAt(i) == '.' || input.charAt(i) == '@'
				|| input.charAt(i) == '_' || input.charAt(i) == '-' || input.charAt(i) == '~' || input.charAt(i) == '#'
					|| input.charAt(i) == ':')
			{
				purified += input.charAt(i);
			}

		return purified;
	}
}