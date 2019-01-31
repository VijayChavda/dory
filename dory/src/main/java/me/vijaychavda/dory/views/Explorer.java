package me.vijaychavda.dory.views;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import static me.vijaychavda.dory.Dory.DORY;
import static me.vijaychavda.dory.Dory.LOGGER;
import me.vijaychavda.dory.models.FileItem;
import me.vijaychavda.dory.models.Items;
import me.vijaychavda.dory.utils.FileUtils;
import me.vijaychavda.dory.workers.Crawler;

/**
 *
 * @author Vijay
 */
public class Explorer extends javax.swing.JFrame {

	private Path dir;
	private final Items dirContent;

	public Explorer() {
		initComponents();

		dir = FileUtils.getFileSystemRoots().get(0);
		dirContent = new Items();
		itemsView.setContext(dirContent);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowOpened(WindowEvent e) {
				navigate(dir);
			}
		});
	}

	public void navigate(Path path) {
		if (path == null)
			return;

		setTitle(DORY + " - " + path.toString());
		addressBar.setText(path.toString());

		dir = path;
		dirContent.clear();

		Crawler crawler = new Crawler(path, "glob", "*", 1) {
			@Override
			public void found(Path path) {
				dirContent.add(new FileItem(path));
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

        scrollPane = new javax.swing.JScrollPane();
        itemsView = new me.vijaychavda.dory.views.ItemsView();
        navBar = new javax.swing.JPanel();
        upBtn = new javax.swing.JLabel();
        addressBar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 500));

        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(itemsView);

        getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);

        upBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/up.png"))); // NOI18N
        upBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                upBtnMouseClicked(evt);
            }
        });

        addressBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressBarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout navBarLayout = new javax.swing.GroupLayout(navBar);
        navBar.setLayout(navBarLayout);
        navBarLayout.setHorizontalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(upBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addContainerGap())
        );
        navBarLayout.setVerticalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(addressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(upBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        navBarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addressBar, upBtn});

        getContentPane().add(navBar, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addressBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressBarActionPerformed
		String str = addressBar.getText().trim();
		navigate(Paths.get(str));
    }//GEN-LAST:event_addressBarActionPerformed

    private void upBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upBtnMouseClicked
		navigate(dir.getParent());
    }//GEN-LAST:event_upBtnMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressBar;
    private me.vijaychavda.dory.views.ItemsView itemsView;
    private javax.swing.JPanel navBar;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel upBtn;
    // End of variables declaration//GEN-END:variables
	//</editor-fold>
}
