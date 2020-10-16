package chatProject.model.user;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserInfoTest {
    @Test
    public void testGetAccount(){
        final UserAccount userAccount = new UserAccount(1, "User");

        final UserInfo userInfo = new UserInfo(userAccount, Status.ACTIVE);

        assertEquals("The user account should be the one set in the constructor", userInfo.getAccount(), userAccount);
    }

    @Test
    public void testGetCurrentStatus(){
        final UserAccount userAccount = new UserAccount(1, "User");

        final UserInfo userInfo = new UserInfo(userAccount, Status.ACTIVE);

        assertEquals("The status should be the one set in the constructor", userInfo.getCurrentStatus(), Status.ACTIVE);
    }

    @Test
    public void testSetCurrentStatus(){
        final UserAccount userAccount = new UserAccount(1, "User");

        final UserInfo userInfo = new UserInfo(userAccount, Status.INACTIVE);

        assertEquals("The status should be the one set in the constructor", userInfo.getCurrentStatus(), Status.INACTIVE);

        userInfo.setCurrentStatus(Status.ACTIVE);

        assertEquals("The status should be the new one", userInfo.getCurrentStatus(), Status.ACTIVE);
    }

    @Test
    public void testEquals() {

        final UserInfo user1 = new UserInfo(new UserAccount(1, "MyUser"), Status.ACTIVE);
        final UserInfo user2 = new UserInfo(new UserAccount(1, "MyUser"), Status.ACTIVE);

        assertEquals("2 users are equal if they have the same username",
                user1, user2);
    }

    @Test
    public void testHashCode() {

        final UserInfo user1 = new UserInfo(new UserAccount(1, "MyUser"), Status.ACTIVE);
        final UserInfo user2 = new UserInfo(new UserAccount(2, "MyUser"), Status.ACTIVE);

        assertEquals("2 accounts with the same username should have the same hashcode",
                user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testToString() {

        final UserInfo user = new UserInfo(new UserAccount(1, "MyUser"), Status.ACTIVE);
        assertEquals("The toString() method of a UserAccount should print the username",
                "MyUser(active)", user.toString());
    }
}
