package Service;

import Model.Message;
import DAO.MessageDAO;

// put the dao in service
public class MessageService {
    private MessageDAO messageDAO;

    // constructor
    public MessageService()
    {
        messageDAO = new MessageDAO();
    }

    // new message needs to be created
    // retrieve all messages
    // retrieve message by id
    // delete message by id
    // retrieve message from particular user
}
