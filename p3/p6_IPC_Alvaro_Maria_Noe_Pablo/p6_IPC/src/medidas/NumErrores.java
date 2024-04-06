package medidas;

import misc.Tarea;

public class NumErrores extends Medida {
	
    public NumErrores(String id, String nombre, Tarea tarea, double optimo,
    			   double objetivo, double peorAceptable) {
        super(id, nombre, tarea, optimo, objetivo, peorAceptable);
        this.setMedida(0);
    }

    public void addError () {
    	this.setMedida(this.getMedida() + 1);
    }
    
}
