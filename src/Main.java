
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Step 2-1
        // Chiedo all'utente di inserire un nuovo evento con tutti i parametri
        // TODO: inserire un controllo per il titolo
        System.out.println("Benvenuto, inserisci un nuovo evento:");
        System.out.println("Titolo:");
        Scanner scanTitolo = new Scanner(System.in);
        String titolo = scanTitolo.nextLine();

        System.out.println("Data evento: (dd/MM/yyy)");
        Scanner scanData = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataInput = scanData.nextLine();
        LocalDate data = LocalDate.parse(dataInput, formatter);

        System.out.println("Posti totali evento:");
        Scanner scanPostiTotali = new Scanner(System.in);
        int postiTotali = scanPostiTotali.nextInt();

        // creazione evento - istanziazione
        Evento evento1 = new Evento(titolo, data, postiTotali);

        // Step 2-2
        // chiedere all’utente se e quante prenotazioni vuole fare e provare ad effettuarle, implementando opportuni controlli
        System.out.println("Vuoi fare delle prenotazioni? Y/n");
        Scanner scanInserimento = new Scanner(System.in);
        String sceltaInserimento = scanInserimento.nextLine();
        
        if(sceltaInserimento.equalsIgnoreCase("y")){
            // farne un metodo?
            System.out.println("Inserisci il numero di posti che vuoi prenotare:");
            Scanner scanNumPrenotazioni = new Scanner(System.in);
            int numPrenotazioni = scanNumPrenotazioni.nextInt();

            // ciclo for che richiama il metodo per il numero di volte inserito dall'utente
            for(int i = 0; i < numPrenotazioni; i++){
                evento1.prenota();
            }

            // Step 2-3
            // TODO: farne un metodo
            // Stampare a video il numero di posti prenotati e quelli disponibili
            System.out.println("Posti prenotati: " + evento1.getPostiPrenotati());
            System.out.println("Posti disponibili: " + evento1.getPostiDisponibili());
            System.out.println(evento1.toString());
        } else {
            // Step 2-3
            // Stampare a video il numero di posti prenotati e quelli disponibili
            System.out.println("Posti prenotati: " + evento1.getPostiPrenotati());
            System.out.println("Posti disponibili: " + evento1.getPostiDisponibili());
            System.out.println(evento1.toString());
            return;
        }

        // Step 2-4, 2-5
        // Chiedere all’utente se e quanti posti vuole disdire
        System.out.println("Vuoi disdire delle prenotazioni? Y/n");
        Scanner scanCancellazione = new Scanner(System.in);
        String sceltaCancellazione = scanCancellazione.nextLine();

        if(sceltaCancellazione.equalsIgnoreCase("y")){
            // farne un metodo?
            System.out.println("Inserisci il numero di posti che vuoi disdire:");
            Scanner scanNumCancellazioni = new Scanner(System.in);
            int numCancellazioni = scanNumCancellazioni.nextInt();

            // ciclo for che richiama il metodo per il numero di volte inserito dall'utente
            for(int i = 0; i < numCancellazioni; i++){
                evento1.disdici();
            }

            // Step 2-6
            // Stampare a video il numero di posti prenotati e quelli disponibili
            System.out.println("Posti prenotati: " + evento1.getPostiPrenotati());
            System.out.println("Posti disponibili: " + evento1.getPostiDisponibili());
            System.out.println(evento1.toString());
        } else {
            // Step 2-3
            // Stampare a video il numero di posti prenotati e quelli disponibili
            System.out.println("Posti prenotati: " + evento1.getPostiPrenotati());
            System.out.println("Posti disponibili: " + evento1.getPostiDisponibili());
            System.out.println(evento1.toString());
            return;
        }
    }
}
