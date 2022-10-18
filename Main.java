import java.util.*;
import java.io.*;

public class Main {

// Error Code
// E1: IOException
// E2: ArrayIndexOutOfBoundsException

// available command lines
final static String CMD_LINE_COMMAND = "cal";

// Whitespace to be inserted to determine the number of arguments
final static String BLANK_FOR_JUDGEMENT = " ";

// Range of possible values for each command and argument on the command line
final static int COMMAND_LEN = 4;
final static int MONTH_MAXLEN = 2;
final static int MONTH_LIMIT_L = 1;
final static int MONTH_LIMIT_H = 12;
final static int YEAR_MAXLEN = 4;
final static int YEAR_LIMIT_L = 1;
final static int YEAR_LIMIT_H = 9999;

// Array corresponding to the calendar class DAY_OF_WEEK
final static String[] WEEK_OF_THE_DAY_NAMES = {"", "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};


  public static void main(String[] args) {


    String cmdLine = "";
    String cmd = "";
    int month = 0;
    int year = 0;
    
    int[] inspectionResult = {0, 0, 0};
    
    int argCnt = 0;
    int NumOfDigitInMonth = 0;
    int NumOfDigitInYear = 0;
    int inspectionCode = 0;
    
    int monthsAcquired = 0;
    int yearAcquired = 0;
    int dayAcquired = 0;
    int actualMaxValForMonth = 0;
    
    
    Calendar calendar = Calendar.getInstance();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    
    System.out.println("プログラム起動の旨");
    
    while (true) {
      System.out.println("コマンドの使い方等");
      // $ の出力を行う？

      try {
        cmdLine = in.readLine();
        cmdLine = cmdLine + BLANK_FOR_JUDGEMENT;
      }
      catch (IOException e) {
        System.out.println("エラーが発生しました。エラーコード: E1");
        continue;
      }
      
      
      inspectionResult = cmdLineInspection(cmdLine); // Command lineに入力された値の検査
      inspectionCode = inspectionResult[0]; 
      NumOfDigitInMonth = inspectionResult[1]; //monthの桁数を取り出す
      NumOfDigitInYear = inspectionResult[2];
    
    // 検査結果の検査コードを用いて判定結果を出力
      switch(inspectionCode) {
        case 0:
          break;
        case 1:
          System.out.println("入力したコマンドに誤りがある可能性があります。正しいコマンドは\"cal\"です。");
          continue;
        case 2:
          System.out.println("入力したmonthの値に誤りがある可能性があります。");
          continue;
        case 3:
          System.out.println("入力したyearの値に誤りがある可能性があります。");
          continue;
      }
    }
    
    
    // 引数の数を判定
    // monthとyearの桁数が1以上の場合は引数が存在するとして判定する。
    if (NumOfDigitInMonth > 0) { // 第一引数がない場合は、第二引数もない
        argCnt = argCnt + 1;
        
        if (NumOfDigitInYear > 0) {
            argCnt = argCnt + 1;
        }
    }
    
    
    // 引数に応じて、cmdLine の文字列を分解してそれぞれの変数に格納
    switch (argCnt) {
        case 0 :
            cmd = cmdLine.substring(1, COMMAND_LEN+1);
            break;
        case 1 :
            cmd = cmdLine.substring(1, COMMAND_LEN+1);
            month = Integer.parseInt(cmdLine.substring((COMMAND_LEN+2),
                                                      (COMMAND_LEN + 1 + NumOfDigitInMonth)
                                                      ));
            break;
        case 2 :
            cmd = cmdLine.substring(1, COMMAND_LEN+1);
            month = Integer.parseInt(cmdLine.substring((COMMAND_LEN+2),
                                                      (COMMAND_LEN + 1 + NumOfDigitInMonth)
                                                      ));
            year = Integer.parseInt(cmdLine.substring((COMMAND_LEN + 1 + NumOfDigitInMonth + 2),
                                                     (COMMAND_LEN + 1 + NumOfDigitInMonth + 1 + NumOfDigitInYear + 1)
                                                     ));        
    }
    
    
    // 引数の数、値に応じた日付をセット。
    calendar.setLenient(true); // Calendarを厳密モードにする。get引数の違反検知に使う。
    calendar.clear(); // カレンダーフィールドをクリア。　
    
    try {
        switch (argCnt) { // 大文字の変数はCalenderクラスのフィールド。
            case 0 :
                break;
            case 1 :
                calendar.set(Calendar.MONTH, month);
            case 2 :
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);
        }
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("エラーが発生しました。エラーコード: E2");
    }
    
    
    // 日付の取得
    actualMaxValForMonth = calendar.getActualMaximum(calendar.MONTH); // monthが取りえる最大値を取得。すでにmonthをCalender.MONTHに設定しているため引数としている。


    // 
    
    

// 日付の取得
// 取得値からカレンダーをフォーマット
// カレンダーの生成

  }

