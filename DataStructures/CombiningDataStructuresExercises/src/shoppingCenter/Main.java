package shoppingCenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ShoppingCenter shoppingCenter = new ShoppingCenter();
        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String line = reader.readLine();
            String[] tokens = line.split(" ");
            switch (tokens[0]) {
                case "AddProduct":
                    String[] productParams = line.split(";");
                    String name = productParams[0].replace("AddProduct ", "");
                    shoppingCenter.addProduct(name, Double.parseDouble(productParams[1]), productParams[2]);
                    System.out.println("Product added");
                    break;
                case "DeleteProducts":
                    String[] deleteParams = line.split(";");
                    int count = 0;
                    if(deleteParams.length == 1) {
                        count = shoppingCenter.deleteProducts(tokens[1]);
                    } else {
                        count = shoppingCenter.deleteProducts(deleteParams[0].replace("DeleteProducts ", ""), deleteParams[1]);
                    }
                    System.out.println(count == 0 ? "No product found" : count + " products deleted");
                    break;
                case "FindProductsByName":
                    Iterable<Product> products = shoppingCenter.findProductsByName(line.replace("FindProductsByName ", ""));
                    if (!products.iterator().hasNext()) {
                        System.out.println("No product found");
                        break;
                    }
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;
                case "FindProductsByProducer":
                    Iterable<Product> productsByProducer = shoppingCenter.findProductsByProducer(line.replace("FindProductsByProducer ", ""));
                    if (!productsByProducer.iterator().hasNext()) {
                        System.out.println("No product found");
                        break;
                    }
                    for (Product product : productsByProducer) {
                        System.out.println(product);
                    }
                    break;
                case "FindProductsByPriceRange":
                    String[] rangeParams = line.split(";");
                    Iterable<Product> productsByPrice = shoppingCenter
                            .findProductByPriceRange(Double.parseDouble(rangeParams[0].replace("FindProductsByPriceRange ", "")),
                                    Double.parseDouble(rangeParams[1]));
                    if (!productsByPrice.iterator().hasNext()) {
                        System.out.println("No product found");
                        break;
                    }
                    for (Product product : productsByPrice) {
                        System.out.println(product);
                    }
                    break;
            }
        }
    }
}
