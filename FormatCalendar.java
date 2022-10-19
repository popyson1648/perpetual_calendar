import java.lang.ref.Cleaner;
import java.time.Year;
import java.util.*;
public class FormatCalendar {

/*
October 2000       // titleLine[]
Su Mo Tu We Th Fr Sa   // weekOfDayLine[]
 1  2  3  4  5  6  7   // monthOfDayLine1[]
 8  9 10 11 12 13 14   // monthOfDayLine2[]
15 16 17 18 19 20 21   // monthOfDayLine3[]
22 23 24 25 26 27 28   // monthOfDayLine4[]
29 30 31               // monthOfDayLine5[]
*/

final static String MONTH_1  = "January";
final static String MONTH_2  = "February";
final static String MONTH_3  = "March";
final static String MONTH_4  = "April";
final static String MONTH_5  = "May";
final static String MONTH_6  = "June";
final static String MONTH_7  = "July";
final static String MONTH_8  = "August";
final static String MONTH_9  = "September";
final static String MONTH_10 = "October";
final static String MONTH_11 = "November";
final static String MONTH_12 = "December";

final static String DELIMITED_BLANK = " ";
final static String BLANK = " ";

final static String[] WEEK_OF_DAY_TYPE_ARR = {"", "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
final static String[] WEEK_OF_DAY_LINE = {"Su", BLANK, "Mo", BLANK, "Tu", BLANK, "We", BLANK, "Th", BLANK, "Fr", BLANK, "Sa"};

final static String[] DAYS_STR = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

final static int LINE_LENGTH = 20;

static String[] titleLine       = new String[20];
static String[] dayOfMonthLine1 = new String[20];
static String[] dayOfMonthLine2 = new String[20];
static String[] dayOfMonthLine3 = new String[20];
static String[] dayOfMonthLine4 = new String[20];
static String[] dayOfMonthLine5 = new String[20];

    public static String[] initStrArr(String[] targetArray){
        for (String x: targetArray) x = "";
        return targetArray;
    }


    public static int[] initIntArr(int[] targetArray){
        for (int x: targetArray) x = 0;
        return targetArray;
    }


    public static String[] formatTitleLine(int month, int year, int numOfDigitYear) {

        String monthName = "";
        String extractingMonthStr = "";
        int monthNameLen = 0;
        int workMonthNameLen = 0;

        int workNumOfDigitYear = numOfDigitYear;
        String yearStr = "";
        String extractingYearStr = "";

        int lineMidPotision = LINE_LENGTH / 2;
        int lenLineTop = 0;
        int lenLineBottom = 0;

        int delimitedBlankPositionType = 0;
        int delimitedBlankPosition = 0;
        int lineTopBlankCnt = 0;
        int lineBottomBlankCnt = 0;
        int workLineTopBlankCnt = 0;
        int workLineBottomBlankCnt = 0;

        int arrayIndexCnt = 0;

        boolean isTopBlankInsertionCpl = false;
        boolean isBottomBlankInsertionCpl = false;
        boolean isDelimitedBlankInsertionCpl = false;
        boolean isMonthNameInsertionCpl = false;
        boolean isYearNumInsertionCpl = false;


        initStrArr(titleLine);

        // month の名前と、名前の長さを変数に設定。
        switch (month) {
            case 1:
                monthName = MONTH_1;
                monthNameLen = MONTH_1.length();
                workMonthNameLen = monthNameLen;
            case 2:
                monthName = MONTH_2;
                monthNameLen = MONTH_2.length();
                workMonthNameLen = monthNameLen;
            case 3:
                monthName = MONTH_3;
                monthNameLen = MONTH_3.length();
                workMonthNameLen = monthNameLen;
            case 4:
                monthName = MONTH_4;
                monthNameLen = MONTH_4.length();
                workMonthNameLen = monthNameLen;
            case 5:
                monthName = MONTH_5;
                monthNameLen = MONTH_5.length();
                workMonthNameLen = monthNameLen;
            case 6:
                monthName = MONTH_6;
                monthNameLen = MONTH_6.length();
                workMonthNameLen = monthNameLen;
            case 7:
                monthName = MONTH_7;
                monthNameLen = MONTH_7.length();
                workMonthNameLen = monthNameLen;
            case 8:
                monthName = MONTH_8;
                monthNameLen = MONTH_8.length();
                workMonthNameLen = monthNameLen;
            case 9:
                monthName = MONTH_9;
                monthNameLen = MONTH_9.length();
                workMonthNameLen = monthNameLen;
            case 10:
                monthName = MONTH_10;
                monthNameLen = MONTH_10.length();
                workMonthNameLen = monthNameLen;
            case 11:
                monthName = MONTH_11;
                monthNameLen = MONTH_11.length();
                workMonthNameLen = monthNameLen;
            case 12:
                monthName = MONTH_12;
                monthNameLen = MONTH_12.length();
                workMonthNameLen = monthNameLen;
            }

            // monthの長さから区切り空白の挿入位置タイプを判定
            switch (monthNameLen) {
                case 3 : delimitedBlankPositionType = 4;
                case 4 : delimitedBlankPositionType = 2;
                case 5 : delimitedBlankPositionType = 3;
                case 6 : delimitedBlankPositionType = 2;
                case 7 : delimitedBlankPositionType = 2;
                case 8 : delimitedBlankPositionType = 1;
                case 9 : delimitedBlankPositionType = 1;
            }

            // 区切り空白の挿入位置を変数に設定
            switch (delimitedBlankPositionType) {
                case 1: delimitedBlankPosition = lineMidPotision + 3;
                case 2: delimitedBlankPosition = lineMidPotision + 2;
                case 3: delimitedBlankPosition = lineMidPotision + 1;
                case 4: delimitedBlankPosition = lineMidPotision - 1;
            }

            // titleLineのtopとbottomの長さを計算し変数に設定
            lenLineTop = delimitedBlankPosition - 1;
            lenLineBottom = LINE_LENGTH - delimitedBlankPosition;

            // titleLineのtopとbottomの「穴埋め用空白」の挿入数を計算し変数に設定
            lineTopBlankCnt = lenLineTop - monthNameLen;
            lineBottomBlankCnt = lenLineBottom - numOfDigitYear;
            workLineBottomBlankCnt = lineBottomBlankCnt;
            workLineTopBlankCnt = lineTopBlankCnt;

            // titleLine[] に 穴埋め用空白, 月名, 区切り空白, 西暦年数, 穴埋め用空白 を挿入
            // トップの穴埋め用空白を挿入
            while (!(isTopBlankInsertionCpl)) {
                for (int i=arrayIndexCnt; i<workLineTopBlankCnt+1; i++) {
                    titleLine[i] = BLANK;
                }
                isBottomBlankInsertionCpl = true;
            }
            // 月名
            while (!(isMonthNameInsertionCpl)) {
                // 初期値のみが異なり、条件、インクリメント量は同じであるようなfor処理を実装している
                int s = 1; //substring用。
                int i = arrayIndexCnt;
                while (i < workMonthNameLen && s < workMonthNameLen) {
                    extractingMonthStr = monthName.substring(s, s+1);
                    titleLine[i] = extractingMonthStr;
                    s++;
                    i++;
                }
                isMonthNameInsertionCpl = true;
            }
            // 区切り空白
            while (!(isDelimitedBlankInsertionCpl)) {
                titleLine[arrayIndexCnt] = DELIMITED_BLANK;
                isDelimitedBlankInsertionCpl = true;
            }
            // 西暦
            while (!(isYearNumInsertionCpl)) {
                yearStr = String.valueOf(year); // int型yearをString型に変換
                int s = 1;
                int i = arrayIndexCnt;
                while (i < workNumOfDigitYear && s < workNumOfDigitYear) {
                    extractingYearStr = yearStr.substring(s, s+1);
                    titleLine[i] = extractingYearStr;
                    s++;
                    i++;
                }
                isYearNumInsertionCpl = true;
            }
            // ボトムの穴埋め用空白を挿入
            while (!(isBottomBlankInsertionCpl)) {
                for (int i=arrayIndexCnt; i<workLineBottomBlankCnt+1; i++) {
                    titleLine[i] = BLANK;
                }
                isBottomBlankInsertionCpl = true;
            }

            return titleLine;
    }


    public static String[] formatDayOfMonthLine(int month, int year, int endDayOfMonth, int dayOfWeekType) {
        // Su Mo Tu We Th Fr Sa
        //  1  2  3  4  5  6  7

        //     S   u   _   M   o   _   T   u   _   W   e   _   T   h   _   F   r   _   S   a
        //    [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
        //     0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19

        //     February 1990
        // Su Mo Tu We Th Fr Sa
        // ..*..*..*..*.1  2  3
        //  4  5  6  7  8  9 10
        // 11 12 13 14 15 16 17
        // 18 19 20 21 22 23 24
        // 25 26 27 28


        //   !!!!!!!!!!!!!!!!!ToDooooooooo!!!!!!!!!!!! DAYS_STR は、一桁の数字は「空白」と「数字」に分ける。二桁の数字は、「一桁目」と「二桁目」に分ける。
        int firstDayIndex = 0;
        int lineStoreLimit = 0;

        // DayOfMonthLine1[] に 1（1日目）を格納する場所（添え字）を決定し、変数に設定する。
        switch (dayOfWeekType) {
            case 1 : firstDayIndex = 1;
            case 2 : firstDayIndex = 4;
            case 3 : firstDayIndex = 7;
            case 4 : firstDayIndex = 10;
            case 5 : firstDayIndex = 13;
            case 6 : firstDayIndex = 16;
            case 7 : firstDayIndex = 19;
        }

        lineStoreLimit = endDayOfMonth;

        // DayOfMonthLine1[]
        for (int i=0; i<21; i++) {
            if (firstDayIndex > 0 && lineStoreLimit < 10) {  //格納するものがBLANKか、10以下の数字の場合は空白を挿入する。
                dayOfMonthLine1[i] = BLANK;
            }
            // firstDayIndex が0になるまでは空白を格納する。空白の後に格納するため i+1。
            dayOfMonthLine1[i+1] = BLANK;
            // 数字を格納。
            if (i<10) {
                dayOfMonthLine1[i+1] = 一桁の数字;
            }
            dayOfMonthLine1[i]

            }
            dayOfMonthLine1[i] = DAYS_STR[i];
        }

        // 以下の場合は、BLANK を挿入する。
        // @ lineStoreLimit が 10 より小さい場合
        // @ 挿入するものが BLANK の場合（firatDayIndexが0ではない場合）、
    }








// -------------------------------------------------------------------------------------------------------------------------------
    public static void FUNCTION_FOR_MAIN() {

        Calendar calendar = Calendar.getInstance();  // CalendarのgetInstanceメソッドで得られるCalendarオブジェクトは、現在の日付と時間を返す。（現在の日付と時間が初期値）。

        calendar.setLenient(true);  // Calendarを厳密モードにする。無効な日付にエラーを吐く。　
        try {
            // 引数に応じてCalenarクラスのフィールドに値をセット。引数入力がない場合は、現在の日付、時間が使われる。
            switch (argCnt) {
                case 0 :
                    break;
                case 1 :
                    calendar.set(Calendar.MONTH, month-1); // CalendarクラスのMONTHフィールドは 0 が JANUARY であるため -1をする。
                case 2 :
                    calendar.set(Calendar.MONTH, month-1);
                    calendar.set(Calendar.YEAR, year);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("エラーが発生しました。エラーコード: E2");
            }
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);

            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 今月の最初の曜日(一日目の曜日)を取得
            endDayOfMonth = calendar.getActualMaximum(Calendar.MONTH); // monthの最終日を取得。
    }


}

