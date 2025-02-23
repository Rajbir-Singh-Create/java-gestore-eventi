
import java.time.LocalDate;

public class Evento {
    // Proprietà
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;
    private int postiDisponibili;
    
    // Quando si istanzia un nuovo evento questi attributi devono essere tutti valorizzati nel costruttore,
    // tranne posti prenotati che va inizializzato a 0.
    public Evento(String titolo, LocalDate data, int postiTotali) {
        this.titolo = titolo;
        checkDate(data);
        checkPostiTotali(postiTotali);
        this.postiPrenotati = 0;
        setPostiDisponibili();
    }

    //* getters e setters
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        checkDate(data);
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    public void setPostiDisponibili() {
        postiDisponibili = getPostiTotali() - getPostiPrenotati();
    }

    public int getPostiDisponibili(){
        // postiDisponibili = getPostiTotali() - getPostiPrenotati();
        return postiDisponibili;
    }

    //* Metodi di servizio
    // TODO: gestire le eccezioni
    public int prenota(){
        checkDate(data);
        if (getPostiDisponibili() > getPostiPrenotati()){
            postiPrenotati += 1;
            setPostiDisponibili();
            return postiPrenotati;
        } else {
            throw new IllegalArgumentException("Non ci sono più posti disponibili.");
        }
    }

    public int disdici(){
        checkDate(data);
        if (getPostiPrenotati() > 0){
            postiPrenotati -= 1;
            setPostiDisponibili();
            return postiPrenotati;
        } else {
            throw new IllegalArgumentException("Non ci sono posti prenotati.");
        }
    }

    // private int calcolaPostiDisponibili(){
    //     postiDisponibili = getPostiTotali() - getPostiPrenotati();
    //     return postiDisponibili;
    // }

    //* metodi di servizio per eseguire controlli
    private LocalDate checkDate(LocalDate data){
        if(data.isAfter(data.now())){
            return this.data = data;
        } else {
            throw new IllegalArgumentException("Non puoi inserire una data nel passato.");
        }
    }

    private int checkPostiTotali(int postiTotali){
        if(postiTotali > 0){
            return this.postiTotali = postiTotali;
        } else {
            throw new IllegalArgumentException("Deve esserci almeno un posto.");
        }
    }

    //* override metodo toString()
    // TODO: formattare correttamente la data
    @Override
    public String toString() {
        return getData() + " - " + getTitolo();
    }

}
