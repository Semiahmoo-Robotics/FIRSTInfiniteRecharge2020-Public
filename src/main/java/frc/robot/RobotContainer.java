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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AimChassisCmd;
import frc.robot.commands.BoostRobotDriveCmd;
import frc.robot.commands.LaunchCellCmd;
import frc.robot.commands.PreciseRobotDriveCmd;
import frc.robot.commands.TankDriveCmd;
import frc.robot.subsystems.DriveSstm;
import frc.robot.subsystems.LauncherSstm;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here.
  private final DriveSstm m_DriveSstm = new DriveSstm();
  private final LauncherSstm m_LauncherSstm = new LauncherSstm();

  //OI devices
  public final XboxController m_controller = new XboxController(Constants.CONTROLLER_PORT);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   * Constructor runs at robot init - Place robot init code here.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Sets the default command for the drivetrain subsystem
    setDefaultDrive();
  
    }

  private void setDefaultDrive() {
    //#1 - Tank Drive
    m_DriveSstm.setDefaultCommand(new TankDriveCmd(
        m_DriveSstm, () -> m_controller.getY(Hand.kLeft), () -> m_controller.getY(Hand.kRight)));
  

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
    
    //Left Bumper -> Boost Drive
    new JoystickButton(m_controller, Button.kBumperRight.value)
        .whenHeld(new BoostRobotDriveCmd(m_DriveSstm));
    //Right Bumper -> Precision Drive
    new JoystickButton(m_controller, Button.kBumperLeft.value)
        .whenHeld(new PreciseRobotDriveCmd(m_DriveSstm));
    //A Button -> Aim on Vision Targets (LimeLight)
    new JoystickButton(m_controller, Button.kA.value)
        .whenHeld(new AimChassisCmd(m_DriveSstm));
    //X Button -> Shoot Power Cells
    new JoystickButton(m_controller, Button.kX.value)
        .whenHeld(new LaunchCellCmd(m_LauncherSstm));
  }

  /**
  * Use this to pass the autonomous command to the main {@link Robot} class.
  *
  * @return the command to run in autonomous.
  */
  public Command getAutonomousCommand() {
    return new InstantCommand();
  }

}
