package clases;

public class Test {

	public static void main(String[] args) {
		 
	
		GastosAnuales g = new GastosAnuales();
		g.agregarGasto(Mes.DICIEMBRE, "Cumple Papa", 24354);
		g.agregarGasto(Mes.DICIEMBRE, "Fiestas", 5000);
		g.agregarGasto(Mes.ENERO, "Fiestas", 10000);
		g.agregarGasto(Mes.FEBRERO, "Cumple Mama", 1000);
		g.agregarGasto(Mes.JUNIO, "Cumple isa", 5000);
		System.out.println(g.gastoAcumulado(Mes.DICIEMBRE));
		System.out.println(g.gastoAcumulado("Fiestas"));
		g.informarConsumoXmes();
		System.out.println(g.informarPromedioMensual());
	}

}
