package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.commands.tankDrive;


import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3694.robot.Robot;
import org.usfirst.frc.team3694.robot.OI;

/**
 *
 */
public class DriveTrain extends Subsystem {
	public static SpeedController left = new Victor(0);
	public static SpeedController right = new Victor(1);
	
	private RobotDrive drive = new RobotDrive(left, right);

    public void initDefaultCommand() {
        setDefaultCommand(new tankDrive());
    }

    }


