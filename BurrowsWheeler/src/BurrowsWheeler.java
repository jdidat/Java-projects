public class BurrowsWheeler {
    public static class CSA {
        public int[] arrayOfIndexes;
        public int len;
        public CSA(String encodeString) {
            len = encodeString.length();
            arrayOfIndexes = new int[len];
            for (int i = 0; i < encodeString.length(); i++) {
                arrayOfIndexes[i] = i;
            }
            modifiedQuickSort(encodeString, 0, len - 1, 0);
        }
        public void modifiedQuickSort(String encodeString, int low, int high, int counterBits) {
            if (high - low <= 15) {
                for (int i = low; i <= high; i++) {
                    for (int j = i; j > low && lessThanCheck(encodeString, j, j - 1, counterBits); j--) {
                        int temp = arrayOfIndexes[j];
                        arrayOfIndexes[j] = arrayOfIndexes[j - 1];
                        arrayOfIndexes[j - 1] = temp;
                    }
                }
                return;
            }
            int lessThan = low;
            int greaterThan = high;
            int pivot = encodeString.charAt((arrayOfIndexes[low] + counterBits) % len);
            int equalTo = low + 1;
            while (equalTo <= greaterThan) {
                int currentChar = encodeString.charAt((arrayOfIndexes[equalTo] + counterBits) % len);
                if (currentChar < pivot) {
                    int lessThan2 = lessThan++;
                    int equalTo2 = equalTo++;
                    int temp = arrayOfIndexes[lessThan2];
                    arrayOfIndexes[lessThan2] = arrayOfIndexes[equalTo2];
                    arrayOfIndexes[equalTo2] = temp;
                }
                else if (currentChar > pivot) {
                    int greaterThan2 = greaterThan--;
                    int temp = arrayOfIndexes[equalTo];
                    arrayOfIndexes[equalTo] = arrayOfIndexes[greaterThan2];
                    arrayOfIndexes[greaterThan2] = temp;
                }
                else {
                    equalTo++;
                }
            }
            modifiedQuickSort(encodeString, low, lessThan - 1, counterBits);
            if (pivot >= 0) {
                modifiedQuickSort(encodeString, lessThan, greaterThan, counterBits + 1);
            }
            modifiedQuickSort(encodeString, greaterThan + 1, high, counterBits);
        }
        public boolean lessThanCheck(String encodeString, int i, int j, int counterBits) {
            int intofI = arrayOfIndexes[i];
            int intofJ = arrayOfIndexes[j];
            for (int k = counterBits; k < len; k++) {
                int charAtI = encodeString.charAt((intofI + k) % len);
                int charAtJ = encodeString.charAt((intofJ + k) % len);
                if (charAtI < charAtJ) {
                    return true;
                }
                else if (charAtI > charAtJ) {
                    return false;
                }
            }
            return false;
        }
    }
    public static void encode() {
        String encodeString = BinaryStdIn.readString();
        CSA a = new CSA(encodeString);
        int firstChar = 0;
        while (firstChar < a.len && a.arrayOfIndexes[firstChar] != 0) {
            firstChar++;
        }
        BinaryStdOut.write(firstChar);
        for (int i = 0; i < a.len; i++) {
            BinaryStdOut.write(encodeString.charAt((a.arrayOfIndexes[i] + encodeString.length() - 1) % encodeString.length()));
        }
        BinaryStdOut.close();
    }

    public static void decode() {
        int firstChar = BinaryStdIn.readInt();
        String decodeString = BinaryStdIn.readString();
        int length = decodeString.length();
        int[] ascii = new int[256 + 1];
        int[] stringDecode = new int[length];
        for (int i = 0; i < length; i++) {
            ascii[decodeString.charAt(i) + 1]++;
        }
        for (int j = 1; j < 256 + 1; j++) {
            ascii[j] += ascii[j - 1];
        }
        for (int k = 0; k < length; k++) {
            stringDecode[ascii[decodeString.charAt(k)]++] = k;
        }
        for (int l = stringDecode[firstChar], m = 0; m < length; l = stringDecode[l], m++) {
            BinaryStdOut.write(decodeString.charAt(l));
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        }
        else if (args[0].equals("+")) {
            decode();
        }
    }
}