// issues ===============================================================================
/*

Calendarの取得と日助の設定、曜日の取得など、情報の取得はメインクラスで行う。
このクラスでは、あくまでもフォーマット、つまり、文字列データの操作が主である。

#######################################
################# Calendarクラスのmonthは 0 -> 1である。=====> 定数で、0を1に変換して扱おう
#######################################

*/

// memo ===============================================================================
/*

// Day of Month のフォーマット方法
- 一日目を挿入するインデックスを変数に設定する
- DayOfMonthLine[] の格納上限数を変数に設定する


// Mainクラスで行うこと
- Calendarクラスのインスタンスを作成（現在の日付、時間を取得）
- ユーザ指定のmonth, year を設定 ----->  ユーザの引数入力がない場合は、Calendarのインスタンス作成時に取得した現在の日付、時間を設定する
- monthまたは今月の最終日を取得
- 今月の一日目の曜日 (今月の最初の曜日)を取得。
-

titleの配置
-- 挿入文字数(Len)を求める。20 / Len = middle
Len = 13;
mid = 20/2;  // 10
lenMid = Math.ceil(Len/2);  // 6.5 -> 7

1. 月名の長さから、区切り空白挿入位置を確定
2. 「titleLineの半分(区切り空白)から左の要素数」が確定(t_Left)
3. leading = t_Left - 月名の長さ  //先頭を埋める空白の数
4. 配列の内容は、{ledingの数だけ空白を挿入, month name, 区切り空白, year number}


 1   2   3   4   5   6   7   8   9    10  11  12  13  14  15  16  17  18  19  20
 S   u   _   M   o   _   T   u   _    W    e   _   T   h   _   F   r   _   S   a
[ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [mid] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
                             J   u    n    e   _   2   0   2   2

lenLinetTop = blankType - 1
    11        mid+2=12

Weの間が10(mid)。
Su_Mo_Tu_We_Th_Fr_Sa
    ______|____
   September 1990  9  type-1  mid+3(空白の位置)
    ______|____
    February 1990  8  type-1  mid+3
    ______|____
    January 1990   7  type-2  mid+2
    ______|____
     August 1990   6  type-2  mid+2
    ______|____
     March 1990    5  type-3  mid+1
    ______|____
       June 1990   4  type-2  mid+2
    ______|____
      May 1990     3  type-4  mid-1

    February 1990
Su Mo Tu We Th Fr Sa
             1  2  3
 4  5  6  7  8  9 10
11 12 13 14 15 16 17
18 19 20 21 22 23 24
25 26 27 28

   September 1990
Su Mo Tu We Th Fr Sa
                   1
 2  3  4  5  6  7  8
 9 10 11 12 13 14 15
16 17 18 19 20 21 22
23 24 25 26 27 28 29
30

     March 1990
Su Mo Tu We Th Fr Sa
             1  2  3
 4  5  6  7  8  9 10
11 12 13 14 15 16 17
18 19 20 21 22 23 24
25 26 27 28 29 30 31

      May 1990
Su Mo Tu We Th Fr Sa
       1  2  3  4  5
 6  7  8  9 10 11 12
13 14 15 16 17 18 19
20 21 22 23 24 25 26
27 28 29 30 31

     August 1990
Su Mo Tu We Th Fr Sa
          1  2  3  4
 5  6  7  8  9 10 11
12 13 14 15 16 17 18
19 20 21 22 23 24 25
26 27 28 29 30 31

      June 1990
Su Mo Tu We Th Fr Sa
                1  2
 3  4  5  6  7  8  9
10 11 12 13 14 15 16
17 18 19 20 21 22 23
24 25 26 27 28 29 30

*/
