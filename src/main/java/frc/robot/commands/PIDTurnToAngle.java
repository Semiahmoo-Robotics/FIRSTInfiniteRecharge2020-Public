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

public class PIDTurnToAngle extends PIDCommand {

  public PIDTurnToAngle(double targetAngleDegrees, DriveSstm drive) {
    super(
        // The controller that the command will use
        new PIDController(Constants.KP_ANGLE, Constants.KI_ANGLE, Constants.KD_ANGLE),
        // This should return the measurement
        drive::getGyroDeg,
        // This should return the setpoint target.
        targetAngleDegrees,
        // This uses the output
        output -> drive.arcadeDrive(0, output),
        // Subsystem requirement
        drive);

    // Configure additional PID options by calling `getController` here.
    getController().enableContinuousInput(-180, 180);
    // Set the controller tolerance - the delta tolerance ensures the robot is
    // stationary at the setpoint before it is considered as having reached the
    // reference
    getController().setTolerance(Constants.TURN_TOLERANCE_DEG, Constants.TURN_RATE_TOL_DEGPERSEC);

  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atSetpoint();

  }
}
