/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertad6.jpacontroller;

import com.libertad6.jpa.ControlProducto2;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author juan.cuello
 */
@Stateless
public class ControlProducto2Facade extends AbstractFacade<ControlProducto2> {

    @PersistenceContext(unitName = "libertad6concb3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControlProducto2Facade() {
        super(ControlProducto2.class);
    }
    
}
