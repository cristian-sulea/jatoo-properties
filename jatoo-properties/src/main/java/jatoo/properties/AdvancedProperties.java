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

import java.awt.Dimension;
import java.awt.Point;
import java.util.Properties;

/**
 * An advanced {@link Properties} class.
 * 
 * @author Cristian Sulea ( http://cristian.sulea.net )
 * @version 4.1, December 4, 2013
 */
@SuppressWarnings("serial")
public class AdvancedProperties extends Properties {

  public AdvancedProperties() {
    super();
  }

  public AdvancedProperties(Properties defaults) {
    super(defaults);
  }

  //
  // String

  public String setProperty(String key, String value) {
    return (String) super.setProperty(key, value);
  }

  public String getPropertyAsString(String key) throws Throwable {
    return getPropertyNotNull(key);
  }

  public String getPropertyAsString(String key, String defaultValue) {
    try {
      return getPropertyAsString(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // int
  //

  public void setProperty(String key, int value) {
    setProperty(key, Integer.toString(value));
  }

  public int getPropertyAsInt(String key) throws Throwable {
    return Integer.parseInt(getPropertyNotNull(key));
  }

  public int getPropertyAsInt(String key, int defaultValue) {
    try {
      return getPropertyAsInt(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // boolean
  //

  public void setProperty(String key, boolean value) {
    setProperty(key, Boolean.toString(value));
  }

  public boolean getPropertyAsBoolean(String key) throws Throwable {
    return Boolean.parseBoolean(getPropertyNotNull(key));
  }

  public boolean getPropertyAsBoolean(String key, boolean defaultValue) {
    try {
      return getPropertyAsBoolean(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // Point
  //

  public void setProperty(String key, Point value) {
    setProperty(key, value.x + "," + value.y);
  }

  public Point getPropertyAsPoint(String key) throws Throwable {

    String value = getPropertyNotNull(key);
    String[] xy = value.split(",");

    return new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
  }

  public Point getPropertyAsPoint(String key, Point defaultValue) {
    try {
      return getPropertyAsPoint(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // Dimension
  //

  public void setProperty(String key, Dimension value) {
    setProperty(key, value.width + "," + value.height);
  }

  public Dimension getPropertyAsDimension(String key) throws Throwable {

    String value = getPropertyNotNull(key);
    String[] xy = value.split(",");

    return new Dimension(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
  }

  public Dimension getPropertyAsDimension(String key, Dimension defaultValue) {
    try {
      return getPropertyAsDimension(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // Class
  //

  public void setProperty(String key, Class<?> value) {
    setProperty(key, value.getName());
  }

  public Class<?> getPropertyAsClass(String key) throws Throwable {
    return Class.forName(getPropertyNotNull(key));
  }

  public Class<?> getPropertyAsClass(String key, Class<?> defaultValue) {
    try {
      return getPropertyAsClass(key);
    } catch (Throwable t) {
      return defaultValue;
    }
  }

  //
  // handy methods
  //

  private String getPropertyNotNull(String key) throws NullPointerException {

    String value = getProperty(key);

    if (value == null) {
      throw new NullPointerException("no value for key " + key);
    }

    return value;
  }
}
