package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

// Функция для расчета минимальной продолжительности сна(в минутах)

public class MinimalAmountOfSleep implements SleepingResultFunction<List<SleepingSession>, Long> {

    private SleepAnalysisResult result;
    private long minAmountOfSleep;

    @Override
    public Long apply(List<SleepingSession> sessions) {
        minAmountOfSleep = sessions.stream()
                .min((session1, session2) -> (int) session1.getSleepInMinutes() -
                        (int) session2.getSleepInMinutes()).get().getSleepInMinutes();
        result = new SleepAnalysisResult<>(minAmountOfSleep,
                "Минимальная продолжительность сессии (в минутах)");
        return (Long) result.getResult();
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
