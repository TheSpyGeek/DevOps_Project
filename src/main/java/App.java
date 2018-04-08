import java.util.ArrayList;

class App {
	public static void main(String args[]){
		System.out.println("Hello World!!!");
		ArrayList<Comparable> i = new ArrayList<Comparable>();
		String s = "3.5";
		try {
			Double.parseDouble(s);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}