package com.dt181g.project.controllers;

import com.dt181g.project.views.themes.DarkThemeFactory;
import com.dt181g.project.views.themes.DisplayTheme;
import com.dt181g.project.views.themes.LightThemeFactory;
import com.dt181g.project.views.themes.ThemeFactory;

/**
 * Handles theme management for the game
 * Will set the theme in the model and allows getting the current theme factory to produce UI from.
 *
 * @author Jakob Cederblad
 */
public class ThemeController {
    private final DisplayTheme displayTheme;
    /**
     * Construct a theme controller with passed model
     * @param displayTheme the displayTheme to use
     */
    public ThemeController(DisplayTheme displayTheme) {
        this.displayTheme = displayTheme;
    }

    /**
     * Will get a theme factory based on the model
     * @return the theme factory
     */
    public ThemeFactory getFactory() {
        return switch (displayTheme) {
            case DisplayTheme.LIGHT -> new LightThemeFactory();
            case DisplayTheme.DARK -> new DarkThemeFactory();
        };
    }
}
