package org.usfirst.frc.team3694.robot.commands;

import org.usfirst.frc.team3694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3694.robot.OI;
import org.usfirst.frc.team3694.robot.subsystems.DriveTrain;

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
    	/**DriveTrain.frontLeft.set(OI.leftStick.getY());
    	DriveTrain.frontRight.set(OI.rightStick.getY());
    	DriveTrain.backLeft.set(OI.leftStick.getY());
    	DriveTrain.backRight.set(OI.rightStick.getY()); **/
    	DriveTrain.Chassis.tankDrive(OI.leftStick, OI.rightStick);
    	DriveTrain.backLeft.set(DriveTrain.frontLeft.getSpeed());
    	DriveTrain.backRight.set(DriveTrain.frontRight.getSpeed());
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
