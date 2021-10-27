package it.javaproject.springwebapp.model;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class Ordine {
    private int codice;
    private Date data;
    private float prezzo;
    private List<Item> carrello;

    public Ordine() {
    }

    public Ordine(Date data, List<Item> carrello) {
        this.codice = generaCod();
        this.data = data;
        this.carrello = carrello;
        this.prezzo = calcolaPrezzo();
    }

    private int generaCod(){
        Random rand = new Random();

        // Obtain a number between [0 - 49].
        return rand.nextInt(50);
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
