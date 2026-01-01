package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* Функция для расчета количества бессонных ночей
    Есть сомнения в реализации данного класса, так как по условию задания сказано:
    "Временем логирования считаем интервал от начала первой сессии сна в файле до окончания последней.
    При этом считаем, что пользователь носит часы не снимая — то есть не было сессий сна, которые не попали бы в файл."
    Если принять данное допущение, то получается что человек не спал с 12.10.25 по 30.10.25, что выглядит нереально :)
 */
public class SleeplessNights implements Function<List<SleepingSession>, Integer> {

    private SleepAnalysisResult result;
    private int amountOfSleeplessNights;

    @Override
    public Integer apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return 0;
        }
        LocalTime sleepDeadline = LocalTime.of(6, 0);


        Set<LocalDate> nightsWithSleep = sessions.stream()
                .filter(session -> session.getSleepTime().getDayOfMonth() <
                        session.getWakeUpTime().getDayOfMonth()
                        || !(session.getSleepTime().getMonth().equals(session.getWakeUpTime().getMonth()))
                        || session.getSleepTime().getYear() < session.getWakeUpTime().getYear()
                        || session.getSleepTime().toLocalTime().isBefore(sleepDeadline))
                .map(session -> session.getWakeUpTime().toLocalDate()).collect(Collectors.toSet());

        final List<LocalDate> nightsWithSleepAsList = new ArrayList<>(nightsWithSleep);

        Comparator<LocalDate> comparator = Comparator.naturalOrder();

        nightsWithSleepAsList.sort(comparator);
        amountOfSleeplessNights = IntStream.range(0, nightsWithSleepAsList.size() - 1)
                .map(i -> (int) ChronoUnit.DAYS.between(
                        nightsWithSleepAsList.get(i),
                        nightsWithSleepAsList.get(i + 1)
                ) - 1)
                .filter(diff -> diff > 0)
                .sum();
        result = new SleepAnalysisResult<>(amountOfSleeplessNights,
                "Количество бессонных ночей:");
        printResult();
        return amountOfSleeplessNights;
    }

    public void printResult() {
        if (result == null) {
            System.out.println("Вызовите сперва метод!");
        } else {
            System.out.println(result.getDescription() + " " + result.getResult());
        }
    }
}




