package State;

public class Minus implements Tool{
    @Override
    public void click() {

    }

    @Override
    public int calculate(int num1, int num2) {
        return num1 - num2;
    }
}
