package com.example.demoaggregator;

import java.util.List;

public interface IFormatter {
    String getHeader();

    String getFooter();

    void addItems(List<AggregatorEntry> items);

    String getBody();

    default String getOutput() {
        return getHeader() + getBody() + getFooter();
    }
}
