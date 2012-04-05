package addressbook.gui.purifiers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Purifies the input text of a JTextField or JTextArea by stripping disallowed
 * characters from the input and updating the input field's text with the
 * filtered String.
 * <p>
 * This class is abstract and can not be used directly. You must create a child
 * class that extends this purifier and implement the purify method.
 * <p>
 * To use purifiers simply add a key listener to your text field with an
 * instance of your purifier as the argument.
 * <p>
 * e.g.: myTextField.addKeyListener(new MyCustomPurifier());
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
abstract public class Purifier implements KeyListener
{
	/**
	 * Purifies the input text by stripping out disallowed characters.
	 * 
	 * @param input the input string to purify
	 * @return the purified input string
	 * @since 1.1
	 */
	abstract public String purify(String input);

	/**
	 * Whenever a key is released within a text field or text area that
	 * has registered this listener the input is purified.
	 * 
	 * @since 1.1
	 */
	@Override public void keyReleased(KeyEvent e)
	{
		// allow left and right cursor movement
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT
			|| e.getKeyCode() == KeyEvent.VK_KP_LEFT || e.getKeyCode() == KeyEvent.VK_KP_RIGHT)
		{
			return;
		}
		// filter the input text
		else if (e.getSource() instanceof JTextField)
		{
			JTextField source = (JTextField) e.getSource();
			source.setText(this.purify(source.getText()));
		}
		// filter the input text
		else if (e.getSource() instanceof JTextArea)
		{
			JTextArea source = (JTextArea) e.getSource();
			source.setText(this.purify(source.getText()));
		}
	}

	//---
	// Unused Listener Methods
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyPressed(KeyEvent e) {}
	//---
}