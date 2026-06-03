Feature: Ecommerce Database Validation

  Background:
    Given user connects to ecommerce database

  Scenario: Validate customer order data
    When user fetches order details for customer "Purvik Varma"
    Then customer order data should be displayed in terminal
    And customer order data should be validated successfully

  Scenario: Validate product category mapping
    When user fetches product and category details
    Then product category data should be displayed in terminal
    And product category mapping should be validated successfully

  Scenario: Validate specific product data
    When user validates product "iPhone 15" with expected price 80000
    Then product validation result should be displayed in terminal

  Scenario: Validate order total amount
    When user validates total amount for order ID 101
    Then order total amount should be displayed in terminal

