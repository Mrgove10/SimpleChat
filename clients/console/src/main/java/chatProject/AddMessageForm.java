package chatProject;

import chatProject.model.user.UserInfo;

/**
 * The content of the form to add a new message.
 *
 * @param <T> the type of messages to use (probably String)
 */
public class AddMessageForm<T> {

    private final int chatroomId;
    private final UserInfo user;
    private final T content;

    public AddMessageForm(int chatroomId, UserInfo user, T content) {
        this.chatroomId = chatroomId;
        this.user = user;
        this.content = content;
    }

    /**
     * Gets the sender of the message.
     *
     * @return the user who sent the message
     */
    public UserInfo getUser() {
        return user;
    }

}