package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    public Image image;
    public int width;
    public int height;

    public Sprite(String path) throws IOException {
        this.image = ImageIO.read(new File(path));
        rotateImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Sprite(Image image) {
        this.image = image;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public void draw(Graphics g, Cord c, int width, int height) {
        g.setColor(Color.blue);
        g.drawImage(image, c.x, c.y, -width, -height, null);
    }

    public Sprite flipHorizontal() {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        Image image = op.filter((BufferedImage) this.image, null);
        return new Sprite(image);
    }
    private void rotateImage() {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
        tx.translate(-image.getWidth(null), -image.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter((BufferedImage) image, null);
    }
}