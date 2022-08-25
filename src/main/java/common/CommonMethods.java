package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class CommonMethods {
	
	static Logger logger = Logger.getLogger(CommonMethods.class.getName());
	
	private static String url;
	
	private static String username;
	
	private static String password;
	
	private static String companyName;
	
	private static String configFile = "src/main/resources/config.properties";
	
	public static String getUrl() {
		return url;
	}
	
	public static String getUsername() {
		return username;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public static String getDisplaycompanyName() {
		return companyName;
	}
	

	
	public static void I_read_config_properties() throws IOException {
		//logger.info("configFile = " + configFile);
		I_read_config_properties(configFile);
	}
	
	public static void I_read_config_properties(String configFile) throws IOException {
		Properties prop = new Properties();

		try {
			FileInputStream in = new FileInputStream(configFile);
			prop.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

		
		String url = prop.getProperty("url");
		if (url != null && !url.trim().isEmpty()) {
			CommonMethods.url = url;
			logger.info("url = " + url);
		}
		String username = prop.getProperty("username");
		if (username != null && !username.trim().isEmpty()) {
			CommonMethods.username = username;
			logger.info("username = " + username);
		}
		String password = prop.getProperty("password");
		if (password != null && !password.trim().isEmpty()) {
			CommonMethods.password = password;
			logger.info("password = " + password);
		}
		String companyName = prop.getProperty("companyName");
		if (companyName != null && !companyName.trim().isEmpty()) {
			CommonMethods.companyName = companyName;
			logger.info("companyName = " + companyName);
		}
		return;
	}
}
