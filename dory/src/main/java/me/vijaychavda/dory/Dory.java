package me.vijaychavda.dory;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.Logger;
import me.vijaychavda.dory.services.Assets;
import me.vijaychavda.dory.services.Settings;
import me.vijaychavda.dory.services.Storage;
import me.vijaychavda.dory.utils.LoggerUtil;
import me.vijaychavda.dory.views.Explorer;

/**
 *
 * @author Vijay
 */
public class Dory {

	public static final String DORY = "dory";
	public static final Logger LOGGER;
	public static final Assets ASSETS;
	public static final Settings SETTINGS;
	public static final Storage STORAGE;

	private static Explorer Explorer;

	static {
		try {
			ASSETS = new Assets();
		} catch (IOException ex) {
			throw new Error("Failed to load default Assets", ex);
		}

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
		EventQueue.invokeLater(() -> {
			Explorer = new Explorer();
			Explorer.setVisible(true);
		});
	}

	public static Explorer getExplorer() {
		return Explorer;
	}

}
