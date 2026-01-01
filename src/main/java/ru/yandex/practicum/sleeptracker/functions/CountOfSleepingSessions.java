package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;
// Функция для расчета количества всех сессий сна
public class CountOfSleepingSessions implements Function<List<SleepingSession>, Integer> {

    private SleepAnalysisResult result;

    @Override
    public Integer apply(List<SleepingSession> sessions) {
        result = new SleepAnalysisResult<>(sessions.size(), "Количество сессий сна:");
        printResult();
        return (Integer) result.getResult();
    }


    public void printResult() {
        if (result == null) {
            System.out.println("Вызовите сперва метод!");
        } else {
            System.out.println(result.getDescription() + " " + result.getResult());
        }
    }
}
