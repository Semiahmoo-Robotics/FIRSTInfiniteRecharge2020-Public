/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSstm;

public class DriveStraight extends CommandBase {
  
  private DriveSstm m_driveSstm;
  private double m_lEncoderInit;
  private double m_rEncoderInit;
  private double m_distance;

  /**
   * Creates a new driveDistance.
   */
  public DriveStraight(DriveSstm drive, double distance) {
    this.m_driveSstm = drive;
    this.m_distance = distance;
    addRequirements(m_driveSstm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_lEncoderInit = m_driveSstm.getLEncoderDistance();
    m_rEncoderInit = m_driveSstm.getREncoderDistance();

    m_driveSstm.tankDrive(Constants.AUTO_STRAIGHT_DRIVE_SPEED, Constants.AUTO_STRAIGHT_DRIVE_SPEED);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSstm.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (((m_driveSstm.getLEncoderDistance() - m_lEncoderInit) + 
      (m_driveSstm.getREncoderDistance() - m_rEncoderInit))/2 >= m_distance) {
        return true;
    }
    return false;
  }
}
