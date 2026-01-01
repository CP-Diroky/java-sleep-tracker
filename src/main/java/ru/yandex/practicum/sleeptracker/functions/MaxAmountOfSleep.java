package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

// Функция для расчета максимальной продолжительности сна (в минутах)
public class MaxAmountOfSleep implements Function<List<SleepingSession>, Long> {
    private SleepAnalysisResult result;
    private long maxAmountOfSleep;

    @Override
    public Long apply(List<SleepingSession> sessions) {
        maxAmountOfSleep = sessions.stream().
                max((session1, session2) -> (int) session1.getSleepInMinutes() -
                        (int) session2.getSleepInMinutes()).get().getSleepInMinutes();
        result = new SleepAnalysisResult<>(maxAmountOfSleep,
                "Максимальная продолжительность сессии (в минутах):");
        printResult();
        return (Long) result.getResult();
    }

    public void printResult() {
        if (result == null) {
            System.out.println("Вызовите сперва метод!");
        } else {
            System.out.println(result.getDescription() + " " + result.getResult());
        }
    }
}
