package chap03.ast;

import com.github.javaparser.ast.ArrayBracketPair;
import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EmptyMemberDeclaration;
import com.github.javaparser.ast.body.EmptyTypeDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.VariableDeclaratorId;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.IntegerLiteralMinValueExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.LongLiteralMinValueExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.QualifiedNameExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.SuperExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.expr.TypeExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.imports.EmptyImportDeclaration;
import com.github.javaparser.ast.imports.ImportDeclaration;
import com.github.javaparser.ast.imports.SingleStaticImportDeclaration;
import com.github.javaparser.ast.imports.SingleTypeImportDeclaration;
import com.github.javaparser.ast.imports.StaticImportOnDemandDeclaration;
import com.github.javaparser.ast.imports.TypeImportOnDemandDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.LabeledStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntryStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.TypeDeclarationStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.IntersectionType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.UnionType;
import com.github.javaparser.ast.type.UnknownType;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.ast.visitor.VoidVisitor;
import java.util.Iterator;
import java.util.Optional;

import java.util.Iterator;

public abstract class TreeStructVisitor implements VoidVisitor<Integer> {
    public TreeStructVisitor() {
    }

    public abstract void out(Node var1, int var2);

    public void visit(AnnotationDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        n.getNameExpr().accept(this, arg + 1);
        if (n.getMembers() != null) {
            Iterator var4 = n.getMembers().iterator();

            while(var4.hasNext()) {
                BodyDeclaration<?> member = (BodyDeclaration)var4.next();
                member.accept(this, arg + 1);
            }
        }

    }

    public void visit(AnnotationMemberDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        n.getType().accept(this, arg + 1);
        n.getDefaultValue().ifPresent((d) -> {
            d.accept(this, arg + 1);
        });
    }

    public void visit(ArrayAccessExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getName().accept(this, arg + 1);
        n.getIndex().accept(this, arg + 1);
    }

    public void visit(ArrayCreationExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getElementType().accept(this, arg + 1);
        Iterator var4 = n.getLevels().iterator();

        while(var4.hasNext()) {
            ArrayCreationLevel level = (ArrayCreationLevel)var4.next();
            level.accept(this, arg + 1);
        }

        n.getInitializer().ifPresent((i) -> {
            i.accept(this, arg + 1);
        });
    }

    public void visit(ArrayInitializerExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        if (n.getValues() != null) {
            Iterator var4 = n.getValues().iterator();

            while(var4.hasNext()) {
                Expression expr = (Expression)var4.next();
                expr.accept(this, arg + 1);
            }
        }

    }

    public void visit(AssertStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getCheck().accept(this, arg + 1);
        n.getMessage().ifPresent((m) -> {
            m.accept(this, arg + 1);
        });
    }

    public void visit(AssignExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getTarget().accept(this, arg + 1);
        n.getValue().accept(this, arg + 1);
    }

    public void visit(BinaryExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getLeft().accept(this, arg + 1);
        n.getRight().accept(this, arg + 1);
    }

    public void visit(BlockComment n, Integer arg) {
        this.out(n, arg);
    }

    public void visit(BlockStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        if (n.getStmts() != null) {
            Iterator var4 = n.getStmts().iterator();

            while(var4.hasNext()) {
                Statement s = (Statement)var4.next();
                s.accept(this, arg + 1);
            }
        }

    }

