package framework.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GenericFunctions {
	
	
	
	public String propertyReader(String key) throws IOException
	{
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream(new File("src//main/java//framework//resources//GlobalParameter.properties"));
		prop.load(fis);
		String value=prop.getProperty(key);
		return value;
	}

}
