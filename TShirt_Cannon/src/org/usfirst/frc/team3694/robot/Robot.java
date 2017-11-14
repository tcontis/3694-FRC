
package org.usfirst.frc.team3694.robot;

import org.usfirst.frc.team3694.robot.commands.FireCannonA;
import org.usfirst.frc.team3694.robot.commands.FireCannonB;
import org.usfirst.frc.team3694.robot.commands.FireCannonC;
import org.usfirst.frc.team3694.robot.commands.FireCannonD;
import org.usfirst.frc.team3694.robot.subsystems.Cannon;
import org.usfirst.frc.team3694.robot.subsystems.Chassis;
import org.usfirst.frc.team3694.robot.subsystems.Turret;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends IterativeRobot {

	public static final Chassis chassis = new Chassis();
	public static final Cannon cannon = new Cannon();
	public static final Turret turret = new Turret();
	public static final RobotDrive driveChassis = new RobotDrive(chassis.leftDrive, chassis.rightDrive);
	public static final FireCannonA fireCannonA = new FireCannonA();
	public static final FireCannonB fireCannonB = new FireCannonB();
	public static final FireCannonC fireCannonC = new FireCannonC();
	public static final FireCannonD fireCannonD = new FireCannonD();
	public static OI oi;

    SendableChooser chooser;

    public void robotInit() {
		oi = new OI();
    }
	
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}


    public void teleopInit() {
		
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        driveChassis.arcadeDrive(oi.driveStick);
        Turret.turnMotor.set(oi.shootStick.getAxis(Joystick.AxisType.kX));
        Turret.liftMotor.set(oi.shootStick.getAxis(Joystick.AxisType.kY));
    }
    
}
