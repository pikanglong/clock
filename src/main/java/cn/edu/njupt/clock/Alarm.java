package cn.edu.njupt.clock;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author gaofan
 */
public class Alarm extends JPanel {

    public static ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
    private JCheckBox enableCheckbox = new JCheckBox();

    private Label time = new Label();
    private JFrame parent;
    public Alarm(int hour, int minute, JFrame parent){
        this.parent = parent;
        time.setText(String.format("%02d:%02d",hour,minute));
        add(time);
        add(enableCheckbox);

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
