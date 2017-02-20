/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.UsuariosLinkinCRM;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dfcastellanosc
 */
@Stateless
public class UsuariosLinkinCRMFacade extends AbstractFacade<UsuariosLinkinCRM> {

    @PersistenceContext(unitName = "JaspertReportFinishPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosLinkinCRMFacade() {
        super(UsuariosLinkinCRM.class);
    }
    
}
