//Defines stuff
package org.usfirst.frc.team3694.robot;

//import stuff here
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
    int session;
    Image frame;
	RobotDrive chassis;
    Joystick driveStick;
    Joystick shootStick;
    Victor frontDrive = new Victor(0);
    Victor rearDrive = new Victor(1);
    Victor roller = new Victor(3);
    Victor rollerTilt = new Victor(4);
    SendableChooser chooser;

//ROBOT INIZILIZATION
    public void robotInit() {
    	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        CameraServer.getInstance().setImage(frame);
    	SendableChooser chooser = new SendableChooser();
    	frontDrive.setInverted(true);
    	rearDrive.setInverted(true);
    	chassis = new RobotDrive(frontDrive, rearDrive);
    	chassis.setExpiration(0.1);
    	driveStick = new Joystick(0);
    	shootStick = new Joystick(1);
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
    }

//AUTONOMOUS
    public void autonomous() {
    		String defense = chooser.getSelected().toString();
    		if(defense.equals("lowbar")){
    			 chassis.setSafetyEnabled(false);
    	         chassis.drive(0.5, 0.0);	// drive forwards half speed
    	         Timer.delay(2.0);		//    for 2 seconds
    	         chassis.drive(0.0, 0.0);	// stop robot	
    		}else if(defense.equals("portcullis")){
    			
    		}else if(defense.equals("cheval")){
    			
    		}else if(defense.equals("moat")){
    			
    		}else if(defense.equals("ramparts")){
    			
    		}else if(defense.equals("drawbridge")){
    			
    		}else if(defense.equals("sallyport")){
    			
    		}else if(defense.equals("rockwall")){
    			
    		}else if(defense.equals("roughterrain")){
    			
    		}else{
    			SmartDashboard.putString("Error", "No defenses match");
    		}
    		}
    		
//TELEOPERATED
    public void operatorControl() {
    	 chassis.setSafetyEnabled(true);
        //while under in Teleoperation mode.
        while (isOperatorControl() && isEnabled()) {
            Timer.delay(0.005);
            chassis.arcadeDrive(driveStick);
            double shootY = shootStick.getAxis(Joystick.AxisType.kY);
            rollerTilt.set(shootY);
        }
    }
}
//END BRACKET, ROBOT CODE ENDS