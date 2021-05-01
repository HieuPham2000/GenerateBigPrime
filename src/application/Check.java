package application;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/*
 * Class Check
 * Chứa các phương thức kiểm tra số nguyên tố
 */
public class Check {
	
	static final BigInteger ZERO = BigInteger.ZERO;
	static final BigInteger ONE = BigInteger.ONE;
	static final BigInteger TWO = BigInteger.TWO;
	
	/*
	 * Chứa 400 số nguyên tố đầu tiên (chuẩn bị trước)
	 */
	static List<Integer> listPreparePrime;
	
	/*
	 * Kiểm tra đã chuẩn bị 400 số nguyên tố chưa
	 */
	static boolean isPrepared = false;
	
	/*
	 * Nguồn sinh ngẫu nhiên
	 */
	static SecureRandom random = new SecureRandom();
	
	
	/*
	 * Chuẩn bị trước 400 số nguyên tố đầu tiên, lưu vào listPreparePrime	
	 */
	public static void preparePrime() {
		int count = 400;
		int N = 3000;
		boolean check[] = new boolean[N];
		check[0] = false; check[1] = false;
		
		for(int i = 2; i < N; ++i) {
			check[i] = true;
		}
		
		
		for(int i = 2; i < N; ++i) {
			if(check[i] == true) {
				for(int j = 2*i; j < N; j += i) {
					check[j] = false;
				}
			}
		}
		
		listPreparePrime = new ArrayList<Integer>();
		for (int i = 2; i < N; i++) {
		    if (check[i] == true) {
		      listPreparePrime.add(i);
		      count --;
		    }
		    if(count == 0) {
		    	break;
		    }
		}	
		
		isPrepared = true;
	}
	
	
	/*
	 * Kiểm tra 1: Kiểm tra số n có chia hết cho số nào đó trong listPreparePrime hay không
	 * Trả về true nếu n có chia hết (n là hợp số)
	 * Trả về false: n CÓ THỂ là số nguyên tố
	 */
	public static boolean nativeCheck(BigInteger n) {
		for(int i: listPreparePrime) {
			if(n.mod(BigInteger.valueOf(i)).equals(ZERO)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/*
	 * Kiểm tra 2: Kiểm tra số n dựa trên định lý Fermat nhỏ
	 * Trả về true: n chắc chắn là hợp số
	 * Trả về false: n CÓ THỂ là số nguyên tố
	 */
	public static boolean checkFermat(BigInteger n) {
		
		if(!TWO.modPow(n.subtract(ONE), n).equals(ONE)) {
			return true;
		}
		
		return false;
	}
	
	
	/*
	 * Cài đặt thuật toán Witness
	 */
	public static boolean Witness(BigInteger a, BigInteger n) {
		
		// Xác định t, u
		BigInteger u = TWO;
		int t = 0;
		long p = 1;
		boolean condition1 = true, condition2 = true;
		do {
			t++;	
			p *= 2;
			BigInteger I = BigInteger.valueOf(p);
			
			condition1 = n.subtract(ONE).mod(I).equals(ZERO);
			if(condition1) {
				u = n.subtract(ONE).divide(I);
			}
			
			condition2 = u.mod(TWO).equals(ONE);
			
		} while(!condition2);
		
		
		
		BigInteger x[] = new BigInteger[t + 1];
		x[0] = a.modPow(u, n);
		for(int i = 1; i <= t; ++i) {
			x[i] = x[i - 1].modPow(TWO, n);
			if( x[i].equals(ONE) && !x[i - 1].equals(ONE) && !x[i - 1].equals(n.subtract(ONE)) ) {
				return true;
			}
		}
		
		if( !x[t].equals(ONE) ) {
			return true;
		}
		
		return false;
	}
	
	
	/*
	 * Cài đặt thuật toán Rabin - Miller
	 * Trả về true: n là hợp số
	 * Trả về false: n là số nguyên tố, xác suất sai là 2^(-2*s)
	 */
	public static boolean RabinMiller(BigInteger n, int s) {
		for(int i = 1; i <= s; ++i) {
			BigInteger a = new BigInteger(n.bitLength(), random);
			if(Witness(a, n)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/*
	 * Triển khai thuật toán kiểm tra
	 * Sử dụng thuật toán Rabin - Miller
	 * Kết hợp với các bước tiền xử lý để tăng tốc độ
	 * Trả về true: n là hợp số
	 * Trả về false: n là số nguyên tố, xác suất sai là 2^-128
	 */
	public static boolean checkPrime(BigInteger n) {
		if(nativeCheck(n)) {
			return true;
		}
		
		if(checkFermat(n)) {
			return true;
		}
		
		if(RabinMiller(n, 64)) {
			return true;
		}
		
		return false;
	}
}
