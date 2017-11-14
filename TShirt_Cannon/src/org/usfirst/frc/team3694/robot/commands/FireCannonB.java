package org.usfirst.frc.team3694.robot.commands;

import org.usfirst.frc.team3694.robot.Robot;
import org.usfirst.frc.team3694.robot.subsystems.Turret;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class FireCannonB extends Command {

    public FireCannonB() {
        requires(Robot.turret);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Turret.compressorA.start();
    	Timer.delay(2);
    	Turret.solenoidB.set(true);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Timer.delay(1);
    	Turret.compressorA.stop();
    	Turret.solenoidB.set(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}