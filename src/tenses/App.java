package tenses;

import javax.swing.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

public class App extends JFrame{

    public JFrame frame;

    public App() throws IOException{
        frame = new JFrame();

        frame.setSize(500,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tense Test Solver");

        // Creates Top Panel (Displays the word to guess the tense of)
        JPanel tensePanel = new JPanel();
        Word currentWord = newWord();
        JTextField wordBox = new JTextField(currentWord.word);
        Font font = new Font("SansSerif", Font.BOLD, 40);
        wordBox.setFont(font);
        wordBox.setEditable(false);
        wordBox.setHorizontalAlignment(JTextField.CENTER);
        GridLayout tensesLayout = new GridLayout(1,1);
        tensePanel.setLayout(tensesLayout);
        tensePanel.add(wordBox, BorderLayout.CENTER);

        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
              try {
                newGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
          });
        timer.setRepeats(false); 

        // Creates the bottom panel containing the buttons
        JPanel choicesPanel = new JPanel();
        
        //Creates each tense option and it's action listener
        JButton present = new JButton("Present");
        present.setToolTipText("Pretty self explanatory man like... I have a car");
        present.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the present tense is pressed
                // Verifies if the answer is correct 
                if (currentWord.tense.equals("present")){
                    System.out.print("YES");
                    present.setBackground(Color.green);
                    // Timer that delays creating a new game by 1.5 seconds so the user has time
                    // to see that they were right
                    timer.start();
                }
                else{
                    System.out.print("NO");
                    present.setBackground(Color.red);
                }
            }
        });
       
        JButton preterite = new JButton("Preterite");
        preterite.setToolTipText("Used to describe completed actions in the past. E.g (I went to the park/ She did her homework yesterday), often used with phrases that denote a specific time frame (e.g last night / yesterday / last month etc)");
        preterite.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
    
                if (currentWord.tense.equals("preterite")){
                    preterite.setBackground(Color.green);
                    timer.setRepeats(false); 
                    timer.start();
                }
                else{
                    preterite.setBackground(Color.red);
                }
            }
        });
        
        JButton imperfect = new JButton("Imperfect");
        imperfect.setToolTipText("Describe past habitual actions (E.g I used to...) and describing people, places, situations etc in the past (E.g There //were// no cars)");
        imperfect.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
    
                if (currentWord.tense.equals("imperfect")){
                    imperfect.setBackground(Color.green);
                    timer.setRepeats(false); 
                    timer.start();
                }
                else{
                    imperfect.setBackground(Color.red);
                }
            }
        });
        
        JButton future = new JButton("Future");
        future.setToolTipText("Actions that will take place (E.g I will do it later)");
        future.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
    
                if (currentWord.tense.equals("future")){
                    future.setBackground(Color.green);
                    timer.setRepeats(false); 
                    timer.start();
                }
                else{
                    future.setBackground(Color.red);
                }
            }
        });
        
        JButton imperative = new JButton("Imperative");
        imperative.setToolTipText("A command, described more as a mood than a tense (E.g Do it now!!)");
        imperative.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
    
                if (currentWord.tense.equals("imperative")){
                    imperative.setBackground(Color.green);
                    timer.setRepeats(false); 
                    timer.start();
                }
                else{
                    imperative.setBackground(Color.red);
                }
            }
        });
        
        JButton conditional = new JButton("Conditional");
        conditional.setToolTipText("Express desire for the future (me gustaria) express frustration about something that could've been avoided (E.g if I had walked more, I Wwould be more tired)");
        conditional.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
    
                if (currentWord.tense.equals("conditional")){
                    conditional.setBackground(Color.green);
                    timer.setRepeats(false); 
                    timer.start();
                }
                else{
                    conditional.setBackground(Color.red);
                }
            }
        });

        // Builds frame
        GridLayout frameLayout = new GridLayout(2,0);
        GridLayout panelLayout = new GridLayout(2,3);
        choicesPanel.setLayout(panelLayout);
        choicesPanel.add(present);
        choicesPanel.add(preterite);
        choicesPanel.add(imperfect);
        choicesPanel.add(future);
        choicesPanel.add(imperative);
        choicesPanel.add(conditional);
        frame.setLayout(frameLayout);
        frame.add(tensePanel);
        frame.add(choicesPanel);
        frame.setVisible(true);
    }

    // Method for new game (resetting thing)
    public void newGame() throws IOException {
        new App();
        frame.dispose();
    }

    // Method for finding a new object of the word class and reading data
    // for it from the excel worksheet
    public Word newWord() throws IOException{
        
        // Finds the workbook and the sheet with the tenses and stuff
        String excelFilePath = "words.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        
        // Num of rows and num of cols
        int rows = sheet.getLastRowNum();
        int max = rows;
        int min = 1;
        
        // Takes a random value to test with
        int num = (int) Math.floor(Math.random() *(max - min + 1) + min) ;
        XSSFRow row = sheet.getRow(num);
        
        // Get word (0) tense (1) infin(2)
        XSSFCell wordCell = row.getCell(0);
        String word = wordCell.getStringCellValue();
        XSSFCell tenseCell = row.getCell(1);
        String tense = tenseCell.getStringCellValue();
        XSSFCell infiCell = row.getCell(2);
        String infi = infiCell.getStringCellValue();

        // Creates new word and returns it 
        Word newWord = new Word(word,infi, tense);
        workbook.close();
        return newWord;
    }
    
    public static void main(String[] args) throws Exception {
        new App();
    }
}
