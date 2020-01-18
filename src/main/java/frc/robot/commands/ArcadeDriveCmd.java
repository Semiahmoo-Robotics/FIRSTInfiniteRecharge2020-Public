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

public class ArcadeDriveCmd extends CommandBase {
  
  private final DriveSstm m_driveSstm;
  private final XboxController m_joystick;
  
  public ArcadeDriveCmd(DriveSstm sstm, XboxController js) {
    m_driveSstm = sstm;
    m_joystick = js;
    addRequirements(m_driveSstm);
  }

  @Override
  public void execute() {
    //multiplyer
    double m = 0.75;
    if (m_joystick.getBButton()) {
      m = 1.0;
    } else if (m_joystick.getYButton()) {
      m = 0.5;
    }
    m_driveSstm.ArcadeDrive(m_joystick.getY(Hand.kLeft) * m,
    m_joystick.getX(Hand.kLeft) * m);
  }

}
