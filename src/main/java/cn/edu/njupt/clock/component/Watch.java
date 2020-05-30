package cn.edu.njupt.clock.component;

import cn.edu.njupt.clock.dialog.WatchDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Watch{
    private static JPanel parent;
    private static JFrame frame;
    private static int hour;
    private static int minute;
    private static int second;
    private static JPanel hPanel = new JPanel(new BorderLayout());
    private static JPanel mPanel = new JPanel(new BorderLayout());
    private static JPanel sPanel = new JPanel(new BorderLayout());
    private static JLabel hLabel = new JLabel();
    private static JLabel mLabel = new JLabel();
    private static JLabel sLabel = new JLabel();
    private static JLabel hDesc = new JLabel();
    private static JLabel mDesc = new JLabel();
    private static JLabel sDesc = new JLabel();
    private static JSpinner hSpinner;
    private static JSpinner mSpinner;
    private static JSpinner sSpinner;
    private static JButton button;
    private static Timer timer;
    private static TimerTask timerTask;

    private static ActionListener actionListener1 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Watch watch = new Watch();
            watch.start((int)hSpinner.getValue(), (int)mSpinner.getValue(), (int)sSpinner.getValue(), parent);
        }
    };

    private static ActionListener actionListener2 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            timer.cancel();
            timer = null;
            timerTask.cancel();
            timerTask = null;
        }
    };

    public static void init(JPanel p, JFrame f) {
        parent = p;
        frame = f;
        parent.setBackground(Color.BLACK);
        parent.setLayout(new GridLayout(4,1));

        hPanel.setBackground(Color.BLACK);
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 99, 1);
        hSpinner = new JSpinner(model);
        hSpinner.setFont(new Font("SansSerif",Font.PLAIN,38));
        hDesc.setForeground(Color.WHITE);
        hDesc.setFont(new Font("PingFang SC",Font.PLAIN,38));
        hDesc.setText("时");
        hPanel.add(hSpinner, "West");
        hPanel.add(hDesc, "East");
        parent.add(hPanel);

        mPanel.setBackground(Color.BLACK);
        model = new SpinnerNumberModel(0, 0, 59, 1);
        mSpinner = new JSpinner(model);
        mSpinner.setFont(new Font("SansSerif",Font.PLAIN,38));
        mDesc.setForeground(Color.WHITE);
        mDesc.setFont(new Font("PingFang SC",Font.PLAIN,38));
        mDesc.setText("分");
        mPanel.add(mSpinner, "West");
        mPanel.add(mDesc, "East");
        parent.add(mPanel);

        sPanel.setBackground(Color.BLACK);
        model = new SpinnerNumberModel(0, 0, 59, 1);
        sSpinner = new JSpinner(model);
        sSpinner.setFont(new Font("SansSerif",Font.PLAIN,38));
        sDesc.setForeground(Color.WHITE);
        sDesc.setFont(new Font("PingFang SC",Font.PLAIN,38));
        sDesc.setText("秒");
        sPanel.add(sSpinner, "West");
        sPanel.add(sDesc, "East");
        parent.add(sPanel);

        button = new JButton("开始");
        button.setFont(new Font("PingFang SC",Font.PLAIN,38));
        button.addActionListener(actionListener1);
        parent.add(button);
    }

    public void start(int hour, int minute, int second, JPanel parent) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;

        hPanel.remove(hSpinner);
        mPanel.remove(mSpinner);
        sPanel.remove(sSpinner);

        paint();

        parent.repaint();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                countdown();
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);

        button.setText("取消");
        button.removeActionListener(actionListener1);
        button.addActionListener(actionListener2);
    }

    private void countdown() {
        if (second > 0) {
            second--;
        } else if (minute > 0) {
            minute--;
            second = 59;
        } else if (hour > 0) {
            hour--;
            minute = 59;
            second = 59;
        } else {
            timer.cancel();
            timer = null;
            timerTask.cancel();
            timerTask = null;
            WatchDialog.create(frame);
            hPanel.remove(hLabel);
            mPanel.remove(mLabel);
            sPanel.remove(sLabel);
            SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 99, 1);
            hSpinner = new JSpinner(model);
            hSpinner.setFont(new Font("SansSerif",Font.PLAIN,38));
            hPanel.add(hSpinner, "West");
            model = new SpinnerNumberModel(0, 0, 59, 1);
            mSpinner = new JSpinner(model);
            mSpinner.setFont(new Font("SansSerif",Font.PLAIN,38));
            mPanel.add(mSpinner, "West");
            model = new SpinnerNumberModel(0, 0, 59, 1);
            sSpinner = new JSpinner(model);
            sSpinner.setFont(new Font("SansSerif",Font.PLAIN,38));
            sPanel.add(sSpinner, "West");
            button.setText("开始");
            button.removeActionListener(actionListener2);
            button.addActionListener(actionListener1);
            parent.repaint();
            return;
        }
        paint();
    }

    private void paint() {
        hLabel.setForeground(Color.WHITE);
        hLabel.setFont(new Font("SansSerif",Font.PLAIN,38));
        hLabel.setText(String.format("%02d", hour));
        hPanel.add(hLabel, "West");

        mLabel.setForeground(Color.WHITE);
        mLabel.setFont(new Font("SansSerif",Font.PLAIN,38));
        mLabel.setText(String.format("%02d", minute));
        mPanel.add(mLabel, "West");

        sLabel.setForeground(Color.WHITE);
        sLabel.setFont(new Font("SansSerif",Font.PLAIN,38));
        sLabel.setText(String.format("%02d", second));
        sPanel.add(sLabel, "West");

        parent.repaint();
    }
}
