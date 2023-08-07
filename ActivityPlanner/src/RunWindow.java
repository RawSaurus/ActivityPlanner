import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class RunWindow implements ActionListener {
    JFrame frame = new JFrame();
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu  = new JMenu("File");
    JMenu timeMenu = new JMenu("Time");
    JMenuItem shortItem = new JMenuItem("Short");
    JMenuItem mediumItem = new JMenuItem("Medium");
    JMenuItem longItem = new JMenuItem("Long");
    JMenuItem saveItem = new JMenuItem("Save");
    JMenuItem exitItem = new JMenuItem("Exit");
    JTextArea addTextArea = new JTextArea();
    JButton addButton = new JButton("Add");
    int i;

    RunWindow(){

        addTextArea.setBounds(0, 25,420,570);
        addTextArea.setText("Choose from time menu");
        addTextArea.setLineWrap(true);
        addTextArea.setWrapStyleWord(true);
        addTextArea.setFont(new Font("Arial", Font.PLAIN, 20));

        addButton.setBounds(330,525,60,25);
        addButton.setFocusable(false);

        shortItem.addActionListener(this);
        mediumItem.addActionListener(this);
        longItem.addActionListener(this);
        exitItem.addActionListener(this);
        saveItem.addActionListener(this);

        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        timeMenu.add(shortItem);
        timeMenu.add(mediumItem);
        timeMenu.add(longItem);

        menuBar.setBounds(0,0,420,25);
        menuBar.add(fileMenu);
        menuBar.add(timeMenu);

        frame.add(menuBar);
        frame.add(addTextArea);
        frame.setSize(420, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==shortItem){
            addTextArea.setText(readFile("shortItem.txt"));
            i = 1;
        }
        if(e.getSource()==mediumItem){
            addTextArea.setText(readFile("mediumItem.txt"));
            i = 2;
        }
        if(e.getSource()==longItem){
            addTextArea.setText(readFile("longItem.txt"));
            i = 3;
        }

        if(e.getSource()==saveItem){
            if(i==1){
                saveFiles("shortItem.txt");
            }
            if(i==2){
                saveFiles("mediumItem.txt");
            }
            if(i==3){
                saveFiles("longItem.txt");
            }
        }
        if (e.getSource()==exitItem){
            System.exit(0);
        }
    }


    public void saveFiles(String name){
        try (PrintWriter writer = new PrintWriter(new FileWriter(name))) {
            writer.write(addTextArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(String file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while(true) {
                try {
                    if (!((line = reader.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
