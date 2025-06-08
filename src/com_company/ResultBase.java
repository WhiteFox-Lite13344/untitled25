package com_company;

public class ResultBase implements Result {
    private String value;
    private long id;
    public ResultBase() {
        this("",0L);
    }

    public ResultBase(String value, long id) {
        this.value = value;
        this.id = id;
    }

    @Override
    public void setValue(String value, long id) {
        this.value = safe(value);
        this.id = id;
    }

    @Override
    public void print() {
        if(!isValid(value)){
            value = "";
        }
    }

    @Override
    public boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }

    @Override
    public long getId() {
        return id;
    }

    private String safe(String value) {
        return value == null ? "" : value.trim();
    }

    @Override
    public String toString() {
        return value;
    }
}
