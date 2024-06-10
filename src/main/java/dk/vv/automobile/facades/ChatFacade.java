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

        StringBuilder answer = new StringBuilder("");
        String line;
        while ((line = stdInput.readLine()) != null) {
            answer.append(line).append("\n");
        }

        if(answer.toString().contains("error:")){
            logger.errorf("chat failed with message: [%s]",answer.toString().split("error:")[1]);
            throw new ChatException(answer.toString().split("error:")[1]);
        }

        return new ChatDTO(answer.toString().split("result:")[1]);
    }
}
