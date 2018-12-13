package problems;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Day {
	
	public String input;
	
	public Day(int day, int year) {
		try {
			input = readFile(day, year);
		} catch (IOException e) {
			
		}
	}
	public static String readFile(int day, int year) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/input/input"+year+"/Day"+day+"Input"));
        try {
            String sb = "";
            String line = br.readLine();

            while (line != null) {
                sb += line.trim()+"\n";
                line = br.readLine();
            }
            return sb;
        } finally {
            br.close();
        }
    }
}