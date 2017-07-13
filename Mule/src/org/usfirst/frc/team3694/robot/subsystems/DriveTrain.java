package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.commands.tankDrive;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

   public static Victor left = new Victor(0);
   public static Victor right = new Victor(1);

    public void initDefaultCommand() {
        setDefaultCommand(new tankDrive());
    }
}

