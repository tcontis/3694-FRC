package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick leftStick = new Joystick(0);
  	public static Joystick rightStick = new Joystick(1);
  	public static Joystick driveStick = new Joystick(2);
 	public static JoystickButton forwardSpin = new JoystickButton(rightStick, 2);
 	public static JoystickButton reverseSpin = new JoystickButton(rightStick, 3);
 	public static JoystickButton manipulatorClimb = new JoystickButton(rightStick, 1);
 	public static JoystickButton manipulatorScale = new JoystickButton(rightStick, 4);
}
