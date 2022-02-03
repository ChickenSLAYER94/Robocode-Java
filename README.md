# Robocode-Java
Aayo Gorkhali Robot is a robot built to compete with other robots in a Robocode battle
evolution, Robocode Game is an evolutionary technique to create java-based controller
for a stimulated robot tank. The Robocode game is a programming platform that allows
such robot tanks to compete with each other. This report discusses the use of Aayo
Gorkhali Robot a form of special java code programming that evolve relatively complex
behaviours within the program size constrains of the competition. The robot was built
using previous human coded robots as an inspiration such as wall-robot, super tracker
robot, etc. this enable us to build a robot able to compete effectively against many top
previous and new unseen opponents, scoring well against many robots in a head to
head battles.

# Introduction
This report shall explain the research, main functions and the work performed on a java
base code programming for a robot. The project consists of three review that describe
each research, main functions and work performance and further improvement of our
robot, these three phases are interlinked, yet each performed in a distinct way.
Research into existing java base robot programming is outlined, with appropriate
references; the testing performed to validate the java base code used is described. The
overall design, at the time of writing is describe along with the functional description of
each review
Our aim as a group of three was to build a robot which will participate in the robotics
competition.
Our main objective in this project was to find methods which would:
To produce a robot platform for indoor use,
• Enable our robot to maintain a constant accurate move, fire and speed (Scanning
event),
• Define the coordinate of our robot so that it doesn’t deviate from its define
intended path (Main Loop event).
• Enable the robot to identify its main objects (onHitRobot event),
• Enable the robot not to lose sight and aim for other robots in the battle arena,
• To duplicate the robotic platform if possible, and investigate the potential for
cooperative behaviors and a future improvement.


# Description of how Robocode Works
Robocode is a java programming language-based game in which instructions to the
robot is already given or done in real time. The main ambition of the robocode is to help
student to learn, interact and hone the skill a fun way.
In Robocode, robot can perform predefine instructions or learn next set of instruction
according to the activity of the enemy robot. In robocode battle, it can either be one vs
one or team vs team battle in 800 x 600 pixel arena.
The instruction given to the robocode which is absolutely imperative in order to run is as
follows:
• Movement
• Firing
• Tracking
Movement: In this part it determines where exactly the robot moves. For instance,
ahead() method moves robot forward.
Firing: This is a set of instruction that determines how much strength bullet can be
while firing, the maximum strength of firing bullet is 3 and minimum is 0.
Tracking: It is also known as scanning part where the robot searches for an enemy
robot. After the initiation tracking robot part robot can find the exact distance and
position of the robot. This help to pinpoint the enemy robot and act according to it.
Purpose of different statement for our strategy and implementation:
For the first robot we set up the following methods:
1) Main Loop event
In the while(true) method (this loop continues forever), our robot moves ahead from 0 to
500 pixels if arena is 800 x 600 height and width, if our robot is in the middle position
then it will hit the wall and come back 50 pixels (if our robot is moving forward) or go
ahead(if our robot is moving backward) and continues the ahead while(true) loop. Our
robot move ahead and when it comes to moveAmount method (moveAmount =
Math.max(getBattleFieldWidth()-100, getBattleFieldHeight()-100) it makes right turn and
again continue the loop.

2) onHitRobot event
boolean movingDirection=true;
if(movingDirection){
turnRight(e.getBearing());
movingDirection=false;
}
else{
turnLeft(e.getBearing());
movingDirection=true;
}
if (e.getEnergy() > 50) {
fire(3);
} else if (e.getEnergy() > 40) {
fire(2);
} else if (e.getEnergy() > 20) {
fire(1);
} else if (e.getEnergy() > 2) {
fire(.5);
} else if (e.getEnergy() > .4) {
fire(.1);
}
ahead(40); // Ram him again!
So, according to the above method, when our robot hits another robot then the above
code will function where if it is true, then our robot move/turn towards hit robot (e.g. if
enemy robot is in left direction our robot move towards left otherwise right). We get the
energy of that robot and if that robot energy is more than 50 then we will fire with the
power of 3 and vice versa) and move ahead by 40 pixels to ram him again.

