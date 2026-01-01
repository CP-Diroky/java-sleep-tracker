package ru.yandex.practicum.sleeptracker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.*;


import java.util.ArrayList;
import java.util.List;

public class SleepTrackerAppTest {

    //Тесты для класса AverageAmountOfSleep
    @Test
    public void averageAmountOfSleepShouldThrowExceptionWhenListIsEmpty() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        AverageAmountOfSleep averageAmountOfSleep = new AverageAmountOfSleep();
        try {
            averageAmountOfSleep.apply(listOfSessions);
        } catch (ArithmeticException e) {
            Assertions.assertNotNull(e);
        }
    }

    @Test
    public void averageAmountOfSleepShouldBeNintyMinutes() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 10:00;01.10.25 11:00;GOOD"));
        listOfSessions.add(new SleepingSession("01.10.25 14:00;01.10.25 16:00;GOOD"));
        AverageAmountOfSleep averageAmountOfSleep = new AverageAmountOfSleep();
        Assertions.assertEquals(90, averageAmountOfSleep.apply(listOfSessions));
    }

    //Тесты для класса BadAmountOfSleep
    @Test
    public void badAmountShouldBeZeroWhenSessionsAreGood() {
    List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 10:00;01.10.25 12:00;GOOD"));
        listOfSessions.add(new SleepingSession("01.10.25 14:00;01.10.25 16:00;GOOD"));
        BadAmountOfSleep badAmountOfSleep = new BadAmountOfSleep();
        Assertions.assertEquals(0, badAmountOfSleep.apply(listOfSessions));
    }

    @Test
    public void badAmountShouldBeOneWhenOneSessionIsBad() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 10:00;01.10.25 12:00;BAD"));
        listOfSessions.add(new SleepingSession("01.10.25 14:00;01.10.25 16:00;GOOD"));
        BadAmountOfSleep badAmountOfSleep = new BadAmountOfSleep();
        Assertions.assertEquals(1, badAmountOfSleep.apply(listOfSessions));
    }

    @Test
    public void badAmountShouldBeTwoWhenTwoSessionsAreBad() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 10:00;01.10.25 12:00;BAD"));
        listOfSessions.add(new SleepingSession("01.10.25 14:00;01.10.25 16:00;BAD"));
        BadAmountOfSleep badAmountOfSleep = new BadAmountOfSleep();
        Assertions.assertEquals(2, badAmountOfSleep.apply(listOfSessions));
    }

    //Тесты для класса CountOfSleepingSessions
    @Test
    public void countOfSessionsShouldBeZeroWhenListIsEmpty() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        CountOfSleepingSessions countOfSleepingSessions = new CountOfSleepingSessions();
        Assertions.assertEquals(0, countOfSleepingSessions.apply(listOfSessions));
    }

    @Test
    public void countOfSessionsShouldBeTwo() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 10:00;01.10.25 12:00;BAD"));
        listOfSessions.add(new SleepingSession("01.10.25 14:00;01.10.25 16:00;BAD"));
        CountOfSleepingSessions countOfSleepingSessions = new CountOfSleepingSessions();
        Assertions.assertEquals(2, countOfSleepingSessions.apply(listOfSessions));
    }


    //Тесты для класса MaxAmountOfSleep
    @Test
    public void maxAmountOfSleepShouldEqualIfSessionIsOne() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 10:00;01.10.25 12:00;BAD"));
        MaxAmountOfSleep maxAmountOfSleep = new MaxAmountOfSleep();
        Assertions.assertEquals(120, maxAmountOfSleep.apply(listOfSessions));
    }

    @Test
    public void maxAmountOfSleepShouldBeThreeHours() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 10:00;01.10.25 11:00;BAD"));
        listOfSessions.add(new SleepingSession("02.10.25 10:00;02.10.25 12:00;BAD"));
        listOfSessions.add(new SleepingSession("02.10.25 23:00;03.10.25 02:00;BAD"));
        MaxAmountOfSleep maxAmountOfSleep = new MaxAmountOfSleep();
        Assertions.assertEquals(180, maxAmountOfSleep.apply(listOfSessions));
    }

    //Тесты для класса MinimalAmountOfSleep
    @Test
    public void minimalAmountOfSleepShouldEqualIfSessionIsOne() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 10:00;01.10.25 12:00;BAD"));
        MinimalAmountOfSleep minimalAmountOfSleep = new MinimalAmountOfSleep();
        Assertions.assertEquals(120, minimalAmountOfSleep.apply(listOfSessions));
    }

    @Test
    public void minimalAmountOfSleepShouldBeOneHour() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 10:00;01.10.25 11:00;BAD"));
        listOfSessions.add(new SleepingSession("02.10.25 10:00;02.10.25 12:00;BAD"));
        listOfSessions.add(new SleepingSession("02.10.25 23:00;03.10.25 02:00;BAD"));
        MinimalAmountOfSleep minimalAmountOfSleep = new MinimalAmountOfSleep();
        Assertions.assertEquals(60, minimalAmountOfSleep.apply(listOfSessions));
    }

    //Тесты для класса SleeplessNights
    @Test
    public void sleeplessNightsShouldBeZeroWhenAllNightsAreWithSleep() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 23:00;02.10.25 08:00;GOOD"));
        listOfSessions.add(new SleepingSession("02.10.25 23:00;03.10.25 08:00;GOOD"));
        listOfSessions.add(new SleepingSession("03.10.25 23:00;04.10.25 08:00;GOOD"));
        SleeplessNights sleeplessNights = new SleeplessNights();
        Assertions.assertEquals(0, sleeplessNights.apply(listOfSessions));
    }

    @Test
    public void sleeplessNightsShouldBeOne() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 23:00;02.10.25 08:00;GOOD"));
        listOfSessions.add(new SleepingSession("02.10.25 23:00;03.10.25 08:00;GOOD"));
        listOfSessions.add(new SleepingSession("03.10.25 23:00;04.10.25 08:00;GOOD"));
        listOfSessions.add(new SleepingSession("05.10.25 23:00;06.10.25 08:00;GOOD"));
        SleeplessNights sleeplessNights = new SleeplessNights();
        Assertions.assertEquals(1, sleeplessNights.apply(listOfSessions));
    }

    @Test
    public void sleeplessNightsShouldBeFive() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 23:00;02.10.25 08:00;GOOD"));
        listOfSessions.add(new SleepingSession("02.10.25 23:00;03.10.25 08:00;GOOD"));
        listOfSessions.add(new SleepingSession("03.10.25 23:00;04.10.25 08:00;GOOD"));
        listOfSessions.add(new SleepingSession("06.10.25 23:00;07.10.25 08:00;GOOD"));
        listOfSessions.add(new SleepingSession("10.10.25 23:00;11.10.25 08:00;GOOD"));
        SleeplessNights sleeplessNights = new SleeplessNights();
        Assertions.assertEquals(5, sleeplessNights.apply(listOfSessions));
    }


    @Test
    public void sleeplessNightsShouldBeZeroWithDaySleep() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 10:00;01.10.25 11:00;NORMAL")); //Добавим дневной сон
        SleeplessNights sleeplessNights = new SleeplessNights();
        Assertions.assertEquals(0, sleeplessNights.apply(listOfSessions));
    }

    //Тесты для класса SleepType
    @Test
    public void typeShouldBeOwl() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 01:00;01.10.25 11:00;NORMAL"));
        listOfSessions.add(new SleepingSession("02.10.25 01:00;02.10.25 11:00;NORMAL"));
        listOfSessions.add(new SleepingSession("02.10.25 21:00;03.10.25 06:00;NORMAL"));
        SleepType sleepType = new SleepType();
        Assertions.assertEquals("Сова", sleepType.apply(listOfSessions));
    }

    @Test
    public void typeShouldBeEarlyBird() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 21:00;02.10.25 06:00;NORMAL"));
        listOfSessions.add(new SleepingSession("02.10.25 21:00;03.10.25 06:00;NORMAL"));
        listOfSessions.add(new SleepingSession("04.10.25 02:00;04.10.25 10:00;NORMAL"));
        SleepType sleepType = new SleepType();
        Assertions.assertEquals("Жаворонок", sleepType.apply(listOfSessions));
    }

    @Test
    public void typeShouldBePigeon() {
        List<SleepingSession> listOfSessions = new ArrayList<>();
        listOfSessions.add(new SleepingSession("01.10.25 10:00;01.10.25 12:00;BAD"));
        listOfSessions.add(new SleepingSession("01.10.25 14:00;01.10.25 16:00;BAD"));
        SleepType sleepType = new SleepType();
        Assertions.assertEquals("Голубь", sleepType.apply(listOfSessions));
    }









}