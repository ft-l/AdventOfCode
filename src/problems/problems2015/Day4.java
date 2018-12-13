package problems.problems2015;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Day4 {
	String input;
	public Day4() {
		input = "bgvyzdsv";
	}
	public static void main(String[] args) {
		Day4 day = new Day4();
		day.part2();
	}
	public void part1() {
		int num = 0;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch(NoSuchAlgorithmException e) {
			
		}
		while(true) {
			md.update((input+num).getBytes());
		    byte[] digest = md.digest();
		    String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		    if(myHash.substring(0,5).equals("00000")) {
		    	System.out.println(num);
		    	break;
		    } else {
		    	num++;
		    }
		}
	}
	public void part2() {
		int num = 0;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch(NoSuchAlgorithmException e) {
			
		}
		while(true) {
			md.update((input+num).getBytes());
		    byte[] digest = md.digest();
		    String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		    if(myHash.substring(0,6).equals("000000")) {
		    	System.out.println(num);
		    	break;
		    } else {
		    	num++;
		    }
		}
	}
}
