package com.example.demoaggregator;

import java.util.ArrayList;
import java.util.List;

public class TextFormatter implements IFormatter {

    private List<AggregatorEntry> m_entries = new ArrayList<>();

    @Override
    public String getHeader() {
        return "Min, Max, Average: ";
    }

    @Override
    public String getFooter() {
        return "";
    }

    @Override
    public void addItems(List<AggregatorEntry> items) {
        m_entries.addAll(items);
    }

    @Override
    public String getBody() {
        StringBuilder body = new StringBuilder();
        for (AggregatorEntry entry : m_entries) {
            if (body.length() > 0) {
                body.append(", ");
            }
            body.append(Double.toString(entry.m_value));
        }
        body.append("\n");
        return body.toString();
    }
}
