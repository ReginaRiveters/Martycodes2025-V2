// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


//import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType; 


import edu.wpi.first.wpilibj.motorcontrol.NidecBrushless;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
  /** Creates a new Motor. */
  NidecBrushless motor11;
  
    public Motor() {
     
    }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
