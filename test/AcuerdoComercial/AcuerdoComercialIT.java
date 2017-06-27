/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuerdoComercial;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author juan.cuello
 * clase para testiar con Junit Acuerdo Comercial
 */
public class AcuerdoComercialIT {
    
    public AcuerdoComercialIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPROVEEDOR_ID method, of class AcuerdoComercial.
     */
    @Test
    public void testGetPROVEEDOR_ID() {
        System.out.println("getPROVEEDOR_ID");
        AcuerdoComercial instance = new AcuerdoComercial();
        instance.setPROVEEDOR_ID(4);
        int expResult = 4;
        int result = instance.getPROVEEDOR_ID();
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPROVEEDOR_ID method, of class AcuerdoComercial.
     */
    @Ignore
    public void testSetPROVEEDOR_ID() {
        System.out.println("setPROVEEDOR_ID");
        int PROVEEDOR_ID = 2;
        AcuerdoComercial instance = new AcuerdoComercial();
        instance.setPROVEEDOR_ID(PROVEEDOR_ID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        
    }

    /**
     * Test of getResultado method, of class AcuerdoComercial.
     */
    @Test
    public void testGetResultado() {
        System.out.println("getResultado");
        AcuerdoComercial instance = new AcuerdoComercial();
        String expResult = "paso bien";
        instance.setResultado("paso bien");
        String result = instance.getResultado();
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setResultado method, of class AcuerdoComercial.
     */
    @Ignore
    public void testSetResultado() {
        System.out.println("setResultado");
        String resultado = "paso bien";
        AcuerdoComercial instance = new AcuerdoComercial();
        instance.setResultado(resultado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFecha method, of class AcuerdoComercial.
     */
    @Ignore
    public void testGetFecha() {
        System.out.println("getFecha");
        AcuerdoComercial instance = new AcuerdoComercial();
        Date expResult = null;
        
        Date result = instance.getFecha();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFecha method, of class AcuerdoComercial.
     */
    @Ignore
    public void testSetFecha() {
        System.out.println("setFecha");
        Date fecha = null;
        AcuerdoComercial instance = new AcuerdoComercial();
        instance.setFecha(fecha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFile method, of class AcuerdoComercial.
     */
    @Ignore
    public void testGetFile() {
        System.out.println("getFile");
        AcuerdoComercial instance = new AcuerdoComercial();
        UploadedFile expResult = null;
        UploadedFile result = instance.getFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFile method, of class AcuerdoComercial.
     */
    @Ignore
    public void testSetFile() {
        System.out.println("setFile");
        UploadedFile file = null;
        AcuerdoComercial instance = new AcuerdoComercial();
        instance.setFile(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upload method, of class AcuerdoComercial.
     */
    @Ignore
    public void testUpload() {
        System.out.println("upload");
        AcuerdoComercial instance = new AcuerdoComercial();
        instance.upload();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
