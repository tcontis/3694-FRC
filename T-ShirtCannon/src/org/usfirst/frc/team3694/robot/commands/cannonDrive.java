package org.usfirst.frc.team3694.robot.commands;

import org.usfirst.frc.team3694.robot.Robot;
import org.usfirst.frc.team3694.robot.subsystems.CannonPosition;
import org.usfirst.frc.team3694.robot.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cannonDrive extends Command {

    public cannonDrive() {
        requires(Robot.cannonposition);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	CannonPosition.XAxis.set(org.usfirst.frc.team3694.robot.OI.cannonStick.getX());
    	CannonPosition.YAxis.set(org.usfirst.frc.team3694.robot.OI.cannonStick.getY());
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
