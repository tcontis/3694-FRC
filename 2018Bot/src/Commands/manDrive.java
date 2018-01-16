package Commands;

import Subsystems.Manipulator;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team3694.robot.OI;

public class manDrive {
	public static void Manipulator () {
		Manipulator.base.set(OI.rightStick.getY()); 
		if (OI.manipulatorClimb.get() == true) {
			Manipulator.base.set(0.5); //speed temporary
				Timer.delay(2); //time temporary -- needs testing
		}
		if (OI.manipulatorScale.get() == true) {
			Manipulator.base.set(0.5); //speed temporary
			Timer.delay(2); //time temporary -- needs testing
		}
	}
}
