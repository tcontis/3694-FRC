package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.commands.fillCompressor;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class airCompressor extends Subsystem {

   public static Compressor main = new Compressor(0);
   public double compressorPressure = main.getCompressorCurrent();

    public void initDefaultCommand() {
        setDefaultCommand(new fillCompressor());
    }
}

