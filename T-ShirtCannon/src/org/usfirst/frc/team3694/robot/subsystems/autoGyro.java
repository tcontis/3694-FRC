package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.commands.autLevelGyro;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class autoGyro extends Subsystem {

    Gyro gyro;

    public void initDefaultCommand() {
        setDefaultCommand(new autLevelGyro());
    }
}

