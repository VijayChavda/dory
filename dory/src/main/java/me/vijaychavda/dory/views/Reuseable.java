package me.vijaychavda.dory.views;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import static me.vijaychavda.dory.Dory.SETTINGS;

/**
 *
 * @author Vijay
 */
public interface Reuseable {

	Border FB = BorderFactory.createDashedBorder(SETTINGS.getItemFocusBorderColor());

	Border SB = BorderFactory.createLineBorder(SETTINGS.getItemSelectBorderColor());

}
