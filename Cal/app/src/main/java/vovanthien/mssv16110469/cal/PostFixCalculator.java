package vovanthien.mssv16110469.cal;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public class PostFixCalculator {
    private List<String> expression = new ArrayList<String>();
    private Deque<Double> stack = new ArrayDeque<Double>();

    public PostFixCalculator(List<String> postfix) {expression = postfix;}

    public double resultSquared(double number, double sqrNumber){
        double result = 1;
        for(int i = 0 ; i < sqrNumber; i++){
            result = result*number;
        }
        return result;
    }

    public double resultSqrt(double number){
        return Math.sqrt(number);
    }

    public BigDecimal result()
    {
        for(int i = 0; i != expression.size(); ++i)
        {
            // Determine if current element is digit or not
            if(Character.isDigit(expression.get(i).charAt(0)))
            {
                stack.addLast(Double.parseDouble(expression.get(i)));
            }
            else
            {
                double tempResult = 0;
                double temp;

                switch(expression.get(i))
                {
                    case "√": temp = stack.removeLast();
                        tempResult = resultSqrt(temp);
                        break;
                    case "^": temp = stack.removeLast();
                         tempResult = resultSquared(stack.removeLast(),temp);
                        break;
                    case "+": temp = stack.removeLast();
                        tempResult = stack.removeLast() + temp;
                        break;

                    case "-": temp = stack.removeLast();
                        tempResult = stack.removeLast() - temp;
                        break;

                    case "*": temp = stack.removeLast();
                        tempResult = stack.removeLast() * temp;
                        break;

                    case "/": temp = stack.removeLast();
                        tempResult = stack.removeLast() / temp;
                        break;
                }
                stack.addLast(tempResult);
            }
        }
        return new BigDecimal(stack.removeLast()).setScale(3, BigDecimal.ROUND_HALF_UP);
    }
}
