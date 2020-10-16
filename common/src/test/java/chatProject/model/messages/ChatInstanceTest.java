package chatProject.model.messages;

import chatProject.model.user.Status;
import chatProject.model.user.UserAccount;
import chatProject.model.user.UserInfo;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ChatInstanceTest {
    @Test
    public void getCurentChatrooms(){
        UserInfo userInfo = new UserInfo(new UserAccount(0, "Username"), Status.ACTIVE);

        final ArrayList<Message<String>> messages = new ArrayList<>();
        messages.add(new Message<>(0, userInfo, "Message"));

        final ArrayList<Chatroom<String>> chatrooms = new ArrayList<>();
        chatrooms.add(new Chatroom<>("Chatroom1", userInfo, messages));

        final HashMap<UserInfo, LocalTime> users = new HashMap<>();

        final ChatInstance<String> chatInstance = new ChatInstance<>(chatrooms, users);

        assertEquals("The chatrooms should be the same set in the constructor", chatrooms, chatInstance.getCurentChatrooms());
    }

    @Test
    public void getUsers(){
        UserInfo userInfo = new UserInfo(new UserAccount(0, "Username"), Status.ACTIVE);

        final ArrayList<Message<String>> messages = new ArrayList<>();
        messages.add(new Message<>(0, userInfo, "Message"));

        final ArrayList<Chatroom<String>> chatrooms = new ArrayList<>();
        chatrooms.add(new Chatroom<>("Chatroom1", userInfo, messages));

        final HashMap<UserInfo, LocalTime> users = new HashMap<>();
        users.put(userInfo, LocalTime.now());
        users.put(new UserInfo(new UserAccount(1, "Other"), Status.ACTIVE), LocalTime.now());

        final ChatInstance<String> chatInstance = new ChatInstance<>(chatrooms, users);

        assertEquals("The users should be the same set in the constructor", users, chatInstance.getUsers());
    }

    @Test
    public void addChatroom(){
        UserInfo userInfo = new UserInfo(new UserAccount(0, "Username"), Status.ACTIVE);

        final ArrayList<Message<String>> messages = new ArrayList<>();
        messages.add(new Message<>(0, userInfo, "Message"));

        final ArrayList<Chatroom<String>> chatrooms = new ArrayList<>();

        final HashMap<UserInfo, LocalTime> users = new HashMap<>();
        users.put(userInfo, LocalTime.now());
        users.put(new UserInfo(new UserAccount(1, "Other"), Status.ACTIVE), LocalTime.now());

        final ChatInstance<String> chatInstance = new ChatInstance<>(chatrooms, users);

        assertEquals("The chatrooms should be the same set in the constructor", chatrooms, chatInstance.getCurentChatrooms());

        int firstChatroom = chatInstance.addChatroom(new Chatroom<>("Chatroom1", userInfo, messages));
        int secondChatroom = chatInstance.addChatroom(new Chatroom<>("Chatroom2", null, new ArrayList<>()));

        assertEquals("Two chatrooms have been added to the list", 2, chatInstance.getCurentChatrooms().size());
        assertEquals("The first chatroom is find into the list", "Chatroom1", chatInstance.getCurentChatrooms().get(firstChatroom).getName());
        assertEquals("The second chatroom is find into the list", "Chatroom2", chatInstance.getCurentChatrooms().get(secondChatroom).getName());

    }

    @Test
    public void addUser(){
        UserAccount userAccount = new UserAccount(0, "Username");
        UserInfo userInfo = new UserInfo(userAccount, Status.ACTIVE);

        final ArrayList<Message<String>> messages = new ArrayList<>();
        messages.add(new Message<>(0, userInfo, "Message"));

        final ArrayList<Chatroom<String>> chatrooms = new ArrayList<>();
        chatrooms.add(new Chatroom<>("Chatroom1", userInfo, messages));

        final HashMap<UserInfo, LocalTime> users = new HashMap<>();

        final ChatInstance<String> chatInstance = new ChatInstance<>(chatrooms, users);

        assertEquals("The users should be the same set in the constructor", users, chatInstance.getUsers());

        boolean isAddFirst = chatInstance.addUser(userInfo);
        boolean isAddSecond = chatInstance.addUser(userInfo);
        boolean isAddThird = chatInstance.addUser(new UserInfo(new UserAccount(1, "Other"), Status.ACTIVE));

        assertEquals("Two users have been added to the list", 2, chatInstance.getUsers().size());
        assertFalse("This user is already present in the list of user", isAddSecond);
        assertTrue("This user has been added to the list", isAddFirst);
        assertTrue("This user has been added to the list", isAddThird);
    }

    @Test
    public void initEmptyChat(){
        ChatInstance<String> chatInstance = ChatInstance.initEmptyChat();
        assertEquals(new ArrayList<>(), chatInstance.getCurentChatrooms());
        assertEquals(new HashMap<>(), chatInstance.getUsers());
    }
}
