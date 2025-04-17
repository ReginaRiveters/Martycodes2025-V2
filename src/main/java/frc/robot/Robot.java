// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
/*import com.revrobotics.spark.SparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.PWMSparkMax;
import com.revrobotics.PWMSparkLowerLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;*/




/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  //Channels for spark maxes on roboRio (PWM)
  private static final int kFrontLeftChannel = 5;
  private static final int kRearLeftChannel = 2;
  private static final int kFrontRightChannel = 4;
  private static final int kRearRightChannel = 0;
  private static final int kFirstArmChannel = 6;
  private static final int kSecondArmChannel = 7;
  private static final int kFirstIntakeChannel = 8;
  private static final int kSecondIntakeChannel = 9;

    // Channels not used: 1 & 3


  private final MecanumDrive m_robotDrive;

  private final Joystick l_stick = new Joystick(1);
  private final Joystick r_stick = new Joystick(0);

  PWMSparkMax arm1 = new PWMSparkMax(kFirstArmChannel);
  PWMSparkMax arm2 = new PWMSparkMax(kSecondArmChannel);

  PWMSparkMax intake1 = new PWMSparkMax(kFirstIntakeChannel);
  PWMSparkMax intake2 = new PWMSparkMax(kSecondIntakeChannel);


  
  PWMSparkMax frontLeft = new PWMSparkMax(kFrontLeftChannel);
  PWMSparkMax rearLeft = new PWMSparkMax(kRearLeftChannel);
  PWMSparkMax frontRight = new PWMSparkMax(kFrontRightChannel);
  PWMSparkMax rearRight = new PWMSparkMax(kRearRightChannel);

  /*private SparkPIDController controller1 = arm1.getPIDController();
  private SparkPIDController controller2 = arm2.getPIDController();

  controller1.setP(gain:0.1);
  controller1.setI(gain:0);
  controller1.setD(gain:0);
  controller2.setP(gain:0.1);
  controller2.setI(gain:0);
  controller2.setD(gain:0);


  private double setPoint = 0;
  
  private RelativeEncoder encoder1 = arm1.getEncoder();*/
  private Timer timer = new Timer();


  /** Called once at the beginning of the robot program. */
  public Robot() {
    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontRight.setInverted(true);
    rearRight.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft::set, rearLeft::set, frontRight::set, rearRight::set);


    SendableRegistry.addChild(m_robotDrive, frontLeft);
    SendableRegistry.addChild(m_robotDrive, rearLeft);
    SendableRegistry.addChild(m_robotDrive, frontRight);
    SendableRegistry.addChild(m_robotDrive, rearRight);
    
  }

  @Override
  public void autonomousInit() {
      timer.reset();
      timer.start();
  }

  @Override
  public void autonomousPeriodic() {
      if (timer.get() < 2.0) {
          frontLeft.set(0.3);
          rearLeft.set(0.3);
          frontRight.set(0.3);
          rearRight.set(0.3);
      } else {
          m_robotDrive.stopMotor(); // Stop the robot 
      }
  }

  @Override
  public void teleopPeriodic() {
    
      // Worse arm movement
    if (l_stick.getRawButton(6) == true){
      arm1.set(0.35);
      arm2.set(-0.35);
    }
    else if (l_stick.getRawButton(4) == true){
      arm1.set(-0.35);
      arm2.set(0.35);
    }
    else {
      arm1.set(0);
      arm2.set(0);
    }
    

      //Intake/outtake
    if (r_stick.getRawButton(1) == true){
      intake1.set(-1);
      intake2.set(1);
    }
    else if (l_stick.getRawButton(1) == true){
      intake1.set(1);
      intake2.set(-1);
    }
    else {
      intake1.set(0);
      intake2.set(0);
    }
    
    
    // Replace 2 with your motor controller ID  
 
    if (l_stick.getRawAxis(1) < -0.1){
      arm2.set((l_stick.getRawAxis(1)/2.5 ) - 0.22);
      arm1.set((-l_stick.getRawAxis(1)/2.5 ) + 0.22);
    }
    else if (l_stick.getRawAxis(1) > 0.1){
      arm2.set((l_stick.getRawAxis(1)/2.5 ) + 0.22);
      arm1.set((-l_stick.getRawAxis(1)/2.5 ) - 0.22);
    }
    // else if (m_stick.getRawAxis(3) != 0){
     // arm2.set(m_stick.getRawAxis(3)/2.5);
    //  arm1.set(-(m_stick.getRawAxis(3)/2.5));
   // }
    //else{
    //  arm1.set(0);
    //  arm2.set(0);
    //} 
  

    

    // Use the joystick Y axis for forward movement, X axis for lateral
    // movement, and Z axis for rotation.
    
    //m_robotDrive.driveCartesian(r_stick.getRawAxis(1), -r_stick.getRawAxis(0), -r_stick.getRawAxis(2));
    m_robotDrive.driveCartesian(-r_stick.getY(), -r_stick.getX(), -r_stick.getZ());
  
  
  
  }
}
