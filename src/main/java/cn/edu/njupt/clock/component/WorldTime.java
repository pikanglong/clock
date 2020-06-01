package cn.edu.njupt.clock.component;

import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * @author gaofan
 */
public class WorldTime extends JPanel {

    private final String[] locals = new String[]{
            "-7,洛杉矶",
            "-4,纽约",
            "+1,伦敦",
            "+3,莫斯科",
            "+8,北京",
            "+9,东京",
    };
    private ArrayList<LocalTimeLabel> localTimePanelList = new ArrayList<>();

    public WorldTime(){
        setBackground(Color.BLACK);
        setLayout(new GridLayout(locals.length,1));
        for(String item : locals){
            String[] args = item.split(",");
            LocalTimeLabel localTimePanel = new LocalTimeLabel(ZoneId.of("UTC" + args[0]),args[1]);
            add(localTimePanel);
            localTimePanelList.add(localTimePanel);
        }
        Timer timer = new Timer( 1000, e ->{
            for(LocalTimeLabel panel:localTimePanelList){
                panel.updateTime();
            }
        });
        timer.start();
    }
}
