package com.airvise.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@Service
public class ADSBReceiverService {

    private final Logger logger = LoggerFactory.getLogger(ADSBReceiverService.class);

    @PostConstruct
    public void retrieveDump1090Data() {
        logger.info("Trying to connect to socket.");
        try {
            Socket socket = new Socket("localhost", 30003);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            logger.info("Starting to retrieve data from socket {} with port {}", socket.getInetAddress(), socket.getPort());

            String line;
            while ((line = reader.readLine()) != null) {
                logger.info("Data package received: {}", line);
            }

            socket.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
