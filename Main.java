import java.util.*;
import java.io.*;

public class Main {
// Error Code
// E1: IOException
// E2: ArrayIndexOutOfBoundsException

public static void main(String[] args) {

    String cmdLine = null;

    int argCnt = 0;
    int month = 0;
    int year = 0;
    int numOfDigitYear = 0;

    int[] inspectionResult = new int[5];
    int inspectionCode = -1;

    int firstDayOfWeek = 0;
    int endDayOfMonth = 0;

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("プログラム起動の旨");

    while (!(inspectionCode == 0)) {
        System.out.println("コマンドの使い方等");
        // $ の出力を行う？
        try {
            cmdLine = in.readLine();
        } catch (IOException e) {
            System.out.println("エラーが発生しました。エラーコード: E1");
            continue;
        }

        inspectionResult = InputInspection.inspection(cmdLine); // Command lineに入力された値の検査
        inspectionCode = inspectionResult[0];

        // 検査結果の検査コードを用いて判定結果を出力
        switch (inspectionCode) {
            case 0 : break;
            case 1 :
                System.out.println("入力したコマンドに誤りがある可能性があります。正しいコマンドは\"cal\"です。");
                continue;
            case 2 :
                System.out.println("入力したmonthの値に誤りがある可能性があります。");
                continue;
            case 3 :
                System.out.println("入力したyearの値に誤りがある可能性があります。");
                continue;
            case 4 :
                System.out.println("入力形式に誤りがある可能性があります。");
        }
    }

    argCnt = inspectionResult[1];
    month = inspectionResult[2];
    year = inspectionResult[3];
    numOfDigitYear = inspectionResult[4];

    // カレンダー操作
    Calendar calendar = Calendar.getInstance();  // CalendarのgetInstanceメソッドで得られるCalendarオブジェクトは、現在の日付と時間を返す。（現在の日付と時間が初期値）。

    calendar.setLenient(true);  // Calendarを厳密モードにする。無効な日付にエラーを吐く。　
    try {
        // 「今月の最初の曜日(一日目の曜日)」や「monthの最終日」を得るために、引数に応じてCalendarクラスのフィールドに値をセット。
        // 引数入力がない場合は、現在のmonth, year, yearの桁数を各変数セット。FormatCalendarクラスのフォーマット関数群に使用する。
        // 引数が1(monthだけ)の場合、year, yearの桁数を各変数セット。
        switch (argCnt) {
            case 0 :
                month = (calendar.get(Calendar.MONTH)) +1 ; // CalendarクラスのMONTHフィールドは 0 が JANUARY であるため、+1する。Calendarクラスで使用する値ではなく、そのままmonthとして利用するためである。例えば今月が10月なら、取得される値は9のため+1して10にする。
                year = calendar.get(Calendar.YEAR);
                numOfDigitYear = String.valueOf(calendar.get(Calendar.YEAR)).length();
            case 1 :
                calendar.set(Calendar.MONTH, month-1); // CalendarクラスのMONTHフィールドは 0 が JANUARY であるため -1 をする。month は JANUARY を 1 としている。
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                year = calendar.get(Calendar.YEAR);
                numOfDigitYear = String.valueOf(calendar.get(Calendar.YEAR)).length();
            case 2 :
                calendar.set(Calendar.MONTH, month-1);
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("エラーが発生しました。エラーコード: E2");
        }

    firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);  // 今月の最初の曜日(一日目の曜日)を取得
    endDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);  // monthの最終日を取得。


    // カレンダーをフォーマット
    FormatCalendar.formatTitleLine(month, year, numOfDigitYear);
    FormatCalendar.formatDayOfMonthLine(endDayOfMonth, firstDayOfWeek);

    // フォーマットされたカレンダーを出力。
    FormatCalendar.printCalendar(FormatCalendar.titleLine,
                  FormatCalendar.WEEK_OF_DAY_LINE,
                  FormatCalendar.dayOfMonthLine1,
                  FormatCalendar.dayOfMonthLine2,
                  FormatCalendar.dayOfMonthLine3,
                  FormatCalendar.dayOfMonthLine4,
                  FormatCalendar.dayOfMonthLine5,
                  FormatCalendar.dayOfMonthLine6);
  }

  }

  // ToDo list ========================================================================