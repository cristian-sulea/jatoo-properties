/*
 * Copyright (C) 2014 Cristian Sulea ( http://cristian.sulea.net )
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jatoo.properties;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Properties;

/**
 * An advanced {@link Properties} class.
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 4.4, October 6, 2015
 */
@SuppressWarnings("serial")
public class AdvancedProperties extends Properties {

  public AdvancedProperties() {
    super();
  }

  public AdvancedProperties(final Properties defaults) {
    super(defaults);
  }

  //
  // String

  public final String setProperty(final String key, final String value) {
    return (String) super.setProperty(key, value);
  }

  public final String getPropertyAsString(final String key) throws NullPointerException {
    return getPropertyNotNull(key);
  }

  public final String getPropertyAsString(final String key, final String defaultValue) {
    try {
      return getPropertyAsString(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // int
  //

  public final void setProperty(final String key, final int value) {
    setProperty(key, Integer.toString(value));
  }

  public final int getPropertyAsInt(final String key) throws NullPointerException, NumberFormatException {
    return Integer.parseInt(getPropertyNotNull(key));
  }

  public final int getPropertyAsInt(final String key, final int defaultValue) {
    try {
      return getPropertyAsInt(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // boolean
  //

  public final void setProperty(final String key, final boolean value) {
    setProperty(key, Boolean.toString(value));
  }

  public final boolean getPropertyAsBoolean(final String key) throws NullPointerException {
    return Boolean.parseBoolean(getPropertyNotNull(key));
  }

  public final boolean getPropertyAsBoolean(final String key, final boolean defaultValue) {
    try {
      return getPropertyAsBoolean(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // Point
  //

  public final void setProperty(final String key, final Point value) {
    setProperty(key, value.x + "," + value.y);
  }

  public final Point getPropertyAsPoint(final String key) throws NullPointerException, NumberFormatException {

    String value = getPropertyNotNull(key);
    String[] xy = value.split(",");

    return new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
  }

  public final Point getPropertyAsPoint(final String key, final Point defaultValue) {
    try {
      return getPropertyAsPoint(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // Dimension
  //

  public final void setProperty(final String key, final Dimension value) {
    setProperty(key, value.width + "," + value.height);
  }

  public final Dimension getPropertyAsDimension(final String key) throws NullPointerException, NumberFormatException {

    String value = getPropertyNotNull(key);
    String[] xy = value.split(",");

    return new Dimension(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
  }

  public final Dimension getPropertyAsDimension(final String key, final Dimension defaultValue) {
    try {
      return getPropertyAsDimension(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // Class
  //

  public final void setProperty(final String key, final Class<?> value) {
    setProperty(key, value.getName());
  }

  public final Class<?> getPropertyAsClass(final String key) throws NullPointerException, ClassNotFoundException {
    return Class.forName(getPropertyNotNull(key));
  }

  public final Class<?> getPropertyAsClass(final String key, final Class<?> defaultValue) {
    try {
      return getPropertyAsClass(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // Color
  //

  public final void setProperty(final String key, final Color value) {
    setProperty(key, value.getRed() + "," + value.getGreen() + "," + value.getBlue() + "," + value.getAlpha());
  }

  public final Color getPropertyAsColor(final String key) throws NullPointerException, IllegalArgumentException {

    String value = getPropertyNotNull(key);
    String[] color = value.split(",");

    return new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2]), Integer.parseInt(color[3]));
  }

  public final Color getPropertyAsColor(final String key, final Color defaultValue) {
    try {
      return getPropertyAsColor(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // handy methods
  //

  private String getPropertyNotNull(final String key) throws NullPointerException {

    String value = getProperty(key);

    if (value == null) {
      throw new NullPointerException("no value for key " + key);
    }

    return value;
  }

}
