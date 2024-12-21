package system._default_;

import database.MySQL_Security;
import oop.Access;
import oop.enums.SecurityRole;

public interface Login {

	public default void inputPassword(char password[]) {
		Object result[] = MySQL_Security.select_where_password_is(password);
		
		Access access = null;//access denied
		if(result != null) {
			int id = (int)result[0];
			SecurityRole role = SecurityRole.valueOf((String)result[1]);
			
			access = new Access(id, role);//access granted
		}
		
		onInputPassword(access);//pass the access argument
	}
	public abstract void onInputPassword(Access access);
}
