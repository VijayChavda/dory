package me.vijaychavda.dory.views;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import javax.swing.JPanel;
import me.vijaychavda.dory.models.Item;
import me.vijaychavda.dory.models.Items;
import me.vijaychavda.dory.models.Items.ItemsListener;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Vijay
 */
public class ItemsView extends JPanel implements ItemsListener {

	private Items context;
	private final HashMap<Item, ItemView> itemViews;

	public ItemsView() {
		this(new Items());
	}

	public ItemsView(Items context) {
		this.context = context;
		itemViews = new HashMap<>();

		init();
	}

	private void init() {
		context.addItemsListener(this);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reLayout();
			}
		});
	}

	public void reLayout() {
		if (getComponentCount() == 0)
			return;

		if (getWidth() == 0 || getHeight() == 0)
			return;

		int cellWidth = 0, cellHeight = 0;
		for (Component component : getComponents()) {
			cellWidth = Math.max(cellWidth, component.getMaximumSize().width);
			cellHeight = Math.max(cellHeight, component.getMaximumSize().height);
		}

		if (cellWidth == 0 || cellHeight == 0)
			return;

		int horizontalGap = 30;
		int verticalGap = 30;
		int colCount = (getWidth() - horizontalGap) / (cellWidth + horizontalGap);
		setLayout(
			new MigLayout(
				new LC()
				.wrapAfter(colCount)
				.alignX("left")
				.alignY("top")
				.gridGapX(horizontalGap + "::")
				.gridGapY(verticalGap + "!"),
				new AC().size(cellWidth + ":" + cellWidth).align("center").grow(),
				new AC().size(cellHeight + ":" + cellHeight).align("bottom")
			)
		);

		revalidate();
		repaint();
	}

	public Items getContext() {
		return context;
	}

	public void setContext(Items context) {
		this.context = context;
		context.addItemsListener(this);

		reLayout();
	}

	@Override
	public void onItemAdded(Item item) {
		ItemView itemView = new ItemView(item);
		itemViews.put(item, itemView);
		add(itemView);
		revalidate();
		repaint();
	}

	@Override
	public void onItemRemoved(Item item) {
		itemViews.remove(item);
		revalidate();
		repaint();
	}

	@Override
	public void onItemsCleared() {
		itemViews.clear();
		removeAll();
		revalidate();
		repaint();
	}

	@Override
	public void onReordered() {
		removeAll();
		for (Item item : context.getItems()) {
			itemViews.get(item);
		}
		revalidate();
		repaint();
	}

}
