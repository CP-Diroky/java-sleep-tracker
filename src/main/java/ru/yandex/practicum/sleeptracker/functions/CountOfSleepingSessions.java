package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

// Функция для расчета количества всех сессий сна

public class CountOfSleepingSessions implements SleepingResultFunction<List<SleepingSession>, Integer> {

    private SleepAnalysisResult result;

    @Override
    public Integer apply(List<SleepingSession> sessions) {
        result = new SleepAnalysisResult<>(sessions.size(), "Количество сессий сна");
        return (Integer) result.getResult();
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
