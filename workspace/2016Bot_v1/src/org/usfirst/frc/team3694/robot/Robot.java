/***************************************************\
 * Team 3694 NAHS Warbotz
 * FRC 2016 Robot Code
 * 
 * Version 1.0.0
 * 
 * Changes: 
 * -Cleaned Up Code (Despaghettified)
 * -Made code shorter and more efficient
 * -Reformatted Autonomous
 * -Fixes and Tweaks to Code
 * -Roller encoder added
 * -Changed project name
\***************************************************/

//Defines stuff
package org.usfirst.frc.team3694.robot;

//Imports
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.*;
import edu.wpi.first.wpilibj.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

//ROBOT CODE FROM THIS POINT ON
public class Robot extends IterativeRobot {
	
	//SmartDashboard Objects and Variables
	public static SendableChooser chooser; //Destination Point
	public static SendableChooser chooser2; //Current Point
	public static SendableChooser chooser3; //Camera
	public static int point; //Destination Point Int
	public static int cpoint; //Current Point Int
	public static int status; //Camera Status
	public static int rawDist; //Int distance between points
	public static double dist; //Encoder distance between points
	
	//PWM Objects and Variables
	public static RobotDrive chassis; //Robot Chassis
	public static Victor leftDrive = new Victor(0); //Left Drive Motors (Both Front and Rear)---------------------------------PWM (0)
	public static Victor rightDrive = new Victor(1); //Right Drive Motors (Both Front and Rear)-------------------------------PWM (1)
	public static Victor rollerTilt = new Victor(2); //Roller Tilt (Forwards and Backwards)-----------------------------------PWM (2)
	public static Victor roller = new Victor(3); //Roller Motor (Forwards and Backwards)--------------------------------------PWM (3)
	
	//USB Objects and Variables
	public static Joystick driveStick = new Joystick(0); //Joystick used for Driving------------------------------------------USB (0)
	public static Joystick shootStick = new Joystick(1); //Joystick used for Shooting-----------------------------------------USB (1)
	public static double shootY = shootStick.getAxis(Joystick.AxisType.kY); //The y values of ShootStick
	
	//SPI Objects and Variables
	public static ADXL362 accel = new ADXL362(SPI.Port.kOnboardCS1, Accelerometer.Range.k16G); //Accelerometer----------------SPI (1)
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0); //Gyroscope-----------------------------------SPI (0)

	//DIO Objects and Variables
	public static Encoder leftEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X); //Left Side Driver Encoder---------DIO (0,1)
	public static Encoder rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X); //Right Side Driver Encoder------DIO (2,3)
	public static Encoder rollerEncoder = new Encoder(5, 6, false, Encoder.EncodingType.k4X); //Roller Motor Encoder----------DIO (5,6)
	public static DigitalInput rollerUpSwitch = new DigitalInput(7); //Roller Upper Limit Switch------------------------------DIO (7)
	public static DigitalInput rollerDownSwitch = new DigitalInput(8); //Roller Lower Limit Switch----------------------------DIO (8)
	public static DigitalInput rollerBallSwitch = new DigitalInput(9); //Roller Ball Detection Limit Switch-------------------DIO (9)
	public static final double distPerRev = 4.05 * Math.PI; //Distance per revolution
	public static final double distPerCount = distPerRev/249; //Distance per encoder count

//ROBOT INIZILIZATION (RUNS ONCE)
	public void robotInit() {
    	//Table Creation
    	createTable(chooser, "Destination Chooser"); //destination point table
    	createTable(chooser2, "Current Point Chooser"); //current point table
      	
        //Table selection for Camera Status
      	chooser3 = new SendableChooser();
      	chooser3.initTable(NetworkTable.getTable("RoboRio Camera"));
      	chooser3.addDefault("Enable", 0);
      	chooser3.addObject("Disable", 1); 
      	SmartDashboard.putData("RoboRio Camera", chooser3);
      	
      	//Initialize SmartDashboard Fields
      	SmartDashboard.putString("Current Point", chooser2.getSelected().toString());
      	SmartDashboard.putString("Destination Point", chooser.getSelected().toString());
      	SmartDashboard.putString("Roborio Camera Status:", chooser3.getSelected().toString());
      	SmartDashboard.putString("Error","");
      	dashVarUpdate(0 ,0, 0, 0, 0, 0, 0);
      	SmartDashboard.putString("Roller Direction", "---");
      	resetEncoders(); //reset encoders
      	
    	//Configure motors present in Chassis, configure safety
    	chassis = new RobotDrive(leftDrive, rightDrive);
    	gyro.calibrate();
    	chassis.setExpiration(0.1);
    }
//CREATES A TABLE WITH CHOOSER
	public void createTable(SendableChooser a, String b){
		a = new SendableChooser();
      	a.initTable(NetworkTable.getTable(b));
      	a.addDefault("Nothing", 0);
      	a.addObject("Point A" , 1); 
      	a.addObject("Point B" , 2);
      	a.addObject("Point C" , 3);
      	a.addObject("Point D" , 4);
      	a.addObject("Point E" , 5);
      	SmartDashboard.putData(b, a);
	}
//RESET ENCODERS
	public void resetEncoders(){
		leftEncoder.reset(); //Reset left encoder
    	rightEncoder.reset(); //Reset right encoder
    	rollerEncoder.reset();
	}
