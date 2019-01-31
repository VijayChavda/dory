package me.vijaychavda.dory.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Vijay
 */
public class Items {

	private final List<Item> items;

	private HashSet<ItemsListener> listeners;

	public Items() {
		items = new ArrayList<>();
	}

	//<editor-fold defaultstate="collapsed" desc="Getters, Queries">
	public List<Item> getItems() {
		return items;
	}

	public Item get(int index) {
		return items.get(index);
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public int count() {
		return items.size();
	}
	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="Add, Remove, Clear, Reorder">
	public void add(Item item) {
		items.add(item);
		fireItemAdded(item);
	}

	public void remove(int index) {
		Item removed = items.remove(index);
		fireItemRemoved(removed);
	}

	public void clear() {
		items.clear();
		fireItemsCleared();
	}

	public void reorder(Comparator<Item> order) {
		items.sort(order);
		fireReordered();
	}
	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="Property change">
	public void addItemsListener(ItemsListener listener) {
		if (listener == null)
			return;

		if (listeners == null)
			listeners = new HashSet<>();

		listeners.add(listener);
	}

	public void removeItemsListener(ItemsListener listener) {
		if (listener == null)
			return;

		if (listeners == null)
			return;

		listeners.remove(listener);
	}

	private void fireItemAdded(Item added) {
		if (listeners == null)
			return;

		for (ItemsListener listener : listeners) {
			listener.onItemAdded(added);
		}
	}

	private void fireItemRemoved(Item removed) {
		if (listeners == null)
			return;

		for (ItemsListener listener : listeners) {
			listener.onItemRemoved(removed);
		}
	}

	private void fireItemsCleared() {
		if (listeners == null)
			return;

		for (ItemsListener listener : listeners) {
			listener.onItemsCleared();
		}
	}

	private void fireReordered() {
		if (listeners == null)
			return;

		for (ItemsListener listener : listeners) {
			listener.onReordered();
		}
	}

	public static interface ItemsListener {

		void onItemAdded(Item added);

		void onItemRemoved(Item removed);

		void onItemsCleared();

		void onReordered();
	}
	//</editor-fold>

}
