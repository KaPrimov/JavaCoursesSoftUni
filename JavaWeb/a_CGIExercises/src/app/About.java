package app;

public class About {
	public static void main(String[] args) {
		
		final String TYPE = "Content-Type: text/html\r\n";
		
		String output = 
				"<html>"
					+ HtmlFragments.HEADER
					+ "<body>"
						+ "<a href=\"/cgi-bin/menu.cgi\">Back to Home</a>"
						+ "<h2>About</h2>"
						+ "<section id=\"about\">"
							+ "<ul>"
								+ "<li>ByTheCake EOOD"
								+ "<ul type=\"circle\"><li>Name of the company</li></ul></li>"
								+ "<li>Kalin Primov"
								+ "<ul type=\"circle\"><li>Owner</li></ul></li>"
							+ "</ul>" 
						+ "</section>"
					+ "</body>"
					+ HtmlFragments.FOOTER
			  + "</html>";
			
			System.out.println(TYPE);
			System.out.println(output);
	}

}
