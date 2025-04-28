package Contorller;

import View.Canvas;
import View.MainWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

public class StartListener implements ActionListener {
    private MainWindow window;

    public StartListener(MainWindow wnd) {
        super();
        window = wnd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.closeMainMenu();
        window.setLayout(new GridBagLayout());
        try {
            window.add(Canvas.getInstance(), new GridBagConstraints(
                    0, 0, 1, 1, 1, 1,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            Canvas.getInstance().setFocusable(false);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        window.validate();
        window.getGame().startGame();
    }
}
