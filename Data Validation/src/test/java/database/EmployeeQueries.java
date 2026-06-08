package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeQueries {

    public static void getCustomerOrders(String customerName) {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT c.customer_name, o.order_id, o.order_date, o.total_amount " +
                    "FROM customers c " +
                    "JOIN orders o ON c.customer_id = o.customer_id " +
                    "WHERE c.customer_name = '" + customerName + "'";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String actualCustomerName = rs.getString("customer_name");
                int orderId = rs.getInt("order_id");
                String orderDate = rs.getString("order_date");
                double totalAmount = rs.getDouble("total_amount");

                System.out.println("Customer Name: " + actualCustomerName);
                System.out.println("Order ID: " + orderId);
                System.out.println("Order Date: " + orderDate);
                System.out.println("Total Amount: " + totalAmount);
                System.out.println("-----------------------------------");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getProductCategoryDetails() {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT p.product_name, cat.category_name, p.price " +
                    "FROM products p " +
                    "JOIN categories cat ON p.category_id = cat.category_id";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String productName = rs.getString("product_name");
                String categoryName = rs.getString("category_name");
                double price = rs.getDouble("price");

                System.out.println("Product Name: " + productName);
                System.out.println("Category Name: " + categoryName);
                System.out.println("Price: " + price);
                System.out.println("-----------------------------------");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void validateProductData(String productName) {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT product_name, price FROM products WHERE product_name = '" + productName + "'";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                String name = rs.getString("product_name");
                double price = rs.getDouble("price");
                System.out.println("Product Name: " + name);
                System.out.println("Price: " + price);
            } else {
                System.out.println("Product not found");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void validateOrderAmount(int orderId) {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT order_id, total_amount FROM orders WHERE order_id = " + orderId;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                int id = rs.getInt("order_id");
                double amount = rs.getDouble("total_amount");
                System.out.println("Order ID: " + id);
                System.out.println("Total Amount: " + amount);
            } else {
                System.out.println("Order not found");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Validate customer email format (non-regex: must contain @ and . after @)
    public static boolean validateCustomerEmail(String customerName) {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT email FROM customers WHERE customer_name = ?";
            java.sql.PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customerName);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String email = rs.getString("email");
                System.out.println("Fetched email: " + email);
                // Simple validation: must contain '@' and a '.' after '@'
                int at = email.indexOf('@');
                if (at > 0 && email.indexOf('.', at) > at + 1) {
                    System.out.println("Email format is valid: " + email);
                    return true;
                } else {
                    System.out.println("Email format is invalid: " + email);
                    return false;
                }
            } else {
                System.out.println("Customer not found: " + customerName);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error validating email: " + e);
            return false;
        }
    }

    // Check product stock availability (returns true if stock > 0, false otherwise)
    public static boolean checkProductStockAvailability(String productName) {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT stock FROM products WHERE product_name = ?";
            java.sql.PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, productName);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int stock = rs.getInt("stock");
                System.out.println("Fetched stock: " + stock);
                if (stock > 0) {
                    System.out.println("Product is in stock: " + productName);
                    return true;
                } else {
                    System.out.println("Product is out of stock: " + productName);
                    return false;
                }
            } else {
                System.out.println("Product not found: " + productName);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error checking stock: " + e);
            return false;
        }
    }
}

