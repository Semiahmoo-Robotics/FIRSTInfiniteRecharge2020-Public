/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.LauncherSstm;

public class IntakeCmd extends CommandBase {

  private LauncherSstm m_launcherSstm;

  //Shuffleboard Config
  private double m_intakeSpeed = Constants.INTAKE_SPEED;

  public IntakeCmd(LauncherSstm sstm) {
    m_launcherSstm = sstm;
    addRequirements(sstm);
  }

  // Called when the command is initially scheduled
  @Override
  public void initialize() {
    m_launcherSstm.setIntake(m_intakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_launcherSstm.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
