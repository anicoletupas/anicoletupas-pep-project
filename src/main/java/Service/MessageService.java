package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.*;

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

    public Message deleteMessageByID(int id) 
    {
        return messageDAO.deleteMessageByID(id);
    }

    public Message updateMessageByID(Message message, int id) 
    {
        messageDAO.updateMessageByID(message, id);
        return message;

    }
}
