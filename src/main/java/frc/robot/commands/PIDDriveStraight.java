/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSstm;

public class PIDDriveStraight extends PIDCommand {
  /**
   * Creates a new PIDDriveStraight.
   */
  public PIDDriveStraight(double targetDistanceMeters, DriveSstm drive) {
    super(
        // The controller that the command will use
        new PIDController(Constants.KP_DRIVE, Constants.KI_DRIVE, Constants.KD_DRIVE),
        // This should return the measurement
        drive::getAverageEncoderDistance,
        // This should return the setpoint (can also be a constant)
        targetDistanceMeters,
        // This uses the output
        output -> {
          drive.tankDrive(output, output);
        },
        drive);
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(Constants.TURN_TOLERANCE_DEG, Constants.TURN_RATE_TOL_DEGPERSEC);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
