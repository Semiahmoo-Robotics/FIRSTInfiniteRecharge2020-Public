/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.AdjustableShootCmd;
import frc.robot.commands.AimChassisCmd;
import frc.robot.commands.BoostRobotDriveCmd;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.ExtendClawCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.PIDTurnToAngle;
import frc.robot.commands.ShootCmd;
import frc.robot.commands.TankArcadeCmd;
import frc.robot.commands.TankDriveCmd;
import frc.robot.commands.TunnelCmd;
import frc.robot.subsystems.ClimbSstm;
import frc.robot.subsystems.DriveSstm;
import frc.robot.subsystems.IntakeSstm;
import frc.robot.subsystems.ShooterSstm;
import frc.robot.subsystems.TunnelSstm;

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
  private final ShooterSstm m_ShooterSstm = new ShooterSstm();
  private final TunnelSstm m_TunnelSstm = new TunnelSstm();
  private final IntakeSstm m_IntakeSstm = new IntakeSstm();
  private final ClimbSstm m_ClimbSstm = new ClimbSstm();

  // OI devices
  private final XboxController m_driveController = new XboxController(Constants.DRIVE_CONTROLLER_PORT);
  private final Joystick m_controlJoystick = new Joystick(Constants.CONTROL_JOYSTICK_PORT);

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

    // Tunnel Default Command
    m_TunnelSstm.setDefaultCommand(new TunnelCmd(m_TunnelSstm, () -> m_controlJoystick.getPOV()));

    // Drive Default Command
    m_DriveSstm.setDefaultCommand(new TankArcadeCmd(m_DriveSstm, m_driveController, m_controlJoystick));

    // Shooter Default Command
    m_ShooterSstm.setDefaultCommand(new AdjustableShootCmd(m_ShooterSstm, () -> m_controlJoystick.getThrottle()));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    //PLAYER 1 - JACK - DRIVE

    // 2 Joysticks -> Tank Drive (Default)
    // A Button -> Aim on Vision Targets (LimeLight)
    new JoystickButton(m_driveController, Button.kA.value).whenHeld(
      new AimChassisCmd(m_DriveSstm));
    // Y Button -> Rotate 180 Deg
    new JoystickButton(m_driveController, Button.kA.value).whenHeld(
      new PIDTurnToAngle(180, m_DriveSstm));
    // Left Bumper -> Rotate left
    new JoystickButton(m_driveController, Button.kBumperLeft.value).whenHeld(
      new TankDriveCmd(m_DriveSstm, () -> -Constants.TELEOP_ROTATE_SPEED, () -> Constants.TELEOP_ROTATE_SPEED));
    // Right Bumper -> Rotate right
    new JoystickButton(m_driveController, Button.kBumperRight.value).whenHeld(
      new TankDriveCmd(m_DriveSstm, () -> Constants.TELEOP_ROTATE_SPEED, () -> -Constants.TELEOP_ROTATE_SPEED));
    //Left Trigger -> Drive Straight. Sensitive to press depth.
    new Trigger(() -> (m_driveController.getTriggerAxis(Hand.kLeft) > Constants.TRIGGER_DEADBAND)).whileActiveOnce(
      new TankDriveCmd(m_DriveSstm, () -> m_driveController.getTriggerAxis(Hand.kLeft),  () -> m_driveController.getTriggerAxis(Hand.kLeft)));
    //Right Trigger -> Apply Boost to speed. Not sensitive to press depth.
    new Trigger(() -> (m_driveController.getTriggerAxis(Hand.kRight) > Constants.TRIGGER_DEADBAND)).whileActiveOnce(
      new BoostRobotDriveCmd(m_DriveSstm));

    
    //PLAYER 2 - GABE - CONTROL
    // Main Joystick -> Damped Arcade Drive for slight angle adjustments (Default)
    // Small POV -> Tunnel In/Out (Default)
    // Slider -> Shooter Output (Default)
    // Button 1 (Wii-mote B button) -> Intake
    new JoystickButton(m_controlJoystick, 1).whenHeld(new IntakeCmd(m_IntakeSstm));
    // Button 7 and 8 pressed together -> Trigger Claw Motor
    new JoystickButton(m_driveController, 7).and(new JoystickButton(m_driveController, 8))
      .whileActiveOnce(new ExtendClawCmd(m_ClimbSstm));
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
      autoCommand = new DriveStraight(m_DriveSstm, 0.5).withTimeout(5);
      break;
    case "moveBackAndShoot":
      autoCommand = new SequentialCommandGroup(new AimChassisCmd(m_DriveSstm).withTimeout(5),
          new ShootCmd(m_ShooterSstm).withTimeout(2), new ParallelCommandGroup(
              new TunnelCmd(m_TunnelSstm, () -> 0).withTimeout(5), new ShootCmd(m_ShooterSstm).withTimeout(5)));
      break;
    default:
      autoCommand = new DriveStraight(m_DriveSstm, 0.5).withTimeout(5);
      break;
    }

    return autoCommand;
  }

}
