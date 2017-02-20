/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.TablePeter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dfcastellanosc
 */
@Stateless
public class TablePeterFacade extends AbstractFacade<TablePeter> {

    @PersistenceContext(unitName = "SpeeddPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TablePeterFacade() {
        super(TablePeter.class);
    }
    
}
