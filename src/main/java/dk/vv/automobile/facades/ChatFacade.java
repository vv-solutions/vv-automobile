package dk.vv.automobile.facades;

import dk.vv.automobile.dtos.ChatDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatFacade {
    public ChatDTO askQuestion(String question) throws IOException {
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
        return new ChatDTO(answer);
    }
}
