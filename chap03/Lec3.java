package chap03;

import chap03.ast.ASTPrinter;
import lec2.Hello;

public class Lec3 {
    public static void main(String[] args) {
        String progText = TextIOPlus.readTextFileContent(Hello.class, "TestClass");
        ASTPrinter.print(progText);
    }
}
