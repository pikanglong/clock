package cn.edu.njupt.clock.component;

import cn.edu.njupt.clock.dialog.AlarmDialog;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author gaofan
 */
public class Alarm extends JPanel {

    public static ScheduledExecutorService pool;
    private JCheckBox enableCheckbox = new JCheckBox();
    private JPanel setter = new JPanel(new FlowLayout());
    private JSpinner hSpinner = new JSpinner(new SpinnerNumberModel(9, 0, 23, 1));
    private JSpinner mSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
    private JButton apply = new JButton("设置时间");

    private JLabel time = new JLabel();
    private JLabel current = new JLabel();
    private JFrame parent;

    public Alarm(int hour, int minute, JFrame parent){
        super(new GridLayout(4,1));
        this.parent = parent;
        time.setFont(new Font("PingFang SC",Font.PLAIN,38));
        enableCheckbox.setSelected(true);
        enableCheckbox.setText("启用闹钟");
        setter.add(hSpinner);
        setter.add(mSpinner);
        setter.add(apply);

        add(current);
        add(time);
        add(enableCheckbox);
        add(setter);
        updateAlarmTime(hour,minute);

        apply.addActionListener(e -> {
            updateAlarmTime((int)hSpinner.getValue(), (int)mSpinner.getValue());
        });

        new Timer( 1000, e ->{
            LocalDateTime cur = LocalDateTime.now();
            current.setText(cur.getMonthValue() + "月" + cur.getDayOfMonth() + "日  " +
                    cur.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINA)
            + String.format("  %02d:%02d",cur.getHour(),cur.getMinute()));
        }).start();
    }

    private void updateAlarmTime(int hour, int minute){
        time.setText(String.format("每天 %02d:%02d",hour,minute));

        if(pool != null){
            pool.shutdownNow();
        }
        pool = Executors.newScheduledThreadPool(2);

        LocalTime cur = LocalTime.of(hour,minute);
        long first = Duration.between(LocalTime.now(),cur).toMillis();
        if(first < 0){
            first += 24L * 60 * 60 * 1000;
        }
        pool.scheduleAtFixedRate(this::active, first,24L * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
    }

    private void active(){
        if(!enableCheckbox.isSelected()){
            return;
        }
        int result = AlarmDialog.create(parent);
        if(result == 0){
            pool.schedule(this::active,10,TimeUnit.MINUTES);
        }
    }
}
