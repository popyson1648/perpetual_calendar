import Main; // ?

public class FormatCalender {


/*
    October 2000       // titleLine[]
Su Mo Tu We Th Fr Sa   // weekOfDayLine[]
 1  2  3  4  5  6  7   // monthOfDayLine1[]
 8  9 10 11 12 13 14   // monthOfDayLine2[]
15 16 17 18 19 20 21   // monthOfDayLine3[]  
22 23 24 25 26 27 28   // monthOfDayLine4[]
29 30 31               // monthOfDayLine5[]
*/

final static String MONTH_1  = "Junuary"; 
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

static String[] titleLine     = new String[20];
static String[] weekOfDayLine = new String[20];
static int[] monthOfDayLine1 = new int[20];
static int[] monthOfDayLine2 = new int[20];
static int[] monthOfDayLine3 = new int[20];
static int[] monthOfDayLine4 = new int[20];
static int[] monthOfDayLine5 = new int[20];



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
    
    public static String[] formatWeekOfDay(void){
        
    }
    
    public static String[] formatMonthDay(int lastDayOfTheMonthType){
        
    }
    
    
}