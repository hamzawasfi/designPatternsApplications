package State;

public class Manager {
    private Tool operation;

    public void Click(){
        operation.click();
    }

    public int Calculate(int num1, int num2){
        return operation.calculate(num1, num2);
    }

    public Tool getOperation() {
        return operation;
    }

    public void setOperation(Tool operation) {
        this.operation = operation;
    }
}
