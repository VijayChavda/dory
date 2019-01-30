package me.vijaychavda.dory.views;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import me.vijaychavda.dory.models.Item;

/**
 *
 * @author Vijay
 */
public class ItemView extends JLabel {

	private Item context;

	private static final Border FB = BorderFactory.createDashedBorder(Color.GRAY);

	public ItemView(Item context) {
		this.context = context;

		initView();
	}

	private void initView() {
		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.BOTTOM);

		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (!context.isSelected())
					setBorder(FB);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!context.isSelected())
					setBorder(null);
			}
		});

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
