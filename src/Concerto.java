
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Concerto extends Evento {
    private LocalTime ora;
    private double prezzo;
    
    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, double prezzo) {
        super(titolo, data, postiTotali);
        this.ora = ora;
        checkPrezzo(prezzo);
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        checkPrezzo(prezzo);
    }

    // Metodi di servizio
    private double checkPrezzo(double prezzo){
        if (prezzo > 00.00) {
            return this.prezzo = prezzo;
        } else {
            throw new IllegalArgumentException("Il prezzo non può essere meno di 00,00 €.");
        }
    }

    // Metodo per formattare data e ora
    public String getDataOraFormattata(){
        return getDataFormattata() + " " + ora.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    // Metodo per formattare il prezzo
    public String getPrezzoFormattato(){
        NumberFormat formatoEur = NumberFormat.getCurrencyInstance(Locale.ITALY);
        String prezzoFormattato = formatoEur.format(prezzo);
        return prezzoFormattato;
    }

    @Override
    public String toString() {
        return getDataOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato();
    }
    
}
