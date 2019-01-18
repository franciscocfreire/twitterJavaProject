package br.com.fiap.twitterapi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	private Properties prop = new Properties();
	
	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public Config() {
		
		InputStream input = null;

		try {
			
			InputStream propertiesInputStream = getClass().getClassLoader()
			        .getResourceAsStream("config.properties");			

			prop.load(propertiesInputStream);			

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
