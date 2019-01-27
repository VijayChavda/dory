package me.vijaychavda.dory.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static me.vijaychavda.dory.Dory.DORY;
import static me.vijaychavda.dory.Dory.SETTINGS;

/**
 *
 * @author Vijay
 */
public class Storage {

	private final File appDir, logsDir, tagsDir;
	private final File udfaLogFile;

	public Storage() throws IOException {
		String dir = SETTINGS.getAppDirPath();

		appDir = Files.createDirectories(Paths.get(dir, DORY)).toFile();
		logsDir = Files.createDirectories(Paths.get(dir, DORY, "logs")).toFile();
		tagsDir = Files.createDirectories(Paths.get(dir, DORY, "tags")).toFile();
		udfaLogFile = Paths.get(dir, DORY, "logs", "udfa.log").toFile();

		if (!udfaLogFile.exists())
			udfaLogFile.createNewFile();
	}

	public File getAppDir() {
		return appDir;
	}

	public File getLogsDir() {
		return logsDir;
	}

	public File getTagsDir() {
		return tagsDir;
	}

	public File getUdfaLogFile() {
		return udfaLogFile;
	}

}
