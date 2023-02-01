package oop.classes;

public class Time {

    private int hour;
    private int minutes;
    private int seconds;

    public Time(int hour, int minutes, int seconds) {
        String message = "Wrong value";
        if (hour >= 0 && hour <= 24) {
            if (minutes >= 0 && minutes <= 59) {
                if (seconds >= 0 && seconds <= 59) {
                    setHour(hour);
                    setMinute(minutes);
                    setSecond(seconds);
                }
            } else {
                System.out.println(message);
            }
        }

        setSecond(getSecond() >= 59 ? 0: getSecond());
        setMinute(getMinute() >= 59 ? 0: getMinute());
        setHour(getHour() >= 23 ? 0: getHour());
    }

    public Time nextSecond() {
        return new Time(hour, minutes, seconds + 1);
    }

    public void setTime(int hour, int minutes, int seconds) {
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        String hour = getHour() == 0 ? "00" : String.valueOf(getHour());
        String minutes = getMinute() == 0 ? "00" : String.valueOf(getMinute());
        String seconds = getSecond() == 0 ? "00" : String.valueOf(getSecond());
        return hour + "/" + minutes + "/" + seconds;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minutes;
    }

    public int getSecond() {
        return seconds;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minutes) {
        this.minutes = minutes;
    }

    public void setSecond(int seconds) {
        this.seconds = seconds;
    }
}
