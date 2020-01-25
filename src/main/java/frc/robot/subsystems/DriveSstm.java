/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSstm extends SubsystemBase {

  private final Spark m_lSpark = new Spark(Constants.L_DRIVE_PORT);
  private final Spark m_rSpark = new Spark(Constants.R_DRIVE_PORT);
  private final DifferentialDrive m_chassis = new DifferentialDrive(m_lSpark, m_rSpark);

  private final Encoder m_lEncoder
    = new Encoder(Constants.L_ENCODER_A, Constants.L_ENCODER_B, Constants.L_ENCODER_REVERSED);
  private final Encoder m_rEncoder
    = new Encoder(Constants.R_ENCODER_A, Constants.R_ENCODER_B, Constants.R_ENCODER_REVERSED);

  private final Gyro m_gyro = new ADXRS450_Gyro();

  public DriveSstm() {
    m_lSpark.setInverted(Constants.L_SPARK_REVERSED);
    m_rSpark.setInverted(Constants.R_SPARK_REVERSED);
    m_chassis.setMaxOutput(0.75);
    m_chassis.setDeadband(0.035);
    // Stops motor if the robot loses connection to the driver station.
    m_chassis.setSafetyEnabled(true);
  }

  public void tankDrive(double l, double r) {
    m_chassis.tankDrive(l, r, false);
  }

  public void arcadeDrive(double x, double z) {
    m_chassis.arcadeDrive(x, z, false);
  }

  public void curvatureDrive(double x, double z, boolean quickTurn) {
    m_chassis.curvatureDrive(x, z, quickTurn);
  }

  public void setMaxOuput(double n) {
    m_chassis.setMaxOutput(n);
  }

  public Gyro getGyro() {
    return m_gyro;
  }

  public void zeroHeading() {
    m_gyro.reset();
  }

  public void calibrateGyro() {
    m_gyro.calibrate();
  }

  public double getGyroDeg() {
    return m_gyro.getAngle();
  }

  public double getTurnRate() {
    return m_gyro.getRate();
  }

  public Encoder getLEncoder() {
    return m_lEncoder;
  }

  public Encoder getREncoder() {
    return m_rEncoder;
  }
  

}
