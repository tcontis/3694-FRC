/****************************************************\
 * Team 3694 NAHS Warbotz
 * FRC 2016 Robot Code
 * "El Diablo"
 * 
 * Version 1.1.1
 * 
 * Changes: 
 * -Tweaks
\***************************************************/

//Defines stuff
package org.usfirst.frc.team3694.robot;

//Imports
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.*;
import edu.wpi.first.wpilibj.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

//ROBOT CODE FROM THIS POINT ON
public class Robot extends IterativeRobot{
	//SmartDashboard Objects and Variables
	public static SendableChooser chooser; //Destination Point
	public static SendableChooser chooser3; //Camera
	public static int status; //Camera Status
	public static int autoChooser; //Autonomous chooser
	
	//PWM Objects and Variables
	public static RobotDrive chassis; //Robot Chassis
	public static double motorPos; //Position of rollerTilt
	public static Victor leftDrive = new Victor(0); //Left Drive Motors (Both Front and Rear)---------------------------------PWM (0)
	public static Victor rightDrive = new Victor(1); //Right Drive Motors (Both Front and Rear)-------------------------------PWM (1)
	public static Victor roller = new Victor(2); //Roller Motor (Forwards and Backwards)--------------------------------------PWM (2)
	public static Victor rollerTilt = new Victor(3); //Roller Tilt (Forwards and Backwards)-----------------------------------PWM (3)
	
	//USB Objects and Variables
	public static Joystick driveStick = new Joystick(0); //Joystick used for Driving------------------------------------------USB (0)
	public static Joystick shootStick = new Joystick(1); //Joystick used for Shooting-----------------------------------------USB (1)
	public static boolean if4 = false; //Variable used to toggle shootStick button 4
	public static boolean if5 = false; //Variable used to toggle shootStick button 5
	
	//SPI Objects and Variables
	public static final double Kp = 0.03; //proportional scaling constant
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0); //Gyroscope-----------------------------------SPI (0)
	public static ADXL362 accel = new ADXL362(SPI.Port.kOnboardCS1, Accelerometer.Range.k16G); //Accelerometer----------------SPI (1)

	//DIO Objects and Variables
	public static Encoder leftEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X); //Left Side Driver Encoder---------DIO (0,1)
	public static Encoder rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X); //Right Side Driver Encoder------DIO (2,3)
	public static Encoder rollerEncoder = new Encoder(5, 6, false, Encoder.EncodingType.k4X); //Roller Motor Encoder----------DIO (5,6)
	public static DigitalInput rollerUpSwitch = new DigitalInput(7); //Roller Upper Limit Switch------------------------------DIO (7)
	public static DigitalInput rollerDownSwitch = new DigitalInput(8); //Roller Lower Limit Switch----------------------------DIO (8)
	public static DigitalInput rollerBallSwitch = new DigitalInput(9); //Roller Ball Limit Switch-----------------------------DIO (9)
	public static final double circumference = 4.05 * Math.PI; //Distance per revolution

//RESET ENCODERS
	public void resetEncoders(){
		leftEncoder.reset(); //Reset left encoder
    	rightEncoder.reset(); //Reset right encoder
    	rollerEncoder.reset(); //Reset arm encoder
	}
//UPDATE VARIABLES IN SMARTDASHBOARD
	public void dashVarUpdate(double a, double b, double c){
		SmartDashboard.putNumber("Gyro Angle", a);
      	SmartDashboard.putNumber("Gyro Angle Dial", b);
      	SmartDashboard.putNumber("Gyro Rate", c);
	}
//MOVE ROBOT
    public void move(double dist, double speed){
    	double revsToTravel = dist/circumference;
    	if(leftEncoder.get() > revsToTravel || rightEncoder.get() > revsToTravel){
    		chassis.drive(0.0, 0.0);
    		resetEncoders(); //reset encoders
    	}else{
    		chassis.drive(speed, (-gyro.getAngle()*Kp)); //Drive until encoder counts met
    	}
    }
//TURN ROBOT
    public void turn(double angle){
    	if(gyro.getAngle() > 0){
    		while(gyro.getAngle() < angle){
    			chassis.arcadeDrive(0.0, angle);
    		}
    	}else if (gyro.getAngle() < 0){
    		while(gyro.getAngle() > angle){
    			chassis.arcadeDrive(0.0, angle);
    		}
    	}
    	gyro.reset();
    }
