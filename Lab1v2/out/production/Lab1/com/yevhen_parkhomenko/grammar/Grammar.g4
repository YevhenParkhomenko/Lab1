grammar Grammar;

expression :
LPAREN expression RPAREN #ParenthesizedExpr
| operatorToken=(MMAX | MMIN) LPAREN expression (COMA expression)*RPAREN #MMinMMaxExpr
| operatorToken=(ADD | SUBTRACT) expression #UnaryPositiveNegativeExpr
| expression EXPONENT expression #ExponentialExpr
| expression operatorToken=(MULTIPLY | DIVIDE) expression #MultiplicativeExpr
| expression operatorToken=(ADD | SUBTRACT) expression #AdditiveExpr
| NUMBER #NumberExpr
;

NUMBER : INT ('.' INT)?;
INT : ('0'..'9')+;
COMA : ',';
EXPONENT : '^';
MULTIPLY : '*';
DIVIDE : '/';
SUBTRACT : '-';
ADD : '+';
LPAREN : '(';
RPAREN : ')';
MMAX: 'mmax';
MMIN: 'mmin';
WS : [ \t\r\n] -> channel(HIDDEN);