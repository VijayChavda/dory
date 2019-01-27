package me.vijaychavda.dory;

import me.vijaychavda.dory.utils.Settings;
import me.vijaychavda.dory.utils.Storage;

/**
 *
 * @author Vijay
 */
public class Dory {

	public static final String DORY = "dory";

	public static final Settings SETTINGS;
	public static final Storage STORAGE;

	static {
		SETTINGS = new Settings();
		STORAGE = new Storage();
	}

	public static void main(String[] args) {
		System.out.println("Hello, World!");
	}
}
