package oop.composition;

public class MyTime {

    private int hour;
    private int minutes;
    private int seconds;

    public MyTime() {
    }

    public MyTime(int hour, int minutes, int seconds) {
        setTime(hour, minutes, seconds);
    }

    public void setTime(int hour, int minutes, int seconds) {
        if (hour >= 0 && hour <= 24) {
            if (minutes >= 0 && minutes <= 60) {
                if (seconds >= 0 && seconds <= 60) {
                    if (seconds == 60) {
                        this.seconds = 0;
                        minutes++;
                        if (minutes == 60) {
                            this.minutes = 0;
                            hour++;
                            if (hour == 24) {
                                this.hour = 0;
                            } else {
                                this.hour = hour;
                            }
                        } else {
                            this.minutes = minutes;
                            this.hour = hour;
                        }
                    } else {
                        this.hour = hour;
                        this.minutes = minutes;
                        this.seconds = seconds;
                    }
                } else {
                    System.out.println("Wrong seconds");
                }
            } else {
                System.out.println("Wrong minutes");
            }
        } else {
            System.out.println("Wrong hour");
        }
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return hour +
                "/" + minutes +
                "/" + seconds;
    }

    public MyTime nextSecond() {
        return new MyTime(getHour(), getMinutes(), getSeconds() + 1);
    }

    public MyTime nextMinutes() {
        return new MyTime(getHour(), getMinutes() + 1, getSeconds());
    }

    public MyTime nextHour() {
        return new MyTime(getHour() + 1, getMinutes(), getSeconds());
    }
}
