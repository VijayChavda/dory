package me.vijaychavda.dory.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import static me.vijaychavda.dory.Dory.LOGGER;
import static me.vijaychavda.dory.Dory.STORAGE;

/**
 *
 * @author Vijay
 */
public class LoggerUtil {

	private static final int MAX_LOG_COUNT;
	private static final int MAX_LOG_SIZE;

	private static final DateFormat DATE_FORMAT;

	static {
		MAX_LOG_COUNT = 100;
		MAX_LOG_SIZE = 1 * 1024 * 1024;	// 1 MB
		DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd");
	}

	public static void addFileHandler(Logger logger) throws IOException {
		String pattern = MessageFormat.format("{0}/{1}_%u.%g.log",
			STORAGE.getLogsDir().getAbsolutePath(),
			DATE_FORMAT.format(new Date())
		);
		FileHandler handler
			= new FileHandler(pattern, MAX_LOG_SIZE, MAX_LOG_COUNT, true);
		handler.setFormatter(new SimpleFormatter());
		logger.addHandler(handler);
	}

	public static boolean logUDFA(Path path, String name) {
		File udfaLogFile = STORAGE.getUdfaLogFile();

		String entry = MessageFormat.format(
			"{0}\t[{1}::{2}]", new Date(), name, path.toString()
		);
		try (FileWriter writer = new FileWriter(udfaLogFile, true)) {
			writer.append(entry + "\n");
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "Failed to log UDFA: " + entry, ex);
			return false;
		}
		return true;
	}

}
