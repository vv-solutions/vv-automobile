package dk.vv.automobile.facades;

import dk.vv.automobile.dtos.ChatDTO;
import dk.vv.automobile.errorhandling.ChatException;
import org.jboss.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ChatFacade {

    private  final Logger logger;

    public ChatFacade(Logger logger) {
        this.logger = logger;
    }

    public ChatDTO askQuestion(String question) throws IOException, ChatException {
        String[] command = {
                "python",
                "src/main/resources/python/chat.py",
                "--question=" + question
        };

        Process p = Runtime.getRuntime().exec(command);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        String answer = null;
        String line;
        while ((line = stdInput.readLine()) != null) {
            answer = line;
        }

        if(answer.contains("error:")){
            logger.errorf("chat failed with message: [%s]",answer.split("error:")[1]);
            throw new ChatException(answer.split("error:")[1]);
        }


        return new ChatDTO(answer.split("result:")[1]);
    }
}
