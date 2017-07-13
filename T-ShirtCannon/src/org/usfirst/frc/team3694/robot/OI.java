package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team3694.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);
	public static Joystick cannonStick = new Joystick(2);
	
	public static JoystickButton shoot = new JoystickButton(cannonStick, 1);
	public JoystickButton autoShoot = new JoystickButton(cannonStick, 2);
	
	public Joystick getJoystick() {
		return leftStick;
		
		
	}
}
