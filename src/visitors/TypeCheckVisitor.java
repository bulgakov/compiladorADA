/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitors;

import ast.AritmeticExpression;
import ast.AssignStatement;
import ast.CallExpression;
import ast.DeclarativeItem;
import ast.ExitStatement;
import ast.False;
import ast.FloatLiteral;
import ast.Identifier;
import ast.IdentifierExpression;
import ast.IfStatement;
import ast.IntegerLiteral;
import ast.LogicalExpression;
import ast.LoopStatement;
import ast.NotExpression;
import ast.NullStatement;
import ast.ObjectDeclarativeItem;
import ast.ParameterDefinition;
import ast.CallStatement;
import ast.ElsifStatement;
import ast.ErrorExpression;
import ast.GetStatement;
import ast.Program;
import ast.PutStatement;
import ast.RelationExpression;
import ast.ReturnStatement;
import ast.Statement;
import ast.StringLiteral;
import ast.SubProgramBody;
import ast.True;
import ast.UnaryExpression;
import java.util.ArrayList;
import java.util.List;
import table.ErrorMsg;
import table.SymbolInfo;
import table.SymbolTable;
import type.BaseType;
import type.ProcType;
import type.Undefined;
import type.Void;

/**
 *
 * @author mijail
 */
public class TypeCheckVisitor implements TypeVisitor {

    private SymbolTable global;
    private SymbolTable top;
    private List<ErrorMsg> errors;
    
    public TypeCheckVisitor(SymbolTable global) {
        this.global = global;
        this.top = global;
        this.errors = new ArrayList<ErrorMsg>();
    }
    
    @Override
    public void visit(Program n) {
        n.SubProgramBody.accept(this);
    }

    @Override
    public void visit(SubProgramBody n) {
        if (global.get(n.SubProgramSpecification.Identifier.id).type.same(Undefined.UNDEFINED)) 
            return;
        
        global = global.scopes.get(n.SubProgramSpecification.Identifier.id);
        
        for (ParameterDefinition p : n.SubProgramSpecification.ParameterList.Parameters) {
            p.accept(this);
        }
        
        for(DeclarativeItem i : n.DeclarativePart.Items)
            if (i != null) i.accept(this);
        
        for(Statement s : n.SequenceOfStatements.Statements )
            if (s != null) s.accept(this);
        
        // BIG TODO
        // check every return statements :(
        // if FUNCTION check return type vs return statement type
        // if PROCEDURE check return statement type must be VOID
        // check assig statements
        // if assing id in parameters check mode Out or InOut
        
        global = global.parent;
    }

