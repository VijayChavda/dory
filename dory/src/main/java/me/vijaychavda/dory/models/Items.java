package me.vijaychavda.dory.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/**
 *
 * @author Vijay
 */
public class Items {

	private final ArrayList<Item> items;
	private final HashSet<Integer> addedItems;
	private final HashSet<Integer> removedItems;
	private boolean reordered;

	public Items() {
		items = new ArrayList<>();
		addedItems = new HashSet<>();
		removedItems = new HashSet<>();
		reordered = false;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public void clear() {
		items.clear();

		for (int index = 0; index < count(); index++) {
			removedItems.add(index);
		}
	}

	public int count() {
		return items.size();
	}

	public Item get(int index) {
		return items.get(index);
	}

	public void add(int index, Item item) {
		items.add(index, item);
		addedItems.add(index);
	}

	public void remove(int index) {
		items.remove(index);
		removedItems.add(index);
	}

	public void reorder(Comparator<Item> order) {
		reordered = true;
		items.sort(order);
	}

	public boolean wereReordered() {
		return reordered;
	}

	public HashSet<Integer> getAddedItems() {
		return addedItems;
	}

	public HashSet<Integer> getRemovedItems() {
		return removedItems;
	}

	public void refresh() {
		addedItems.clear();
		removedItems.clear();
		reordered = false;
	}

}
