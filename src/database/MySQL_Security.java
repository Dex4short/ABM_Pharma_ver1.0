package database;

import system.enumerators.SecurityRole;

public class MySQL_Security {

	/**
	 Selects the Security table from the database and retrieve the id and role of the registered password.
	 @param 
	 	password - char[]
	 @return 
	 	Object[] {(int)sec_id, (String)sec_role}
	 */
	public static Object[] selectSecurityData(char password[]) {
		String pass = "";
		for(char p: password) {
			pass += p;
		}

		//search for registered security id and its role
		Object result[][] = MySQL.select(new String[] {"sec_id", "role"}, " security ", " where password='" + pass + "' ");
		
		if(result.length > 0) {
			//has result
			return new Object[] {
					(int) result[0][0],		//security id
					(String) result[0][1]	//security role
			};
		}
		else {
			//has no result
			return null; // no registered data
		}
	}
	public static void updateSecurityData(SecurityRole role, char old_password[], char new_password[]) {
		Object data[] = selectSecurityData(old_password);
		
		if(data == null) throw new RuntimeException("Please input the correct current password");
		
		String new_pass = "";
		for(char p: new_password) {
			new_pass += p;
		}
		
		MySQL.update(
			"security", 
			new String[] {"password"}, 
			new Object[] {new_pass}, 
			"where role='" + role.name() + "'"
		);
	}
}
