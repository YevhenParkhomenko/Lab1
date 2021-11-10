package com.yevhen_parkhomenko.app;

import com.yevhen_parkhomenko.main.GrammarLexer;
import com.yevhen_parkhomenko.main.GrammarParser;
import com.yevhen_parkhomenko.main.ThrowingErrorListener;
import com.yevhen_parkhomenko.main.VisitorClass;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Arrays;
import java.util.HashSet;

public class Evaluate {

    public static String atPointParser(String expression, HashSet<String> set, Object[][] otherSide, int rows, int columns) throws Exception {
        char c;
        String temp = "";
        int i = 0;
        do {
            c = expression.charAt(i);
            if (c == '@') {
                String cell = "@" + expression.charAt(i + 1);
                String number = "";
                for (int j = i + 2; j < expression.length(); j++) {
                    if (Character.isDigit(expression.charAt(j))) {
                        number += expression.charAt(j);
                    } else break;
                }
                cell += number;
                if (!set.contains(cell)) {
                    set.add(cell);
                    int row = Integer.parseInt(number) - 1;
                    int column = (expression.charAt(i + 1) - 'A');
                    if (row+1 > rows || column+1 > columns){
                        temp = "ERROR";
                        return temp;
                    }
                    String toEvaluate = atPointParser((String) otherSide[row][column], set, otherSide, rows, columns);
                    temp += evaluate(toEvaluate);
                    i += cell.length();
                    set.remove(cell);
                } else {
                    temp = "ERROR";
                    return temp;
                }
            } else {
                temp += c;
                i++;
            }
        }
        while (i < expression.length());
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


