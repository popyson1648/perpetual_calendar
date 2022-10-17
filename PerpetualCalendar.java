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
    boolean 

      // $ の出力をおこなう？
      System.out.println("説明"); // 説明を書く
      try {
        cmdLine = in.readLine();
      } catch (IOException e) {
        System.out.println("エラーが発生しました。入力をやり直してください。");
      }

      // extractingStr() を使って、コマンドの抽出を１文字ずつ行いコマンドラインの検査を行う
      // cmdLineInspection....

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


    // ToDo
    