package com.example.demoaggregator;

import java.util.ArrayList;
import java.util.List;

/**
 * Track input numbers and determine Min, Max and Average value
 */
public class MinMaxAggregator implements IAggregator {

    private double m_min = 0d;
    private double m_max = 0d;
    private double m_sum = 0d;
    private long   m_count = 0;

    @Override
    public void addNumber(Double dNumber) {
        if (dNumber < m_min || m_count == 0l) {
            m_min = dNumber;
        }
        if (dNumber > m_max || m_count == 0l) {
            m_max = dNumber;
        }
        m_sum += dNumber;
        m_count++;
    }

    @Override
    public String getOutput(IFormatter formatter) {
        List<AggregatorEntry> entries = new ArrayList<>();
        entries.add(new AggregatorEntry("Min", m_min));
        entries.add(new AggregatorEntry("Max", m_max));
        if (m_count > 0) {
            entries.add(new AggregatorEntry("Average", m_sum / m_count));
        }
        formatter.addItems(entries);
        return formatter.getOutput();
    }
}
