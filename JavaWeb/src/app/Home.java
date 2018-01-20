package app;

public class Home {

	public static void main(String[] args) {
		final String TYPE = "Content-Type: text/html\r\n";
		
		String output = 
			"<html>"
				+ HtmlFragments.HEADER
				+ "<body>"
					+ "<a href=\"/cgi-bin/menu.cgi\">Back to Home</a>"
					+ "<h2>Home</h2>"
					+ "<section id=\"cakes\">"
						+ "<h3>Our cakes</h1>"
						+ "<p><em><strong>Cake</strong></em> is a form of <strong><em>sweet dessert</em></strong> that is typically baked. In its oldest forms, cakes were modifications of breads, but cakes now cover a wide range of preparations that can be simple or elaborate, and that share features with other desserts such as pastries, meringues, custards, and pies</p>"
						+ "<img width=\"600\" src=\"/cake.jpeg\" alt=\"cake\"/>"
					+ "</section>"
					+ "<section id=\"stores\">"
						+ "<h3>Our stores</h1>"
						+ "<p>Our stores are located in 21 cities all over the world. Come and see what we have for you.</p>"
						+ "<img width=\"600\" src=\"/cake-store.jpg\" alt=\"cake\"/>"
					+ "</section>"
				+ "</body>"
				+ HtmlFragments.FOOTER
		  + "</html>";
		
		System.out.println(TYPE);
		System.out.println(output);

	}

}
