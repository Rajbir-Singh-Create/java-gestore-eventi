
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    }

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
        postiDisponibili = postiTotali - postiPrenotati;
    }

    public int getPostiDisponibili(){
        return postiDisponibili;
    }

    //* metodi di servizio
    // Metodo di controllo che la data non sia nel passato
    private LocalDate checkDate(LocalDate data) {
        if(data.isAfter(LocalDate.now())){
            return this.data = data;
        } else {
            throw new IllegalArgumentException("Non puoi inserire una data nel passato.");
        }
    }

    // Metodo di controllo che l'input dei posti totali non sia minore di 0
    private int checkPostiTotali(int postiTotali) {
        if(postiTotali > 0){
            return this.postiTotali = postiTotali;
        } else {
            throw new IllegalArgumentException("Deve esserci almeno un posto.");
        }
    }

    // Metodo per prenotare posti
    public void prenota() {
        checkDataPrenotazioneEDisdetta();
        if (postiPrenotati >= postiTotali){
            throw new IllegalStateException("Non ci sono più posti disponibili.\n");
        } else {
            postiPrenotati++;
            setPostiDisponibili();
        }
    }

    // Metodo per disdire posti
    public void disdici() {
        checkDataPrenotazioneEDisdetta();
        if (postiPrenotati == 0){
            throw new IllegalStateException("Non ci sono posti prenotati.\n");
        } else {
            postiPrenotati--;
            setPostiDisponibili();
        }
    }

    // Metodo per controllare che la data dell'evento non sia già passata
    private void checkDataPrenotazioneEDisdetta(){
        if(data.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("L'evento è già passato.");
        }
    }

    // Metodo per formattare la data
    public String getDataFormattata(){
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    //* override metodo toString()
    @Override
    public String toString() {
        return getDataFormattata() + " - " + titolo;
    }

}
