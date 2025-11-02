package com.dt181g.project.views.themes;

import com.dt181g.project.support.AppConfig;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

/**
 * Factory to create dark themed JComponents
 *
 * @author Jakob Cederblad
 */
public class DarkThemeFactory implements ThemeFactory {
    /**
     * Construct a dark theme factory
     */
    public DarkThemeFactory() {}

    /**
     * Create a dark themed label
     * @param text the text for the label
     * @param font the font of the label
     * @param colors optional font color for label
     * @return the created label
     */
    @Override
    public JLabel createLabel(String text, Font font, Color... colors) {
        JLabel label = new JLabel(text);
        Color color = (colors.length > 0) ? colors[0] : getForegroundColor();
        label.setForeground(color);
        label.setFont(font);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    /**
     * Create a dark themed button
     * @param text the text for the button
     * @return the created button
     */
    @Override
    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(60, 63, 65));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    /**
     * Create a dark themed progress bar
     * @return the created progress bar
     */
    @Override
    public JProgressBar createProgressbar() {
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setSize(100, 100);
        progressBar.setValue(0);
        progressBar.setBackground(new Color(35, 35, 35));
        progressBar.setForeground(AppConfig.COLOR_BLUE);
        progressBar.setBorderPainted(false);
        return progressBar;
    }

    /**
     * Create a dark themed separator
     * @return the created separator
     */
    @Override
    public JSeparator createSeparator() {
        JSeparator jSeparator = new JSeparator();
        jSeparator.setBackground(getForegroundColor());
        return jSeparator;
    }

    /**
     * Create a dark themed text area
     * @return the created text area
     */
    @Override
    public JTextArea createTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(new Color(55, 55, 55));
        textArea.setForeground(getForegroundColor());
        return textArea;
    }

    /**
     * Will get the dark theme background color
     * @return the dark theme background color
     */
    @Override
    public Color getBackgroundColor() {
        return new Color(50, 50, 50);
    }

    /**
     * Will get the dark theme foreground color
     * @return the dark theme foreground color
     */
    @Override
    public Color getForegroundColor() {
        return Color.WHITE;
    }
}
