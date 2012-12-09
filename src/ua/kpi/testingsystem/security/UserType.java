package ua.kpi.testingsystem.security;

import java.util.EnumMap;

/**
 * @author Ivanov Yaroslav
 * 
 */
public enum UserType {
	TUTOR(AccessList.TUTOR_PAGE, AccessList.CREATE_TEST, AccessList.EDIT_TEST,
			AccessList.DELETE_TEST), 
	STUDENT(AccessList.PASS_TEST), 
	ANONYMOUS;

	private EnumMap<AccessList, UserType> accesses;

	private UserType(final AccessList... accessList) {
		accesses = new EnumMap<AccessList, UserType>(AccessList.class);
		for (AccessList access : accessList) {
			accesses.put(access, this);
		}
	}

	public boolean contain(final AccessList access) {
		return accesses.containsKey(access);
	}
}
