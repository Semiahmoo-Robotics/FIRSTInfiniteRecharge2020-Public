/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.commands.CurvatureDriveCmd;
import frc.robot.commands.TankDriveCmd;
import frc.robot.subsystems.DriveSstm;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here.
  private final DriveSstm m_DriveSstm = new DriveSstm();

  //OI devices
  private final XboxController m_controller = new XboxController(Constants.CONTROLLER_PORT);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();


    // Set default commands
    /* m_DriveSstm.setDefaultCommand(new TankDriveCmd(
      m_DriveSstm, m_controller)); */
    m_DriveSstm.setDefaultCommand(new ArcadeDriveCmd(
      m_DriveSstm, m_controller));
    /* m_DriveSstm.setDefaultCommand(new CurvatureDriveCmd(
      m_DriveSstm, m_controller)); */

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

}
