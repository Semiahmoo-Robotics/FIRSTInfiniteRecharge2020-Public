/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSstm;

public class AdjustableShootCmd extends CommandBase {

  private ShooterSstm m_launcherSstm;
  private DoubleSupplier m_power;

  public AdjustableShootCmd(ShooterSstm sstm, DoubleSupplier power) {
    this.m_launcherSstm = sstm;
    this.m_power = power;

    addRequirements(sstm);
  }

  @Override
  public void execute() {
    m_launcherSstm.setShooter(m_power.getAsDouble());
  }

}