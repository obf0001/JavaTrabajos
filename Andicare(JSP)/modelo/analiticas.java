
package modelo;

import java.sql.Date;

public class analiticas {
    private int glucosaPromedio;
    private int promVistasDias;
    private int porcObjetivo;
    private int sensorTiempoPorcAct;
    private int sensorDuracionPromedioGlucosaBaja;
    private int porcDebajoObjetivo;
    private int porcDebajoEventosHipoglucemia;
    private int porcEncimaEventosHiperglucemia;
    private float coeficienteVariacion;
    private int eventosGlucosaBaja;
    private float promGlucosaBajaDia;
    private float indGestionGlucosa;
    private String nuhsa;
    private Date fechaAnalitica;
    private String tecnicoImplicado;

    public analiticas(int glucosaPromedio, int promVistasDias, int porcObjetivo, int sensorTiempoPorcAct,
                      int sensorDuracionPromedioGlucosaBaja, int porcDebajoObjetivo,
                      int porcDebajoEventosHipoglucemia, int porcEncimaEventosHiperglucemia,
                      float coeficienteVariacion, int eventosGlucosaBaja, float promGlucosaBajaDia,
                      float indGestionGlucosa, String nuhsa, Date fechaAnalitica, String tecnicoImplicado) {
        
        this.glucosaPromedio = glucosaPromedio;
        this.promVistasDias = promVistasDias;
        this.porcObjetivo = porcObjetivo;
        this.sensorTiempoPorcAct = sensorTiempoPorcAct;
        this.sensorDuracionPromedioGlucosaBaja = sensorDuracionPromedioGlucosaBaja;
        this.porcDebajoObjetivo = porcDebajoObjetivo;
        this.porcDebajoEventosHipoglucemia = porcDebajoEventosHipoglucemia;
        this.porcEncimaEventosHiperglucemia = porcEncimaEventosHiperglucemia;
        this.coeficienteVariacion = coeficienteVariacion;
        this.eventosGlucosaBaja = eventosGlucosaBaja;
        this.promGlucosaBajaDia = promGlucosaBajaDia;
        this.indGestionGlucosa = indGestionGlucosa;
        this.nuhsa = nuhsa;
        this.fechaAnalitica = fechaAnalitica;
        this.tecnicoImplicado = tecnicoImplicado;
    }

    public int getGlucosaPromedio() {
        return glucosaPromedio;
    }

    public int getPromVistasDias() {
        return promVistasDias;
    }

    public int getPorcObjetivo() {
        return porcObjetivo;
    }

    public int getSensorTiempoPorcAct() {
        return sensorTiempoPorcAct;
    }

    public int getSensorDuracionPromedioGlucosaBaja() {
        return sensorDuracionPromedioGlucosaBaja;
    }

    public int getPorcDebajoObjetivo() {
        return porcDebajoObjetivo;
    }

    public int getPorcDebajoEventosHipoglucemia() {
        return porcDebajoEventosHipoglucemia;
    }

    public int getPorcEncimaEventosHiperglucemia() {
        return porcEncimaEventosHiperglucemia;
    }

    public float getCoeficienteVariacion() {
        return coeficienteVariacion;
    }

    public int getEventosGlucosaBaja() {
        return eventosGlucosaBaja;
    }

    public float getPromGlucosaBajaDia() {
        return promGlucosaBajaDia;
    }

    public float getIndGestionGlucosa() {
        return indGestionGlucosa;
    }

    public String getNuhsa() {
        return nuhsa;
    }

    public Date getFechaAnalitica() {
        return fechaAnalitica;
    }

    public String getTecnicoImplicado() {
        return tecnicoImplicado;
    }

    
    public void setGlucosaPromedio(int glucosaPromedio) {
        this.glucosaPromedio = glucosaPromedio;
    }

    public void setPromVistasDias(int promVistasDias) {
        this.promVistasDias = promVistasDias;
    }

    public void setPorcObjetivo(int porcObjetivo) {
        this.porcObjetivo = porcObjetivo;
    }

    public void setSensorTiempoPorcAct(int sensorTiempoPorcAct) {
        this.sensorTiempoPorcAct = sensorTiempoPorcAct;
    }

    public void setSensorDuracionPromedioGlucosaBaja(int sensorDuracionPromedioGlucosaBaja) {
        this.sensorDuracionPromedioGlucosaBaja = sensorDuracionPromedioGlucosaBaja;
    }

    public void setPorcDebajoObjetivo(int porcDebajoObjetivo) {
        this.porcDebajoObjetivo = porcDebajoObjetivo;
    }

    public void setPorcDebajoEventosHipoglucemia(int porcDebajoEventosHipoglucemia) {
        this.porcDebajoEventosHipoglucemia = porcDebajoEventosHipoglucemia;
    }

    public void setPorcEncimaEventosHiperglucemia(int porcEncimaEventosHiperglucemia) {
        this.porcEncimaEventosHiperglucemia = porcEncimaEventosHiperglucemia;
    }

    public void setCoeficienteVariacion(float coeficienteVariacion) {
        this.coeficienteVariacion = coeficienteVariacion;
    }

    public void setEventosGlucosaBaja(int eventosGlucosaBaja) {
        this.eventosGlucosaBaja = eventosGlucosaBaja;
    }

    public void setPromGlucosaBajaDia(float promGlucosaBajaDia) {
        this.promGlucosaBajaDia = promGlucosaBajaDia;
    }

    public void setIndGestionGlucosa(float indGestionGlucosa) {
        this.indGestionGlucosa = indGestionGlucosa;
    }

    public void setNuhsa(String nuhsa) {
        this.nuhsa = nuhsa;
    }

    public void setFechaAnalitica(Date fechaAnalitica) {
        this.fechaAnalitica = fechaAnalitica;
    }

    public void setTecnicoImplicado(String tecnicoImplicado) {
        this.tecnicoImplicado = tecnicoImplicado;
    }
    
}
