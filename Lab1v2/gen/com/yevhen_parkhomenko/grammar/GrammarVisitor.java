// Generated from C:/Users/godbl/Desktop/Lab1/src/com/yevhen_parkhomenko/grammar\Grammar.g4 by ANTLR 4.9.1
package main.yevhen_parkhomenko.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code MultiplicativeExpr}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpr(GrammarParser.MultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExponentialExpr}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExponentialExpr(GrammarParser.ExponentialExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditiveExpr}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(GrammarParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpr(GrammarParser.NumberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpr(GrammarParser.ParenthesizedExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MMinMMaxExpr}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMMinMMaxExpr(GrammarParser.MMinMMaxExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryPositiveNegativeExpr}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryPositiveNegativeExpr(GrammarParser.UnaryPositiveNegativeExprContext ctx);
}