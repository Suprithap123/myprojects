import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class EventRegistrationForm extends JFrame implements ActionListener {

    JLabel labelTitle, labelName, labelEmail, labelContact, labelEvent;
    JTextField textName, textEmail, textContact, textEvent;
    JButton buttonRegister, buttonReset;

    EventRegistrationForm() {
        setTitle("Event Registration Form");
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        labelTitle = new JLabel("Event Registration Form");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitle.setBounds(50, 20, 300, 30);
        add(labelTitle);

        labelName = new JLabel("Name:");
        labelName.setBounds(50, 80, 100, 30);
        add(labelName);
        textName = new JTextField();
        textName.setBounds(150, 80, 180, 30);
        add(textName);

        labelEmail = new JLabel("Email:");
        labelEmail.setBounds(50, 120, 100, 30);
        add(labelEmail);
        textEmail = new JTextField();
        textEmail.setBounds(150, 120, 180, 30);
        add(textEmail);

        labelContact = new JLabel("Contact No:");
        labelContact.setBounds(50, 160, 100, 30);
        add(labelContact);
        textContact = new JTextField();
        textContact.setBounds(150, 160, 180, 30);
        add(textContact);

        labelEvent = new JLabel("Event Name:");
        labelEvent.setBounds(50, 200, 100, 30);
        add(labelEvent);
        textEvent = new JTextField();
        textEvent.setBounds(150, 200, 180, 30);
        add(textEvent);

        buttonRegister = new JButton("Register");
        buttonRegister.setBounds(70, 270, 100, 40);
        buttonRegister.addActionListener(this);
        add(buttonRegister);

        buttonReset = new JButton("Reset");
        buttonReset.setBounds(200, 270, 100, 40);
        buttonReset.addActionListener(this);
        add(buttonReset);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonRegister) {
            registerParticipant();
        } else if (e.getSource() == buttonReset) {
            resetForm();
        }
    }

    private void registerParticipant() {
        String name = textName.getText();
        String email = textEmail.getText();
        String contact = textContact.getText();
        String event = textEvent.getText();

        if (name.isEmpty() || email.isEmpty() || contact.isEmpty() || event.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
        } else {
            try {
                FileWriter fw = new FileWriter("registrations.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(name + "," + email + "," + contact + "," + event);
                bw.newLine();
                bw.close();
                JOptionPane.showMessageDialog(this, "Registration Successful!");
                resetForm();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving data!");
                ex.printStackTrace();
            }
        }
    }

    private void resetForm() {
        textName.setText("");
        textEmail.setText("");
        textContact.setText("");
        textEvent.setText("");
    }

    public static void main(String[] args) {
        new EventRegistrationForm();
    }
}
