package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Created by Kalin on 2/8/2017.
 */
public class AddVAT {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(", ");
        List<Double> doubles = new ArrayList<>();
        for (String s : input) {
            doubles.add(Double.parseDouble(s));
        }

        UnaryOperator<Double> calcVat = price -> price * 1.2;

        System.out.println("Prices with VAT:");
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        df.setDecimalFormatSymbols(symbols);
        symbols.setDecimalSeparator(',');
        for (Double price : doubles) {
            String num = String.format("%.2f", calcVat.apply(price));
            System.out.println(num.replace(".", ","));
        }
    }
}
