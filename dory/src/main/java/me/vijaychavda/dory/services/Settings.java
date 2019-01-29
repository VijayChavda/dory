package me.vijaychavda.dory.services;

/**
 *
 * @author Vijay
 */
public class Settings {

	private String appDirPath;

	public Settings() {
		appDirPath = System.getProperty("user.home");
	}

	public String getAppDirPath() {
		return appDirPath;
	}

	public void setAppDirPath(String appDirPath) {
		this.appDirPath = appDirPath;
	}

}
