package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.Robot;
import org.usfirst.frc.team3694.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    public static Victor frontLeft = new Victor(RobotMap.frontLeft);
    public static Victor rearLeft = new Victor(RobotMap.rearLeft);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

