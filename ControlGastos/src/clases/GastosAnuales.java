package clases;

import java.util.ArrayList;

public class GastosAnuales {

	private ArrayList<Rubro> rubros;

	// private double Matriz

	public GastosAnuales() {
		this.rubros = new ArrayList<Rubro>();
	}

	
	
	
	
	
	
	
	
	/**
	 * Muestro los consumos promedios por mes en cada rubro
	 *
	 * si el total del ruboro es tanto, el 100% es el gasto total anual dividido por
	 * el total de rubros
	 * 
	 * @return
	 * 
	 **/

	public void informarPromedioMensualXrubro() {
		double totalAnual = gastoTotalAnual();
		double[][] matriz = consolidadoDeGastos();

		for (int i = 0; i < matriz.length; i++) {
			Rubro r = rubros.get(i);
			System.out.printf(r.getNombre());
			System.out.println();
			for (int j = 0; j < matriz[i].length; j++) {
				Mes[] m = Mes.values();
				Mes mes = m[j];
				double total = matriz[i][j] = r.getTotalGastos(mes);
				double promedio = (total * 100) / totalAnual;
				if (promedio > 0) {
					System.out.println(mes);
					System.out.printf("%.2f", promedio+" porciento");
					System.out.println();
				}
			}

		}
		System.out.println();
	}

	public double gastoTotalAnual() {
		double totalAnual = 0;
		double[][] matriz = consolidadoDeGastos();
		for (int i = 0; i < matriz.length; i++) {
			Rubro r = rubros.get(i);
			for (int j = 0; j < matriz[i].length; j++) {
				Mes[] m = Mes.values();
				Mes mes = m[j];
				totalAnual += matriz[i][j] = r.getTotalGastos(mes);

			}
		}
		return totalAnual;
	}

	/**
	 * muestra los consumos por mes (Discriminando x cada rubro de gasto y
	 * acumulado)
	 */
	public void informarConsumoXmes() {

		double[][] matriz = consolidadoDeGastos();
		for (int i = 0; i < matriz.length; i++) {
			Rubro r = rubros.get(i);
			System.out.printf(r.getNombre());
			System.out.println();
			for (int j = 0; j < matriz[i].length; j++) {
				Mes[] m = Mes.values();
				Mes mes = m[j];
				double total = matriz[i][j] = r.getTotalGastos(mes);
				if (total > 0) {
					System.out.println(mes);
					System.out.print(matriz[i][j]);
					System.out.println();
				}

			}
			System.out.println();
		}

	}

	/**
	 * Genera un arrgelo bidimensional consolidando en una sola estructura todos los
	 * gastos del anio La matriz mide la cantidad de meses x ani x la cantidad de
	 * rubros existentes y cada celda contiene el importe acumulado para el rubro en
	 * ese mes
	 * 
	 * @return matriz
	 */

	private double[][] consolidadoDeGastos() {
		int col_r = rubros.size();
		int totalM = Mes.values().length;
		double[][] matriz = new double[col_r][totalM];

		for (int d1 = 0; d1 < matriz.length; d1++) { // 1er vector
			Rubro r = rubros.get(d1);
			for (int d2 = 0; d2 < matriz[d1].length; d2++) {
				Mes[] m = Mes.values();
				Mes mes = m[d2];
				matriz[d1][d2] = r.getTotalGastos(mes);
			}
		}

		return matriz;
	}

	public double gastoAcumulado(Mes mes) {
		double total = 0;

		double[][] matriz = consolidadoDeGastos();

		for (int d1 = 0; d1 < matriz.length; d1++) {

			total += matriz[d1][mes.ordinal()];
		}

		return total;
	}

	public void agregarGasto(Mes mes, String nombreRubro, double importe) {

		if (importe > 0) {
			Rubro r = obtenerRubro(nombreRubro);
			r.agregarGasto(mes, importe);

		} else {
			System.out.println("El monto debe ser mayor a cero, no se agrego el gasto");
		}

	}

	/**
	 * Devuelve el importe del gasto acumulado en el rubro indicado Si el rubro no
	 * existe devolvera -1
	 * 
	 * @param nombreRubro
	 * @return total
	 */
	public double gastoAcumulado(String nombreRubro) {
		double total = 0;
		double[][] matriz = consolidadoDeGastos();

		for (int i = 0; i < matriz.length; i++) {
			Rubro r = rubros.get(i);
			if (r.comparaNombre(nombreRubro)) {
				for (int j = 0; j < matriz[i].length; j++) {
					total += matriz[i][j];
				}

			}
		}

		return total;
	}

	private Rubro obtenerRubro(String nombreRubro) {
		Rubro r = buscarRubro(nombreRubro);
		if (r == null) {
			r = new Rubro(nombreRubro);
			rubros.add(r);
		}

		return r;
	}

	private Rubro buscarRubro(String nombreRubro) {
		Rubro r = null;
		Rubro encontrado = null;
		int indx = 0;
		boolean bandera = false;

		while (indx < this.rubros.size() && !bandera) {
			r = this.rubros.get(indx);
			encontrado = r.comparaNombre(nombreRubro) ? encontrado = r : encontrado;
			bandera = (encontrado != null) ? bandera = true : bandera;
			indx++;
		}
		return encontrado;
	}

}
