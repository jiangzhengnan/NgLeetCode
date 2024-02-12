package com.ng.code.designmodel.行为型.解释器模式;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author : 
 * @creation : 2022/10/06
 * @description :
 * 概念:
 * 解释器模式为某个语言定义它的语法（或者叫文法）表示，并定义一个解释器用来处理这个语法。
 * 它的代码实现的核心思想，就是将语法解析的工作拆分到各个小类中，以此来避免大而全的解析类。
 * 示例：
 * 比如“ 8 3 2 4 - + * ”这样一个表达式，我们按照上面的语法规则来处理，取出数字“8 3”和“-”运算符，计算得到5，
 * 于是表达式就变成了“ 5 2 4 + * ”。然后，我们再取出“ 5 2 ”和“ + ”运算符，计算得到7，
 * 表达式就变成了“ 7 4 * ”。最后，我们取出“ 7 4”和“ * ”运算符，最终得到的结果就是28。
 * 在这里就是把数字与表达式计算解藕成单独的子类
 */
public class 解释器模式 {

    public interface Expression {
        long interpret();
    }

    static class NumberExpression implements Expression {
        private long number;

        public NumberExpression(long number) {
            this.number = number;
        }

        public NumberExpression(String number) {
            this.number = Long.parseLong(number);
        }

        @Override
        public long interpret() {
            return this.number;
        }
    }

    static class AdditionExpression implements Expression {
        private Expression exp1;
        private Expression exp2;

        public AdditionExpression(Expression exp1, Expression exp2) {
            this.exp1 = exp1;
            this.exp2 = exp2;
        }

        @Override
        public long interpret() {
            return exp1.interpret() + exp2.interpret();
        }
    }
    // SubstractionExpression/MultiplicationExpression/DivisionExpression与AdditionExpression代码结构类似，这里就省略了

    static class ExpressionInterpreter {
        private Deque<Expression> numbers = new LinkedList<>();

        public long interpret(String expression) {
            String[] elements = expression.split(" ");
            int length = elements.length;
            for (int i = 0; i < (length + 1) / 2; ++i) {
                numbers.addLast(new NumberExpression(elements[i]));
            }
            for (int i = (length + 1) / 2; i < length; ++i) {
                String operator = elements[i];
                boolean isValid = "+".equals(operator) || "-".equals(operator)
                                  || "*".equals(operator) || "/".equals(operator);
                if (!isValid) {
                    throw new RuntimeException("Expression is invalid: " + expression);
                }

                Expression exp1 = numbers.pollFirst();
                Expression exp2 = numbers.pollFirst();
                Expression combinedExp = null;
                if (operator.equals("+")) {
                    combinedExp = new AdditionExpression(exp1, exp2);
                } else if (operator.equals("-")) {
                    combinedExp = new AdditionExpression(exp1, exp2);
                } else if (operator.equals("*")) {
                    combinedExp = new AdditionExpression(exp1, exp2);
                } else if (operator.equals("/")) {
                    combinedExp = new AdditionExpression(exp1, exp2);
                }
                long result = combinedExp.interpret();
                numbers.addFirst(new NumberExpression(result));
            }

            if (numbers.size() != 1) {
                throw new RuntimeException("Expression is invalid: " + expression);
            }

            return numbers.pop().interpret();
        }
    }

    public static void main(String[] args) {
        ExpressionInterpreter interpreter = new ExpressionInterpreter();
        System.out.println(interpreter.interpret("8 3 2 4 - + *") + "");
    }

}