    // コマンドライン入力内容をすべて検閲をする関数
    // 戻り値 
        // inspectionResul: 0 -> すべて問題なし, 1 -> コマンドに問題あり, 2-> 引数monthに問題あり, 3-> 引数yearに問題あり 
        // monthValSize: monthの桁数。
        // yearValSize: yearの桁数。
    public static int[] cmdLineInspection(String srcCmdLine){ 
        String SrcCmd = "";
        String compareCmd = "";
        
        String srcMonthStr = "";
        int    srcMonthInt  = 0;
        int    monthValSize = 0;

        String srcYearStr = "";
        int    srcYearInt  = 0;
        int    yearValSize = 0;
        
        String extractedStr = "";
        int inspectionResult = 0;
        int[] result = {inspectionResult, monthValSize, yearValSize};

    // Command

        for (int i=1; i<(COMMAND_LEN+1); i++) { //上限値はextractingStr()の仕様により+1
          srcCmdLine = srcCmdLine.substring(i, i+1);
            compareCmd = CMD_LINE_COMMAND.substring(i, i+1); // substring(i, i+1) は、抽出対象の文字が右に進んでいく記述。
        
            if (!(srcCmdLine.equals(compareCmd))) {
                inspectionResult = 1;
                return result;
            }
        }

    // Month
    // (COMMAND_LEN + 2) は、"cal_9_"の 9。 

        srcMonthStr = srcCmdLine.substring((COMMAND_LEN + 2), (COMMAND_LEN + 3));

        for (int i=1; i<3; i++) { // monthの桁を判別。空白を検出しなかった回数が桁数。
            extractedStr = srcMonthStr.substring(i, i+1);
            if (!(extractedStr.equals(" "))) { 
                monthValSize = monthValSize + 1; 
            }
        }
        switch (monthValSize) {
            case 1: // 9_ -> 9
                srcMonthInt = Integer.parseInt(srcCmdLine.substring((COMMAND_LEN + 2), (COMMAND_LEN + 3))); // 1桁目のみ抽出。
            case 2: // 10 -> 10
                srcMonthInt = Integer.parseInt(srcCmdLine.substring((COMMAND_LEN + 2), (COMMAND_LEN + 4))); // 1桁目のみ抽出。   
        }
        if (!(srcMonthInt >= MONTH_LIMIT_L && srcMonthInt <= MONTH_LIMIT_L)) {
            inspectionResult = 2;
        }

  
  // Year
  // (COMMAND_LEN + 1 + MONTH_MAXLEN + 2)は、"cal 10 1234" の "1" の位置を指す.

        srcYearStr = srcCmdLine.substring((COMMAND_LEN + 1 + MONTH_MAXLEN + 2), (COMMAND_LEN + 6));

        for (int i=1; i<5; i++) { 
            extractedStr = srcYearStr.substring(i, i+1);
            if (!(extractedStr.equals(" "))) { 
                yearValSize = yearValSize + 1; 
            }
        }
        switch (yearValSize) {
            case 1 :
                srcYearInt = Integer.parseInt(srcCmdLine.substring((COMMAND_LEN + 1 + MONTH_MAXLEN + 2), (COMMAND_LEN + 1 + MONTH_MAXLEN + 3)));
            case 2 :
                srcYearInt = Integer.parseInt(srcCmdLine.substring((COMMAND_LEN + 1 + MONTH_MAXLEN + 2), (COMMAND_LEN + 1 + MONTH_MAXLEN + 4)));
            case 3 :
                srcYearInt = Integer.parseInt(srcCmdLine.substring((COMMAND_LEN + 1 + MONTH_MAXLEN + 2), (COMMAND_LEN + 1 + MONTH_MAXLEN + 5)));
            case 4 :
                srcYearInt = Integer.parseInt(srcCmdLine.substring((COMMAND_LEN + 1 + MONTH_MAXLEN + 2), (COMMAND_LEN + 1 + MONTH_MAXLEN + 6)));
        }            
        if (!(srcYearInt >= YEAR_LIMIT_L && srcYearInt <= YEAR_LIMIT_L)) {
            inspectionResult = 3;
        }  
            
      return result;
    }
  }
    
    


// ToDo list ========================================================================

// - arg を argに名称を変更する
// - class名を、Mainに変える。
// - FormatCalendarクラスを作る
// - java.util.Calendarによる日付の取得
// - 取得値からカレンダーをフォーマット
// - カレンダーの生成

// issue list ========================================================================



//memo ===============================================================================
// year が指定されるとき、必ずmonthが指定される。

// コマンドラインの引数の数を判断する関数 => スペースの数で判定。

// cal 10 1000
// cal 9 1

// cal_          space->
// cal_12_       space->2
// cal_12_2022_  spacce->3
// cal_12_2022__ space->4

// Test case ==========================================================================
// - "_cal_12_2002" => ユーザが空白を入力しているため正しい引数の判定が行えない。



// trush code==========================================================================

