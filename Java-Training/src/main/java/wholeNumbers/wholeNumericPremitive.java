package wholeNumbers;

public class wholeNumericPremitive {
    public static void main(String[] args) {
        long max  = 12_123_145; // represent how we can write big numbers in Java

        // octal (0 - 7)
        int o1 = 012; // 10 in decimal
        int o2 = 045; // 37 in decimal
        int sumOct = o1 + o2; // 47 decimal
        System.out.println("firstDecimal= " + o1 + " secondDecimal= " + o2 + " octalSum= " + Integer.toOctalString(sumOct));

        // hexadecimal (0-9 and A-F)
        int h1 = 0xF; // 15 decimal
        int h2 = 0x1E; // 30 decimal
        int sumHex = h1 + h2; // 45 decimal
        System.out.println("firstDecimal= " + h1 + " secondDecimal= " + h2 + " hexSum= " + Integer.toHexString(sumHex));

        // binary
        int firstBin = 0b1001; // 9 decimal
        int secondBin = 0b0111; // 7 decimal
        int binSum = firstBin + secondBin; // 16 decimal
        System.out.println("firstDecimal= " + firstBin + " secondDecimal= " + secondBin + " BinSum= " + Integer.toBinaryString(binSum));
    }

}
