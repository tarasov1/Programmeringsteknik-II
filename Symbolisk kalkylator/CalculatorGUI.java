import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorGUI extends JFrame implements ActionListener {
  
  private JButton exitButton;
  private JTextField inputField;
  private JLabel expressionLabel;
  private JLabel resultLabel;
  
  private Map<String,Sexpr> variables = new TreeMap<String, Sexpr>();
  
  
  public CalculatorGUI() {
    setUpGUI();
  }
  
  private void setUpGUI() {
    
    exitButton = new JButton("Exit");
    inputField = new JTextField(10);
    resultLabel = new JLabel();
    expressionLabel = new JLabel();
    setLayout(new GridLayout(5,1));
    JPanel p1 = new JPanel();
    add(inputField);
    add(expressionLabel);
    add(resultLabel);
    add(p1);
    p1.add(exitButton);
    
    exitButton.addActionListener(this);
    inputField.addActionListener(this);
    
    setSize(400,300);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);  
  }
  
  public void actionPerformed(ActionEvent e ) {
    if (e.getSource() == exitButton) {
      System.exit(0);
    } else if (e.getSource() == inputField) {
      performCompute();
      System.out.println(inputField.getText());
    } else {
      System.out.println("Can not handle yet");
    }
  }
  
  
  private void performCompute() {
    
    System.out.println("Start parsing");
    Stokenizer st = new Stokenizer(inputField.getText());
    System.out.println("input: " + inputField.getText());
    Parser p = new Parser(st);
    Sexpr parsed = new Variable("*ERROR*");
    Sexpr evaluated;
    try {
      st.nextToken();
      parsed = p.assignment();
      System.out.println("Evaluerat: " + parsed);
    } catch (SyntaxException e) { }
    
    try {
        evaluated = parsed.eval(variables);
        resultLabel.setText(evaluated.toString()); // resultLab till resultLabel
      
    } catch (EvaluationException e) {
      resultLabel.setText(e.getMessage());
    }
    expressionLabel.setText("input: " + parsed);
    resultLabel.setText("Evaluerat   : " + parsed.eval(variables));
  }
  
  public static void main(String[] args) {
    new CalculatorGUI();
  }  
  
  
  
}

