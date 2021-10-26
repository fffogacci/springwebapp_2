package it.javaproject.springwebapp.model;

import java.util.Date;
import java.util.List;

public class Ordine {
    private int codice;
    private Date data;
    private float prezzo;
    private List<Item> carrello;

    public Ordine() {
    }

    public Ordine(int codice, Date data, List<Item> carrello) {
        this.codice = codice;
        this.data = data;
        this.carrello = carrello;
        this.prezzo = calcolaPrezzo();
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public List<Item> getCarrello() {
        return carrello;
    }

    public void setCarrello(List<Item> carrello) {
        this.carrello = carrello;
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "codice=" + codice +
                ", data=" + data +
                ", prezzo=" + prezzo +
                ", carrello=" + carrello +
                '}';
    }

    private float calcolaPrezzo(){
        float somma=0;
        for(Item tmp: carrello){
            somma+=tmp.getPrice();
        }
        return somma;
    }

}
