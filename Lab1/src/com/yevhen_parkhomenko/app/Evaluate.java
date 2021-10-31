package com.yevhen_parkhomenko.app;

import com.yevhen_parkhomenko.main.GrammarLexer;
import com.yevhen_parkhomenko.main.GrammarParser;
import com.yevhen_parkhomenko.main.ThrowingErrorListener;
import com.yevhen_parkhomenko.main.VisitorClass;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashSet;

public class Evaluate {

    public static String atPointParser(String expression, HashSet<String> set, Object[][] otherSide) throws Exception {
        char ch;
        String temp = "";
        int i = 0;
        do{
            ch = expression.charAt(i);
            if (ch == '@') {
                String cell = "@" + expression.charAt(i + 1) + expression.charAt(i + 2);
                if (!set.contains(cell)) {
                    set.add(cell);
                    int row = Character.getNumericValue(expression.charAt(i + 2)) - 1;
                    int column = (expression.charAt(i + 1) - 'A');
                    String toEvaluate = atPointParser((String) otherSide[row][column], set, otherSide);
                    temp += evaluate(toEvaluate);
                    i += 3;
                    set.remove(cell);
                }
                else
                {
                    temp = "ERROR";
                    return temp;
                }
            }
            else
            {
                temp += ch;
                i++;
            }
        }
        while(i < expression.length());
        return temp;
    }


    public static double evaluate(String expression) {
        GrammarLexer lexer = new GrammarLexer(new ANTLRInputStream(expression));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new ThrowingErrorListener());
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokenStream);
        ParseTree tree = parser.expression();
        VisitorClass visitor = new VisitorClass();
        return visitor.visit(tree);
    }
}