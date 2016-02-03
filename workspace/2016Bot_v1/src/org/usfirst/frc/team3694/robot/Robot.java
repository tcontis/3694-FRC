//Defines stuff
package org.usfirst.frc.team3694.robot;

//Import stuff here
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
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
	//NIVision Objects and Variables
	public static int session;
	public static Image frame;
	
	//SmartDashboard Objects and Variables
	public static SendableChooser chooser;
	public static String defense;
	
	//Drive and Chassis Objects and Variables
	public static RobotDrive chassis;
	public static Victor frontDrive = new Victor(0);
	public static Victor rearDrive = new Victor(1);
	public static Victor roller = new Victor(3);
	public static Victor rollerTilt = new Victor(4);
	
	//Joystick Objects and Variables
	public static Joystick driveStick = new Joystick(0);
	public static Joystick shootStick = new Joystick(1);
	public static double shootY;

	

//ROBOT INIZILIZATION
    public void robotInit() {
    	//Configure Image and Session for NIVISIon
    	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        
        //Stream and Capture Image
        NIVision.IMAQdxConfigureGrab(session);
        CameraServer.getInstance().setImage(frame);
    	
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
    	frontDrive.setInverted(true);
    	rearDrive.setInverted(true);
    	
    	//Configure motors present in Chassis, configure safety
    	chassis = new RobotDrive(frontDrive, rearDrive);
    	chassis.setExpiration(0.1);
    }

//AUTONOMOUS
    public void autonomous() {
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