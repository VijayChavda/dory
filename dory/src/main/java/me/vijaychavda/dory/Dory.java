package me.vijaychavda.dory;

import java.io.IOException;
import java.util.logging.Logger;
import me.vijaychavda.dory.utils.LoggerUtil;
import me.vijaychavda.dory.utils.Settings;
import me.vijaychavda.dory.utils.Storage;

/**
 *
 * @author Vijay
 */
public class Dory {

	public static final String DORY = "dory";
	public static final Logger LOGGER;
	public static final Settings SETTINGS;
	public static final Storage STORAGE;

	static {
		SETTINGS = new Settings();

		try {
			STORAGE = new Storage();
		} catch (IOException ex) {
			throw new Error("Failed to initialize Storage", ex);
		}

		try {
			LOGGER = Logger.getLogger(DORY);
			LoggerUtil.addFileHandler(LOGGER);
		} catch (IOException ex) {
			throw new Error("Failed to initialize Logger", ex);
		}
	}

	public static void main(String[] args) {
		System.out.println("Hello, World!");
	}

}
