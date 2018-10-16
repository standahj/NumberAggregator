package com.example.demoaggregator;

import java.util.Scanner;

public class CommandLine {
    public static void main(String[] argv) {
        System.out.println("Type a (comma-separated list of) number(s): ");
        Scanner input = new Scanner(System.in);
        IAggregator aggregator = new MinMaxAggregator();
        while (input.hasNext()) {
            InputValidator.validateInput(input.nextLine()).stream().forEach(e -> aggregator.addNumber(e));
            System.out.println(aggregator.getOutput(new TextFormatter()));
        }
    }
}
