/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.BoostRobotDriveCmd;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.PreciseRobotDriveCmd;
import frc.robot.commands.ShootCmd;
import frc.robot.commands.TankDriveCmd;
import frc.robot.commands.TriggerLaunchCmd;
import frc.robot.commands.TunnelCmd;
import frc.robot.subsystems.DriveSstm;
import frc.robot.subsystems.LauncherSstm;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here.
  private final DriveSstm m_DriveSstm = new DriveSstm();
  private final LauncherSstm m_LauncherSstm = new LauncherSstm();

  // OI devices
  private final XboxController m_controller = new XboxController(Constants.CONTROLLER_PORT);

  // Autochooser
  private final SendableChooser<String> m_autoChooser = new SendableChooser<String>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   * Constructor runs at robot init - Place robot init code here.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Sets the default command for the drivetrain subsystem
    setDefaultCommands();

    // Sets autochooser on the SmartDashboard
    setAutochooser();

  }

  private void setAutochooser() {
    m_autoChooser.setDefaultOption("moveForward", "moveForward");
    m_autoChooser.setDefaultOption("moveBackAndShoot", "moveBackAndShoot");
  }

  private void setDefaultCommands() {

    // Launcher Triggers Default Command
    m_LauncherSstm.setDefaultCommand(new TriggerLaunchCmd(m_LauncherSstm, () -> m_controller.getTriggerAxis(Hand.kLeft),
        () -> m_controller.getTriggerAxis(Hand.kRight)));

    // #1 - Tank Drive
    m_DriveSstm.setDefaultCommand(
        new TankDriveCmd(m_DriveSstm, () -> m_controller.getY(Hand.kLeft), () -> m_controller.getY(Hand.kRight)));

    /*
     * m_LauncherSstm.setDefaultCommand(new LauncherCmd( m_LauncherSstm, () ->
     * m_controller.getTriggerAxis(Hand.kLeft), () ->
     * m_controller.getTriggerAxis(Hand.kLeft); );
     */
    /*
     * //#2 - Arcade Drive m_DriveSstm.setDefaultCommand(new ArcadeDriveCmd(
     * m_DriveSstm, () -> m_controller.getY(Hand.kLeft), () ->
     * m_controller.getX(Hand.kLeft)));
     */

    /*
     * // #3 - Curvature Drive m_DriveSstm.setDefaultCommand(new CurvatureDriveCmd(
     * m_DriveSstm, () -> m_controller.getY(Hand.kLeft), () ->
     * m_controller.getX(Hand.kLeft), () -> m_controller.getBButton()));
     */ }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // Left Bumper -> Boost Drive
    new JoystickButton(m_controller, Button.kBumperRight.value).whenHeld(new BoostRobotDriveCmd(m_DriveSstm));
    // Right Bumper -> Precision Drive
    new JoystickButton(m_controller, Button.kBumperLeft.value).whenHeld(new PreciseRobotDriveCmd(m_DriveSstm));
    // A Button -> Aim on Vision Targets (LimeLight)
    new JoystickButton(m_controller, Button.kA.value).whenHeld(new TunnelCmd(m_LauncherSstm));
    // Y Button -> Toggle Tunnel TODO: Bind Intake to Left Trigger
    new JoystickButton(m_controller, Button.kY.value).whenHeld(new IntakeCmd(m_LauncherSstm));
    // X Button -> Run Shooter TODO: Bind Shoot to Right Trigger
    new JoystickButton(m_controller, Button.kX.value).whenHeld(new ShootCmd(m_LauncherSstm));
    // Use X and Y buttons to test if both shooter motors run the same direction.
    // Subject to change.
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous.
   */
  public Command getAutonomousCommand() {

    Command autoCommand;

    switch (m_autoChooser.getSelected()) {
      case "moveForward":
        autoCommand = new DriveStraight(m_DriveSstm, 1);
      default:
        autoCommand = new DriveStraight(m_DriveSstm, 1);
        break;
    }

    return autoCommand;
  }

}
