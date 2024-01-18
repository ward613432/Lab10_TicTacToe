public class PrettyHeader {
    public static void printHeader(String msg) {
        printChar(60, "*", true);

        // calculate left/right spaces (odd messages will be 1 char off-center)
        int leftSpaces = (54 - msg.length()) / 2;
        int rightSpaces = 54 - leftSpaces - msg.length();

        // print rows
        printChar(3, "*", false);
        printChar(leftSpaces, " ", false);
        System.out.print(msg);
        printChar(rightSpaces, " ", false);
        printChar(3, "*", true);
        printChar(60, "*", true);
    }

    public static void printChar(int amount, String c, boolean breakAfter) {
        for (int i = 0; i < amount; i++) {
            System.out.print(c);
        }

        if (breakAfter) {
            System.out.println();
        }
    }
}