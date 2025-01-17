/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSstm;

public class TankDriveCmd extends CommandBase {
  
  private final DriveSstm m_driveSstm;
  private final DoubleSupplier m_l;
  private final DoubleSupplier m_r;

  public TankDriveCmd(DriveSstm sstm, DoubleSupplier l, DoubleSupplier r) {
    m_driveSstm = sstm;
    m_l = l;
    m_r = r;
    addRequirements(m_driveSstm);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveSstm.tankDrive(m_l.getAsDouble(), m_r.getAsDouble());
  }


}
