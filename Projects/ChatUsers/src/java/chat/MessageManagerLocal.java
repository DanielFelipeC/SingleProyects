/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author dfcastellanosc
 */
@Local
public interface MessageManagerLocal {
    
    void sendMessage(Message msg);
 
    Message getFirstAfter(Date after);
    
}
