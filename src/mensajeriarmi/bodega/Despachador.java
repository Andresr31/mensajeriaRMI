package mensajeriarmi.bodega;

import java.io.Serializable;
import java.util.ArrayList;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.paquete.Ubicacion;

/**
 *
 * @author Otro
 */
public class Despachador implements Serializable {

    private ArrayList<Paquete> paquetesEnvioTemp;
    private ArrayList<Double> distanciasTemp;

    public Despachador() {
        this.paquetesEnvioTemp = new ArrayList<>();
        this.distanciasTemp = new ArrayList<>();
    }

    public double calcularDistancia(double latPaquete,
            double latDestino, double lonPaquete,
            double lonDestino) {

        // The math module contains a function 
        // named toRadians which converts from 
        // degrees to radians. 
        lonPaquete = Math.toRadians(lonPaquete);
        lonDestino = Math.toRadians(lonDestino);
        latPaquete = Math.toRadians(latPaquete);
        latDestino = Math.toRadians(latDestino);

        // Haversine formula  
        double dlon = lonDestino - lonPaquete;
        double dlat = latDestino - latPaquete;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(latPaquete) * Math.cos(latDestino)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956  
        // for miles 
        double r = 6371;

        // calculate the result 
        return (c * r);
    }

    public void ordenarPaquetes() {
        for (int i = 1; i < this.paquetesEnvioTemp.size(); i++) {
            for (int j = this.paquetesEnvioTemp.size() - 1; j >= i; j--) {
                if (this.distanciasTemp.get(j) < this.distanciasTemp.get(j-1)) {
                    
                    Paquete temp = this.paquetesEnvioTemp.get(j);
                    this.paquetesEnvioTemp.set(j, this.paquetesEnvioTemp.get(j-1));
                    this.paquetesEnvioTemp.set(j-1, temp);
                    
                    double dtemp = this.distanciasTemp.get(j);
                    this.distanciasTemp.set(j, this.distanciasTemp.get(j-1));
                    this.distanciasTemp.set(j-1, dtemp);
                }
            }
        }
    }
    
    public void mostrarDistancias(){
        for (int i = 0; i < this.paquetesEnvioTemp.size(); i++) {
            Paquete p = this.paquetesEnvioTemp.get(i);
            double d = this.distanciasTemp.get(i);
            
            System.out.println(i+1+" "+p.getCiudadReceptor() +" : "+d);
        }
    }

    public Camion despachar(ArrayList<Paquete> paquetes, Ubicacion destino, Camion c) {
        for (Paquete paquete : paquetes) {
            Ubicacion ubicacionR = paquete.getUbicacionReceptor();
            double distancia = calcularDistancia(ubicacionR.getLatitud(), destino.getLatitud(),
                    ubicacionR.getLongitud(), destino.getLongitud());

            this.distanciasTemp.add(distancia);
            this.paquetesEnvioTemp.add(paquete);
        }

        this.ordenarPaquetes();
        mostrarDistancias();
        return this.cargarCamion(c);
    }
    
    
    public Camion cargarCamion(Camion c){
        for (Paquete paquete : paquetesEnvioTemp) {
            if(c.agregarPaquete(paquete)){
                System.out.println("Paquete agregado destino: "+paquete.getCiudadReceptor());  
            }  
        }
        return c;
    }

}
