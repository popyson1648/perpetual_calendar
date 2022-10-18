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

// Array corresponding to the calendar class DAY_OF_WEEK
final static String[] WEEK_OF_THE_DAY_NAMES = { "", "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };

final static String ADJUST_BLANK = " ";

static String[] titleLine     = new String[20];
static String[] weekOfDayLine = {"Su", ADJUST_BLANK, "Mo", ADJUST_BLANK, "Tu", ADJUST_BLANK, "We", ADJUST_BLANK, "Th", ADJUST_BLANK, "Fr", ADJUST_BLANK, "Sa"};
static int[] monthOfDayLine1 = new int[20];
static int[] monthOfDayLine2 = new int[20];
static int[] monthOfDayLine3 = new int[20];
static int[] monthOfDayLine4 = new int[20];
static int[] monthOfDayLine5 = new int[20];

static int mainMonth  = Main.month;
static int mainYear   = Main.year;
static int mainArgCnt = Main.argCnt;
static int mainNumOfDigitInYear = Main.NumOfDigitInYear;

static Calendar calendar = Calendar.getInstance();


    public static String[] initStrArr(String[] targetArray){
        for (String x: targetArray) x = "";
        return targetArray;
    }


    public static int[] initIntArr(int[] targetArray){
        for (int x: targetArray) x = 0;
        return targetArray;
    }


    public static String[] formatTitle() {
        int monthNameLen = 0;
        int monthNameAndYearLen = monthNameLen + mainNumOfDigitInYear;
        int mid = 20 / 2;

        initStrArr(titleLine);

        // month の名前の長さを取得
        switch (mainMonth) {
            case 1:
                monthNameLen = MONTH_1.length();
            case 2:
                monthNameLen = MONTH_2.length();
            case 3:
                monthNameLen = MONTH_3.length();
            case 4:
                monthNameLen = MONTH_4.length();
            case 5:
                monthNameLen = MONTH_5.length();
            case 6:
                monthNameLen = MONTH_6.length();
            case 7:
                monthNameLen = MONTH_7.length();
            case 8:
                monthNameLen = MONTH_8.length();
            case 9:
                monthNameLen = MONTH_9.length();
            case 10:
                monthNameLen = MONTH_10.length();
            case 11:
                monthNameLen = MONTH_11.length();
            case 12:
                monthNameLen = MONTH_12.length();
            }

            if (aaaaaaaaaa % 2 == 0) {

            }

    }


    public static String[] formatWeekOfDay(){
    }


    public static String[] formatMonthDay(int lastDayOfTheMonthType){
        int actualMaxValForMonth = 0;

        // 引数の数、値に応じた日付をセット。
        calendar.setLenient(true);  // Calendarを厳密モードにする。get引数の違反検知に使う。
        calendar.clear();           // カレンダーフィールドをクリア。　
        try {
            switch (mainArgCnt) {  // 大文字の変数はCalenderクラスのフィールド。
                case 0 :
                    break;
                case 1 :
                    calendar.set(Calendar.MONTH, mainMonth);
                case 2 :
                    calendar.set(Calendar.MONTH, mainMonth);
                    calendar.set(Calendar.YEAR, mainYear);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("エラーが発生しました。エラーコード: E2");
        }

        actualMaxValForMonth = calendar.getActualMaximum(Calendar.MONTH); // monthが取りえる最大値を取得。すでにmonthをCalender.MONTHに設定しているため引数としている。


    }


}

// memo
/*

titleの配置
-- 挿入文字数(Len)を求める。20 / Len = middle
Len = 13;
mid = 20/2;  // 10
lenMid = Math.ceil(Len/2);  // 6.5 -> 7

1. 月名の長さから、空白挿入位置を確定
2. titleLineの半分から左の要素数が確定(t_Left)
3. t_Left - 月名の長さ

--
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