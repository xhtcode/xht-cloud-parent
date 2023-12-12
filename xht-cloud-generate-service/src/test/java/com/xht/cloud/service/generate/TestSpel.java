package com.xht.cloud.service.generate;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class TestSpel {
    private static final StandardEvaluationContext context = new StandardEvaluationContext();

    public static void main(String[] args) throws Exception {
        ExpressionParser parser = new SpelExpressionParser();
        Method[] methods = TestSpel.class.getMethods();
        Method parseInt = Arrays.stream(methods).filter(item -> item.getName().equals("stream")).findFirst().orElse(null);
        Expression expression = parser.parseExpression("#stream(#end)");
        context.setVariable("stream", parseInt);
        context.setVariable("end", new ArrayList<>());
        System.out.println(expression.getValue(context));
    }

    public static List<String> stream(List<String> lists) {
        System.out.println("------------------" + lists.size());
        lists.add("2");
        return lists;
    }
}
