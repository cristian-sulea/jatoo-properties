/*
 * Copyright (C) Cristian Sulea ( http://cristian.sulea.net )
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jatoo.properties;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import org.junit.Assert;
import org.junit.Test;

public class AdvancedPropertiesTest {

  @Test
  public void test() throws Exception {

    AdvancedProperties p = new AdvancedProperties();

    p.setProperty("string", "string");
    p.setProperty("int", 1);
    p.setProperty("boolean", true);
    p.setProperty("point", new Point(1, 1));
    p.setProperty("dimension", new Dimension(1, 1));
    p.setProperty("class", getClass());
    p.setProperty("color", Color.RED);

    Assert.assertEquals("string", p.getPropertyAsString("string"));
    Assert.assertEquals(1, p.getPropertyAsInt("int"));
    Assert.assertTrue(p.getPropertyAsBoolean("boolean"));
    Assert.assertEquals(new Point(1, 1), p.getPropertyAsPoint("point"));
    Assert.assertEquals(new Dimension(1, 1), p.getPropertyAsDimension("dimension"));
    Assert.assertEquals(getClass(), p.getPropertyAsClass("class"));
    Assert.assertEquals(new Color(Color.RED.getRGB()), p.getPropertyAsColor("color"));
  }

}
