/**
 * Team 3694 NAHS Warbotz
 * FRC 2016 Robot Code
 * 
 * Version 0.8.2
 * 
 * Changes: 
 * -Fixed Values not Updating in SmartDashboard 
 * -Autonomous code fixed
 * -Implemented PID
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
	public static double shootY = shootStick.getAxis(Joystick.AxisType.kY);
	
	//Analog Sensors
	ADXL362 accel = new ADXL362(SPI.Port.kOnboardCS1, Accelerometer.Range.k16G);
	ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	
	
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
      	SmartDashboard.putNumber("Gyro Angle", 0);
      	SmartDashboard.putNumber("Acceleration", 0);
      	
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
    public void autonomousInit() {
    	Timer.delay(0.005);
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
    					if(gyro.getAngle() == 270){
    					chassis.drive(0.5, -90);
    					}
    					move(45.5, 1);
    				}else if(cpoint.equals("c")){
    					if(gyro.getAngle() == 270){
        					chassis.drive(0.5, -90);
        					}
    					move(91, 1);
    				}else if(cpoint.equals("d")){
    					if(gyro.getAngle() == 270){
        					chassis.drive(0.5, -90);
        					}
    					move(136.5, 1);
    				}else if(cpoint.equals("e")){
    					if(gyro.getAngle() == 270){
        					chassis.drive(0.5, -90);
        					}
    					move(182, 1);
    				}
    			//if point is b
    			}else if(point.equals("b")){
    				if(cpoint.equals("a")){
    					if(gyro.getAngle() == 90){
        					chassis.drive(0.5, -90);
        					}
    					move(45.5, 1);
    				}else if(cpoint.equals("c")){
    					if(gyro.getAngle() == 270){
        					chassis.drive(0.5, -90);
        					}
    					move(45.5, 1);
    				}else if(cpoint.equals("d")){
    					if(gyro.getAngle() == 270){
        					chassis.drive(0.5, -90);
        					}
    					move(91, 1);
    				}else if(cpoint.equals("e")){
    					if(gyro.getAngle() == 270){
        					chassis.drive(0.5, -90);
        					}
    					move(136.5, 1);
    				}
    			//if point is c
    			}else if(point.equals("c")){
    				if(cpoint.equals("a")){
    					if(gyro.getAngle() == 90){
        					chassis.drive(0.5, -90);
        					}
    					move(45.5, 1);
    				}else if(cpoint.equals("b")){
    					if(gyro.getAngle() == 90){
        					chassis.drive(0.5, -90);
        					}
    					move(91, 1);
    				}else if(cpoint.equals("d")){
    					if(gyro.getAngle() == 270){
        					chassis.drive(0.5, -90);
        					}
    					move(45.5, 1);
    				}else if(cpoint.equals("e")){
    					if(gyro.getAngle() == 270){
        					chassis.drive(0.5, -90);
        					}
    					move(91, 1);
    				}
    			//if point is d
    			}else if(point.equals("d")){
    				if(cpoint.equals("a")){
    					if(gyro.getAngle() == 90){
        					chassis.drive(0.5, -90);
        					}
    					move(45.5, 1);
    				}else if(cpoint.equals("b")){
    					if(gyro.getAngle() == 90){
        					chassis.drive(0.5, -90);
        					}
    					move(91, 1);
    				}else if(cpoint.equals("c")){
    					if(gyro.getAngle() == 90){
        					chassis.drive(0.5, -90);
        					}
    					move(136.5, 1);
    				}else if(cpoint.equals("e")){
    					if(gyro.getAngle() == 270){
        					chassis.drive(0.5, -90);
        					}
    					move(45.5, 1);
    				}
    			//if point is e
    			}else if(point.equals("e")){
    				if(cpoint.equals("a")){
    					if(gyro.getAngle() == 90){
        					chassis.drive(0.5, -90);
        					}
    					move(45.5, 1);
    				}else if(cpoint.equals("b")){
    					if(gyro.getAngle() == 90){
        					chassis.drive(0.5, -90);
        					}
    					move(91, 1);
    				}else if(cpoint.equals("c")){
    					if(gyro.getAngle() == 90){
        					chassis.drive(0.5, -90);
        					}
    					move(136.5, 1);
    				}else if(cpoint.equals("d")){
    					if(gyro.getAngle() == 90){
        					chassis.drive(0.5, -90);
        					}
    					move(182, 1);
    				}
    			}
    		}
    }
    public void autonomousPeriodic(){
    	SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
      	SmartDashboard.putNumber("Acceleration", accel.getX());
    }
//TELEOPERATED
    public void teleopInit() {
    	//Enable Chassis Safety
    	chassis.setSafetyEnabled(true);
    	gyro.reset();
    	leftEncoder.reset();
    	rightEncoder.reset();
    }    
    	//While in Teleoperated mode.
    public void teleopPeriodic(){
            //Slight delay required
        	Timer.delay(0.005);
        	SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
          	SmartDashboard.putNumber("Acceleration", accel.getY());
        	
        	//Drive chassis using Arcade Drive (One Joystick)
            chassis.arcadeDrive(driveStick);
            
            //Set the roller's tilt to be equal to the Shooting Joystick's Y
  
            rollerTilt.set(shootY);
            if(shootY > rollerTilt.get()){
            	rollerTilt.set(rollerTilt.get() + 0.1);
            }
            if(shootStick.getRawButton(2)){
            	roller.set(-0.75);
            }
            if(shootStick.getRawButton(3)){
            	roller.set(0);
            }
            if(shootStick.getRawButton(4)){
            	roller.set(0.75);
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