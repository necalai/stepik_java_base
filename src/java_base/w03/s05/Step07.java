package java_base.w03.s05;

public class Step07 {
    public static void main(String[] args) {
        byte[] bytesArray = new byte[] {72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 32, 58, 41};
        AsciiCharSequence arr = new AsciiCharSequence(bytesArray);

        System.out.println(arr);
        System.out.println(arr.length());
        System.out.println(arr.charAt(4));
        System.out.println(arr.subSequence(0, 5));
    }
}
