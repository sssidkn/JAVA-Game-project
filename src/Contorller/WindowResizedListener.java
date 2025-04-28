package Contorller;

import Model.Cord;
import Model.Hero;
import Observer.WindowObserver;
import View.MainWindow;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

public class WindowResizedListener implements ComponentListener {

    private final MainWindow window;

    public WindowResizedListener(MainWindow window) {
        this.window = window;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        for (WindowObserver observer : window.getObservers()) {
            try {
                observer.update(window.getWidth(), window.getHeight());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
