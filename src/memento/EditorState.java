package memento;

public class EditorState {
    int num1, num2, result;

    public EditorState(int num1, int num2, int result){
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getResult() {
        return result;
    }
}
