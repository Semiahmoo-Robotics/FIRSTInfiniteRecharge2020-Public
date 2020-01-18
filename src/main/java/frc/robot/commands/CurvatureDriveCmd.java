/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSstm;

public class CurvatureDriveCmd extends CommandBase {

  private final DriveSstm m_driveSstm;
  private final XboxController m_joystick;

  public CurvatureDriveCmd(DriveSstm sstm, XboxController js) {
    m_driveSstm = sstm;
    m_joystick = js;
    addRequirements(sstm);
  }

  @Override
  public void execute() {

    boolean quickTurn = false;

    if (m_joystick.getBButton()) {
      quickTurn = true;
    }
    m_driveSstm.CurvatureDrive(
      m_joystick.getY(Hand.kLeft), m_joystick.getX(Hand.kLeft), quickTurn);

  }

}
