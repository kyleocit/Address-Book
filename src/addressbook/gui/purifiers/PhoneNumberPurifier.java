package addressbook.gui.purifiers;

/**
 * An input purifier for a phone number. The input is stripped of any
 * non-numeric characters and special formatting is applied to the
 * purified phone number. Additionally, unnecessary whitespace is removed.
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class PhoneNumberPurifier extends Purifier
{
	/**
	 * Allow only numbers. Special formatting is applied to make numbers take
	 * one of the following forms: <br />
	 * 1 - 2345 <br />
	 * 123 - 4567 <br />
	 * (123) 456 - 7890 <br />
	 * (123) 456 - 7890 Ext: 01234 <br />
	 * 
	 * @param input the input string to purify
	 * @return the purified input string
	 * @since 1.1
	 */
	@Override public String purify(String input)
	{
		String purified = "";

		for (int i = 0; i < input.length(); i++)
			if (Character.isDigit(input.charAt(i)))
				purified += input.charAt(i);

		if (purified.length() > 10)
			purified = "(" + purified.substring(0, 3) + ") " + purified.substring(3, 6) + " - " + purified.substring(6, 10) + " Ext: " + purified.substring(10, purified.length());
		else if (purified.length() > 7)
			purified = "(" + purified.substring(0, 3) + ") " + purified.substring(3, 6) + " - " + purified.substring(6, purified.length());
		else if (purified.length() > 4)
			purified = purified.substring(0, purified.length()-4) + " - " + purified.substring(purified.length()-4, purified.length());
		
		return purified;
	}
}