//MOVE MANIPULATOR
    public void moveArm(Joystick stick){
    	// Store position
		// If Limit switches tripped, do not allow other direction.
		if (rollerUpSwitch.get() == true && shootStick.getAxis(Joystick.AxisType.kY) < 0) {
			rollerTilt.set(0);
		} else if (rollerDownSwitch.get() == true && shootStick.getAxis(Joystick.AxisType.kY) > 0) {
			rollerTilt.set(0);
		} else {
			rollerTilt.set(shootStick.getAxis(Joystick.AxisType.kY));
		}
		if (shootStick.getAxis(Joystick.AxisType.kY) == 0) {
			rollerTilt.setPosition(rollerTilt.getPosition()); // set rollerTilt to previous position	
		}
		if(shootStick.getAxis(Joystick.AxisType.kY) <= 0.15 && shootStick.getAxis(Joystick.AxisType.kY) >= -0.15){
	    	if(shootStick.getAxis(Joystick.AxisType.kY) < 0 && rollerUpSwitch.get() == false){
	    		rollerTilt.set(shootStick.getAxis(Joystick.AxisType.kY) * 1.15);
	    	}else if(shootStick.getAxis(Joystick.AxisType.kY) > 0 && rollerUpSwitch.get() == false){
	    		rollerTilt.set(shootStick.getAxis(Joystick.AxisType.kY) * 1.15);
	    	}else if(shootStick.getAxis(Joystick.AxisType.kY) == 0 && rollerUpSwitch.get() == false && rollerUpSwitch.get() == false){
	    		rollerTilt.set(rollerTilt.getPosition() * 1.15);
	    	}else if(rollerUpSwitch.get() == true || rollerUpSwitch.get() == true){
	    		rollerTilt.set(0);
	    	}
	    }
    }
//MOVE OVER DEFENSE AND SHOOT AUTO
	public void fullAuto(double move1, double move2, double angle){
	    SmartDashboard.putString("Console", "The robot will move onwards and shoot at the low goal.");
		while(rollerBallSwitch.get() == false){
			roller.set(-0.75);
		}
		while(rollerDownSwitch.get() == false){
			rollerTilt.set(0.5);
		}
		move(move1, -0.6);
		while(rollerUpSwitch.get() == false){
			rollerTilt.set(-0.5);
		}
		turn(angle);
		move(move2, -0.6);
		while(rollerDownSwitch.get() == false){
			rollerTilt.set(0.5);
		}
		roller.set(0.75);
	}
//SEQUENCE CREATOR
    public void sequenceCreator(Victor v){
    	Timer.delay(0.5);
		v.set(0.25);
		Timer.delay(0.25);
		v.set(0.5);
		Timer.delay(0.25);
		v.set(0.75);
		Timer.delay(0.25);
		v.set(1.0);
		Timer.delay(0.25);
		
		v.set(0.75);
		Timer.delay(0.25);
		v.set(0.5);
		Timer.delay(0.25);
		v.set(0.25);
		Timer.delay(0.25);
		v.set(0.0);
		Timer.delay(0.25);
		
		v.set(-0.25);
		Timer.delay(0.25);
		v.set(-0.5);
		Timer.delay(0.25);
		v.set(-0.75);
		Timer.delay(0.25);
		v.set(-1.0);
		Timer.delay(0.25);
		
		v.set(-0.75);
		Timer.delay(0.25);
		v.set(-0.5);
		Timer.delay(0.25);
		v.set(-0.25);
		Timer.delay(0.25);
		v.set(0.0);
		Timer.delay(0.25);
    }
