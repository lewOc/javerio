package com.boomie.versioning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.FileSystemException;
import java.util.HashMap;

public class Version
{
  private static final String versionFile = "version.properties";

  private static final String MAJOR_PROPERTY = "version.major";
  private static final String MINOR_PROPERTY = "version.minor";
  private static final String BUGFIX_PROPERTY = "version.bugfix";
  private static final String POSTFIX_PROPERTY = "version.postfix";

  private Version()
  {
  }

  public static String getVersion() throws Exception
  {
    String version = "";
    Version ver = new Version();
    URL versionResource = ver.getClass().getClassLoader().getResource(versionFile);
    if(versionResource == null)
    {
      throw new FileNotFoundException(versionFile + " does not exist.");
    }

    HashMap<String, String> versionMap = readFile(versionResource);

    version = versionMap.get(MAJOR_PROPERTY) + "."
            + versionMap.get(MINOR_PROPERTY) + "."
            + versionMap.get(BUGFIX_PROPERTY);

    if(!versionMap.get(POSTFIX_PROPERTY).isEmpty())
    {
      version += "-" + versionMap.get(POSTFIX_PROPERTY);
    }

    return version;
  }

  private static HashMap<String, String> readFile(URL versionResource) throws Exception
  {
    HashMap<String, String> values = new HashMap<>();

    File file = new File(versionResource.getFile());

    if(!file.canRead())
    {
      throw new FileSystemException("Can not read file: " + file.getName());
    }

    try(FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader))
    {
      String curLine = br.readLine();

      while(curLine != null)
      {
        int indexOfEquals = curLine.indexOf('=');
        values.put(curLine.substring(0, indexOfEquals), curLine.substring(indexOfEquals + 1));
        curLine = br.readLine();
      }
    }

    return values;
  }
}
