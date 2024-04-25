package compiler.x64codegen;

import compiler.x86codegen.*;

public class X64CodegenException extends RuntimeException{

  public X64CodegenException(String msg) {
    super (msg);
  }
}