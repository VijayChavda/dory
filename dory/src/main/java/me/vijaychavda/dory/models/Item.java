package me.vijaychavda.dory.models;

import java.text.MessageFormat;
import java.util.HashSet;
import javax.swing.Icon;

/**
 *
 * @author Vijay
 */
public class Item {

	private String text;
	private Icon icon;
	private boolean selected;

	private HashSet<ItemListener> listeners;

	//<editor-fold defaultstate="collapsed" desc="Getters, Setters">
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		fireTextChanged();
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
		fireIconChanged();
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		fireSelectionChanged();
	}
	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="Property change">
	public void addItemListener(ItemListener listener) {
		if (listener == null)
			return;

		if (listeners == null)
			listeners = new HashSet<>();

		listeners.add(listener);
	}

	public void removeItemListener(ItemListener listener) {
		if (listener == null)
			return;

		if (listeners == null)
			return;

		listeners.remove(listener);
	}

	private void fireTextChanged() {
		if (listeners == null)
			return;

		for (ItemListener listener : listeners) {
			listener.onTextChanged();
		}
	}

	private void fireIconChanged() {
		if (listeners == null)
			return;

		for (ItemListener listener : listeners) {
			listener.onIconChanged();
		}
	}

	private void fireSelectionChanged() {
		if (listeners == null)
			return;

		for (ItemListener listener : listeners) {
			listener.onSelectionChanged();
		}
	}

	public interface ItemListener {

		void onTextChanged();

		void onIconChanged();

		void onSelectionChanged();

	}
	//</editor-fold>

	@Override
	public String toString() {
		return MessageFormat.format("Item[text={0}, icon={1}]", text, icon);
	}
}
