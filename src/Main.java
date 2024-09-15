import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Linear Equation Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 650);
        frame.setLayout(null);

        Vector2<Integer> pointOne = new Vector2<>(0, 0);
        Vector2<Integer> pointTwo = new Vector2<>(0, 0);
        Vector2<Double> equation = new Vector2<>(0.0, 0.0);

        JLabel equationLabel = new JLabel("Equation: y = 0x + 0");
        equationLabel.setBounds(50, 580, 300, 30);
        frame.add(equationLabel);

        JLabel coordinatesLabel = new JLabel("Coordinates: ");
        coordinatesLabel.setBounds(50, 610, 300, 30);
        frame.add(coordinatesLabel);

        JPanel panel = getjPanel(pointOne, pointTwo);
        frame.add(panel);

        JLabel instructionsLabel = new JLabel("<html>Enter two values of the simple equation A and B. The correct form of the entered data is two values entered after the decimal point. Example: \"1, 20\".</html>");
        instructionsLabel.setBounds(5, 5, 580, 60);
        frame.add(instructionsLabel);

        JTextField textField = new JTextField();
        textField.setBounds(50, 70, 300, 30);
        frame.add(textField);

        JButton button = new JButton("Draw Line");
        button.setBounds(400, 70, 150, 30);
        frame.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                JOptionPane.showMessageDialog(frame, "You entered: " + inputText);

                String[] parts = inputText.split(",");

                try {
                    double a = Double.parseDouble(parts[0].trim());
                    double b = Double.parseDouble(parts[1].trim());

                    equation.setXValue(a);
                    equation.setYValue(b);

                    DecimalFormat df = new DecimalFormat("#.##");
                    equationLabel.setText("Equation: y = " + df.format(a) + "x + " + df.format(b));

                    int centerX = panel.getWidth() / 2;
                    int centerY = panel.getHeight() / 2;

                    pointOne.setXValue(centerX - 200);
                    pointOne.setYValue(centerY - (int)(equation.getXValue() * (pointOne.getXValue() - centerX) + equation.getYValue()));

                    pointTwo.setXValue(centerX + 200);
                    pointTwo.setYValue(centerY - (int)(equation.getXValue() * (pointTwo.getXValue() - centerX) + equation.getYValue()));

                    panel.repaint();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid number format. Please enter numbers in the format 'a,b'.");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter both 'a' and 'b' values separated by a comma.");
                }
            }
        });

        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // null
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                coordinatesLabel.setText(String.format("Coordinates: (%d, %d)", x - panel.getWidth() / 2, panel.getHeight() / 2 - y));
            }
        });

        frame.setVisible(true);
    }

    private static JPanel getjPanel(Vector2<Integer> pointOne, Vector2<Integer> pointTwo) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.WHITE);

                g.setColor(Color.BLACK);
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;

                g.drawLine(centerX, 0, centerX, getHeight()); // Y
                g.drawLine(0, centerY, getWidth(), centerY); // X

                g.setColor(Color.RED);
                if (pointOne.getXValue() != null && pointTwo.getXValue() != null) {
                    g.drawLine(pointOne.getXValue(), pointOne.getYValue(), pointTwo.getXValue(), pointTwo.getYValue());
                }
            }
        };

        panel.setBounds(0, 110, 600, 430);
        return panel;
    }
}
