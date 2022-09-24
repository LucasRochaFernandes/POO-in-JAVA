import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.json.*;

public class Main {
    public static void main(String[] args) {

        JSONArray arrayJsonUsers = new JSONArray(getJsonUsers());
        JSONArray arrayJsonProducts = new JSONArray(getJsonProducts());
        JSONArray arrayJsonInvoices = new JSONArray(getJsonInvoices());
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<Invoice> invoices = new ArrayList<Invoice>();

        for (int i = 0; i < arrayJsonUsers.length(); i++) {
            JSONObject userObj = arrayJsonUsers.getJSONObject(i);
            int userId = userObj.getInt("id");
            JSONObject nameObj = userObj.getJSONObject("name");
            String firstName = nameObj.getString("firstname");
            String lastName = nameObj.getString("lastname");
            String fullName = firstName.concat(" ").concat(lastName);
            String userEmail = userObj.getString("email");
            User newUser = new User();
            newUser.setId(userId);
            newUser.setName(fullName);
            newUser.setEmail(userEmail);
            users.add(newUser);
        }

        for (int i = 0; i < arrayJsonProducts.length(); i++) {
            JSONObject productObj = arrayJsonProducts.getJSONObject(i);
            int productId = productObj.getInt("id");
            String titleProduct = productObj.getString("title");
            Double priceProduct = productObj.getDouble("price");
            String descriptionProduct = productObj.getString("description");
            Product newProduct = new Product();
            newProduct.setDescription(descriptionProduct);
            newProduct.setName(titleProduct);
            newProduct.setPrice(priceProduct);
            newProduct.setId(productId);
            products.add(newProduct);
        }

        for (int i = 0; i < arrayJsonInvoices.length(); i++) {
            JSONObject invoiceObj = arrayJsonInvoices.getJSONObject(i);
            int invoiceId = invoiceObj.getInt("id");
            int userId = invoiceObj.getInt("userId");
            JSONArray items = invoiceObj.getJSONArray("products");
            String date = invoiceObj.getString("date");
            Invoice newInvoice = new Invoice();
            newInvoice.setId(invoiceId);
            newInvoice.setUserId(userId);
            newInvoice.setItems(items);
            newInvoice.setDate(date);
            invoices.add(newInvoice);
        }

        String descriptionInvoice = "";
        for (int i = 0; i < invoices.size(); i++) {
            Invoice invoice = invoices.get(i);
            descriptionInvoice = descriptionInvoice.concat(invoice.description(users, products))
            .concat("\n").concat("-----------------").concat("\n");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./Invoices.txt"));
            writer.write(descriptionInvoice);
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        } 

    }

    public static String getJsonUsers() {
        try {
            Path usersPath = Path.of("./data/Users.json");
            String content = Files.readString(usersPath);
            return content;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "{}";
    }

    public static String getJsonProducts() {
        try {
            Path productsPath = Path.of("./data/Products.json");
            String content = Files.readString(productsPath);
            return content;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "{}";
    }

    public static String getJsonInvoices() {
        try {
            Path invoicesPath = Path.of("./data/Invoices.json");
            String content = Files.readString(invoicesPath);
            return content;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "{}";
    }
}
