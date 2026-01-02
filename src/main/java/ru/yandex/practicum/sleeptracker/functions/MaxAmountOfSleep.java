package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

// Функция для расчета максимальной продолжительности сна (в минутах)

public class MaxAmountOfSleep implements SleepingResultFunction<List<SleepingSession>, Long> {
    private SleepAnalysisResult result;
    private long maxAmountOfSleep;

    @Override
    public Long apply(List<SleepingSession> sessions) {
        maxAmountOfSleep = sessions.stream()
                .max((session1, session2) -> (int) session1.getSleepInMinutes() -
                        (int) session2.getSleepInMinutes()).get().getSleepInMinutes();
        result = new SleepAnalysisResult<>(maxAmountOfSleep,
                "Максимальная продолжительность сессии (в минутах)");
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
