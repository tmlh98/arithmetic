package xyz.tmlh.airthmetic.example.stack;

import java.util.ArrayList;
import java.util.List;

import xyz.tmlh.airthmetic.stack.ArrayStack;

/*
 * 栈实现计算器+ - * / ( )
 * 中缀表达式
 */
/*
 * 思路: 1)声明两个栈（数栈、符号栈） 
 *      2)自左向右扫描表达式，凡是遇到操作数一律进操作数栈。 
 *      3)当遇到运算符时，如果它的优先级比符号栈栈顶元素的优先级高就进栈。
 *      反之，取出栈顶运算符和数栈栈顶的两个连续操作数运算，并将结果存入数栈，然后继续比较该运算符与栈顶的运算符的优先级(递归)。
 *      4)左括号直接进符号栈，右括号一律不进符号栈， 取出栈顶运算符和操作数栈顶的两个连续操作数运算，并将结果存入数栈，直到取出左括号为止。
 */
public class Calculator {

    // 数栈
    private static ArrayStack<Integer> sumStack = new ArrayStack<Integer>();

    // 符号栈
    private static ArrayStack<Character> operStack = new ArrayStack<Character>();

    public static void main(String[] args) {
        String exStr = "3*2*(12-5)+10-(5+3)-4";
        List<String> exList = processStr(exStr.trim());
        for (String str : exList) {
            handlerExper(str);
        }

        while (true) {
            if (sumStack.size() == 1) {
                break;
            }
            cal(operStack.pop());
        }

        System.out.println(sumStack.pop());
    }

    // 依次进栈
    private static void handlerExper(String str) {
        if (str.matches("^[0-9]*$")) {
            // 操作数一律进操作数栈
            sumStack.push(Integer.valueOf(str));
        } else if ("(".equals(str)) {
            // 左括号一律进符号栈，右括号一律不进符号栈，
            operStack.push(str.charAt(0));
        } else if (")".equals(str)) {
            // 取出栈顶运算符和操作数栈顶的两个连续操作数运算，并将结果存入数栈，直到取出左括号为止。
            popOperation2(str.charAt(0));
        } else {
            // 符号
            popOperation(str.charAt(0));
        }
    }

    /*
     * 出栈操作
     * c : 待入栈的操作符
     */
    private static void popOperation(char c) { 
        if (operStack.empty() || level(c) > level(operStack.peek()) || operStack.peek() == '(') {
            operStack.push(c);
            return;
        }
        cal(operStack.pop());
        popOperation(c);
    }

    private static void popOperation2(char c) {
        char oper = operStack.pop();
        if (oper == '(') {
            return;
        }
        cal(oper);
        popOperation2(c);
    }

    /*
     * 弹出数运算
     */
    private static void cal(char oper) {
        int sum1 = sumStack.pop();
        int sum2 = sumStack.pop();
        int result = calculate(sum1, sum2, oper);
        sumStack.push(result);
    }

    /*
     * 整理字符和数字
     */
    private static List<String> processStr(String exStr) {
        List<String> exList = new ArrayList<String>();
        String temp = "";
        for (int i = 0; i < exStr.length(); i++) {
            // 如果是一个数字
            if (exStr.charAt(i) >= 48 && exStr.charAt(i) <= 57) {
                temp += exStr.charAt(i);
            } else {
                if (temp.length() != 0) {
                    exList.add(temp);
                    temp = "";
                }
                exList.add(String.valueOf(exStr.charAt(i)));
            }
        }
        if (temp.length() != 0) {
            exList.add(temp);
        }
        return exList;
    }

    /*
     * 优先级定义  
     */
    public static int level(char oper) {
        int level = -1;
        switch (oper) {
            case '(':
            case ')':
                // 符号不处理
                break;
            case '+':
            case '-':
                level = 1;
            case '*':
            case '/':
                level = 2;
                break;
            default:
                throw new RuntimeException("Incorrect operator.");
        }
        return level;
    }

    public static int calculate(int sum1, int sum2, char oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = sum1 + sum2;
                break;
            case '-':
                result = sum2 - sum1;
                break;
            case '*':
                result = sum1 * sum2;
                break;
            case '/':
                result = sum2 / sum1;
                break;
            default:
                throw new RuntimeException(oper + " is incorrect operator.");
        }
        return result;
    }

}
