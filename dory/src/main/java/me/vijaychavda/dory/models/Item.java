package me.vijaychavda.dory.models;

import java.text.MessageFormat;
import javax.swing.Icon;

/**
 *
 * @author Vijay
 */
public class Item {

	private String text;
	private Icon icon;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return MessageFormat.format("Item[text={0}, icon={1}]", text, icon);
	}

}