    @Override
    public void visit(ParameterDefinition n) {
        if (n.Expression == null) // optional expression
            return;
        
        n.Expression.accept(this);
        if (n.Expression.type == null) { 
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "null in 'ParameterDefinition'"));
            return;
        }
        
        for (Identifier i : n.ParameterNameList.Identifiers) 
            if (i.type.assignable(n.Expression.type)) 
                errors.add(new ErrorMsg(
                        n.Expression.getLeft(), 
                        n.Expression.getRight(), 
                        "Error unable to assing " + n.Expression.type.toString() + " to " + i.type.toString()));
    }

    @Override
    public void visit(ObjectDeclarativeItem n) {
        if (n.Expression == null) // optional expression
            return;
        
        n.Expression.accept(this);
        if (n.Expression.type == null) {
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "null in 'ObjectDeclarativeItem'"));
            return;
        }
        
        for (Identifier i : n.IdentifierList.Identifiers) 
            if (i.type.assignable(n.Expression.type)) 
                errors.add(new ErrorMsg(
                        n.Expression.getLeft(), 
                        n.Expression.getRight(), 
                        "unable to assing " + n.Expression.type.toString() + " to " + i.type.toString()));
    }

    @Override
    public void visit(NullStatement n) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //nothing here
    }

    @Override
    public void visit(AssignStatement n) {
        n.Expression.accept(this);
        n.Identifier.type = n.Expression.type;
        
        if (n.Expression.type == null) { 
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "null in 'AssignStatement'"));
            return;
        }
        
        if (global.get(n.Identifier.id).type == null) {
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "null in 'AssignStatement'"));
            return;
        }
        
        if (!global.get(n.Identifier.id).type.assignable(n.Expression.type)) {
            errors.add(new ErrorMsg(
                        n.Expression.getLeft(), 
                        n.Expression.getRight(), 
                        "Error unable to assing " + n.Expression.type.toString() + " to " + global.get(n.Identifier.id).type.toString()));
        }
    }

    @Override
    public void visit(ReturnStatement n) {
        ProcType parentType = (ProcType)global.parent.get(global.name).type;
        
        if (n.Expression == null) {  // optional expression
            n.type = Void.VOID;
            
            if (!parentType.returnType.assignable(n.type))
                errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "expected return type [" + parentType.returnType.toString() + "]"));
            
            return;
        }
        
        n.Expression.accept(this);
        n.type = n.Expression.type;
        
        if (n.Expression.type == null) {
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "null in 'ReturnStatement'"));
            return;
        }
        
        if (!parentType.returnType.assignable(n.type))
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "expected return type [" + parentType.returnType.toString() + "]"));
    }

    @Override
    public void visit(ExitStatement n) {
        n.Expression.accept(this);
        n.type = n.Expression.type;
        
        if (n.Expression.type == null) { 
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "null in 'AssignStatement'"));
            return;
        }
        
        if (!n.Expression.type.same(BaseType.BOOLEAN)) 
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "the expression must be of type boolean."));
    }

    @Override
    public void visit(CallStatement n) {
        n.Expression.accept(this);
        
        if (n.Expression.type == null) { 
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "null in 'CallStatement'"));
            return;
        }
        
        if (!(n.Expression.type instanceof ProcType)) { 
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "unable to invoke " + n.Expression.type.toString()));
            return;
        }
            
        ProcType type = (ProcType)n.Expression.type;
        if (!type.returnType.same(Void.VOID)) 
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "unable to assing " + n.Expression.type.toString()));
    }

    @Override
    public void visit(IfStatement n) {
        n.Expression.accept(this);
        
        if (n.Expression.type == null) { 
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "null in 'IfStatement'"));
            return;
        }
        
        if (!n.Expression.type.same(BaseType.BOOLEAN)) 
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "the expression must be of type boolean."));
        
        for (Statement s : n.Statements.Statements) 
            if (s != null) s.accept(this);
        
        for (ElsifStatement s : n.ElsifStatements.ElsifStatements) 
            if (s != null) s.accept(this);
        
        for (Statement s : n.ElseStatements.Statements) {
            if (s != null) s.accept(this);
        }        
    }

    @Override
    public void visit(ElsifStatement n) {
        n.Expression.accept(this);
        
        if (n.Expression.type == null) { 
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "null in 'ElsifStatement'"));
            return;
        }
        
        if (!n.Expression.type.same(BaseType.BOOLEAN)) 
            errors.add(new ErrorMsg(n.Expression.getLeft(), n.Expression.getRight(), "the expression must be of type boolean."));
        
        for (Statement s : n.Statements.Statements) 
            s.accept(this);
    }

    @Override
    public void visit(LoopStatement n) {
        if (n.Iterator.Iterator.equals("WHILE")) {
            n.Iterator.Exp1.accept(this);
        
            if (n.Iterator.Exp1.type == null) { 
                errors.add(new ErrorMsg(n.Iterator.Exp1.getLeft(), n.Iterator.Exp1.getRight(), "null in 'LoopStatement'"));
                return;
            }

            if (!n.Iterator.Exp1.type.same(BaseType.BOOLEAN)) 
                errors.add(new ErrorMsg(n.Iterator.Exp1.getLeft(), n.Iterator.Exp1.getRight(), "the expression must be of type boolean."));
        }
        
        if (n.Iterator.Iterator.equals("FOR")) {
            n.Iterator.Exp1.accept(this);
            n.Iterator.Exp2.accept(this);
            n.Iterator.Identifier.type = n.Iterator.Exp1.type;
        
            if (n.Iterator.Exp1.type == null || n.Iterator.Exp2.type == null) { 
                errors.add(new ErrorMsg(n.Iterator.Exp1.getLeft(), n.Iterator.Exp1.getRight(), "null in 'LoopStatement'"));
                return;
            }
            
            if (global.get(n.Iterator.Identifier.id).type == null) {
                errors.add(new ErrorMsg(n.Iterator.Exp1.getLeft(), n.Iterator.Exp1.getRight(), "null in 'LoopStatement'"));
                return;
            }
            
            if (!n.Iterator.Identifier.type.same(BaseType.INTEGER)) 
                errors.add(new ErrorMsg(n.Iterator.Identifier.getLeft(), n.Iterator.Identifier.getRight(), "the expression must be of type integer."));
            
            if (!global.get(n.Iterator.Identifier.id).type.assignable(n.Iterator.Exp1.type))
                errors.add(new ErrorMsg(
                        n.Iterator.Exp1.getLeft(), 
                        n.Iterator.Exp1.getRight(), 
                        "Error unable to assing " + n.Iterator.Exp1.type.toString() + " to " + global.get(n.Iterator.Identifier.id).type.toString()));
            
            if (!global.get(n.Iterator.Identifier.id).type.assignable(n.Iterator.Exp1.type))
                errors.add(new ErrorMsg(
                        n.Iterator.Exp2.getLeft(), 
                        n.Iterator.Exp2.getRight(), 
                        "Error unable to assing " + n.Iterator.Exp2.type.toString() + " to " + global.get(n.Iterator.Identifier.id).type.toString()));
        }
        
        if (n.Iterator.Iterator.equals("LOOP")) {
            // Nothing to do here
            // On loop keeps going until a exit statement is executed
        }
        
        for (Statement s : n.Statements.Statements) 
            s.accept(this);
    }

    @Override
    public void visit(GetStatement n) {
        //n.Identifier.id
        if (global == null) {
            errors.add(new ErrorMsg(n.getLeft(), n.getRight(), "null in 'GetStatement'"));
            return;
        }
        
        SymbolInfo info = global.get(n.Identifier.id);
        if (info.getType().same(Undefined.UNDEFINED)) {
            errors.add(new ErrorMsg(n.getLeft(), n.getRight(), "" + n.Identifier.id + "is not declared."));
        }
    }
    
    @Override
    public void visit(PutStatement n) {
        n.param.accept(this);
        
        if (n.param.type == null) {
            errors.add(new ErrorMsg(n.getLeft(), n.getRight(), "null in 'PutStatement'"));
            return;
        }
        
        if (!BaseType.STRING.assignable(n.param.type)) {
            errors.add(new ErrorMsg(
                    n.param.getLeft(), 
                    n.param.getRight(), 
                    "Error unable to " + n.param.type.toString() + "] to [" + BaseType.STRING.toString() + "]"));
            n.type = Undefined.UNDEFINED;
        } else if (!BaseType.INTEGER.assignable(n.param.type)) {
            errors.add(new ErrorMsg(
                    n.param.getLeft(), 
                    n.param.getRight(), 
                    "Error unable to " + n.param.type.toString() + "] to [" + BaseType.STRING.toString() + "]"));
            n.type = Undefined.UNDEFINED;
        } else if (!BaseType.FLOAT.assignable(n.param.type)) {
            errors.add(new ErrorMsg(
                    n.param.getLeft(), 
                    n.param.getRight(), 
                    "Error unable to " + n.param.type.toString() + "] to [" + BaseType.STRING.toString() + "]"));
            n.type = Undefined.UNDEFINED;
        } else if (!BaseType.BOOLEAN.assignable(n.param.type)) {
            errors.add(new ErrorMsg(
                    n.param.getLeft(), 
                    n.param.getRight(), 
                    "Error unable to " + n.param.type.toString() + "] to [" + BaseType.STRING.toString() + "]"));
            n.type = Undefined.UNDEFINED;
        }
    }

    @Override
    public void visit(False n) {
        n.type = BaseType.BOOLEAN;
    }

    @Override
    public void visit(True n) {
        n.type = BaseType.BOOLEAN;
    }

    @Override
    public void visit(LogicalExpression n) {
        n.Exp1.accept(this);
        n.Exp2.accept(this);
        
        if (n.Exp1.type == null) { 
            errors.add(new ErrorMsg(n.Exp1.getLeft(), n.Exp1.getRight(), "null in 'AritmeticExpression'"));
            return;
        }
        
        if (n.Exp2.type == null) { 
            errors.add(new ErrorMsg(n.Exp2.getLeft(), n.Exp2.getRight(), "null in 'AritmeticExpression'"));
            return;
        }

        if (!(BaseType.INTEGER.assignable(n.Exp1.type) && BaseType.INTEGER.assignable(n.Exp2.type))) {
            errors.add(new ErrorMsg(
                    n.Exp1.getLeft(), 
                    n.Exp1.getRight(), 
                    "Error unable to " + n.Operator + " Exp1 [" + n.Exp1.type.toString() + "] and Exp2 [" + n.Exp2.type.toString() + "]"));
            n.type = Undefined.UNDEFINED;
            return;
        } else if (!(BaseType.FLOAT.assignable(n.Exp1.type) && BaseType.FLOAT.assignable(n.Exp2.type))) {
            errors.add(new ErrorMsg(
                    n.Exp1.getLeft(), 
                    n.Exp1.getRight(), 
                    "Error unable to " + n.Operator + " Exp1 [" + n.Exp1.type.toString() + "] and Exp2 [" + n.Exp2.type.toString() + "]"));
            n.type = Undefined.UNDEFINED;
            return;
        }
        
        n.type = n.Exp1.type;
    }

    @Override
    public void visit(RelationExpression n) {
        n.Exp1.accept(this);
        n.Exp2.accept(this);
        
        if (n.Exp1.type == null) { 
            errors.add(new ErrorMsg(n.Exp1.getLeft(), n.Exp1.getRight(), "null in 'AritmeticExpression'"));
            return;
        }
        
        if (n.Exp2.type == null) { 
            errors.add(new ErrorMsg(n.Exp2.getLeft(), n.Exp2.getRight(), "null in 'AritmeticExpression'"));
            return;
        }

        if (!(BaseType.INTEGER.assignable(n.Exp1.type) && BaseType.INTEGER.assignable(n.Exp2.type))) {
            errors.add(new ErrorMsg(
                    n.Exp1.getLeft(), 
                    n.Exp1.getRight(), 
                    "Error unable to " + n.Operator + " Exp1 [" + n.Exp1.type.toString() + "] and Exp2 [" + n.Exp2.type.toString() + "]"));
            n.type = Undefined.UNDEFINED;
            return;
        } else if (!(BaseType.FLOAT.assignable(n.Exp1.type) && BaseType.FLOAT.assignable(n.Exp2.type))) {
            errors.add(new ErrorMsg(
                    n.Exp1.getLeft(), 
                    n.Exp1.getRight(), 
                    "Error unable to " + n.Operator + " Exp1 [" + n.Exp1.type.toString() + "] and Exp2 [" + n.Exp2.type.toString() + "]"));
            n.type = Undefined.UNDEFINED;
            return;
        }
        
        n.type = n.Exp1.type;
    }

    @Override
    public void visit(AritmeticExpression n) {
        n.Exp1.accept(this);
        n.Exp2.accept(this);
        
        if (n.Exp1.type == null) { 
            errors.add(new ErrorMsg(n.Exp1.getLeft(), n.Exp1.getRight(), "null in 'AritmeticExpression'"));
            return;
        }
        
        if (n.Exp2.type == null) { 
            errors.add(new ErrorMsg(n.Exp2.getLeft(), n.Exp2.getRight(), "null in 'AritmeticExpression'"));
            return;
        }

        if (!(BaseType.INTEGER.assignable(n.Exp1.type) && BaseType.INTEGER.assignable(n.Exp2.type))) {
            errors.add(new ErrorMsg(
                    n.Exp1.getLeft(), 
                    n.Exp1.getRight(), 
                    "Error unable to " + n.Operator + " Exp1 [" + n.Exp1.type.toString() + "] and Exp2 [" + n.Exp2.type.toString() + "]"));
            n.type = Undefined.UNDEFINED;
            return;
        } else if (!(BaseType.FLOAT.assignable(n.Exp1.type) && BaseType.FLOAT.assignable(n.Exp2.type))) {
            errors.add(new ErrorMsg(
                    n.Exp1.getLeft(), 
                    n.Exp1.getRight(), 
                    "Error unable to " + n.Operator + " Exp1 [" + n.Exp1.type.toString() + "] and Exp2 [" + n.Exp2.type.toString() + "]"));
            n.type = Undefined.UNDEFINED;
            return;
        }
        
        n.type = n.Exp1.type;
    }

    @Override
    public void visit(NotExpression n) {
        n.Exp.accept(this);
        
        if (n.Exp.type == null) { 
            errors.add(new ErrorMsg(n.Exp.getLeft(), n.Exp.getRight(), "null in 'NotExpression'"));
            return;
        }

        if (!n.Exp.type.same(BaseType.BOOLEAN)) { 
            errors.add(new ErrorMsg(n.Exp.getLeft(), n.Exp.getRight(), "the expression must be of type boolean."));
            n.type = Undefined.UNDEFINED;
            return;
        }
        
        n.type = n.Exp.type;
    }

    @Override
    public void visit(UnaryExpression n) {
        n.Exp1.accept(this);
        
        if (n.Exp1.type == null) { 
            errors.add(new ErrorMsg(n.Exp1.getLeft(), n.Exp1.getRight(), "null in 'UnaryExpression'"));
            return;
        }

        if (!BaseType.INTEGER.assignable(n.Exp1.type)) {
            errors.add(new ErrorMsg(n.Exp1.getLeft(), n.Exp1.getRight(), "the expression must be of type integer."));
            n.type = Undefined.UNDEFINED;
            return;
        } else if (!BaseType.FLOAT.assignable(n.Exp1.type)) {
            errors.add(new ErrorMsg(n.Exp1.getLeft(), n.Exp1.getRight(), "the expression must be of type float."));
            n.type = Undefined.UNDEFINED;
            return;
        }
        
        n.type = n.Exp1.type;
    }

    @Override
    public void visit(CallExpression n) {
        SymbolInfo info = global.get(n.Identifier.id);
        if (info.getType().same(Undefined.UNDEFINED)) {
            errors.add(new ErrorMsg(n.getLeft(), n.getRight(), "" + n.Identifier.id + " is not declared."));
            n.type = Undefined.UNDEFINED;
            return;
        }
        
        if (info.getType() instanceof ProcType) {
            errors.add(new ErrorMsg(n.getLeft(), n.getRight(), "" + n.Identifier.id + " is not a procedure."));
            n.type = Undefined.UNDEFINED;
            return;
        }
        
        ProcType type = (ProcType)info.getType();
        n.type = type.returnType;
        
        if (type.paramsCount() != n.Parameters.Parameters.size()) {
            errors.add(new ErrorMsg(n.getLeft(), n.getRight(), "" + n.Identifier.id + " incorrect number of arguments."));
            n.type = Undefined.UNDEFINED; // TODO
        } else {
            for (int i = 0; i < n.Parameters.Parameters.size(); i++) {
                n.Parameters.Parameters.get(i).accept(this);
                type.Type et = n.Parameters.Parameters.get(i).type;
                if (!type.params.get(i).assignable(et)) {
                    errors.add(new ErrorMsg(n.Parameters.Parameters.get(i).getLeft(), n.Parameters.Parameters.get(i).getRight(), "" + n.Parameters.Parameters.get(i).type.toString() + " parameter do not match type with argument " + type.params.get(i).toString()));
                    n.type = Undefined.UNDEFINED; // TODO
                }
            }
        }
    }

    @Override
    public void visit(StringLiteral n) {
        n.type = BaseType.STRING;
    }

    @Override
    public void visit(FloatLiteral n) {
        n.type = BaseType.FLOAT;
    }

    @Override
    public void visit(IntegerLiteral n) {
        n.type = BaseType.INTEGER;
    }

    @Override
    public void visit(IdentifierExpression n) {
        if (global == null) {
            errors.add(new ErrorMsg(n.getLeft(), n.getRight(), "null in 'IdentifierExpression'"));
            return;
        }
        
        SymbolInfo info = global.get(n.Identifier.id);
        if (info.getType().same(Undefined.UNDEFINED)) {
            errors.add(new ErrorMsg(n.getLeft(), n.getRight(), "" + n.Identifier.id + "is not declared."));
            n.type = Undefined.UNDEFINED;
            return;
        }
        
        n.type = info.getType();
    }
    
    @Override
    public void visit(ErrorExpression n) {
        n.type = Undefined.UNDEFINED;
    }

    public boolean error() { 
        return errors.size() > 0;
    }
    
    public List<ErrorMsg> getErrors() {
        return errors;
    }
}
