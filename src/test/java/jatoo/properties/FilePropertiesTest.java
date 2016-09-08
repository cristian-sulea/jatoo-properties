package jatoo.properties;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class FilePropertiesTest {

  private static final File FILE = new File("file.properties");

  @Test
  public void test() throws Exception {

    FileProperties p = new FileProperties(FILE);

    p.setProperty("string", "string");
    p.setProperty("int", 1);
    p.setProperty("boolean", true);
    p.setProperty("point", new Point(1, 1));
    p.setProperty("dimension", new Dimension(1, 1));
    p.setProperty("class", getClass());
    p.setProperty("color", Color.RED);

    p.save();

    p = new FileProperties(FILE);
    p.load();

    Assert.assertEquals("string", p.getPropertyAsString("string"));
    Assert.assertEquals(1, p.getPropertyAsInt("int"));
    Assert.assertTrue(p.getPropertyAsBoolean("boolean"));
    Assert.assertEquals(new Point(1, 1), p.getPropertyAsPoint("point"));
    Assert.assertEquals(new Dimension(1, 1), p.getPropertyAsDimension("dimension"));
    Assert.assertEquals(getClass(), p.getPropertyAsClass("class"));
    Assert.assertEquals(new Color(Color.RED.getRGB()), p.getPropertyAsColor("color"));

    FILE.delete();

    Assert.assertFalse(FILE.exists());
  }

}
