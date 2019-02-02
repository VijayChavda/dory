package me.vijaychavda.dory.services;

import java.awt.Color;

/**
 *
 * @author Vijay
 */
public class Settings {

	private String appDirPath;
	private Color mouseDragSelectionColorBorder;
	private Color mouseDragSelectionColorBackground;

	public Settings() {
		appDirPath = System.getProperty("user.home");
		mouseDragSelectionColorBorder = Color.GRAY;
		mouseDragSelectionColorBackground = new Color(100, 100, 100, 100);
	}

	public String getAppDirPath() {
		return appDirPath;
	}

	public void setAppDirPath(String appDirPath) {
		this.appDirPath = appDirPath;
	}

	public Color getMouseDragSelectionColorBorder() {
		return mouseDragSelectionColorBorder;
	}

	public void setMouseDragSelectionColorBorder(Color mouseDragSelectionColorBorder) {
		this.mouseDragSelectionColorBorder = mouseDragSelectionColorBorder;
	}

	public Color getMouseDragSelectionColorBackground() {
		return mouseDragSelectionColorBackground;
	}

	public void setMouseDragSelectionColorBackground(Color mouseDragSelectionColorBackground) {
		this.mouseDragSelectionColorBackground = mouseDragSelectionColorBackground;
	}

}
