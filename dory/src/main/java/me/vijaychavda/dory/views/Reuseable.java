package me.vijaychavda.dory.views;

import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.GRAY;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author Vijay
 */
public interface Reuseable {

	Border FB = BorderFactory.createDashedBorder(GRAY);

	Border SB = BorderFactory.createLineBorder(DARK_GRAY);

}
