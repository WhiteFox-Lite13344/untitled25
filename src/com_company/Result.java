package com_company;



public interface Result  {
    void setValue(String value, long id);
    void print();
    long getId();
    boolean isValid(String value);
}