package Observer;

import View.MainWindow;

import java.io.IOException;

public interface WindowObserver {
    public void update(int width, int height) throws IOException;
}
