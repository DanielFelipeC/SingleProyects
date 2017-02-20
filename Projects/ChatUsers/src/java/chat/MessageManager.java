/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import javax.ejb.Singleton;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.Startup;


@Singleton
@Startup
public class MessageManager implements MessageManagerLocal {

    private final List messages = Collections.synchronizedList(new LinkedList());

    @Override
    public void sendMessage(Message msg) {
        messages.add(msg);
        msg.setDateSent(new Date());
    }

    @Override
    public Message getFirstAfter(Date after) {
        if (messages.isEmpty()) {
            return null;
        }
        if (after == null) {
            return (Message) messages.get(0);
        }
        for (Object o :  messages) {
            Message m = null;
            m = (Message) o;
            if (m.getDateSent().after(after)) {
                return m;
            }
        }
        return null;
    }

}
