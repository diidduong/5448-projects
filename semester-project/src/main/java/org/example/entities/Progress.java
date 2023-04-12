package org.example.entities;

public class Progress {
    private double value;

    Progress() {
        value = 0;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if (value >= 0 && value <= 100) {
            this.value = value;
        }
    }
}
