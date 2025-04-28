package Tools;

import Model.Object;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Func {

    public static int distance(Object object1, Object object2) {
        int d = (int) sqrt(pow((object1.getX() - object1.getWidth() / 2 - object2.getX() - object2.getWidth() / 2), 2) + pow(
                (object1.getY() - object1.getHeight() / 2 - object2.getY() - object2.getHeight() / 2), 2));
        return d;
    }
}
