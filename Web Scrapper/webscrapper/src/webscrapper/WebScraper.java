package webscrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScraper {
    public static void main(String[] args) {
        String url = "https://www.takealot.com/computers/laptops"; // Replace with the target URL
        try {
            Document doc = Jsoup.connect(url).get();

            // Select each product card
            Elements products = doc.select(".product-card");

            
            System.out.println("We got here");
            for (Element product : products) {
            	
            	System.out.println("We got here");
                // Extract product name
                String productName = product.select(".product-card-module_product-title_16xh8").text();

                // Extract current price
                String currentPrice = product.select(".product-card-price-module_price_westP .currency").text();

                // Extract original price (if available)
                String originalPrice = product.select(".product-card-price-module_list-price_om_3Y .currency").isEmpty() ? 
                                       "N/A" : 
                                       product.select(".product-card-price-module_list-price_om_3Y .currency").text();

                // Extract product image URL
                String imageUrl = product.select(".product-card-image-module_product-image_3mJsJ").attr("src");
                if (!imageUrl.startsWith("http")) {
                    imageUrl = "https://www.takealot.com" + imageUrl;
                }

                // Extract product link
                String productLink = product.select(".product-card-module_link-underlay_3sfaA").attr("href");
                if (!productLink.startsWith("http")) {
                    productLink = "https://www.takealot.com" + productLink;
                }

                // Extract rating
                String rating = product.select(".rating-module_rating-wrapper_3Cogb").text();

                // Print the extracted information
                System.out.println("Product Name: " + productName);
                System.out.println("Current Price: " + currentPrice);
                System.out.println("Original Price: " + originalPrice);
                System.out.println("Image URL: " + imageUrl);
                System.out.println("Product Link: " + productLink);
                System.out.println("Rating: " + rating);
                System.out.println("-----------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Thread was interrupted: " + e.getMessage());
        }
    }
}
