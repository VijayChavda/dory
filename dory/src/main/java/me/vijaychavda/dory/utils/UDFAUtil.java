package me.vijaychavda.dory.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import static me.vijaychavda.dory.Dory.DORY;

/**
 *
 * @author Vijay
 */
public class UDFAUtil {

	public static void add(Path path, String name, String value) throws IOException {
		UserDefinedFileAttributeView view
			= Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);

		view.write(DORY + "-" + name, Charset.defaultCharset().encode(value));

		LoggerUtil.logUDFA(path, name);
	}

	public static String get(Path path, String name) throws IOException {
		UserDefinedFileAttributeView view
			= Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);

		ByteBuffer buffer = ByteBuffer.allocate(view.size(name));
		view.read(name, buffer);
		buffer.flip();

		return Charset.defaultCharset().decode(buffer).toString();
	}

	public static ArrayList<String> getAll(Path path) throws IOException {
		UserDefinedFileAttributeView view
			= Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);

		ArrayList<String> values = new ArrayList<>();
		for (String name : view.list()) {
			if (name.startsWith(DORY + "-")) {
				ByteBuffer buffer = ByteBuffer.allocate(view.size(name));
				view.read(name, buffer);
				buffer.flip();
				values.add(Charset.defaultCharset().decode(buffer).toString());
			}
		}

		return values;
	}

	public static void remove(Path path, String name) throws IOException {
		UserDefinedFileAttributeView view
			= Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);

		view.delete(name);
	}

	public static void removeAll(Path path) throws IOException {
		UserDefinedFileAttributeView view
			= Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);

		for (String name : view.list()) {
			view.delete(name);
		}
	}

}
