package EvilMario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class menu {
    public static Rectangle marioButton = new Rectangle(10, 570, 300, 37);
    public static Rectangle streetButton = new Rectangle(325, 570, 300, 37);
    public static Rectangle pacButton = new Rectangle(645, 570, 300, 37);
    public static Rectangle spaceButton = new Rectangle(960, 570, 300, 37);

    public static void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("Proxima Nova", Font.BOLD, 45);
        Font fnt1 = new Font("Proxima Nova", Font.BOLD, 30);
        Font fnt2 = new Font("Proxima Nova", Font.BOLD, 15);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        //g.drawString("Ultimate Star Wars Arcade", 350, 40);

        g.setFont(fnt1);
        g.drawString("SUPER LUKE BROS", marioButton.x + 17, marioButton.y + 29);
            g2d.draw(marioButton);
        g.drawString("STAR FIGHTER", streetButton.x + 49, streetButton.y + 29);
            g2d.draw(streetButton);
        g.drawString("PAC WARS", pacButton.x + 73, pacButton.y + 29);
            g2d.draw(pacButton);
        g.drawString("SPACE WARS", spaceButton.x + 59, spaceButton.y + 29);
            g2d.draw(spaceButton);

        g.setFont(fnt2);
        g.drawString("VERSION 4.5.2016",1130,20);
    }
}