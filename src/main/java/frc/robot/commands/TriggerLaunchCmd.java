package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.LauncherSstm;

public class TriggerLaunchCmd extends CommandBase {
  
  private final LauncherSstm m_launcherSstm;
  private final DoubleSupplier m_intakeValue;
  private final DoubleSupplier m_shooterValue;

  public TriggerLaunchCmd(LauncherSstm m_launcherSstm, DoubleSupplier m_intakeValue, DoubleSupplier m_shooterValue) {
    this.m_launcherSstm = m_launcherSstm;
    this.m_intakeValue= m_intakeValue;
    this.m_shooterValue = m_shooterValue;
    addRequirements(m_launcherSstm);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_intakeValue.getAsDouble() > Constants.TRIGGER_DEADBAND)
      m_launcherSstm.setIntake(Constants.INTAKE_SPEED);
    else
      m_launcherSstm.stopIntake();
    if (m_shooterValue.getAsDouble() > Constants.TRIGGER_DEADBAND) {
      m_launcherSstm.setTunnel(Constants.TUNNEL_SPEED);
      m_launcherSstm.setLauncher(Constants.LAUNCHER_SPEED);
    } else {
      m_launcherSstm.stopTunnel();
      m_launcherSstm.stopLauncher();
    }
  }


}