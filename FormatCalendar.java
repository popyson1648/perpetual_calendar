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

static Calendar calendar = Calendar.getInstance();


    public static String[] initStrArr(String[] targetArray){
        for (String x: targetArray) x = "";
        return targetArray;
    }


    public static int[] initIntArr(int[] targetArray){
        for (int x: targetArray) x = 0;
        return targetArray;
    }


    public static String[] formatTitle(int month, int year){
        int lenMonthName =  0;
        int lenYear = 0;

        initStrArr(titleLine);

        switch (month) {
            case 1:
                lenMonthName = MONTH_1.length();
            case 2:
                lenMonthName = MONTH_2.length();
            case 3:
                lenMonthName = MONTH_3.length();
            case 4:
                lenMonthName = MONTH_4.length();
            case 5:
                lenMonthName = MONTH_5.length();
            case 6:
                lenMonthName = MONTH_6.length();
            case 7:
                lenMonthName = MONTH_7.length();
            case 8:
                lenMonthName = MONTH_8.length();
            case 9:
                lenMonthName = MONTH_9.length();
            case 10:
                lenMonthName = MONTH_10.length();
            case 11:
                lenMonthName = MONTH_11.length();
            case 12:
                lenMonthName = MONTH_12.length();


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
            switch (Main.argCnt) {  // 大文字の変数はCalenderクラスのフィールド。
                case 0 :
                    break;
                case 1 :
                    calendar.set(Calendar.MONTH, Main.month);
                case 2 :
                    calendar.set(Calendar.MONTH, Main.month);
                    calendar.set(Calendar.YEAR, Main.year);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("エラーが発生しました。エラーコード: E2");
        }
        actualMaxValForMonth = calendar.getActualMaximum(Calendar.MONTH); // monthが取りえる最大値を取得。すでにmonthをCalender.MONTHに設定しているため引数としている。


    }


}