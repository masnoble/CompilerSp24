package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

public class NumExpression extends Expression{
    int num;
    NumExpression(int inNum){
        num = inNum;
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + num);
    }
}