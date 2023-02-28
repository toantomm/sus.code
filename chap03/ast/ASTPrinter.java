package chap03.ast;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchEntryStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import java.io.InputStream;

public class ASTPrinter {
    private static final Class[] CompositeNodeClasses = new Class[]{CompilationUnit.class, ClassOrInterfaceDeclaration.class, MethodDeclaration.class, BlockStmt.class, IfStmt.class, WhileStmt.class, ForeachStmt.class, ForStmt.class, DoStmt.class, SwitchStmt.class, SwitchEntryStmt.class, TryStmt.class, CatchClause.class};

    public ASTPrinter() {
    }

    public static void print(String code) {
        CompilationUnit cu = JavaParser.parse(code);
        cu.accept(new TreeStructVisitor() {
            public void out(Node n, int indentLevel) {
                ASTPrinter.printNode(n, indentLevel);
            }
        }, 0);
    }

    public static void print(InputStream codeStream) {
        CompilationUnit cu = JavaParser.parse(codeStream);
        cu.accept(new TreeStructVisitor() {
            public void out(Node n, int indentLevel) {
                ASTPrinter.printNode(n, indentLevel);
            }
        }, 0);
    }

    protected static void printNode(Node n, int indentLevel) {
        Class nodeType = n.getClass();
        if (isComposite(n)) {
            System.out.println(indent("  ", indentLevel) + nodeType.getSimpleName());
        } else {
            String content = n.toStringWithoutComments();
            System.out.println(indent("  ", indentLevel) + nodeType.getSimpleName() + ": " + content);
        }

    }

    private static boolean isComposite(Node n) {
        Class nodeType = n.getClass();
        Class[] var5;
        int var4 = (var5 = CompositeNodeClasses).length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Class c = var5[var3];
            if (c.equals(nodeType)) {
                return true;
            }
        }

        return false;
    }

    private static String indent(String indentChar, int level) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < level; ++i) {
            sb.append(indentChar);
        }

        return sb.toString();
    }
}