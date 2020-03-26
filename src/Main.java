import State.*;
import memento.Editor;
import memento.EditorState;
import memento.History;
import memento.Iterator.Iterator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{

    private JPanel panel;
    private JTextArea num1, num2;
    private JLabel result;
    private JButton calculate, undo, redo, plus, minus, multiply, divide, browseHistory;

    private int currentState, saveFiles;

    private Editor editor = new Editor();
    private History history = new History();
    private Manager manager = new Manager();

    public static void main(String[] args){
        new Main();
    }

    public Main(){
        this.setSize(500, 100);
        this.setTitle("memento");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        panel = new JPanel();
        this.add(panel);

        ButtonListener calcualteListener = new ButtonListener();
        ButtonListener undoListener = new ButtonListener();
        ButtonListener redoListener = new ButtonListener();
        ButtonListener plusListener = new ButtonListener();
        ButtonListener minusListener = new ButtonListener();
        ButtonListener multiplyListener = new ButtonListener();
        ButtonListener divideListener = new ButtonListener();
        ButtonListener historyListener = new ButtonListener();


        calculate = new JButton("calculate");
        calculate.addActionListener(calcualteListener);
        panel.add(calculate);

        undo = new JButton("undo");
        undo.addActionListener(undoListener);
        undo.setEnabled(false);
        panel.add(undo);

        redo = new JButton("redo");
        redo.addActionListener(redoListener);
        redo.setEnabled(false);
        panel.add(redo);

        plus = new JButton("+");
        plus.addActionListener(plusListener);
        panel.add(plus);

        minus = new JButton("-");
        minus.addActionListener(minusListener);
        panel.add(minus);

        multiply = new JButton("*");
        multiply.addActionListener(multiplyListener);
        panel.add(multiply);

        divide = new JButton("/");
        divide.addActionListener(divideListener);
        panel.add(divide);

        browseHistory = new JButton("history");
        browseHistory.addActionListener(historyListener);
        panel.add(browseHistory);

        num1 = new JTextArea(1, 5);
        panel.add(num1);

        num2 = new JTextArea(1, 5);
        panel.add(num2);

        result = new JLabel();
        panel.add(result);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == calculate){
                int num3 = Integer.parseInt(num1.getText());
                int num4 = Integer.parseInt(num2.getText());
                int num5 = manager.Calculate(num3, num4);

                result.setText(String.valueOf(num5));

                editor.setNum1(num3);
                editor.setNum2(num4);
                editor.setResult(num5);

                history.addState(editor.createState());

                currentState++;
                saveFiles++;
                undo.setEnabled(true);
            }else if(e.getSource() == undo){
                if(currentState >= 1) {
                    currentState--;

                    if(currentState == 0){
                        undo.setEnabled(false);
                    }

                    editor.restoreState(history.getState(currentState));

                    num1.setText(String.valueOf(editor.getNum1()));
                    num2.setText(String.valueOf(editor.getNum2()));
                    result.setText(String.valueOf(editor.getResult()));

                    redo.setEnabled(true);
                }else {
                    undo.setEnabled(false);
                }
            }else if(e.getSource() == redo){
                if((saveFiles - 1) > currentState){
                    currentState++;

                    editor.restoreState(history.getState(currentState));

                    num1.setText(String.valueOf(editor.getNum1()));
                    num2.setText(String.valueOf(editor.getNum2()));
                    result.setText(String.valueOf(editor.getResult()));

                    undo.setEnabled(true);
                }else {
                    redo.setEnabled(false);
                }
            }else if(e.getSource() == plus){
                manager.setOperation(new Plus());
            }else if(e.getSource() == minus){
                manager.setOperation(new Minus());
            }else if(e.getSource() == multiply){
                manager.setOperation(new Multiply());
            }else if(e.getSource() == divide){
                manager.setOperation(new Divide());
            }else if(e.getSource() == browseHistory){
                Iterator iterator = history.createIterator();
                while (iterator.hasNext()){
                    var content = iterator.current();
                    editor.restoreState((EditorState) content);
                    System.out.println(editor.getResult());
                    iterator.next();
                }
            }
        }
    }
}