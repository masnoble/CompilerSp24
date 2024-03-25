package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

public interface Parser {
    public Program parse() throws CMinusException;
    public void printTree(BufferedWriter writer) throws IOException;
}
