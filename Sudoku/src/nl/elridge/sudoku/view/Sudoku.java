package nl.elridge.sudoku.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.UIManager;
import nl.elridge.sudoku.controller.ButtonController;
import nl.elridge.sudoku.controller.SudokuController;
import nl.elridge.sudoku.model.Game;

 
public class Sudoku extends JFrame {
    public Sudoku() {
        super("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        Game game = new Game();

        ButtonController buttonController = new ButtonController(game);
        ButtonPanel buttonPanel = new ButtonPanel();
        buttonPanel.setController(buttonController);
        buttonPanel.setBackground(Color.red);
        add(buttonPanel, BorderLayout.EAST);

        SudokuPanel sudokuPanel = new SudokuPanel();
        SudokuController sudokuController = new SudokuController(sudokuPanel, game);
        sudokuPanel.setGame(game);
        sudokuPanel.setController(sudokuController);
        add(sudokuPanel, BorderLayout.CENTER);

        game.addObserver(buttonPanel);
        game.addObserver(sudokuPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    public static void main(String[] args) {
     
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ex) { ex.printStackTrace(); }
        new Sudoku();
    }
}