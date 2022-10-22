public class InputInspection {
    static int[] result = new int[3];
    public static int[] inspection(String srcCmdLine) {
        // 戻り地
        // inspectionResult: 0-> すべて問題なし, 1-> コマンドに問題あり, 2-> 引数monthに問題あり, 3-> 引数yearに問題あり, 4-> コマンドラインの入力形式が不正
        // argCnt: 引数の数
        // numOfDigitYear: yearの桁数

        final String CHECK_BLANK = " "; // コマンドラインの検査に用いるチェック用の空白
        final int MONTH_LIMIT_L = 1;
        final int MONTH_LIMIT_H = 12;
        final int YEAR_LIMIT_L = 1;
        final int YEAR_LIMIT_H = 9999;

        int cmdLineLen = 0;
        int blankCnt = 0;

        String srcCmd = null;

        String srcMonthStr = null;
        int    srcMonthInt  = 0;
        int    numOfDigitMonth = 0;

        String srcYearStr = null;
        int    srcYearInt  = 0;
        int    numOfDigitYear = 0;

        String extractedStr = null;

        int argCnt = 0;
        int inspectionResult = 0;


        srcCmdLine += CHECK_BLANK; // チェックに使用するブランクをコマンドライン末尾に追加

        // コマンドラインの長さが取り得る範囲か
        cmdLineLen = srcCmdLine.length();
        if (!(cmdLineLen > 3 && cmdLineLen < 13)) {
            inspectionResult = 4;
            storeResult(result, inspectionResult, argCnt, numOfDigitYear);
            return result;
        }

        // コマンドラインの空白の数が取り得る量の範囲か
        for (int i=0; i<cmdLineLen; i++) {
            extractedStr = srcCmdLine.substring(i, i+1);

            if (extractedStr.matches(" ")) {
                blankCnt += 1;
            }
        }
        if (!(blankCnt > 0 && blankCnt < 5)) {
            inspectionResult = 4;
            storeResult(result, inspectionResult, argCnt, numOfDigitYear);
            return result;
        }

        // Inspection Command
        srcCmd = srcCmdLine.substring(0, 3); // コマンドラインを分割

        if (!(srcCmd.matches("cal"))) {
            inspectionResult = 1;
            storeResult(result, inspectionResult, argCnt, numOfDigitYear);
            return result;
        }

        // 引数の間に空白がない場合を検知
        extractedStr = srcCmdLine.substring(3, 4);
        if (!(extractedStr.matches(" "))) {
            inspectionResult = 4;
            storeResult(result, inspectionResult, argCnt, numOfDigitYear);
            return result;
        }

        // cal_12_9876
        // Inspection Month
        srcMonthStr = srcCmdLine.substring(4, 6);// コマンドラインを分割

        // monthの桁を判別。ブランク文字を検出しなかった回数が桁数。引数の有無の確認も兼ねている。
        for (int i = 0; i < 2; i++) {
            extractedStr = srcMonthStr.substring(i, i + 1);
            if (!(extractedStr.matches(" "))) {
                numOfDigitMonth = numOfDigitMonth + 1;
            }
            if (numOfDigitMonth == 1) {
                argCnt = argCnt + 1;
            }
        }
        if (argCnt > 0) { // 引数が1未満の場合は第二引数も存在しない。

            switch (numOfDigitMonth) {
                case 1 -> srcMonthInt = Integer.parseInt(srcMonthStr); // 1桁目のみ抽出。
                case 2 -> srcMonthInt = Integer.parseInt(srcMonthStr); // 1桁目、2桁目を抽出。
            }
            if (!(srcMonthInt >= MONTH_LIMIT_L && srcMonthInt <= MONTH_LIMIT_H)) {
                inspectionResult = 2;
                storeResult(result, inspectionResult, argCnt, numOfDigitYear);
                return result;
            }

            // 引数の間に空白がない場合を検知
            if (numOfDigitMonth == 1) {
                extractedStr = srcCmdLine.substring(5, 6);
            }
            if (numOfDigitMonth == 2) {
                extractedStr = srcCmdLine.substring(6, 7);
            }
            if (!(extractedStr.matches(" "))) {
                inspectionResult = 4;
                storeResult(result, inspectionResult, argCnt, numOfDigitYear);
                return result;
            }

            // Inspection Year
            srcYearStr = srcCmdLine.substring(7, 11); // コマンドラインを分割

            for (int i = 0; i < 4; i++) {
                extractedStr = srcYearStr.substring(i, i + 1);
                if (!(extractedStr.matches(" "))) {
                    numOfDigitYear = numOfDigitYear + 1;
                }
                if (numOfDigitYear == 1) {
                    argCnt = argCnt + 1;
                }
            }
            switch (numOfDigitYear) {
                case 1 -> srcYearInt = Integer.parseInt(srcCmdLine.substring(7, 8));
                case 2 -> srcYearInt = Integer.parseInt(srcCmdLine.substring(7, 9));
                case 3 -> srcYearInt = Integer.parseInt(srcCmdLine.substring(7, 10));
                case 4 -> srcYearInt = Integer.parseInt(srcCmdLine.substring(7, 11));
            }
            if (!(srcYearInt >= YEAR_LIMIT_L && srcYearInt <= YEAR_LIMIT_H)) {
                inspectionResult = 3;
                storeResult(result, inspectionResult, argCnt, numOfDigitYear);
                return result;
            }
        }
        storeResult(result, inspectionResult, argCnt, numOfDigitYear);
        return result;
    }
    
    private static void storeResult(int[] result, int inspectionResult, int argCnt, int numOfDigitYear) {
        result[0] = inspectionResult;
        result[1] = argCnt;
        result[2] = numOfDigitYear;
    }
    
}