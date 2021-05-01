package application;

import java.io.IOException;
import java.math.BigInteger;

public class Main {
public static void main(String[] args) throws IOException {
	
		System.out.println("Start!");
		
		// Để sinh 100 số nguyên tố và lưu vào file thì dùng đoạn code phía dưới
		// Cảnh báo: Mất nhiều thời gian
//		 Generate.genOutput(3072, 100);
		
		
		// Để sinh 1 số nguyên tố thì dùng đoạn code phía dưới
		BigInteger n;
		n = Generate.genPrime(3072);
		System.out.println(n);
		
		
		System.out.println("Done!");
		
	}
}