//ROBOT INIZILIZATION (RUNS ONCE)
	public void robotInit() {
		//Start Camera
		if(status == 0){
    		CameraServer.getInstance().startAutomaticCapture("cam0");
    	}	
		//Table Creation for Destination Point
		chooser = new SendableChooser();
		chooser.initTable(NetworkTable.getTable("Autonomous"));
      	chooser.addDefault("Nothing", 0);
      	chooser.addObject("Forward 50%" , 1);
      	chooser.addObject("Forward 75%", 2);
      	chooser.addObject("Full Auto [0] [] [] [] []" , 3);
      	chooser.addObject("Full Auto [] [0] [] [] []" , 4);
      	chooser.addObject("Full Auto [] [] [0] [] []" , 5);
      	chooser.addObject("Full Auto [] [] [] [0] []" , 6);
    	chooser.addObject("Full Auto [] [] [] [] [0]" , 7);
      	SmartDashboard.putData("Destination Point", chooser);
      	
      	//Table selection for Camera Status 
      	chooser3 = new SendableChooser(); 
      	chooser3.initTable(NetworkTable.getTable("RoboRio Camera")); 
      	chooser3.addDefault("Enable", 0); 
      	chooser3.addObject("Disable", 1);  
      	SmartDashboard.putData("RoboRio Camera", chooser3); 
      	
      	//Initialize SmartDashboard Fields
      	dashVarUpdate(0, 0, 0);
        SmartDashboard.putString("Roborio Camera Status:", chooser3.getSelected().toString()); 
      	SmartDashboard.putString("Roller Direction", "---");
      	SmartDashboard.putString("Test Sequence", "---");
      	SmartDashboard.putString("Error","---");
      	SmartDashboard.putString("Console","---");
      	
    	//Configure motors present in Chassis, configure safety
    	chassis = new RobotDrive(leftDrive, rightDrive);
    	gyro.calibrate();
    	chassis.setExpiration(0.1);
      	resetEncoders(); //reset encoders
    }
//AUTONOMOUS INITIATION (RUNS ONCE)
    public void autonomousInit() {
    	Timer.delay(0.005); //Slight delay required 
    	chassis.setSafetyEnabled(false);
    	gyro.reset(); //Reset Gyro
    	resetEncoders(); //reset encoders
    	
    	//Get options we selected before autonomous
    	autoChooser = Integer.parseInt(chooser.getSelected().toString());
    	if(isEnabled()){
   		 	switch(autoChooser){ 
   		 		case 0: SmartDashboard.putString("Console", "The robot will do nothing."); 
   		 				break;  
   		 		case 1: SmartDashboard.putString("Console", "The robot will move onwards."); 
   		    			move(259, -0.5); 
   		    			break;
   		 		case 2: SmartDashboard.putString("Console", "The robot will move onwards."); 
	    				move(259, -0.75); 
	    				break;
   		 		case 3: SmartDashboard.putString("Console", "The robot will move onwards and shoot at the low goal.");
   		 				fullAuto(290, 124, 30); // add robot length to 259 
   		 				break;
   		 		case 4: SmartDashboard.putString("Console", "The robot will move onwards and shoot at the low goal.");
   		 				fullAuto(312, 68, 30); // add robot length to 281
   		 				break;
	   		 	case 5: SmartDashboard.putString("Console", "The robot will move onwards and shoot at the low goal.");
	   		 			fullAuto(0, 0, 30);
	   		 			break;
		   		case 6: SmartDashboard.putString("Console", "The robot will move onwards and shoot at the low goal.");
		   		 		fullAuto(0, 0, -30);
	   		 			break;
		   		case 7: SmartDashboard.putString("Console", "The robot will move onwards and shoot at the low goal.");
		   				fullAuto(0, 0, -30);
	   		 			break;
   		 	}
    	}
    }
//AUTONOMOUS PERIODIC (CALLED EVERY FIELD CYCLE)  
    public void autonomousPeriodic(){
    	dashVarUpdate(gyro.getAngle(), gyro.getAngle(), gyro.getRate()); //Update SmartDashboard Values
    }
//TELEOPERATED INITIATION (RUNS ONCE)
    public void teleopInit() {
    	chassis.setSafetyEnabled(true); //Enable Safety
    	resetEncoders(); //reset encoders
    	gyro.reset();
    }
