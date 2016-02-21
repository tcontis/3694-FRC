/**
 * Team 3694 NAHS Warbotz
 * FRC 2016 Robot Code
 * 
 * Version 0.9.0
 * 
 * Changes: 
 * -Fixed SmartDashboard error
 * -Directions correct
 * -Cleaned up unused values
 * -Corrected Autonomous
 */

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
	//Destination Point
	public static SendableChooser chooser;
	//CurrentPoint
	public static SendableChooser chooser2;
	//Camera
	public static SendableChooser chooser3;
	public static int point;
	public static int cpoint;
	public static int status;
	public static int rawDist;
	public static double dist;
	public static boolean left;
	
	//Drive and Chassis Objects and Variables
	public static RobotDrive chassis;
	public static Victor leftDrive = new Victor(0);
	public static Victor rightDrive = new Victor(1);
	public static Victor rollerTilt = new Victor(2);
	public static Victor roller = new Victor(3);
	
	//Joystick Objects and Variables
	public static Joystick driveStick = new Joystick(0);
	public static Joystick shootStick = new Joystick(1);
	public static double shootY = shootStick.getAxis(Joystick.AxisType.kY);
	
	//Analog Sensors
	public static ADXL362 accel = new ADXL362(SPI.Port.kOnboardCS1, Accelerometer.Range.k16G);
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

	//DIO Sensors
	Encoder leftEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
	Encoder rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
	Encoder rollerEncoder = new Encoder(5, 6, false, Encoder.EncodingType.k4X);
	DigitalInput rollerUpSwitch = new DigitalInput(7);
	DigitalInput rollerDownSwitch = new DigitalInput(8);
	DigitalInput rollerBallSwitch = new DigitalInput(9);
	
	//Values for Encoders
	public static final double distPerRev = 4.05 * Math.PI;
	public static final double distPerCount = distPerRev/360;

//ROBOT INIZILIZATION
	public void robotInit() {
    	//Table selection for desination
    	chooser = new SendableChooser();
      	chooser.initTable(NetworkTable.getTable("Destination Chooser"));
      	chooser.addDefault("Nothing", 0);
      	chooser.addObject("Point A" , 1); 
      	chooser.addObject("Point B" , 2);
      	chooser.addObject("Point C" , 3);
      	chooser.addObject("Point D" , 4);
      	chooser.addObject("Point E" , 5);
      	SmartDashboard.putData("Destination Chooser", chooser);
      	
        chooser2 = new SendableChooser();
      	chooser2.initTable(NetworkTable.getTable("Current Point Chooser"));
      	chooser2.addDefault("Nothing", 0);
      	chooser2.addObject("Point A" , 1); 
      	chooser2.addObject("Point B" , 2);
      	chooser2.addObject("Point C" , 3);
      	chooser2.addObject("Point D" , 4);
      	chooser2.addObject("Point E" , 5);
      	SmartDashboard.putData("Current Point", chooser2);
      	
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
      	SmartDashboard.putNumber("Gyro Angle", 0);
      	SmartDashboard.putNumber("Gyro Angle", 0);
      	SmartDashboard.putNumber("Gyro Rate", 0);
      	SmartDashboard.putNumber("Acceleration X-Axis", 0);
      	SmartDashboard.putNumber("Acceleration Y-Axis", 0);
      	SmartDashboard.putNumber("Acceleration Z-Axis", 0);
      	SmartDashboard.putString("Roller Direction", "---");
    	
    	//Configure motors present in Chassis, configure safety
    	chassis = new RobotDrive(leftDrive, rightDrive);
    	gyro.calibrate();
    	chassis.setExpiration(0.1);
    }
//ENCODER COUNTS
    public void move(double dist, double speed){
    	double cntsNeeded = dist/distPerCount;
    	double rnddCountsNeeded = Math.round(cntsNeeded * 100.0)/100.0;
    	leftEncoder.reset();
    	rightEncoder.reset();
    	chassis.drive(speed, 0.0);
    	if(leftEncoder.get() > rnddCountsNeeded && rightEncoder.get() > rnddCountsNeeded){
    		chassis.drive(0.0, 0.0);
    		leftEncoder.reset();
        	rightEncoder.reset();
    	}
    }

