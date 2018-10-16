package com.example.demoaggregator;

public interface IAggregator {

    void addNumber(Double dNumber);

    String getOutput(IFormatter htmlFormatter);
}
