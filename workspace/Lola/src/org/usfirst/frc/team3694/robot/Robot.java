/***************************************************\
 * Team 3694 NAHS Warbotz
 * FRC 2015 Robot Code
 * "Lola"
 * 
 * Version 0.8
 * 
 * Changes: 
 * -Fixed bugs with code
 * -Tweaks
 * -Reformatted code
 * -Fixed Name
\***************************************************/
//Defines stuff
package org.usfirst.frc.team3694.robot;

//Imports
import edu.wpi.first.wpilibj.*;

//ROBOT CODE FROM THIS POINT ON
public class Robot extends IterativeRobot{
    
    //PWM Objects and Variables
    public static RobotDrive chassis;
    public static final Victor leftDriveMotor = new Victor(0); //Left Drive Motor----------PWM (0)
    public static final Victor rightDriveMotor = new Victor(1); //Right Drive Motor--------PWM (1)
    public static final Victor skidMotor = new Victor(2); //Skid Motor---------------------PWM (2)
    public static final Victor liftMotor = new Victor(3); //Lift/Elevator Motor------------PWM (3)
    
    //USB Objects and Variables
    public static Joystick driveStick = new Joystick(0); //Joystick used for Driving-------USB (0)
    public static Joystick liftStick = new Joystick(1); //Joystick used for Lift-----------USB (1)

//ROBOT INIZILIZATION (RUNS ONCE)
    public void robotInit() {
    	//Configure motors present in Chassis, configure safety
    	chassis = new RobotDrive(leftDriveMotor, rightDriveMotor);
    	chassis.setExpiration(0.1);
    }

//AUTONOMOUS INITIATION (RUNS ONCE)
    public void autonomousInit() {
            chassis.setSafetyEnabled(false); //Disable Safety
            chassis.drive(0.5, 0.0); //drive forwards half speed
            Timer.delay(2.0); //for 2 seconds
            chassis.drive(0.0, 0.0); //stop robot
    }

//TELEOPERATED (CALLED EVERY FIELD CYCLE)
    public void teleopPeriodic() {
    	chassis.setSafetyEnabled(true); //Enable Safety
        Timer.delay(0.005); //Delay required
        chassis.arcadeDrive(driveStick); //Drive chassis using Arcade Drive (One Joystick)
        liftMotor.set(liftStick.getAxis(Joystick.AxisType.kY)); //Set the roller's tilt to be equal to the Shooting Joystick's Y
    }
}
