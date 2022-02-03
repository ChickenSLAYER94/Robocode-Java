package projectbot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
import robocode.*;
import java.awt.*;


public class Gorkhali extends AdvancedRobot {
		int moveDirection=1;
	
		public void run() {
		
		setBodyColor(new Color(0, 56, 147));
		setGunColor(new Color(220, 20, 60));
		setRadarColor(new Color(0, 56, 147));
		setBulletColor(new Color(220, 20, 60));
		setScanColor(new Color(255, 255, 255));

        
		setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
		
		while (true) {
			execute(); //run above code
			scan(); // ask for onScannedRobot
			
		if(getRadarTurnRemaining() == 0){ //if in case radar stops, it activates radar
			setTurnRadarLeft(Double.POSITIVE_INFINITY); //turns radar 360 infinitly 
			}	
		}
	}
		public void onScannedRobot(ScannedRobotEvent e) {
			double absBearing=e.getBearingRadians()+getHeadingRadians();
			double lateVeloci =e.getVelocity() * Math.sin(e.getHeadingRadians() -absBearing);//enemies late velocity
			double bearing =  this.getHeading() + e.getBearing();
			//to find out the angle between enemy and radar
			double TurningRadar = bearing - this.getRadarHeading();
			TurningRadar = Utils.normalRelativeAngleDegrees(TurningRadar);
			//turning radar and with the help of this we won't lose sight of opponent
			setTurnRadarRight(1.9 * TurningRadar);
			//to find angle between enemy and gun
			double gunTurnAmount;
		 	setTurnGunRight(Utils.normalRelativeAngleDegrees(bearing - this.getGunHeading()));
			//firing power vary according to distance between enemy and our robot					
		
		if(Math.random()>.9){
			setMaxVelocity((12*Math.random())+12);//randomly change speed
		}
		if (e.getDistance() > 150) {//if distance between our robot and ememy is greater than 150
			gunTurnAmount = robocode.util.Utils.normalRelativeAngle(absBearing- getGunHeadingRadians()+lateVeloci/22);//this is the amount to turn our gun
			setTurnGunRightRadians(gunTurnAmount); //turn our gun
			setTurnRightRadians(robocode.util.Utils.normalRelativeAngle(absBearing-getHeadingRadians()+lateVeloci/getVelocity()));
			setAhead((e.getDistance() - 140)*moveDirection);//move forward
			if(this.getEnergy() >25){
				setFire(3);
			}else if(this.getEnergy() >= 2 || this.getEnergy()<=25) {  //fires with the power of 1 after it hits energy of less than 25 and fires until robot energy becomes 2 
				setFire(1);
			}else if(this.getEnergy() >= 0.5 || this.getEnergy()<2){  // fires at the rate of 0.5 when it energy reaches less than 2 and fires until energy becomes 0.5
				setFire(0.5);
			}	
			else{  // when the robot reaches energy level if 0.5 it will not attack the enemy
				//no fire
			}

		}else{//if distance between robot and enemy is less than equals to 150
			gunTurnAmount = robocode.util.Utils.normalRelativeAngle(absBearing- getGunHeadingRadians()+lateVeloci/15);//amount to turn our gun, lead just a little bit
			setTurnGunRightRadians(gunTurnAmount);//turn our gun
			setTurnLeft(-90-e.getBearing()); //turn perpendicular to the enemy
			setAhead((e.getDistance() - 140)*moveDirection);//move forward
				if(this.getEnergy() >25){ //fire with power of 3 until our robot have energy more than 25.
				setFire(3);
			}else if(this.getEnergy() >= 2 || this.getEnergy()<=25) { //fires with the power of 1 after it hits energy of less than 25 and fires until robot energy becomes 2 
				setFire(1);
			}else if(this.getEnergy() >= 0.5 || this.getEnergy()<2){ // fires at the rate of 0.5 when it energy reaches less than 2 and fires until energy becomes 0.5 
				setFire(0.5);
			}	
			else{ // when the robot reaches energy level if 0.5 it will not attack the enemy
				//no fire
			}
		} 

		}		
		public void onHitWall(HitWallEvent e){
			moveDirection = -moveDirection;//if it hits the wall it will move in reverse direction 
		}
	 	public void onBulletHit(BulletHitEvent e){
			out.println(e.getName() + " " + "got hit" + "!");
			out.println(e.getEnergy()+ " " + "is enemy energy" + "!");
		}
		
public void onHitRobot(HitRobotEvent e){
	boolean moveForward=true;
	if(moveForward){
		back(50);
		moveForward=false;
	}else{
		ahead(50);
		moveForward=true;
	}
}

		}