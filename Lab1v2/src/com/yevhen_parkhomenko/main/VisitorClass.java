package com.yevhen_parkhomenko.main;

public class VisitorClass extends GrammarBaseVisitor<Double>{
    @Override
    public Double visitMultiplicativeExpr(GrammarParser.MultiplicativeExprContext ctx) {
        double left = super.visit(ctx.expression(0));
        double right = super.visit(ctx.expression(1));

        if (ctx.operatorToken.getType()==GrammarLexer.MULTIPLY)
            return left*right;
        else return left/right;
    }
    @Override
    public Double visitExponentialExpr(GrammarParser.ExponentialExprContext ctx) {
        double left = super.visit(ctx.expression(0));
        double right = super.visit(ctx.expression(1));

        return Math.pow(left, right);
    }
    @Override
    public Double visitAdditiveExpr(GrammarParser.AdditiveExprContext ctx) {
        double left = visit(ctx.expression(0));
        double right = visit(ctx.expression(1));

        if (ctx.operatorToken.getType()==GrammarLexer.ADD)
            return left+right;
        else return left-right;
    }
    @Override
    public Double visitNumberExpr(GrammarParser.NumberExprContext ctx) {
        return Double.parseDouble(ctx.getText());
    }
    @Override
    public Double visitParenthesizedExpr(GrammarParser.ParenthesizedExprContext ctx) {
        return visit(ctx.expression());
    }
    @Override
    public Double visitUnaryPositiveNegativeExpr(GrammarParser.UnaryPositiveNegativeExprContext ctx){
        double number = visit(ctx.expression());

        if (ctx.operatorToken.getType()==GrammarLexer.ADD)
            return number;
        else return -number;
    }
    @Override
    public Double visitMMinMMaxExpr(GrammarParser.MMinMMaxExprContext ctx){
        var expressions = ctx.expression();
        double temp = visit(ctx.expression(0));
        if(ctx.operatorToken.getType()==GrammarLexer.MMAX){
            for (var expr: expressions) {
                if(visit(expr)>temp) temp = visit(expr);
            }
        }
        else {
            for (var expr: expressions) {
                if(visit(expr)<temp) temp = visit(expr);
            }
        }
        return temp;
    }
}
