import java.util.*;
import java.io.*;
import java.lang.*;

public class PerpetualCalendar {

// available command lines
final static String COMMAND = "cal";
final static String MONTH = "month";
final static String YEAR = "year";

// Range of possible values for each command and argument on the command line
final static int COMMAND_LEN = 4;
final static int MONTH_LIMIT_L = 1;
final static int MONTH_LIMIT_H = 12;
final static int YEAR_LIMIT_L = 1;
final static int YEAR_LIMIT_H = 9999;


  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    String cmdLine = "";
    String cmd = "";
    int month = 0;
    int year = 0;
    int inspectionResult = 0;
    
    System.out.println("プログラム起動の旨");
    while (true) {
      System.out.println("コマンドの使い方等");
      // $ の出力を行う？

      try {
        cmdLine = in.readLine();
      }
      catch (IOException e) {
        System.out.println("エラーが発生しました。入力をやり直してください。");
      }

      switch(cmdLineInspection(cmdLine)) {
        case 0:
          break;
        case 1:
          System.out.println("入力したコマンドに誤りがあります。正しいコマンドは¥"cal¥"です。");
          continue;
        case 2:
          System.out.println("入力したmonthの値に誤りがあります。");
          continue;
        case 3:
          System.out.println("入力したyearの値に誤りがあります。");
          continue;
      }
    }

// 日付の取得
// 取得値からカレンダーをフォーマット
// カレンダーの生成

  }


    // 文字列から任意の範囲の文字を取り出す関数
    public static String extractingStr(String srcVar, int extractingStart, int extractingEnd) { // 抽出される文字 x は、extractingStart　=< x < extractingEnd
        String x = "";
        x = srcVar.substring(extractingStart, extractingEnd);
        return x;
    }


    // コマンドライン入力内容をすべて検閲をする関数
    // cmdTypeの引数は定数command type。
    // 戻り地は、0 -> すべて問題なし 1 -> コマンドに問題あり 2-> 引数monthに問題あり 3-> 引数yearに問題あり 
    public static int cmdLineInspection(Strnig srcCmdLine){ 
      int inspectionResult = 0;
      String SrcCmd = "";
      String = compareCmd = "";
      int srcArgm = 0;

      // compare Command
        for (int i=1; i<(COMMAND_LEN+1); i++) { //上限値はextractingStr()の仕様により+1
          srcCmd = extractingStr(srcCmdLine, 1, i);
          compareCmd = extractingStr(COMMAND, 1, i);

        if (!(srcCmd.equals(compareCmd)) {
          return inspectionResult = 1;
        }
      } 

      // compare month
      // COMMAND_LEN+1+MONTH_LEN は、"cal 10" の "1" の位置を指す
        srcArgm = Integer.parseInt(extractingStr(srcCmdLine,
                                                (COMMAND_LEN+1+MONTH_LEN),
                                                (COMMAND_LEN+1+MONTH_LEN+1));
        if (!(srcArgm => MONTH_LIMIT_L && srcArgm <= MONTH_LIMIT_L)) {
          return inspectionResult = 2;
        }

      // compare year
      // COMMAND_LEN+1+MONTH_LEN+1+YEAR_LEN は、"cal 10 2000" の "2" の位置を指す
        srcArgm = Integer.parseInt(extractingStr(srcCmdLine,
                                                  (COMMAND_LEN+1+MONTH_LEN+1+YEAR_LEN),
                                                  (COMMAND_LEN+1+MONTH_LEN+1+YEAR_LEN)+1));
          if (!(srcArgm => YEAR_LIMIT_L && srcArgm <= YEAR_LIMIT_L)) {
            return inspectionResult = 3;
          }  
      inspectionResult = true;
      return inspectionResult;
    }


// ToDo==================================================-
// - class名を、Mainに変える。
// - FormatCalendarクラスを作る
// - java.util.Calendarによる日付の取得
// - 取得値からカレンダーをフォーマット
// - カレンダーの生成 