package me.vijaychavda.dory.views;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.logging.Level;
import static me.vijaychavda.dory.Dory.LOGGER;
import me.vijaychavda.dory.models.FileItem;
import me.vijaychavda.dory.models.Items;
import me.vijaychavda.dory.utils.Crawler;

/**
 *
 * @author Vijay
 */
public class Explorer extends javax.swing.JFrame {

	private final ItemsView itemsView;

	public Explorer() {
		initComponents();

		itemsView = new ItemsView(new Items());
		this.add(itemsView, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowActivated(WindowEvent e) {
				Path root = FileSystems.getDefault().getRootDirectories().iterator().next();
				navigate(root);
			}
		});
	}

	public void navigate(Path path) {
		Crawler crawler = new Crawler(path, "glob", "*", 1) {

			int index = 0;

			@Override
			public void found(Path path) {
				FileItem item = new FileItem(path);
				item.setText(path.getFileName().toString());
				itemsView.getContext().add(index++, item);
				itemsView.reload();
			}
		};
		try {
			crawler.crawl();
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}

	//<editor-fold defaultstate="collapsed" desc="Netbeans code">
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
	//</editor-fold>
}