//UPDATE VARIABLES IN SMARTDASHBOARD
	public void dashVarUpdate(double a, double b, double c, double d, double e, double f, double g){
		SmartDashboard.putNumber("Gyro Angle", a);
      	SmartDashboard.putNumber("Gyro Angle Dial", b);
      	SmartDashboard.putNumber("Gyro Rate", c);
      	SmartDashboard.putNumber("Acceleration X-Axis", d);
      	SmartDashboard.putNumber("Acceleration Y-Axis", e);
      	SmartDashboard.putNumber("Acceleration Z-Axis", f);
      	SmartDashboard.putNumber("Roller Encoder", g);
	}
//ENCODER MOVE COUNTS
    public void move(double dist, double speed){
    	double cntsNeeded = dist/distPerCount; //Counts needed for distance
    	double rnddCountsNeeded = Math.round(cntsNeeded); //Rounded Counts Needed
    	resetEncoders(); //reset encoders
    	chassis.drive(speed, 0.0); //Drive until encoder counts met
    	if(leftEncoder.get() > rnddCountsNeeded && rightEncoder.get() > rnddCountsNeeded){
    		chassis.drive(0.0, 0.0);
    		resetEncoders(); //reset encoders
    	}
    }
//AUTONOMOUS INITIATION (RUNS ONCE)
    public void autonomousInit() {
    	Timer.delay(0.005); //Slight delay required
    	chassis.setSafetyEnabled(false);
    	gyro.reset(); //Reset Gyro
    	resetEncoders(); //reset encoders
    	
    	//Get options we selected before autonomous
    	cpoint = Integer.parseInt(chooser2.getSelected().toString());
    	point = Integer.parseInt(chooser.getSelected().toString());
    	status = Integer.parseInt(chooser3.getSelected().toString());
    		//Camera Status
    		if(status == 0){
    			CameraServer.getInstance().startAutomaticCapture("cam0");
    		}
    			
    		//Autonomous Move
    		if(point == 0 || cpoint == 0){ //If points are nothing
    			SmartDashboard.putString("Error", "You chose Nothing as one of the points"); //Display error
    		}else{
    			rawDist = point - cpoint; //Int distance between points
    			dist = Math.abs(rawDist * 45.5); //Encoder distance between points
    			move(6, 0.5); //move 6 Inches
    			
    			//If distance is greater than 0, turn left until Gyro is at 90, else turn right until Gyro is 90
    			if(rawDist > 0){
    				while(gyro.getAngle() > -90){
    					chassis.drive(0.5, 0.5);
    					move(dist, 0.75); //move calculated distance
    					while(gyro.getAngle() < 90){ // turn right to be straight
        					chassis.drive(0.5, 0.5);
        				}
    					move(156, 0.5); //move 156 inches
    				}	
    			}else{
    				while(gyro.getAngle() < 90){
    					chassis.drive(0.5, 0.5);
    					move(dist, 0.75); //move calculated distance
    					while(gyro.getAngle() < 90){ // turn left to be straight
        					chassis.drive(0.5, 0.5);
        				}
    					move(156, 0.5); //move 156 inches
    				}
    			}
    		}
    }
//AUTONOMOUS PERIODIC (CALLED EVERY FIELD CYCLE)  
    public void autonomousPeriodic(){
    	dashVarUpdate(gyro.getAngle(), gyro.getAngle(), gyro.getRate(), accel.getX(), accel.getY(), accel.getZ(), 0); //Update SmartDashboard Values
    }
//TELEOPERATED INITIATION (RUNS ONCE)
    public void teleopInit() {
    	chassis.setSafetyEnabled(true); //Enable Safety
    	resetEncoders(); //reset encoders
    }    
//TELEOPERATED PERIODIC (CALLED EVERY FIELD CYCLE)
    public void teleopPeriodic(){
        	Timer.delay(0.005); //Slight delay required
        	dashVarUpdate(gyro.getAngle(), gyro.getAngle(), gyro.getRate(), accel.getX(), accel.getY(), accel.getZ(), rollerEncoder.get()); //Update SmartDashboard Values
            chassis.arcadeDrive(driveStick);//Drive chassis using Arcade Drive (One Joystick)
            rollerTilt.set(shootY);//Set the roller's tilt to be equal to the Shooting Joystick's Y
            
            //Rough PID Implementation
            if((shootY - rollerTilt.get()) >= 0.1){
            	rollerTilt.set(rollerTilt.get() + 0.1);
            }
            //When Button 4 on shootStick pressed, Roll Roller Backwards
            if(shootStick.getRawButton(4)){
            	SmartDashboard.putString("Roller Direction", "Backwards");
            	roller.set(-0.75);
            }
         	//When Button 3 on shootStick pressed, Stop Roller
            if(shootStick.getRawButton(3)){
            	roller.set(0);
            	SmartDashboard.putString("Roller Direction", "Stopped");
            }
            //When Button 5 on shootStick pressed, Roll Roller Forwards
            if(shootStick.getRawButton(5)){
            	roller.set(0.75);
            	SmartDashboard.putString("Roller Direction", "Forwards");
            }
            //When Button 2 on shootStick pressed, manually reset Gyro
            if(driveStick.getRawButton(2)){
            	gyro.reset();
            }
            //While Limit switches tripped, change directions.
            while(rollerUpSwitch.get() == false){
            	rollerTilt.set(-0.5);
            }
            while(rollerDownSwitch.get() == false){
            	rollerTilt.set(0.5);
            }
            while(rollerBallSwitch.get() == false){
            	roller.set(0);
            }
    }
}