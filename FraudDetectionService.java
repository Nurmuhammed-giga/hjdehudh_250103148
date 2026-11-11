package com.ecommerce;

import java.util.Set;

/**
 * Subsystem Class 5: FraudDetectionService
 * Responsible for checking orders for signs of fraud before payment is processed.
 */
public class FraudDetectionService {

    // Maximum order amount allowed without additional manual review
    private static final double MAX_ALLOWED_AMOUNT = 10000.00;

    // Customers that were previously flagged for fraud (mocked blacklist)
    private static final Set<String> BLACKLISTED_CUSTOMERS = Set.of("user_666", "fraudster_1");

    /**
     * Step 1: Analyze the order and decide whether it looks fraudulent.
     *
     * @param customerId The customer placing the order.
     * @param address The delivery address.
     * @param amount The total order amount.
     * @return true if the order is suspicious (fake), false if it looks legitimate.
     */
    public boolean isFraudulent(String customerId, String address, double amount) {
        System.out.println("[FraudDetectionService] Step 1: Analyzing order of customer: " + customerId);

        // Step 1.1: Check whether the customer is on the blacklist
        if (BLACKLISTED_CUSTOMERS.contains(customerId)) {
            System.out.println("[FraudDetectionService] Step 2: Customer is blacklisted: " + customerId);
            return true;
        }

        // Step 1.2: Check that the delivery address is present
        if (address == null || address.trim().isEmpty()) {
            System.out.println("[FraudDetectionService] Step 2: Delivery address is missing.");
            return true;
        }

        // Step 1.3: Check for an abnormally large or invalid amount
        if (amount <= 0 || amount > MAX_ALLOWED_AMOUNT) {
            System.out.println("[FraudDetectionService] Step 2: Suspicious amount: $" + amount);
            return true;
        }

        System.out.println("[FraudDetectionService] Step 2: Order passed fraud check.");
        return false;
    }
}
