package org.example.entities;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author Duy Duong & Ahmed Biby
 * Progress class with observable value for UI update
 */
public class Progress {
    private final DoubleProperty value = new SimpleDoubleProperty();

    public Progress(double value) {
        setValue(value);
    }

    public double getValue() {
        return value.getValue();
    }

    public void setValue(double value) {
        if (value >= 0 && value <= 1) {
            this.value.setValue(value);
        }
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    public void reset() {
        setValue(0);
    }
}
