import java.util.*;
import java.io.*;
import java.lang.*;

public class PerpetualCalendar {

// Constants for extracting cmd line input values
// The format is such an array => {000, 00, 0000}
final static int CMD_START = 1;
final static int CMD_END = 4;
final static int MONTH_START = 6;
final static int MONTH_END = 8;
final static int YEAR_START = 10;
final static int YEAR_END = 14;

// command type
final static String CMD = "command";
final static String MONTH = "month";
final static String YEAR = "year";

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

    // コマンドライン入力の検閲をする関数
    public static boolean cmdLineInspection(Strnig srcCmd, String cmdType, int cmdLen){ //cmdTypeの引数は定数command type。
      boolean inspectionResult = false;
      String tempSrcCmd = "";

      if (cmdType.equals(CMD)) {
        String tempCmd = "cal";  
      }
      if (cmdType.equals(MONTH)) {
        int limitL = 1;
        int limitH = 12;
      }
      if (cmdType.equals(YEAR)) {
        int limitL = 1;
        int limitH = 9999;
      }
      
            for (int i<1; i<cmdLine+1; i++) { // extractingStr()の仕様により+1する。
              tempSrcCmd = extractingStr(srcCmd, 1, i);
              tempCmd = extractingStr(cmd, 1, i);

              if (!(tempSrcCmd.equals(tempCmd)) {
                return inspectionResult;
              }
            }
      inspectionResult = true;
      return inspectionResult;
    }

    // ToDo

    // - コマンドラインの引数に無効な入力を受けたときに弾く処理
    // - - cal
    // - - - caaaaal, cccccccal, abc
    // - - 12
    // - - - 111112, 122222
    // - - 2022
    // - - - 2222222222202, 200000022, 2022222222222222

    // コマンドライン検査でcalだけでなく数値も検査できるようにする。