package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

// Функция для расчета количества сессий с плохим качеством сна

public class BadAmountOfSleep implements SleepingResultFunction<List<SleepingSession>, Integer> {

    private SleepAnalysisResult result;
    private int amountOfBadSleeps;

    @Override
    public Integer apply(List<SleepingSession> sessions) {
        List<SleepingSession> badSessions = sessions.stream()
                .filter(session -> session.getQualityOfSleep().equals("BAD")).toList();
        amountOfBadSleeps = badSessions.size();
        result = new SleepAnalysisResult<>(amountOfBadSleeps, "Количество сессий с плохим качество сна");
        return (int) result.getResult();
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
