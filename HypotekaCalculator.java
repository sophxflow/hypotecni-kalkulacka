import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class HypotekaCalculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HypotekaGUI());
    }
}

class HypotekaGUI {
    private JFrame frame;
    private JTextField amountField, yearsField, fixationsField;
    private JTextArea resultArea;
    
    public HypotekaGUI() {
        frame = new JFrame("Hypoteční kalkulačka");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        
        panel.add(new JLabel("Výše hypotéky (Kč):"));
        amountField = new JTextField();
        panel.add(amountField);
        
        panel.add(new JLabel("Doba splácení (let):"));
        yearsField = new JTextField();
        panel.add(yearsField);
        
        panel.add(new JLabel("Doba fixace sazby:"));
        fixationsField = new JTextField();
        panel.add(fixationsField);
        
        JButton calculateButton = new JButton("Vypočítat");
        panel.add(calculateButton);
        
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        
        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateMortgage();
            }
        });
        
        frame.setVisible(true);
    }
    
    private void calculateMortgage() {
        try {
            double loanAmount = Double.parseDouble(amountField.getText());
            int years = Integer.parseInt(yearsField.getText());
            int fixations = Integer.parseInt(fixationsField.getText());
            
            double monthlyRate = 0.05 / 12; // Příkladová úroková sazba 5%
            int months = years * 12;
            
            double monthlyPayment = (loanAmount * monthlyRate) /
                    (1 - Math.pow(1 + monthlyRate, -months));
            
            DecimalFormat df = new DecimalFormat("#.##");
            resultArea.setText("Měsíční splátka: " + df.format(monthlyPayment) + " Kč");
        } catch (NumberFormatException ex) {
            resultArea.setText("Zadejte platná čísla!");
        }
    }
}
