/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman2;
import java .awt.Image;
/**
 *
 * @author Leonardo Araujo
 */
public class Sprite {
    private boolean visible;
        private Image image;
        protected int x;
        protected int y;
        protected int x_spot;
        protected int y_spot;
        protected boolean dying;
        protected int dx;
        protected int dy;

        public Sprite() {
            visible = true;
        }

        public void die() {
            visible = false;
        }

        public boolean isVisible() {
            return visible;
        }

        protected void setVisible(boolean visible) {
            this.visible = visible;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public Image getImage() {
            return image;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
        public int getYSpot() {
            return y_spot;
        }

        public int getXSpot() {
            return x_spot;
        }
        public void setDying(boolean dying) {
            this.dying = dying;
        }

        public boolean isDying() {
            return this.dying;
        }
}
