package me.vijaychavda.dory.utils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.SwingWorker;

/**
 *
 * @author Vijay
 */
public abstract class Crawler {

	private final Path start;
	private final String syntax;
	private final String pattern;

	public Crawler(Path start, String syntax, String pattern) {
		this.start = start;
		this.syntax = syntax;
		this.pattern = pattern;
	}

	public void crawl() throws IOException {
		new SwingWorker<Void, Path>() {
			@Override
			protected Void doInBackground() throws Exception {
				PathMatcher matcher = FileSystems.getDefault().getPathMatcher(syntax + ":" + pattern);
				try (Stream<Path> stream = Files.walk(start)) {
					stream.forEach((Path path) -> {
						Path name = path.getFileName();
						if (name != null && matcher.matches(name)) {
							publish(path);
						}
					});
				}
				return null;
			}

			@Override
			protected void process(List<Path> paths) {
				for (Path chunk : paths) {
					found(chunk);
				}
			}
		}.execute();
	}

	public abstract void found(Path path);

	public Path getStart() {
		return start;
	}

	public String getSyntax() {
		return syntax;
	}

	public String getPattern() {
		return pattern;
	}

}
