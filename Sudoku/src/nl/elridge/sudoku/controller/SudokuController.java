package nl.elridge.sudoku.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import nl.elridge.sudoku.model.Game;
import nl.elridge.sudoku.model.UpdateAction;
import nl.elridge.sudoku.view.Field;
import nl.elridge.sudoku.view.SudokuPanel;

 
public class SudokuController implements MouseListener {
    private SudokuPanel sudokuPanel;    // sudoku panalinin kontrolü burada yapılıyor.
    private Game game;                  // Oyunun gerçekleştiği yer

    /**
     * Constructor ve set edilen bölüm 
     *
     * 
     */
    public SudokuController(SudokuPanel sudokuPanel, Game game) {
        this.sudokuPanel = sudokuPanel;
        this.game = game;
    }

    /**
     * Oyuncu alanında tıklamaları aldıgımız bölüm. Seçilen numarayı burada oyun alanına taşıyoruz. Eğer seçim 
     * doğru ise oyun alanı güncelleniyor
     *   Kullanıcı bir alanı tıklatıp farenin sol tuşuna basarsa, tıklanan
     * konumdaki sayı temizlenecek ve tıklanan alan güncellenecektir.
     */
    public void mousePressed(MouseEvent e) {
        JPanel panel = (JPanel)e.getSource();
        Component component = panel.getComponentAt(e.getPoint());
        if (component instanceof Field) {
            Field field = (Field)component;
            int x = field.getFieldX();
            int y = field.getFieldY();

            if (e.getButton() == MouseEvent.BUTTON1 && (game.getNumber(x, y) == 0 || field.getForeground().equals(Color.BLUE))) {
                int number = game.getSelectedNumber();
                if (number == -1)
                    return;
                game.setNumber(x, y, number);
                field.setNumber(number, true);
            } else if (e.getButton() == MouseEvent.BUTTON3 && !field.getForeground().equals(Color.BLACK)) {
                game.setNumber(x, y, 0);
                field.setNumber(0, false);
            }
            sudokuPanel.update(game, UpdateAction.CANDIDATES);
        }
    }

    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
}