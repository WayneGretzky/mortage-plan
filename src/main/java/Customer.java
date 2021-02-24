public class Customer {
    private String name;
    private double totalLoan;
    private double yearlyInterestPercent;
    private int years;

    public Customer(String name, double totalLoan, double yearlyInterestPercent, int years) {
        this.name = name;
        this.totalLoan = totalLoan;
        this.yearlyInterestPercent = yearlyInterestPercent;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public double getTotalLoan() {
        return totalLoan;
    }

    public double getYearlyInterestPercent() {
        return yearlyInterestPercent;
    }

    public int getYears() {
        return years;
    }

    // Calculates monthly payment for the customer
    public double calculateMonthlyPayment() {
        int numberOfPayments = 12 * years;
        double monthlyInterestDecimal = calculateMonthlyInterest(yearlyInterestPercent);
        double power = calculatePower(1 + monthlyInterestDecimal, numberOfPayments);
        double numerator = totalLoan * (monthlyInterestDecimal * power);
        double denominator = power - 1;

        return numerator / denominator;
    }

    // Calculates monthly interest rate
    public double calculateMonthlyInterest(double yearlyInterestPercent) {
        double yearlyInterestDecimal = yearlyInterestPercent / 100;
        double monthlyInterestDecimal = yearlyInterestDecimal / 12;

        return monthlyInterestDecimal;
    }

    // Calculates "base" to the power of "exponent". Returns -1 if exponent is negative
    public double calculatePower(double base, int exponent) {
        if(exponent < 0) {
            return -1;
        }

        double result = 1;

        while(exponent > 0) {
            result = result * base;
            exponent--;
        }

        return result;
    }
}
