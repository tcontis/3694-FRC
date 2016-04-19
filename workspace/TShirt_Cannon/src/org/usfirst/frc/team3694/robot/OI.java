package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public static Joystick driveStick = new Joystick(0); 
	public static Joystick shootStick = new Joystick(1);
	public static Button button1 = new JoystickButton(shootStick, 4);
	public static Button button2 = new JoystickButton(shootStick, 3);
	public static Button button3 = new JoystickButton(shootStick, 5);
	public static Button button4 = new JoystickButton(shootStick, 2);
	
	public OI(){
		button1.whenPressed(Robot.fireCannonA);
		button2.whenPressed(Robot.fireCannonB);
		button3.whenPressed(Robot.fireCannonC);
		button4.whenPressed(Robot.fireCannonD);
	}
	
}

