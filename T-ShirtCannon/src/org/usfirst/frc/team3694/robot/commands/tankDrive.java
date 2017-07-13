package org.usfirst.frc.team3694.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3694.robot.Robot;
import org.usfirst.frc.team3694.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3694.robot.OI;

/**
 *
 */
public class tankDrive extends Command {

    public tankDrive() {
    requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.left.set(org.usfirst.frc.team3694.robot.OI.leftStick.getY());
    	DriveTrain.right.set(org.usfirst.frc.team3694.robot.OI.rightStick.getY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
 
}
