package app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Form {

	public static void main(String[] args) throws IOException {
		final String TYPE = "Content-Type: text/html\r\n";
		
		String output = 
			"<html>" + HtmlFragments.HEADER
				+ "<body>"
					+ "<a href=\"/cgi-bin/menu.cgi\">Back to Home</a>"
					+ "<form method=\"POST\" action=\"/cgi-bin/add_cake.cgi\">"
					+ "<label>Name</label><input name=\"name\" type=\"text\" required/>"
					+ "<label>Price</label><input name=\"price\" type=\"number\" required/>"
					+ "<input type=\"submit\"/ value=\"Create\">"
					+ "</form>" ;
		Scanner scanner = new Scanner(System.in);
		
		if ("POST".equals(System.getenv("REQUEST_METHOD"))) {
			String queryString = scanner.nextLine();
			String[] tokens = queryString.split("&");
			if (tokens.length == 2) {
				String nameData = tokens[0].split("=")[1];
				String priceData = tokens[1].split("=")[1];
				output += "<p>name: " + nameData + "</p>";
				output += "<p>price: " + priceData + "</p>";
				FileWriter fw = new FileWriter("cakes.csv", true);
				fw.append(nameData + "," + priceData + System.lineSeparator());
				fw.flush();
				fw.close();
			}
		}
		
		output += "</body>"
				+ HtmlFragments.FOOTER				
		  + "</html>";
		System.out.println(TYPE);
		System.out.println(output);

	}

}
