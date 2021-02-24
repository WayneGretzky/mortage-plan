import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CalculationsTest {

    Customer testCustomer = new Customer("Juha", 100, 5, 2);

    // Tests calculatePower with positive exponent
    @Test
    public void calculatePowerTest() {
        double result = testCustomer.calculatePower(2,2);
        double expected = Math.pow(2, 2);

        assertTrue(expected == result);
    }

    // Tests calculatePower when exponent is 0
    @Test
    public void calculatePowerTest2() {
        double result = testCustomer.calculatePower(2,0);
        double expected = Math.pow(2, 0);

        assertTrue(expected == result);
    }

    // Tests calculatePower with negative exponent. The method should return -1 if exponent is negative
    @Test
    public void calculatePowerTest3() {
        double result = testCustomer.calculatePower(2,-1);
        double expected = -1;

        assertTrue(expected == result);
    }

    // Tests that calculateMonthlyInterest converts yearly interest to monthly interest rate in decimals
    @Test
    public void calculateMonthlyInterestTest() {
        double result = testCustomer.calculateMonthlyInterest(testCustomer.getYearlyInterestPercent());
        double expected = (testCustomer.getYearlyInterestPercent() / 100.0) / 12.0;

        assertTrue(expected == result);
    }

    /*
    Tests if calculateMonthlyPayment returns the same value as when calculating without its helper methods and with
    Math.pow instead
    */
    @Test
    public void calculateMonthlyPaymentTest() {
        int numberOfPayments = 12 * testCustomer.getYears();
        double monthlyInterestDecimal = (testCustomer.getYearlyInterestPercent() / 100.0) / 12.0;
        double numerator = testCustomer.getTotalLoan() * (monthlyInterestDecimal * Math.pow(1 + monthlyInterestDecimal, numberOfPayments));
        double denominator = (Math.pow(1 + monthlyInterestDecimal, numberOfPayments)) - 1;
        double expected = numerator / denominator;

        double result = testCustomer.calculateMonthlyPayment();

        assertTrue(expected == result);
    }
}
