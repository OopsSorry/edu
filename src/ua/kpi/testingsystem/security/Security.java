package ua.kpi.testingsystem.security;

import ua.kpi.testingsystem.beans.User;

/**
 * @author Ivanov Yaroslav
 *
 */
public class Security {
	
	protected Security(){
	}
	
	public static void ensure(final User user, final AccessList requiredAccess) {
		if(!user.getType().contain(requiredAccess)) {
			throw new SecurityException();
		}
	}
	
}
