package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

import compiler.lowlevel.Function;

public abstract class Statement{
    abstract void print(String prefix, BufferedWriter writer) throws IOException;

    abstract void genLLCode(Function f);
}