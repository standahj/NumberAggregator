package com.example.demoaggregator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MinMaxAggregatorTest {

    @Test
    public void aggregatorValidNumbers() {
        IAggregator aggregator = new MinMaxAggregator();

        InputValidator.validateInput("10").stream().forEach(n -> aggregator.addNumber(n));
        String result = aggregator.getOutput(new TextFormatter());
        assertThat(result, is("Min, Max, Average: 10.0, 10.0, 10.0\n"));

        InputValidator.validateInput("10, 10, 10").stream().forEach(n -> aggregator.addNumber(n));
        result = aggregator.getOutput(new TextFormatter());
        assertThat(result, is("Min, Max, Average: 10.0, 10.0, 10.0\n"));

        InputValidator.validateInput("-10, 20, -10, 20").stream().forEach(n -> aggregator.addNumber(n));
        result = aggregator.getOutput(new TextFormatter()); //sum is 60, count is 8
        assertThat(result, is("Min, Max, Average: -10.0, 20.0, 7.5\n"));
    }

    @Test
    public void aggregatorInvalidNumbers() {
        IAggregator aggregator = new MinMaxAggregator();

        InputValidator.validateInput("10r").stream().forEach(n -> aggregator.addNumber(n));
        String result = aggregator.getOutput(new TextFormatter());
        assertThat(result, is("Min, Max, Average: 0.0, 0.0\n"));

        InputValidator.validateInput("=10=, , <let me in>").stream().forEach(n -> aggregator.addNumber(n));
        result = aggregator.getOutput(new TextFormatter());
        assertThat(result, is("Min, Max, Average: 0.0, 0.0\n"));

        // give it one valid input number
        InputValidator.validateInput("--10, 20.5, +-10, fooo").stream().forEach(n -> aggregator.addNumber(n));
        result = aggregator.getOutput(new TextFormatter());
        assertThat(result, is("Min, Max, Average: 20.5, 20.5, 20.5\n"));
    }
}
