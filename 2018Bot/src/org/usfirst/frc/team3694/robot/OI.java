package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);
	public static Joystick manStick = new Joystick(2);
	public static JoystickButton forwardSpin = new JoystickButton(manStick, 2);
	public static JoystickButton reverseSpin = new JoystickButton(manStick, 3);
	public static JoystickButton manipulatorClimb = new JoystickButton(manStick, 1);
	public static JoystickButton manipulatorScale = new JoystickButton(manStick, 4);
	}
