package addressbook.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;

import addressbook.gui.StyleConstants;

public class LabelledTextArea extends JPanel implements ComponentListener
{
	public static final int VALIDATION_NONE = 0;
	
	protected JLabel label;
	protected JTextArea field;
	protected JScrollPane scrollpane;

	public LabelledTextArea(String description)
	{
		// create and style label with default settings
		label = new JLabel(description);
		label.setBackground(StyleConstants.BACKGROUND_COLOR);
		label.setFont(StyleConstants.CI_LABEL_FONT);
		label.setForeground(Color.BLACK);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setOpaque(true);
		label.setVerticalAlignment(SwingConstants.CENTER);

		// create and style field with default settings
		field = new JTextArea();
		field.setFont(StyleConstants.CI_INPUT_FONT);
		field.setLineWrap(true);
		field.setOpaque(false);
		field.setTabSize(4);
		field.setWrapStyleWord(true);

		// setup scroll pane for field to prevent text overflow
		scrollpane = new JScrollPane();
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setViewportView(field);

		// add label and field to panel
		this.setBackground(StyleConstants.CI_BACKGROUND_COLOR);
		this.setLayout(null);
		this.add(label);
		this.add(scrollpane);
		this.addComponentListener(this);
	}

	@Override public void componentResized(ComponentEvent e)
	{
		label.setBounds(0, 0, this.getWidth(), 20);
		scrollpane.setBounds(0, 20, this.getWidth(), this.getHeight() - 20);
	}

	//---
	// Unused Listener Methods
	@Override public void componentMoved(ComponentEvent e) {}
	@Override public void componentShown(ComponentEvent e) {}
	@Override public void componentHidden(ComponentEvent e) {}
	//---
}