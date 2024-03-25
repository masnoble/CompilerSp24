package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Statement{
    abstract void print(String prefix, BufferedWriter writer) throws IOException;
}