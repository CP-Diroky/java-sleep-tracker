package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

// Функция для расчета средней продолжительности сессии (в минутах)

public class AverageAmountOfSleep implements Function<List<SleepingSession>, Long> {

    private SleepAnalysisResult result;
    private long averageAmountOfSleep;

    @Override
    public Long apply(List<SleepingSession> sessions) {
        long sumAmountOfSleep = sessions.stream().mapToLong(session -> session.getSleepInMinutes()).sum();
        averageAmountOfSleep = sumAmountOfSleep / sessions.size();
        result = new SleepAnalysisResult<>(averageAmountOfSleep,
                "Cредняя продолжительность сессии (в минутах):");
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

