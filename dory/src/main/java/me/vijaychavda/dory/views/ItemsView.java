package me.vijaychavda.dory.views;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import javax.swing.JPanel;
import static me.vijaychavda.dory.Dory.SETTINGS;
import me.vijaychavda.dory.models.Item;
import me.vijaychavda.dory.models.Items;
import me.vijaychavda.dory.models.Items.ItemsListener;
import static me.vijaychavda.dory.views.Reuseable.FB;
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

	private Point selectionRectStart;
	private Point selectionRectEnd;

	public ItemsView() {
		this(null);
	}

	public ItemsView(Items items) {
		itemViews = new HashMap<>();

		initView(items);
	}

	private void initView(Items context) {
		setContext(context);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reLayout();
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectionRectStart = e.getPoint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				selectionRectStart = selectionRectEnd = null;
				repaint();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				selectionRectEnd = e.getPoint();
				repaint();
			}
		});
	}

	public Items getContext() {
		return context;
	}

	public void setContext(Items context) {
		this.context = context;

		if (context != null) {
			context.addItemsListener(this);

			for (Item item : context.getItems()) {
				onItemAdded(item);
			}
		}
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

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (selectionRectStart == null || selectionRectEnd == null)
			return;

		Graphics2D gg = (Graphics2D) g;
		Rectangle selectionRect = new Rectangle(
			Math.min(selectionRectStart.x, selectionRectEnd.x),
			Math.min(selectionRectStart.y, selectionRectEnd.y),
			Math.abs(selectionRectStart.x - selectionRectEnd.x),
			Math.abs(selectionRectStart.y - selectionRectEnd.y)
		);

		gg.setColor(SETTINGS.getMouseDragSelectionColorBorder());
		gg.drawRect(
			selectionRect.x, selectionRect.y,
			selectionRect.width, selectionRect.height
		);

		gg.setColor(SETTINGS.getMouseDragSelectionColorBackground());
		gg.fillRect(
			selectionRect.x, selectionRect.y,
			selectionRect.width, selectionRect.height
		);
	}

	@Override
	public void onItemAdded(Item item) {
		if (context == null)
			return;

		ItemView itemView = new ItemView(item);
		itemView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				item.setSelected(!item.isSelected());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!item.isSelected())
					itemView.setBorder(FB);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!item.isSelected())
					itemView.setBorder(null);
			}
		});

		itemViews.put(item, itemView);
		add(itemView);

		revalidate();
		repaint();
	}

	@Override
	public void onItemRemoved(Item item) {
		if (context == null)
			return;

		if (!itemViews.containsKey(item))
			return;

		remove(itemViews.get(item));
		itemViews.remove(item);

		revalidate();
		repaint();
	}

	@Override
	public void onItemsCleared() {
		if (context == null)
			return;

		itemViews.clear();
		removeAll();

		revalidate();
		repaint();
	}

	@Override
	public void onReordered() {
		if (context == null)
			return;

		removeAll();
		for (Item item : context.getItems()) {
			add(itemViews.get(item));
		}
		revalidate();
		repaint();
	}

}
