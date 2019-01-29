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
	private final HashSet<ItemsListener> listeners;

	public Items() {
		items = new ArrayList<>();
		listeners = new HashSet<>();
	}

	public List<Item> getItems() {
		return items;
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public void clear() {
		items.clear();

		fireItemsCleared();
	}

	public int count() {
		return items.size();
	}

	public Item get(int index) {
		return items.get(index);
	}

	public void add(Item item) {
		items.add(item);

		fireItemAdded(item);
	}

	public void remove(int index) {
		Item removed = items.remove(index);

		fireItemRemoved(removed);
	}

	public void reorder(Comparator<Item> order) {
		items.sort(order);

		fireReordered();
	}

	public void addItemsListener(ItemsListener listener) {
		if (listener != null)
			listeners.add(listener);
	}

	public void removeItemsListener(ItemsListener listener) {
		if (listener != null)
			listeners.remove(listener);
	}

	private void fireItemAdded(Item added) {
		for (ItemsListener listener : listeners) {
			listener.onItemAdded(added);
		}
	}

	private void fireItemRemoved(Item removed) {
		for (ItemsListener listener : listeners) {
			listener.onItemRemoved(removed);
		}
	}

	private void fireItemsCleared() {
		for (ItemsListener listener : listeners) {
			listener.onItemsCleared();
		}
	}

	private void fireReordered() {
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

}
