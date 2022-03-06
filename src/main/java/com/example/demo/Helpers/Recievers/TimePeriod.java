package com.example.demo.Helpers.Recievers;

import java.io.Serializable;
import java.time.LocalDate;

public class TimePeriod implements Serializable {
    private LocalDate startingDatePeriod;
    private LocalDate endingDatePeriod;

    public TimePeriod(LocalDate startingDatePeriod, LocalDate endingDatePeriod) {
        this.startingDatePeriod = startingDatePeriod;
        this.endingDatePeriod = endingDatePeriod;
    }

    public TimePeriod() {
    }

    public LocalDate getStartingDatePeriod() {
        return startingDatePeriod;
    }

    public void setStartingDatePeriod(LocalDate startingDatePeriod) {
        this.startingDatePeriod = startingDatePeriod;
    }

    public LocalDate getEndingDatePeriod() {
        return endingDatePeriod;
    }

    public void setEndingDatePeriod(LocalDate endingDatePeriod) {
        this.endingDatePeriod = endingDatePeriod;
    }
}
