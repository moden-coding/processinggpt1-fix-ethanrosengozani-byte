import processing.core.PApplet;

public class App extends PApplet {

int redX, redY;// red square position
int greenX, greenY;// green square position
float redV, greenV; // how fast they go/ velocities
boolean wDown = false; // is the w key down
boolean arrowUp = false;// is the up arrow key down
boolean arrowDown = false;// is the down arrow key down
boolean arrowleft = false;// is the left arrow key down
boolean arrowRight = false;// is the right arrow key down
boolean sDown = false; // is the s key down
int Greenresets = 0;// how many times green reset
int Redresets = 0;// how many times red reset
boolean Adown = false;// is the a key down
boolean Ddown = false;// is the d key down
int startMs;// when the game started
boolean blueStarted = false;// has the blue square started moving
boolean Max = false;// has either player reached max resets
float redVX, greenVX;// horizontal velocities
int ROAD_X = 265; // left edge of gray road
int ROAD_W = 200; // road width
int SIZE = 50; // square size (you already use 50 elsewhere)
boolean wallhitGreen = false;// has green hit the wall
boolean wallhitRed = false;// has red hit the wall
boolean maxRedx = false;// has red reached max x
boolean maxGreenx = false;// has green reached max x
boolean minRedx = false;// has red reached min x
boolean gameOver = false;
String winner = "";
boolean gameOverShown = false;
int gameOverMs = 0;

boolean minGreenx = false;// has green reached min x

public static void main(String[] args) {// main method
PApplet.main("App");
 }

public void settings() {// settings method which runs at the begining
size(800, 600);
 }

public void setup() {// setup method which runs once

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

public void draw() {// draw method which runs 30 times a second
// --- sky ---
background(135, 206, 235);// sky
fill(34, 139, 34);// green color
rect(0, 300, 800, 300);// ground

// --- road ---
fill(150);// gray color
rect(265, 0, 200, 600);// gray road

roadLines();// draw road lines

// checkers flag
checkersFlag();// draw checkers flag
Timer();// draw timer
if (millis() - startMs > 5000) {// after 5 seconds
blueStarted = true;// blue square can start moving
 }
if (blueStarted && !gameOver) {

if (arrowUp) {// up arrow key
greenV -= 0.1;// increases blue square velocity
 } else if (arrowDown) {// decreases
greenV += 0.1;// decreases blue square velocity
 } else {
greenV *= 0.7;
 }

if (wDown) {// w key
redV -= 0.1;
System.out.println(redV);// increases red square velocity
 } else if (sDown) {
redV += 0.1;// decreases red square velocity
 } else {
redV *= 0.7;
 }

redY += redV;
greenY += greenV;

if (arrowleft) {// left arrow key
greenVX -= 0.5;// increases horizontal velocity
 } else if (arrowRight) {// right arrow key
greenVX += 0.5;// increases horizontal velocity
 } else {
greenVX *= 0.7;// slows down horizontal velocity
 }
greenX += greenVX;

if (Adown) {// a key
redVX -= 0.5;
 } else if (Ddown) {// d key
redVX += 0.5;
 } else {// slows down horizontal velocity
redVX *= 0.7;// slows down horizontal velocity
 }
redX += redVX;// updates red square horizontal position
 }
constrainToRoad();

collideSquares2D(0.9f);

reset();
gamecheck();
fill(255, 80, 0);// orange color
square(redX, redY, 50);// draw red square
fill(0, 0, 255);
square(greenX, greenY, 50);// draw green square
 }

public void keyPressed() {
if (key == 'w' || key == 'W')
wDown = true;// w key down
if (key == 's' || key == 'S')
sDown = true;// s key down
if (key == 'a' || key == 'A')
Adown = true;// a key down
if (key == 'd' || key == 'D')
Ddown = true;// d key down

if (keyCode == UP)
arrowUp = true;// up arrow key down
if (keyCode == DOWN)
arrowDown = true;// down arrow key down
if (keyCode == LEFT)
arrowleft = true;// left arrow key down
if (keyCode == RIGHT)
arrowRight = true;// right arrow key down
 }

public void keyReleased() {
if (key == 'w' || key == 'W')
wDown = false;// w key up
if (key == 's' || key == 'S')
sDown = false;// s key up
if (key == 'a' || key == 'A')
Adown = false;// a key up
if (key == 'd' || key == 'D')
Ddown = false;// d key up

// Restart immediately when gray page is up
if ((key == 'R' || key == 'r') && gameOver) {
Redresets = 0;
Greenresets = 0;
gameOver = false;
gameOverShown = false;
winner = "";
setup();
startMs = millis();
blueStarted = false;
 }

if (keyCode == UP)
arrowUp = false;// up arrow key up
if (keyCode == DOWN)
arrowDown = false;// down arrow key up
if (keyCode == LEFT)
arrowleft = false;// left arrow key up
if (keyCode == RIGHT)
arrowRight = false;// right arrow key up
 }

public void Timer() {
int time = millis() - startMs;// time since the start of the game
if (time < 1000) {
textSize(20);
text("4 Seconds Left", 50, 100);// display 4 seconds left
 } else if (time < 2000) {
textSize(20);
text("3 Seconds Left", 50, 100);// display 3 seconds left
 } else if (time < 3000) {
textSize(20);
text("2 Seconds Left", 50, 100);// display 2 seconds left
 } else if (time < 4000) {
textSize(20);
text("1 Second Left", 50, 100);// display 1 second left
 } else if (time < 5000) {
fill(0);
textSize(20);
text("GO!", 100, 100);// display GO!
 }
if (millis() - startMs > 5000) {// after 5 seconds
blueStarted = true;
 }
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

// moden code
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
 
 public void gamecheck() {
  if (Redresets >= 5 || Greenresets >= 5) {
   gameOver = true; // <-- always true once someone hits max
 
   if (!gameOverShown) {
    gameOverShown = true;
    fill(0);
    textSize(40);
    text("Game Over", 600, 50);
    confetti();
    return; // show confetti this frame only
   }
 
   // gray “Press R” page
   background(128);
   fill(255);
   textSize(40);
   text("Press R to restart", 250, 300);
   return; // <-- prevent later drawing from overwriting
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
if (redX < greenX + S && redX + S > greenX && // if they are overlapping
redY < greenY + S && redY + S > greenY) {// if they are overlapping

float acx = redX + S / 2f, acy = redY + S / 2f;// center of red square
float bcx = greenX + S / 2f, bcy = greenY + S / 2f;// center of green square
float dx = acx - bcx;
float dy = acy - bcy;

if (dx == 0 && dy == 0)
dx = 1;// prevent division by zero

float overlapX = S - Math.abs(dx);// overlap amounts
float overlapY = S - Math.abs(dy);// overlap amounts

if (overlapX < overlapY) {// if overlapX is less than overlapY
// resolve along X
int p = (int) Math.ceil(overlapX / 2f);// penetration amount
if (dx > 0) {
redX += p;
greenX -= p;
 } // if red is to the right of green
else {
redX -= p;
greenX += p;
 } // if red is to the left of green

// bounce X (swap horizontal velocities with energy factor)
float tmpVX = redVX;// temporary variable for red horizontal velocity
redVX = greenVX * e;// red horizontal velocity
greenVX = tmpVX * e;// green horizontal velocity

 } else {
// resolve along Y
int p = (int) Math.ceil(overlapY / 2f);// penetration amount
if (dy > 0) {
redY += p;
greenY -= p;
 } // if red is below green
else {
redY -= p;
greenY += p;
 } // if red is above green

// bounce Y (swap vertical velocities with energy factor)
float tmpV = redV;// temporary variable for red vertical velocity
redV = greenV * e;// red vertical velocity
greenV = tmpV * e;// green vertical velocity
 }
 }
 }

// part of chargpt code above ^
public void constrainToRoad() {

int minX = ROAD_X;
int maxX = ROAD_X + ROAD_W - SIZE;

// --- RED borders ---
if (redX < minX) {
redX = minX;
redVX = -redVX * 0.7f;
 } else if (redX > maxX) {
redX = maxX;
redVX = -redVX * 0.7f; // f turns a int into a float
 }

// --- GREEN borders ---
if (greenX < minX) {
greenX = minX;
greenVX = -greenVX * 0.7f;
 } else if (greenX > maxX) {
greenX = maxX;
greenVX = -greenVX * 0.7f;
 }
 }
}



