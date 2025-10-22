import processing.core.PApplet;

public class App extends PApplet {

    int redX, redY;
    int greenX, greenY;
    float redV, greenV;
    boolean wDown = false;
    boolean sDown = false;
    int Greenresets = 0;
    int Redresets = 0;
    boolean Adown = false;
    boolean Ddown = false;
    int startMs;
    boolean blueStarted = false;
    boolean Max = false;

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
        int MaxSpeed = 5;
    }

    public void draw() {
        background(200);

        // --- road ---
        fill(150);
        rect(265, 0, 200, 600);

        fill(0);
        rect(355, 0, 20, 50);
        rect(355, 80, 20, 50);
        rect(355, 160, 20, 50);
        rect(355, 240, 20, 50);
        rect(355, 320, 20, 50);
        rect(355, 400, 20, 50);
        rect(355, 480, 20, 50);
        rect(355, 560, 20, 50);

        textAlign(CENTER, CENTER);
        textSize(24);
        fill(0);
        int time = millis() - startMs;
        if (time < 1000) {
            text("4 Seconds Left", width / 2f, height / 2f);
        } else if (time < 2000) {
            text("3 Seconds Left", width / 2f, height / 2f);
        } else if (time < 3000) {
            text("2 Seconds Left", width / 2f, height / 2f);
        } else if (time < 4000) {
            text("1 Second Left", width / 2f, height / 2f);
        } else if (time < 5000) {
            text("GO!", width / 2f, height / 2f);
        }

        if (millis() - startMs > 5000) {
            blueStarted = true;
        }
        if (blueStarted && greenV < 5) {
            greenV -= 0.1;

            if (wDown) {
                redV -= 0.1;
            } else if (sDown) {
                redV += 0.1;
                greenV += 0.1;
            } else {
                redV *= 0.7;
            }

            redY += redV;
            greenY += greenV;

            if (blueStarted && greenV < 5) {
                greenV -= 0.1;
            }
            if (wDown) {
                redV -= 0.1;
            } else if (sDown) {
                redV += 0.1;
                greenV += 0.1;
            } else {
                redV *= 0.7;
            }
            if (greenV > 5) {
                greenV = 5;
            }
            if (Adown) {
                redX -= 5;
            } else if (Ddown) {
                redX += 5;
            }

            int S = 50;

            if (redY + S < 0 && Redresets < 6) {
                redY = height;
                Redresets++;
            } else if (redY > height && Redresets < 6) {
                redY = -S;
                Redresets++;
            }

            if (greenY + S < 0 && Greenresets < 6) {
                greenY = height;
                Greenresets++;
            } else if (greenY > height && Greenresets < 6) {
                greenY = -S;
                Greenresets++;
            }

            if (Redresets >= 6 && Greenresets >= 6) {
                noLoop();
                fill(0);
                textSize(80);
                text("Game Over", width / 2f, height / 2f);
            }
            if (Redresets > Greenresets) {
                fill(0);
                textSize(40);
                text("Red Wins!", width / 2f, height / 2f + 100);
            } else if (Greenresets > Redresets) {
                fill(0);
                textSize(40);
                text("Green Wins!", width / 2f, height / 2f + 100);
            } else {
                fill(0);
                textSize(40);
                text("It's a Tie!", width / 2f, height / 2f + 100);
            }
        }

        // --- draw cars LAST so visuals match updated positions ---
        fill(255, 80, 0);
        square(redX, redY, 50);

        fill(0, 0, 255);
        square(greenX, greenY, 50);
    }

    public void keyPressed() {
        if (key == 'w' || key == 'W') {
            wDown = true;
        } else if (key == 's' || key == 'S') {
            sDown = true;
        } else if (key == 'a' || key == 'A') {
            Adown = true;
        } else if (key == 'd' || key == 'D') {
            Ddown = true;
        }
    }

    public void keyReleased() {
        if (key == 'w' || key == 'W') {
            wDown = false;
        } else if (key == 's' || key == 'S') {
            sDown = false;
        } else if (key == 'a' || key == 'A') {
            Adown = false;
        } else if (key == 'd' || key == 'D') {
            Ddown = false;
        }
    }
}
