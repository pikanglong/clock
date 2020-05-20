package cn.edu.njupt.clock.entity;

import java.awt.*;
import java.io.Serializable;

public class AlarmEntity implements Serializable {

    public static final long serialVersionUID = 1234567890L;

    private int hour;
    private int minute;
    private boolean active;

    public AlarmEntity() {
    }

    public AlarmEntity(int hour, int minute, boolean active) {
        this.hour = hour;
        this.minute = minute;
        this.active = active;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "AlarmEntity{" +
                "hour=" + hour +
                ", minute=" + minute +
                ", active=" + active +
                '}';
    }
}
