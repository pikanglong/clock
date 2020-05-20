package cn.edu.njupt.clock;

import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * @author gaofan
 */
public class Main extends JFrame {
    private JPanel parent = new JPanel();
    private JLabel title = new JLabel("世界时钟");
    private final String[] locals = new String[]{
            "-7,洛杉矶",
            "-4,纽约",
            "+1,伦敦",
            "+3,莫斯科",
            "+8,北京",
            "+9,东京",
    };
    private ArrayList<LocalTimeLabel> localTimePanelList = new ArrayList<>();

    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("PinFang SC",Font.BOLD,20));
        parent.setBackground(Color.BLACK);
        parent.setLayout(new GridLayout(locals.length,1));
        for(String item : locals){
            String[] args = item.split(",");
            LocalTimeLabel localTimePanel = new LocalTimeLabel(ZoneId.of("GMT" + args[0]),args[1]);
            parent.add(localTimePanel);
            localTimePanelList.add(localTimePanel);
        }
        Timer timer = new Timer(59 * 1000, e ->{
            for(LocalTimeLabel panel:localTimePanelList){
                panel.updateTime();
            }
        });
        timer.start();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);
        getContentPane().add(title,"North");
        getContentPane().add(parent,"Center");
        getContentPane().add(new Alarm(20,26,this),"South");
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}
