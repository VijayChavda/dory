package me.vijaychavda.dory.models;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import javax.swing.ImageIcon;
import me.vijaychavda.dory.Dory;
import static me.vijaychavda.dory.Dory.ASSETS;
import me.vijaychavda.dory.utils.FileUtils;

/**
 *
 * @author Vijay
 */
public class FileItem extends Item {

	private final Path path;

	public FileItem(Path path) {
		this.path = path;

		init();
	}

	private void init() {
		if (path.getFileName() != null)
			setText(path.getFileName().toString());
		else
			setText("root");

		String type = FileUtils.getType(path);
		Image asset = ASSETS.getDefaultAsset(type);
		if (asset == null) {
			String mainType = type.split("/")[0];
			asset = ASSETS.getDefaultAsset(mainType);
			if (asset == null) {
				if (Files.isDirectory(path)) {
					asset = ASSETS.getDefaultAsset("directory");
				} else
					asset = ASSETS.getDefaultAsset("file");
			}
		}
		setIcon(new ImageIcon(asset));
	}

	@Override
	public void action() throws IOException, SecurityException {
		if (!Files.isReadable(path))
			throw new SecurityException("You do not have read permission on: " + path);

		if (Files.isDirectory(path)) {
			Dory.getExplorer().navigate(path);
			return;
		}

		if (Files.isRegularFile(path)) {
			FileUtils.openWithDefaultProgram(path);
		}
	}

	public Path getPath() {
		return path;
	}

	@Override
	public String toString() {
		return MessageFormat.format("FileItem[path={0}, text={1}, icon={2}]", path, getText(), getIcon());
	}

}
