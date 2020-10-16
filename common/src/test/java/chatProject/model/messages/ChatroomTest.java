package chatProject.model.messages;

import chatProject.model.user.Status;
import chatProject.model.user.UserAccount;
import chatProject.model.user.UserInfo;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ChatroomTest {
    @Test
    public void getName(){
        final Chatroom<String> chatroom = new Chatroom<>("Name",
                                                                new UserInfo(
                                                                        new UserAccount(0,"Username"),
                                                                        Status.ACTIVE),
                                                                new ArrayList<>());

        assertEquals("The name should be the one set in the constructor", "Name", chatroom.getName());
    }

    @Test
    public void getOwner(){
        final UserInfo userInfo = new UserInfo(new UserAccount(0,"Username"),Status.ACTIVE);
        final Chatroom<String> chatroom = new Chatroom<>("Name",
                                                                userInfo,
                                                                new ArrayList<>());
        assertEquals("The owner should be the same set in the constructor", userInfo, chatroom.getOwner());

    }

    @Test
    public void getCurrentMessages(){
        final UserInfo userInfo = new UserInfo(new UserAccount(0,"Username"),Status.ACTIVE);

        final ArrayList<Message<String>> messages = new ArrayList<>();
        messages.add(new Message<>(0, userInfo, "First message"));
        messages.add(new Message<>(1, userInfo, "Second message"));
        messages.add(new Message<>(2, new UserInfo(new UserAccount(1, "OtherUser"), Status.ACTIVE), "Third message"));

        final Chatroom<String> chatroom = new Chatroom<>("Name", userInfo, messages);
        assertEquals("The messages should be the same set in the constructor", messages, chatroom.getCurrentMessages());
    }

    @Test
    public void addMessage(){
        final UserInfo userInfo = new UserInfo(new UserAccount(0,"Username"),Status.ACTIVE);
        final Chatroom<String> chatroom = new Chatroom<>("Name",
                userInfo,
                new ArrayList<>());
        assertEquals("The messages should be the same set in the constructor", new ArrayList<>(), chatroom.getCurrentMessages());

        final ArrayList<Message<String>> messages = new ArrayList<>();
        messages.add(chatroom.addMessage(userInfo, "First Message"));
        messages.add(chatroom.addMessage(new Message<>(5, userInfo, "Second Message")));

        assertEquals("Two messages have been added to the list", 2, chatroom.getCurrentMessages().size());
        assertEquals("Two messages have been added to the list", messages, chatroom.getCurrentMessages());
    }

    @Test
    public void testToString_OwnerNull() {
        final Chatroom<String> chatroom = new Chatroom<>("Name", null, new ArrayList<>());

        assertEquals("The toString() method of a Chatroom should print the name",
                "Name", chatroom.toString());
    }

    @Test
    public void testToString_OwnerNotNull() {
        final UserInfo userInfo = new UserInfo(new UserAccount(0,"Username"),Status.ACTIVE);
        final Chatroom<String> chatroom = new Chatroom<>("Name", userInfo, new ArrayList<>());

        assertEquals("The toString() method of a Chatroom should print the name and the owner",
                "Name (Username)", chatroom.toString());
    }
}
