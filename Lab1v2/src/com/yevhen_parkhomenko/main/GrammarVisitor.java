// Generated from C:/Users/godbl/Desktop/Lab1/src/com/yevhen_parkhomenko/grammar\Grammar.g4 by ANTLR 4.9.1
package com.yevhen_parkhomenko.main;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;


public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {

	T visitMultiplicativeExpr(GrammarParser.MultiplicativeExprContext ctx);

	T visitExponentialExpr(GrammarParser.ExponentialExprContext ctx);

	T visitAdditiveExpr(GrammarParser.AdditiveExprContext ctx);

	T visitNumberExpr(GrammarParser.NumberExprContext ctx);

	T visitParenthesizedExpr(GrammarParser.ParenthesizedExprContext ctx);

	T visitMMinMMaxExpr(GrammarParser.MMinMMaxExprContext ctx);

	T visitUnaryPositiveNegativeExpr(GrammarParser.UnaryPositiveNegativeExprContext ctx);
}