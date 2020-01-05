/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSstm;

public class TankDriveCmd extends CommandBase {
  
  private final DriveSstm m_driveSstm;
  private final XboxController m_joystick;

  public TankDriveCmd(DriveSstm sstm, XboxController js) {
    m_driveSstm = sstm;
    m_joystick = js;
    addRequirements(m_driveSstm);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double precision = 1 - m_joystick.getTriggerAxis(Hand.kRight) / 2;
    m_driveSstm.TankDrive(m_joystick.getY(Hand.kLeft) * precision,
    m_joystick.getY(Hand.kRight) * precision);
  }


}
