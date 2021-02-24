import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("prospects.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        List<Customer> customers = new ArrayList<>();
        List<String> customerString = new ArrayList<>();

        String line;

        // Adds each line from the text file to an arrayList of strings.
        while ((line = bufferedReader.readLine()) != null) {
            if(line.length() > 1) {
                customerString.add(line);
            }
        }
        bufferedReader.close();

        /*
        A customer is created inside the loop from each element in the arrayList of strings, and  each customer is added
        to an arrayList of customers
        Skips first element from the arrayList of strings, because it does not contain any customer data
        */
        for(int i = 1; i < customerString.size(); i++) {
            String[] customerSplitted = customerString.get(i).split(",");

            // The string has been split by the commas in the string into an array. This means that each element in the array
            // should represent the customer's name, total loan, interest, years respectively
            if(customerSplitted.length == 4) {
                String name = customerSplitted[0];
                double totalLoan = Double.parseDouble(customerSplitted[1]);
                double interest = Double.parseDouble(customerSplitted[2]);
                int years = Integer.parseInt(customerSplitted[3]);
                Customer customer = new Customer(name, totalLoan, interest, years);
                customers.add(customer);
            } else {
                System.out.println("Wrong format in the text file for prospect " + i);
            }
        }

        // Calculates and prints monthly payments for each customer
        for(int i = 0; i < customers.size(); i++) {
            String name = customers.get(i).getName();
            double monthlyPayment = customers.get(i).calculateMonthlyPayment();
            double totalLoan = customers.get(i).getTotalLoan();
            int years = customers.get(i).getYears();

            int prospectNr = i + 1;

            System.out.println("Prospect " + prospectNr + ": " + name + " wants to borrow "
                    + totalLoan + " € for a period of " + years + " years and pay " + monthlyPayment + " € each month.");
        }
    }
}

