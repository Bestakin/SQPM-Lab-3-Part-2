package com.ontariotechu.sofe3980U;

public class BinaryAPIResult {
    private String operand1;
    private String operator;
    private String operand2;
    private String result;

    public BinaryAPIResult(Binary op1, String operator, Binary op2, Binary result) {
        this.operand1 = op1.getValue();
        this.operator = operator;
        this.operand2 = op2.getValue();
        this.result = result.getValue();
    }

    // Getters and setters
    public String getOperand1() {
        return operand1;
    }
    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public String getOperand2() {
        return operand2;
    }
    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
}
