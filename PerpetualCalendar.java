import java.util.*;
import java.io.*;
import java.lang.*;

public class PerpetualCalendar {

  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    String command = "";
    String rowMonth = "";
    String rowYear = "";
    byte month = 0;
    byte year = 0;

    while(true){
      // $ の出力をおこなう？
      System.out.println("説明"); // 説明を書く
      try {
        command = in.readLine();
        rowMonth = in.readLine();
        rowYear = in.readLine();

      } catch (IOException e) {
        System.out.println("エラーが発生しました。入力をやり直してください。");
      }
      
      if (!(("cal").equals(command))) {
        if (command == null){
          System.out.println("コマンドに誤りがあります。実行可能なコマンドは\"cal\"です。あなたはコマンドを入力しませんでした。");
        }
        System.out.println("コマンドに誤りがあります。実行可能なコマンドは\"cal\"です。あなたの入力したコマンドは" + command + "でした。");
        continue;
      }

      if (!(Objects.isNull(rowMonth))) { // 引数がある場合は、月と年の制限表示の処理に進む。

        month = Byte.parseByte(rowMonth);
        year = Byte.parseByte(rowYear);
        
        if (!(month > 1 && month < 13)) {
          System.out.println("第一引数に誤りがあります。受付可能な数値は1から12です。");
          continue;
        }
        if (!(year > 0)) {
          System.out.println("第二引数に誤りがあります。受付可能な数値は1以上です。"); 
          continue;
        }

      }
      
    }
  }
}