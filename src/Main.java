
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Evento evento1 = creaEvento();
        evento1.setPostiDisponibili();
        System.out.print("Evento creato con successo: ");
        stampaRiepilogo(evento1);
        

        //* Step 2-2
        // chiedere all’utente se e quante prenotazioni vuole fare
        // e provare ad effettuarle, implementando opportuni controlli
        while (evento1.getPostiDisponibili() > 0) {
            System.out.print("Vuoi fare delle prenotazioni? (Y/n) ");
            Scanner scanPrenotazioni = new Scanner(System.in);
            String sceltaInserimento = scanPrenotazioni.nextLine();
        
            if(sceltaInserimento.equalsIgnoreCase("y")){
                try {
                    System.out.print("Inserisci il numero di posti che vuoi prenotare: ");
                    int numPrenotazioni = scanPrenotazioni.nextInt();
                    // ciclo for che richiama il metodo per il numero di volte inserito dall'utente
                    for(int i = 0; i < numPrenotazioni; i++){
                        evento1.prenota();
                    }
            } catch (IllegalArgumentException s){
                System.err.println(s);
            }
            stampaRiepilogo(evento1);
            } else {
                stampaRiepilogo(evento1);
                break;
            }
        }
        if (evento1.getPostiDisponibili() == 0){
            System.out.println("Non è più possibile effettuare altre prenotazioni.\n");
        }
        
        //* Step 2-4, 2-5
        // Chiedere all’utente se e quanti posti vuole disdire
        // e provare ad effettuare le disdette, implementando opportuni controlli
        while (evento1.getPostiPrenotati() > 0) {
            System.out.print("Vuoi disdire delle prenotazioni? (Y/n) ");
            Scanner scanCancellazione = new Scanner(System.in);
            String sceltaCancellazione = scanCancellazione.nextLine();
    
            if(sceltaCancellazione.equalsIgnoreCase("y")){
                try {
                    System.out.print("Inserisci il numero di posti che vuoi disdire: ");
                    int numCancellazioni = scanCancellazione.nextInt();
                    // ciclo for che richiama il metodo per il numero di volte inserito dall'utente
                    for(int i = 0; i < numCancellazioni; i++){
                        evento1.disdici();
                    }
                } catch (IllegalArgumentException s){
                    System.err.println(s);
                }
                stampaRiepilogo(evento1);
            } else {
                stampaRiepilogo(evento1);
                break;
            }
        }
        if (evento1.getPostiPrenotati() == 0){
            System.out.println("Non ci sono posti prenotati.\n");
        }
    }

    //* Step 2-1 metodo per la creazione di un evento
    public static Evento creaEvento(){
        // Chiedo all'utente di inserire un nuovo evento con tutti i parametri
        // in base alla scelta, verrà creato il tipo di evento adatto
        Scanner scan = new Scanner (System.in);
        System.out.print("Benvenuto, vuoi creare un evento generico o un concerto? (E/C): ");
        String scanScelta = scan.nextLine();

        // Prendo il titolo
        System.out.print("Titolo: ");
        String titolo = scan.nextLine();
    
        // Prendo la data
        LocalDate data = null;
        try {
            System.out.print("Data evento (dd/MM/yyyy): ");
            String dataInput = scan.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            data = LocalDate.parse(dataInput, formatter);
        } catch (IllegalArgumentException s) {
            System.err.println(s);
        }

        // Prendo i posti totali
        int postiTotali = 0;
        try {
            System.out.print("Posti totali evento: ");
            postiTotali = scan.nextInt();
        } catch (IllegalArgumentException s) {
            System.err.println(s);
        }

        // Se si tratta di un concerto
        if(scanScelta.equalsIgnoreCase("C")){
            // Prendo l'ora
            Scanner scanner = new Scanner (System.in);
            LocalTime ora = null;
            System.out.print("Ora evento (HH:mm): ");
            String oraInput = scanner.nextLine();
            DateTimeFormatter formatterOra = DateTimeFormatter.ofPattern("HH:mm");
            ora = LocalTime.parse(oraInput, formatterOra);

            // Prendo il prezzo del biglietto
            System.out.print("Prezzo del biglietto: ");
            double prezzo = scan.nextDouble();

            // creazione evento di tipo Concerto - istanziazione
            return new Concerto(titolo, data, postiTotali, ora, prezzo);
        } else {
            // creazione evento di tipo Evento - istanziazione
            return new Evento(titolo, data, postiTotali);
        }   
    }

    public static void gestisciPrenotazioni(){

    }

    //* Step 2-3/6
    // Stampa a video un riepilogo dell'evento con il numero di posti prenotati e quelli disponibili
    public static void stampaRiepilogo(Evento evento){
        System.out.println(evento.toString());
        System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
        System.out.println("Posti disponibili: " + evento.getPostiDisponibili());  
    }
}
