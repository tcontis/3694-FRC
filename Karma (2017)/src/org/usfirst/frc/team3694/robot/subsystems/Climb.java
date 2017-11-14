package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.commands.climber;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climb extends Subsystem {

   public static Victor climbMotor = new Victor(4);

    public void initDefaultCommand() {
        setDefaultCommand(new climber());
    }
}

