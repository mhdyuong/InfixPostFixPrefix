
import java.util.*;
import java.util.Stack;


public class NotationV2
{
    /**
     * str used to hold the infix expression
     */
    String str = "";
    /**
     * strList is used to hold str for easier access to each character
     */
    ArrayList<String> strList = new ArrayList<String>();
    /**
     * contructor for the
     */
    public NotationV2(String str)
    {
        this.str = str;
    }
    /**
     * This method checks if a given expression is a correctly formatted expression. True is an expression, false otherwise.
     */

    public boolean isExpression()
    {
        ArrayList<String> list = new ArrayList<String>();
        for ( int i = 0; i < str.length()-1; i++)
        {
            list.add(str.substring(i, i+1));
        }
        list.add(str.substring(str.length()-1,str.length()));
        ArrayList<String> arr = new ArrayList<String>();
        String temp = "";
        for ( int m = 0; m < list.size(); m++)
        {
            if( isDigitOrLetter(list.get(m)))
                temp = temp + list.get(m); 
            else
            {
                arr.add(temp);
                temp = "";
                arr.add(list.get(m));
            }
        }
        if ( !temp.equals(""))
            arr.add(temp);
        int leftP = 0;
        int rightP = 0;
        for ( int k = 0; k < arr.size(); k++)
        {
            if( arr.get(k).equals("("))
                leftP++;
            if( arr.get(k).equals(")"))
                rightP++;
        }
        if ( leftP != rightP )
        {
            System.out.println("There's something wrong with the parenthesis.");
            return false;
        }
        for ( int p = 0; p <= arr.size()-3; p++)
        {
            String s = arr.get(p);
            String t = arr.get(p+1);
            String r = arr.get(p+2);
            if (isDigitOrLetter(s) && t.equals(" ") && isDigitOrLetter(r))
            {
                System.out.println("There's some error in the expression.");
                return false;
            }
            if( isDigitOrLetter(s) && isOperator(t) && isOperator(r))
            {
                System.out.println("There's some error in the expression.");
                return false;
            }
            if( isDigitOrLetter(r) && isOperator(t) && isOperator(s))
            {
                System.out.println("There's some error in the expression.");
                return false;
            } 
        }
        for ( int q = 0; q < arr.size(); q++)
        {
            if ( arr.get(q).equals("") )
                arr.remove(q);
        }
        for ( int qq = 0; qq < arr.size(); qq++)
        {
            if(arr.get(qq).equals(" "))
                arr.remove(qq);
        }
        for ( int j = 0; j < arr.size(); j++)
        {
            strList.add(arr.get(j));
            //System.out.println("The index is " + j + " " + arr.get(j));
        }
        int operands = 0;
        int operators = 0;
        boolean flag = false;
        boolean flag2 = false;
        String re;
        String ree;
        for ( int r  = 0; r < arr.size(); r++)
        { 
            re = arr.get(r);
            if ( arr.get(r).equals("(") )
            {
                for ( int rr = r+1; rr < arr.size(); rr++)
                {
                    ree = arr.get(rr);
                    if( arr.get(rr).equals("(")|| arr.get(rr).equals(")") )
                    {
                        if (arr.get(rr).equals(")"))
                        {
                            flag = true;
                        }
                        break;
                    }
                    if ( !isOperator(arr.get(rr))&& !isParen(arr.get(rr)) )
                        operands++;
                    if ( isOperator(arr.get(rr)) )
                        operators++;  
                }
                if (flag)
                {
                    if ( ((operands - operators) != 1))
                    {
                        System.out.println("There's some error in the expression.");
                        return false;
                    }
                }
                else
                {
                    if ( operators != operands )
                    {
                        System.out.println("There's some error in the expression.");
                        return false;
                    }
                }
                operands = 0;
                operators = 0;
                flag = false;
            }
            if ( arr.get(r).equals(")") )
            { 
                if( r == arr.size()-1)
                {
                    return true;
                }
                for ( int rrr = r+1; rrr < arr.size(); rrr++)
                {
                    ree = arr.get(rrr);
                    if( arr.get(rrr).equals("(")|| arr.get(rrr).equals(")") )
                    {
                        if (arr.get(rrr).equals("("))
                        {
                            flag2 = true;
                        }
                        break;
                    }
                    if ( !isOperator(arr.get(rrr))&& !isParen(arr.get(rrr)) )
                        operands++;
                    if ( isOperator(arr.get(rrr)) )
                        operators++;  
                } 
                if(flag2)
                {
                    if ( (operators - operands) != 1)
                    {
                        System.out.println("There's some error in the expression.");
                        return false;
                    }
                }
                else
                {
                    if (operators != operands)
                    {
                        System.out.println("There's some error in the expression.");
                        return false;
                    }
                }
                operands = 0;
                operators = 0;
                flag2 = false;
            }
        }
        return true;
    }
    /**
     * This method returns true of a given string is a digit; false otherwise.
     */

