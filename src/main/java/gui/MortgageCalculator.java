package gui;

import util.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;

public class MortgageCalculator extends JFrame {
    private JButton calculateButton;
    private PlaceholderTextField loanAmountField;
    private PlaceholderTextField interestRateField;
    private PlaceholderTextField loanDurationField;
    private JLabel output;

    public MortgageCalculator() {
        this.setLayout(new GridLayout(5, 1, 10, 10));

        this.loanAmountField = new PlaceholderTextField();
        this.loanAmountField.setPlaceholder("Principle Loan Amount");

        this.interestRateField = new PlaceholderTextField();
        this.interestRateField.setPlaceholder("Interest Rate (%)");

        this.loanDurationField = new PlaceholderTextField();
        this.loanDurationField.setPlaceholder("Loan Duration (Years)");

        this.calculateButton = new JButton("Calculate");

        this.output = new JLabel();

        this.add(this.loanAmountField);
        this.add(this.interestRateField);
        this.add(this.loanDurationField);
        this.add(this.calculateButton);
        this.add(output);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Mortgage Calculator");
        this.setSize(500, 200);
        this.setVisible(true);

        addListener();
    }

    private void addListener() {
        //Calculates the monthly payment and displays in a label below
        calculateButton.addActionListener(event -> {
            int loanAmount, loanDurationInYears;
            double interestRate;
            //checks for invalid inputs
            try {
                loanAmount = Integer.parseInt(loanAmountField.getText());
                interestRate = Double.parseDouble(interestRateField.getText());
                loanDurationInYears = Integer.parseInt(loanDurationField.getText());
            } catch (NumberFormatException nfe) {
                output.setText("Invalid input!");
                return;
            }
            //rounds the output to 2 decimals
            output.setText(String.format("Monthly Mortgage Payment: $%.2f", calculateMonthlyPayment(loanAmount, interestRate*.01, loanDurationInYears)));
        });
    }

    private static double calculateMonthlyPayment (int loanAmount, double interestRate, int durationInYears) {
        var numerator = loanAmount * (interestRate/12);
        var denominator = 1 - Math.pow((1 + interestRate/12), -12*durationInYears);
        return numerator/denominator;
    }
}
