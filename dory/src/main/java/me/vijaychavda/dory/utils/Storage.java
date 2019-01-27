package me.vijaychavda.dory.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import static me.vijaychavda.dory.Dory.DORY;
import static me.vijaychavda.dory.Dory.SETTINGS;

/**
 *
 * @author Vijay
 */
public class Storage {

	private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());

	private Path appDir, logsDir, tagsDir;
	private Path udfaLogFile;

	public Storage() {
		String dir = SETTINGS.getAppDirPath();

		try {
			appDir = Files.createDirectories(Paths.get(dir, DORY));
			logsDir = Files.createDirectories(appDir.resolve("logs"));
			tagsDir = Files.createDirectories(appDir.resolve("tags"));
			udfaLogFile = logsDir.resolve("udfa.log");

			if (!Files.exists(udfaLogFile))
				Files.createFile(udfaLogFile);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "Failed to initialize storage.", ex);
			throw new Error("Failed to initialize storage.");
		}
	}

	public Path getAppDir() {
		return appDir;
	}

	public Path getLogsDir() {
		return logsDir;
	}

	public Path getTagsDir() {
		return tagsDir;
	}

}
