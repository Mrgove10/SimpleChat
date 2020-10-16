package chatProject.model.messages;

import chatProject.model.user.Status;
import chatProject.model.user.UserAccount;
import chatProject.model.user.UserInfo;
import org.junit.Test;


import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void getId() {
        int id = 42;
        final Message<Object> message = new Message<>(id, null, null);

        assertEquals("The message ID is the one set in the constructor",
                id, message.getId());
    }

    @Test
    public void getMessage() {
        String content = "TEST";
        final Message<String> message = new Message<>(0, null, content);

        assertEquals("The message content is the one set in the constructor",
                content, message.getMessage());
    }

    @Test
    public void getSender(){
        UserAccount userAccount = new UserAccount(0, "Test");
        UserInfo userInfo = new UserInfo(userAccount, Status.ACTIVE);
        final Message<String> message = new Message<>(1, userInfo, "This is my message");

        assertEquals("The sender of the message is the one set in the constructor", userInfo.getAccount(), message.getSender().getAccount());
        assertEquals("The sender of the message is the one set in the constructor", userInfo.getCurrentStatus(), message.getSender().getCurrentStatus());
    }

    @Test
    public void testToString() {

        UserAccount userAccount = new UserAccount(0, "Test");
        UserInfo userInfo = new UserInfo(userAccount, Status.ACTIVE);
        final Message<String> message = new Message<>(1, userInfo, "This is my message");

        String toString = "Message{" +
                            "id=" + 1 +
                            ", sender=" + "Test(active)" +
                            ", content=" + "This is my message" +
                            '}';

        assertEquals("The toString() method of a Message should print the Content, the id and the sender",
                toString, message.toString());
    }

}