//AUTONOMOUS
    public void autonomousInit() {
    	Timer.delay(0.005);
    	chassis.setSafetyEnabled(false);
    	//Reset Gyro
    	gyro.reset();
    	leftEncoder.reset();
    	rightEncoder.reset();
    	//Get options we selected before autonomous
    	cpoint = Integer.parseInt(chooser2.getSelected().toString());
    	point = Integer.parseInt(chooser.getSelected().toString());
    	status = Integer.parseInt(chooser3.getSelected().toString());
    		//camera
    		if(status == 0){
    			CameraServer.getInstance().startAutomaticCapture("cam0");
    		}else{
    			//No video
    		}
    			
    		//autonomous move
    		if(point - cpoint == 0 || cpoint - point == 0){
    			SmartDashboard.putString("Error", "Current Point matches Destination Point");
    		}else{
    			if(point == 0 || cpoint == 0){
    				SmartDashboard.putString("Error", "You chose 'Nothing' as one of the points");
    			}else{
    			rawDist = point - cpoint;
    			dist = Math.abs(rawDist * 45.5);
    			move(6, 0.5);
    			if(rawDist > 0){
    				left = false;
    				while(gyro.getAngle() > -90){
    					chassis.drive(0.5, 0.5);
    				}
    				
    			}else{
    				left = true;
    				while(gyro.getAngle() < 90){
    					chassis.drive(0.5, 0.5);
    				}
    			}
    			
    			move(dist, 0.75);
    			if(left == false){
    				while(gyro.getAngle() < 90){
    					chassis.drive(0.5, 0.5);
    				}
    				
    			}else{
    				while(gyro.getAngle() > -90){
    					chassis.drive(0.5, -0.5);
    				}
    				
    			}
    			move(156, 0.5);
    		
    			}
    		}
    }
    public void autonomousPeriodic(){
    	SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
    	SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
    	SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
    	SmartDashboard.putNumber("Acceleration X-Axis", accel.getX());
      	SmartDashboard.putNumber("Acceleration Y-Axis", accel.getY());
      	SmartDashboard.putNumber("Acceleration Z-Axis", accel.getZ());
    }
//TELEOPERATED
    public void teleopInit() {
    	//Enable Chassis Safety
    	chassis.setSafetyEnabled(true);
    	leftEncoder.reset();
    	rightEncoder.reset();
    }    
    	//While in Teleoperated mode.
    public void teleopPeriodic(){
            //Slight delay required
        	Timer.delay(0.005);
        	
        	SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
        	SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
        	SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
        	SmartDashboard.putNumber("Acceleration X-Axis", accel.getX());
          	SmartDashboard.putNumber("Acceleration Y-Axis", accel.getY());
          	SmartDashboard.putNumber("Acceleration Z-Axis", accel.getZ());
          	SmartDashboard.putNumber("Encoder (r)", rightEncoder.getRaw());
        	
        	//Drive chassis using Arcade Drive (One Joystick)
            chassis.arcadeDrive(driveStick);
            
            //Set the roller's tilt to be equal to the Shooting Joystick's Y
            rollerTilt.set(-shootY);
            if(shootY > rollerTilt.get()){
            	rollerTilt.set(rollerTilt.get() + 0.1);
            }
            if(shootStick.getRawButton(2)){
            	SmartDashboard.putString("Roller Direction", "Backwards");
            	roller.set(-0.75);
            }
            if(shootStick.getRawButton(3)){
            	roller.set(0);
            	SmartDashboard.putString("Roller Direction", "Stopped");
            }
            if(shootStick.getRawButton(4)){
            	roller.set(0.75);
            	SmartDashboard.putString("Roller Direction", "Forwards");
            }
            if(driveStick.getRawButton(2)){
            	gyro.reset();
            }
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
//END BRACKET, ROBOT CODE ENDS