package memento;

public class Editor {
    int num1, num2, result;

    public EditorState createState(){
        return new EditorState(num1, num2, result);
    }

    public void restoreState(EditorState state){
        this.num1 = state.getNum1();
        this.num2 = state.getNum2();
        this.result = state.getResult();
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
