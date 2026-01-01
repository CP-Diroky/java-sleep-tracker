package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult<T> {
    private T result;
    private String description;

    public SleepAnalysisResult(T result, String description) {
        this.result = result;
        this.description = description;
    }

    public T getResult() {
        return result;
    }

    public String getDescription() {
        return description;
    }
}
