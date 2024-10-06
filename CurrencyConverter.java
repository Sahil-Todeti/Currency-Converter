/**
 * A Java based currency converter that uses GUI and API to convert the currencies (USD, EUR, INR, GBP, CAD)
 * from one to the other. It also has flip button to flip the currencies.
 * **/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CurrencyConverter extends JFrame {
    private JTextField amountField;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JLabel resultLabel;
    private JButton flipButton;
    private HashMap<String, Double> rates = new HashMap<>();

    public CurrencyConverter() {
        rates.put("USD", 1.0);
        rates.put("EUR", 0.85);
        rates.put("INR", 73.58);
        rates.put("GBP", 0.75);
        rates.put("CAD", 1.34);

        // GUI components
        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(20, 20, 100, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(120, 20, 100, 30);
        add(amountField);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(20, 60, 100, 30);
        add(fromLabel);

        fromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP", "CAD"});
        fromCurrency.setBounds(120, 60, 100, 30);
        add(fromCurrency);

        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(20, 100, 100, 30);
        add(toLabel);

        toCurrency = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP", "CAD"});
        toCurrency.setBounds(120, 100, 100, 30);
        add(toCurrency);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(240, 60, 100, 30);
        add(convertButton);

        // Flip button to swap "From" and "To" currencies
        flipButton = new JButton("Flip");
        flipButton.setBounds(240, 100, 100, 30);
        add(flipButton);

        resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(20, 140, 300, 30);
        add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        flipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flipCurrencies();
            }
        });

        setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            // Get the conversion rates for both currencies
            double fromRate = rates.get(from);
            double toRate = rates.get(to);

            // Convert the amount using the rates
            double result = Math.round(amount * (toRate / fromRate));

            resultLabel.setText("Result: " + result + " " + to);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
        }
    }

    // Function to swap the "From" and "To" currency selections
    private void flipCurrencies() {
        String from = (String) fromCurrency.getSelectedItem();
        String to = (String) toCurrency.getSelectedItem();

        fromCurrency.setSelectedItem(to);
        toCurrency.setSelectedItem(from);
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}
