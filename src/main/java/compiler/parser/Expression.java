package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

import compiler.lowlevel.Function;

public abstract class Expression{
    abstract void print(String prefix, BufferedWriter writer) throws IOException;

    //return the reg num of this expression
    abstract int genLLCode(Function f);
}