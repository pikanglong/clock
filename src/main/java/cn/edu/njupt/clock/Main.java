package cn.edu.njupt.clock;

import cn.edu.njupt.clock.component.Alarm;
import cn.edu.njupt.clock.component.LocalTimeLabel;
import cn.edu.njupt.clock.component.Watch;
import cn.edu.njupt.clock.component.WorldTime;

import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * @author gaofan, pikanglong
 */
public class Main extends JFrame {

    private JPanel alarm = new JPanel();
    private JPanel watch = new JPanel();


    {
        setLookAndFeel();
        //显示在屏幕中间
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        alarm.add(new Alarm(9, 0, this));

        Watch.init(watch, this);

        JPanel body = new JPanel();
        JPanel foot = new JPanel(new FlowLayout());
        CardLayout cardLayout = new CardLayout();
        body.setLayout(cardLayout);
        body.add("clock", new WorldTime());
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
