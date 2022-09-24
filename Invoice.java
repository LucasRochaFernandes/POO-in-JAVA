import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Invoice {
    private int id;
    private int userId;
    private JSONArray items;
    private String date;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setItems(JSONArray items) {
        this.items = items;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public JSONArray getItems() {
        return items;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String description(ArrayList<User> usersList, ArrayList<Product> productsList) {
        String textInvoice = "Venda: ".concat(String.valueOf(this.id))
                .concat("\n").concat("Date: ").concat(this.date).concat("\n")
                .concat("User: ").concat("\n").concat("     Name: ");

        User userInvoice = new User();
        for (int i = 0; i < usersList.size(); i++) {
            if (this.userId == usersList.get(i).getId()) {
                userInvoice = usersList.get(i);
            }
        }
        
        textInvoice = textInvoice.concat(userInvoice.getName()).concat("\n").concat("     Email: ").concat(userInvoice.getEmail())
                .concat("\n").concat("Products: ").concat("\n");

        Double totalInvoice = 0.0;

        for (int j = 0; j < this.items.length(); j++) {
            JSONObject itemObj = this.items.getJSONObject(j);
            int productId = itemObj.getInt("productId");
            int amount = itemObj.getInt("quantity");
            Product productInvoice = new Product();
            for (int i = 0; i < productsList.size(); i++) {
                if (productId == productsList.get(i).getId()) {
                    productInvoice = productsList.get(i);
                }
            }
            textInvoice = textInvoice.concat("  Name: ").concat(productInvoice.getName()).concat("\n")
            .concat("   Quantity: ").concat(String.valueOf(amount)).concat("\n").concat("-").concat("\n");

            totalInvoice = totalInvoice + (productInvoice.getPrice() * amount);
            
        }

        textInvoice = textInvoice.concat("Invoice Total: ").concat(String.valueOf(totalInvoice));

        return textInvoice;
    }
}
