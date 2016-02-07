//Defines stuff
package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
//Import stuff here
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//ROBOT CODE FROM THIS POINT ON
public class Robot extends SampleRobot {
	//SmartDashboard Objects and Variables
	public static SendableChooser chooser;
	public static String defense;
	
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
	AnalogGyro gyro = new AnalogGyro(0);
	Encoder encoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

//ROBOT INIZILIZATION
    public void robotInit() {
    	//Stream and Capture Image
    	CameraServer.getInstance().startAutomaticCapture("cam0");
        
        //Initialize SmartDashboard Autonomous Defense Chooser with Options
        SendableChooser chooser = new SendableChooser();
    	chooser.initTable(NetworkTable.getTable("Defense Chooser"));
    	chooser.addDefault("Low Bar", "lowbar");
    	chooser.addObject("Portcullis" ,"portcullis"); 
    	chooser.addObject("Cheval de Frise" ,"cheval");
    	chooser.addObject("Moat" ,"moat");
    	chooser.addObject("Ramparts" ,"ramparts");
    	chooser.addObject("Drawbridge" ,"drawbridge");
    	chooser.addObject("Sally Port" ,"sallyport");
    	chooser.addObject("Rock Wall" ,"rockwall");
    	chooser.addObject("Rough Terrain" ,"roughterrain");
    	SmartDashboard.putData("Defense Chooser", chooser);
    	
    	//Invert Chassis Motors so that they roll in the right direction
    	leftDrive.setInverted(true);
    	rightDrive.setInverted(true);
    	
    	//Configure motors present in Chassis, configure safety
    	chassis = new RobotDrive(leftDrive, rightDrive);
    	chassis.setExpiration(0.1);
    }

//AUTONOMOUS
    public void autonomous() {
    	//Reset Gyro
    	gyro.reset();
    	//Set a string to find out the defense chosen
    	defense = chooser.getSelected().toString();
    	
    	//Start of loops to figure out which defense selected
    	if(defense.equals("lowbar")){
    		//Lowbar autonomous
    		chassis.setSafetyEnabled(false);
    		
    		//Drive forward for two seconds at half speed, the stop
    	    chassis.drive(0.5, 0.0);	
    	    Timer.delay(2.0);
    	    chassis.drive(0.0, 0.0);		
    	}else if(defense.equals("portcullis")){
    		//Portcullis autonomous
    	}else if(defense.equals("cheval")){
    		//Cheval de Frise autonomous
    	}else if(defense.equals("moat")){
    		//Moat autonomous
    	}else if(defense.equals("ramparts")){
    		//Rampart autonomous
    	}else if(defense.equals("drawbridge")){
    		//Drawbridge autonomous
    	}else if(defense.equals("sallyport")){
    		//Sallyport autonomous
    	}else if(defense.equals("rockwall")){
    		//Rock Wall autonomous
    	}else if(defense.equals("roughterrain")){
    		//Rough Terrain autonomous
    	}else{
    		//If none of the above are the selected defense, then there is an error
    		SmartDashboard.putString("Error", "No defenses match");
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
        }
    }
}
//END BRACKET, ROBOT CODE ENDS
