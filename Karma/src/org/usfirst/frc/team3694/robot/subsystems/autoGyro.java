package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.commands.lineDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class autoGyro extends Subsystem {

    public static Gyro gyro = new AnalogGyro(0);

    public void initDefaultCommand() {
        setDefaultCommand(new lineDrive());
    }
}

