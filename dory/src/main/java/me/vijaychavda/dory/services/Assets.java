package me.vijaychavda.dory.services;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Vijay
 */
public class Assets {

	private final HashMap<String, Image> defaultAssets;

	public Assets() throws IOException {
		defaultAssets = new HashMap<>();

		String name = "/assets/default/";
		URL resource = Assets.class.getResource(name);
		if (resource == null)
			throw new IOException("Could not find Asset: " + name);

		File dir = new File(resource.getFile());
		if (!dir.exists() || !dir.canRead())
			throw new IOException("Could not find Asset: " + name);

		for (File file : dir.listFiles()) {
			BufferedImage image = ImageIO.read(file);
			defaultAssets.put(file.getName(), image);
		}
	}

	public Image getDefaultAsset(String name) {
		return defaultAssets.get(name);
	}

}
