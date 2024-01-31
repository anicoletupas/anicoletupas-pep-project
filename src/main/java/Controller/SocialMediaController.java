package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::newMessageHandler);
        app.get("/messages", this::retrieveMessagesHandler);
        app.get("/messages/{message_id}", this::messageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::retrieveMessagesByIdHandler);

        return app;
    }

    /**
     * Registration of a new user account // done
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void registerHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.newAccount(account);
        if(addedAccount!=null){
            if((addedAccount.getUsername()).isEmpty() || (addedAccount.getPassword()).length() < 4)
                context.status(400);
            else
                context.status(200).json(mapper.writeValueAsString(addedAccount));
        }else{
            context.status(400);
        }
     }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account loginAccount = accountService.verifyAccount(account);
        
        context.status(200).json("sample");
        context.status(401); // unauthorized login error
     }

    /**
     * done
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void newMessageHandler (Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message newMessage = messageService.newMessage(message);
        if(newMessage!=null){
            if((newMessage.getMessage_text()).isEmpty() || (newMessage.getMessage_text()).length() > 255)
                context.status(400);
            else
                context.status(200).json(mapper.writeValueAsString(newMessage));
        }else{
            context.status(400);
        }
     }

    /**
     * This is an example handler for an example endpoint. // done
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void retrieveMessagesHandler(Context context) {
        List<Message> messages = messageService.getAllMessages();
        context.status(200).json(messages);
     }

    /**
     * This is an example handler for an example endpoint. // done
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void messageByIdHandler(Context context) {
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message retrievedMessage = messageService.getMessageByID(messageId);
        if(retrievedMessage != null)
            context.status(200).json(retrievedMessage);
        else
            context.status(200);
     }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void deleteMessageHandler(Context context) {
        // context.json("sample text");
        
        context.status(200).json("sample"); // always 200, can return empty message list
     }


    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void updateMessageHandler(Context context) {
        // context.json("sample text");
        
        context.status(200).json("sample"); // always 200, can return empty message list
     }

    /**
     * This is an example handler for an example endpoint. // done
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void retrieveMessagesByIdHandler(Context context) {
        int accountId = Integer.parseInt(context.pathParam("account_id"));
        List<Message> messages = messageService.getAllMessageByUser(accountId);
        context.status(200).json(messages);
     }
}