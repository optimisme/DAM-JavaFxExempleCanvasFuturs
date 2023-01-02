import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

// Codi executat pels processos paral·lels
// en una clase a part, per organitzar el codi
public class Futures {

    // Definir atributs
    private boolean running = true;
    private boolean paused = false;
    private static ArrayList<CompletableFuture<Void>> threadsList = new ArrayList<>();

    // Getters i setters
    public boolean  getPaused()                 { return paused; }
    public void     setPaused(boolean paused)   { this.paused = paused; }
    public boolean  getRunning()                { return running; }
    public void     setRunning(boolean running) { this.running = running; }

    // Inicia els processos paral·lels
    public void start () {
        running = true;
        threadsList.add(CompletableFuture.runAsync(getRunnableRellotge()));
        threadsList.add(CompletableFuture.runAsync(getRunnableLogic()));
    }

    // Atura els processos paral·lels (quan acaben el bucle)
    public void stop () {
        System.out.println("Esperant threads ...");
        if (running) {
            running = false;
            threadsList.forEach(CompletableFuture::join); 
            threadsList.clear();
            System.out.println("Threads acabats");
        }
    }

    // Procés paral·lel que actualitza la hora cada 0,5 segons
    Runnable getRunnableRellotge () {
        return new Runnable () {
            @Override public void run () {
                while (running) {
                    if (paused) {
                        // Prevent pause deadlock
                        try { Thread.sleep(10); } 
                        catch (InterruptedException e) { e.printStackTrace(); }
                        continue;
                    };

                    LocalDateTime now = LocalDateTime.now();
                    Main.rellotge.setHores(now.getHour());
                    Main.rellotge.setMinuts(now.getMinute());
                    Main.rellotge.setSegons(now.getSecond());

                    try { Thread.sleep(500); } 
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
        };
    }

    // Procés paral·lel que calcula la lògica del joc
    // (canvia el destí del cotxe cada 1,5 segons)
    Runnable getRunnableLogic () {
        return new Runnable () {
            @Override public void run () {
                while (running) {
                    if (paused) {
                        // Prevent pause deadlock
                        try { Thread.sleep(10); } 
                        catch (InterruptedException e) { e.printStackTrace(); }
                        continue;
                    };

                    Main.cotxe.destiX = (int) (Math.random() * 300);

                    try { Thread.sleep(1500); } 
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
        };
    }
}
