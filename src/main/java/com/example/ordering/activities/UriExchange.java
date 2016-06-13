package com.example.ordering.activities;

import com.example.ordering.representations.RestbucksUri;

import static com.example.ordering.common.Preconditions.checkNotNull;

public final class UriExchange {
    public static RestbucksUri receiptForPayment(final RestbucksUri paymentUri) {
        checkForValidPaymentUri(checkNotNull(paymentUri, "paymentUri"));
        return new RestbucksUri(paymentUri.getBaseUri() + "/receipt/" + paymentUri.getId().toString());
    }

    private static void checkForValidPaymentUri(final RestbucksUri payment) {
        if (!payment.toString().contains("/payment/")) {
            throw new RuntimeException("Invalid Payment URI");
        }
    }
}
