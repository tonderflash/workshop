package org.example;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
    private static final NumberFormat percentFormatter = NumberFormat.getPercentInstance(Locale.US);

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("       FINANCIAL CALCULATOR - MORTGAGE      ");
        System.out.println("============================================");
        
        boolean continueCalculations = true;
        
        while (continueCalculations) {
            runMortgageCalculator();
            
            System.out.print("\nWould you like to calculate another mortgage? (y/n): ");
            String answer = scanner.next().trim().toLowerCase();
            continueCalculations = answer.equals("y") || answer.equals("yes");
            
            // Clear the input buffer
            scanner.nextLine();
        }
        
        System.out.println("\nThank you for using the Financial Calculator!");
        scanner.close();
    }
    
    private static void runMortgageCalculator() {
        System.out.println("\nMORTGAGE CALCULATOR");
        System.out.println("------------------");
        System.out.println("This calculator helps determine your monthly mortgage payment and total interest paid.");
        
        // Get loan principal
        double principal = getValidDoubleInput("Enter the loan amount (principal): $", 1000, 10000000);
        
        // Get annual interest rate
        double annualInterestRate = getValidDoubleInput("Enter the annual interest rate (%): ", 0.1, 30) / 100;
        
        // Get loan term in years
        int loanTermYears = getValidIntInput("Enter the loan term (in years): ", 1, 50);
        
        // Calculate monthly payment
        double monthlyPayment = MortgageCalculator.calculateMonthlyPayment(principal, annualInterestRate, loanTermYears);
        
        // Calculate total interest
        double totalInterest = MortgageCalculator.calculateTotalInterest(monthlyPayment, principal, loanTermYears);
        
        // Display results
        System.out.println("\nRESULTS:");
        System.out.println("--------");
        System.out.println("Loan Amount: " + currencyFormatter.format(principal));
        System.out.println("Annual Interest Rate: " + percentFormatter.format(annualInterestRate));
        System.out.println("Loan Term: " + loanTermYears + " years");
        System.out.println("Monthly Payment: " + currencyFormatter.format(monthlyPayment));
        System.out.println("Total Interest Paid: " + currencyFormatter.format(totalInterest));
        System.out.println("Total Amount Paid: " + currencyFormatter.format(principal + totalInterest));
    }
    
    private static double getValidDoubleInput(String prompt, double min, double max) {
        double value = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(prompt);
            try {
                value = scanner.nextDouble();
                if (value >= min && value <= max) {
                    validInput = true;
                } else {
                    System.out.println("Error: Please enter a value between " + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number");
                scanner.next(); // Clear invalid input
            }
        }
        
        return value;
    }
    
    private static int getValidIntInput(String prompt, int min, int max) {
        int value = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    validInput = true;
                } else {
                    System.out.println("Error: Please enter a value between " + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer");
                scanner.next(); // Clear invalid input
            }
        }
        
        return value;
    }
}
