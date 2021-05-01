package application;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

/*
 * Class Generate
 * Chứa các phương thức sinh số
 */
public class Generate {
	static SecureRandom random = new SecureRandom();
	
	/*
	 * Sinh số ngẫu nhiên có độ dài numberBit bits
	 * Set bit cao nhất và thấp nhất thành 1
	 */
	public static BigInteger genRandom(int numberBit) {
		BigInteger value = new BigInteger(numberBit, random);
		value = value.setBit(0);
		value = value.setBit(numberBit - 1);
		
		return value;
	}
	
	/*
	 * Sinh số nguyên tố độ dài numberBit bits
	 * Sử dụng thuật toán kiểm tra số nguyên tố đã cài đặt trong class Check (xác suất sai là 2^-128)
	 */
	public static BigInteger genPrime(int numberBit) {
		
		BigInteger n;
		
		// Kiểm tra đã chuẩn bị 400 số nguyên tố đầu tiên chưa
		// Nếu đã có thì không làm lại
		if(!Check.isPrepared) {
			Check.preparePrime();
		}
		
		// Lặp: sinh ngẫu nhiên rồi kiểm tra đến khi thu được số nguyên tố
		do {
			n = genRandom(numberBit);
			if(Check.checkPrime(n) == false) {
				break;
			}
		} while(true);
		
		return n;
	}
	
	/*
	 * Sinh nhiều lần số nguyên tố
	 */
	public static void genOutput(int numBit, int num) throws IOException {
		
		BigInteger n;
		long start, end, time;
		// Lưu thời gian chạy
		FileWriter wr = new FileWriter("output/time.txt");
		
		
		for(int i = 1; i <= num; ++i) {
			
			start = System.currentTimeMillis();
			
			n = genPrime(numBit);
			
			end = System.currentTimeMillis();
			
			// Lưu số nguyên tố sinh ra được vào output/prime_number/
			FileWriter writer = new FileWriter("output/prime_number/" + i + ".txt");
			writer.write(n.toString());
			writer.close();
				
			// Lưu thời gian chạy
			time = end - start;
			wr.write(String.valueOf(time));
			wr.write(System.getProperty("line.separator"));
			
			System.out.println(i + ": " + time);
		}
		
		wr.close();
	}
	
}
