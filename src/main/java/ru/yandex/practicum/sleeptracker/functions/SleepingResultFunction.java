package ru.yandex.practicum.sleeptracker.functions;

public interface SleepingResultFunction<T, R> {

    R apply(T t);

    String getResult();
}