    public boolean isDigit(String s)
    {
        if( s.equals("0") || s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5") || s.equals("6") || s.equals("7") || s.equals("8") 
        || s.equals("9"))
        {
            return true;
        }
        return false;
    }
     /**
     * This method returns true of a given string is a parenthesis; false otherwise.
     */
    public boolean isParen(String s)
    {
        if ( s.equals("(") || s.equals(")") )
            return true;
        return false;
    }
     /**
     * This method returns true of a given string is a operator; false otherwise.
     */
    public boolean isOperator(String o)
    {
        if ( o.equals("+") || o.equals("-") || o.equals("/") || o.equals("*") || o.equals("%") )
        {
            return true;
        }
        return false;
    }
     /**
     * This method returns true of a given string is a digit or letter; false otherwise.
     */
    public boolean isDigitOrLetter(String s)
    {
        if( !isOperator(s) && !isParen(s) )
        {
            return true;
        }
        return false;
    }
    /**
     * This method turns an infix expression to postfix. Returns a string.
     */
    public String infixToPostfix()
    {
        Stack<String> stack  = new Stack<String>();
        stack.push("$");
        String output = "";
        for (int x = 0; x<strList.size(); x++)
        {
            if( isDigitOrLetter(strList.get(x)))
            {
                output = output + strList.get(x);
            }
            else if ( strList.get(x).equals("(") )
            {
                stack.push(strList.get(x));  
            }
            else if ( strList.get(x).equals(")") )
            {
                while(stack.peek() != null && !stack.peek().equals("("))
                {
                    output = output + stack.pop();
                } 
                stack.pop();
            }
            else if(isOperator(strList.get(x))&& highPrecedence(strList.get(x), stack.peek()))
            {
                stack.push(strList.get(x));
            }
            else if (isOperator(strList.get(x)) && !highPrecedence(strList.get(x), stack.peek()) && !stack.peek().equals("$"))
            {
                while(!highPrecedence(strList.get(x), stack.peek())&& !stack.peek().equals("$"))
                {
                    output = output + stack.pop();
                }  
                stack.push(strList.get(x)); 
            }
        }
        while(!stack.isEmpty())
        {
            output = output + stack.pop();
        } 
        output.replaceAll("\\s","");
        return output.substring(0, output.length()-1);
    }
    /**
     * This method turns an infix expression to prefix. Returns a string.
     */
    public String infixToPrefix()
    {
        String output = "";
        Stack<String> operands  = new Stack<String>();
        Stack<String> operators  = new Stack<String>();
        operators.push("$");
        operands.push("$");
        String leftOperand = "";
        String rightOperand = "";
        String op = "";
        for (int x = 0; x<strList.size(); x++)
        {
            if( isDigitOrLetter(strList.get(x)))
            {
                operands.push(strList.get(x)); 
            }
            else if ( strList.get(x).equals("(") )
            {
                operators.push(strList.get(x)); 
            }
            else if ( strList.get(x).equals(")") )
            {
                while(!operators.peek().equals("("))
                {	
                    op = operators.pop();      
                    rightOperand = operands.pop();
                    leftOperand = operands.pop();
                    operands.push(op + leftOperand + rightOperand);
                }
                operators.pop();
            }
            else if(isOperator(strList.get(x)) && highPrecedence(strList.get(x),operators.peek()))
            {
               operators.push(strList.get(x));
            }
            else if( isOperator(strList.get(x)) && !highPrecedence(strList.get(x), operators.peek()) &&  !operators.peek().equals("$"))
            {
                while(!highPrecedence(strList.get(x),operators.peek()) && !operators.peek().equals("$"))
                {
                    op = operators.pop();
                    rightOperand = operands.pop();
                    leftOperand = operands.pop();
                    operands.push(op + leftOperand + rightOperand);
                }  
                operators.push(strList.get(x)); 
            }
        }
        while(!operators.isEmpty())
        {
            op = operators.pop();
            rightOperand = operands.pop();
            leftOperand = operands.pop();
            operands.push(op + leftOperand + rightOperand);
        }
        while(!operands.isEmpty())
        {
            output = output + operands.pop();
        }
         output.replaceAll("\\s","");
        return output.substring(2, output.length());
    }


    /**
     * This method returns true when String s has higher precedence than t. False otherwise. 
     */
    public boolean highPrecedence(String s, String t)     
    {
        if( t.equals("(") || t.equals("$") )
        {
            return true;
        }
        if( s.equals("+") || s.equals("-") )
        {
            if( t.equals("+") || t.equals("-"))
            {
                return false;
            }
            if( t.equals("/") || t.equals("*"))
            {
                return false;
            }
        }
        if( s.equals("/") || s.equals("*") )
        {
            if( t.equals("/") || t.equals("*"))
            {
                return false;
            }
            if( t.equals("+") || t.equals("-"))
            {
                return true;
            }
        }
        return true;
    }
}

    