package solet;

public interface HttpSolet {
	void doGet(HttpSoletRequest request, HttpSoletResponse response);
	
	void doPost(HttpSoletRequest request, HttpSoletResponse response);
	
	void doDelete(HttpSoletRequest request, HttpSoletResponse response);
	
	void doPut(HttpSoletRequest request, HttpSoletResponse response);
}
