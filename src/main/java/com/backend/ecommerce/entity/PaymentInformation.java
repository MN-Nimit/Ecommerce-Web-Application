package com.backend.ecommerce.entity;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class PaymentInformation {

    private String cardHolderName;

    private String cardNumber;

    private LocalDate expirationDate;

    private String cvv;
}
