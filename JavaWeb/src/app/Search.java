package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Search {

	public static void main(String[] args) throws FileNotFoundException {
		final String TYPE = "Content-Type: text/html";
		
		String output = 
			"<html>" + HtmlFragments.HEADER
				+ "<body>"
					+ "<a href=\"/cgi-bin/menu.cgi\">Back to Home</a>"
					+ "<form>"
					+ "<label>Name: </label><input name=\"name\" type=\"text\" required/>"
					+ "<input type=\"submit\"/ value=\"SEARCH\">"
					+ "</form>" ;
		
		String query = System.getenv("QUERY_STRING");
		System.out.println(TYPE);
		System.out.println();
		
		if (query != null && query.trim().length() > 0) {
			BufferedReader br = new BufferedReader(new FileReader("cakes.csv"));
			List<Cake> cakes = new ArrayList<>();
			String searchedName = query.split("=")[1];
			br.lines().forEach(line -> {
				String[] tokens = line.split("; ");
				for (String cakeData : tokens) {
					String[] singleCakeData = cakeData.split(",");
					if (singleCakeData[0].contains(searchedName)) {
						cakes.add(new Cake(singleCakeData[0], Double.parseDouble(singleCakeData[1].replace(";", ""))));
					}
				}
			});
			for (Cake cake : cakes) {				
				output += "<p>" + cake.toString() + "</p>";
			}
		}
				
		output += "</body>"
				+ HtmlFragments.FOOTER				
		  + "</html>";
		System.out.println(output);
	}	
}
