package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;
// Функция для расчета количества сессий с плохим качеством сна
public class BadAmountOfSleep implements Function<List<SleepingSession>, Integer> {

    private SleepAnalysisResult result;
    private int amountOfBadSleeps;

    @Override
    public Integer apply(List<SleepingSession> sessions) {
        List<SleepingSession> badSessions = sessions.stream()
                .filter(session -> session.getQualityOfSleep().equals("BAD")).toList();
        amountOfBadSleeps = badSessions.size();
        result = new SleepAnalysisResult<>(amountOfBadSleeps, "Количество сессий с плохим качество сна:");
        printResult();
        return (int) result.getResult();
    }


    public void printResult() {
        if (result == null) {
            System.out.println("Вызовите сперва метод!");
        } else {
            System.out.println(result.getDescription() + " " + result.getResult());
        }
    }
}
