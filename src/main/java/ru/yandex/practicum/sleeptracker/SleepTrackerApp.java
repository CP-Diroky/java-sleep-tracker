package ru.yandex.practicum.sleeptracker;


import ru.yandex.practicum.sleeptracker.functions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class SleepTrackerApp {


    public static void main(String[] args) {
        // считываем файл log.txt
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/main/resources/sleep_log.txt", StandardCharsets.UTF_8))) {
            final List<SleepingSession> sessions = bufferedReader.lines()
                    .map(session -> new SleepingSession(session)).toList();
            // Добавляем функции в лист functions и вызываем их
            List<Function> functions = new ArrayList<>();
            functions.add(new AverageAmountOfSleep());
            functions.add(new BadAmountOfSleep());
            functions.add(new CountOfSleepingSessions());
            functions.add(new MaxAmountOfSleep());
            functions.add(new MinimalAmountOfSleep());
            functions.add(new SleeplessNights());
            functions.add(new SleepType());
            functions.stream().peek(function -> function.apply(sessions)).toList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}