/*-----------------------------------------------------------------------
    // 引数の数を判定する関数
        // 戻り値 argCnt: 0 -> commandのみで引数なし, 1 -> 引数が一つ(month), 2 -> 引数が二つ(year) 
        // cal_          space*1 -> argument count 0
        // cal_12_       space*2 -> argument count 1
        // cal_12_2022_  space*3 -> argument count 2

   
    public static int judgeTheNumOfarg(Strnig srcCmdLine) {
        int argCnt = 0;
        char extractedStr = "";
        int blankCnt = 0;
        
        for (int i=1; i<srcCmdLine.length(); i++)) {  // 空白の数を数える
            extractedStr = srcCmdLine.substring(i, i);
            if (extractedStr.equals(" ")) {
                blankCnt++;
            }
        }
        
        switch (blankCnt) {
            case 1:
                return argCnt;
            case 2:
                argCnt = 1;
                return argCnt;
            case 3:
                argCnt = 2;
                return argCnt;
        }
    }
*/

/*--------------------------------------------------------------
    // コマンドライン入力内容をすべて検閲をする関数
    // 戻り値 
        // inspectionResul: 0 -> すべて問題なし, 1 -> コマンドに問題あり, 2-> 引数monthに問題あり, 3-> 引数yearに問題あり 
        // monthValSize: monthの桁数。
        // yearValSize: yearの桁数。
    public static int[] cmdLineInspection(String srcCmdLine, int argCnt){ 
        String SrcCmd = "";
        String compareCmd = "";
        
        String srcMonthSTR = "";
        int    srcMonthINT  = 0;
        int    monthValSize = 0;

        String srcYearSTR = "";
        int    srcYearINT  = 0;
        int    yearValSize = 0;
        
        String extractedStr = "";
        int inspectionResult = 0;
        int[] result = {inspectionResult, monthValSize, yearValSize};

        // Command
        if (argCnt == 0) {
            for (int i=1; i<(COMMAND_LEN+1); i++) { //上限値はextractingStr()の仕様により+1
                srcCmd = srcCmdLine.substring(i, i+1);
                compareCmd = COMMAND.substring(i, i+1); // substring(i, i+1) は、抽出対象の文字が右に進んでいく記述。
            
                if (!(srcCmd.equals(compareCmd)) {
                    inspectionResult = 1;
                    return result;
                }
            }
        }
      

        // Month
        // (COMMAND_LEN + 2) は、"cal_9_"の 9。 
        if (argCnt == 1 || argCnt == 2) {
            srcMonthSTR = srcCmdLine.substring((COMMAND_LEN + 2), (COMMAND_LEN + 3));

            for (int i=1; i<3; i++) { // monthの桁を判別。空白を検出しなかった回数が桁数。
                extractedStr = srcMonthSTR.substring(i, i+1);
                if (!(extractedStr.equals(" "))) { 
                    monthValSize = monthValSize + 1; 
                }
            }
            switch (monthValSize) {
                case 1: // 9_ -> 9
                    srcMonthINT = Integer.parseInt(srcCmdLine.substring((COMMAND_LEN + 2), (COMMAND_LEN + 3))); // 1桁目のみ抽出。
                case 2: // 10 -> 10
                    srcMonthINT = Integer.parseInt(srcCmdLine.substring((COMMAND_LEN + 2), (COMMAND_LEN + 4))); // 1桁目のみ抽出。   
            }
            if (!(srcMonthINT => MONTH_LIMIT_L && srcMonthINT <= MONTH_LIMIT_L)) {
                inspectionResult = 2;
            }
        }
      
      // Year
      // (COMMAND_LEN + 1 + MONTH_MAXLEN + 2)は、"cal 10 1234" の "1" の位置を指す.
        if (argCnt == 2) {
            srcYearSTR = srcCmdLine.substring((COMMAND_LEN + 1 + MONTH_MAXLEN + 2), (COMMAND_LEN + 6));

            for (int i=1; i<5; i++) { 
                extractedStr = srcYearSTR.substring(i, i+1);
                if (!(extractedStr.equals(" "))) { 
                    yearValSize = yearValSize + 1; 
                }
            }
            switch (yearValSize) {
                case 1:
                    srcYearINT = Integer.parseInt(srcCmdLine.substring((COMMAND_LEN + 1 + MONTH_MAXLEN + 2), (COMMAND_LEN + 1 + MONTH_MAXLEN + 3));
                case 2:
                    srcYearINT = Integer.parseInt(srcCmdLine.substring((COMMAND_LEN + 1 + MONTH_MAXLEN + 2), (COMMAND_LEN + 1 + MONTH_MAXLEN + 4));
                case 3:
                    srcYearINT = Integer.parseInt(srcCmdLine.substring((COMMAND_LEN + 1 + MONTH_MAXLEN + 2), (COMMAND_LEN + 1 + MONTH_MAXLEN + 5));
                case 4:
                    srcYearINT = Integer.parseInt(srcCmdLine.substring((COMMAND_LEN + 1 + MONTH_MAXLEN + 2), (COMMAND_LEN + 1 + MONTH_MAXLEN + 6));
            }            
            if (!(srcYearInt => YEAR_LIMIT_L && srcYearInt <= YEAR_LIMIT_L)) {
                inspectionResult = 3;
            }  
        }
      return inspectionResult;
    }

*/

