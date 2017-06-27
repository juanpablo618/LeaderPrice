/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

/**
 *
 * @author juan.cuello
 */
public class ApplicationProperties {
    
    String CarpetaDeDescargaYAnotificacion = "C:\\Users\\admin100\\Documents\\";
    //Si desea colocar varias personas, debe separarlas por ",". 
    String AlertaMailDlVencido = "juanpablo618@hotmail.com";

    public ApplicationProperties() {
    }

    public String getAlertaMailDlVencido() {
        return AlertaMailDlVencido;
    }

    public void setAlertaMailDlVencido(String AlertaMailDlVencido) {
        this.AlertaMailDlVencido = AlertaMailDlVencido;
    }

    public String getCarpetaDeDescargaYAnotificacion() {
        return CarpetaDeDescargaYAnotificacion;
    }

    public void setCarpetaDeDescargaYAnotificacion(String CarpetaDeDescargaYAnotificacion) {
        this.CarpetaDeDescargaYAnotificacion = CarpetaDeDescargaYAnotificacion;
    }
    
    
    
    
}
