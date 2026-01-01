package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class SleepingSession {
    private  String[] array;
    private  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private  LocalDateTime sleepTime;
    private  LocalDateTime wakeUpTime;
    private  Duration sleep;


    public SleepingSession(String session) {
        array = session.split(";");
        sleepTime = LocalDateTime.parse(array[0], formatter);
        wakeUpTime = LocalDateTime.parse(array[1], formatter);
        sleep = Duration.between(sleepTime, wakeUpTime);
    }

    public String getQualityOfSleep(){
        return array[2];
    }

    public long getSleepInMinutes() {
        return sleep.toMinutes();
    }

    public LocalDateTime getSleepTime() {
        return sleepTime;
    }

    public LocalDateTime getWakeUpTime() {
        return wakeUpTime;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SleepingSession that = (SleepingSession) o;
        return Objects.deepEquals(array, that.array) && Objects.equals(formatter, that.formatter)
                && Objects.equals(sleepTime, that.sleepTime) && Objects.equals(wakeUpTime, that.wakeUpTime)
                && Objects.equals(sleep, that.sleep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), formatter, sleepTime, wakeUpTime, sleep);
    }
}
