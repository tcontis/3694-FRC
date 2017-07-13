package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.commands.shootAll;

import edu.wpi.first.wpilibj.Relay;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class airShoot extends Subsystem {

    
	public static Relay t1 = new Relay(5);
	public static Relay t2 = new Relay(3);
    public static Relay t3 = new Relay(4);
    public static Relay t4 = new Relay(5);

    public void initDefaultCommand() {
        setDefaultCommand(new shootAll());
    }
}

