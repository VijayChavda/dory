package me.vijaychavda.dory.views;

import javax.swing.JLabel;
import me.vijaychavda.dory.models.Item;

/**
 *
 * @author Vijay
 */
public class ItemView extends JLabel {

	private Item context;

	public ItemView(Item context) {
		this.context = context;

		initView();
	}

	private void initView() {
		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.BOTTOM);

		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);

		reContext();
	}

	public void reContext() {
		setText(context.getText());
		setIcon(context.getIcon());
	}

	public Item getContext() {
		return context;
	}

	public void setContext(Item context) {
		this.context = context;
		reContext();
	}

}
