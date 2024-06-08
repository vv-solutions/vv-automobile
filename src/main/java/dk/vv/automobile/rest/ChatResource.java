package dk.vv.automobile.rest;

import dk.vv.automobile.dtos.ChatDTO;
import dk.vv.automobile.errorhandling.ChatException;
import dk.vv.automobile.facades.ChatFacade;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.io.IOException;

@ApplicationScoped
@Resource
@Path("/chat/")
@Produces("application/json")
@Consumes("application/json")
public class ChatResource {

    private final ChatFacade chatFacade;

    @Inject
    public ChatResource(ChatFacade chatFacade) {

        this.chatFacade = chatFacade;
    }

    @GET
    public String test(){
        return "OK";
    }

    @POST
    public ChatDTO askQuestion(ChatDTO chatDTO) throws IOException, ChatException {
        return chatFacade.askQuestion(chatDTO.getMsg());
    }
}
