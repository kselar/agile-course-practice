package ru.unn.agile.ConverterWeight.viewmodel;

import ru.unn.agile.ConverterWeight.Model.ConverterWeight;

public class ViewModel {
    private String value;
    private String result;
    private String status;
    private UnitWeight valueUnit;
    private UnitWeight resultUnit;
    private boolean convertButton;

    public ViewModel() {
        value = "";
        result = "";
        status = Status.WAITING;
        valueUnit = UnitWeight.GRAMM;
        resultUnit = UnitWeight.GRAMM;
        convertButton = false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getResult() {
        return result;
    }

    public void setResult(final String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public UnitWeight getValueUnit() {
        return valueUnit;
    }

    public UnitWeight getResultUnit() {
        return resultUnit;
    }

    public boolean isConvertButton() {
        return convertButton;
    }

    public void setValueUnit(final UnitWeight valueUnit) {
        this.valueUnit = valueUnit;
    }

    public void setResultUnit(final UnitWeight resultUnit) {
        this.resultUnit = resultUnit;
    }

    public void convert() {
        prepareForConvert();

        if (status == Status.READY && convertButton) {
            ConverterWeight converterWeight = new ConverterWeight();
            result = Double.toString(converterWeight.converter(valueUnit.toString(),
                                                               resultUnit.toString(),
                                                               Double.parseDouble(value)));
            status = Status.SUCCESS;
        }
    }

    public boolean isRightValue() {
        try {
            if (value.isEmpty()) {
                status = Status.WAITING;
            } else {
                Double.parseDouble(value);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void prepareForConvert() {
        if (isRightValue()) {
            convertButton = true;
            status = Status.READY;
        } else {
            convertButton = false;
            status = Status.BAD_FORMAT;
        }
    }

    public final class Status {
        public static final String WAITING = "Please, input value";
        public static final String BAD_FORMAT = "Bad format";
        public static final String READY = "Please, press 'Convert'";
        public static final String SUCCESS = "Success";

        private Status() { }
    }

    public enum UnitWeight {
        GRAMM("GRAMM"),
        KILOGRAM("KILOGRAM"),
        CENTNER("CENTNER"),
        TON("TON");
        private final String name;

        private UnitWeight(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }
}
