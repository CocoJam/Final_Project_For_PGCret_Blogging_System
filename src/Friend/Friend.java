package Friend;

import java.io.Serializable;

/**
 * Created by ljam763 on 8/06/2017.
 */
public class Friend implements Serializable {

    private String username;
    private String friendusername;
    private String friendProfilePicture;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriendusername() {
        return friendusername;
    }

    public void setFriendusername(String friendusername) {
        this.friendusername = friendusername;
    }

    public String getFriendProfilePicture() {
        return friendProfilePicture;
    }

    public void setFriendProfilePicture(String friendProfilePicture) {
        this.friendProfilePicture = friendProfilePicture;
    }
}
