package cn.edu.njupt.clock.component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @author gaofan
 */
public class LocalTimeLabel extends JPanel {

    private JLabel date = new JLabel();
    private JLabel local = new JLabel();
    private JPanel group = new JPanel(new GridLayout(2,1));
    private JLabel time = new JLabel();

    private ZoneId zoneId;
    private String zoneText;

    public LocalTimeLabel(ZoneId zoneId, String zoneText){
        super(new BorderLayout());
        setBackground(Color.BLACK);
        setBorder(new EmptyBorder(10, 24, 10, 24));
        group.setBackground(Color.BLACK);
        date.setForeground(Color.LIGHT_GRAY);
        date.setFont(new Font("PingFang SC",Font.PLAIN,13));
        local.setForeground(Color.WHITE);
        local.setFont(new Font("PingFang SC",Font.PLAIN,26));
        time.setForeground(Color.WHITE);
        time.setFont(new Font("SansSerif",Font.PLAIN,38));
        group.setBorder(new EmptyBorder(0,0, 0, 50));
        group.add(date);
        group.add(local);
        add(group,"West");
        add(time,"East");

        this.zoneId = zoneId;
        this.zoneText = zoneText;
        updateTime();
    }

    public void updateTime(){
        LocalDateTime cur = LocalDateTime.now(zoneId);
        date.setText(cur.getMonthValue() + "月" + cur.getDayOfMonth() + "日" + cur.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINA));
        local.setText(zoneText);
        time.setText(String.format("%02d:%02d",cur.getHour(),cur.getMinute()));

    }
}
