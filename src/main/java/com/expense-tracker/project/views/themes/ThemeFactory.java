package com.dt181g.project.views.themes;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

/**
 * Interface for a visual theme factory
 *
 * @author Jakob Cederblad
 */
public interface ThemeFactory {
    /**
     * Create a theme label with specified text, font and color (opinional)
     * @param text the text for the label
     * @param font the font of the label
     * @param colors optional font color for label
     * @return the created label
     */
    JLabel createLabel(String text, Font font, Color... colors);

    /**
     * Create a themed button with specified text
     * @param text the text for the button
     * @return the created button
     */
    JButton createButton(String text);

    /**
     * Create a themed progress bar
     * @return the created progress bar
     */
    JProgressBar createProgressbar();

    /**
     * Create a themed separator
     * @return the created separator
     */
    JSeparator createSeparator();

    /**
     * Create a themed text area
     * @return the created text area
     */
    JTextArea createTextArea();

    /**
     * Will get the background color for the theme
     * @return the background color
     */
    Color getBackgroundColor();

    /**
     * Will get the foreground color for the theme
     * @return the foreground color
     */
    Color getForegroundColor();
}
