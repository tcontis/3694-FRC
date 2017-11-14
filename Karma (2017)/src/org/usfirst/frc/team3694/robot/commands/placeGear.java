package org.usfirst.frc.team3694.robot.commands;

import org.usfirst.frc.team3694.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3694.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3694.robot.subsystems.autoGyro;

/**
 *
 */
public class placeGear extends Command {
	//Timer for how long to drive (no encoders)
		Timer lineTimer = new Timer();
		//for gyroscope correction speed
		public double kP = 0.03;
	    
		public placeGear() {
	        requires(Robot.drivetrain);
	        requires(Robot.autogyro);
	        requires(Robot.drivetrain);
	    }

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    	//start the Timer
	    	lineTimer.reset();
	    	lineTimer.start();
	    	
	    	
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	double getAngle = autoGyro.gyro.getAngle(); //Get current angle
	    	if (lineTimer.get() <= 4) {
	    	DriveTrain.Chassis.tankDrive(1.0, -getAngle * kP);
	    		DriveTrain.backLeft.set(DriveTrain.frontLeft.getSpeed());
	    		DriveTrain.backRight.set(DriveTrain.frontRight.getSpeed());
	    	Timer.delay(0.01);
	    	}
	    	if (lineTimer.get() > 4) {
	    		DriveTrain.Chassis.drive(0, 0);
	    		DriveTrain.backLeft.set(DriveTrain.frontLeft.getSpeed());
	    		DriveTrain.backRight.set(DriveTrain.frontRight.getSpeed());
	    	}
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
