package cn.edu.njupt.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * @author gaofan, pikanglong
 */
public class Main extends JFrame {
    private JPanel clock = new JPanel();
    private JPanel alarm = new JPanel();
    private JPanel watch = new JPanel();
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
        clock.setBackground(Color.BLACK);
        clock.setLayout(new GridLayout(locals.length,1));
        for(String item : locals){
            String[] args = item.split(",");
            LocalTimeLabel localTimePanel = new LocalTimeLabel(ZoneId.of("GMT" + args[0]),args[1]);
            clock.add(localTimePanel);
            localTimePanelList.add(localTimePanel);
        }
        Timer timer = new Timer(59 * 1000, e ->{
            for(LocalTimeLabel panel:localTimePanelList){
                panel.updateTime();
            }
        });
        timer.start();

        alarm.add(new Alarm(23, 33, this));

        watch.setBackground(Color.BLACK);

        JPanel body = new JPanel();
        JPanel foot = new JPanel();
        CardLayout cardLayout = new CardLayout();
        body.setLayout(cardLayout);
        body.add("clock", clock);
        body.add("alarm", alarm);
        body.add("watch", watch);

        JButton clockButton = new JButton("时间");
        clockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(body, "clock");
            }
        });

        JButton alarmButton = new JButton("闹钟");
        alarmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(body, "alarm");
            }
        });

        JButton watchButton = new JButton("倒计时");
        watchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(body, "watch");
            }
        });

        foot.add(clockButton);
        foot.add(alarmButton);
        foot.add(watchButton);

        getContentPane().add(body);
        getContentPane().add(foot, BorderLayout.SOUTH);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}
