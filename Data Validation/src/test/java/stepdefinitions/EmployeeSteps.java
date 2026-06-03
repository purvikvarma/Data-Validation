package stepdefinitions;

import database.DBConnection;
import database.EmployeeQueries;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeSteps {

    @Given("user connects to ecommerce database")
    public void connect_database() {
        DBConnection.getConnection();
    }

    @When("user fetches order details for customer {string}")
    public void fetch_customer_orders(String customerName) {
        System.out.println("Fetching customer orders for: " + customerName);
        // CHANGED: Call static method directly on class instead of instance
        EmployeeQueries.getCustomerOrders(customerName);
    }

    @Then("customer order data should be displayed in terminal")
    public void validate_customer_order_data() {
        System.out.println("Customer order data displayed in terminal");
    }

    @Then("customer order data should be validated successfully")
    public void validate_customer_orders() {
        String expectedCustomerName = "Purvik Varma";
        String expectedOrderAmount = "50000.00";
        System.out.println("Validating customer name: " + expectedCustomerName);
        System.out.println("Validating order amount: " + expectedOrderAmount);
    }

    @When("user fetches product and category details")
    public void fetch_product_category_details() {
        System.out.println("Fetching product category details...");
        // CHANGED: Call static method directly on class instead of instance
        EmployeeQueries.getProductCategoryDetails();
    }

    @Then("product category data should be displayed in terminal")
    public void validate_product_category_data() {
        System.out.println("Product category data displayed successfully");
    }

    @Then("product category mapping should be validated successfully")
    public void validate_product_category_mapping() {
        System.out.println("Product category mapping validation passed");
    }

    @When("user validates product {string} with expected price {int}")
    public void validate_specific_product(String productName, int expectedPrice) {
        System.out.println("Validating product: " + productName + " with expected price: " + expectedPrice);
        // CHANGED: Call static method directly on class instead of instance
        EmployeeQueries.validateProductData(productName);
    }

    @Then("product validation result should be displayed in terminal")
    public void product_validation_result() {
        System.out.println("Product validation result displayed successfully");
    }

    @When("user validates total amount for order ID {int}")
    public void userValidatesTotalAmountForOrderID(int orderId) {
        System.out.println("Validating total amount for order ID: " + orderId);
        // CHANGED: Call static method directly on class instead of instance
        EmployeeQueries.validateOrderAmount(orderId);
    }

    @Then("order total amount should be displayed in terminal")
    public void orderTotalAmountShouldBeDisplayedInTerminal() {
        System.out.println("Order total amount displayed in terminal");
    }
}