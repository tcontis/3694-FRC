package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

public class Motors {
	//Motors
	public static Victor left = new Victor(0);
	public static Victor right = new Victor(1);
	
	//RobotDrive
	public static RobotDrive robodrive = new RobotDrive(left, right);
}
