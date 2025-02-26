
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Evento evento1 = creaEvento();
        System.out.print("\nEvento creato con successo: ");
        stampaRiepilogo(evento1);
        
        gestisciPrenotazioni(evento1);
        gestisciDisdette(evento1);
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
            // Formatto la data
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Converto la stringa della data in un oggetto di tipo LocalDate
            // e assegno il risultato alla variabile data
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
            // Formatto l'ora
            DateTimeFormatter formatterOra = DateTimeFormatter.ofPattern("HH:mm");
            // Converto la stringa dell'ora in un oggetto di tipo LocalTime
            // e lo assegno alla variabile ora
            ora = LocalTime.parse(oraInput, formatterOra);

            // Prendo il prezzo del biglietto
            double prezzo = 0;
            try {
                System.out.print("Prezzo del biglietto: ");
                prezzo = scan.nextDouble();
            } catch (IllegalArgumentException e) {
                System.err.println(e);
            }

            // creazione evento di tipo Concerto - istanziazione
            return new Concerto(titolo, data, postiTotali, ora, prezzo);
        } else {
            // creazione evento di tipo Evento - istanziazione
            return new Evento(titolo, data, postiTotali);
        }   
    }

    //* Step 2-2
    // chiedere all’utente se e quante prenotazioni vuole fare
    // e provare ad effettuarle, implementando opportuni controlli
    public static void gestisciPrenotazioni(Evento evento){
        while (evento.getPostiDisponibili() > 0) {
            System.out.print("\nVuoi fare delle prenotazioni? (Y/n) ");
            Scanner scanPrenotazioni = new Scanner(System.in);
            String sceltaInserimento = scanPrenotazioni.nextLine();
        
            if(sceltaInserimento.equalsIgnoreCase("y")){
                System.out.print("Inserisci il numero di posti che vuoi prenotare: ");
                int numPrenotazioni = scanPrenotazioni.nextInt();
                try {
                    // ciclo for che richiama il metodo per il numero di volte inserito dall'utente
                    for(int i = 0; i < numPrenotazioni; i++){
                        evento.prenota();
                    }
                } catch (IllegalStateException s){
                    System.err.println(s);
                }
                stampaRiepilogo(evento);
            } else {
                stampaRiepilogo(evento);
                break;
            }
        }
    }

    //* Step 2-4, 2-5
    // Chiedere all’utente se e quanti posti vuole disdire
    // e provare ad effettuare le disdette, implementando opportuni controlli
    public static void gestisciDisdette(Evento evento) {
        while (evento.getPostiPrenotati() > 0) {
            System.out.print("\nVuoi disdire delle prenotazioni? (Y/n) ");
            Scanner scanCancellazione = new Scanner(System.in);
            String sceltaCancellazione = scanCancellazione.nextLine();
    
            if(sceltaCancellazione.equalsIgnoreCase("y")){                
                System.out.print("Inserisci il numero di posti che vuoi disdire: ");
                int numCancellazioni = scanCancellazione.nextInt();
                try {
                    // ciclo for che richiama il metodo per il numero di volte inserito dall'utente
                    for(int i = 0; i < numCancellazioni; i++){
                        evento.disdici();
                    }
                } catch (IllegalStateException s){
                    System.err.println(s);
                }
                stampaRiepilogo(evento);
            } else {
                stampaRiepilogo(evento);
                break;
            }
        }
    }

    //* Step 2-3/6
    // Stampa a video un riepilogo dell'evento con il numero di posti prenotati e quelli disponibili
    public static void stampaRiepilogo(Evento evento){
        System.out.println();
        System.out.println(evento.toString());
        System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
        System.out.println("Posti disponibili: " + evento.getPostiDisponibili());  
    }
}
