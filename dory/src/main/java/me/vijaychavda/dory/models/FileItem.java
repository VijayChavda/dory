package me.vijaychavda.dory.models;

import java.awt.Image;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import javax.swing.ImageIcon;
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
		setText(path.getFileName().toString());

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

	public Path getPath() {
		return path;
	}

	@Override
	public String toString() {
		return MessageFormat.format("FileItem[path={0}, text={1}, icon={2}]", path, getText(), getIcon());
	}

}
