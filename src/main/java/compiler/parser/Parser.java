package compiler.parser;

public interface Parser {
    public Program parse() throws CMinusException;
    public void printTree();
}
