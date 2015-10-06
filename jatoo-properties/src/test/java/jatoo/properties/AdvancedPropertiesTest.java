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
