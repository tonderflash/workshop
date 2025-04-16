# Financial Calculator - Mortgage

A simple Java console application that calculates mortgage payments and total interest paid.

## Description

This financial calculator helps users determine:

- Monthly mortgage payment
- Total interest paid over the life of the loan

The calculator uses the standard mortgage formula:

```
M = P × (i(1+i)^n / ((1+i)^n - 1))
```

Where:

- M = Monthly Payment
- P = Principal (loan amount)
- i = Monthly interest rate (annual rate divided by 12)
- n = Number of monthly payments (years × 12)

## How to Run

### Prerequisites

- Java 11 or higher
- Maven

### Building and Running

1. Clone the repository
2. Navigate to the project directory
3. Build the project: `mvn clean package`
4. Run the application: `java -jar target/financial-calculators-1.0-SNAPSHOT.jar`

## Example

For a $53,000 loan at 7.625% interest for 15 years:

- Monthly payment: $495.09
- Total interest paid: $36,115.99

## Usage

1. Run the application
2. Enter the loan amount when prompted
3. Enter the annual interest rate as a percentage
4. Enter the loan term in years
5. View the calculated monthly payment and total interest
6. Choose to run another calculation or exit

## Author

Workshop Project - Pluralsight Java Course
