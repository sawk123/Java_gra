package Gierka;


import input.klawiatura;
import objects.gracz;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Okno extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;
    private Thread thread;
    private boolean running = false;

    private klawiatura Klistener = new klawiatura(this);

    public gracz Gracz = new gracz(100,100,64,64);

    public Okno(String Title){
        JFrame ramka = new JFrame(Title);
        ramka.setVisible(true);
        ramka.setResizable(true);
        ramka.add(this);
        ramka.setSize(800,600);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start(){
        running = true;
        thread = new Thread(this);
        thread.start();

    }

    public void stop() {
        running = false;
        try {
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while (running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1)
            {
                tick();
                updates++;
                delta--;
            }
            Render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

        stop();
    }

    public void tick(){
        Gracz.tick();

    }
    public void Render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,this.getWidth(), this.getHeight());

        Gracz.Render(g);

        bs.show();
        g.dispose();

    }


}
