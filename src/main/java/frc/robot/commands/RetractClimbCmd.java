package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSstm;

public class RetractClimbCmd extends CommandBase {

  private final ClimberSstm m_ClimberSstm;

  public RetractClimbCmd(ClimberSstm m_ClimberSstm) {
    this.m_ClimberSstm = m_ClimberSstm;
    addRequirements(m_ClimberSstm);
  }

  @Override
  public void initialize() {
    m_ClimberSstm.retractClimbers();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}