package system._default_;

import database.MySQL_Security;
import oop.Access;
import oop.enumerations.SecurityRole;

public interface Login {

	public default void inputPassword(char password[]) {
		Object result[] = MySQL_Security.select_where_password_is(password);
		
		Access access = null;//access denied
		if(result != null) {
			int id = (int)result[0];
			SecurityRole role = SecurityRole.valueOf((String)result[1]);
			
			access = new Access(id, role);//access granted
		}
		
		if(access != null) switch(access.getRole()) {
			case adm: adminAccess();
			break;
			case emp: employeeAccess(); 
			break;
			default: unidentifiedEntry();
		}
		else wrongPassword();
		
	}
	public default void unidentifiedEntry() {
		onUnidentifiedEntry();
	}
	public default void wrongPassword() {
		onWrongPassword();
	}
	public default void adminAccess() {
		onAdminAccess();
	}
	public default void employeeAccess() {
		onEmployeeAccess();
	}
	
	public abstract void onUnidentifiedEntry();
	public abstract void onWrongPassword();
	public abstract void onAdminAccess();
	public abstract void onEmployeeAccess();
	
}
