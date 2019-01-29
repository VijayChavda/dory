package me.vijaychavda.dory.utils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vijay
 */
public class FileUtils {

	public static String getExtension(Path file) {
		String name = file.getFileName().toString();

		if (name.isEmpty())
			return name;

		int dotIndex = name.lastIndexOf(".");

		if (dotIndex == -1)
			return "";

		return name.substring(dotIndex, name.length());
	}

	public static String getType(Path file) {
		try {
			String type = Files.probeContentType(file);
			return type == null ? "file" : type;
		} catch (IOException ex) {
			return "file";
		}
	}

	public static List<Path> getFileSystemRoots() {
		ArrayList<Path> list = new ArrayList<>();

		for (Path root : FileSystems.getDefault().getRootDirectories()) {
			list.add(root);
		}

		return list;
	}
}
