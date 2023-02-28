package chap03.HW;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import chap03.TextIOPlus;
import chap03.ast.ASTPrinter;
//import lec2.Hello;


public class Ex2 {
    public static void main(String[] args) {
        String progText = TextIOPlus.readTextFileContent(Ex2.class,"Hello.j" );

        // parse the program text
        CompilationUnit codeUnit = JavaParser.parse(progText);
        // obtain the generated source code
        System.out.println(codeUnit);
    }

}
