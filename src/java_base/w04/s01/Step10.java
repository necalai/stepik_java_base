package java_base.w04.s01;

public class Step10 {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        // System.out.println("--");
        anotherMethod();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    public static String getCallerClassAndMethodName() {
        if (new Exception().getStackTrace().length < 3) {
            return null;
        } else {
            StackTraceElement element = new Exception().getStackTrace()[2];
            return element.getClassName() + "#" + element.getMethodName();
        }
    }
}
