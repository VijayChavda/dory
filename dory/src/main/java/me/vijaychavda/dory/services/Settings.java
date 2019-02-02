package me.vijaychavda.dory.services;

import java.awt.Color;

/**
 *
 * @author Vijay
 */
public class Settings {

	private String appDirPath;

	private Color itemFocusBorderColor;
	private Color itemSelectBorderColor;

	private Color selectionRectBorderColor;
	private Color selectionRectBackgroundColor;

	public Settings() {
		appDirPath = System.getProperty("user.home");

		itemFocusBorderColor = Color.GRAY;
		itemSelectBorderColor = Color.GRAY;

		selectionRectBorderColor = Color.GRAY;
		selectionRectBackgroundColor = new Color(100, 100, 100, 100);
	}

	public String getAppDirPath() {
		return appDirPath;
	}

	public void setAppDirPath(String appDirPath) {
		this.appDirPath = appDirPath;
	}

	public Color getItemFocusBorderColor() {
		return itemFocusBorderColor;
	}

	public void setItemFocusBorderColor(Color itemFocusBorderColor) {
		this.itemFocusBorderColor = itemFocusBorderColor;
	}

	public Color getItemSelectBorderColor() {
		return itemSelectBorderColor;
	}

	public void setItemSelectBorderColor(Color itemSelectBorderColor) {
		this.itemSelectBorderColor = itemSelectBorderColor;
	}

	public Color getSelectionRectBorderColor() {
		return selectionRectBorderColor;
	}

	public void setSelectionRectBorderColor(Color selectionRectBorderColor) {
		this.selectionRectBorderColor = selectionRectBorderColor;
	}

	public Color getSelectionRectBackgroundColor() {
		return selectionRectBackgroundColor;
	}

	public void setSelectionRectBackgroundColor(Color selectionRectBackgroundColor) {
		this.selectionRectBackgroundColor = selectionRectBackgroundColor;
	}

}
