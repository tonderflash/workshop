package org.example;

/**
 * Calculator to determine mortgage payments and total interest
 */
public class MortgageCalculator {
    
    /**
     * Calculates monthly payment for a mortgage loan
     * 
     * @param principal loan amount in dollars
     * @param annualInterestRate annual interest rate as decimal (e.g., 0.07625 for 7.625%)
     * @param loanTermYears loan term in years
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(double principal, double annualInterestRate, int loanTermYears) {
        // Monthly interest rate
        double monthlyInterestRate = annualInterestRate / 12;
        
        // Total number of monthly payments
        int numberOfPayments = loanTermYears * 12;
        
        // Calculate monthly payment using the formula: M = P × (i(1+i)^n / ((1+i)^n - 1))
        double monthlyPayment = principal * 
                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments)) / 
                (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
        
        return monthlyPayment;
    }
    
    /**
     * Calculates total interest paid over the life of the loan
     * 
     * @param monthlyPayment monthly payment amount
     * @param principal loan amount
     * @param loanTermYears loan term in years
     * @return total interest paid
     */
    
    public static double calculateTotalInterest(double monthlyPayment, double principal, int loanTermYears) {
        // Total payments = monthly payment * number of payments
        double totalPayments = monthlyPayment * loanTermYears * 12;
        
        // Total interest = total payments - principal
        return totalPayments - principal;
    }
} 
