package app;

public class Menu {

	public static void main(String[] args) {
		final String TYPE = "Content-Type: text/html\r\n";
		
		String output = 
			"<html>" + HtmlFragments.HEADER
				+ "<body>"
					+ "<h1>By the Cake</h1>"
					+ "<h2>Enjoy our awesome cakes</h2>"
					+ "<hr/>"
					+ "<ul>"
						+ "<li><a href=\"/cgi-bin/home.cgi\">Home<a/>"
							+ "<ol>"
								+ "<li><a href=\"/cgi-bin/home.cgi#cakes\">Our cakes</a></li>"
								+ "<li><a href=\"/cgi-bin/home.cgi#stores\">Our stores</a></li>"
							+ "</ol>"
						+ "</li>"
						+ "<li><a href=\"/cgi-bin/add_cake.cgi\">Add cake</a></li>"
						+ "<li><a href=\"/cgi-bin/browse_cakes.cgi\">Browse cakes</a></li>"
						+ "<li><a href=\"/cgi-bin/about.cgi#about\">About us</a></li>"
					+ "</ul>" 
				+ "</body>"
				+ HtmlFragments.FOOTER
		  + "</html>";
		
		System.out.println(TYPE);
		System.out.println(output);

	}

}
