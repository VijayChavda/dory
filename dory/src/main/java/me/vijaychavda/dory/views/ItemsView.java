package me.vijaychavda.dory.views;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import javax.swing.JPanel;
import me.vijaychavda.dory.models.Item;
import me.vijaychavda.dory.models.Items;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Vijay
 */
public class ItemsView extends JPanel {

	private Items context;
	private final HashMap<Item, ItemView> itemViews;

	public ItemsView(Items context) {
		this.context = context;
		itemViews = new HashMap<>();

		initView();
	}

	private void initView() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reLayout();
			}
		});
		reload();
	}

	public void reload() {
		reContext();
		reLayout();
	}

	public void reContext() {
		if (context.wereReordered()) {
			removeAll();
			for (Item item : context.getItems()) {
				add(itemViews.get(item));
			}
			return;
		}

		for (Integer index : context.getAddedItems()) {
			Item item = context.get(index);
			ItemView itemView = new ItemView(item);

			itemViews.put(item, itemView);
			add(itemView, "", index);
		}

		for (Integer index : context.getRemovedItems()) {
			itemViews.remove(context.get(index));
			remove(index);
		}

		context.refresh();
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
				new AC().size(cellWidth + "").align("center").grow(),
				new AC().size(cellHeight + "").align("bottom")
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
		reContext();
	}

}
