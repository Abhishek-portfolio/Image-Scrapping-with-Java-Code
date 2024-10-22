package polymorphism;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class ImageScraper {

    public static void main(String[] args) {
        String url = "https://sites.google.com/view/seyondigitalmarketingservices/about?authuser=0"; // The URL to scrape
        String imageUrl = ""; // Variable to store the image URL

        try {
            // Connect to the website and parse the HTML
            Document doc = Jsoup.connect(url).get();

            // Select the image element using a CSS selector based on the provided XPath
            Element imgElement = doc.select("div#h\\.31ec1e46ed0abc8d_180 img").first(); // Adjusted selector for Jsoup
            if (imgElement != null) {
                imageUrl = imgElement.absUrl("src"); // Get the absolute URL of the image
                System.out.println("Image URL: " + imageUrl);

                // Specify the download path (change this according to your OS)
                String downloadPath = System.getProperty("user.home") + "/Downloads/downloaded_image.jpg";

                // Download the image
                downloadImage(imageUrl, downloadPath);
            } else {
                System.out.println("No image found on the page.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadImage(String imageUrl, String fileName) {
        try (InputStream in = new URL(imageUrl).openStream();
             FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("Image downloaded successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}