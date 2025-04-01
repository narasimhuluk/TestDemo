package Programs;

public class removeSpaces {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String x="JOIN ABC GET SKILLED GET PLACED OR	TAKE	COMPLETE REFUND";
		x=x.replaceAll("\\s", "");
		System.out.println(x);
	}

}
