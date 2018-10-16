package com.example.demoaggregator;

/**
 * Aggregator interface
 */
public interface IAggregator {

    /**
     * Submit new number to aggregator
     * @param dNumber
     */
    void addNumber(Double dNumber);

    /**
     * Retrieve the aggregator output formatted by given formatter
     * @param formatter
     * @return
     */
    String getOutput(IFormatter formatter);
}
