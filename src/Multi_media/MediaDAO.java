package Multi_media;

import Connection.ConnectionToTheDataBase;
import Login.LoginPassing;
import Login.Passwords_Checker;

/**
 * Created by ljam763 on 31/05/2017.
 */
public class MediaDAO extends LoginPassing {
    public MediaDAO() {
        this.conn = ConnectionToTheDataBase.conn;
    }
}
