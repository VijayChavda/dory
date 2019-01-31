package me.vijaychavda.dory.views;

import javax.swing.JLabel;
import me.vijaychavda.dory.models.Item;
import me.vijaychavda.dory.models.Item.ItemListener;
import static me.vijaychavda.dory.views.Reuseable.SB;

/**
 *
 * @author Vijay
 */
public class ItemView extends JLabel implements ItemListener {

	private Item context;

	public ItemView() {
		this(null);
	}

	public ItemView(Item item) {
		initView(item);
	}

	private void initView(Item context) {
		setContext(context);

		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.BOTTOM);

		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
	}

	public Item getContext() {
		return context;
	}

	public void setContext(Item context) {
		this.context = context;

		if (context != null) {
			context.addItemListener(this);
			onTextChanged();
			onIconChanged();
			onSelectionChanged();
		}
	}

	@Override
	public void onTextChanged() {
		if (context == null)
			return;

		setText(context.getText());
	}

	@Override
	public void onIconChanged() {
		if (context == null)
			return;

		setIcon(context.getIcon());
	}

	@Override
	public void onSelectionChanged() {
		if (context == null)
			return;

		setBorder(context.isSelected() ? SB : null);
	}

}