3) Scanning event:
• Robot, gun and radar are made in a way that they are independent to each
other.
Methods used: setAdjustGunForRobotTurn() and setAdjustRadarForGunTurn();
• Radar turn 360 degree if radar stops while running.
Methods used: getRadarTurnRemaining() and setTurnRadarLeft().
• When this event is initiated, our robot first finds the angle between enemy and
the radar of our robot. After that, we make our robot radar to turn right to face
enemy robot and making sure that we won’t lose the sight and at last, find the
angle between enemies and gun of our robot.
Methods Used: getHeading(), getBearing(), normalRelativeAngleDegrees(),
getRadarHeading(), setTurnRadarRight(), setTurnGunRight(), and
getGunHeading().
• Firing power vary according to distance to the enemy robot. In this change we
simply divide firing power to various strength and fire with that fire power
strength with respect to the distance to the enemy robot. Basically, higher the
distance to the enemy robot lower will be the strength of bullet power while firing
and vice-versa.
Also, we added a code for output, which gives reading of distance to the enemy
robot.
Method used: getDistance().
if(e.getDistance()<150){ //to save energy, this is created to limit enery which is
use to attack enemy
setFire(3);
}else if(e.getDistance()>=150 && e.getDistance()<=250){
setFire(2);
}else if(e.getDistance()>250 && e.getDistance()<=380){
setFire(1);
}else{
setFire(0.5);
For the second robot we set up the following methods:
1) At first our robot moves forward by 100pixel and turnRight by 90 degree and this
continues until the robot is destroyed.
Our robot moves in square until it faces some obstacle.
ahead(100);
turnRight(90);
execute(); //run above code
scan(); // ask for onScannedRobot
2) onScannedRobot class: When this event is initiated, our robot first finds the angle
between enemy and the radar of our robot. After that, we make our robot radar to
turn right to face enemy robot and making sure that we won’t lose the sight and
at last, find the angle between enemies and gun of our robot.
Methods Used: getHeading(), getBearing(), normalRelativeAngleDegrees(),
getRadarHeading(), setTurnRadarRight(), setTurnGunRight(), and
getGunHeading().
3) When our robot is moving ahead and hit the wall robot hits wall then it moves
backward by 200 pixel and also when moving backward and hit the wall then it
moves forward by 200pixel
if (movingForward) {
back(200);
movingForward = false;
} else {
ahead(200);
movingForward = true;
}
4) When our robot hits other robot then it turns right to the bearing of the enemy, if
the enemy is on right and if the enemy is on the left side, then our robot turns left
to the bearing of the enemy robot and then if the energy of enemy robot is less
than 50 then our robot fires with the power of 3 and if it is less than 40 the power
is 2 and vice versa
boolean movingDirection=true;
if(movingDirection){
ahead(e.getBearing());
movingDirection=false;
}
else{
turnLeft(e.getBearing());
movingDirection=true;
}
// Determine a shot that won't kill the robot...
if (e.getEnergy() < 50) {
fire(3);
} else if (e.getEnergy() < 40) {
fire(2);
} else if (e.getEnergy() < 100) {
fire(1);
} else if (e.getEnergy() < 2) {
fire(.5);
} else if (e.getEnergy() < .4) {
fire(1);
}
For the third review we set up the following methods:
Here, in this review we had two plans and after several test of the two, we decided to
choose the last one:
Plan one:
Movement: Changed the move Amount function:
double moveAmount = Math.max(getBattleFieldWidth()-50, getBattleFieldHeight()-50);
This changes the move Amount function so that our robot won't hit the wall and move
with the height or width of battlefield minus 50pixel.
Second movement: modified the moveAmount in while(true) loop.
while (true) {
moveAmount = Math.max(getBattleFieldWidth()-110,
getBattleFieldHeight()-110);
back(moveAmount);
moveAmount = Math.max(getBattleFieldWidth()-103,
getBattleFieldHeight()-103);
ahead(moveAmount);
}
So, when our robot touches the wall it starts to execute the above code. At first our
robot will start to move back according to the pixels of the arena minus 110s and
similarly move ahead according to the pixels of arena minus 103 pixels. For example, if
the battlefield is 600 pixels of height and width then our robot will move back (600-110)
pixels and similarly move ahead (600-103). We want our robot to move back and ahead
without touching the wall because when we hit the wall we lose 3 points of our robot so
to make our robot move back and ahead without hitting the wall, the above code is
implemented.
- Here we fix the problem that we face in the review 2 (i.e. robot continuously hit
wall after each initiation of ahead() and back() which is in loop.)
- We fixed that problem but another problem that really hinders our score was
bullet damage. So, that is why we use another plan to overcome those criteria.
Plan two:
Firstly, in this change we add enemy bearing and heading of our robot to get absolute
bearing of the enemy robot. We also tweak the velocity of the robot to get the later
velocity of the enemy robot. The main point to do that is to preciously change the
direction of robot and gun with respect to enemy.
Method Used: getBearingRadians(), getHeadingRadians(), getGunHeadingRadians(),
Math.random().
Secondly, this is the prime change where our robot initiates movement part as well as
firing part. In this change we use all the method that we create in pervious commit to target the enemy move forward and then fire. In this change, we have if else condition, it
first checks the distance between robot and enemy and if the robot is greater than 150,
if condition started where our robot moves towards the enemy robot while locking the
target and fires with the power of 3. In else condition, our robot revolves around the
target locked enemy robot maintaining the distance and fire at the rate of 3.
Method Used: getDistance(), setAhead(), getVelocity(), setTurnLeft(), setFire().
Lastly, we added the limit in setFire() and we added fuction on onHitWall(), onBulletHit()
and onHitRobot() method. Now the robot fires according to its energy level. Also, when
the robot hits the wall it will move in reverse direction and prevent from being fixed in
wall in the battle.
Method Used: setFire(),onHitWall(), onBulletHit(),
onHitRobot(),.getName(),getEnergy(), back(), ahead().
