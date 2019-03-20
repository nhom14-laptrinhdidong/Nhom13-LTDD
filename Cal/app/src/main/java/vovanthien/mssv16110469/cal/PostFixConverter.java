package vovanthien.mssv16110469.cal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.lang.String;
// Chuyển biểu thức từ trung tố sang hậu tố - Infix to Postfix

public class PostFixConverter {
    private String infix; // The infix expression to be converted
    private Deque<Character> stack = new ArrayDeque<Character>();
    private List<String> postfix = new ArrayList<String>(); // To hold the postfix expression


    public PostFixConverter(String expression)
    {
        //Kiểm tra kí tự đầu có phải dấu '-'
        int index = expression.charAt(0);
        if(index==45){
            expression = '0' +expression;
        }
        infix = expression;
        convertExpression();
    }

    /* Nếu là số thì đẩy bào chuỗi postfix, nếu là toán tử thì đẩy vào stack*/
    private void convertExpression()
    {
        // Temporary string to hold the number
        StringBuilder temp = new StringBuilder();

        for(int i = 0; i != infix.length(); ++i)
        {
            if(Character.isDigit(infix.charAt(i)))
            {
                //Nếu gặp 1 chữ số, kiểm tra tiếp tục kí tự sau nó, nếu là số thì đẩy vào temp.
                // Cho tới khi gặp toán tử
                temp.append(infix.charAt(i));

                while((i+1) != infix.length() && (Character.isDigit(infix.charAt(i+1))
                        || infix.charAt(i+1) == '.'))
                {
                    temp.append(infix.charAt(++i));
                }


                    //Vòng lặp kết thúc = gặp toán tử
                    // lưu temp vào postfix và xóa temp để sử dụng lần sau
                postfix.add(temp.toString());
                temp.delete(0, temp.length());
            }

            else
                inputToStack(infix.charAt(i));
        }
        clearStack();
    }


    private void inputToStack(char input)
    {
        if(stack.isEmpty() || input == '(')
            stack.addLast(input);
        else
        {
            if(input == ')')
            {
                while(!stack.getLast().equals('('))
                {
                    postfix.add(stack.removeLast().toString());
                }
                stack.removeLast();
            }
            else
            {
                if(stack.getLast().equals('('))
                    stack.addLast(input);
                else
                {
                    while(!stack.isEmpty() && !stack.getLast().equals('(') &&
                            getPrecedence(input) <= getPrecedence(stack.getLast()))
                    {
                        postfix.add(stack.removeLast().toString());
                    }
                    stack.addLast(input);
                }
            }
        }
    }


    private int getPrecedence(char op)
    {
        if (op == '+' || op == '-')
            return 1;
        else if (op == '*' || op == '/')
            return 2;
        else if (op == '^' || op == '√')
            return 3;
        else return 0;
    }


    private void clearStack()
    {
        while(!stack.isEmpty())
        {
            postfix.add(stack.removeLast().toString());
        }
    }


    public void printExpression()
    {
        for(String str : postfix)
        {
            System.out.print(str + ' ');
        }
    }


    public List<String> getPostfixAsList()
    {
        return postfix;
    }
}
