package org.mslab.games.client.tool;

public class ApplicationContext {

private static ApplicationContext _instance;
	
	public static ApplicationContext getInstance() {
		if (_instance == null) {
			_instance = new ApplicationContext(); 
		}
		return _instance;
	}
}
