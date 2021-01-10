package clases;

public class Rubro {

	private String nombre;
	private double[] gastosporMes;

	public Rubro(String nombre) {
		this.nombre = nombre;
		this.gastosporMes = new double[Mes.values().length];// inicializar arreglo
		inicializarGastos();
	}

	private void inicializarGastos() {

		for (int i = 0; i < gastosporMes.length; i++) {
			double gasto = 0;
			this.gastosporMes[i] = gasto;

		}
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarGasto(Mes mes, double importe) {
		this.gastosporMes[mes.ordinal()] +=importe;
	}
	public boolean comparaNombre(String nombre) {
		return this.nombre.equals(nombre);
	}

	public double getTotalGastos(Mes mes) {
		
		return this.gastosporMes[mes.ordinal()];
		
		}
	}


