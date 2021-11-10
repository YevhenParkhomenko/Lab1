// Generated from C:/Users/godbl/Desktop/Lab1/src/com/yevhen_parkhomenko/grammar\Grammar.g4 by ANTLR 4.9.1
package com.yevhen_parkhomenko.main;
import org.antlr.v4.runtime.tree.ParseTreeListener;



public interface GrammarListener extends ParseTreeListener {

	void enterMultiplicativeExpr(GrammarParser.MultiplicativeExprContext ctx);

	void exitMultiplicativeExpr(GrammarParser.MultiplicativeExprContext ctx);

	void enterExponentialExpr(GrammarParser.ExponentialExprContext ctx);

	void exitExponentialExpr(GrammarParser.ExponentialExprContext ctx);

	void enterAdditiveExpr(GrammarParser.AdditiveExprContext ctx);

	void exitAdditiveExpr(GrammarParser.AdditiveExprContext ctx);

	void enterNumberExpr(GrammarParser.NumberExprContext ctx);

	void exitNumberExpr(GrammarParser.NumberExprContext ctx);

	void enterParenthesizedExpr(GrammarParser.ParenthesizedExprContext ctx);

	void exitParenthesizedExpr(GrammarParser.ParenthesizedExprContext ctx);

	void enterMMinMMaxExpr(GrammarParser.MMinMMaxExprContext ctx);

	void exitMMinMMaxExpr(GrammarParser.MMinMMaxExprContext ctx);

	void enterUnaryPositiveNegativeExpr(GrammarParser.UnaryPositiveNegativeExprContext ctx);

	void exitUnaryPositiveNegativeExpr(GrammarParser.UnaryPositiveNegativeExprContext ctx);
}