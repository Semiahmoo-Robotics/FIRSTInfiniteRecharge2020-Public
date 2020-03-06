/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSstm;

public class TankArcadeCmd extends CommandBase {
  
  private final DriveSstm m_driveSstm;
  private Joystick m_controlJoystick;
  private XboxController m_driveController;

  public TankArcadeCmd(DriveSstm sstm, XboxController xbox, Joystick joystick) {
    this.m_driveSstm = sstm;
    this.m_driveController = xbox;
    this.m_controlJoystick = joystick;

    addRequirements(m_driveSstm);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if ((Math.abs(m_controlJoystick.getY()) > Constants.LOGITECH_DEADBAND)
      || (Math.abs(m_controlJoystick.getX()) > Constants.LOGITECH_DEADBAND)) {
        m_driveSstm.arcadeDrive(
          Math.copySign(Constants.DAMPED_ARCADE_SPEED, m_controlJoystick.getY()),
          Math.copySign(Constants.DAMPED_ARCADE_SPEED, m_controlJoystick.getX())
        );
    } else {
      m_driveSstm.tankDrive(m_driveController.getY(Hand.kLeft), m_driveController.getY(Hand.kRight));
    }
  }


}
