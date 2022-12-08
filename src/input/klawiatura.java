package input;



import Gierka.Okno;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class klawiatura implements KeyListener {

    private final Okno o;

    public klawiatura(Okno o){
        this.o = o;
        o.addKeyListener(this);

    }

    public void keyTyped(KeyEvent e) {

    }

    boolean kierunek_lewy = false;

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if ( key == KeyEvent.VK_D){
            o.Gracz.velx = 1;
            kierunek_lewy = false;
        }
        if ( key == KeyEvent.VK_A){
            o.Gracz.velx = -1;
            kierunek_lewy = true;
        }


    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D && !kierunek_lewy){
            o.Gracz.velx = 0;
        }
        if (key == KeyEvent.VK_A && kierunek_lewy){
            o.Gracz.velx = 0;
        }

    }
}
