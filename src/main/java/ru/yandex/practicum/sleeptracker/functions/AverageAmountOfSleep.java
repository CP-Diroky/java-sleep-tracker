package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

// Функция для расчета средней продолжительности сессии (в минутах)

public class AverageAmountOfSleep implements SleepingResultFunction<List<SleepingSession>, Long> {

    private SleepAnalysisResult result;
    private long averageAmountOfSleep;

    @Override
    public Long apply(List<SleepingSession> sessions) {
        long sumAmountOfSleep = sessions.stream().mapToLong(session -> session.getSleepInMinutes()).sum();
        averageAmountOfSleep = sumAmountOfSleep / sessions.size();
        result = new SleepAnalysisResult<>(averageAmountOfSleep,
                "Cредняя продолжительность сессии (в минутах)");
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

