/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import objects.DecoderMessage;
import objects.EncoderMessage;
import objects.Mensaje;

/**
 *
 * @author dfcastellanosc
 */
@ServerEndpoint(value = "/chat", encoders = {EncoderMessage.class}, decoders = {DecoderMessage.class})
public class Chat {

    private static Set<Session> conectados = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session ses) {
        conectados.add(ses);
    }

    @OnClose
    public void onClose(Session ses) {
        conectados.remove(ses);
    }

    @OnMessage
    public void onMessage(Mensaje message) throws IOException, EncodeException {
        if (conectados.size() < 3) {
            for (Session conectado : conectados) {
                conectado.getBasicRemote().sendObject(message);
                System.out.println("Imprime esto " + conectado.getPathParameters());
            }
        } else {
            
            System.out.println("No hay mas disponibles");
        }

    }

}