//TELEOPERATED PERIODIC (CALLED EVERY FIELD CYCLE)
    public void teleopPeriodic(){
	    Timer.delay(0.005); //Slight delay required
	    dashVarUpdate(gyro.getAngle(), gyro.getAngle(), gyro.getRate()); //Update SmartDashboard Values
	    chassis.arcadeDrive(driveStick); //Drive chassis using Arcade Drive (One Joystick)
	    moveArm(shootStick); //Move manipulator arm
	    //Make sure manipulator doesn't slam
	    //Roller Button Functions 
        if(shootStick.getRawButton(3)){ 
	        if4 = false; 
	        if5 = false; 
	        SmartDashboard.putString("Roller Direction", "Stopped"); 
	        roller.set(0.0); 
        } //Stop Roller-----------------------ShootStick (3) 
        if (rollerBallSwitch.get() == true) { 
        	if(shootStick.getRawButton(4)){ 
        		if5 = false; 
        		SmartDashboard.putString("Roller Direction", "Cannot go Backwards!"); 
        		roller.set(0.0); 
            }//Can't go Backwards 
        	roller.set(0.0); 
		} else { 
			if(shootStick.getRawButton(4)){ 
				if5 = false; 
				if(if4 == false){ 
		            SmartDashboard.putString("Roller Direction", "Backwards"); 
		            roller.set(-0.75); 
		            if4 = true; 
				}else{ 
					SmartDashboard.putString("Roller Direction", "Stopped"); 
					roller.set(0.0); 
					if4 = false; 
				} 
		    }//Roll Roller Backwards----ShootStick (4) 
		} 
        if(shootStick.getRawButton(5)){ 
        	if4 = false; 
        	if(if5 == false){ 
        		SmartDashboard.putString("Roller Direction", "Forwards"); 
        		roller.set(0.75); 
        		if5 = true; 
        	}else{ 
        		SmartDashboard.putString("Roller Direction", "Stopped"); 
        		roller.set(0.0); 
        		if5 = false; 
        	} 
        } //Roll Roller Forwards----------ShootStick (5) 
         
	    //Manual Gyro Reset
	    if(driveStick.getRawButton(2)){ //Manually reset Gyro-------------------DriveStick (2)
	    	gyro.reset();
	    }
}
//TEST SEQUENCE (RUNS ONCE)
    public void testInit(){
    	if(isEnabled()){
    		SmartDashboard.putString("Test Sequence", "Initializing.");
    		Timer.delay(0.5);
    		SmartDashboard.putString("Test Sequence", "Initializing..");
    		Timer.delay(0.5);
    		SmartDashboard.putString("Test Sequence", "Initializing...");
    		Timer.delay(0.5);
    		SmartDashboard.putString("Test Sequence", "<!> Preparing to Start Test Sequence <!>");
    		Timer.delay(0.5);
    		SmartDashboard.putString("Test Sequence", "<!> Please keep your distance. The test will start in 10 seconds. <!>");
    		Timer.delay(10.0);
    		SmartDashboard.putString("Test Sequence", "Now testing Left Drive");
    		
    		//Left Drive Sequence (0 to 1.0, then back to 0. Then 0 to -1.0, then back to 0.
    		sequenceCreator(leftDrive);
    		SmartDashboard.putString("Test Sequence", "Success! Now testing Right Drive");
    		
    		//Right Drive Sequence (0 to 1.0, then back to 0. Then 0 to -1.0, then back to 0.
    		sequenceCreator(rightDrive);
    		SmartDashboard.putString("Test Sequence", "Success! Now testing Roller");
    		
    		//Roller Drive Sequence (0 to 1.0, then back to 0. Then 0 to -1.0, then back to 0.
    		sequenceCreator(roller);
    		SmartDashboard.putString("Test Sequence", "Success! Now testing Manipulator Arm");
    		
    		//Press Roller Up Switch
    		Timer.delay(0.5);
    		SmartDashboard.putString("Test Sequence", "Success! Now testing Roller Up Switch");
    		Timer.delay(0.5);
    		SmartDashboard.putString("Test Sequence", "Please press the Roller Up Switch");
    		while(rollerUpSwitch.get() == false){
    			Timer.delay(0.5);
    			SmartDashboard.putString("Test Sequence", "Please press the Roller Up Switch");
    		}
    		
    		//Press Roller Down Switch
    		Timer.delay(0.5);
    		SmartDashboard.putString("Test Sequence", "Success! Now testing Roller Down Switch");
    		Timer.delay(0.5);
    		SmartDashboard.putString("Test Sequence", "Please press the Roller Down Switch");
    		while(rollerDownSwitch.get() == false){
    			Timer.delay(0.5);
    			SmartDashboard.putString("Test Sequence", "Please press the Roller Down Switch");
    		}
    		
    		//Press Roller Ball Switch
    		Timer.delay(0.5);
    		SmartDashboard.putString("Test Sequence", "Success! Now testing Roller Ball Switch");
    		Timer.delay(0.5);
    		SmartDashboard.putString("Test Sequence", "Please press the Roller Ball Switch");
    		while(rollerBallSwitch.get() == false){
    			Timer.delay(0.5);
    			SmartDashboard.putString("Test Sequence", "Please press the Roller Ball Switch");
    		}
    		SmartDashboard.putString("Test Sequence", "<!> You have successfuly completed the Test Sequence <!>");
     	}
    } 
}
