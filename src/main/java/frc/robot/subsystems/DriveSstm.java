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
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSstm extends SubsystemBase {

  private final Spark m_lSpark = new Spark(Constants.L_DRIVE_PORT);
  private final Spark m_rSpark = new Spark(Constants.R_DRIVE_PORT);
  private final DifferentialDrive m_chassis = new DifferentialDrive(m_lSpark, m_rSpark);

  private final Encoder m_lEncoder = new Encoder(Constants.L_ENCODER_A, Constants.L_ENCODER_B,
      Constants.L_ENCODER_REVERSED);
  private final Encoder m_rEncoder = new Encoder(Constants.R_ENCODER_A, Constants.R_ENCODER_B,
      Constants.R_ENCODER_REVERSED);

  private final Gyro m_gyro = new ADXRS450_Gyro();

  private final DifferentialDriveOdometry m_odometry;

  public DriveSstm() {

    m_lSpark.setInverted(Constants.L_SPARK_REVERSED);
    m_rSpark.setInverted(Constants.R_SPARK_REVERSED);
    m_chassis.setMaxOutput(Constants.NORMAL_SPEED);
    m_chassis.setDeadband(0.035);

    // Stops motor if the robot loses connection to the driver station.
    m_chassis.setSafetyEnabled(true);

    m_lEncoder.setDistancePerPulse(Constants.ENCODER_DISTANCE_PER_PULSE);
    m_rEncoder.setDistancePerPulse(Constants.ENCODER_DISTANCE_PER_PULSE);
    zeroREncoder();
    zeroLEncoder();
    calibrateGyro();

    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getGyroDeg()));
  }

  @Override
  public void periodic() {
    // Update the odometry in the periodic block
    m_odometry.update(Rotation2d.fromDegrees(getGyroDeg()),
        getLEncoderDistance(), getREncoderDistance());    
  }
  
  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(m_lEncoder.getRate(), m_rEncoder.getRate());
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    zeroREncoder();
    zeroLEncoder();
    m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getGyroDeg()));
  }
  

  /**
   * Drives the robot using tank controls.
   *
   * @param l the commanded left side movement
   * @param r the commanded right side movement
   */
  public void tankDrive(double l, double r) {
    m_chassis.tankDrive(l, r, false);
  }

  /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts  the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_lSpark.setVoltage(leftVolts);
    m_rSpark.setVoltage(-rightVolts);
    m_chassis.feed();
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param x the commanded forward movement
   * @param z the commanded rotation
   */
  public void arcadeDrive(double x, double z) {
    m_chassis.arcadeDrive(x, z, false);
  }

  /**
   * Drives the robot using curvature controls.
   *
   * @param x the commanded forward movement
   * @param z the commanded magnitude of curvature
   * @param quickTurn If true, overides curvature drive for turning in place.
   */
  public void curvatureDrive(double x, double z, boolean quickTurn) {
    m_chassis.curvatureDrive(x, z, quickTurn);
  }

  /**
   * Sets the maximum speed of the robot's drivetrain.
   *
   * @param n value between 0 and 1, where 1 is the maximum speed.
   */
  public void setMaxOuput(double n) {
    m_chassis.setMaxOutput(n);
  }


  public void zeroGyroHeading() {
    m_gyro.reset();
  }

  public void calibrateGyro() {
    m_gyro.calibrate();
  }

  public double getGyroDeg() {
    return m_gyro.getAngle();
  }

  public double getGyroTurnRate() {
    return m_gyro.getRate();
  }


  public void zeroLEncoder() {
    m_lEncoder.reset();
  }

  public double getLEncoderDistance() {
    return m_lEncoder.getDistance();
  }


  public void zeroREncoder() {
    m_rEncoder.reset();
  }  
  
  public double getREncoderDistance() {
    return m_rEncoder.getDistance();
  }


  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (getLEncoderDistance() + getREncoderDistance()) / 2.0;
  }


}
