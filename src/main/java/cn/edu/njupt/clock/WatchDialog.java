package cn.edu.njupt.clock;

import cn.edu.njupt.clock.utils.MP3Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class WatchDialog extends JDialog {
    private JButton jButton;
    private JLabel jLabel;
    private JPanel jPanel;
    private static MP3Player mp3Player;

    public WatchDialog(Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("倒计时");
        initComponents();
    }

    private void initComponents() {
        jButton = new JButton();
        jLabel = new JLabel();
        jPanel = new JPanel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jButton.setText("确定");
        jLabel.setText("时间到！");

        jPanel.add(jLabel);
        jPanel.add(jButton);

        add(jPanel);

        pack();
    }

    public static void create(JFrame parent) {
        WatchDialog watchDialog = new WatchDialog(parent, true);
        File file = new File("src/main/resources/sound/watch.mp3");
        mp3Player = new MP3Player(file.getAbsolutePath());
        mp3Player.start();
        watchDialog.jButton.addActionListener(e -> {
            watchDialog.setVisible(false);
            watchDialog.dispose();
        });
        watchDialog.setLocationRelativeTo(parent);
        watchDialog.setVisible(true);
        mp3Player.stop();
    }
}
