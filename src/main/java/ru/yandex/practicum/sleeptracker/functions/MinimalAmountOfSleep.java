package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

// Функция для расчета минимальной продолжительности сна(в минутах)
public class MinimalAmountOfSleep implements Function<List<SleepingSession>, Long> {

    private SleepAnalysisResult result;
    private long minAmountOfSleep;

    @Override
    public Long apply(List<SleepingSession> sessions) {
        minAmountOfSleep = sessions.stream().
                min((session1, session2) -> (int) session1.getSleepInMinutes() -
                        (int) session2.getSleepInMinutes()).get().getSleepInMinutes();
        result = new SleepAnalysisResult<>(minAmountOfSleep,
                "Минимальная продолжительность сессии (в минутах):");
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