    public void visit(BooleanLiteralExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(BreakStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(CastExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getType().accept(this, arg + 1);
        n.getExpr().accept(this, arg + 1);
    }

    public void visit(CatchClause n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getParam().accept(this, arg + 1);
        n.getBody().accept(this, arg + 1);
    }

    public void visit(CharLiteralExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(ClassExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getType().accept(this, arg + 1);
    }

    public void visit(ClassOrInterfaceDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        n.getNameExpr().accept(this, arg + 1);
        Iterator var4 = n.getTypeParameters().iterator();

        while(var4.hasNext()) {
            TypeParameter t = (TypeParameter)var4.next();
            t.accept(this, arg + 1);
        }

        var4 = n.getExtends().iterator();

        ClassOrInterfaceType c;
        while(var4.hasNext()) {
            c = (ClassOrInterfaceType)var4.next();
            c.accept(this, arg + 1);
        }

        var4 = n.getImplements().iterator();

        while(var4.hasNext()) {
            c = (ClassOrInterfaceType)var4.next();
            c.accept(this, arg + 1);
        }

        var4 = n.getMembers().iterator();

        while(var4.hasNext()) {
            BodyDeclaration<?> member = (BodyDeclaration)var4.next();
            member.accept(this, arg + 1);
        }

    }

    public void visit(ClassOrInterfaceType n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        n.getScope().ifPresent((s) -> {
            s.accept(this, arg + 1);
        });
        n.getTypeArguments().ifPresent((tas) -> {
            tas.forEach((ta) -> {
                ta.accept(this, arg + 1);
            });
        });
    }

    public void visit(CompilationUnit n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getPackage().ifPresent((p) -> {
            p.accept(this, arg + 1);
        });
        Iterator var4;
        if (n.getImports() != null) {
            var4 = n.getImports().iterator();

            while(var4.hasNext()) {
                ImportDeclaration i = (ImportDeclaration)var4.next();
                i.accept(this, arg + 1);
            }
        }

        if (n.getTypes() != null) {
            var4 = n.getTypes().iterator();

            while(var4.hasNext()) {
                TypeDeclaration<?> typeDeclaration = (TypeDeclaration)var4.next();
                typeDeclaration.accept(this, arg + 1);
            }
        }

    }

    public void visit(ConditionalExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getCondition().accept(this, arg + 1);
        n.getThenExpr().accept(this, arg + 1);
        n.getElseExpr().accept(this, arg + 1);
    }

    public void visit(ConstructorDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        Iterator var4;
        if (n.getTypeParameters() != null) {
            var4 = n.getTypeParameters().iterator();

            while(var4.hasNext()) {
                TypeParameter t = (TypeParameter)var4.next();
                t.accept(this, arg + 1);
            }
        }

        n.getNameExpr().accept(this, arg + 1);
        if (n.getParameters() != null) {
            var4 = n.getParameters().iterator();

            while(var4.hasNext()) {
                Parameter p = (Parameter)var4.next();
                p.accept(this, arg + 1);
            }
        }

        if (n.getThrows() != null) {
            var4 = n.getThrows().iterator();

            while(var4.hasNext()) {
                ReferenceType name = (ReferenceType)var4.next();
                name.accept(this, arg + 1);
            }
        }

        n.getBody().accept(this, arg + 1);
    }

    public void visit(ContinueStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(DoStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getBody().accept(this, arg + 1);
        n.getCondition().accept(this, arg + 1);
    }

    public void visit(DoubleLiteralExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(EmptyMemberDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(EmptyStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(EmptyTypeDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getNameExpr().accept(this, arg + 1);
    }

    public void visit(EnclosedExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getInner().ifPresent((i) -> {
            i.accept(this, arg + 1);
        });
    }

    public void visit(EnumConstantDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        Iterator var4;
        if (n.getArgs() != null) {
            var4 = n.getArgs().iterator();

            while(var4.hasNext()) {
                Expression e = (Expression)var4.next();
                e.accept(this, arg + 1);
            }
        }

        if (n.getClassBody() != null) {
            var4 = n.getClassBody().iterator();

            while(var4.hasNext()) {
                BodyDeclaration<?> member = (BodyDeclaration)var4.next();
                member.accept(this, arg + 1);
            }
        }

    }

    public void visit(EnumDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        n.getNameExpr().accept(this, arg + 1);
        Iterator var4;
        if (n.getImplements() != null) {
            var4 = n.getImplements().iterator();

            while(var4.hasNext()) {
                ClassOrInterfaceType c = (ClassOrInterfaceType)var4.next();
                c.accept(this, arg + 1);
            }
        }

        if (n.getEntries() != null) {
            var4 = n.getEntries().iterator();

            while(var4.hasNext()) {
                EnumConstantDeclaration e = (EnumConstantDeclaration)var4.next();
                e.accept(this, arg + 1);
            }
        }

        if (n.getMembers() != null) {
            var4 = n.getMembers().iterator();

            while(var4.hasNext()) {
                BodyDeclaration<?> member = (BodyDeclaration)var4.next();
                member.accept(this, arg + 1);
            }
        }

    }

    public void visit(ExplicitConstructorInvocationStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        if (!n.isThis() && n.getExpr().isPresent()) {
            ((Expression)n.getExpr().get()).accept(this, arg + 1);
        }

        n.getTypeArguments().ifPresent((tas) -> {
            tas.forEach((ta) -> {
                ta.accept(this, arg + 1);
            });
        });
        if (n.getArgs() != null) {
            Iterator var4 = n.getArgs().iterator();

            while(var4.hasNext()) {
                Expression e = (Expression)var4.next();
                e.accept(this, arg + 1);
            }
        }

    }

    public void visit(ExpressionStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getExpression().accept(this, arg + 1);
    }

    public void visit(FieldAccessExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getScope().accept(this, arg + 1);
        n.getFieldExpr().accept(this, arg + 1);
    }

    public void visit(FieldDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        n.getElementType().accept(this, arg + 1);
        Iterator var4 = n.getVariables().iterator();

        while(var4.hasNext()) {
            VariableDeclarator var = (VariableDeclarator)var4.next();
            var.accept(this, arg + 1);
        }

    }

    public void visit(ForeachStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getVariable().accept(this, arg + 1);
        n.getIterable().accept(this, arg + 1);
        n.getBody().accept(this, arg + 1);
    }

    public void visit(ForStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        Iterator var4 = n.getInit().iterator();

        Expression e;
        while(var4.hasNext()) {
            e = (Expression)var4.next();
            e.accept(this, arg + 1);
        }

        n.getCompare().ifPresent((c) -> {
            c.accept(this, arg + 1);
        });
        var4 = n.getUpdate().iterator();

        while(var4.hasNext()) {
            e = (Expression)var4.next();
            e.accept(this, arg + 1);
        }

        n.getBody().accept(this, arg + 1);
    }

    public void visit(IfStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getCondition().accept(this, arg + 1);
        n.getThenStmt().accept(this, arg + 1);
        n.getElseStmt().ifPresent((es) -> {
            es.accept(this, arg + 1);
        });
    }

    public void visit(InitializerDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getBlock().accept(this, arg + 1);
    }

    public void visit(InstanceOfExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getExpr().accept(this, arg + 1);
        n.getType().accept(this, arg + 1);
    }

    public void visit(IntegerLiteralExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(IntegerLiteralMinValueExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(JavadocComment n, Integer arg) {
        this.out(n, arg);
    }

    public void visit(LabeledStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getStmt().accept(this, arg + 1);
    }

    public void visit(LineComment n, Integer arg) {
        this.out(n, arg);
    }

    public void visit(LongLiteralExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(LongLiteralMinValueExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(MarkerAnnotationExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getName().accept(this, arg + 1);
    }

    public void visit(MemberValuePair n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getValue().accept(this, arg + 1);
    }

    public void visit(MethodCallExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getScope().ifPresent((s) -> {
            s.accept(this, arg + 1);
        });
        n.getTypeArguments().ifPresent((tas) -> {
            tas.forEach((ta) -> {
                ta.accept(this, arg + 1);
            });
        });
        n.getNameExpr().accept(this, arg + 1);
        if (n.getArgs() != null) {
            Iterator var4 = n.getArgs().iterator();

            while(var4.hasNext()) {
                Expression e = (Expression)var4.next();
                e.accept(this, arg + 1);
            }
        }

    }

    public void visit(MethodDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        Iterator var4;
        if (n.getTypeParameters() != null) {
            var4 = n.getTypeParameters().iterator();

            while(var4.hasNext()) {
                TypeParameter t = (TypeParameter)var4.next();
                t.accept(this, arg + 1);
            }
        }

        n.getElementType().accept(this, arg + 1);
        n.getNameExpr().accept(this, arg + 1);
        if (n.getParameters() != null) {
            var4 = n.getParameters().iterator();

            while(var4.hasNext()) {
                Parameter p = (Parameter)var4.next();
                p.accept(this, arg + 1);
            }
        }

        if (n.getThrows() != null) {
            var4 = n.getThrows().iterator();

            while(var4.hasNext()) {
                ReferenceType name = (ReferenceType)var4.next();
                name.accept(this, arg + 1);
            }
        }

        n.getBody().ifPresent((b) -> {
            b.accept(this, arg + 1);
        });
    }

    public void visit(NameExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(NormalAnnotationExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getName().accept(this, arg + 1);
        if (n.getPairs() != null) {
            Iterator var4 = n.getPairs().iterator();

            while(var4.hasNext()) {
                MemberValuePair m = (MemberValuePair)var4.next();
                m.accept(this, arg + 1);
            }
        }

    }

    public void visit(NullLiteralExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(ObjectCreationExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getScope().ifPresent((s) -> {
            s.accept(this, arg + 1);
        });
        n.getTypeArguments().ifPresent((tas) -> {
            tas.forEach((ta) -> {
                ta.accept(this, arg + 1);
            });
        });
        n.getType().accept(this, arg + 1);
        if (n.getArgs() != null) {
            Iterator var4 = n.getArgs().iterator();

            while(var4.hasNext()) {
                Expression e = (Expression)var4.next();
                e.accept(this, arg + 1);
            }
        }

        n.getAnonymousClassBody().ifPresent((acb) -> {
            acb.forEach((m) -> {
                m.accept(this, arg + 1);
            });
        });
    }

    public void visit(PackageDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        n.getName().accept(this, arg + 1);
    }

    public void visit(Parameter n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        n.getElementType().accept(this, arg + 1);
        n.getId().accept(this, arg + 1);
    }

    public void visit(PrimitiveType n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
    }

    public void visit(QualifiedNameExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getQualifier().accept(this, arg + 1);
    }

    public void visit(ArrayType n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        n.getComponentType().accept(this, arg + 1);
    }

    public void visit(ArrayCreationLevel n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        n.getDimension().ifPresent((d) -> {
            d.accept(this, arg + 1);
        });
    }

    public void visit(IntersectionType n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        Iterator var4 = n.getElements().iterator();

        while(var4.hasNext()) {
            ReferenceType element = (ReferenceType)var4.next();
            element.accept(this, arg + 1);
        }

    }

    public void visit(UnionType n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        Iterator var4 = n.getElements().iterator();

        while(var4.hasNext()) {
            ReferenceType element = (ReferenceType)var4.next();
            element.accept(this, arg + 1);
        }

    }

    public void visit(ReturnStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getExpr().ifPresent((e) -> {
            e.accept(this, arg + 1);
        });
    }

    public void visit(SingleMemberAnnotationExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getName().accept(this, arg + 1);
        n.getMemberValue().accept(this, arg + 1);
    }

    public void visit(StringLiteralExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(SuperExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getClassExpr().ifPresent((ce) -> {
            ce.accept(this, arg + 1);
        });
    }

    public void visit(SwitchEntryStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getLabel().ifPresent((l) -> {
            l.accept(this, arg + 1);
        });
        if (n.getStmts() != null) {
            Iterator var4 = n.getStmts().iterator();

            while(var4.hasNext()) {
                Statement s = (Statement)var4.next();
                s.accept(this, arg + 1);
            }
        }

    }

    public void visit(SwitchStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getSelector().accept(this, arg + 1);
        if (n.getEntries() != null) {
            Iterator var4 = n.getEntries().iterator();

            while(var4.hasNext()) {
                SwitchEntryStmt e = (SwitchEntryStmt)var4.next();
                e.accept(this, arg + 1);
            }
        }

    }

    public void visit(SynchronizedStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getExpr().accept(this, arg + 1);
        n.getBody().accept(this, arg + 1);
    }

    public void visit(ThisExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getClassExpr().ifPresent((ce) -> {
            ce.accept(this, arg + 1);
        });
    }

    public void visit(ThrowStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getExpr().accept(this, arg + 1);
    }

    public void visit(TryStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        Iterator var4 = n.getResources().iterator();

        while(var4.hasNext()) {
            VariableDeclarationExpr v = (VariableDeclarationExpr)var4.next();
            v.accept(this, arg + 1);
        }

        n.getTryBlock().accept(this, arg + 1);
        if (n.getCatchs() != null) {
            var4 = n.getCatchs().iterator();

            while(var4.hasNext()) {
                CatchClause c = (CatchClause)var4.next();
                c.accept(this, arg + 1);
            }
        }

        n.getFinallyBlock().ifPresent((f) -> {
            f.accept(this, arg + 1);
        });
    }

    public void visit(TypeDeclarationStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getTypeDeclaration().accept(this, arg + 1);
    }

    public void visit(TypeParameter n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        if (n.getTypeBound() != null) {
            Iterator var4 = n.getTypeBound().iterator();

            while(var4.hasNext()) {
                ClassOrInterfaceType c = (ClassOrInterfaceType)var4.next();
                c.accept(this, arg + 1);
            }
        }

    }

    public void visit(UnaryExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getExpr().accept(this, arg + 1);
    }

    public void visit(UnknownType n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(VariableDeclarationExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        n.getElementType().accept(this, arg + 1);
        Iterator var4 = n.getVariables().iterator();

        while(var4.hasNext()) {
            VariableDeclarator v = (VariableDeclarator)var4.next();
            v.accept(this, arg + 1);
        }

    }

    public void visit(VariableDeclarator n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getId().accept(this, arg + 1);
        n.getInit().ifPresent((i) -> {
            i.accept(this, arg + 1);
        });
    }

    public void visit(VariableDeclaratorId n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(VoidType n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
    }

    public void visit(WhileStmt n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getCondition().accept(this, arg + 1);
        n.getBody().accept(this, arg + 1);
    }

    public void visit(WildcardType n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        this.visitAnnotations(n, arg + 1);
        n.getExtends().ifPresent((e) -> {
            e.accept(this, arg + 1);
        });
        n.getSuper().ifPresent((s) -> {
            s.accept(this, arg + 1);
        });
    }

    public void visit(LambdaExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        if (n.getParameters() != null) {
            Iterator var4 = n.getParameters().iterator();

            while(var4.hasNext()) {
                Parameter a = (Parameter)var4.next();
                a.accept(this, arg + 1);
            }
        }

        if (n.getBody() != null) {
            n.getBody().accept(this, arg + 1);
        }

    }

    public void visit(MethodReferenceExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getTypeArguments().ifPresent((tas) -> {
            tas.forEach((ta) -> {
                ta.accept(this, arg + 1);
            });
        });
        if (n.getScope() != null) {
            n.getScope().accept(this, arg + 1);
        }

    }

    public void visit(TypeExpr n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        if (n.getType() != null) {
            n.getType().accept(this, arg + 1);
        }

    }

    public void visit(ArrayBracketPair n, Integer arg) {
        this.out(n, arg);
        this.visitAnnotations(n, arg + 1);
    }

    public void visit(NodeList n, Integer arg) {
        this.out(n, arg);
        Iterator var4 = n.iterator();

        while(var4.hasNext()) {
            Object node = var4.next();
            ((Node)node).accept(this, arg + 1);
        }

    }

    public void visit(EmptyImportDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
    }

    public void visit(SingleStaticImportDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getType().accept(this, arg + 1);
    }

    public void visit(SingleTypeImportDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getType().accept(this, arg + 1);
    }

    public void visit(StaticImportOnDemandDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getType().accept(this, arg + 1);
    }

    public void visit(TypeImportOnDemandDeclaration n, Integer arg) {
        this.out(n, arg);
        this.visitComment(n.getComment(), arg + 1);
        n.getName().accept(this, arg + 1);
    }

    private void visitComment(Optional<? extends Comment> n, Integer arg) {
        n.ifPresent((c) -> {
            this.out(c, arg);
        });
        n.ifPresent((c) -> {
            c.accept(this, arg + 1);
        });
    }

    private void visitAnnotations(NodeWithAnnotations<?> n, Integer arg) {
        this.out((Node)n, arg);
        Iterator var4 = n.getAnnotations().iterator();

        while(var4.hasNext()) {
            AnnotationExpr annotation = (AnnotationExpr)var4.next();
            annotation.accept(this, arg + 1);
        }

    }
}