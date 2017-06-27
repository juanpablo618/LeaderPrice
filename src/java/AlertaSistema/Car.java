
package AlertaSistema;

import java.util.Date;

/**
 *
 * @author juan.cuello
 */

public class Car {
 
    private Integer cid;
    private String desarrollo;
    private Date color;
    private Date vencimiento;
    private String tipo;    
    private String fileName;
    public Car() {
    }
 
    //public Car(Integer cid, String cname, Date color, Date speed, String tipo, String fileName) {
    public Car(Integer cid, String desarrollo, Date color, Date vencimiento, String tipo, String fileName) {
        this.cid = cid;
        //this.cname = cname;
       this.desarrollo = desarrollo;
       
        this.color = color;
        this.vencimiento = vencimiento;
        this.tipo = tipo;
        this.fileName = fileName;
    }
 
    public Integer getCid() {
        return cid;
    }
 
    public void setCid(Integer cid) {
        this.cid = cid;
    }
 
    //public String getCname() {
      //  return cname;
    //}
 
    public String getDesarrollo() {
        return desarrollo;
    }
 
    
    //public void setCname(String cname) {
      //  this.cname = cname;
    //}
    
        public void setDesarrollo(String desarrollo) {
        this.desarrollo = desarrollo;
    }
 
    public Date getColor() {
        return color;
    }
 
    public void setColor(Date color) {
        this.color = color;
    }
 
    public Date getVencimiento() {
        return vencimiento;
    }
 
    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "DAto LEgal vencido: {" + "ID=" + cid + ", Desarrollo al que Pertenece=" + desarrollo + ", VENCIMIENTO=" + vencimiento + ", TIPO=" + tipo + ", NOMBRE DEL ARCHIVO=" + fileName + '}'+"\n";
    }
 
    
    
    
    
    
 
}