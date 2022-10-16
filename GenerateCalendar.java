import java.util.Calendar;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class GenerateCalendar {

  public static void main(String[] args) {

    Calendar calendar = Calendar.getInstance();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    try {
      String command = in.readLine();
    } catch (IOException e) {
      System.out.println("err");
    }

    System.out.println("d");
  }

}