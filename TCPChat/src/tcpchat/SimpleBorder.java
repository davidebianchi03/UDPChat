package tcpchat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author Davide
 */

/*
    Classe per disegnare bordi personalizzati
 */
public class SimpleBorder extends AbstractBorder {

    private Color border_color;//colore del bordo
    private int border_thickness;//spessore del bordo
    private int border_radius;//raggio di curvatura del bordo negli spigoli
    private Insets padding;//distanza tra il contenuto e il bordo

    public SimpleBorder() {
        border_color = null;
        border_thickness = 0;
        border_radius = 0;
        padding = null;
    }

    public SimpleBorder(Color border_color, int border_thickness, int border_radius, Insets padding) {
        this.border_color = border_color;
        this.border_thickness = border_thickness;
        this.border_radius = border_radius;
        this.padding = padding;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2 = (Graphics2D) g;;
        BasicStroke stroke = new BasicStroke(border_thickness);
        g2.setStroke(stroke);
        
        RoundRectangle2D.Double border_rect = new RoundRectangle2D.Double(x, y, width, height, border_radius, border_radius);
        Area area = new Area(border_rect);
        Component parent_component = c.getParent();
        if (parent_component != null) {
            Color bg = parent_component.getBackground();
            Rectangle rect = new Rectangle(0, 0, width, height);
            Area borderRegion = new Area(rect);
            borderRegion.subtract(area);
            g2.setClip(borderRegion);
            g2.setColor(bg);
            g2.fillRect(0, 0, width, height);
            g2.setClip(null);
        }

        g2.setColor(border_color);
        g2.setStroke(stroke);
        g2.draw(area);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return getBorderInsets(c);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return padding;
    }

    /*
        Metodi get e set
     */
    public Color getBorder_color() {
        return border_color;
    }

    public void setBorder_color(Color border_color) {
        this.border_color = border_color;
    }

    public int getBorder_thickness() {
        return border_thickness;
    }

    public void setBorder_thickness(int border_thickness) {
        this.border_thickness = border_thickness;
    }

    public int getBorder_radius() {
        return border_radius;
    }

    public void setBorder_radius(int border_radius) {
        this.border_radius = border_radius;
    }

    public Insets getPadding() {
        return padding;
    }

    public void setPadding(Insets padding) {
        this.padding = padding;
    }
}
