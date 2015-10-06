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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * An {@link AdvancedProperties} class constructed over a file.
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 2.3, October 6, 2015
 */
@SuppressWarnings("serial")
public class FileProperties extends AdvancedProperties {

  /** the logger */
  private final Log logger = LogFactory.getLog(getClass());

  private File file;

  private Cipher cipherEncrypt;
  private Cipher cipherDecrypt;

  public FileProperties(final File file) {
    this.file = file;
  }

  public FileProperties(final File file, final Key key) throws GeneralSecurityException {

    this.file = file;

    cipherEncrypt = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipherEncrypt.init(Cipher.ENCRYPT_MODE, key);

    cipherDecrypt = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipherDecrypt.init(Cipher.DECRYPT_MODE, key);
  }

  public FileProperties(final File file, final String passphrase) throws GeneralSecurityException {
    this(file, generateKey(passphrase));
  }

  public FileProperties(final String filename) {
    this(new File(filename));
  }

  public FileProperties(final String filename, final Key key) throws GeneralSecurityException {
    this(new File(filename), key);
  }

  public FileProperties(final String filename, final String passphrase) throws GeneralSecurityException {
    this(new File(filename), passphrase);
  }

  //
  // save
  //

  public final FileProperties save() throws IOException {

    //
    // ensure the parent directories

    file.getAbsoluteFile().getParentFile().mkdirs();

    //
    // save

    OutputStream stream = null;

    try {

      stream = new FileOutputStream(file);

      if (cipherEncrypt != null) {
        stream = new CipherOutputStream(stream, cipherEncrypt);
      }

      store(stream, null);
    }

    catch (IOException e) {
      throw e;
    }

    finally {
      if (stream != null) {
        try {
          stream.close();
        } catch (IOException e) {
          logger.warn("failed to close the stream after saving the properties", e);
        }
      }
    }

    return this;
  }

  public final FileProperties saveSilently() {

    try {
      save();
    }

    catch (IOException e) {
      logger.warn("failed to save properties", e);
    }

    return this;
  }

  //
  // load
  //

  public final FileProperties load() throws IOException {

    InputStream stream = null;

    try {

      stream = new FileInputStream(file);

      if (cipherDecrypt != null) {
        stream = new CipherInputStream(stream, cipherDecrypt);
      }

      load(stream);
    }

    catch (IOException e) {
      throw e;
    }

    finally {
      if (stream != null) {
        try {
          stream.close();
        } catch (IOException e) {
          logger.warn("failed to close the stream after loading the properties", e);
        }
      }
    }

    return this;
  }

  public final FileProperties loadSilently() {

    if (file.exists()) {

      try {
        load();
      }

      catch (IOException e) {
        logger.warn("failed to load properties", e);
      }
    }

    return this;
  }

  //
  //

  private static Key generateKey(final String passphrase) throws NoSuchAlgorithmException {

    MessageDigest digest = MessageDigest.getInstance("SHA");
    digest.update(passphrase.getBytes());

    return new SecretKeySpec(digest.digest(), 0, 16, "AES");
  }

}
