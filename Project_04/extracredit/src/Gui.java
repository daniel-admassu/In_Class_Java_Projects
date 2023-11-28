import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 */
public class Gui extends JFrame {



    public Gui() {
        setTitle("My First GUI");
        setSize(450, 375);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // center the dialog
        Toolkit toolkit = getToolkit();
        Dimension screenDim = toolkit.getScreenSize();
        setLocation((screenDim.width - getWidth()) / 2,
                (screenDim.height - getHeight()) / 2);

        // set up main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); 
        getContentPane().add(mainPanel);
        mainPanel.setBackground(Color.WHITE);

        // widgets
        JTextField isbnField = new JTextField();
        isbnField.setBounds(100, 95, 300, 30);
        JLabel isbnLabel = new JLabel("ISBN");
        isbnLabel.setBounds(30,95,30,30);
        mainPanel.add(isbnField);
        mainPanel.add(isbnLabel);

        JTextField authorsField = new JTextField();
        authorsField.setBounds(100, 140, 300, 30);
        JLabel authorLabel = new JLabel("Author");
        authorLabel.setBounds(25,140,40,40);
        mainPanel.add(authorLabel);
        authorsField.setEditable(false);
        mainPanel.add(authorsField);

        JTextField titleField = new JTextField();
        titleField.setBounds(100, 185, 300, 30);
        JLabel tileLabel = new JLabel("Title");
        tileLabel.setBounds(30,185,30,30);
        mainPanel.add(tileLabel);
        titleField.setEditable(false);
        mainPanel.add(titleField);

        JTextField yearField = new JTextField();
        yearField.setBounds(100, 230, 300, 30);
        JLabel yearLabel = new JLabel("Year");
        yearLabel.setBounds(30,230,30,30);
        mainPanel.add(yearLabel);
        yearField.setEditable(false);
        mainPanel.add(yearField);

        JTextField ratingField = new JTextField();
        ratingField.setBounds(100, 275, 100, 30);
        JLabel ratingLabel = new JLabel("Rating");
        ratingLabel.setBounds(25,275,40,40);
        mainPanel.add(ratingLabel);
        ratingField.setEditable(false);
        mainPanel.add(ratingField);

        TreeMap<String,Book> tree = new TreeMap<>();

        File file = new File("BooksDataFile.txt");
        Scanner input;

        JLabel fileLabel = new JLabel("File \"BooksDataFile.txt\" Was Successfully loaded");
        fileLabel.setBounds(75,10,300,30);
        fileLabel.setForeground(Color.GREEN);
        mainPanel.add(fileLabel);


        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            isbnField.setEditable(false);
            fileLabel.setText("File \"BooksDataFile.txt\" Was not Successfully loaded");
            fileLabel.setForeground(Color.red);
            setVisible(true);
            throw new RuntimeException(e);
        }
        String line1 = input.nextLine();

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] parts = line.split("~",10);

            Book book = new Book(parts[2],parts[3],Integer.parseInt(parts[4]),parts[5],
                    parts[6],Double.parseDouble(parts[7]));

            tree.put(parts[2],book);
        }
        String[] array = new String[9300];
        final JComboBox<String> cb = new JComboBox<String>(tree.toKeyArray(array));
        cb.setBounds(100,50,100,30);
        mainPanel.add(cb);

        JLabel result = new JLabel();
        result.setBounds(290,50,120,30);
        mainPanel.add(result);

        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = cb.getSelectedItem().toString();
                isbnField.setText(key);
                Book book = tree.get(key);
                result.setText("Book Found");
                result.setForeground(Color.GREEN);
                String rate = String.valueOf(book.AverageRating());
                String year = String.valueOf(book.PublicationYear());
                authorsField.setText(book.Authors());
                titleField.setText(book.Title());
                yearField.setText(year);
                ratingField.setText(rate);
            }
        });

        isbnField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = isbnField.getText();
                Book book = tree.get(key);
                if(book==null){
                    result.setText("Book Not Found");
                    result.setForeground(Color.red);
                    isbnField.setText("");
                    authorsField.setText("");
                    titleField.setText("");
                    yearField.setText("");
                    ratingField.setText("");
                } else {
                    result.setText("Book Found");
                    result.setForeground(Color.GREEN);
                    String rate = String.valueOf(book.AverageRating());
                    String year = String.valueOf(book.PublicationYear());
                    authorsField.setText(book.Authors());
                    titleField.setText(book.Title());
                    yearField.setText(year);
                    ratingField.setText(rate);
                }
            }
        });

        // last line of code
        setVisible(true);
    }


    public static void main(String[] args) {

        Gui gui = new Gui();

    }

}