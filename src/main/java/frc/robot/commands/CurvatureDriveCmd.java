/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSstm;

public class CurvatureDriveCmd extends CommandBase {

  private final DriveSstm m_driveSstm;
  private final DoubleSupplier m_x, m_z;
  private final BooleanSupplier m_quickTurn;

  public CurvatureDriveCmd(DriveSstm sstm, DoubleSupplier x, DoubleSupplier z, BooleanSupplier quickTurn) {
    m_driveSstm = sstm;
    m_x = x;
    m_z = z;
    m_quickTurn = quickTurn;
    addRequirements(sstm);
  }

  @Override
  public void execute() {
    m_driveSstm.curvatureDrive(m_x.getAsDouble(), m_z.getAsDouble(), !m_quickTurn.getAsBoolean());
  }

}
