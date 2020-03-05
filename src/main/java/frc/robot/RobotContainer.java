/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AimChassisCmd;
import frc.robot.commands.BoostRobotDriveCmd;
import frc.robot.commands.ExtendClawCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.PreciseRobotDriveCmd;
import frc.robot.commands.RetractClawCmd;
import frc.robot.commands.ShootCmd;
import frc.robot.commands.TankDriveCmd;
import frc.robot.commands.TriggerLaunchCmd;
import frc.robot.commands.TunnelCmd;
import frc.robot.subsystems.ClimbSstm;
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
  private final ClimbSstm m_ClimbSstm = new ClimbSstm();

  // OI devices
  private final XboxController m_controller = new XboxController(Constants.CONTROLLER_PORT);


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   * Constructor runs at robot init - Place robot init code here.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Sets the default command for the drivetrain subsystem
    setDefaultDrive();

    m_LauncherSstm.setDefaultCommand(new TriggerLaunchCmd(m_LauncherSstm,
       () -> m_controller.getTriggerAxis(Hand.kLeft), () -> m_controller.getTriggerAxis(Hand.kRight)));
  
    }

  private void setDefaultDrive() {
    //#1 - Tank Drive
    m_DriveSstm.setDefaultCommand(new TankDriveCmd(
        m_DriveSstm, () -> m_controller.getY(Hand.kLeft), () -> m_controller.getY(Hand.kRight)));

    /*m_LauncherSstm.setDefaultCommand(new LauncherCmd(
      m_LauncherSstm, () -> m_controller.getTriggerAxis(Hand.kLeft), () -> m_controller.getTriggerAxis(Hand.kLeft);
    );*/
    /* //#2 - Arcade Drive
    m_DriveSstm.setDefaultCommand(new ArcadeDriveCmd(
        m_DriveSstm, () -> m_controller.getY(Hand.kLeft), () -> m_controller.getX(Hand.kLeft)));
    */
    
/*     // #3 - Curvature Drive
    m_DriveSstm.setDefaultCommand(new CurvatureDriveCmd(
        m_DriveSstm, () -> m_controller.getY(Hand.kLeft), () -> m_controller.getX(Hand.kLeft), () -> m_controller.getBButton()));
 */  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    //Right Bumper -> Boost Drive
    new JoystickButton(m_controller, Button.kBumperRight.value)
        .whenHeld(new BoostRobotDriveCmd(m_DriveSstm));
    //Left Bumper -> Precision Drive
    new JoystickButton(m_controller, Button.kBumperLeft.value)
        .whenHeld(new PreciseRobotDriveCmd(m_DriveSstm));
    //A Button -> Aim on Vision Targets (LimeLight)
    new JoystickButton(m_controller, Button.kA.value)
        .whenHeld(new AimChassisCmd(m_DriveSstm));
    //Left Stick Button -> Tunnel Shuffle Up
    new JoystickButton(m_controller, Button.kStickLeft.value)
    .whenHeld(new TunnelCmd(m_LauncherSstm, Constants.TUNNEL_SHUFFLE_UP_SPEED));
    //Right Stick Button -> Tunnel Shuffle Down
    new JoystickButton(m_controller, Button.kStickRight.value)
    .whenHeld(new TunnelCmd(m_LauncherSstm, Constants.TUNNEL_SHUFFLE_DOWN_SPEED));
    //B Button -> Trigger Claw Motor
    new JoystickButton(m_controller, Button.kB.value)
    .whenHeld(new ExtendClawCmd(m_ClimbSstm));
  }

  /**
  * Use this to pass the autonomous command to the main {@link Robot} class.
  *
  * @return the command to run in autonomous.
  */
  public Command getAutonomousCommand() {

    // Create a voltage constraint to ensure we don't accelerate too fast
    final var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(
      Constants.KS_VOLTS, Constants.KV_VOLT_SEC_PER_METER, Constants.KA_VOLT_SEC_SQUARE_PER_METER)
      , Constants.DRIVE_KINEMATICS, 10);

    // Create config for trajectory
    final var trajectoryConfig = new TrajectoryConfig(Constants.MAX_VEL_MPS, Constants.MAX_ACC_MPS2)
        .setKinematics(Constants.DRIVE_KINEMATICS).addConstraint(autoVoltageConstraint);
    
    // Trajectory in which the robot will follow
    final Trajectory autoTrajectory = TrajectoryGenerator.generateTrajectory(
      //Starting point: at origin facing +X direction
      new Pose2d(0, 0, new Rotation2d(0)), 
      // TODO set waypoints to travel through
      List.of(
        new Translation2d(1, 1),
        new Translation2d(2, -1)
      ),
      //Ending point: TODO set ending point and direction facing.
      new Pose2d(3, 0, new Rotation2d(0)),
      // pass trajectory config
      trajectoryConfig);

    // Get the total time of the trajectory in seconds and sends it to smart dashboard.
    SmartDashboard.putNumber("Auto Length (sec)", autoTrajectory.getTotalTimeSeconds());
    
    final RamseteCommand autoRamseteCommand = new RamseteCommand(
      autoTrajectory,
      m_DriveSstm::getPose,
      new RamseteController(Constants.RAMSETE_B, Constants.RAMSETE_ZETA),
      new SimpleMotorFeedforward(Constants.KS_VOLTS, Constants.KV_VOLT_SEC_PER_METER, Constants.KA_VOLT_SEC_SQUARE_PER_METER),
      Constants.DRIVE_KINEMATICS,
      m_DriveSstm::getWheelSpeeds,
      new PIDController(Constants.KP_DRIVE_VEL, 0, 0),
      new PIDController(Constants.KP_DRIVE_VEL, 0, 0),
      m_DriveSstm::tankDriveVolts,
      m_DriveSstm);
    
    // Run path following command, then stop at the end.
    return autoRamseteCommand.andThen(() -> m_DriveSstm.tankDriveVolts(0, 0));

  }

}
