package p6_IPC;

public class Errores extends Medida {
	
	private int nErrores;

    public Errores(String id, String nombre, Tarea tarea, double optimo,
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
