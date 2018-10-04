package choubey.apurva.hotel.util;

import java.util.HashMap;
import java.util.Map;

import choubey.apurva.hotel.controller.UserController;

public class ControllerObjectProvider {

	private static Map<String, Object> controllerObject;
	
	private ControllerObjectProvider() {
		
	}
	
	static {
		
		controllerObject = new HashMap<>();
		controllerObject.put("user", new UserController());
	}
	
	public static Object getControllerObject(String key) {
		
		return controllerObject.get(key);
	}
}
