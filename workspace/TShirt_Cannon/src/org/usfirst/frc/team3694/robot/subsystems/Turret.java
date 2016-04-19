package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Turret extends Subsystem {
   
	public static Victor turnMotor;
	public static Victor liftMotor;
	public static Compressor compressorA;
	public static Compressor compressorB;
	public static Solenoid solenoidA;
	public static Solenoid solenoidB;
	public static Solenoid solenoidC;
	public static Solenoid solenoidD;
	public Turret(){
		turnMotor = new Victor(RobotMap.turretPivot);
		liftMotor = new Victor(RobotMap.leadScrew);
		compressorA = new Compressor(RobotMap.compressorA);
		compressorB = new Compressor(RobotMap.compressorB);
		solenoidA = new Solenoid(RobotMap.solenoidA);
		solenoidB = new Solenoid(RobotMap.solenoidB);
		solenoidC = new Solenoid(RobotMap.solenoidC);
		solenoidD = new Solenoid(RobotMap.solenoidD);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

