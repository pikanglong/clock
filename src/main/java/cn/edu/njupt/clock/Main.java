package cn.edu.njupt.clock;

import cn.edu.njupt.clock.component.Alarm;
import cn.edu.njupt.clock.component.LocalTimeLabel;
import cn.edu.njupt.clock.component.Watch;

import javax.swing.*;
import java.awt.*;
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
        setLookAndFeel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        clock.setBackground(Color.BLACK);
        clock.setLayout(new GridLayout(locals.length,1));
        for(String item : locals){
            String[] args = item.split(",");
            LocalTimeLabel localTimePanel = new LocalTimeLabel(ZoneId.of("GMT" + args[0]),args[1]);
            clock.add(localTimePanel);
            localTimePanelList.add(localTimePanel);
        }
        Timer timer = new Timer( 1000, e ->{
            for(LocalTimeLabel panel:localTimePanelList){
                panel.updateTime();
            }
        });
        timer.start();

        alarm.add(new Alarm(9, 0, this));

        Watch.init(watch, this);

        JPanel body = new JPanel();
        JPanel foot = new JPanel();
        CardLayout cardLayout = new CardLayout();
        body.setLayout(cardLayout);
        body.add("clock", clock);
        body.add("alarm", alarm);
        body.add("watch", watch);

        JButton clockButton = new JButton("世界时间");
        clockButton.addActionListener(actionEvent -> cardLayout.show(body, "clock"));

        JButton alarmButton = new JButton("闹钟");
        alarmButton.addActionListener(actionEvent -> cardLayout.show(body, "alarm"));

        JButton watchButton = new JButton("倒计时");
        watchButton.addActionListener(actionEvent -> cardLayout.show(body, "watch"));

        foot.add(clockButton);
        foot.add(alarmButton);
        foot.add(watchButton);

        getContentPane().add(body);
        getContentPane().add(foot, BorderLayout.SOUTH);
        setTitle("时钟");
        pack();
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}
