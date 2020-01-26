/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LauncherSstm;

public class LaunchCellCmd extends CommandBase {
  
  private final LauncherSstm m_LauncherSstm;

  public LaunchCellCmd(LauncherSstm launcherSstm) {
    m_LauncherSstm = launcherSstm;
    addRequirements(m_LauncherSstm);
  }

  @Override
  public void execute() {
    m_LauncherSstm.startLauncher();
  }

  @Override
  public void end(boolean interrupted) {
    m_LauncherSstm.stopLauncher();
  }




}
