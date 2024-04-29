package compiler.parser;

import java.io.BufferedWriter;
import java.util.ArrayList;

import compiler.lowlevel.Attribute;
import compiler.lowlevel.Function;
import compiler.lowlevel.Operand;
import compiler.lowlevel.Operation;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation.OperationType;

import java.io.IOException;

public class CallExpression extends Expression {
    String ID;
    ArrayList<Expression> args;

    CallExpression(String inID, ArrayList<Expression> inArgs) {
        ID = inID;
        args = inArgs;
    }

    void print(String prefix, BufferedWriter writer) throws IOException {
        writer.write(prefix + ID + "(");
        if (args.isEmpty()) {
            writer.write(prefix + "    " + "void");
        } else {
            for (int i = 0; i < args.size(); i++) {
                args.get(i).print(prefix + "    ", writer);
            }
        }
        writer.write(prefix + ")");
    }

    int genLLCode(Function f) {


        ArrayList<Integer> argRegs = new ArrayList<Integer>();

        for(int i = 0; i < args.size(); i++){
            argRegs.add(args.get(i).genLLCode(f));
            Operation passOperation = new Operation(OperationType.PASS, f.getCurrBlock());
            Operand argOp = new Operand(OperandType.REGISTER, argRegs.get(i));
            passOperation.setSrcOperand(0,argOp);
            passOperation.addAttribute(new Attribute("PARAM_NUM", Integer.toString(i)));

            f.getCurrBlock().appendOper(passOperation);
        }


        Operation callOperation = new Operation(OperationType.CALL, f.getCurrBlock());
        Attribute numParamsAttribute = new Attribute("numParams", 
                                                    new String(Integer.toString(argRegs.size()))
                                                    );
        callOperation.addAttribute(numParamsAttribute);
        Operand funcOperand = new Operand(OperandType.STRING, ID);
        callOperation.setSrcOperand(0, funcOperand);

        f.getCurrBlock().appendOper(callOperation);


        
        // Move the ret reg into our register
        Operation movOperation = new Operation(OperationType.ASSIGN, f.getCurrBlock());

        Operand srcOperand = new Operand(OperandType.MACRO, "RetReg");
        movOperation.setSrcOperand(0, srcOperand);

        int expReg = f.getNewRegNum();
        Operand dstOperand = new Operand(OperandType.REGISTER, expReg);
        movOperation.setDestOperand(0, dstOperand);
        f.getCurrBlock().appendOper(movOperation);

        return expReg;
    }
}