public class InputInspection {
    static int[] result = new int[5];
    public static int[] inspection(String rowCmdLine) {
        // 戻り地
        // inspectionResult: 0-> すべて問題なし, 1-> コマンドに問題あり, 2-> 引数monthに問題あり, 3-> 引数yearに問題あり, 4-> コマンドラインの入力形式が不正
        // argCnt: 引数の数
        // month: monthをint型に変換した値
        // year: yearをint型に変換した値
        // numOfDigitYear: yearの桁数

        final String CHECK_BLANK = " "; // コマンドラインの検査に用いるチェック用の空白
        final int MONTH_LIMIT_L = 1;
        final int MONTH_LIMIT_H = 12;
        final int YEAR_LIMIT_L = 1;
        final int YEAR_LIMIT_H = 9999;

        String workRowCmdLine = null;
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
        int generalCntInt = 0; // カウント用汎用変数
        int generalLenInt = 0; // レングス用汎用関数
        int generalIdxInt = 0;

        int argCnt = -1;
        int inspectionResult = 0;


        rowCmdLine += CHECK_BLANK; // チェックに使用するブランクをコマンドライン末尾に追加

        // コマンドラインの長さが取り得る範囲か
        cmdLineLen = rowCmdLine.length();
        if (!(cmdLineLen > 3 && cmdLineLen < 13)) {
            inspectionResult = 4;
            storeResult(result, inspectionResult, argCnt, srcMonthInt, srcYearInt, numOfDigitYear);
            return result;
        }

        workRowCmdLine = rowCmdLine + "E"; // substringでStringIndexOutOfBoundsExceptionにならずにrwoCmdLineの最後尾の文字を抽出するために、文字列の終わりを示す文字"E"を末尾に付加する。

        // コマンドラインの空白の数が取り得る量の範囲か
        for (int i=0; i<cmdLineLen; i++) {
            extractedStr = workRowCmdLine.substring(i, i+1);
            if (extractedStr.matches(" ")) {
                blankCnt += 1;
            }
        }
        if (!(blankCnt > 0 && blankCnt < 5)) {
            inspectionResult = 4;
            storeResult(result, inspectionResult, argCnt, srcMonthInt, srcYearInt, numOfDigitYear);
            return result;
        }

        // Inspection Command
        srcCmd = rowCmdLine.substring(0, 3); // コマンドラインを分割

        if (!(srcCmd.matches("cal"))) {
            inspectionResult = 1;
            storeResult(result, inspectionResult, argCnt, srcMonthInt, srcYearInt, numOfDigitYear);
            return result;
        }

        // calのあとに空白が連続することはないことを利用して、空白だけの入力を検知
        for (int i=3; i<5; i++) {
            extractedStr = workRowCmdLine.substring(i, i+1);
            if (extractedStr.matches(" ")) {
                generalCntInt += 1;
            }
            if (generalCntInt == 2) {
                inspectionResult = 4;
                storeResult(result, inspectionResult, argCnt, srcMonthInt, srcYearInt, numOfDigitYear);
                return result;
            }
        }

        // 引数の数を取得する
        // switchでは、引数が0の場合と、1の場合のみを判定できる
        switch (cmdLineLen) {
            case 4 -> argCnt = 0; // calのみ
            case 6, 7 -> argCnt = 1; // monthのみ
            default -> argCnt = 2; // これ以上はすべてyearが含まれる
        }

        // Inspection Month
        if (argCnt >= 1) {

            // yearの桁数の判別と、yearに数字以外が入力されていないかを検知。
            // ブランク文字を検出しなかった回数が桁数。
            for (int i =4; i<6; i++) {
                extractedStr = workRowCmdLine.substring(i, i + 1);
                if (!(extractedStr.matches(" "))) {
                    numOfDigitMonth += 1;
                }
                if (extractedStr.matches(" ")) {
                    break;
                }
                try {
                    Integer.parseInt(extractedStr);
                } catch (NumberFormatException e) {
                    inspectionResult = 4;
                    storeResult(result, inspectionResult, argCnt, srcMonthInt, srcYearInt, numOfDigitYear);
                    return result;
                }
            }
            // monthの桁数に応じた取り出しと、int型への変換
            switch (numOfDigitMonth) {
                case 1 -> srcMonthInt = Integer.parseInt(workRowCmdLine.substring(4, 5)); // 1桁目のみ抽出。
                case 2 -> srcMonthInt = Integer.parseInt(workRowCmdLine.substring(4, 6)); // 1桁目、2桁目を抽出。
            }
            // monthが範囲内か
            if (!(srcMonthInt >= MONTH_LIMIT_L && srcMonthInt <= MONTH_LIMIT_H)) {
                inspectionResult = 2;
                storeResult(result, inspectionResult, argCnt, srcMonthInt, srcYearInt, numOfDigitYear);
                return result;
            }

            if (argCnt == 2) {
                // 引数の間に空白があるか検査
                if (numOfDigitMonth == 1) {
                    extractedStr = workRowCmdLine.substring(5, 6);
                }
                if (numOfDigitMonth == 2) {
                    extractedStr = workRowCmdLine.substring(6, 7);
                }
                if (!(extractedStr.matches(" "))) {
                    inspectionResult = 4;
                    storeResult(result, inspectionResult, argCnt, srcMonthInt, srcYearInt, numOfDigitYear);
                    return result;
                }

                // Inspection Year
                //yearが四桁入力されている場合のコマンドライン長を、monthの桁数に応じて求める
                switch (numOfDigitMonth) {
                    case 1 -> {
                        generalLenInt = 11;  // yearが四桁入力されている場合のコマンドライン長
                        generalIdxInt = 6;  // rowCmdLineのyear部分の開始位置
                    }
                    case 2 -> {
                        generalLenInt = 12;
                        generalIdxInt = 7;
                    }
                }

                // yearの桁数の判別と、yearに数字以外が入力されていないかを検知。
                // ブランク文字を検出しなかった回数が桁数。
                for (int i=generalIdxInt; i<generalLenInt; i++) {
                    extractedStr = workRowCmdLine.substring(i, i + 1);
                    if (!(extractedStr.matches(" "))) {
                        numOfDigitYear += 1;
                    }
                    if (extractedStr.matches(" ")) {
                        break;
                    }
                    try {
                        Integer.parseInt(extractedStr);
                    } catch (NumberFormatException e) {
                        inspectionResult = 4;
                        storeResult(result, inspectionResult, argCnt, srcMonthInt, srcYearInt, numOfDigitYear);
                        return result;
                    }
                }

                //コマンドラインからyearを桁数に応じて取り出し、Int型に変換。
                switch (numOfDigitMonth) {
                    case 1 -> {
                        switch (numOfDigitYear) {
                            case 1 -> srcYearInt = Integer.parseInt(rowCmdLine.substring(6, 7));
                            case 2 -> srcYearInt = Integer.parseInt(rowCmdLine.substring(6, 8));
                            case 3 -> srcYearInt = Integer.parseInt(rowCmdLine.substring(6, 9));
                            case 4 -> srcYearInt = Integer.parseInt(rowCmdLine.substring(6, 10));
                        }
                    }
                    case 2 -> {
                        switch (numOfDigitYear) {
                            case 1 -> srcYearInt = Integer.parseInt(rowCmdLine.substring(7, 8));
                            case 2 -> srcYearInt = Integer.parseInt(rowCmdLine.substring(7, 9));
                            case 3 -> srcYearInt = Integer.parseInt(rowCmdLine.substring(7, 10));
                            case 4 -> srcYearInt = Integer.parseInt(rowCmdLine.substring(7, 11));
                        }
                    }
                }
                // yearが範囲内か
                if (!(srcYearInt >= YEAR_LIMIT_L && srcYearInt <= YEAR_LIMIT_H)) {
                    inspectionResult = 3;
                    storeResult(result, inspectionResult, argCnt, srcMonthInt, srcYearInt, numOfDigitYear);
                    return result;
                }
            }
        }
        storeResult(result, inspectionResult, argCnt, srcMonthInt, srcYearInt, numOfDigitYear);
        return result;
    }
    
    private static void storeResult(int[] result, int inspectionResult, int argCnt, int month, int year, int numOfDigitYear) {
        result[0] = inspectionResult;
        result[1] = argCnt;
        result[2] = month;
        result[3] = year;
        result[4] = numOfDigitYear;
    }
    
}