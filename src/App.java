import processing.core.PApplet;

public class App extends PApplet {

    int redX, redY;
    int greenX, greenY;
    float redV, greenV;
    boolean wDown = false;
    boolean arrowUp = false;
    boolean arrowDown = false;
    boolean arrowleft = false;
    boolean arrowRight = false; 
    boolean sDown = false;
    int Greenresets = 0;
    int Redresets = 0;
    boolean Adown = false;
    boolean Ddown = false;
    int startMs;
    boolean blueStarted = false;
    boolean Max = false;
    float redVX, greenVX;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {

        redX = 285;
        redY = 400;
        redV = 0;
        greenX = 400;
        greenY = 400;
        greenV = 0;
        startMs = millis();
        blueStarted = false;
        redVX = 0;
        greenVX = 0;
    }

    public void draw() {
        background(135, 206, 235);

        // --- road ---
        fill(150);
        rect(265, 0, 200, 600);

        roadLines();

        // checkers flag
        checkersFlag();

        int time = millis() - startMs;
        if (time < 1000) {
            textSize(20);
            text("4 Seconds Left", 50, 100);
        } else if (time < 2000) {
            textSize(20);
            text("3 Seconds Left", 50, 100);
        } else if (time < 3000) {
            textSize(20);
            text("2 Seconds Left", 50, 100);
        } else if (time < 4000) {
            textSize(20);
            text("1 Second Left", 50, 100);
        } else if (time < 5000) {
            fill(0);
            textSize(20);
            text("GO!", 100, 100);
        }

        if (millis() - startMs > 5000) {
            blueStarted = true;
        }
        if (blueStarted) {
            // vertical controls
            if (arrowUp) {
                greenV -= 0.1;
            } else if (arrowDown) {
                greenV += 0.1;
            } else {
                greenV *= 0.7;
            }

            if (wDown) {
                redV -= 0.1;
                System.out.println(redV);
            } else if (sDown) {
                redV += 0.1;           // (fixed) only affects red
            } else {
                redV *= 0.7;
            }

            redY += redV;
            greenY += greenV;

            // horizontal controls now use velocities (required for X-bounce)
            if (arrowleft) {
                greenVX -= 0.5f;
            } else if (arrowRight) {
                greenVX += 0.5f;
            } else {
                greenVX *= 0.7f;   // friction
            }
            greenX += greenVX;

            if (Adown) {
                redVX -= 0.5f;
            } else if (Ddown) {
                redVX += 0.5f;
            } else {
                redVX *= 0.7f;     // friction
            }
            redX += redVX;
        }

        // (fixed) removed duplicate vertical physics blocks here

        // collisions before wrap
        collideSquares2D(0.9f);

        reset();

        if (Redresets >= 5 || Greenresets >= 5) {
            noLoop();
            fill(0);
            textSize(40);
            text("Game Over", 600, 50);
            confetti();

            if (Redresets > Greenresets) {
                fill(0);
                textSize(40);
                text("Red Wins!", 600, 150);
            } else if (Greenresets > Redresets) {
                fill(0);
                textSize(40);
                text("Green Wins!", 600, 150);
            } else {
                fill(0);
                textSize(40);
                text("It's a Tie!", 600, 250);
            }
        }

        fill(255, 80, 0);
        square(redX, redY, 50);

        fill(0, 0, 255);
        square(greenX, greenY, 50);
    }

    public void keyPressed() {
        if (key == 'w' || key == 'W') wDown = true;
        if (key == 's' || key == 'S') sDown = true;
        if (key == 'a' || key == 'A') Adown = true;
        if (key == 'd' || key == 'D') Ddown = true;

        if (keyCode == UP)    arrowUp = true;
        if (keyCode == DOWN)  arrowDown = true;
        if (keyCode == LEFT)  arrowleft = true;
        if (keyCode == RIGHT) arrowRight = true;
    }

    public void keyReleased() {
        if (key == 'w' || key == 'W') wDown = false;
        if (key == 's' || key == 'S') sDown = false;
        if (key == 'a' || key == 'A') Adown = false;
        if (key == 'd' || key == 'D') Ddown = false;

        if (keyCode == UP)    arrowUp = false;
        if (keyCode == DOWN)  arrowDown = false;
        if (keyCode == LEFT)  arrowleft= false;
        if (keyCode == RIGHT) arrowRight = false;
    }

    public void confetti() {
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
        square(random(600), random(400), 50);
        fill(random(255), random(255), random(255));
    }

    public void roadLines() {
        fill(0);
        rect(355, 80, 20, 50);
        rect(355, 160, 20, 50);
        rect(355, 240, 20, 50);
        rect(355, 320, 20, 50);
        rect(355, 400, 20, 50);
        rect(355, 480, 20, 50);
        rect(355, 560, 20, 50);
        fill(255, 255, 0);
        rect(200, 0, 40, 800);
        rect(490, 0, 40, 800);
    }

    //chatgpt taught me how to do this but it is my code
    public void reset() {
        int S = 50; // this says how big the square is
        if (redY + S < 0 && Redresets < 5) { // if the red height plus 50 is less than zero (off the screen)
            redY = height;
            Redresets++;
        } else if (redY > height && Redresets < 5) { // off the bottom
            redY = -S;
            Redresets++;
        }

        if (greenY + S < 0 && Greenresets < 5) { // same for the other square
            greenY = height;
            Greenresets++;
        } else if (greenY > height && Greenresets < 5) { // off the bottom
            greenY = -S;
            Greenresets++;
        }
    }

    public void checkersFlag() {
        fill(0);
        square(265, 0, 30);
        fill(255);
        square(295, 0, 30);
        fill(0);
        square(325, 0, 30);
        fill(255);
        square(355, 0, 30);
        fill(0);
        square(385, 0, 30);
        fill(255);
        square(415, 0, 30);
        fill(255);
        square(265, 30, 30);
        fill(0);
        square(295, 30, 30);
        fill(255);
        square(325, 30, 30);
        fill(0);
        square(355, 30, 30);
        fill(255);
        square(385, 30, 30);
        fill(0);
        square(415, 30, 30);
        fill(255);
        square(445, 30, 30);
        fill(0);
        square(445, 0, 30);
    }

    // ChatGPT below
    void collideSquares2D(float e) {
        final int S = 50;

        // AABB overlap
        if (redX < greenX + S && redX + S > greenX &&
            redY < greenY + S && redY + S > greenY) {

            float acx = redX + S / 2f, acy = redY + S / 2f;
            float bcx = greenX + S / 2f, bcy = greenY + S / 2f;
            float dx = acx - bcx;
            float dy = acy - bcy;

            if (dx == 0 && dy == 0) dx = 1;

            float overlapX = S - Math.abs(dx);
            float overlapY = S - Math.abs(dy);

            if (overlapX < overlapY) {
                // resolve along X
                int p = (int)Math.ceil(overlapX / 2f);
                if (dx > 0) { redX += p; greenX -= p; }
                else        { redX -= p; greenX += p; }

                // bounce X (swap horizontal velocities with energy factor)
                float tmpVX = redVX;
                redVX   =  greenVX * e;
                greenVX =  tmpVX  * e;

            } else {
                // resolve along Y
                int p = (int)Math.ceil(overlapY / 2f);
                if (dy > 0) { redY += p; greenY -= p; }
                else        { redY -= p; greenY += p; }

                // bounce Y (swap vertical velocities with energy factor)
                float tmpV = redV;
                redV   =  greenV * e;
                greenV =  tmpV  * e;
            }
        }
    }
}
