
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        //* Step 2-1
        // TODO fare un metodo per creare l'evento
        // Chiedo all'utente di inserire un nuovo evento con tutti i parametri
        System.out.println("Benvenuto, inserisci un nuovo evento:");

        System.out.print("Titolo: ");
        Scanner scan = new Scanner(System.in);
        String titolo = scan.nextLine();
    
        LocalDate data = null;
        try {
            System.out.print("Data evento (dd/MM/yyy): ");
            // Scanner scanData = new Scanner(System.in);
            String dataInput = scan.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            data = LocalDate.parse(dataInput, formatter);
        } catch (IllegalArgumentException s) {
            System.err.println(s);
        }

        int postiTotali = 0;
        try {
            System.out.print("Posti totali evento: ");
            // Scanner scanPostiTotali = new Scanner(System.in);
            postiTotali = scan.nextInt();
        } catch (IllegalArgumentException s) {
            System.err.println(s);
        }

        // creazione evento - istanziazione
        Evento evento1 = new Evento(titolo, data, postiTotali);
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

    public static void creaEvento(Evento evento){
        
    }

    //* Step 2-3/6
    // Stampare a video il numero di posti prenotati e quelli disponibili
    public static void stampaRiepilogo(Evento evento){
        System.out.println(evento.toString());
        System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
        System.out.println("Posti disponibili: " + evento.getPostiDisponibili());  
    }
}
