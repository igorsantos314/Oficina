package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConstantesUtil {
	
	public final static String OFICINA_DATABASE_PORT;
	public final static String OFICINA_DATABASE_IP;
	public final static String OFICINA_DATABASE_NAME;
	
	private static Properties prop; 
	private static FileInputStream fis; 
	
	static 
	{
		try 
		{
			prop = new Properties();
			fis = new FileInputStream("Properties");
			prop.load(fis);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OFICINA_DATABASE_PORT = prop.getProperty("oficina_database_port");
		OFICINA_DATABASE_IP   = prop.getProperty("oficina_database_ip");
		OFICINA_DATABASE_NAME = prop.getProperty("oficina_database_name");
	}
	
	public static boolean containsKey(String key)
	{
		return prop.containsKey(key);
	}
}
