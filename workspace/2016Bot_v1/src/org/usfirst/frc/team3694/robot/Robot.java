/**
 * Team 3694 NAHS Warbotz
 * FRC 2016 Robot Code
 * 
 * Version 0.75
 * 
 * Changes: 
 * -Limit switches added 
 * -Autonomous code updated, 
 * -Roller direction/stop controlled by Joystick buttons
 */

//Defines stuff
package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//ROBOT CODE FROM THIS POINT ON
public class Robot extends SampleRobot {
	//SmartDashboard Objects and Variables
	public static SendableChooser chooser2;
	public static String point;
	public static String cpoint = "";
	public static String defense = "";
	
	//Drive and Chassis Objects and Variables
	public static RobotDrive chassis;
	public static Victor leftDrive = new Victor(0);
	public static Victor rightDrive = new Victor(1);
	public static Victor rollerTilt = new Victor(2);
	public static Victor roller = new Victor(3);
	
	//Joystick Objects and Variables
	public static Joystick driveStick = new Joystick(0);
	public static Joystick shootStick = new Joystick(1);
	public static double shootY;
	
	//Analog Sensors
	ADXL362 accel = new ADXL362(SPI.Port.kOnboardCS0, Accelerometer.Range.k16G);
	ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS1);
	
	//DIO Sensors
	Encoder leftEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
	Encoder rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
	DigitalInput rollerUpSwitch = new DigitalInput(7);
	DigitalInput rollerDownSwitch = new DigitalInput(8);
	DigitalInput rollerBallSwitch = new DigitalInput(9);
	
	//Values for Encoders
	public static final double distPerRev = 4.05 * Math.PI;
	public static final double distPerCount = distPerRev/360;

//ROBOT INIZILIZATION
	public void robotInit() {
    	//Stream and Capture Image
    	CameraServer.getInstance().startAutomaticCapture("cam0");
    	
    	//Table selection for desination
        chooser2 = new SendableChooser();
      	chooser2.initTable(NetworkTable.getTable("Destination Chooser"));
      	chooser2.addDefault("Nothing", "nothing");
      	chooser2.addObject("Point A" ,"a"); 
      	chooser2.addObject("Point B" ,"b");
      	chooser2.addObject("Point C" ,"c");
      	chooser2.addObject("Point D" ,"d");
      	chooser2.addObject("Point E" ,"e");
      	SmartDashboard.putData("Destination Chooser", chooser2);
      	
      	//Initialize SmartDashboard Fields
      	SmartDashboard.putString("Current Point", "");
      	SmartDashboard.putString("Error","");
      	SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
      	SmartDashboard.putNumber("Acceleration", accel.getX());
      	
      	//Invert Chassis Motors so that they roll in the right direction
    	leftDrive.setInverted(true); 
    	rightDrive.setInverted(true);
    	
    	//Configure motors present in Chassis, configure safety
    	chassis = new RobotDrive(leftDrive, rightDrive);
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
    public void autonomous() {
    	chassis.setSafetyEnabled(false);
    	//Reset Gyro
    	gyro.reset();
    	leftEncoder.reset();
    	rightEncoder.reset();
    	//Set a string to find out the defense chosen
    	point = chooser2.getSelected().toString();
    	cpoint = SmartDashboard.getString("Current Point");
    		
    		if(point.equals(cpoint)){
    			SmartDashboard.putString("Error", "Current Point matches Destination Point");
    		}else{
    			//if point is a
    			if(point.equals("a")){
    				if(cpoint.equals("b")){
    					chassis.drive(0.5, -90);
    					move(45.5, 1);
    				}else if(cpoint.equals("c")){
    					chassis.drive(0.5, -90);
    					move(91, 1);
    				}else if(cpoint.equals("d")){
    					chassis.drive(0.5, -90);
    					move(136.5, 1);
    				}else if(cpoint.equals("e")){
    					chassis.drive(0.5, -90);
    					move(182, 1);
    				}
    			//if point is b
    			}else if(point.equals("b")){
    				if(cpoint.equals("a")){
    					chassis.drive(0.5, 90);
    					move(45.5, 1);
    				}else if(cpoint.equals("c")){
    					chassis.drive(0.5, -90);
    					move(45.5, 1);
    				}else if(cpoint.equals("d")){
    					chassis.drive(0.5, -90);
    					move(91, 1);
    				}else if(cpoint.equals("e")){
    					chassis.drive(0.5, -90);
    					move(136.5, 1);
    				}
    			//if point is c
    			}else if(point.equals("c")){
    				if(cpoint.equals("a")){
    					chassis.drive(0.5, 90);
    					move(45.5, 1);
    				}else if(cpoint.equals("b")){
    					chassis.drive(0.5, 90);
    					move(91, 1);
    				}else if(cpoint.equals("d")){
    					chassis.drive(0.5, -90);
    					move(45.5, 1);
    				}else if(cpoint.equals("e")){
    					chassis.drive(0.5, -90);
    					move(91, 1);
    				}
    			//if point is d
    			}else if(point.equals("d")){
    				if(cpoint.equals("a")){
    					chassis.drive(0.5, 90);
    					move(45.5, 1);
    				}else if(cpoint.equals("b")){
    					chassis.drive(0.5, 90);
    					move(91, 1);
    				}else if(cpoint.equals("c")){
    					chassis.drive(0.5, 90);
    					move(136.5, 1);
    				}else if(cpoint.equals("e")){
    					chassis.drive(0.5, -90);
    					move(45.5, 1);
    				}
    			//if point is e
    			}else if(point.equals("e")){
    				if(cpoint.equals("a")){
    					chassis.drive(0.5, 90);
    					move(45.5, 1);
    				}else if(cpoint.equals("b")){
    					chassis.drive(0.5, 90);
    					move(91, 1);
    				}else if(cpoint.equals("c")){
    					chassis.drive(0.5, 90);
    					move(136.5, 1);
    				}else if(cpoint.equals("d")){
    					chassis.drive(0.5, 90);
    					move(182, 1);
    				}
    			}
    		}
    } 		
//TELEOPERATED
    public void operatorControl() {
    	//Enable Chassis Safety
    	chassis.setSafetyEnabled(true);
        
    	//While in Teleoperated mode.
        while (isOperatorControl() && isEnabled()) {
            //Slight delay required
        	Timer.delay(0.005);
            
        	//Drive chassis using Arcade Drive (One Joystick)
            chassis.arcadeDrive(driveStick);
            
            //Set the roller's tilt to be equal to the Shooting Joystick's Y
            shootY = shootStick.getAxis(Joystick.AxisType.kY);
            rollerTilt.set(shootY);
            if(shootStick.getRawButton(2)){
            	roller.set(-0.75);
            }
            if(shootStick.getRawButton(3)){
            	roller.set(0);
            }
            if(shootStick.getRawButton(4)){
            	roller.set(0.75);
            }
            while(rollerUpSwitch.get() == true){
            	rollerTilt.set(-0.5);
            }
            while(rollerDownSwitch.get() == true){
            	rollerTilt.set(0.5);
            }
            while(rollerBallSwitch.get() == true){
            	roller.set(0);
            }
        }
    }
}
//END BRACKET, ROBOT CODE ENDS