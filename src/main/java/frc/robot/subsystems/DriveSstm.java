/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSstm extends SubsystemBase {

  private final Spark m_lSpark = new Spark(Constants.L_DRIVE_PORT);
  private final Spark m_rSpark = new Spark(Constants.R_DRIVE_PORT);

  private final DifferentialDrive m_chassis = new DifferentialDrive(m_lSpark, m_rSpark);

  public DriveSstm() {
    // Stops motor if the robot loses connection to the driver station.
    m_chassis.setSafetyEnabled(true);
  }

  public void TankDrive(double l, double r) {
    m_chassis.tankDrive(l, r, false);
  }

}
