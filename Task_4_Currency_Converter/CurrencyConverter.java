import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/*
 * Task 4 - Currency Converter
 * CodSoft Java Internship
 * 
 * This program converts an amount from one currency to another
 * using real-time exchange rates fetched from a public API.
 */

public class CurrencyConverter {

    // Method to fetch exchange rate from API
    public static double getExchangeRate(String base, String target) {
        try {
            // Free public API for exchange rates
            String apiUrl = "https://open.er-api.com/v6/latest/" + base;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String json = response.toString();

            // Extract exchange rate manually (no external libraries)
            int index = json.indexOf("\"" + target + "\"");
            if (index == -1) return -1;

            int colon = json.indexOf(":", index);
            int comma = json.indexOf(",", colon);

            return Double.parseDouble(json.substring(colon + 1, comma));

        } catch (Exception e) {
            return -1;
        }
    }

    // Method to return currency symbol
    public static String getCurrencySymbol(String currency) {
        switch (currency) {
            case "USD": return "$";
            case "INR": return "₹";
            case "EUR": return "€";
            case "GBP": return "£";
            case "JPY": return "¥";
            default: return currency + " ";
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Currency Converter =====");

        // User input
        System.out.print("Enter Base Currency (USD, INR, EUR): ");
        String baseCurrency = sc.next().toUpperCase();

        System.out.print("Enter Target Currency (USD, INR, EUR): ");
        String targetCurrency = sc.next().toUpperCase();

        System.out.print("Enter Amount to Convert: ");
        double amount = sc.nextDouble();

        // Fetch exchange rate
        double rate = getExchangeRate(baseCurrency, targetCurrency);

        if (rate == -1) {
            System.out.println("Error fetching exchange rate.");
            return;
        }

        // Currency conversion
        double convertedAmount = amount * rate;

        // Display result
        System.out.println("\n===== Conversion Result =====");
        System.out.println("Exchange Rate: " + rate);
        System.out.println("Converted Amount: "
                + getCurrencySymbol(targetCurrency)
                + String.format("%.2f", convertedAmount));

        sc.close();
    }
}
