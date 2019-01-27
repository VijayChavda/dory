package me.vijaychavda.dory.utils;

/**
 *
 * @author Vijay
 */
public class Settings {

	private static Settings instance;

	public static Settings getInstance() {
		if (instance == null)
			instance = new Settings();
		return instance;
	}

	private Settings() {
		appDirPath = System.getProperty("user.home");
	}

	private String appDirPath;

	public String getAppDirPath() {
		return appDirPath;
	}

	public void setAppDirPath(String appDirPath) {
		this.appDirPath = appDirPath;
	}

}
