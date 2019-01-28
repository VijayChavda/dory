package me.vijaychavda.dory.models;

import java.io.File;
import java.nio.file.Path;
import java.text.MessageFormat;

/**
 *
 * @author Vijay
 */
public class FileItem extends Item {

	private final File file;

	public FileItem(Path path) {
		this(path.toFile());
	}

	public FileItem(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	@Override
	public String toString() {
		return MessageFormat.format("FileItem[file={0}, text={1}, icon={2}]", file, getText(), getIcon());
	}

}
