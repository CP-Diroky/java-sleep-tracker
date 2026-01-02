package ru.yandex.practicum.sleeptracker.functions;


import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalTime;
import java.util.List;

// Функция для определения типа пользователя

public class SleepType implements SleepingResultFunction<List<SleepingSession>, String> {

    private String sleepingType;
    private SleepAnalysisResult result;

    @Override
    public String apply(List<SleepingSession> sessions) {

        LocalTime sleepDeadline = LocalTime.of(6, 0);


        List<SleepingSession> nightsWithSleep = sessions.stream()
                .filter(session -> session.getSleepTime().getDayOfMonth() <
                        session.getWakeUpTime().getDayOfMonth()
                        || !(session.getSleepTime().getMonth().equals(session.getWakeUpTime().getMonth()))
                        || session.getSleepTime().getYear() < session.getWakeUpTime().getYear()
                        || session.getSleepTime().toLocalTime().isBefore(sleepDeadline))
                .toList();
        // тип сова
        int owlType = nightsWithSleep.stream()
                .filter(session -> (session.getSleepTime().toLocalTime()
                        .isAfter(LocalTime.of(23, 0))
                        || session.getSleepTime().toLocalTime()
                        .isBefore(LocalTime.of(9, 0)))
                        && session.getWakeUpTime().toLocalTime()
                        .isAfter(LocalTime.of(9, 0))).toList().size();
        // тип жаворонок
        int earlyBird = nightsWithSleep.stream()
                .filter(session -> session.getSleepTime().toLocalTime()
                        .isBefore(LocalTime.of(22, 0))
                        && session.getWakeUpTime().toLocalTime()
                        .isBefore(LocalTime.of(7, 0))).toList().size();
        // тип голубь
        int pigeonType = nightsWithSleep.size() - owlType - earlyBird;

        if (owlType > earlyBird && owlType > pigeonType) {
            sleepingType = "Сова";
        } else if (earlyBird > owlType && earlyBird > pigeonType) {
            sleepingType = "Жаворонок";
        } else {
            sleepingType = "Голубь";
        }

        result = new SleepAnalysisResult<>(sleepingType,
                "Тип пользователя");
        return sleepingType;
    }

    @Override
    public String getResult() {
        if (result == null) {
            return "Вызовите метод!";
        } else {
            return result.toString();
        }
    }

}


