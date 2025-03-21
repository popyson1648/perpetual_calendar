public class FormatCalendar {
    /*
        October 2000       // titleLine[]
    Su Mo Tu We Th Fr Sa   // weekOfDayLine[]
     1  2  3  4  5  6  7   // monthOfDayLine1[]
     8  9 10 11 12 13 14   // monthOfDayLine2[]
    15 16 17 18 19 20 21   // monthOfDayLine3[]
    22 23 24 25 26 27 28   // monthOfDayLine4[]
    29 30 31               // monthOfDayLine5[]
                           // monthOfDayLine6[]
    */
    final static String MONTH_1 = "January";
    final static String MONTH_2 = "February";
    final static String MONTH_3 = "March";
    final static String MONTH_4 = "April";
    final static String MONTH_5 = "May";
    final static String MONTH_6 = "June";
    final static String MONTH_7 = "July";
    final static String MONTH_8 = "August";
    final static String MONTH_9 = "September";
    final static String MONTH_10 = "October";
    final static String MONTH_11 = "November";
    final static String MONTH_12 = "December";

    final static String[] WEEK_OF_DAY_LINE = { "S", "u", " ", "M", "o", " ", "T", "u", " ", "W", "e", " ", "T", "h", " ", "F", "r",
            " ", "S", "a" };

    final static String[] DAYS_STR = { " ", "1", " ", "2", " ", "3", " ", "4", " ", "5", " ", "6", " ", "7", " ", "8",
            " ", "9", "1", "0", "1", "1", "1", "2", "1", "3", "1", "4", "1", "5", "1", "6", "1", "7", "1", "8", "1",
            "9", "2", "0", "2", "1", "2", "2", "2", "3", "2", "4", "2", "5", "2", "6", "2", "7", "2", "8", "2", "9",
            "3", "0", "3", "1" };
    final static String[] ARRANGEMENT_TYPE = {"num", "num", "dblank", "num", "num", "dblank", "num", "num", "dblank", "num", "num", "dblank", "num", "num", "dblank", "num", "num", "dblank", "num", "num"};

    final static int LINE_LENGTH = 20;

    static String[] titleLine = new String[20];
    static String[] dayOfMonthLine1 = new String[20];
    static String[] dayOfMonthLine2 = new String[20];
    static String[] dayOfMonthLine3 = new String[20];
    static String[] dayOfMonthLine4 = new String[20];
    static String[] dayOfMonthLine5 = new String[20];
    static String[] dayOfMonthLine6 = new String[20];


    public static void formatTitleLine(int month, int year, int numOfDigitYear) {

        String monthName = "";
        String extractingMonthStr;
        int monthNameLen = 0;
        int workMonthNameLen = 0;

        String yearStr;
        String extractingYearStr;

        int lineMidPosition = LINE_LENGTH / 2;
        int lenLineTop;
        int lenLineBottom;

        int delimitedBlankPositionType = 0;
        int delimitedBlankPosition = 0;
        int lineTopBlankCnt;
        int lineBottomBlankCnt;
        int workLineTopBlankCnt;
        int workLineBottomBlankCnt;

        int arrayIndexCnt = 0;

        boolean isTopBlankInsertionCpl = false;
        boolean isBottomBlankInsertionCpl = false;
        boolean isDelimitedBlankInsertionCpl = false;
        boolean isMonthNameInsertionComplete = false;
        boolean isYearNumInsertionCpl = false;


        // month の名前と、名前の長さを変数に設定。
        switch (month) {
            case 1 -> {
                monthName = MONTH_1;
                monthNameLen = MONTH_1.length();
                workMonthNameLen = monthNameLen;
            }
            case 2 -> {
                monthName = MONTH_2;
                monthNameLen = MONTH_2.length();
                workMonthNameLen = monthNameLen;
            }
            case 3 -> {
                monthName = MONTH_3;
                monthNameLen = MONTH_3.length();
                workMonthNameLen = monthNameLen;
            }
            case 4 -> {
                monthName = MONTH_4;
                monthNameLen = MONTH_4.length();
                workMonthNameLen = monthNameLen;
            }
            case 5 -> {
                monthName = MONTH_5;
                monthNameLen = MONTH_5.length();
                workMonthNameLen = monthNameLen;
            }
            case 6 -> {
                monthName = MONTH_6;
                monthNameLen = MONTH_6.length();
                workMonthNameLen = monthNameLen;
            }
            case 7 -> {
                monthName = MONTH_7;
                monthNameLen = MONTH_7.length();
                workMonthNameLen = monthNameLen;
            }
            case 8 -> {
                monthName = MONTH_8;
                monthNameLen = MONTH_8.length();
                workMonthNameLen = monthNameLen;
            }
            case 9 -> {
                monthName = MONTH_9;
                monthNameLen = MONTH_9.length();
                workMonthNameLen = monthNameLen;
            }
            case 10 -> {
                monthName = MONTH_10;
                monthNameLen = MONTH_10.length();
                workMonthNameLen = monthNameLen;
            }
            case 11 -> {
                monthName = MONTH_11;
                monthNameLen = MONTH_11.length();
                workMonthNameLen = monthNameLen;
            }
            case 12 -> {
                monthName = MONTH_12;
                monthNameLen = MONTH_12.length();
                workMonthNameLen = monthNameLen;
            }
        }

        // monthの長さから区切りブランク文字の挿入位置タイプを判定
        switch (monthNameLen) {
            case 3 ->       delimitedBlankPositionType = 4;
            case 4, 6, 7 -> delimitedBlankPositionType = 2;
            case 5 ->       delimitedBlankPositionType = 3;
            case 8, 9 ->    delimitedBlankPositionType = 1;
        }

        // 区切りブランク文字の挿入位置を変数に設定
        switch (delimitedBlankPositionType) {
            case 1 -> delimitedBlankPosition = lineMidPosition + 3;
            case 2 -> delimitedBlankPosition = lineMidPosition + 2;
            case 3 -> delimitedBlankPosition = lineMidPosition + 1;
            case 4 -> delimitedBlankPosition = lineMidPosition - 1;
        }

        // titleLineのtopとbottomの長さを計算し変数に設定
        lenLineTop = delimitedBlankPosition - 1;
        lenLineBottom = LINE_LENGTH - delimitedBlankPosition;

        // titleLineのtopとbottomの「穴埋め用ブランク文字」の挿入数を計算し変数に設定
        lineTopBlankCnt = lenLineTop - monthNameLen;
        lineBottomBlankCnt = lenLineBottom - numOfDigitYear;
        workLineBottomBlankCnt = lineBottomBlankCnt;
        workLineTopBlankCnt = lineTopBlankCnt;

        // titleLine[] に 穴埋め用ブランク文字, 月名, 区切りブランク文字, 西暦年数, 穴埋め用ブランク文字 を挿入
        // トップの穴埋め用ブランク文字を挿入
        while (!(isTopBlankInsertionCpl)) {
            for (int i = arrayIndexCnt; i < workLineTopBlankCnt; i++) {
                titleLine[i] = " ";
                arrayIndexCnt += 1;
            }
            isTopBlankInsertionCpl = true;
        }
        // 月名
        while (!(isMonthNameInsertionComplete)) {
            // 初期値のみが異なり、条件、インクリメント量は同じであるようなfor処理を実装している
            int beginIdx = 0; //substring用。
            int i = arrayIndexCnt;
            int storedCnt = 0;
            while (storedCnt < workMonthNameLen && beginIdx < workMonthNameLen + 1) {
                extractingMonthStr = monthName.substring(beginIdx, beginIdx + 1);
                titleLine[i] = extractingMonthStr;
                storedCnt += 1;
                beginIdx++;
                i++;
                arrayIndexCnt++;
            }
            isMonthNameInsertionComplete = true;
        }
        // 区切りブランク文字
        while (!(isDelimitedBlankInsertionCpl)) {
            titleLine[arrayIndexCnt] = " ";
            arrayIndexCnt += 1;
            isDelimitedBlankInsertionCpl = true;
        }
        // 西暦
        while (!(isYearNumInsertionCpl)) {
            yearStr = String.valueOf(year); // int型yearをString型に変換
            int beginIdx = 0;
            int i = arrayIndexCnt;
            int storedCnt = 0;
            while (storedCnt < numOfDigitYear && beginIdx < numOfDigitYear + 1) {
                extractingYearStr = yearStr.substring(beginIdx, beginIdx + 1);
                titleLine[i] = extractingYearStr;
                storedCnt += 1;
                beginIdx++;
                i++;
                arrayIndexCnt += 1;
            }
            isYearNumInsertionCpl = true;
        }
        // ボトムの穴埋め用ブランク文字を挿入
        while (!(isBottomBlankInsertionCpl)) {
            for (int i=arrayIndexCnt; i<LINE_LENGTH; i++) {
                titleLine[i] = " ";
            }
            isBottomBlankInsertionCpl = true;
        }
    }

    public static void formatDayOfMonthLine(int endDayOfMonth, int dayOfWeekType) {
        
        /*
        . : Blank
        * : Delimited blank
        
            February 1990
        Su Mo Tu We Th Fr Sa
        ..*..*..*..*.1  2  3
         4  5  6  7  8  9 10
        11 12 13 14 15 16 17
        18 19 20 21 22 23 24
        25 26 27 28

        Su Mo Tu We Th Fr Sa
         1  2  3  4  5  6  7

         S   u   _   M   o   _   T   u   _   W   e   _   T   h   _   F   r   _   S   a
        [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
         0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19
        */

        int firstDayIdx = 0;  // dayOfMonthLine1の処理に必要な変数
        int nextDaysIdx = 0;


        // dayOfMonthLine1 と dayOfMonthLine5 の処理に必要な変数
        int blankStoreCnt; // 格納するブランク文字の数


        // dayOfMonthLine1 の処理
        // DayOfMonthLine1[] に 1 (1日目)を格納する場所(添え字)を決定し、変数に設定する。
        switch (dayOfWeekType) {
            case 1 : break;
            case 2 : firstDayIdx = 3;  break;
            case 3 : firstDayIdx = 6;  break;
            case 4 : firstDayIdx = 9;  break;
            case 5 : firstDayIdx = 12; break;
            case 6 : firstDayIdx = 15; break;
            case 7 : firstDayIdx = 18; break;
        }
        blankStoreCnt = firstDayIdx - ((firstDayIdx + 1) / 3);

        // DayOfMonthLine1[]への格納処理
        // はじめてブランク文字を挿入する処理を含むため、storesDayOfMonth()は使用しない。
        for (int i=0; i<LINE_LENGTH; i++) {

            // Stores date numbers and blank
             if (ARRANGEMENT_TYPE[i].equals("num")) {
                 // Store Days
                if (blankStoreCnt < 1) {
                    dayOfMonthLine1[i] = DAYS_STR[nextDaysIdx];
                    nextDaysIdx += 1;
                }
                // Store Blank
                if (blankStoreCnt > 0) {
                    dayOfMonthLine1[i] = " ";
                    blankStoreCnt -= 1;
                }
            }
            // Store Delimited blank
            if (ARRANGEMENT_TYPE[i].equals("dblank")) {  
                dayOfMonthLine1[i] = " ";
            }
        }
        nextDaysIdx = storesDayOfMonth(dayOfMonthLine2, nextDaysIdx);  // dayOfMonthLine2 の処理
        nextDaysIdx = storesDayOfMonth(dayOfMonthLine3, nextDaysIdx);  // dayOfMonthLine3 の処理
        nextDaysIdx = storesDayOfMonth(dayOfMonthLine4, nextDaysIdx);  // dayOfMonthLine4 の処理

        // dayOfMonthLine への DAYS_STR 格納処理の途中に、ブランク文字格納処理が発生するか
        if ( (nextDaysIdx + LINE_LENGTH) > endDayOfMonth){ //trueなら、処理対象は dayOfMonthLine5
            // dayOfMonthLine5 の処理
            storesDayOfMonthAndBlank(dayOfMonthLine5, nextDaysIdx, endDayOfMonth);
        }
        else {
            // dayOfMonthLine5 の処理
            nextDaysIdx = storesDayOfMonth(dayOfMonthLine5, nextDaysIdx);
        }
        // dayOfMonthLine6 の処理
        storesDayOfMonthAndBlank(dayOfMonthLine6, nextDaysIdx, endDayOfMonth);
    }

    // フォーマットされたカレンダーを出力する関数
    public static void printCalendar(String[] titleLine,
                                      String[] dayOfWeekLine,
                                      String[] dayOfMonthLine1,
                                      String[] dayOfMonthLine2,
                                      String[] dayOfMonthLine3,
                                      String[] dayOfMonthLine4,
                                      String[] dayOfMonthLine5,
                                      String[] dayOfMonthLine6) {
        System.out.println();

        for (int i=0; i<20; i++) { // print titleLine
            System.out.print(titleLine[i]);
        }
        System.out.println();

        for (int i=0; i<20; i++) { // print dayOfWeekLine
            System.out.print(dayOfWeekLine[i]);
        }
        System.out.println();

        for (int i=0; i<6; i++) { // print dayOfMonthLines
            switch (i) {
                case 0 -> {
                    for (int k=0; k<20; k++) System.out.print(dayOfMonthLine1[k]);
                }
                case 1 -> {
                    for (int k=0; k<20; k++) System.out.print(dayOfMonthLine2[k]);
                }
                case 2 -> {
                    for (int k=0; k<20; k++) System.out.print(dayOfMonthLine3[k]);
                }
                case 3 -> {
                    for (int k=0; k<20; k++) System.out.print(dayOfMonthLine4[k]);
                }
                case 4 -> {
                    for (int k=0; k<20; k++) System.out.print(dayOfMonthLine5[k]);
                }
                case 5 -> {
                    for (int k=0; k<20; k++) System.out.print(dayOfMonthLine6[k]);
                }
            }
        System.out.println();
        }
    }

    // 日付を格納する関数（ブランク文字格納あり）
    private static int storesDayOfMonth(String[] dayOfMonthLine, int nextDaysIdx) {
        for (int i=0; i<LINE_LENGTH; i++) {
            if (ARRANGEMENT_TYPE[i].equals("num")) {
                dayOfMonthLine[i] = DAYS_STR[nextDaysIdx];
                nextDaysIdx += 1;
            }
            if (ARRANGEMENT_TYPE[i].equals("dblank")) {
                dayOfMonthLine[i] = " ";
            }
        }
        return  nextDaysIdx;
    }

    // 日付を格納する関数（ブランク文字格納あり）
    private static void storesDayOfMonthAndBlank(String[] dayOfMonthLine, int nextDaysIdx, int endDayOfMonth) {
        for (int i=0; i<LINE_LENGTH; i++) {

            if (endDayOfMonth == (nextDaysIdx / 2)) { // DAYS_STR格納の終了の検知
                if (ARRANGEMENT_TYPE[i].equals("num")) {
                    dayOfMonthLine[i] = " ";
                }
            }
            else {
                if (ARRANGEMENT_TYPE[i].equals("num")) {
                    // dayOfMonthLine5 DAYS_STR処理
                    dayOfMonthLine[i] = DAYS_STR[nextDaysIdx];
                    nextDaysIdx += 1;
                }
            }
            if (ARRANGEMENT_TYPE[i].equals("dblank")) {
                dayOfMonthLine[i] = " ";
            }
        }
    }

}


// note ===============================================================================
/*
 1   2   3   4   5   6   7   8   9    10  11  12  13  14  15  16  17  18  19  20
 S   u   _   M   o   _   T   u   _    W    e   _   T   h   _   F   r   _   S   a
[ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [mid] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
                         J   u    n    e   _   1   9   9   0
      June 1990
Su Mo Tu We Th Fr Sa
                1  2
 3  4  5  6  7  8  9
10 11 12 13 14 15 16
17 18 19 20 21 22 23
24 25 26 27 28 29 30
*/

