package gui;

import javax.swing.*;
import java.awt.*;

public class MortgageCalculator extends JFrame {
    private JButton calculateButton;
    private TextField loanAmountField;
    private TextField interestRateField;
    private TextField loanDurationField;
    private JLabel output;

    public MortgageCalculator() {
        this.setLayout(new GridLayout(5, 1, 10, 10));

        this.loanAmountField = new TextField();

        this.interestRateField = new TextField();

        this.loanDurationField = new TextField();

        this.calculateButton = new JButton("Calculate");

        this.output = new JLabel();

        this.add(new JLabel("Principle Loan Amount"));
        this.add(this.loanAmountField);
        this.add(new JLabel("Interest Rate (Years)"));
        this.add(this.interestRateField);
        this.add(new JLabel("Loan Duration (Years)"));
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
        double numerator = loanAmount * (interestRate/12);
        double denominator = 1 - Math.pow((1 + interestRate/12), -12*durationInYears);
        return numerator/denominator;
    }
}
