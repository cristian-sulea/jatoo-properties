/*
 * Copyright (C) Cristian Sulea ( http://cristian.sulea.net )
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
