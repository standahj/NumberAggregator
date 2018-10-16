package com.example.demoaggregator;

import java.util.ArrayList;
import java.util.List;

public class HTMLFormatter implements IFormatter {

    private List<AggregatorEntry> m_entries = new ArrayList<>();

    @Override
    public String getHeader() {
        return "<html><head/><body><h3>Min, Max, Average</h3>\n<ul>\n";
    }

    @Override
    public String getFooter() {
        return "</ul></body></html>";
    }

    @Override
    public void addItems(List<AggregatorEntry> items) {
        m_entries.addAll(items);
    }

    @Override
    public String getBody() {
        StringBuilder body = new StringBuilder();
        for (AggregatorEntry entry : m_entries) {
            body.append("<li>").append(entry.m_name).append(": ");
            body.append("<b>").append(Double.toString(entry.m_value)).append("</b></li>\n");
        }
        return body.toString();
    }
}
