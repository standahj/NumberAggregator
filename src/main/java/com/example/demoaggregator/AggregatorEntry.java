package com.example.demoaggregator;

/**
 * A container (utility class) to hold a name/value pair
 */
public class AggregatorEntry {
    public String m_name;
    public Double m_value;

    public AggregatorEntry(String name, Double value) {
        m_name = name;
        m_value = value;
    }

}
