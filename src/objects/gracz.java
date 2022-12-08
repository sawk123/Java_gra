package objects;

import java.awt.*;

public class gracz {

    public int x, y, width, height;
    public int velx, vely;

    public gracz(int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public void tick(){
        x+=velx;
        y+=vely;

    }

    public void Render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}
