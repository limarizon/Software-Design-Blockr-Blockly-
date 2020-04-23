package com.ui.components.text;

import com.ui.Component;
import com.ui.WindowRegion;

import java.awt.*;

/**
 * a text component that has a fontsize, allignment in both size and the content
 */
public class TextComponent extends Component {

    /**
     * The constant MINIMUM_FONT_SIZE.
     */
    public static final int MINIMUM_FONT_SIZE = 8;
    /**
     * The constant DEFAULT_FONT_SIZE.
     */
    public static final int DEFAULT_FONT_SIZE = 12;

    /**
     * Get horizontal align.
     *
     * @return the horizontal align
     */
    public HorizontalAlign getHorizontalAlign(){
        return horizontalAlign;
    }

    private final HorizontalAlign horizontalAlign;

    /**
     * Get vertical align of Classtype VerticalAlign.
     *
     * @return the vertical align
     */
    public VerticalAlign getVerticalAlign(){
        return verticalAlign;
    }

    private final VerticalAlign verticalAlign;

    /**
     * Get text string.
     *
     * @return the string
     */
    public String getText(){
        return text;
    }

    private final String text;

    /**
     * Get font size int.
     *
     * @return the int
     */
    public int getFontSize(){
        return fontSize;
    }

    private int fontSize;

    /**
     * here you can specify the allignments as extra
     *
     * @param text            the text
     * @param fontSize        the font size
     * @param horizontalAlign the horizontal align
     * @param verticalAlign   the vertical align
     */
    public TextComponent(String text, int fontSize, HorizontalAlign horizontalAlign, VerticalAlign verticalAlign){
      
        throwIfNull(text, "text");

        if(fontSize < MINIMUM_FONT_SIZE){
            throw new IllegalArgumentException("fontSize must be greater than " + MINIMUM_FONT_SIZE);
        }

        throwIfNull(horizontalAlign, "horizontalAlign");
        throwIfNull(verticalAlign, "verticalAlign");
      
        this.text = text;
        this.fontSize = fontSize;
        this.horizontalAlign = horizontalAlign;
        this.verticalAlign = verticalAlign;
    }

    /**
     * Given a string, this will set its allignments to HorizontalAlign.Center and VerticalAlign.Middle
     *
     * @param text     the text
     * @param fontSize the font size
     */
    public TextComponent(String text, int fontSize){
        this(text, fontSize, HorizontalAlign.Center, VerticalAlign.Middle);
    }

    /**
     * given a string, it will set the allignment and fontsize all default
     *
     * @param text the text
     */
    public TextComponent(String text){
        this(text, DEFAULT_FONT_SIZE);
    }

    private void throwIfNull(Object object, String name){
        if(object != null)
            return;
        throw new IllegalArgumentException(String.format("%s must be effective", name));
    }

    @Override
    public void draw(Graphics graphics) {

        var text = getText();

        var region = WindowRegion.fromGraphics(graphics);

        graphics.setFont(new Font(graphics.getFont().getName(), graphics.getFont().getStyle(), getFontSize()));
        var textWidth = graphics.getFontMetrics().stringWidth(text);
        var textHeight = graphics.getFontMetrics().getHeight();

        int x = 0;

        switch(getHorizontalAlign()){

            case Left:
                x = 0;
                break;
            case Center:
                x = (region.getWidth() - textWidth) / 2;
                break;
            case Right:
                x = region.getWidth() - textWidth;
                break;
        }

        int y = 0;

        switch(getVerticalAlign()){

            case Top:
                y = textHeight;
                break;
            case Middle:
                y = (region.getHeight() - textHeight) / 2 + textHeight;
                break;
            case Bottom:
                y = region.getHeight();
                break;
        }

        graphics.drawString(text, x, y);
    }
}
