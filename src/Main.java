
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        //* Step 2-1
        // Chiedo all'utente di inserire un nuovo evento con tutti i parametri
        System.out.println("Benvenuto, inserisci un nuovo evento:");

        System.out.println("Titolo:");
        Scanner scan = new Scanner(System.in);
        String titolo = scan.nextLine();
    
        LocalDate data = null;
        try {
            System.out.println("Data evento: (dd/MM/yyy)");
            // Scanner scanData = new Scanner(System.in);
            String dataInput = scan.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            data = LocalDate.parse(dataInput, formatter);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }

        int postiTotali = 0;
        try {
            System.out.println("Posti totali evento:");
            // Scanner scanPostiTotali = new Scanner(System.in);
            postiTotali = scan.nextInt();
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }

        // creazione evento - istanziazione
        Evento evento1 = new Evento(titolo, data, postiTotali);

        //* Step 2-2
        // chiedere all’utente se e quante prenotazioni vuole fare e provare ad effettuarle, implementando opportuni controlli
        // Ripetere il ciclo di richiesta input
        System.out.println("Vuoi fare delle prenotazioni? (Y/n)");
        Scanner scanInserimento = new Scanner(System.in);
        String sceltaInserimento = scanInserimento.nextLine();
        
        if(sceltaInserimento.equalsIgnoreCase("y")){
            try {
                System.out.println("Inserisci il numero di posti che vuoi prenotare:");
                Scanner scanNumPrenotazioni = new Scanner(System.in);
                int numPrenotazioni = scanNumPrenotazioni.nextInt();
    
                // ciclo for che richiama il metodo per il numero di volte inserito dall'utente
                for(int i = 0; i < numPrenotazioni; i++){
                    evento1.prenota();
                }
            } catch (IllegalArgumentException e){
                System.err.println(e);
            }
            
            stampaRiepilogo(evento1);
        } else {
            stampaRiepilogo(evento1);
            return;
        }

        //* Step 2-4, 2-5
        // Chiedere all’utente se e quanti posti vuole disdire
        System.out.println("Vuoi disdire delle prenotazioni? (Y/n)");
        Scanner scanCancellazione = new Scanner(System.in);
        String sceltaCancellazione = scanCancellazione.nextLine();

        if(sceltaCancellazione.equalsIgnoreCase("y")){
            try {
                System.out.println("Inserisci il numero di posti che vuoi disdire:");
                Scanner scanNumCancellazioni = new Scanner(System.in);
                int numCancellazioni = scanNumCancellazioni.nextInt();
    
                // ciclo for che richiama il metodo per il numero di volte inserito dall'utente
                for(int i = 0; i < numCancellazioni; i++){
                    evento1.disdici();
                }
            } catch (IllegalArgumentException e){
                System.err.println(e);
            }

            stampaRiepilogo(evento1);
        } else {
            stampaRiepilogo(evento1);
            return;
        }
    }

    //* Step 2-3
    // Stampare a video il numero di posti prenotati e quelli disponibili
    public static void stampaRiepilogo(Evento evento){
        System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
        System.out.println("Posti disponibili: " + evento.getPostiDisponibili());
        System.out.println(evento.toString());
    }
}
