package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.commands.tankDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    public static Victor frontLeft = new Victor(0);
    public static Victor frontRight = new Victor(1);
    public static Victor backLeft = new Victor(2);
    public static Victor backRight = new Victor(3);
    
    public static RobotDrive Chassis = new RobotDrive(frontLeft, frontRight);
    
   

    public void initDefaultCommand() {
        setDefaultCommand(new tankDrive());
    }
}

