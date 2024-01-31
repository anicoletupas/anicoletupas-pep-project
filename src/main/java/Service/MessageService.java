package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.*;

// put the dao in service
public class MessageService {
    private MessageDAO messageDAO;

    // constructor
    public MessageService()
    {
        messageDAO = new MessageDAO();
    }

    public Message newMessage(Message message)
    {
        return messageDAO.newMessage(message);
    }

    public List<Message> getAllMessages()
    {
        return messageDAO.getAllMessages();
    }

    public List<Message> getAllMessageByUser(int postId)
    {
        return messageDAO.getAllMessageByUser(postId);
    }

    public Message getMessageByID(int id) 
    {
        return messageDAO.getMessageByID(id);
    }

    public void deleteMessageByID(int id) 
    {
        
    }

    public Message updateMessageByID(int id) 
    {
        return messageDAO.updateMessageByID(id);
    }

    // new message needs to be created
    // retrieve all messages
    // retrieve message by id
    // delete message by id
    // retrieve message from particular user
}
