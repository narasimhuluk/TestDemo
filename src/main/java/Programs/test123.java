package Programs;

import java.util.Arrays;

public class test123 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a[] = { 10, 20, 30, 40, 70 };
		int size = a.length;
		int sum = 0;
//		for (int i = 0; i < size; i++) {
//			sum = sum + a[i];
//		}
//		int i = 0;
//		while(i!=size)
//		{
//			sum=sum+a[i];
//			++i;
//		}
//		do {
//			sum = sum + a[i];
//			++i;
//		} while (i != size);
		
		for(int b:a)
		{
			sum=sum+b;
		}
 
		System.out.println(sum);
	}

}
