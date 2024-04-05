package medidas;

import misc.Tarea;

public class NumErrores extends Medida {
	
	private int nErrores;

    public NumErrores(String id, String nombre, Tarea tarea, double optimo,
    			   double objetivo, double peorAceptable) {
        super(id, nombre, tarea, optimo, objetivo, peorAceptable);
        this.nErrores = 0;
    }

    public void addError () {
    	nErrores++;
    }
    
    public int getErrores () {
    	return nErrores;
    }
}
